Drop Database DuAn1
Create Database DuAn1
use DuAn1

Create table NhanVien
(
	MaNV nvarchar(255) not null,
	HoTen nvarchar(255) not null,
	MatKhau nvarchar(255) not null,
	GioiTinh bit not null,
	Email nvarchar(255) not null,
	Hinh nvarchar(255) not null,
	SDT nchar(10) not null,
	ChucVu bit not null,
	MaNhanDien int identity(1,1) not null,
	Primary Key (MaNV)
)

INSERT INTO NhanVien VALUES
(N'ThienVD', N'Vũ Đức Thiện',123, 1, 'thienvd123@gmail.com',N'NV1.png', '0924028297', 1),
(N'ThinhDP', N'Đặng Phú Thịnh',123, 1, 'thinhdp123@gmail.com',N'NV2.png', '0812519836', 0),
(N'NghiaPL', N'Phạm Lễ Nghĩa',123, 0, 'hungnhkps18575@fpt.edu.vn',N'NV3.png', '0983632853',0)

-- Câu lệnh xóa nhân viên
-- update HoaDon set MaNV = null where MaNV = 'ThienVD' delete from nhanvien where maNV = 'ThienVD'


Create table Phong
(
	MaPhong nvarchar(255) not null,
	MaNV nvarchar(255),
	Gia float not null,
	Hinh nvarchar(255) not null,
	Primary Key (MaPhong),
	Foreign Key (MaNV) References NhanVien (MaNV) on Delete Set null on Update Cascade
)

INSERT INTO Phong VALUES
(N'R101',N'ThienVD',500000 ,'Phong1.png'),
(N'R102',N'ThienVD',300000 ,'Phong2.png'),
(N'R103',N'ThienVD',250000 ,'Phong3.png'),
(N'R104',N'ThienVD',400000 ,'Phong4.png'),
(N'R105',N'ThinhDP',400000 ,'Phong5.png'),
(N'R201',N'ThinhDP',400000 ,'Phong6.png'),
(N'R202',N'ThinhDP',550000 ,'Phong7.png'),
(N'R203',N'NghiaPL',300000 ,'Phong8.png'),
(N'R204',N'NghiaPL',345000 ,'Phong9.png'),
(N'R205',N'NghiaPL',600000 ,'Phong10.png')

Create table HoatDong
(
	MaHD int identity(1,1) not null,
	TenHT nvarchar(255) not null,
	GhiChu nvarchar(255),
	Primary Key (MaHD)
)

INSERT INTO HoatDong VALUES
(N'Tưới rau', N'Tưới vườn rau ở gần sân sau'),
(N'Quét sân', N'Quét sân từ khu C đến khu B'),
(N'Đổ rác', N'Đổ rác từ khu vực hành lang khu A đến hết khu B'),
(N'Lau dọn nhà vệ sinh', N'Lao dọn tất cả các nhà vệ sinh'),
(N'Lau chùi dụng cụ thể thao', N'Lau chùi tất cả dụng cụ thể dục trong kho')


Create table Nghe
(
	MaNghe int identity(1,1) not null,
	TenNghe nvarchar(255) not null,
	MoTa nvarchar(255) not null,
	GhiChu nvarchar(255),
	Primary Key (MaNghe)
)

INSERT INTO Nghe VALUES
(N'May mặc', N'Dạy may, vá cơ bản', null),
(N'Làm giày', N'Học viên được học các bước trong quá trình làm giày', null),
(N'Nhân viên pha chế', N'Học viên được học và trải nghiệm việc pha chế thức uống', null),
(N'Lễ tân', N'Học viên được học và thực hành những kỉ năng giao tiếp cho một lễ tân', null),
(N'Kế toán', N'Học viên được học những phương thức tính toán phù hợp với ngành', null),
(N'Đầu bếp', N'Học viên được học và thực hành những kỉ năng kiến thức cơ bản của nghề đầu bếp', null),
(N'Dịch thuật viên', N'Học viên được học một số ngôn ngữ chuyên dụng nhất hiện nay', null),
(N'Tài xế', N'Học viên được học và thực hành điều khiển các loại xe khác nhau', null)

Create table DichVu
(
	MaDV int identity(1,1) not null,
	TenDV nvarchar(255) not null,
	MoTa nvarchar(255) not null,
	Gia float not null,
	Primary Key (MaDV)
)

