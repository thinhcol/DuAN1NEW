Create Database DuAn1
use DuAn1


Create table Phong
(
	MaPhong nvarchar(255) not null,
	SoPhong int not null,
	SoBN int not null,
	GhiChu nvarchar(255),
	Primary Key (MaPhong)
)

Create table NhanVien
(
	MaNV nvarchar(255) not null,
	MaPhong nvarchar(255) not null,
	HoTen nvarchar(255) not null,
	GioiTinh bit not null,
	Email nvarchar(255) not null,
	SDT nchar(13) not null,
	ChucVu bit not null,
	Primary Key (MaNV),
	Foreign Key (MaPhong) References Phong (MaPhong) on Delete no Action on Update Cascade,
)

Create table BenhNhan
(
	MaBN int identity(1,1) not null,
	MaPhong nvarchar(255) not null,
	HoTen nvarchar(255) not null,
	GioiTinh bit not null,
	NgayVT date not null,
	DiaChi nvarchar(255) not null,
	CMND nchar(12) not null,
	Email nvarchar(255) not null,
	Primary Key (MaBN),
	Foreign Key (MaPhong) References Phong (MaPhong) on Delete no Action on Update Cascade,
)

Create table HoaDon
(
	MaHD int identity(1,1) not null,
	MaBN int not null,
	MaPhong nvarchar(255) not null,
	ThoiGian int not null,
	TongTien Float,
	Primary Key (MaHD),
	Foreign Key (MaBN) References BenhNhan (MaBN) on Delete no Action on Update Cascade ,
	Foreign Key (MaPhong) References Phong (MaPhong) on Delete cascade
)


Create table BenhAn
(
	MaBA int identity(1,1) not null,
	MaBN int not null,
	BieuHien nvarchar(255) not null,
	CachDT nvarchar(255) not null,
	GhiChu nvarchar(255),
	Primary Key (MaBA),
	Foreign Key (MaBN) References BenhNhan (MaBN) on Delete no Action on Update Cascade,
)

Create table CongViec
(
	MaCV int identity(1,1) not null,
	TenCV nvarchar(255) not null,
	SoLuong int not null,
	GhiChu nvarchar(255),
	Primary Key (MaCV)
)

Create table PhanCong
(
	MaPC int identity(1,1) not null,
	MaCV int not null,
	MaBN int not null,
	MoTa nvarchar(255) not null,
	GhiChu nvarchar(255),
	Primary Key (MaPC),
	Foreign Key (MaCV) References CongViec (MaCV) on Delete Cascade on Update Cascade,
	Foreign Key (MaBN) References BenhNhan (MaBN) on Delete no Action on Update Cascade,
)

CREATE PROC sp_ThongKeBenhNhan
AS BEGIN
	SELECT
		YEAR(NgayVT) Nam,
		COUNT(*) SoLuong,
		MIN(NgayVT) DauTien,
		MAX(NgayVT) CuoiCung
	FROM BenhNhan
	GROUP BY YEAR(NgayVT)
END

CREATE PROC sp_ThongKeDoanhThu(@Year INT)
AS BEGIN
	SELECT
		HoTen BenhNhan,
		COUNT(bn.MaBN) SoBN,
		SUM(hd.TongTien) DoanhThu,
		MIN(hd.TongTien) ThapNhat,
		MAX(hd.TongTien) CaoNhat,
		AVG(hd.TongTien) TrungBinh
	FROM HoaDon hd
		JOIN BenhNhan bn ON bn.MaBN= hd.MaBN
	WHERE YEAR(NgayVT) = @Year
	GROUP BY HoTen
END