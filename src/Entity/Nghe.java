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
public class Nghe {

    private int manghe;
    private String Tennghe, Mota, Ghichu;
    @Override
    public String toString(){
        return this.Tennghe;
    }
    public Nghe(int manghe, String Tennghe, String Mota, String Ghichu) {
        this.manghe = manghe;
        this.Tennghe = Tennghe;
        this.Mota = Mota;
        this.Ghichu = Ghichu;
    }

    public Nghe() {
    }

    public int getManghe() {
        return manghe;
    }

    public void setManghe(int manghe) {
        this.manghe = manghe;
    }

    public String getTennghe() {
        return Tennghe;
    }

    public void setTennghe(String Tennghe) {
        this.Tennghe = Tennghe;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String Mota) {
        this.Mota = Mota;
    }

    public String getGhichu() {
        return Ghichu;
    }

    public void setGhichu(String Ghichu) {
        this.Ghichu = Ghichu;
    }

}