INSERT INTO DichVu VALUES
(N'Giặt đồ thuê', N'Bệnh nhân sẽ được giặt đồ thuê', 1500000),
(N'Dọn phòng', N'Bệnh nhân sẽ được dọn phòng sạch sẽ', 1000000),
(N'Văn nghệ', N'Bệnh nhân được tham gia các hoạt động giải trí văn nghệ', 0),
(N'Massage', N'Bệnh nhân có thể trải nghiệm dịch vụ massage xông hơi', 250000)



Create table BenhNhan
(
	MaBN int identity(1,1) not null,
	MaPhong nvarchar(255) not null,
	MaNghe int,
	HoTen nvarchar(255) not null,
	GioiTinh bit not null,
	NgayVT date not null,
	ThoiGianO int not null,
	DiaChi nvarchar(255) not null,
	CMND nchar(12) not null,
	Hinh nvarchar(255) not null,
	Primary Key (MaBN),
	Foreign Key (MaPhong) References Phong (MaPhong) on Delete Cascade on Update Cascade,
	Foreign Key (MaNghe) References Nghe (MaNghe) on Delete Set null on Update Cascade,
)

INSERT INTO BenhNhan VALUES
(N'R101',5, N'Nguyễn Huỳnh Khuyết Hùng', 1, '2012-03-19', 6, N'13 Lý Thường Kiệt, Quận 10, TPHCM', '261673957','BenhNhan1.png'),
(N'R101',2, N'Vũ Đức Thiện', 1, '2013-05-16', 5, N'13 Lý Thái Tổ, Quận 12, TPHCM', '2616827489', 'BenhNhan2.png'),
(N'R101',7, N'Lương Minh Mẫn', 1, '2011-05-19', 5, N'464 Trần Duy Hưng, Quận 1, TPHCM', '261657498','BenhNhan3.png'),
(N'R101',1, N'Đặng Phú Thịnh', 1, '2010-03-19', 12, N'143 Lý Thường Kiệt, Quận 5, TPHCM', '261693725', 'BenhNhan4.png'),
(N'R101',4, N'Nguyễn Ngọc Doanh', 0, '2009-10-19', 15, N'139 Đường 12, Quận 10, TPHCM', '261673935', 'BenhNhan5.png'),
(N'R101',5, N'Dương Hoàng Quân', 1, '2016-05-14', 7, N'43 Trần Hưng Đạo, Quận 9, TPHCM', '261676743', 'BenhNhan6.png'),
(N'R101',2, N'Trần Lý Đức', 1, '2014-11-19', 13, N'163 Trần Duy Hưng, Quận 1, TPHCM', '261672532', 'BenhNhan7.png'),
(N'R101',1, N'Lê Ngọc Hải', 1, '2008-03-19', 5, N'4 Trần Duy Hưng, Quận 1, TPHCM', '261673689', 'BenhNhan8.png'),
(N'R102',4, N'Nguyễn Thị Vui', 0, '2017-08-29', 7, N'443 Trần Hưng Đạo, Quận 9, TPHCM', '2616732246', 'BenhNhan9.png'),
(N'R102',2, N'Lê Ngọc Anh', 1, '2010-03-20', 10, N'35 Đường 12, Quận 10, TPHCM', '261671043', 'BenhNhan10.png'),
(N'R102',1, N'Trần Thị Thanh Bình', 0, '2009-03-19', 12, N'424 Trần Duy Hưng, Quận 1, TPHCM', '261673968','BenhNhan11.png'),
(N'R102',4, N'Nguyễn Thiên Phú', 1, '2017-06-19', 7, N'13 Lý Thường Kiệt, Quận Gò Vấp, TPHCM', '261675086', 'BenhNhan12.png'),
(N'R103',2, N'Nguyễn Xuân Trường', 1, '2014-03-07', 6, N'443 Trần Hưng Đạo, Quận 9, TPHCM', '261673957','BenhNhan13.png'),
(N'R103',4, N'Trần Xuân Hương', 0, '2012-03-17', 3, N'35 Đường số 1, Quận 10, TPHCM', '261673964', 'BenhNhan14.png'),
(N'R104',1, N'Nguyễn Thiên Trường', 1, '2011-03-16', 4, N'163 Trần Duy Hưng, Quận 1, TPHCM', '261672579', 'BenhNhan15.png'),
(N'R105',3, N'Trần Thiên Không', 1, '2015-03-09', 8, N'83 Lý Thường Kiệt, Quận 10, TPHCM', '261673946', 'BenhNhan16.png'),
(N'R105',5, N'Nguyễn Vương Vấn', 0, '2019-03-19', 9, N'133 Lý Thường Kiệt, Quận 12, TPHCM', '261673257', 'BenhNhan17.png'),
(N'R105',1, N'Nguyễn Nhân', 1, '2010-03-10', 9, N'44 Trần Hưng Đạo, Quận 5, TPHCM', '261673987','BenhNhan18.png'),
(N'R105',3, N'Nguyễn Vũ Nhân', 1, '2019-10-19', 10, N'422 Trần Duy Hưng, Quận 1, TPHCM', '261673321', 'BenhNhan19.png'),
(N'R105',1, N'Trần Thanh Nhiên', 0, '2018-03-20', 12, N'63 Lý Thường Kiệt, Quận 9, TPHCM', '261646205', 'BenhNhan20.png'),
(N'R201',1, N'Lý Đức', 1, '2009-03-09', 13, N'35 Đường 12, Quận 10, TPHCM', '261673982', 'BenhNhan21.png'),
(N'R201',4, N'Trần Lý Xuân', 1, '2006-12-17', 14, N'122 Lý Thường Kiệt, Quận 10, TPHCM', '261673268', 'BenhNhan22.png'),
(N'R201',1, N'Nguyễn Xuân Phú', 1, '2010-03-19', 15, N'35 Đường số 5, Quận 12, TPHCM', '261673567', 'BenhNhan23.png'),
(N'R201',6, N'Trần Thị Thanh', 0, '2008-06-19', 6, N'17 Lý Thường Kiệt, Quận 2, TPHCM', '261673742','BenhNhan24.png'),
(N'R201',1, N'Nguyễn Việt Nam', 1, '2020-07-19', 6, N'424 Trần Duy Hưng, Quận 1, TPHCM', '261672356','BenhNhan25.png'),
(N'R202',3, N'Trần Nguyễn', 1, '2009-02-19', 8, N'442 Trần Hưng Đạo, Quận 9, TPHCM', '261673579', 'BenhNhan26.png'),
(N'R204',1, N'Trần Việt Lý', 1, '2009-08-23', 10, N'35 Đường số 1, Quận Gò Vấp, TPHCM', '261673543', 'BenhNhan27.png'),
(N'R204',6, N'Nguyễn Thanh Minh', 1, '2002-03-19', 6, N'138 Lý Thường Kiệt, Quận 10, TPHCM', '261673982','BenhNhan28.png')

