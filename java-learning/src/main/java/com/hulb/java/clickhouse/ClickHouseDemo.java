package com.hulb.java.clickhouse;

import ru.yandex.clickhouse.ClickHouseConnection;
import ru.yandex.clickhouse.ClickHouseConnectionImpl;
import ru.yandex.clickhouse.ClickHouseStatement;
import ru.yandex.clickhouse.domain.ClickHouseFormat;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * clickhouse修改配置文件
 *
 * clickhouse启动停止
 *
 *
 *
 *
 * @author hulb
 * @date 2019-12-13 18:25
 */
public class ClickHouseDemo {

    public static void main(String[] args) throws SQLException {

        String sql1 = "select avg(ss_item_sk) from store_sales_log_83526448";//查询ontime数据量
        String sql2 = "select ss_sold_date_sk,count(*) as cnt from store_sales_log_83526448 group by ss_sold_date_sk order by cnt desc,ss_sold_date_sk limit 10";//查询ontime数据量
        String sql3 = "select ss_sold_date_sk,avg(ss_item_sk) as cnt from store_sales_log_83526448 group by ss_sold_date_sk order by cnt desc,ss_sold_date_sk limit 10";//查询ontime数据量
        String sql4 = "select ss_item_sk,count(*) from store_sales_log_83526448 group by ss_item_sk having count(*)>1 limit 10";//查询ontime数据量
        String sql5 = "select sum(ss_item_sk) from store_sales_log_83526448";//查询ontime数据量
        String sql6 = "select ss_sold_date_sk,4,avg(ss_item_sk) as cnt from store_sales_log_83526448 group by ss_sold_date_sk,ss_wholesale_cost order by cnt desc,ss_sold_date_sk limit 10";//查询ontime数据量
        String sql7 = "select ss_sold_date_sk,ss_wholesale_cost,avg(ss_item_sk) as cnt from store_sales_log_83526448 group by ss_sold_date_sk,ss_wholesale_cost order by cnt desc,ss_sold_date_sk limit 10";//查询ontime数据量
        String sql8 = "select ss_sold_date_sk,ss_wholesale_cost,avg(ss_item_sk) as cnt,count(distinct(ss_sales_price)) as avg1 from store_sales_log_83526448 group by ss_sold_date_sk,ss_wholesale_cost  order by cnt desc,ss_sold_date_sk limit 10";//查询ontime数据量
        String sql9 = "select avg(ss_item_sk) as cnt,avg(ss_sales_price),count(distinct(ss_sales_price)) ,count(ss_item_sk) from store_sales_log_83526448 group by ss_sold_date_sk,ss_wholesale_cost  order by cnt desc,ss_sold_date_sk limit 10";//查询ontime数据量

        exeSql(sql1);
        exeSql(sql1);
        exeSql(sql1);
//        exeSql(sql4);
//        exeSql(sql5);
//        exeSql(sql6);
//        exeSql(sql7);
//        exeSql(sql8);
//        exeSql(sql9);





//        ClickHouseConnection connection =  new ClickHouseConnectionImpl("jdbc:clickhouse://47.97.42.242:8123/default");
//        ClickHouseStatement sth = connection.createStatement();
//        try {
//            sth.write() // Write API entrypoint
//                    .table("default.my_table") // where to write data
//                    .option("format_csv_delimiter", ";") // specific param
//                    .data(new File("/path/to/file.csv"), ClickHouseFormat.CSV) // specify input
//                    .send();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }


    public static void exeSql(String sql){
        //String address = "jdbc:clickhouse://47.97.42.242:8123/default";
        String address = "jdbc:clickhouse://121.41.5.167:8123/default";
        Connection connection = null;
        Statement statement = null;
        ResultSet results = null;
        try {
            Class.forName("ru.yandex.clickhouse.ClickHouseDriver");
            connection = DriverManager.getConnection(address);
            statement = connection.createStatement();
            long begin = System.currentTimeMillis();
            results = statement.executeQuery(sql);
            long end = System.currentTimeMillis();
            System.out.println("执行（"+sql+"）耗时："+(end-begin)+"ms");
            ResultSetMetaData rsmd = results.getMetaData();
            List<Map> list = new ArrayList();
            while(results.next()){
                Map map = new HashMap();
                for(int i = 1;i<=rsmd.getColumnCount();i++){
                    map.put(rsmd.getColumnName(i),results.getString(rsmd.getColumnName(i)));
                }
                list.add(map);
            }
            for(Map map : list){
                System.err.println(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {//关闭连接
            try {
                if(results!=null){
                    results.close();
                }
                if(statement!=null){
                    statement.close();
                }
                if(connection!=null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
