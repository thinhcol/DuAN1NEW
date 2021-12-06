/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class LichSu {
    private int MaBN,STT;
    private String HoTen;
    private Date NgayVT;
    private int ThoiGianO;

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public int getMaBN() {
        return MaBN;
    }

    public void setMaBN(int MaBN) {
        this.MaBN = MaBN;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
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

    
}
