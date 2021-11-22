/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuAnDAO;

import Entity.DichVuCT;
import Helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class DichVuCTDAO {

    String insert = "INSERT INTO DichVuCT VALUES (?, ?, ?, ?)";
    String delete = "DELETE FROM DichVuCT WHERE MaDVCT like ?";
    String update = "UPDATE DichVuCT SET MaDV=?, MaBN=?, NgayDK = ?, GhiChu=? , WHERE MaDVCT =?";
    String selectAll = "SELECT * FROM DichVuCT";
    String selectByID = "SELECT * FROM DichVuCT WHERE MaDVCT=?";

    public void insert(DichVuCT entity) {
        JdbcHelper.executeUpdate(insert,
                entity.getMaDV(),
                entity.getMaBN(),
                entity.getNgayDK(),
                entity.getGhiChu()
        );
    }
    
    public void delete(Integer key) {
        JdbcHelper.executeUpdate(delete, key);
    }

    public void update(DichVuCT entity) {
        JdbcHelper.executeUpdate(update,
                entity.getMaDV(),
                entity.getMaBN(),
                entity.getNgayDK(),
                entity.getGhiChu(),
                entity.getMaDVCT()
        );
    }

    public List<DichVuCT> selectAll() {
        return this.selectBySql(selectAll);
    }

    public DichVuCT selectByID(Integer key) {
        List<DichVuCT> list = this.selectBySql(selectByID, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    protected List<DichVuCT> selectBySql(String sql, Object... args) {
        List<DichVuCT> list = new ArrayList<DichVuCT>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                DichVuCT model = new DichVuCT();
                model.setMaDVCT(rs.getInt("MaDVCT"));
                model.setMaDV(rs.getInt("MaDV"));
                model.setMaBN(rs.getInt("MaBN"));
                model.setNgayDK(rs.getDate("NgayDK"));
                model.setGhiChu(rs.getString("GhiChu"));
                list.add(model);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<DichVuCT> selectByMaBN(Integer key) {
        String selectByMaBN = "select * from DichVuCT where MaBN = ? order by NgayDK asc";
        List<DichVuCT> list = this.selectBySql(selectByMaBN, key);
        return list;
    }

    public List<DichVuCT> selectByMaBNAndNgayDK(Integer key, Date ngayBD, Date ngayKT) {
        String selectByMaBN = "select * from DichVuCT where MaBN = ? and NgayDK>= ? and NgayDK <= ? order by NgayDK asc";
        List<DichVuCT> list = this.selectBySql(selectByMaBN, key, ngayBD, ngayKT);
        return list;
    }
}
