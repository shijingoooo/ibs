package com.capinfo.modules.utils;

/**
 * Created by 何 on 2017/3/17.
 */

import org.bson.Document;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV文件导出工具类
 * <p>
 * Created on 2014-08-07
 *
 * @author
 * @reviewer
 */
public class CSVUtils {

    private BufferedWriter csvWtriter = null;

    public File createCsvFile(String outPutPath, String fileName) {
        File csvFile = null;
        try {
            csvFile = new File(outPutPath + File.separator + fileName + ".csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();
        } catch (IOException e) {
        }
        return csvFile;
    }

    public void writeHeader(File csvFile, List<Object> head) {
        try {
            // GB2312使正确读取分隔符","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    csvFile), "GB2312"), 512);
            // 写入文件头部
            writeRow(head, csvWtriter);
        } catch (IOException e) {
        }
    }

    public void writeContent(List<Document> sourceList) {
        try {
            List<List<Object>> targetList = new ArrayList<List<Object>>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Document doc : sourceList) {
                List<Object> dataList = new ArrayList<Object>();
                dataList.add(doc.get("calibration_tsp"));
                dataList.add(doc.get("calibration_noise"));
                dataList.add(doc.get("calibration_two_pm"));
                dataList.add(doc.get("calibration_ten_pm"));
                dataList.add(sdf.format(doc.get("col_time")));
                targetList.add(dataList);
            }
            // 写入文件内容
            for (List<Object> row : targetList) {
                writeRow(row, csvWtriter);
            }
        } catch (IOException e) {
        }
    }

    public void flush() {
        try {
            csvWtriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            csvWtriter.flush();
            csvWtriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写一行数据方法
     *
     * @param row
     * @param csvWriter
     * @throws IOException
     */
    private void writeRow(List<Object> row, BufferedWriter csvWriter) throws IOException {
        // 写入文件头部
        for (Object data : row) {
            StringBuffer sb = new StringBuffer();
            String rowStr = sb.append("\"").append(data).append("\",").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }
}