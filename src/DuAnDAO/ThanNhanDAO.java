/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuAnDAO;

import Entity.BenhNhan;
import Entity.ThanNhan;
import Helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ThanNhanDAO {

    public void insert(ThanNhan entity) {
        String sql = "INSERT INTO ThanNhan(MaTN,MatKhau,HoTenTN,Email,MaBN) VALUES (?,?,?,?,?)";
        JdbcHelper.executeUpdate(sql,
                entity.getMatn(),
                entity.getPass(),
                entity.getHoten(),
                entity.getEmail(),
                entity.getMabn()
        );
    }

    public void delete(String key) {
        String sql = "Delete from ThanNhan where MaTN like ?";
        JdbcHelper.executeUpdate(sql, key);
    }

    public void update(ThanNhan entity) {
        String sql = "Update ThanNhan set MatKhau = ?,HoTenTN = ?,Email = ?,MaBN = ? where MaTN like ?";
        JdbcHelper.executeUpdate(sql,
                entity.getPass(),
                entity.getHoten(),
                entity.getEmail(),
                entity.getMabn(),
                entity.getMatn()
        );
    }

    public List<ThanNhan> selectAll() {
        String sql = "Select * from ThanNhan";
        return this.selectBySql(sql);
    }

    public ThanNhan selectByID(String key) {
        String sql = "Select * from ThanNhan where MaTN like ?";
        List<ThanNhan> list = this.selectBySql(sql, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    protected List<ThanNhan> selectBySql(String sql, Object... args) {
        List<ThanNhan> list = new ArrayList<ThanNhan>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                ThanNhan entity = new ThanNhan();
                entity.setMatn(rs.getString("MaTN"));
                entity.setPass(rs.getString("MatKhau"));
                entity.setHoten(rs.getString("HoTenTN"));
                entity.setEmail(rs.getString("Email"));
                entity.setMabn(rs.getInt("MaBN"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public ThanNhan selectEmailByID(String id) {
        String selectEmailByID = "SELECT * from ThanNhan where MaTN = ?";
        List<ThanNhan> list = this.selectBySql(selectEmailByID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
