/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuAnDAO;

import Entity.DichVu;
import Helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class DichVuDAO{
    
    String insert = "INSERT INTO DichVu VALUES (?, ?, ?)";
    String delete = "DELETE FROM DichVu WHERE MaDV like ?";
    String update = "UPDATE DichVu SET TenDV=?, MoTa=?, Gia=? WHERE MaDV=?";
    String selectAll = "SELECT * FROM DichVu";
    String selectByID = "SELECT * FROM DichVu WHERE MaDV=?";

    public void insert(DichVu entity) {
        JdbcHelper.executeUpdate(insert,
                entity.getTenDV(),
                entity.getMoTa(),
                entity.getGia()
        );
    }

    public void delete(Integer key) {
        JdbcHelper.executeUpdate(delete, key);
    }

    public void update(DichVu entity) {
        JdbcHelper.executeUpdate(update,
                entity.getTenDV(),
                entity.getMoTa(),
                entity.getGia(),
                entity.getMaDV()
        );
    }

    public List<DichVu> selectAll() {
        return this.selectBySql(selectAll);
    }

    public DichVu selectByID(Integer key) {
        List<DichVu> list = this.selectBySql(selectByID, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    protected List<DichVu> selectBySql(String sql, Object... args) {
        List<DichVu> list = new ArrayList<DichVu>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                DichVu entity = new DichVu();
                entity.setMaDV(rs.getInt("MaDV"));
                entity.setTenDV(rs.getString("TenDV"));
                entity.setMoTa(rs.getString("MoTa"));
                entity.setGia(rs.getDouble("Gia"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<DichVu> selectByKeyword(String keyword) {
        String sql = "select * from DichVu where TenDV like ? or Gia like ? or MaDV like ?";
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
    }
}
