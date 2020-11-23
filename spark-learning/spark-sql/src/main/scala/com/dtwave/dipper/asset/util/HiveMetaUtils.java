package com.dtwave.dipper.asset.util;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.*;
import org.apache.thrift.TException;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.util.List;

/**
 * @author hulb
 * @date 2020/3/2 上午11:46
 */
public class HiveMetaUtils {

    public HiveMetaStoreClient hiveMetaStoreClient;

    public HiveMetaUtils(String url) {
        HiveConf hiveConf = new HiveConf();
        hiveConf.set("hive.metastore.uris", "thrift://localhost:9083");
        //设置hiveMetaStore服务的地址
        try {
            hiveMetaStoreClient = new HiveMetaStoreClient(hiveConf);
        } catch (MetaException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) throws Exception {

        // time();
        HiveMetaUtils hiveMetaUtils = new HiveMetaUtils("thrift://localhost:9083");

        try {

            //当前版本2.3.4与集群3.0版本不兼容，加入此设置
            //hiveMetaStoreClient.setMetaConf("hive.metastore.client.capability.check","false");
            Database database = hiveMetaUtils.hiveMetaStoreClient.getDatabase("default");
            // 根据数据库名称获取所有的表名
            List<String> tablesList = hiveMetaUtils.hiveMetaStoreClient.getAllTables("default");
            // 由表名和数据库名称获取table对象(能获取列、表信息)
            Table table = hiveMetaUtils.hiveMetaStoreClient.getTable("default", "quality_all");

            hiveMetaUtils.judgePartitionTable(table);
            hiveMetaUtils.computePartitionTableSize(hiveMetaUtils.hiveMetaStoreClient, "","",0,0);
            //判断是否是分区表

            Table tableOne = hiveMetaUtils.hiveMetaStoreClient.getTable("default", "quality_one");
            hiveMetaUtils.judgePartitionTable(tableOne);
            hiveMetaUtils.computeNonPartitionTableSize(tableOne);

            // 获取所有的列对象
            List<FieldSchema> fieldSchemaList = table.getSd().getCols();
            short s = 1000;


            //计算分区表一定时间内的totalSize


            // 关闭当前连接
            //获取partitons的存储量

            hiveMetaUtils.hiveMetaStoreClient.close();
        } catch (MetaException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }


    }

    public long computeNonPartitionTableSize(Table tableOne) {
        String totalSizeString = tableOne.getParameters().getOrDefault("totalSize", "0");
        long size = Long.valueOf(totalSizeString);
        System.out.println(tableOne.getTableName() + "该表存储量为：" + size);
        return size;
    }


    /**
     * TODO
     * Product
     * 计算一定时间范围内的分区表的存储量
     * @param hiveMetaStoreClient
     * @param dbName
     * @param tableName
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    public long computePartitionTableSize(HiveMetaStoreClient hiveMetaStoreClient, String dbName, String tableName, Integer startTime, Integer endTime) throws Exception {
        short maxPart = -1;
        List<Partition> partitions = hiveMetaStoreClient.listPartitions(dbName, tableName, maxPart);
        Long totalSize = 0L;
        //计算分区表的totalSize
        for (int i = 0; i < partitions.size(); i++) {
            Partition partition = partitions.get(i);
            int createTime = partition.getCreateTime();
            //如果createTime满足条件
            String partitionSize = partition.getParameters().getOrDefault("totalSize", "0");
            Long ps = Long.valueOf(partitionSize);
            if(startTime>0){
                if (createTime >= startTime && createTime < endTime) {
                    totalSize = totalSize + ps;
                }
            }else{
                totalSize = totalSize + ps;
            }
        }
        System.out.println("该分区表的size" + totalSize);
        return totalSize;
    }

    public boolean judgePartitionTable(Table table) {
        if (table.getPartitionKeys().size() > 0) {
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

    public Table getTable(String dbName, String tableName) throws Exception{
        return hiveMetaStoreClient.getTable(dbName,tableName);
    }
}
