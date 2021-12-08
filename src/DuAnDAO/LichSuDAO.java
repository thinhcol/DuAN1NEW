/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuAnDAO;

import Entity.LichSu;
import Helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class LichSuDAO {
    
    String insert = "INSERT INTO LichSuBenhNhan VALUES (?, ?, ?, ?)";
    String delete = "DELETE FROM LichSuBenhNhan WHERE STT like ?";
    String update = "update LichSuBenhNhan set MaBN= ?, HoTen=?, NgayVT = ?, ThoiGianO = ? Where MaBN=?";
    String selectAll = "SELECT * FROM LichSuBenhNhan";
    String selectByID = "SELECT * FROM LichSuBenhNhan WHERE STT=?";

    public void insert(LichSu entity) {
        JdbcHelper.executeUpdate(insert,
                entity.getMaBN(),
                entity.getHoTen(),
                entity.getNgayVT(),
                entity.getThoiGianO()
        );
    }

    public void delete(int key) {
        JdbcHelper.executeUpdate(delete, key);
    }
    public List<LichSu> selctById(int id) {
        String sql = "SELECT * FROM LichSuBenhNhan WHERE MaBN=?";
        List<LichSu> list = selectBySql(sql, id);
        return list;
    }

    public void update(LichSu entity, String maBN) {
        JdbcHelper.executeUpdate(update,
                maBN,
                entity.getHoTen(),
                entity.getNgayVT(),
                entity.getThoiGianO(),
                entity.getMaBN()
        );
    }

    public List<LichSu> selectAll() {
        return this.selectBySql(selectAll);
    }

    public LichSu selectByID(int key) {
        List<LichSu> list = this.selectBySql(selectByID, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    protected List<LichSu> selectBySql(String sql, Object... args) {
        List<LichSu> list = new ArrayList<LichSu>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                LichSu entity = new LichSu();
                entity.setSTT(rs.getInt("STT"));
                entity.setMaBN(rs.getInt("MaBN"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setNgayVT(rs.getDate("NgayVT"));
                entity.setThoiGianO(rs.getInt("ThoiGianO"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
