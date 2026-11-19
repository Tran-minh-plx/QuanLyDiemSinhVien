package main.java.com.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class FormQLSinhVien extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField txtMaSV, txtHoSV, txtTen, txtNs, txtPhai, txtMaKhoa, txtTim;

    public FormQLSinhVien() {
        setTitle("QUẢN LÝ SINH VIÊN");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblTieuDe = new JLabel("QUẢN LÝ THÔNG TIN SINH VIÊN", JLabel.CENTER);
        add(lblTieuDe, BorderLayout.NORTH);
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 24));
        lblTieuDe.setBackground(new Color(0, 128, 255));
        lblTieuDe.setForeground(Color.white);
        lblTieuDe.setOpaque(true);
        lblTieuDe.setPreferredSize(new Dimension(0, 60));
        
        JPanel centerPanel = new JPanel(null);
        add(centerPanel, BorderLayout.CENTER);
        centerPanel.setBackground(new Color(245, 248, 255));

        JLabel lblMaSV = new JLabel("Mã SV:");
        centerPanel.add(lblMaSV);
        lblMaSV.setBounds(30, 20, 60, 30);
        
        txtMaSV = new JTextField();
        centerPanel.add(txtMaSV);
        txtMaSV.setBounds(90, 20, 120, 30);

        JLabel lblHoSV = new JLabel("Họ SV:");
        centerPanel.add(lblHoSV);
        lblHoSV.setBounds(230, 20, 60, 30);

        txtHoSV = new JTextField();
        centerPanel.add(txtHoSV);
        txtHoSV.setBounds(280, 20, 150, 30);

        JLabel lblTen = new JLabel("Tên:");
        centerPanel.add(lblTen);
        lblTen.setBounds(450, 20, 50, 30);
        
        txtTen = new JTextField();
        centerPanel.add(txtTen);
        txtTen.setBounds(490, 20, 100, 30);

        JLabel lblNs = new JLabel("Ngày sinh:");
        centerPanel.add(lblNs);
        lblNs.setBounds(610, 20, 70, 30);

        txtNs = new JTextField();
        centerPanel.add(txtNs);
        txtNs.setBounds(680, 20, 110, 30);

        JLabel lblPhai = new JLabel("Phái:");
        centerPanel.add(lblPhai);
        lblPhai.setBounds(30, 65, 60, 30);

        txtPhai = new JTextField();
        centerPanel.add(txtPhai);
        txtPhai.setBounds(90, 65, 120, 30);

        JLabel lblMaKhoa = new JLabel("Mã Khoa:");
        centerPanel.add(lblMaKhoa);
        lblMaKhoa.setBounds(220, 65, 60, 30);

        txtMaKhoa = new JTextField();
        centerPanel.add(txtMaKhoa);
        txtMaKhoa.setBounds(280, 65, 150, 30);

        JLabel lblTim = new JLabel("Tìm Mã SV:");
        centerPanel.add(lblTim);
        lblTim.setBounds(450, 65, 80, 30);

        txtTim = new JTextField();
        centerPanel.add(txtTim);
        txtTim.setBounds(530, 65, 150, 30);

        JButton btnTim = new JButton("TÌM");
        centerPanel.add(btnTim);
        btnTim.setBounds(690, 65, 100, 30);
        btnTim.setBackground(new Color(0, 128, 255));
        btnTim.setForeground(Color.WHITE);

        tableModel = new DefaultTableModel(new String[]{"Mã SV", "Họ SV", "Tên", "Ngày Sinh", "Phái", "Mã Khoa"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 115, 760, 240);
        centerPanel.add(scrollPane);

        JButton btnAdd = new JButton("THÊM");
        btnAdd.setBounds(115, 385, 100, 40);
        btnAdd.setBackground(new Color(60, 179, 113));
        btnAdd.setForeground(Color.WHITE);
        centerPanel.add(btnAdd);

        JButton btnEdit = new JButton("SỬA");
        btnEdit.setBounds(240, 385, 100, 40);
        btnEdit.setBackground(new Color(255, 165, 0));
        btnEdit.setForeground(Color.WHITE);
        centerPanel.add(btnEdit);

        JButton btnDelete = new JButton("XÓA");
        btnDelete.setBounds(365, 385, 100, 40);
        btnDelete.setBackground(new Color(220, 20, 60));
        btnDelete.setForeground(Color.WHITE);
        centerPanel.add(btnDelete);

        JButton btnRefresh = new JButton("LÀM MỚI");
        btnRefresh.setBounds(490, 385, 100, 40);
        centerPanel.add(btnRefresh);

        JButton btnExit = new JButton("THOÁT");
        btnExit.setBounds(615, 385, 100, 40);
        centerPanel.add(btnExit);

        loadDataToTable("");

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    txtMaSV.setText(tableModel.getValueAt(row, 0).toString());
                    txtHoSV.setText(tableModel.getValueAt(row, 1).toString());
                    txtTen.setText(tableModel.getValueAt(row, 2).toString());
                    txtNs.setText(tableModel.getValueAt(row, 3).toString());
                    txtPhai.setText(tableModel.getValueAt(row, 4).toString());
                    txtMaKhoa.setText(tableModel.getValueAt(row, 5).toString());
                    txtMaSV.setEditable(false);
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
                String hoSV = txtHoSV.getText().trim();
                String ten = txtTen.getText().trim();
                String ns = txtNs.getText().trim();
                String phai = txtPhai.getText().trim();
                String maKhoa = txtMaKhoa.getText().trim();

                SinhVienDAO.themSinhVien(maSV, hoSV, ten, ns, phai, maKhoa);
                loadDataToTable("");
                clearForm();
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maSV = txtMaSV.getText().trim();
                String hoSV = txtHoSV.getText().trim();
                String ten = txtTen.getText().trim();
                String ns = txtNs.getText().trim();
                String phai = txtPhai.getText().trim();
                String maKhoa = txtMaKhoa.getText().trim();

                SinhVienDAO.suaSinhVien(maSV, hoSV, ten, ns, phai, maKhoa);
                loadDataToTable("");
                txtMaSV.setEditable(true);
                clearForm();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maSV = txtMaSV.getText().trim();

                SinhVienDAO.xoaSinhVien(maSV);
                loadDataToTable("");
                txtMaSV.setEditable(true);
                clearForm();
            }
        });

        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMaSV.setEditable(true);
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

        String sql = timMaSV.isEmpty() ? "SELECT * FROM SINHVIEN" : "SELECT * FROM SINHVIEN WHERE MASV LIKE ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (!timMaSV.isEmpty()) {
                pstmt.setString(1, "%" + timMaSV + "%");
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    tableModel.addRow(new Object[]{
                        rs.getString("MASV"),
                        rs.getString("HOSV"),
                        rs.getString("TEN"),
                        rs.getString("NS"),
                        rs.getString("PHAI"),
                        rs.getString("MAKHOA")
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
        txtHoSV.setText("");
        txtTen.setText("");
        txtNs.setText("");
        txtPhai.setText("");
        txtMaKhoa.setText("");
        txtTim.setText("");
    }

    public static void main(String[] args) {
        new FormQLSinhVien();
    }
}