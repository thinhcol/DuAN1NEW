/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuAnDAO;

import Entity.PhanCong;
import Helper.JdbcHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class PhanCongDAO {

    public void insert(PhanCong model) {
        String sql = "INSERT INTO PhanCong (MaPC, MaCV,MaBN,MoTa, GhiChu) VALUES (?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaPC(),
                model.getMaCV(),
                model.getMaBN(),
                model.getMoTa(),
                model.getGhiChu()
        );
    }

    public void update(PhanCong model) {
        String sql = "Update  PhanCong set  MaCV = ?,MaBN = ?, MoTa = ?, GhiChu = ? Where MaPC = ?";
        JdbcHelper.executeUpdate(sql,
                model.getMaCV(),
                model.getMaBN(),
                model.getMoTa(),
                model.getGhiChu(),
                model.getMaPC());
    }

    public void delete(int MaBA) {
        String sql = "DELETE FROM PhanCong WHERE MaPC=?";
        JdbcHelper.executeUpdate(sql, MaBA);
    }

    public List<PhanCong> select() {
        String sql = "SELECT * FROM PhanCong";
        return SelectBySQL(sql);
    }

    public PhanCong findById(int id) {
        String sql = "SELECT * FROM PhanCong WHERE MaPC=?";
        List<PhanCong> list = SelectBySQL(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    protected List<PhanCong> SelectBySQL(String sql, Object... args) {
        List<PhanCong> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);

                while (rs.next()) {
                    PhanCong model = readFromResultSet(rs);
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

    private PhanCong readFromResultSet(ResultSet rs) throws SQLException {
        PhanCong model = new PhanCong();
        model.setMaPC(rs.getInt("MaPC"));
        model.setMaCV(rs.getInt("MaCV"));
        model.setMaBN(rs.getInt("MaBN"));
        model.setMoTa(rs.getString("MoTa"));
        model.setGhiChu(rs.getString("GhiChu"));

        return model;
    }
}
