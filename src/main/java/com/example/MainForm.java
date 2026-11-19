package main.java.com.example;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame{
    public MainForm(){
        setTitle("HỆ THỐNG QUẢN LÝ ĐIỂM SINH VIÊN");
        setSize(450,420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblTieuDe = new JLabel("TRANG CHỦ QUẢN LÝ ĐIỂM", JLabel.CENTER);
        add(lblTieuDe, BorderLayout.NORTH);
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
        lblTieuDe.setBackground(new Color(0, 128, 255));
        lblTieuDe.setForeground(Color.white);
        lblTieuDe.setOpaque(true);
        lblTieuDe.setPreferredSize(new Dimension(0, 60));

        JPanel panelButtons = new JPanel(null);
        add(panelButtons,BorderLayout.CENTER);
        panelButtons.setBackground(new Color(245, 248, 255));

        JButton btnSinhVien = new JButton("QUẢN LÝ SINH VIÊN");
        panelButtons.add(btnSinhVien);
        btnSinhVien.setBounds(100, 25, 230, 45);
        btnSinhVien.setBackground(new Color(60, 179, 113));
        btnSinhVien.setForeground(Color.WHITE);
        btnSinhVien.setFont(new Font("Arial", Font.BOLD, 14));

        JButton btnMonHoc = new JButton("QUẢN LÝ MÔN HỌC");
        panelButtons.add(btnMonHoc);
        btnMonHoc.setBounds(100, 85, 230, 45);
        btnMonHoc.setBackground(new Color(0, 128, 255));
        btnMonHoc.setForeground(Color.WHITE);
        btnMonHoc.setFont(new Font("Arial", Font.BOLD, 14));

        JButton btnKetQua = new JButton("Quản lý Kết Quả Điểm");
        panelButtons.add(btnKetQua);
        btnKetQua.setBounds(100, 145, 230, 45);
        btnKetQua.setBackground(new Color(255, 140, 0));
        btnKetQua.setForeground(Color.WHITE);
        btnKetQua.setFont(new Font("Arial", Font.BOLD, 14));
        
        JButton btnThoat = new JButton("Thoát chương trình");
        panelButtons.add(btnThoat);
        btnThoat.setBounds(100, 205, 230, 45);
        btnThoat.setBackground(new Color(220, 20, 60));
        btnThoat.setForeground(Color.WHITE);
        btnThoat.setFont(new Font("Arial", Font.BOLD, 14));
        
        btnSinhVien.addActionListener(e -> new FormQLSinhVien());
        btnMonHoc.addActionListener(e -> new FormQLMonHoc());
        btnKetQua.addActionListener(e -> new FormQLKetQua());
        btnThoat.addActionListener(e -> System.exit(0));

        setVisible(true);
    }
    public static void main(String[] args){
        new MainForm();
    }
}