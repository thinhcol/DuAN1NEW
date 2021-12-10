/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuAnDAO;

import Entity.ThongKeDoanhThu;
import Helper.FormatHepler;
import Helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ThongKeDoanhThuDAO {
    public ArrayList<ThongKeDoanhThu> getAllList(int nam) {
        ArrayList<ThongKeDoanhThu> nc = new ArrayList<>();
        ResultSet rs = null;
        try {
            String sql = "{call sp_ThongKeDoanhThu (?)}";
            rs = JdbcHelper.executeQuery(sql, nam);
            while (rs.next()) {
                ThongKeDoanhThu tk = new ThongKeDoanhThu();
                tk.setPhong(rs.getString("Phong"));
                tk.setSoluongbn(rs.getInt("Soluongbenhnhan"));
                tk.setSum(rs.getDouble("TongTien"));
                tk.setMin(rs.getDouble("TienNhoNhat"));
                tk.setMax(rs.getDouble("TienLonNhat"));
                nc.add(tk);
            }
            return nc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
     public List<Object[]> getDoanhThu(int nam) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_ThongKeDoanhThu (?)}";
                rs = JdbcHelper.executeQuery(sql, nam);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("Phong"),
                        rs.getInt("Soluongbenhnhan"),
                        FormatHepler.formatMoney(rs.getDouble("TongTien")),
                        FormatHepler.formatMoney(rs.getDouble("TienNhoNhat")),
                        FormatHepler.formatMoney(rs.getDouble("TienLonNhat"))
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
