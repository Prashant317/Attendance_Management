


package com.company;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;
import java.sql.PreparedStatement;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;


public class Main {
    public static void main(String[] args) throws Exception {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance", "root",
                    "");
            con.setAutoCommit(false);
            PreparedStatement pstm = null;
            FileInputStream input = new FileInputStream("C:/Users/Prashant/Desktop/Attendance.xlsx");
            //POIFSFileSystem fs = new POIFSFileSystem(input);
            XSSFWorkbook wb = new XSSFWorkbook(input);

            XSSFSheet sheet = wb.getSheetAt(0);
            Row row;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                row = sheet.getRow(i);
                double ids = row.getCell(0).getNumericCellValue();
                String n = row.getCell(1).getStringCellValue();
                String A = row.getCell(2).getStringCellValue();
                String B = row.getCell(3).getStringCellValue();


                //String sql = "INSERT INTO student " + "VALUES (id, 'name', 'reg_num', 'status')";
                String sql = "INSERT INTO student (name,reg_num,status,ids) VALUES('" + n + "','" + A + "','" + B + "'," + ids + ")";
                pstm = (PreparedStatement) con.prepareStatement(sql);
                pstm.execute();
                System.out.println("Import rows " + i);
            }
            con.commit();
            pstm.close();
            con.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Hey well done... your excel has successfully updated in the Database..");

    }
}