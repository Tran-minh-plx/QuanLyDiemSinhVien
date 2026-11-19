package main.java.com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SinhVienDAO {

    // Thêm sinh viên
    public static void themSinhVien(String maSV, String hoSV, String ten, String ns, String phai, String maKhoa) {
        String sql = "INSERT INTO SINHVIEN (MASV, HOSV, TEN, NS, PHAI, MAKHOA) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = QLDiemDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maSV);
            pstmt.setString(2, hoSV);
            pstmt.setString(3, ten);
            pstmt.setString(4, ns);
            pstmt.setString(5, phai);
            pstmt.setString(6, maKhoa);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Sửa thông tin sinh viên
    public static void suaSinhVien(String maSV, String hoSV, String ten, String ns, String phai, String maKhoa) {
        String sql = "UPDATE SINHVIEN SET HOSV = ?, TEN = ?, NS = ?, PHAI = ?, MAKHOA = ? WHERE MASV = ?";
        try (Connection conn = QLDiemDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, hoSV);
            pstmt.setString(2, ten);
            pstmt.setString(3, ns);
            pstmt.setString(4, phai);
            pstmt.setString(5, maKhoa);
            pstmt.setString(6, maSV);
            
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xóa sinh viên
    public static void xoaSinhVien(String maSV) {
        String sql = "DELETE FROM SINHVIEN WHERE MASV = ?";
        try (Connection conn = QLDiemDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, maSV);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}