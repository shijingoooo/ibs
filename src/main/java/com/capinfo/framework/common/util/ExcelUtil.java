package com.capinfo.framework.common.util;

import com.capinfo.framework.common.Constants;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ZHAOFJ on 2016/10/28.
 */
public class ExcelUtil {

    /**
     * @Title: 文件下载
     * @Description:文件下载
     * @param path,fileName
     * */
    public void downloadFile(String path, String fileName, HttpServletResponse response) throws IOException
    {
        File file = new File(path);
        InputStream fis = new BufferedInputStream(new FileInputStream(path));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
        response.addHeader("Content-Length", "" + file.length());
        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("text/html;charset=UTF-8;");
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
    }

    /**
     * @Title: 文件生成
     * @Description:文件下载
     * @param path,fileName
     * */
    public void createExcel(String path, String name, String[] header, String[] subHeader, List<Object[]> dataList) throws IOException
    {
        HSSFWorkbook bookWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = bookWorkbook.createSheet(name);
        HSSFCell cell;
        HSSFRow row;

        int titleRow = 0;
        row = sheet.createRow(titleRow++);
        for (int i = 0; i < header.length; i++) {
            cell = row.createCell(i+1);
            cell.setCellValue(header[i]);
        }

        row = sheet.createRow(titleRow++);
        for (int i = 0; i < subHeader.length; i++) {
            cell = row.createCell(i+1);
            cell.setCellValue(subHeader[i]);
        }

        for (Object[] objArr: dataList){
            row = sheet.createRow(titleRow++);
            for (int index = 0; index < objArr.length; index++){
                cell = row.createCell(index+1);
                cell.setCellValue(StringUtil.objToStr(objArr[index]));
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(path);
            bookWorkbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            bookWorkbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Title: 文件下载
     * @Description:文件下载
     * @param
     * */
    public void downLoadModel(String[] header, String[] subHeader, Object[] dataArr, String sheetName, List<Map<String, Object>> dataList, HttpServletResponse response) throws IOException
    {
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

        String fullPath =  ContextLoader.getCurrentWebApplicationContext()
                .getServletContext().getRealPath(Constants.filePath);
        System.out.println(fullPath);
        String fileName = DateUtil.getSystemTime("yyyyMMddHHmmssSSS") + ".xls";

        File file = new File(fullPath);
        file.mkdirs();

        createExcel(fullPath + fileName , sheetName, header, subHeader, dataRList);

        downloadFile(fullPath + fileName, sheetName + "_" + fileName, response);
    }

    /**
     * @Title: 文件导入
     * @Description:文件导入
     * @param
     * */
    public List<String[]> importExcel(String sheetName, String filePath) throws IOException
    {
        // 文件读取
        InputStream inStream = new FileInputStream(filePath);
        Workbook readWB = new HSSFWorkbook(inStream);
        Sheet sheet = readWB.getSheet(sheetName);
        if(sheet==null)
            return null;
        // 获取当前sheet总行数
        int rows = sheet.getLastRowNum();
        List<String[]> dataList = new ArrayList<String[]>();
        String[] empty = null;
        String[] header = null;
        // 数据读取 遍历行
        for (int row = 1; row <= rows; row++)
        {
            String[] dataArr = null;
            if (row == 1)
            {
                try
                {
                    header = new String[sheet.getRow(row)
                            .getLastCellNum()];
                    empty = header;
                }
                catch(Exception e)
                {
                    continue;
                }
            }else {
                dataArr = empty.clone();
            }

            boolean isNew = true;
            // 遍历列
            for (int column = 0; column < header.length; column++)
            {
                // 获取cell值
                String value = "";
                try
                {
                    Cell cell = sheet.getRow(row).getCell(column);
                    DecimalFormat df = new DecimalFormat("#");
                    switch (cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            if(HSSFDateUtil.isCellDateFormatted(cell)){
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                value =  sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                                break;
                            }
                            value =  df.format(cell.getNumericCellValue());
                            break;
                        case HSSFCell.CELL_TYPE_STRING:
                            value = cell.getStringCellValue();
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA:
                            value =  cell.getCellFormula();
                            break;
                        case HSSFCell.CELL_TYPE_BLANK:
                            value =  "";
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            value = cell.getBooleanCellValue() + "";
                            break;
                        case HSSFCell.CELL_TYPE_ERROR:
                            value = cell.getErrorCellValue() + "";
                            break;
                    }
                    // 获取cell值
                }
                catch(Exception e)
                {
                }
                if (column !=0 ){
                    if (row == 1)
                        header[column] = value.toString();
                    else
                        dataArr[column] = value.toString();
                } else {
                    if (row != 1) {
                        if("0".equals(value.toString())) {
                            isNew = false;
                            break;
                        } else {
                            header[column] = value.toString();
                        }
                    }
                }
            }
            if (row != 1 && isNew)
                dataList.add(dataArr);
        }
        dataList.add(0,header);
        return dataList;
    }
}
