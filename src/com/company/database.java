package com.company;


import java.sql.*;
import java.util.*;

public class database {
    public static void main(String[] args) throws Exception {

        Connection conn = null;
        Scanner sc = new Scanner(System.in);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance", "root",
                    "");
                if(conn!=null) {
                    System.out.println("Successfully Connected to Database...");
                }
            }
        catch (Exception e) {
            e.printStackTrace();
        }
    Statement st = conn.createStatement();
        PreparedStatement pt = null;
        ResultSet rs = null;
        String sql = null;

        while(true){
            System.out.println("---------------------*****----------------------");
            System.out.println("1. Display Data\n2. Display based on Present or Absent\n3. Generate The Report\n4. List out the detail by name\n5. Enter 5 To Get Exit From The Portal");
            System.out.println("\nPlease Enter Your Choice");
            int n = sc.nextInt();

            switch(n){
                case 1:
                    sql = "select * from student";
                    try{
                        rs = st.executeQuery(sql);
                        while(rs.next()){
                            System.out.println(rs.getString(5)+" -> "+rs.getString(2)+" -> "+rs.getString(3)+" -> "+rs.getString(4));
                        }
                    }catch(Exception e){
                        System.err.println("Couldn't Load The Database");
                }
                    break;

                case 2:
                    System.out.println("Enter A for Absent and P for Present");
                    String s = sc.next();
                    String sq = "select * from student where status = '"+s+"'";
                    try{
                        rs = st.executeQuery(sq);
                        int count=0;
                        while(rs.next()){
                            System.out.println(rs.getString(5)+" -> "+rs.getString(2)+" -> "+rs.getString(3)+" -> "+rs.getString(4));
                            count++;
                        }
                        int m=70;
                        int x = m-count;
                        if(s.equals("A")){
                            System.out.println("Total number of Students Absent = "+count+"\nAnd Total Number of Students Present = "+x);
                        }else{
                            System.out.println("Total number of Students Present = "+count+"\nAnd Total Number of Students Absent = "+x);
                        }
                    }catch(Exception e){
                        System.err.println("Couldn't Load The Database");
                    }
                        break;
                case 3:
                    System.out.println("Generating the report\n*******************************");
                    sql = "select COUNT(*) from student";
                    try{
                        rs = st.executeQuery(sql);
                        while(rs.next()){
                            System.out.println("Total no. of students = "+rs.getString(1));
                        }
                        sql = "SELECT COUNT(*) as id, status from student GROUP BY status";
                        rs = st.executeQuery(sql);
                        while(rs.next()){
                            System.out.println(rs.getString(2)+" = "+rs.getString(1));
                        }
                    }catch(Exception e){
                        System.err.println("Couldn't Load The Database");
                    }
                    break;
                case 4:
                    System.out.println("Search Operation By Student's name -> Please Enter a Name");
                    sc.nextLine();
                    String nm = sc.nextLine();
                    sql = "select * from student where name like '% "+ nm +"%'";
                    try{
                        rs = st.executeQuery(sql);
                        while(rs.next()){
                            System.out.println(rs.getString(5)+" -> "+rs.getString(2)+" -> "+rs.getString(3)+" -> "+rs.getString(4));
                        }
                    }catch(Exception e){
                        System.err.println("Couldn't Load The Database");
                    }
                    break;
                case 5:
                    System.out.println("Exit...");
                    System.exit(0);
                default:
                    System.out.println("Sorry, You have Entered A wrong Choice...");
                    break;
            }
        }
    }
}
