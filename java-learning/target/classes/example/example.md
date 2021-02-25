## 模板1


```
/**
  * 该示例从DIS读取数据并将查询结果写入DIS
  * >>>>>>>>>请务必确保您的账户下已在数据接入服务（DIS）里创建了您配置的通道<<<<<<<<<<
      >>>>>>>>>*
      >>>>>>>>>  **/

/*************************正式业务逻辑开始**************************************/

/** 创建输入流，从DIS的csinput通道获取数据。
  *
  * 根据实际情况修改以下选项：
  * channel：数据所在通道名
  * partitionCnt：该通道分区数
  * encode: 数据编码方式，可以是csv或json
  * fieldDelimiter：当编码格式为csv时，属性之间的分隔符
      **/
      CREATE SOURCE STREAM car_speed_source (
        car_id STRING, /* 车辆ID */
        car_name STRING, /* 车辆名 */
        car_location STRING, /* 车辆位置 */
        car_speed INT /* 车速 */
      )
      WITH (
        type = "dis",
        region = "cn-north-1",
        channel = "csinput",
        partitionCnt = "1",
        encode = "csv",
        fieldDelimiter = ","
      );

/** 创建SMN输出流.
  *
  * 根据实际情况修改以下选项：
  * topicUrn：SMN服务的主题URN，作为消息通知的目标主题，需要提前在SMN服务中创建
  * messageSubject：发往SMN服务的消息标题
  * messageColumn：输出流的列名，其内容作为消息的内容
      **/
      CREATE SINK STREAM over_speed_warning (
        over_speed_message STRING /* over speed message */
      )
      WITH (
        type = "smn",
        region = "cn-north-1",
        topicUrn = "urn:smn:cn-north-1:38834633fd6f4bae813031b5985dbdea:overspeed",
        messageSubject = "message title",
        messageColumn = "over_speed_message"
      );

/** 将车速大于120的作为告警输出到SMN服务 **/
INSERT INTO over_speed_warning
SELECT "your car speed (" || CAST(car_speed as CHAR(20)) || ") exceeds the maximum speed, please slow down."
FROM car_speed_source
WHERE car_speed > 120;
​    

```


## 模板2



```
/** 创建输入流，从DIS的csinput通道获取数据。
  *
  * 根据实际情况修改以下选项：
  * channel：数据所在通道名
  * partitionCnt：该通道分区数
  * encode: 数据编码方式，可以是csv或json
  * fieldDelimiter：当编码格式为csv时，属性之间的分隔符
      **/
      CREATE SOURCE STREAM driver_behavior (
        DeviceID STRING, /* 设备ID */
        DataTime STRING, /* 数据时间 */
        ReceiveTime STRING, /* 接收时间 */
        IsACCOpen STRING, /* ACC是否打开 */
        Longitude STRING, /* 经度 */
        Latitude STRING, /* 纬度 */
        Velocity DOUBLE,/**/
        Direction STRING,
        Altitude STRING,
        Mileage STRING
      )
      WITH (
        type = "dis",
        region = "cn-north-1",
        channel = "csinput",
        partitionCnt = "1",
        encode = "csv",
        fieldDelimiter = ","
      ) TIMESTAMP BY proctime.proctime;

/** 创建输出流，结果输出到DIS的csoutput通道。
  *
  * 根据实际情况修改以下选项：
  * channel：数据所在通道名
  * partitionKey：当通道有多个分区时用来分发的主键
  * encode： 结果编码方式，可以为csv或者json
  * fieldDelimiter: 当编码格式为csv时，属性之间的分隔符
      **/
      CREATE SINK STREAM over_speed_info (DeviceID STRING, DataTime STRING, Longitude STRING, Latitude STRING, Velocity DOUBLE)
      WITH (
        type = "dis",
        region = "cn-north-1",
        channel = "csoutput",
        partitionKey = "DeviceID",
        encode = "csv",
        fieldDelimiter = ","
      );

/** 将部分字段输出 **/
INSERT INTO over_speed_info
SELECT DeviceID, DataTime, Longitude, Latitude, Velocity
FROM driver_behavior
WHERE Velocity > 35;

/** 创建输出流，结果输出到SMN。
  *
  * 根据实际情况修改以下选项：
  * topicUrn：SMN服务的主题URN，作为消息通知的目标主题，需要提前在SMN服务中创建
  * messageSubject：发往SMN服务的消息标题
  * messageColumn：输出流的列名，其内容作为消息的内容
      **/
      CREATE SINK STREAM over_speed_msg (
        MessageContent STRING /* 超速消息内容 */
      )
      WITH (
        type = "smn",
        region = "cn-north-1",
        topicurn = "urn:smn:cn-north-1:a77d6595e37d443fab32d1db9739ed17:OverSpeed_alarm",
        MESSAGESUBJECT = "OverSpeed_alarm",
        MESSAGECOLUMN = "MessageContent"
      );

/** 当30秒内连续超速三次及以上时，发送告警消息到SMN服务，实现用户手机终端实时告警功能 **/
INSERT INTO over_speed_msg
SELECT "Device (" || DeviceID || ") is overspeed with velocity of " || CAST(Velocity as VARCHAR(20))
FROM (
  SELECT DeviceID, MAX(Velocity) AS Velocity, COUNT(Velocity) AS overspeed_count
  FROM driver_behavior
  WHERE Velocity > 30
  GROUP BY TUMBLE (proctime, INTERVAL '30' SECOND), DeviceID
)
WHERE overspeed_count >= 3;
​    
```



