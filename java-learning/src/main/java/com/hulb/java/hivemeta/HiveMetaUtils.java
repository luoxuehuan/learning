package com.hulb.java.hivemeta;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.*;
import org.apache.thrift.TException;
import org.joda.time.DateTime;

import java.util.List;

/**
 * @author hulb
 * @date 2020/2/28 下午5:55
 */
public class HiveMetaUtils {


    public static void main(String[] args) throws Exception{

        time();

        HiveMetaStoreClient hiveMetaStoreClient;
        HiveConf hiveConf = new HiveConf();
        hiveConf.set("hive.metastore.uris", "thrift://localhost:9083");
        try {
            //设置hiveMetaStore服务的地址
            hiveMetaStoreClient = new HiveMetaStoreClient(hiveConf);
            //当前版本2.3.4与集群3.0版本不兼容，加入此设置
            //hiveMetaStoreClient.setMetaConf("hive.metastore.client.capability.check","false");
            Database database = hiveMetaStoreClient.getDatabase("default");
            // 根据数据库名称获取所有的表名
            List<String> tablesList = hiveMetaStoreClient.getAllTables("default");
            // 由表名和数据库名称获取table对象(能获取列、表信息)
            Table table = hiveMetaStoreClient.getTable("default", "quality_all");

            judgePartitionTable(table);
            computePartitionTableSize(hiveMetaStoreClient,table);
            //判断是否是分区表

            Table tableOne = hiveMetaStoreClient.getTable("default", "quality_one");
            judgePartitionTable(tableOne);
            computeNonPartitionTableSize(tableOne);

            // 获取所有的列对象
            List<FieldSchema> fieldSchemaList = table.getSd().getCols();
            short s = 1000;


            //计算分区表一定时间内的totalSize


            // 关闭当前连接
            //获取partitons的存储量

            hiveMetaStoreClient.close();
        } catch (MetaException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }


    }

    private static long computeNonPartitionTableSize(Table tableOne) {
        String totalSizeString = tableOne.getParameters().getOrDefault("totalSize","0");
        long size = Long.valueOf(totalSizeString);
        System.out.println(tableOne.getTableName() + "该表存储量为："+size);
        return size;
    }


    private static long computePartitionTableSize( HiveMetaStoreClient hiveMetaStoreClient,Table table) throws Exception{
        short s = 1000;
        List<Partition> partitions =  hiveMetaStoreClient.listPartitions("default","quality_all",s);
        Long totalSize = 0L;
        //计算分区表的totalSize
        for(int i=0;i<partitions.size();i++){
            Partition partition = partitions.get(i);
            int createTime = partition.getCreateTime();
            //如果createTime满足条件
            if(createTime>0){
                String partitionSize = partition.getParameters().getOrDefault("totalSize","0");
                Long ps = Long.valueOf(partitionSize);
                totalSize = totalSize+ps;
            }
        }
        System.out.println("该分区表的size"+totalSize);
        return totalSize;
    }

    private static boolean judgePartitionTable(Table table) {
        if(table.getPartitionKeys().size()>0){
            System.out.println("是分区表");
            return true;
        }
        System.out.println("不是分区表");
        return false;
    }


    private static void time() {
        DateTime date = new DateTime();
        date.minusDays(1);







        System.out.println(1);

    }

}
