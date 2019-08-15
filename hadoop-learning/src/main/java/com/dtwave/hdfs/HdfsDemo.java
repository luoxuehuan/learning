package com.dtwave.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.net.URI;
import org.apache.hadoop.fs.Path;

/**
 * @author hulb
 *
 * 验证create 目录权限
 *
 * @date 2018/11/16 下午8:43
 */
public class HdfsDemo {

    public static void main(String[] args) throws Exception{
        String hdfsUri = "hdfs://localhost:9000";
        String user = "hulb2";
        String path = "/data/linkage";
        System.out.println(sizeCount(hdfsUri,user,path));
    }


    /**
     * 计算目录的存储大小 返回byte单位大小
     * @param uri
     * @param user
     * @param pathString
     * @return
     * @throws Exception
     */
    public static long sizeCount(String uri,String user,String pathString) throws Exception{
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI(uri),configuration,user);
        return fileSystem.getContentSummary(new Path(pathString)).getLength();
    }


    /**
     * 创建目录
     * @param uri
     * @param user
     * @param pathString
     * @return
     * @throws Exception
     */
    public static boolean mkdir(String uri,String user,String pathString) throws Exception{
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI(uri),configuration,user);
        Path dir = new Path(pathString);
        return fileSystem.mkdirs(dir);
    }
}
