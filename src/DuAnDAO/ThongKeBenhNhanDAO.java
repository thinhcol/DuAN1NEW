/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuAnDAO;

import Entity.ThongKeBenhNhan;
import Entity.ThongKeDoanhThu;
import Helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ThongKeBenhNhanDAO {
     public ArrayList<ThongKeBenhNhan> getAllList(int nam) {
        ArrayList<ThongKeBenhNhan> nc = new ArrayList<>();
        ResultSet rs = null;
        try {
            String sql = "{call sp_ThongKeSoLuongBN (?)}";
            rs = JdbcHelper.executeQuery(sql, nam);
            while (rs.next()) {
                ThongKeBenhNhan tk = new ThongKeBenhNhan();
                tk.setTenDV(rs.getString("MaPhong"));
                tk.setSoluongBN(rs.getInt("Soluongbenhnhan"));
                nc.add(tk);
            }
            return nc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
      public List<Object[]> getBenhNhan(int nam) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_ThongKeSoLuongBN (?)}";
                rs = JdbcHelper.executeQuery(sql,nam);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("MaPhong"),
                        rs.getInt("Soluongbenhnhan")
                       
                    };
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
}
