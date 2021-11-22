/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author admin
 */
public class HoatDong {
    private int MaHoatDong;   
    private String TenHoatDong, Ghichu;
   
    @Override
    public String toString(){
        return this.TenHoatDong;
    }

    public HoatDong(int MaHoatDong, String TenHoatDong, String Ghichu) {
        this.MaHoatDong = MaHoatDong;        
        this.TenHoatDong = TenHoatDong;
        this.Ghichu = Ghichu;
    }

    public HoatDong() {
    }

    public int getMaHoatDong() {
        return MaHoatDong;
    }

    public void setMaHoatDong(int MaHoatDong) {
        this.MaHoatDong = MaHoatDong;
    }

    public String getTenHoatDong() {
        return TenHoatDong;
    }

    public void setTenHoatDong(String TenHoatDong) {
        this.TenHoatDong = TenHoatDong;
    }

    public String getGhichu() {
        return Ghichu;
    }

    public void setGhichu(String Ghichu) {
        this.Ghichu = Ghichu;
    }    
}