## 模板3


```

/**
  * 该示例从OBS读取数据并将查询结果写入RDS
  * >>>>>>>>>请务必确保您的账户下已对象存储服务（OBS）里上传了您的数据源文件<<<<<<<<<<
      >>>>>>>>>*
      >>>>>>>>>  **/

/** 创建输入流，从OBS的input.csv文件中获取数据。
  *
  * 根据实际情况修改以下选项：
  * bucket：数据所在的OBS桶名
  * objectname: 数据所在的OBS桶中的对象名
  * rowDelimiter: 行间的分隔符
  * fieldDelimiter：属性之间的分隔符
      **/
      CREATE SOURCE STREAM student_scores (
        student_number STRING, /* 学号 */
        student_name STRING, /* 姓名 */
        subject STRING, /* 学科 */
        score INT /* 成绩 */
      )
      WITH (
        type = "obs",
        region = "cn-north-1",
        bucket = "obssource",
        objectname = "input.csv",
        rowDelimiter = "\n",
        fieldDelimiter = ","
      );

/** 创建输出流，结果输出到RDS数据库的table1中。
  *
  * 根据实际情况修改以下选项：
  * username：数据库用户名
  * password：数据库密码
  * driver：数据库驱动
  * dburl： 数据库url
  * tablename: 数据库表名
      **/
      CREATE SINK STREAM score_greate_90 (
        student_number STRING, /* 学号 */
        student_name STRING, /* 姓名 */
        subject STRING, /* 学科 */
        score INT /* 成绩 */
      )
      WITH (
        type = "rds",
        username = "root",
        password = "QWERqwer@123",
        driver = "com.mysql.jdbc.Driver",
        dburl = "jdbc:mysql://192.168.0.102:8635/test1",
        tablename = "table1"
      );

/** 将数学成绩大于90分的同学信息输出 **/
INSERT INTO score_greate_90
SELECT *
FROM student_scores
WHERE score > 90;
​    

```

## 模板4

```

    /**
  * 该示例从DIS读取数据并将查询结果写入DIS
  * >>>>>>>>>请务必确保您的账户下已在数据接入服务（DIS）里创建了您配置的通道<<<<<<<<<<
      >>>>>>>>>*
      >>>>>>>>>  **/

/**************************往输入流csinput插入测试数据*************************/
CREATE SINK STREAM student_scores_data (
  student_number STRING, /* 学号 */
  student_name STRING, /* 姓名 */
  subject STRING, /* 学科 */
  score INT /* 成绩 */
)
WITH (
  type ="dis",
  region = "cn-north-1",
  channel = "csinput",
  partitionKey = "student_name",
  encode = "csv",
  fieldDelimiter = ",\n"
);

INSERT INTO student_scores_data
SELECT "1", "lilei", "math", 95;
INSERT INTO student_scores_data
SELECT "2", "hanmeimei", "math", 85;
/************************往输入流插入测试数结束*********************************/

/*************************正式业务逻辑开始**************************************/

/** 创建输入流，从DIS的csinput通道获取数据。
  *
  * 根据实际情况修改以下选项：
  * channel：数据所在通道名
  * partitionCnt：该通道分区数
  * encode: 数据编码方式，可以是csv或json
  * fieldDelimiter：当编码格式为csv时，属性之间的分隔符
      **/
      CREATE SOURCE STREAM student_scores (
        student_number STRING, /* 学号 */
        student_name STRING, /* 姓名 */
        subject STRING, /* 学科 */
        score INT /* 成绩 */
      )
      WITH (
        type = "dis",
        region = "cn-north-1",
        channel = "csinput",
        partitionCnt = "1",
        encode = "csv",
        fieldDelimiter = ","
      );

/** 创建输出流，结果输出到DIS的csoutput通道。
  *
  * 根据实际情况修改以下选项：
  * channel：数据所在通道名
  * partitionKey：当通道有多个分区时用来分发的主键
  * encode： 结果编码方式，可以为csv或者json
  * fieldDelimiter: 当编码格式为csv时，属性之间的分隔符
      **/
      CREATE SINK STREAM score_greate_90 (
        student_number STRING, /* 学号 */
        student_name STRING, /* 姓名 */
        subject STRING, /* 学科 */
        score INT /* 成绩 */
      )
      WITH (
        type = "dis",
        region = "cn-north-1",
        channel = "csoutput",
        partitionKey = "student_name",
        encode = "csv",
        fieldDelimiter = ","
      );

/** 将数学成绩大于90分的同学信息输出 **/
INSERT INTO score_greate_90
SELECT *
FROM student_scores
WHERE score > 90;

​    
```


