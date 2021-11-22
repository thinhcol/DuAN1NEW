/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuAnDAO;

import Entity.HoaDon;
import Helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class HoaDonDAO {
    String insert = "INSERT INTO HoaDon VALUES (?, ?, ?, ?, ?, ?, ?)";
    String delete = "DELETE FROM HoaDon WHERE MaHD like ?";
    String update = "UPDATE HoaDon SET MaBN=?, MaNV = ? ,ThoiGian=?, NgayBDTT = ?,NgayKTTT = ?, NgayThanhToan=? ,TongTien = ? WHERE MaHD=?";
    String selectAll = "SELECT * FROM HoaDon";
    String selectByID = "SELECT * FROM HoaDon WHERE MaHD=?";

    public void insert(HoaDon entity) {
        JdbcHelper.executeUpdate(insert,
                entity.getMaBN(),
                entity.getMaNV(),
                entity.getThoiGian(),
                entity.getNgayBDTT(),
                entity.getNgayKTTT(),
                entity.getNgayThanhToan(),
                entity.getTongTien()
        );
    }
    
    public void delete(Integer key) {
        JdbcHelper.executeUpdate(delete, key);
    }

    public void update(HoaDon entity) {
        JdbcHelper.executeUpdate(update,
                entity.getMaBN(),
                entity.getMaNV(),
                entity.getThoiGian(),
                entity.getNgayBDTT(),
                entity.getNgayKTTT(),
                entity.getNgayThanhToan(),
                entity.getTongTien(),
                entity.getMaHD()
        );
    }

    public List<HoaDon> selectAll() {
        return this.selectBySql(selectAll);
    }

    public HoaDon selectByID(Integer key) {
        List<HoaDon> list = this.selectBySql(selectByID, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    protected List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<HoaDon>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                HoaDon model = new HoaDon();
                model.setMaHD(rs.getInt("MaHD"));
                model.setMaBN(rs.getInt("MaBN"));
                model.setMaNV(rs.getString("MaNV"));
                model.setThoiGian(rs.getInt("ThoiGian"));
                model.setNgayBDTT(rs.getDate("NgayBDTT"));
                model.setNgayKTTT(rs.getDate("NgayKTTT"));
                model.setNgayThanhToan(rs.getDate("NgayThanhToan"));
                model.setTongTien(rs.getDouble("TongTien"));
                list.add(model);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<HoaDon> selectByMaBN(Integer key) {
        String selectByMaBN = "select * from HoaDon where MaBN = ? order by NgayThanhToan ASC ";
        return this.selectBySql(selectByMaBN, key);
    }
}
