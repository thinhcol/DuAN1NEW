/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuAnDAO;

import Entity.BenhNhan;
import Helper.DateHelper;
import Helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class BenhNhanDAO {

    String insert = "INSERT INTO BenhNhan VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String delete = "DELETE FROM BenhNhan WHERE MaBN like ?";
    String update = "Update BenhNhan set MaPhong=?, MaNghe=?, HoTen=? ,GioiTinh = ?, "
            + "NgayVT = ?, ThoiGianO = ?, DiaChi = ?, CMND = ?, Hinh = ?  Where MaBN like ?";
    String selectAll = "SELECT * FROM BenhNhan";
    String selectByID = "SELECT * FROM BenhNhan WHERE MaBN=?";

    public void insert(BenhNhan entity) {
        JdbcHelper.executeUpdate(insert,
                entity.getMaPhong(),
                entity.getMaNghe(),
                entity.getHoTen(),
                entity.isGioiTinh(),
                entity.getNgayVT(),
                entity.getThoiGianO(),
                entity.getDiaChi(),
                entity.getCMND(),
                entity.getHinh()
        );
    }

    public void delete(int key) {
        JdbcHelper.executeUpdate(delete, key);
    }

    public void update(BenhNhan entity) {
        JdbcHelper.executeUpdate(update,
                entity.getMaPhong(),
                entity.getMaNghe(),
                entity.getHoTen(),
                entity.isGioiTinh(),
                entity.getNgayVT(),
                entity.getThoiGianO(),
                entity.getDiaChi(),
                entity.getCMND(),
                entity.getHinh(),
                entity.getMaBN()
        );
    }

    public List<BenhNhan> selectAll() {
        return this.selectBySql(selectAll);
    }

    public BenhNhan selectByID(int key) {
        List<BenhNhan> list = this.selectBySql(selectByID, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<BenhNhan> findlistById(int id) {
        List<BenhNhan> list = selectBySql(selectByID, id);
        return list;
    }

    public List<BenhNhan> selectByKeyword(String keyword, String keyword1, String keyword2) {
        String sql = "SELECT * FROM BenhNhan WHERE HoTen LIKE ? or MaBN LIKE ? or MaPhong LIKE ?";
        return selectBySql(sql, "%" + keyword + "%", "%" + keyword1 + "%", "%" + keyword2 + "%");
    }

    protected List<BenhNhan> selectBySql(String sql, Object... args) {
        List<BenhNhan> list = new ArrayList<BenhNhan>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                BenhNhan entity = new BenhNhan();
                entity.setMaBN(rs.getInt("MaBN"));
                entity.setMaPhong(rs.getString("MaPhong"));
                entity.setMaNghe(rs.getInt("MaNghe"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setNgayVT(rs.getDate("NgayVT"));
                entity.setThoiGianO(rs.getInt("ThoiGianO"));
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setCMND(rs.getString("CMND"));
                entity.setHinh(rs.getString("Hinh"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BenhNhan findlistByCMND(String cmnd) {
        String sql = "select * from BenhNhan where CMND like ?";
        List<BenhNhan> list = this.selectBySql(sql, cmnd);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
