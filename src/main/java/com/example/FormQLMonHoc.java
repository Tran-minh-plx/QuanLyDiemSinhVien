package main.java.com.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class FormQLMonHoc extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField txtMaMH, txtTenMH, txtSoTiet, txtTim;

    public FormQLMonHoc() {
        setTitle("QUẢN LÝ MÔN HỌC");
        setSize(800, 580);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblTieuDe = new JLabel("QUẢN LÝ THÔNG TIN MÔN HỌC", JLabel.CENTER);
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 24));
        lblTieuDe.setBackground(new Color(0, 128, 255));
        lblTieuDe.setForeground(Color.white);
        lblTieuDe.setOpaque(true);
        lblTieuDe.setPreferredSize(new Dimension(0, 60));
        add(lblTieuDe, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(null);
        centerPanel.setBackground(new Color(245, 248, 255));
        add(centerPanel, BorderLayout.CENTER);

        JLabel lblMaMH = new JLabel("Mã môn học:");
        lblMaMH.setBounds(30, 25, 100, 30);
        centerPanel.add(lblMaMH);
        txtMaMH = new JTextField();
        txtMaMH.setBounds(130, 25, 150, 30);
        centerPanel.add(txtMaMH);

        JLabel lblTenMH = new JLabel("Tên môn học:");
        lblTenMH.setBounds(300, 25, 95, 30);
        centerPanel.add(lblTenMH);
        txtTenMH = new JTextField();
        txtTenMH.setBounds(400, 25, 180, 30);
        centerPanel.add(txtTenMH);

        JLabel lblSoTiet = new JLabel("Số tiết:");
        lblSoTiet.setBounds(600, 25, 50, 30);
        centerPanel.add(lblSoTiet);
        txtSoTiet = new JTextField();
        txtSoTiet.setBounds(650, 25, 100, 30);
        centerPanel.add(txtSoTiet);

        JLabel lblTim = new JLabel("Tìm Mã MH:");
        lblTim.setBounds(30, 75, 100, 30);
        centerPanel.add(lblTim);
        txtTim = new JTextField();
        txtTim.setBounds(130, 75, 180, 30);
        centerPanel.add(txtTim);

        JButton btnTim = new JButton("TÌM KIẾM");
        btnTim.setBounds(330, 75, 110, 30);
        btnTim.setBackground(new Color(0, 128, 255));
        btnTim.setForeground(Color.WHITE);
        centerPanel.add(btnTim);

        tableModel = new DefaultTableModel(new String[]{"Mã Môn Học", "Tên Môn Học", "Số Tiết"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 125, 720, 215);
        centerPanel.add(scrollPane);

        JButton btnAdd = new JButton("THÊM");
        btnAdd.setBounds(90, 375, 100, 40);
        btnAdd.setBackground(new Color(60, 179, 113));
        btnAdd.setForeground(Color.WHITE);
        centerPanel.add(btnAdd);

        JButton btnEdit = new JButton("SỬA");
        btnEdit.setBounds(210, 375, 100, 40);
        btnEdit.setBackground(new Color(255, 165, 0));
        btnEdit.setForeground(Color.WHITE);
        centerPanel.add(btnEdit);

        JButton btnDelete = new JButton("XÓA");
        btnDelete.setBounds(330, 375, 100, 40);
        btnDelete.setBackground(new Color(220, 20, 60));
        btnDelete.setForeground(Color.WHITE);
        centerPanel.add(btnDelete);

        JButton btnRefresh = new JButton("LÀM MỚI");
        btnRefresh.setBounds(450, 375, 100, 40);
        centerPanel.add(btnRefresh);

        JButton btnExit = new JButton("THOÁT");
        btnExit.setBounds(570, 375, 100, 40);
        centerPanel.add(btnExit);

        loadDataToTable("");

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    txtMaMH.setText(tableModel.getValueAt(row, 0).toString());
                    txtTenMH.setText(tableModel.getValueAt(row, 1).toString());
                    txtSoTiet.setText(tableModel.getValueAt(row, 2).toString());
                    txtMaMH.setEditable(false);
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
                String maMH = txtMaMH.getText().trim();
                String tenMH = txtTenMH.getText().trim();
                String soTiet = txtSoTiet.getText().trim();

                MonHocDAO.themMonHoc(maMH, tenMH, soTiet);
                loadDataToTable("");
                clearForm();
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maMH = txtMaMH.getText().trim();
                String tenMH = txtTenMH.getText().trim();
                String soTiet = txtSoTiet.getText().trim();

                MonHocDAO.suaMonHoc(maMH, tenMH, soTiet);
                loadDataToTable("");
                txtMaMH.setEditable(true);
                clearForm();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maMH = txtMaMH.getText().trim();

                MonHocDAO.xoaMonHoc(maMH);
                loadDataToTable("");
                txtMaMH.setEditable(true);
                clearForm();
            }
        });

        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMaMH.setEditable(true);
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

    private void loadDataToTable(String timMaMH) {
        tableModel.setRowCount(0);
        Connection conn = QLDiemDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(this, "Không thể kết nối đến cơ sở dữ liệu SQL Server!", "Lỗi Kết Nối", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = timMaMH.isEmpty() ? "SELECT * FROM MONHOC" : "SELECT * FROM MONHOC WHERE MAMH LIKE ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (!timMaMH.isEmpty()) {
                pstmt.setString(1, "%" + timMaMH + "%");
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    tableModel.addRow(new Object[]{
                        rs.getString("MAMH"),
                        rs.getString("TENMH"),
                        rs.getBigDecimal("SOTIET")
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
        txtMaMH.setText("");
        txtTenMH.setText("");
        txtSoTiet.setText("");
        txtTim.setText("");
    }

    public static void main(String[] args) {
        new FormQLMonHoc();
    }
}