/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class HoaDon {

    private int maHD;
    private int maBN;
    private String maNV;
    private int thoiGian;
    private Date ngayBDTT;
    private Date ngayKTTT;
    private Date ngayThanhToan;
    private double tongTien;

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaBN() {
        return maBN;
    }

    public void setMaBN(int maBN) {
        this.maBN = maBN;
    }

    public int getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(int thoiGian) {
        this.thoiGian = thoiGian;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayBDTT() {
        return ngayBDTT;
    }

    public void setNgayBDTT(Date ngayBDTT) {
        this.ngayBDTT = ngayBDTT;
    }

    public Date getNgayKTTT() {
        return ngayKTTT;
    }

    public void setNgayKTTT(Date ngayKTTT) {
        this.ngayKTTT = ngayKTTT;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

}
