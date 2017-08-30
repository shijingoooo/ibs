package com.capinfo.framework.common.util;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Zhang Chuanjia
 * @Date: 2017/8/21 16:49
 * @Description:
 */
public class ExportExcel{
    public void exportExcel(String[] header, String[] subHeader,String fileName, Object[] dataArr,List<Map<String, Object>> dataList,HttpServletResponse response) {
        //声明表格类
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建名称为fileName的表格
        XSSFSheet sheet = workbook.createSheet(fileName);
        //设置表格默认宽度
        sheet.setDefaultColumnWidth((short) 40);
        //风格
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);//左右居中
        style.setAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直居中
        //字体
        XSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 12);
        //加入到风格
        style.setFont(font);

        List<Object[]> dataRList = new ArrayList<Object[]>();
        for (Map<String, Object> map: dataList){
            Object[] obj = dataArr.clone();
            for (Map.Entry<String, Object> entry : map.entrySet()){
                for(int index = 0; index < subHeader.length; index++){
                    if (subHeader[index].equals(entry.getKey())){
                        obj[index] = entry.getValue();
                        break;
                    }
                }
            }
            dataRList.add(obj);
        }

        //创建行
        XSSFRow row = null;
        XSSFCell cell;

        //创建表格列标题行
        int rowIndex = 0;
        row = sheet.createRow(rowIndex++);
        for (short i = 0; i < header.length; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(style);
            XSSFRichTextString text = new XSSFRichTextString(header[i]);
            cell.setCellValue(text);
        }
        try {
            for (Object[] objArr: dataRList){
                row = sheet.createRow(rowIndex++);
                for (int j = 0; j < objArr.length; j++){
                    cell = row.createCell(j);
                    cell.setCellStyle(style);

                    String textValue = null;
                    //判断数据不为空的话就转换成string
                    if(objArr[j] != null && objArr[j] != ""){
                        textValue = objArr[j].toString();
                    }
                    if (textValue != null) {
                        XSSFRichTextString richString = new XSSFRichTextString(textValue);
                        cell.setCellValue(richString);
                    }
                }
            }
            getExportedFile(workbook,fileName,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
      
    /**
     * 
     * @param workbook
     * @param name
     * @param response
     * @throws Exception
     */
    public void getExportedFile(XSSFWorkbook workbook, String name,HttpServletResponse response) throws Exception {  
        BufferedOutputStream fos = null;  
        try {  
            String fileName = name + ".xls";  
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");//后加的
            response.setContentType("application/octet-stream;charset=utf-8");//后加的
            response.setHeader("Content-Disposition", "attachment;filename=" + new String( fileName.getBytes("gb2312"), "ISO8859-1" ));
            fos = new BufferedOutputStream(response.getOutputStream());

            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }
}