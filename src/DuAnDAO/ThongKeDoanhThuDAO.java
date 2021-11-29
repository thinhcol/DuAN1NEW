/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuAnDAO;

import Entity.ThongKeDoanhThu;
import Helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;

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
}
