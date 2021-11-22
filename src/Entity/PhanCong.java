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
public class PhanCong {
    private  int MaPC,MaCV,MaBN;
    String MoTa,GhiChu;

    public PhanCong(int MaPC, int MaCV, int MaBN, String MoTa, String GhiChu) {
        this.MaPC = MaPC;
        this.MaCV = MaCV;
        this.MaBN = MaBN;
        this.MoTa = MoTa;
        this.GhiChu = GhiChu;
    }

    public PhanCong() {
    }

    public int getMaPC() {
        return MaPC;
    }

    public void setMaPC(int MaPC) {
        this.MaPC = MaPC;
    }

    public int getMaCV() {
        return MaCV;
    }

    public void setMaCV(int MaCV) {
        this.MaCV = MaCV;
    }

    public int getMaBN() {
        return MaBN;
    }

    public void setMaBN(int MaBN) {
        this.MaBN = MaBN;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    
}
