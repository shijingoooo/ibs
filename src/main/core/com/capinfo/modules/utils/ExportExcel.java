package com.capinfo.modules.utils;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.bson.Document;

public class ExportExcel<T>
{

    public void exportExcel(String[] headers,
                            List<Document> dataset,String fileName)
    {
        // 创建Excel的工作书册 Workbook,对应到一个excel文档
        HSSFWorkbook wb = new HSSFWorkbook();

        // 创建Excel的工作sheet,对应到一个excel文档的tab
        HSSFSheet sheet = wb.createSheet("sheet1");

        // 设置excel每列宽度
        sheet.setColumnWidth(0, 4000);
        sheet.setColumnWidth(1, 3500);

        // 创建字体样式
        HSSFFont font = wb.createFont();
        font.setFontName("Verdana");
        font.setBoldweight((short) 100);
        font.setFontHeight((short) 300);
        font.setColor(HSSFColor.BLUE.index);

        // 创建单元格样式
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        // 设置边框
        style.setBottomBorderColor(HSSFColor.RED.index);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);

        style.setFont(font);// 设置字体

        // 创建Excel的sheet的一行
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) 500);// 设定行的高度

        for (short i = 0; i < headers.length; i++)
        {

            HSSFCell cell = row.createCell(i);

            cell.setCellStyle(style);

            HSSFRichTextString text = new HSSFRichTextString(headers[i]);

            cell.setCellValue(text);

        }
        HSSFCellStyle style1 = wb.createCellStyle();
        HSSFRow row1;
        HSSFCell cell;
        String header = ",";
        for (String str : headers)
        {
            header += str + ",";
        }
        // 设置单元格内容格式
        for (int i = 0; i < dataset.size(); i++)
        {
            // style1.setWrapText(true);// 自动换行
            row1 = sheet.createRow(i + 1);
            if (dataset.get(i) != null)
            {
                int j = 0;

                // cell.setCellStyle(style1);
                if(header.indexOf(",TSP,")>=0){
                    if (dataset.get(i).get("calibration_tsp")!= null)
                    {
                        cell = row1.createCell(j++);
                        cell.setCellValue(dataset.get(i).get("calibration_tsp").toString());
                    }
                    else
                    {
                        cell = row1.createCell(j++);
                        cell.setCellValue("---");
                    }
                }
                if(header.indexOf(",NOISE,")>=0){
                    if (dataset.get(i).get("calibration_noise") != null)
                    {
                        cell = row1.createCell(j++);
                        cell.setCellValue(dataset.get(i).get("calibration_noise").toString());
                    }
                    else
                    {
                        cell = row1.createCell(j++);
                        cell.setCellValue("---");
                    }
                }
                if(header.indexOf(",PM2.5,")>=0){
                if (dataset.get(i).get("calibration_two_pm") != null)
                    {
                        cell = row1.createCell(j++);
                        cell.setCellValue(dataset.get(i).get("calibration_two_pm").toString());
                    }
                    else
                    {
                        cell = row1.createCell(j++);
                        cell.setCellValue("---");
                    }
                }
                if(header.indexOf(",PM10,")>=0){
                    if (dataset.get(i).get("calibration_ten_pm") != null)
                    {
                        cell = row1.createCell(j++);
                        cell.setCellValue(dataset.get(i).get("calibration_ten_pm").toString());
                    }
                    else
                    {
                        cell = row1.createCell(j++);
                        cell.setCellValue("---");
                    }
                }
                if(header.indexOf(",收集时间,")>=0){
                    if (dataset.get(i).get("col_time") != null)
                    {
                        cell = row1.createCell(j++);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String colTime = sdf.format(dataset.get(i).get("col_time"));
                        cell.setCellValue(colTime);
                    }
                    else
                    {
                        cell = row1.createCell(j++);
                        cell.setCellValue("---");
                    }
                }

            }
        }
        // FileOutputStream os;
        try
        {
            // os = new FileOutputStream("D:\\workbook+.xls");
            FileOutputStream outputStream = new FileOutputStream("e://"+fileName+".csv");
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
//            wb.write(out);
//            out.close();
        }
        catch(Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}