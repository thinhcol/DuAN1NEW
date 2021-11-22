/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author admin
 */
public class BenhNhan {

    private int MaBN;
    private String MaPhong;
    private int MaNghe;
    String HoTen;
    private boolean GioiTinh;
    private Date NgayVT;
    private int ThoiGianO;
    private String DiaChi;
    private String CMND;
    private String Hinh;

    public String toString() {
        return this.HoTen;
    }

    public int getMaBN() {
        return MaBN;
    }

    public void setMaBN(int MaBN) {
        this.MaBN = MaBN;
    }

    public String getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(String MaPhong) {
        this.MaPhong = MaPhong;
    }

    public int getMaNghe() {
        return MaNghe;
    }

    public void setMaNghe(int MaNghe) {
        this.MaNghe = MaNghe;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public Date getNgayVT() {
        return NgayVT;
    }

    public void setNgayVT(Date NgayVT) {
        this.NgayVT = NgayVT;
    }

    public int getThoiGianO() {
        return ThoiGianO;
    }

    public void setThoiGianO(int ThoiGianO) {
        this.ThoiGianO = ThoiGianO;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String Hinh) {
        this.Hinh = Hinh;
    }

}
