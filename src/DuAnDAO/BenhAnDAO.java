/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuAnDAO;

import Entity.BenhAn;
import Helper.JdbcHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class BenhAnDAO {

    public void insert(BenhAn model) {
        String sql = "INSERT INTO BenhAn (MaBN,BieuHien, CachDT, GhiChu) VALUES ( ?,?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaBN(),
                model.getBieuhien(),
                model.getCachdt(),
                model.getGhichu()
        );
    }

    public void update(BenhAn model) {
        String sql = "Update  BenhAn set  BieuHien = ?, CachDT = ?, GhiChu = ? Where MaBN = ?";
        JdbcHelper.executeUpdate(sql,
                model.getBieuhien(),
                model.getCachdt(),
                model.getGhichu(),
                model.getMaBN());
    }

    public void delete(int id) {
        String sql = "DELETE FROM BenhAn WHERE MaBN=?";
        JdbcHelper.executeUpdate(sql, id);
    }

    public List<BenhAn> select() {
        String sql = "SELECT * FROM BenhAn";
        return SelectBySQL(sql);
    }

    public BenhAn findById(int id) {
        String sql = "SELECT * FROM BenhAn WHERE MaBN=?";
        List<BenhAn> list = SelectBySQL(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<BenhAn> findlistById(int id) {
        String sql = "SELECT * FROM BenhAn WHERE MaBN=?";
        List<BenhAn> list = SelectBySQL(sql, id);
        return list;
    }

    protected List<BenhAn> SelectBySQL(String sql, Object... args) {
        List<BenhAn> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);

                while (rs.next()) {
                    BenhAn model = readFromResultSet(rs);
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

    private BenhAn readFromResultSet(ResultSet rs) throws SQLException {
        BenhAn model = new BenhAn();
        model.setMaBN(rs.getInt("MaBN"));
        model.setBieuhien(rs.getString("BieuHien"));
        model.setCachdt(rs.getString("CachDT"));
        model.setGhichu(rs.getString("GhiChu"));
        return model;
    }
}
