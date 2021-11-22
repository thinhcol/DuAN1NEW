/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuAnDAO;

import Entity.Nghe;
import Helper.JdbcHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NgheDAO {
    public void insert(Nghe model) {
        String sql = "INSERT INTO Nghe (TenNghe,Mota,GhiChu) VALUES ( ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getTennghe(),
                model.getMota(),
                model.getGhichu()
        );
    }

    public void update(Nghe model) {
        String sql = "Update Nghe set TenNghe=?, MoTa =?, GhiChu =? Where MaNghe =?";
        JdbcHelper.executeUpdate(sql,
                 model.getTennghe(),
                model.getMota(),
                model.getGhichu(),
                model.getManghe());
    }

    public void delete(int id) {
        String sql = "DELETE FROM Nghe WHERE MaNghe=?";
        JdbcHelper.executeUpdate(sql, id);
    }

    public List<Nghe> select() {
        String sql = "SELECT * FROM Nghe";
        return SelectBySQL(sql);
    }

    public Nghe findById(int id) {
        String sql = "SELECT * FROM Nghe WHERE MaNghe=?";
        List<Nghe> list = SelectBySQL(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    protected List<Nghe> SelectBySQL(String sql, Object... args) {
        List<Nghe> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    Nghe model = readFromResultSet(rs);
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

    private Nghe readFromResultSet(ResultSet rs) throws SQLException {
        Nghe model = new Nghe();
        model.setManghe(rs.getInt("MaNghe"));
        model.setTennghe(rs.getString("TenNghe"));
        model.setMota(rs.getString("MoTa"));
        model.setGhichu(rs.getString("GhiChu"));
        return model;
    }
}
