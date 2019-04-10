package com.roll.wrench.inner.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.IOException;

/**
 * @author zongqiang.hao
 * created on 2019-04-04 16:36.
 */
public class ExcelUtil {

    public static void main(String[] args) {
        try {
            POIFSFileSystem fileSystem = new POIFSFileSystem(new File("/Users/roll/Desktop/test1.xls"));
            //得到Excel工作簿对象
            HSSFWorkbook wb = new HSSFWorkbook(fileSystem);
            HSSFSheet sheet1 = wb.getSheetAt(1);
            wb.cloneSheet(1);
            wb.cloneSheet(2);
            wb.createSheet("123");

            //得到Excel工作表对象
            HSSFSheet sheet = getSheet(new File("/Users/roll/Desktop/test2.xls"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HSSFSheet getSheet(File file) {
        POIFSFileSystem fileSystem = null;
        try {
            fileSystem = new POIFSFileSystem(file);
            //得到Excel工作簿对象
            HSSFWorkbook wb = new HSSFWorkbook(fileSystem);
            //得到Excel工作表对象
            return wb.getSheetAt(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
