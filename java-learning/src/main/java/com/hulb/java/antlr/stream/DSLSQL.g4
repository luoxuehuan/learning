
grammar DSLSQL;


/**
create source stream sourceName (
    name string,
    id string
)
option (
    contentType="1"
    , driver="com.mysql.jdbc.Driver"
    , connectUrl="mq250:9092,mq250:9092"
    , groupId="xxxxx"
    , topic="dtwave_access_log"
    , type="json"
);
*/

//【序列模式】可以写 多条 以;结尾 的sql(带终止符的序列模式匹配零个或多个sql ender序列)
statement
    : (sql ender)*
    ;

// 【选择模式】sql这有2个备选分支 一个是create source 一个是create sink ,一个是注释信息。 另一个找不到的当做默认主要逻辑sql。
// 【词法符号依赖模式】：'(' ')' 成对出现,用于分组
// 【嵌套模式】
sql
    :
    | create source stream tableName ('(' colTypeList ')')? 'option'? ('(' optionList ')')?
    | ('select' | 'SELECT') other
    |  SIMPLE_COMMENT
    ;

create :'create' | 'CREATE';

source :'source' | 'SOURCE' | 'sink' | 'SINK';

stream :'stream' | 'STREAM';

//列结构列表
colTypeList
    : colType (',' colType)*
    ;

//配置项列表
optionList
    : expression (',' expression)*
    ;

//列结构
colType
    : identifier dataType (COMMENT STRING)?
    ;

//数据类型
dataType
    : identifier
    ;

COMMENT: 'COMMENT';

other
    : (.)*?
    ;

expression
    : identifier '=' STRING
    ;

ender
    :';'
    ;


tableName
    : identifier
    ;

identifier
    : strictIdentifier
    ;

strictIdentifier
    : IDENTIFIER
    | quotedIdentifier
    ;

quotedIdentifier
    : BACKQUOTED_IDENTIFIER
    ;


STRING
    : '\'' ( ~('\''|'\\') | ('\\' .) )* '\''
    | '"' ( ~('"'|'\\') | ('\\' .) )* '"'
    ;

IDENTIFIER
    : (LETTER | DIGIT | '_')+
    ;

BACKQUOTED_IDENTIFIER
    : '`' ( ~'`' | '``' )* '`'
    ;

//【fragment】该规则本身不是词法符号,只会被其他词法规则使用！同时意味着文法规则中不能使用DIGIT;
fragment DIGIT
    : [0-9]
    ;

fragment LETTER
    : [a-zA-Z]
    ;

//【channel HIDDEN】放入一个隐藏的通道并输送给语法分析器。（类似与丢弃隐藏的意思  和 -> skip功能类似）
SIMPLE_COMMENT
    : '--' ~[\r\n]* '\r'? '\n'? -> channel(HIDDEN)
    ;

BRACKETED_EMPTY_COMMENT
    : '/**/' -> channel(HIDDEN)
    ;

BRACKETED_COMMENT
    : '/*' ~[+] .*? '*/' -> channel(HIDDEN)
    ;

WS
    : [ \r\n\t]+ -> channel(HIDDEN)
    ;

// Catch-all for anything we can't recognize.
// We use this to be able to ignore and recover all the text
// when splitting statements with DelimiterLexer
UNRECOGNIZED
    : .
    ;