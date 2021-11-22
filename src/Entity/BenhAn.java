/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Admin
 */
public class BenhAn {
    private  int MaBA,MaBN;
    private String bieuhien,cachdt,ghichu;

    public BenhAn(int MaBA, int MaBN, String bieuhien, String cachdt, String ghichu) {
        this.MaBA = MaBA;
        this.MaBN = MaBN;
        this.bieuhien = bieuhien;
        this.cachdt = cachdt;
        this.ghichu = ghichu;
    }
    
    
    public BenhAn() {
    }

    public int getMaBN() {
        return MaBN;
    }

    public void setMaBN(int MaBN) {
        this.MaBN = MaBN;
    }

    public void setMaBA(int MaBA) {
        this.MaBA = MaBA;
    }

    public int getMaBA() {
        return MaBA;
    }

  
    public String getBieuhien() {
        return bieuhien;
    }

    public void setBieuhien(String bieuhien) {
        this.bieuhien = bieuhien;
    }

    public String getCachdt() {
        return cachdt;
    }

    public void setCachdt(String cachdt) {
        this.cachdt = cachdt;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
    
}
