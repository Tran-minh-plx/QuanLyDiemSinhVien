package main.java.com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.math.BigDecimal;

public class KetQuaDAO {

    // Thêm kết quả thi
    public static void themKetQua(String maSV, String maMH, String lanThi, String diem) {
        String sql = "INSERT INTO KETQUA (MASV, MAMH, LANTHI, DIEM) VALUES (?, ?, ?, ?)";
        try (Connection conn = QLDiemDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maSV);
            pstmt.setString(2, maMH);
            pstmt.setInt(3, Integer.parseInt(lanThi));
            pstmt.setBigDecimal(4, new BigDecimal(diem));
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Sửa điểm kết quả thi
    public static void suaKetQua(String maSV, String maMH, String lanThi, String diem) {
        String sql = "UPDATE KETQUA SET DIEM = ? WHERE MASV = ? AND MAMH = ? AND LANTHI = ?";
        try (Connection conn = QLDiemDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBigDecimal(1, new BigDecimal(diem));
            pstmt.setString(2, maSV);
            pstmt.setString(3, maMH);
            pstmt.setInt(4, Integer.parseInt(lanThi));
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xóa kết quả thi
    public static void xoaKetQua(String maSV, String maMH, String lanThi) {
        String sql = "DELETE FROM KETQUA WHERE MASV = ? AND MAMH = ? AND LANTHI = ?";
        try (Connection conn = QLDiemDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maSV);
            pstmt.setString(2, maMH);
            pstmt.setInt(3, Integer.parseInt(lanThi));
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}