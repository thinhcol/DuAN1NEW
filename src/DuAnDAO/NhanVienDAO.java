/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuAnDAO;

import Entity.NhanVien;
import Helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class NhanVienDAO {

    public void insert(NhanVien model) {
        String sql = "INSERT INTO NhanVien (MaNV, HoTen, MatKhau, GioiTinh, Email, Hinh, SDT, ChucVu) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaNV(),
                model.getHoTen(),
                model.getMatKhau(),
                model.isGioiTinh(),
                model.getEmail(),
                model.getHinh(),
                model.getSoDT(),
                model.isChucVu()
        );
    }

    String update = "UPDATE NhanVien SET HoTen=?, MatKhau=?, GioiTinh=?, Email=?, SDT=?, ChucVu=?, Hinh=? WHERE MaNV=?";
    String selectAll = "SELECT * FROM NhanVien";
    String selectByID = "SELECT * FROM NhanVien WHERE MaNV=?";

    public void update(NhanVien entity) {
        JdbcHelper.executeUpdate(update,
                entity.getHoTen(),
                entity.getMatKhau(),
                entity.isGioiTinh(),
                entity.getEmail(),
                entity.getSoDT(),
                entity.isChucVu(),
                entity.getHinh(),
                entity.getMaNV());

    }

    public void delete(String MaNV) {
        String sql = "DELETE FROM NhanVien WHERE MaNV=?";
        JdbcHelper.executeUpdate(sql, MaNV);
    }

    public NhanVien selectEmailByID(String id) {
        String selectEmailByID = "SELECT * from NhanVien where MaNV = ?";
        List<NhanVien> list = this.selectBySql(selectEmailByID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public NhanVien selectSDTByID(String id) {
        String selectSDTByID = "SELECT * from NhanVien where MaNV = ?";
        List<NhanVien> list = this.selectBySql(selectSDTByID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<NhanVien> selectAll() {
        return this.selectBySql(selectAll);
    }

    public NhanVien selectByID(String id) {
        List<NhanVien> list = this.selectBySql(selectByID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public List<NhanVien> selectByKeyword(String keyword,String keyword1,String keyword2) {
        String sql = "SELECT * FROM NhanVien WHERE HoTen LIKE ? or MaNV LIKE ? or SDT LIKE ?";
        return selectBySql(sql,"%" + keyword + "%","%" + keyword1 + "%","%" + keyword2 + "%");      
    }

    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<NhanVien>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNV"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setEmail(rs.getString("Email"));
                entity.setSoDT(rs.getString("SDT"));
                entity.setChucVu(rs.getBoolean("ChucVu"));
                entity.setHinh(rs.getString("Hinh"));
                entity.setMaid(rs.getInt("MaNhanDien"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
