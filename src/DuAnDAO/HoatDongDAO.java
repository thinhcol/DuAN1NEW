/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuAnDAO;

import Entity.HoatDong;
import Helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class HoatDongDAO {
    public void insert(HoatDong model) {
        String sql = "INSERT INTO HoatDong (TenHT,GhiChu) VALUES ( ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getTenHoatDong(),
                model.getGhichu()
        );
    }

    public void update(HoatDong model) {
        String sql = "Update HoatDong set TenHT=?, GhiChu =? Where MaHD =?";
        JdbcHelper.executeUpdate(sql,
                model.getTenHoatDong(),
                model.getGhichu(),
                model.getMaHoatDong());
    }
    
    public void delete(int id) {
        String sql = "DELETE FROM HoatDong WHERE MaHD=?";
        JdbcHelper.executeUpdate(sql, id);
    }

    public List<HoatDong> select() {
        String sql = "SELECT * FROM HoatDong";
        return SelectBySQL(sql);
    }
    
    public HoatDong findById(int id) {
        String sql = "SELECT * FROM HoatDong WHERE MaHD=?";
        List<HoatDong> list = SelectBySQL(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    protected List<HoatDong> SelectBySQL(String sql, Object... args) {
        List<HoatDong> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    HoatDong model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
    
    private HoatDong readFromResultSet(ResultSet rs) throws SQLException {
        HoatDong model = new HoatDong();
        model.setMaHoatDong(rs.getInt("MaHD"));
        model.setTenHoatDong(rs.getString("TenHT"));
        model.setGhichu(rs.getString("GhiChu"));
        return model;
    }
}
