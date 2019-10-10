package com.hulb.java.china;

import com.dtwave.common.util.FileUtil;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 提取中文
 *
 * @author hulb
 * @date 2019-09-11 17:33
 */
public class ChinaRegex {

    public static void main(String[] args) throws Exception {
        replace();
    }

    public static void test1() throws Exception {
        List<String> lines = FileUtil.lines("/Users/hulb/data/csv/international.txt");
        Long d = 300001010L;
        for (Long i = 0L; i < lines.size(); i++) {
            System.out.println(d + i);
            //System.out.print("=");
            //System.out.println(lines.get(Integer.valueOf(String.valueOf(i))));
        }

    }


    public static void test2() throws Exception {
        List<String> lines = FileUtil.lines("/Users/hulb/data//china.txt");
        String className = "";
        for (String line : lines) {
            if (line.contains("java") && line.contains("/")) {
                String[] li = line.split("/");
                className = li[li.length - 1];
                className = className.split("\\.")[0];
                // System.out.println("------------------------------------------------------------------------"+className);
            }
            test3(line, className);
        }
    }


    public static void test3(String line, String tag) {
        // 标点也匹配
        Pattern p = Pattern.compile("\"(.*?)\"");
        //Matcher m = p.matcher("throw new BizException(\"系统目录无法删除\");");
        Matcher m = p.matcher(line);
        if (m.find()) {
            System.out.println(m.group(1) + "\t" + tag);
        }
        // System.out.println(m.find() ? m.group(1) : "nothing");
    }

    /**
     * 将中文替换成编码
     *
     * @throws Exception
     */
    public static void replace() throws Exception {

        String fileName = "/Users/hulb/project/dipper/cheetah-server/cheetah-server-provider/src/main/java/com/dtwave/cheetah/server/service/clusteruser/ClusterUserServiceImpl.java";
        String replaceFile = "/Users/hulb/project/dipper/cheetah-server/cheetah-server-provider/src/main/resources/messages/messages_zh_CN.properties";
        List<String> lines = FileUtil.lines(fileName);
        List<String> replaceFileLines = FileUtil.lines(replaceFile);
        Map<String, String> pro = new LinkedHashMap<>();
        replaceFileLines.forEach(s -> {
            String[] split = s.split("=");
            pro.put("\"" + split[1] + "\"", "I18NUtils.getInternationalMessage(\"" + split[0] + "\")");
        });
        System.out.println(pro.size());
        for (int i = 0; i < lines.size(); i++) {
            String newLine = lines.get(i);
            for (Map.Entry<String, String> entry : pro.entrySet()) {
                if (newLine.contains(entry.getKey())) {
                    System.out.println("要替换的中文:================" + entry.getKey());
                    newLine = newLine.replace(entry.getKey(), entry.getValue());
                }
            }
            lines.set(i, newLine);
        }
        StringBuffer stringBuffer = new StringBuffer();
        lines.forEach(ss -> {
            stringBuffer.append(ss);
            stringBuffer.append("\n");
        });
        //将替换好的line写回到原来的文件中
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, false));
            out.write(stringBuffer.toString());
            out.close();
        } catch (IOException e) {
        }
    }
}