Create table BenhAn
(
	MaBN int not null,
	BieuHien nvarchar(255) not null,
	CachDT nvarchar(255) not null,
	GhiChu nvarchar(255),
	Primary Key (MaBN),
	Foreign Key (MaBN) References BenhNhan (MaBN) on Delete Cascade on Update Cascade,
)
INSERT INTO BenhAn VALUES
(1, N'Mệt mỏi, sợ nước, sợ lạnh, thiếu ngủ', N'Phương pháp cai khô',Null),
(2, N'Nhịp tim nhanh, run, khô miệng, dị cảm, co cơ', N'Phương pháp thùy miên',Null),
(3, N'Đổ mồ hôi, lơ mơ, mệt mỏi, mất ngủ', N'Phương pháp cai dần',Null),
(4, N'Khó chịu,Ho khàn, thiếu ngủ', N'Phương pháp cai khô',Null),
(5, N'Mệt mỏi, sợ nước, thiếu ngủ', N'Phương pháp điện châm',Null),
(6, N'Nhịp tim nhanh, run, khô miệng, dị cảm, co cơ', N'Phương pháp cai khô',Null),
(7, N'Mệt mỏi, sợ nước, sợ lạnh, thiếu ngủ', N'Phương pháp dùng thuốc đối kháng',Null),
(8, N'Đổ mồ hôi, lơ mơ, mệt mỏi, mất ngủ', N'Phương pháp phẫu thuật thùy trán',Null),
(9, N'Mệt mỏi, sợ nước, sợ lạnh, thiếu ngủ', N'Phương pháp điện châm',Null),
(10, N'Nhịp tim nhanh, run, khô miệng, dị cảm, co cơ', N'Phương pháp cai khô',Null),
(11, N'Đổ mồ hôi, lơ mơ, mệt mỏi, mất ngủ', N'Dùng các thuốc hướng tâm thần',Null),
(12, N'Nhịp tim nhanh, run, khô miệng, dị cảm, co cơ', N'Phương pháp thùy miên',Null),
(13, N'Đổ mồ hôi, lơ mơ, mệt mỏi, mất ngủ', N'Dùng các thuốc hướng tâm thần',Null),
(14, N'Mệt mỏi, sợ nước, sợ lạnh, thiếu ngủ', N'Phương pháp dùng thuốc đối kháng',Null),
(15, N'Mệt mỏi, sợ nước, sợ lạnh, thiếu ngủ', N'Phương pháp cai khô',Null),
(16, N'Nhịp tim nhanh, run, khô miệng, dị cảm, co cơ', N'Phương pháp điều trị bằng chất thay thế',Null),
(17, N'Đổ mồ hôi, lơ mơ, mệt mỏi, mất ngủ', N'Phương pháp điện châm',Null),
(18, N'Choáng váng, ảo thị, nhìn không rõ, hay giật mình', N'Phương pháp dùng thuốc đối kháng',Null),
(19, N'Đổ mồ hôi, lơ mơ, mệt mỏi, mất ngủ', N'Phương pháp thùy miên',Null),
(20, N'Nhịp tim nhanh, run, khô miệng, dị cảm, co cơ', N'Phương pháp cai dần',Null),
(21, N'Choáng váng, ảo thị, nhìn không rõ, hay giật mình', N'Phương pháp điện châm',Null),
(22, N'Choáng váng, ảo thị, nhìn không rõ, hay giật mình', N'Phương pháp cai dần',Null),
(23, N'Mệt mỏi, sợ nước, sợ lạnh, thiếu ngủ', N'Phương pháp thùy miên',Null),
(24, N'Nhịp tim nhanh, run, khô miệng, dị cảm, co cơ', N'Phương pháp điều trị bằng chất thay thế',Null),
(25, N'Đổ mồ hôi, lơ mơ, mệt mỏi, mất ngủ', N'Dùng các thuốc hướng tâm thần',Null),
(26, N'Nhịp tim nhanh, run, khô miệng, dị cảm, co cơ', N'Phương pháp phẫu thuật thùy trán',Null),
(27, N'Choáng váng, ảo thị, nhìn không rõ, hay giật mình', N'Phương pháp điều trị bằng chất thay thế',Null),
(28, N'Mệt mỏi, sợ nước, sợ lạnh, thiếu ngủ', N'Phương pháp phẫu thuật thùy trán',Null)




