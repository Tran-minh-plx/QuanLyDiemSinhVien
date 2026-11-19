package main.java.com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.math.BigDecimal;

public class MonHocDAO {

    // Thêm môn học
    public static void themMonHoc(String maMH, String tenMH, String soTiet) {
        String sql = "INSERT INTO MONHOC (MAMH, TENMH, SOTIET) VALUES (?, ?, ?)";
        try (Connection conn = QLDiemDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maMH);
            pstmt.setString(2, tenMH);
            pstmt.setBigDecimal(3, new BigDecimal(soTiet));
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Sửa môn học
    public static void suaMonHoc(String maMH, String tenMH, String soTiet) {
        String sql = "UPDATE MONHOC SET TENMH = ?, SOTIET = ? WHERE MAMH = ?";
        try (Connection conn = QLDiemDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tenMH);
            pstmt.setBigDecimal(2, new BigDecimal(soTiet));
            pstmt.setString(3, maMH);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xóa môn học
    public static void xoaMonHoc(String maMH) {
        String sql = "DELETE FROM MONHOC WHERE MAMH = ?";
        try (Connection conn = QLDiemDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maMH);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}