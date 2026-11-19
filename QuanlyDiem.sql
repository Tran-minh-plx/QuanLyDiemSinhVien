CREATE DATABASE QLDIEM;
GO

USE QLDIEM;
GO

-- BẢNG KHOA

CREATE TABLE KHOA
(
    MAKHOA  CHAR(10) PRIMARY KEY,
    TENKHOA NVARCHAR(50)
);
GO


-- BẢNG SINHVIEN

CREATE TABLE SINHVIEN
(
    MASV    CHAR(10) PRIMARY KEY,
    HOSV    NVARCHAR(50),
    TEN     NVARCHAR(50),
    NS      DATETIME,
    PHAI    NCHAR(3),
    MAKHOA  CHAR(10),

    CONSTRAINT FK_SINHVIEN_KHOA
        FOREIGN KEY (MAKHOA)
        REFERENCES KHOA(MAKHOA)
);
GO

-- BẢNG MONHOC

CREATE TABLE MONHOC
(
    MAMH    CHAR(10) PRIMARY KEY,
    TENMH   NVARCHAR(50),
    SOTIET  NUMERIC(18,0)
);
GO

-- BẢNG KETQUA
CREATE TABLE KETQUA
(
    MASV    CHAR(10),
    MAMH    CHAR(10),
    LANTHI  INT,
    DIEM    DECIMAL(4,2),

    CONSTRAINT PK_KETQUA
        PRIMARY KEY (MASV, MAMH, LANTHI),

    CONSTRAINT FK_KETQUA_SINHVIEN
        FOREIGN KEY (MASV)
        REFERENCES SINHVIEN(MASV),

    CONSTRAINT FK_KETQUA_MONHOC
        FOREIGN KEY (MAMH)
        REFERENCES MONHOC(MAMH)
);
GO

-- DỮ LIỆU BẢNG KHOA

INSERT INTO KHOA (MAKHOA, TENKHOA) VALUES
('CNTT',  N'Công nghệ thông tin'),
('QTKD',  N'Quản trị kinh doanh'),
('KT',    N'Kế toán'),
('NNA',   N'Ngôn ngữ Anh'),
('DTVT',  N'Điện tử viễn thông');
GO

-- DỮ LIỆU BẢNG SINHVIEN

INSERT INTO SINHVIEN (MASV, HOSV, TEN, NS, PHAI, MAKHOA) VALUES
('SV001', N'Nguyễn Văn',  N'An',    '2003-01-15', N'Nam',  'CNTT'),
('SV002', N'Trần Thị',    N'Bình',  '2003-05-20', N'Nữ',   'CNTT'),
('SV003', N'Lê Văn',      N'Cường', '2002-08-10', N'Nam',  'QTKD'),
('SV004', N'Phạm Thị',    N'Dung',  '2003-11-25', N'Nữ',   'KT'),
('SV005', N'Hoàng Văn',   N'Em',    '2002-03-30', N'Nam',  'NNA'),
('SV006', N'Võ Thị',      N'Hoa',   '2003-07-12', N'Nữ',   'DTVT'),
('SV007', N'Đặng Văn',    N'Nam',   '2003-09-18', N'Nam',  'CNTT'),
('SV008', N'Nguyễn Văn', N'Huy', '1984-12-21', N'Nam', 'CNTT');
GO

-- DỮ LIỆU BẢNG MONHOC

INSERT INTO MONHOC (MAMH, TENMH, SOTIET) VALUES
('MH001', N'Cơ sở dữ liệu',       45),
('MH002', N'Lập trình C',         60),
('MH003', N'Cấu trúc dữ liệu',    45),
('MH004', N'Mạng máy tính',       45),
('MH005', N'Hệ điều hành',        45),
('MH006', N'Tin học đại cương',   30);
GO

-- DỮ LIỆU BẢNG KETQUA

INSERT INTO KETQUA (MASV, MAMH, LANTHI, DIEM) VALUES
('SV001', 'MH001', 1, 8.50),
('SV001', 'MH002', 1, 7.00),
('SV002', 'MH001', 1, 9.00),
('SV002', 'MH003', 1, 8.00),
('SV003', 'MH001', 1, 6.50),
('SV003', 'MH004', 1, 7.50),
('SV004', 'MH002', 1, 5.50),
('SV004', 'MH005', 1, 8.00),
('SV005', 'MH001', 1, 7.00),
('SV005', 'MH006', 1, 9.00),
('SV006', 'MH003', 1, 6.00),
('SV006', 'MH004', 1, 7.50),
('SV007', 'MH001', 1, 4.50),
('SV007', 'MH001', 2, 6.50),
('SV008', 'MH001', 1, 10.00),
('SV008', 'MH005', 2, 9.50);
GO

-- KIỂM TRA DỮ LIỆU

SELECT * FROM KHOA;
SELECT * FROM SINHVIEN;
SELECT * FROM MONHOC;
SELECT * FROM KETQUA;
GO