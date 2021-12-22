package com.freight.utils;

import org.apache.poi.hssf.usermodel.*;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @作者 yan
 * @创建日期
 * @版本 V1.0
 * @描述 Excel 导出通用工具类
 */
public class ExcelUtil {

    public static byte[] export(String sheetTitle, String[] title, List<String[]> list,String name) {

        HSSFWorkbook wb = new HSSFWorkbook();//创建excel表
        HSSFSheet sheet = wb.createSheet(sheetTitle);
        sheet.setDefaultColumnWidth(20);//设置默认行宽

        //表头样式（加粗，水平居中，垂直居中）
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
//        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        //设置边框样式
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        HSSFFont fontStyle = wb.createFont();
        fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        cellStyle.setFont(fontStyle);

        //标题样式（加粗，垂直居中）
        HSSFCellStyle cellStyle2 = wb.createCellStyle();

        cellStyle2.setFont(fontStyle);

        //设置边框样式
        cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        //字段样式（垂直居中）
        HSSFCellStyle cellStyle3 = wb.createCellStyle();


        //设置边框样式
        cellStyle3.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle3.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle3.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle3.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        //创建表头
//        HSSFRow row = sheet.createRow(0);
//        row.setHeightInPoints(20);//行高
//
//        HSSFCell cell = row.createCell(0);
//        cell.setCellValue(sheetTitle);
//        cell.setCellStyle(cellStyle);

//        sheet.addMergedRegion(new CellRangeAddress(0,0,0,(title.length-1)));

        //创建标题
        HSSFRow rowTitle = sheet.createRow(0);
        rowTitle.setHeightInPoints(20);

        HSSFCell hc;
        for (int i = 0; i < title.length; i++) {
            hc = rowTitle.createCell(i);
            hc.setCellValue(title[i]);
            hc.setCellStyle(cellStyle2);
        }

        byte result[] = null;

        ByteArrayOutputStream out = null;

        try {
            //创建表格数据
            Field[] fields;
            int i = 1;

            for (String[] obj : list) {
//                fields = obj.getClass().getDeclaredFields();

                HSSFRow rowBody = sheet.createRow(i);
                rowBody.setHeightInPoints(20);

                int j = 0;
                for(int k=0;k< obj.length;k++)
                {
                    hc = rowBody.createCell(j);
                    hc.setCellValue(obj[k]);
                    hc.setCellStyle(cellStyle3);
                    j++;
                }


                i++;
            }

            out = new ByteArrayOutputStream();
            FileOutputStream output=new FileOutputStream("d:\\"+name+".xls");
            wb.write(output);
            output.flush();
        } catch (Exception ex) {

        } finally{
            try {
                if(null != out){
                    out.close();
                }
            } catch (IOException ex) {

            } finally{
                try {
                    wb.close();
                } catch (IOException ex) {

                }
            }
        }

        return result;
    }
}