
grammar DSLSQL;

@header {
package streaming.dsl.parser;
}

//load jdbc.`mysql1.tb_v_user` as mysql_tb_user;
//save csv_input_result as json.`/tmp/todd/result_json` partitionBy uid;
statement
    : (sql ender)*
    ;


sql
    : ('load'|'LOAD') format '.' path 'options'? expression? booleanExpression*  'as' tableName
    | ('save'|'SAVE') (overwrite | append | errorIfExists |ignore)* tableName 'as' format '.' path 'options'? expression? booleanExpression* ('partitionBy' col)?
    | ('select'|'SELECT') ~(';')* 'as' tableName
    | ('insert'|'INSERT') ~(';')*
    | ('create'|'CREATE') ~(';')*
    | ('set'|'SET') setKey '=' setValue
    | ('connect'|'CONNECT') format ('(' colTypeList ')')? 'where'? expression? booleanExpression* ('as' db)?
    | ('source'|'SOURCE') format 'where'? expression? booleanExpression* ('as' db)?
    | ('train'|'TRAIN') tableName 'as' format '.' path 'where'? expression? booleanExpression*
    | ('register'|'REGISTER') format '.' path 'as' functionName
    |  SIMPLE_COMMENT
    ;


colTypeList
    : colType (',' colType)*
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

overwrite
    : 'overwrite'
    ;

append
    : 'append'
    ;

errorIfExists
    : 'errorIfExists'
    ;

ignore
    : 'ignore'
    ;

booleanExpression
    : 'and' expression
    ;

expression
    : identifier '=' STRING
    ;

ender
    :';'
    ;

format
    : identifier
    ;

path
    : quotedIdentifier | identifier
    ;

setValue
    : quotedIdentifier | STRING
    ;

setKey
    : identifier
    ;

db
    :qualifiedName | identifier
    ;

tableName
    : identifier
    ;

functionName
    : identifier
    ;

col
    : identifier
    ;

qualifiedName
    : identifier ('.' identifier)*
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

fragment DIGIT
    : [0-9]
    ;

fragment LETTER
    : [a-zA-Z]
    ;

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