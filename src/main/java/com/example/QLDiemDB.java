package main.java.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QLDiemDB{
    
    public static Connection getConnection(){
        Connection conn = null;
        try{
            String src = "jdbc:sqlserver://localhost:1433;databaseName=QLDIEM;encrypt=true;trustServerCertificate=true;";
            String user = "sa";
            String password = "123456";

            conn = DriverManager.getConnection(src, user, password);
        }
        catch (SQLException e){
            System.out.println("Lỗi kết nối đến CSDL");
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn){
        try{
            if (conn != null && !conn.isClosed()){
                conn.close();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}