Create table DichVuCT
(
	MaDVCT int identity(1,1) not null,
	MaDV int not null,
	MaBN int not null,
	NgayDK date not null,
	GhiChu nvarchar(255),
	Primary Key (MaDVCT),
	Foreign Key (MaDV) References DichVu(MaDV) on Delete Cascade on Update Cascade,
	Foreign Key (MaBN) References BenhNhan(MaBN) on Delete Cascade on Update Cascade
)
INSERT INTO DichVuCT VALUES
(1, 1,'2012-07-19',  null),
(2, 1,'2012-08-19', null),
(2, 2,'2013-07-16', null),
(3, 3,'2011-09-19', null),
(1, 3,'2011-09-19', null),
(4, 1,'2012-09-19', null),
(4, 4,'2010-05-19', null),
(1, 4,'2010-07-19', null),
(3, 5,'2009-12-19', null),
(1, 6, '2016-08-14', null),
(2, 7,'2015-01-19', null),
(1, 8, '2008-05-19', null),
(3, 10,'2010-05-20', null)






Create table HoatDongCT
(
	MaHDCT int identity(1,1) not null,
	MaBN int not null,
	MaHD int not null,
	NgayTH date not null,
	GhiChu nvarchar(255),
	Primary Key (MaHDCT),
	Foreign Key (MaHD) References HoatDong (MaHD) on Delete Cascade on Update Cascade,
	Foreign Key (MaBN) References BenhNhan (MaBN) on Delete Cascade on Update Cascade,
)

