package main.java.com.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class FormQLKetQua extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField txtMaSV, txtMaMH, txtLanThi, txtDiem, txtTim;

    public FormQLKetQua() {
        setTitle("QUẢN LÝ KẾT QUẢ THI");
        setSize(820, 580);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblTieuDe = new JLabel("QUẢN LÝ KẾT QUẢ ĐIỂM THI", JLabel.CENTER);
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 24));
        lblTieuDe.setBackground(new Color(0, 128, 255));
        lblTieuDe.setForeground(Color.white);
        lblTieuDe.setOpaque(true);
        lblTieuDe.setPreferredSize(new Dimension(0, 60));
        add(lblTieuDe, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(null);
        centerPanel.setBackground(new Color(245, 248, 255));
        add(centerPanel, BorderLayout.CENTER);

        JLabel lblMaSV = new JLabel("Mã SV:");
        lblMaSV.setBounds(30, 20, 60, 30);
        centerPanel.add(lblMaSV);
        txtMaSV = new JTextField();
        txtMaSV.setBounds(85, 20, 110, 30);
        centerPanel.add(txtMaSV);

        JLabel lblMaMH = new JLabel("Mã MH:");
        lblMaMH.setBounds(215, 20, 60, 30);
        centerPanel.add(lblMaMH);
        txtMaMH = new JTextField();
        txtMaMH.setBounds(275, 20, 110, 30);
        centerPanel.add(txtMaMH);

        JLabel lblLanThi = new JLabel("Lần thi:");
        lblLanThi.setBounds(410, 20, 60, 30);
        centerPanel.add(lblLanThi);
        txtLanThi = new JTextField();
        txtLanThi.setBounds(470, 20, 70, 30);
        centerPanel.add(txtLanThi);

        JLabel lblDiem = new JLabel("Điểm:");
        lblDiem.setBounds(575, 20, 50, 30);
        centerPanel.add(lblDiem);
        txtDiem = new JTextField();
        txtDiem.setBounds(630, 20, 110, 30);
        centerPanel.add(txtDiem);

        JLabel lblTim = new JLabel("Tìm Mã SV:");
        lblTim.setBounds(30, 70, 80, 30);
        centerPanel.add(lblTim);
        txtTim = new JTextField();
        txtTim.setBounds(110, 70, 150, 30);
        centerPanel.add(txtTim);

        JButton btnTim = new JButton("TÌM KIẾM");
        btnTim.setBounds(275, 70, 110, 30);
        btnTim.setBackground(new Color(0, 128, 255));
        btnTim.setForeground(Color.WHITE);
        centerPanel.add(btnTim);

        tableModel = new DefaultTableModel(new String[]{"Mã Sinh Viên", "Mã Môn Học", "Lần Thi", "Điểm"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 125, 740, 215);
        centerPanel.add(scrollPane);

        JButton btnAdd = new JButton("THÊM");
        btnAdd.setBounds(105, 375, 100, 40);
        btnAdd.setBackground(new Color(60, 179, 113));
        btnAdd.setForeground(Color.WHITE);
        centerPanel.add(btnAdd);

        JButton btnEdit = new JButton("SỬA");
        btnEdit.setBounds(225, 375, 100, 40);
        btnEdit.setBackground(new Color(255, 165, 0));
        btnEdit.setForeground(Color.WHITE);
        centerPanel.add(btnEdit);

        JButton btnDelete = new JButton("XÓA");
        btnDelete.setBounds(345, 375, 100, 40);
        btnDelete.setBackground(new Color(220, 20, 60));
        btnDelete.setForeground(Color.WHITE);
        centerPanel.add(btnDelete);

        JButton btnRefresh = new JButton("LÀM MỚI");
        btnRefresh.setBounds(465, 375, 100, 40);
        centerPanel.add(btnRefresh);

        JButton btnExit = new JButton("THOÁT");
        btnExit.setBounds(585, 375, 100, 40);
        centerPanel.add(btnExit);

        loadDataToTable("");

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    txtMaSV.setText(tableModel.getValueAt(row, 0).toString());
                    txtMaMH.setText(tableModel.getValueAt(row, 1).toString());
                    txtLanThi.setText(tableModel.getValueAt(row, 2).toString());
                    txtDiem.setText(tableModel.getValueAt(row, 3).toString());
                    txtMaSV.setEditable(false);
                    txtMaMH.setEditable(false);
                    txtLanThi.setEditable(false);
                }
            }
        });

        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tim = txtTim.getText().trim();
                loadDataToTable(tim);
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maSV = txtMaSV.getText().trim();
                String maMH = txtMaMH.getText().trim();
                String lanThi = txtLanThi.getText().trim();
                String diem = txtDiem.getText().trim();

                KetQuaDAO.themKetQua(maSV, maMH, lanThi, diem);
                loadDataToTable("");
                clearForm();
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maSV = txtMaSV.getText().trim();
                String maMH = txtMaMH.getText().trim();
                String lanThi = txtLanThi.getText().trim();
                String diem = txtDiem.getText().trim();

                KetQuaDAO.suaKetQua(maSV, maMH, lanThi, diem);
                loadDataToTable("");
                unlockKeys();
                clearForm();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maSV = txtMaSV.getText().trim();
                String maMH = txtMaMH.getText().trim();
                String lanThi = txtLanThi.getText().trim();

                KetQuaDAO.xoaKetQua(maSV, maMH, lanThi);
                loadDataToTable("");
                unlockKeys();
                clearForm();
            }
        });

        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unlockKeys();
                clearForm();
                loadDataToTable("");
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    private void loadDataToTable(String timMaSV) {
        tableModel.setRowCount(0);
        Connection conn = QLDiemDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(this, "Không thể kết nối đến cơ sở dữ liệu SQL Server!", "Lỗi Kết Nối", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = timMaSV.isEmpty() ? "SELECT * FROM KETQUA" : "SELECT * FROM KETQUA WHERE MASV LIKE ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (!timMaSV.isEmpty()) {
                pstmt.setString(1, "%" + timMaSV + "%");
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    tableModel.addRow(new Object[]{
                        rs.getString("MASV"),
                        rs.getString("MAMH"),
                        rs.getInt("LANTHI"),
                        rs.getBigDecimal("DIEM")
                    });
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            QLDiemDB.closeConnection(conn);
        }
    }

    private void clearForm() {
        txtMaSV.setText("");
        txtMaMH.setText("");
        txtLanThi.setText("");
        txtDiem.setText("");
        txtTim.setText("");
    }

    private void unlockKeys() {
        txtMaSV.setEditable(true);
        txtMaMH.setEditable(true);
        txtLanThi.setEditable(true);
    }

    public static void main(String[] args) {
        new FormQLKetQua();
    }
}