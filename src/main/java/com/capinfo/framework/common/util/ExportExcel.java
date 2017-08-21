package com.capinfo.framework.common.util;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: Zhang Chuanjia
 * @Date: 2017/8/21 16:49
 * @Description:
 */
public class ExportExcel<T> {
    public void exportExcel(Field[] fields,String[] headers,List<T> dataset, String fileName,HttpServletResponse response) {
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

        //创建行
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell;
//        /** 设置表头行*/
//        for (int i = 0; i < headers.length; i++) {
//            cell = row.createCell(i);
//            if(i == 0){
//                cell.setCellValue("小组设备信息");
//            }
//            cell.setCellStyle(style);
//            sheet.autoSizeColumn(i);//自动设宽
//        }
//        //横向：合并第一行的第2列到第4列
//        sheet.addMergedRegion(new CellRangeAddress(0,0,1,36));

        //创建表格列标题行
        row = sheet.createRow(0);
        for (short i = 4; i < headers.length; i++) {
            cell = row.createCell(i-4);
            cell.setCellStyle(style);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        try {
            //将查询到的数据遍历在表格中
            Iterator it = dataset.iterator();
            //标识创建表格的index
            int index = 0;
            while (it.hasNext()) {
                index++;
                row = sheet.createRow(index);
                T t = (T) it.next();
                for (short i = 4; i < headers.length; i++) {
                    cell = row.createCell(i-4);
                    cell.setCellStyle(style);
                    Field field = fields[i];
                    String fieldName = field.getName();
                    String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);  
                    Class tCls = t.getClass();
                    //返回指定的method，后面是参数列表（没有参数可不写）
                    Method getMethod = tCls.getMethod(getMethodName);
                    //对带有指定参数的指定对象调用由此 Method 对象表示的底层方法，后面是参数列表（没有参数可以不写）
                    Object value = getMethod.invoke(t);
                    //用于接收转换后的参数
                    String textValue = null;
                    //判断数据不为空的话就转换成string
                    if(value != null && value != ""){
                        textValue = value.toString();
                    }
                    if (textValue != null) {
                        XSSFRichTextString richString = new XSSFRichTextString(textValue);
                        cell.setCellValue(richString);
                    }
                }
            }
            getExportedFile(workbook, fileName,response);
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