INSERT INTO HoatDongCT VALUES
(1, 1, '2012-04-19', null),
(1, 4, '2012-04-30', null),
(1, 3, '2012-05-19', null),
(2, 1, '2013-06-16', null),
(2, 2, '2013-07-16', null),
(2, 4, '2013-08-16', null),
(3, 5, '2012-05-19', null),
(3, 3, '2013-05-19', null),
(4, 1, '2020-04-29', null),
(4, 2, '2020-05-09', null),
(5, 3, '2020-02-15', null),
(5, 4, '2020-03-16', null),
(5, 5, '2020-06-12', null),
(6, 1, '2020-05-13', null),
(6, 2, '2020-07-11', null),
(6, 4, '2020-06-16', null),
(7, 5, '2020-07-15', null),
(7, 3, '2020-05-15', null),
(7, 1, '2020-05-11', null),
(8, 2, '2020-01-11', null),
(8, 3, '2020-02-14', null),
(8, 5, '2020-03-17', null),
(9, 1, '2020-07-14', null),
(9, 2, '2020-04-16', null),
(10, 5, '2020-07-15', null),
(11, 3, '2020-01-13', null),
(12, 2, '2020-03-17', null),
(13, 1, '2020-04-13', null)


Create table HoaDon
(
	MaHD int identity(1,1) not null,
	MaBN int not null,
	MaNV nvarchar(255),
	ThoiGian int not null,
	NgayBDTT date not null,
	NgayKTTT date not null,
	NgayThanhToan date not null,
	TongTien float not null,
	Primary Key (MaHD),
	Foreign Key (MaBN) References BenhNhan (MaBN) on Delete Cascade on Update Cascade,
	Foreign Key (MaNV) References NhanVien (MaNV)
)
INSERT INTO HoaDon VALUES
(1, null, 2,'2012-03-19','2012-05-19','2012-05-19', 4500000),
(1, N'ThienVD', 1,'2012-05-19','2012-06-19','2013-06-19', 2000000),
(1, N'ThienVD', 1,'2012-06-19','2012-07-19','2013-09-19', 1450000)
Create table ThanNhan
(
	MaTN varchar(50) primary key,
	MatKhau varchar(50),
	HoTenTN nvarchar(255),
	Email varchar(255),
	MaBN int,
	Foreign Key (MaBN) References BenhNhan (MaBN) on Delete Cascade on Update Cascade
);
INSERT INTO ThanNhan(MaTN,MatKhau,HoTenTN,Email,MaBN) VALUES
('TN01','123',N'Lê Thị Khắm','thinhdp2002@gmail.com',2)
select * from ThanNhan
-- Thống kê doanh thu từng năm theo phòng 
DROP PROCEDURE sp_ThongKeDoanhThu
CREATE PROC sp_ThongKeDoanhThu(@Year INT)
AS BEGIN
	SELECT
		Phong.MaPhong Phong,
		COUNT(bn.MaBN) Soluongbenhnhan,
		SUM(hd.TongTien) TongTien,
		Min(hd.TongTien) TienNhoNhat,
		Max(hd.TongTien) TienLonNhat
		FROM Phong
		join BenhNhan bn on bn.MaPhong = Phong.MaPhong
		join HoaDon hd on hd.MaBN = bn.MaBN
		where Year(hd.NgayThanhToan) = @Year 
		GROUP BY phong.MaPhong
END

exec sp_ThongKeDoanhThu 2020



-- Thống kê dịch vụ được sữ dụng nhiều đến ít
DROP PROCEDURE sp_ThongKeDichVu
CREATE PROC sp_ThongKeDichVu(@Year INT)
AS BEGIN
	SELECT
		DichVu.TenDV,
		Count(BenhNhan.MaBN) SoLuongBenhNhan
		from DichVuCT
		inner join DichVu on DichVuCT.MaDV = DichVu.MaDV
		inner join BenhNhan on BenhNhan.MaBN = DichVuCT.MaBN
		where YEAR(NgayDK) = @Year
		GROUP BY DichVu.TenDV
		Order by SoLuongBenhNhan DESC 
END

exec sp_ThongKeDichVu 2010



