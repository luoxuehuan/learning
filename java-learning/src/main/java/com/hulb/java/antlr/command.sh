hulbdeMacBook-Pro:sql hulb$ antlr4 SqlBase.g4
hulbdeMacBook-Pro:sql hulb$ javac *.java
hulbdeMacBook-Pro:sql hulb$ grun SqlBase statement -gui
SELECT * FROM TABLESD;



cd  java-learning/src/main/java/com/hulb/java/antlr/stream/

grun DSLSQL statement -gui

create SINK stream sourceName (
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


SELECT A,B FROM DTW.TABLE JOIN DTW.TABLE2 ORDER BY A LIMIT 10;