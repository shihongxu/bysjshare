package com.axu.share.util.ExcelUtil;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author Axu
 * @Description //TODO Excel导出工具类
 * @Date 10:04 2019/1/15
 * @Param
 * @return
 **/
public class ExcelUtil {

    /**
     * @Author Axu
     * @Description //TODO 生成Excel的工具方法，sheetDataMap 是表的数据；headerMap 是表的第一行；
     * @Date 21:44 2019/1/22
     * @Param [listDate, wb, listHeader]
     * @return void
     **/
    public static void fillExcelData(HSSFWorkbook workbook,
                                     Map<String,List<Map<String, String>>> sheetDataMap,
                                     Map<String,Map<String, String>> headerMap){

        //生成标题行表格样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor((short) 13);// 设置背景色
        headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        //生成标题行字体
        HSSFFont headerFont = workbook.createFont();
        headerFont.setFontName("黑体");
        headerFont.setFontHeightInPoints((short) 12);//设置字体大小
        headerStyle.setFont(headerFont);

        //生成单元格样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        //生成单元格字体样式
        HSSFFont cellFont = workbook.createFont();
        cellFont.setFontName("仿宋_GB2312");
        cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
//        cellFont.setFontHeightInPoints((short) 12);
        cellStyle.setFont(cellFont);

        //获取Excel 的表单名称集合
        Set<String> sheetSet = sheetDataMap.keySet();//通过map 的keySet()方法获取到sheetDataMap 的所有 键，及就是Excel的sheet页的名称

        //通过迭代器获取每个sheet名称,并且创建sheet页
        Iterator<String> sheetIt = sheetSet.iterator();
        while(sheetIt.hasNext()){//设置每个sheet的参数，hashNext()是判断是否遍历完，如果没有遍历完，则返回true；next()则是返回当前元素，并指向下一个元素
            String sheetName = sheetIt.next();//获取每个sheet名字

            HSSFSheet sheet = workbook.createSheet(sheetName);//创建一个sheet页

            sheet.setDefaultColumnWidth(20);

            int rowIndex = 0;//表示表格的行数

            //产生表格的第一行
            HSSFRow row = sheet.createRow(rowIndex ++);

            //设置标题行
            Map<String, String> headerNameMap = headerMap.get(sheetName);
            Set<String> headerNameSet = headerNameMap.keySet();
            Iterator<String> headerNameIt = headerNameSet.iterator();
            //得到每个列名的集合
            int headerIndex = 0; //用于表示每列的索引
            while(headerNameIt.hasNext()){
                String  header = headerNameIt.next();//得到每个列名
                HSSFCell cell = row.createCell(headerIndex ++);//创建单元格
                cell.setCellStyle(headerStyle);
                cell.setCellValue(header);//给单元格赋值
            }

            //根据每个sheet名获取每个sheet页的数据
            List<Map<String, String>> dataList = sheetDataMap.get(sheetName);
            //遍历list集合，获取每一列数据
            for(int i = 0; i <dataList.size(); i++){

                int cellIndex = 0;
                row = sheet.createRow(rowIndex ++);

                Iterator<String> headerItSize = headerNameSet.iterator();//用来 表示有多少列

                while(headerItSize.hasNext()){
                    String cellValue = dataList.get(i).get(headerMap.get(sheetName).get(headerItSize.next()));//获取每个单元格的值

                    //创建单元格
                    HSSFCell cell = row.createCell(cellIndex ++);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(cellValue);//给但单元格赋值
                }
            }

        }
    }

    /**
     * @Author Axu
     * @Description //TODO 导出Excel的方法
     * @Date 22:27 2019/1/22
     * @Param [response, wb, fileName]
     * @return void
     **/
    public static void ResponesExportExcel(HttpServletResponse response, Workbook wb, String fileName) throws Exception {

        response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("utf-8"),"iso8859-1"));
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");
        OutputStream out = response.getOutputStream();

        wb.write(out);
        out.flush();
        out.close();
    }

}
