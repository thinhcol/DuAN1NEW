/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuAnDAO;

import Entity.Phong;
import Helper.JdbcHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class PhongDAO {
    public void insert(Phong model) {
        String sql = "INSERT INTO Phong (MaPhong,MaNV,Gia,Hinh) VALUES ( ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaPhong(),
                model.getMaNV(),
                model.getGia(),
                model.getHinh()
        );
    }

    public void update(Phong model) {
        String sql = "Update Phong set  Gia = ?,Hinh = ? Where MaPhong = ?";
        JdbcHelper.executeUpdate(sql,
                model.getGia(),
                model.getHinh(),
                model.getMaPhong());
    }
    public void delete(String id) {
        String sql = "DELETE FROM Phong WHERE Phong=?";
        JdbcHelper.executeUpdate(sql, id);
    }
      public List<Phong> select() {
        String sql = "SELECT * FROM Phong";
        return SelectBySQL(sql);
    }
       public Phong findById(String id) {
        String sql = "SELECT * FROM Phong WHERE MaPhong=?";
        List<Phong> list = SelectBySQL(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }
        protected List<Phong> SelectBySQL(String sql, Object... args) {
        List<Phong> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);

                while (rs.next()) {
                    Phong model = readFromResultSet(rs);
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

    private Phong readFromResultSet(ResultSet rs) throws SQLException {
        Phong model = new Phong();
        model.setMaPhong(rs.getString("MaPhong"));
        model.setMaNV(rs.getString("MaNV"));
        model.setGia(rs.getDouble("Gia"));
        model.setHinh(rs.getString("Hinh"));
        
        return model;
    }
}
