/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuAnDAO;

import Entity.ThongKeDichVu;
import Entity.ThongKeDoanhThu;
import Helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class ThongKeDichVuDAO {
     public ArrayList<ThongKeDichVu> getAllList(int nam) {
        ArrayList<ThongKeDichVu> nc = new ArrayList<>();
        ResultSet rs = null;
        try {
            String sql = "{call sp_ThongKeDichVu (?)}";
            rs = JdbcHelper.executeQuery(sql, nam);
            while (rs.next()) {
                ThongKeDichVu tk = new ThongKeDichVu();
                tk.setTenDV(rs.getString("TenDV"));
                tk.setSoluongBN(rs.getInt("Soluongbenhnhan"));
                nc.add(tk);
            }
            return nc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
