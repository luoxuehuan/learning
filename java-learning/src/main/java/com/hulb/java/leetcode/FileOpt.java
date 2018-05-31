package com.hulb.java.leetcode;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hulb
 * @date 2018/3/10 下午1:56
 *
 *
 java -cp opt.jar com.hulb.java.leetcode.FileOpt /Users/hulb/Desktop/hd/mulu.xls /Users/hulb/Desktop/hd/files/ /Users/hulb/Desktop/hd/dirs/ 4


 */
public class FileOpt {



    /**
     * 文件操作 将一个文件夹中的文件 根据关键词 分类 移动到相应的文件夹中
     */
    public static void main(String[] args){


//        String excelPath  = "/Users/hulb/Desktop/hd/mulu.xls";
//        String filesPath = "/Users/hulb/Desktop/hd/files/";
//        String destPath = "/Users/hulb/Desktop/hd/dirs/";
//        String col = "4";

        String excelPath  = args[0];
        String filesPath = args[1];
        String destPath = args[2];
        String col = args[3];

        Integer idex = Integer.valueOf(col);
        List<String> words = readExcel(excelPath,idex);
        /**
         * 读取excel 单元格形成string数组
         *
         * 根据string数组搜索 文件夹下 文件
         *
         * 符合要求,移动到相应文件夹，如果文件夹不存在，先创建文件夹。
         *
         * 循环string数组。
         *
         * 循环结束
         */
        List<String> files = getFiles(filesPath,1);
        for(String word:words){
            for(String file:files){
                if(file.contains(word)){
                    System.out.println("开始将["+file+"]移动到：["+word+"]文件夹");
                    moveTotherFolders(word,file,destPath,filesPath);
                }
            }
        }
        System.out.println("aa");
    }



    public static void moveTotherFolders(String word,String fileName,String destPath,String filesPath){
        String startPath =  filesPath + fileName;
        String endPath = destPath + word;
        try {
            File startFile = new File(startPath);
            //获取文件夹路径
            File tmpFile = new File(endPath);
            //判断文件夹是否创建，没有创建则创建新文件夹
            if(!tmpFile.exists()){
                tmpFile.mkdirs();
            }
            System.out.println("正在将文件["+startPath +"]移动到:"+ tmpFile);
            if (startFile.renameTo(new File(endPath + "/"+startFile.getName()))) {
                System.out.println("File is moved successful!");
            } else {
                System.out.println("File is failed to move!");
            }
        } catch (Exception e) {


        }
    }

    public static List<String> readExcel(String filePath,Integer idex){
        List<String> words = new ArrayList();
        try {
            File file = new File(filePath);
            InputStream inputStream = new FileInputStream(file);
            String fileName = file.getName();
            Workbook wb = null;
            // poi-3.9.jar  只可以读取2007以下的版本，后缀为：xsl //解析xls格式
            wb = new HSSFWorkbook(inputStream);

            //第一个工作表  ，第二个则为1，以此类推...
            Sheet sheet = wb.getSheetAt(0);

            int firstRowIndex = sheet.getFirstRowNum();
            int lastRowIndex = sheet.getLastRowNum();
            for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex ++){
                Row row = sheet.getRow(rIndex);
                if(row != null){
                    int firstCellIndex = row.getFirstCellNum();
                    // int lastCellIndex = row.getLastCellNum();
                    //此处参数cIndex决定可以取到excel的列数。
                    for(int cIndex = firstCellIndex; cIndex < idex; cIndex ++){
                        Cell cell = row.getCell(cIndex);
                        String value = "";
                        if(cell != null){
                            value = cell.toString();
                            System.out.println(value);
                            words.add(value);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
        return words;
    }



    private static List<String> getFiles(String path,int deep){
        List<String> fileNames = new ArrayList<>();
        File file = new File(path);
        File[] array = file.listFiles();
        for(int i=0;i<array.length;i++) {
            if(array[i].isFile()) {
                // 只输出文件名字
                System.out.println( array[i].getName());
                fileNames.add(array[i].getName());
            }
        }
        return fileNames;
    }
}
