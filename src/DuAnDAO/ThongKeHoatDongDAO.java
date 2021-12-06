/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuAnDAO;

import Entity.ThongKeHoatDong;
import Helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ThongKeHoatDongDAO {
    public ArrayList<ThongKeHoatDong> getAllList(int nam) {
        ArrayList<ThongKeHoatDong> nc = new ArrayList<>();
        ResultSet rs = null;
        try {
            String sql = "{call sp_ThongKeHoatDong(?)}";
            rs = JdbcHelper.executeQuery(sql,nam);
            while (rs.next()) {
                ThongKeHoatDong tk = new ThongKeHoatDong();
               tk.setTenhd(rs.getString("TenHD"));
               tk.setSoluong(rs.getInt("SoLuongBenhNhan"));
                nc.add(tk);
            }
            return nc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
     public List<Object[]> getHD(int nam) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_ThongKeHoatDong(?)}";
                rs = JdbcHelper.executeQuery(sql,nam);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("TenHD"),
                        rs.getInt("Soluongbenhnhan"),
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
