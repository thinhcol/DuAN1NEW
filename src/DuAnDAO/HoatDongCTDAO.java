/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuAnDAO;

import Entity.DichVuCT;
import Entity.HoatDongCT;
import Helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class HoatDongCTDAO {
    String insert = "INSERT INTO HoatDongCT VALUES (?, ?, ?, ?)";
    String delete = "DELETE FROM HoatDongCT WHERE MaHDCT like ?";
    String update = "UPDATE HoatDongCT SET MaBN=?, MaHD=?, NgayTH=?, GhiChu = ? WHERE MaDV=?";
    String selectAll = "SELECT * FROM HoatDongCT order by NgayTH asc";
    String selectByID = "SELECT * FROM HoatDongCT WHERE MaHDCT=?";

    public void insert(HoatDongCT entity) {
        JdbcHelper.executeUpdate(insert,
                entity.getMaBN(),
                entity.getMaHD(),
                entity.getNgayThucHien(),
                entity.getGhiChu()
        );
    }

    public void delete(Integer key) {
        JdbcHelper.executeUpdate(delete, key);
    }

    public void update(HoatDongCT entity) {
        JdbcHelper.executeUpdate(update,
                entity.getMaBN(),
                entity.getMaHD(),
                entity.getNgayThucHien(),
                entity.getGhiChu(),
                entity.getMaHDCT()
        );
    }

    public List<HoatDongCT> selectAll() {
        return this.selectBySql(selectAll);
    }

    public HoatDongCT selectByID(Integer key) {
        List<HoatDongCT> list = this.selectBySql(selectByID, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    protected List<HoatDongCT> selectBySql(String sql, Object... args) {
        List<HoatDongCT> list = new ArrayList<HoatDongCT>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                HoatDongCT entity = new HoatDongCT();
                entity.setMaHDCT(rs.getInt("MaHDCT"));
                entity.setMaBN(rs.getInt("MaBN"));
                entity.setMaHD(rs.getInt("MaHD"));
                entity.setNgayThucHien(rs.getDate("NgayTH"));
                entity.setGhiChu(rs.getString("GhiChu"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<HoatDongCT> selectByMaHD(Integer maHD) {
        String sql = "select * from HoatDongCT where MaHD like ?";
        List<HoatDongCT> list = this.selectBySql(sql, maHD);
        return list;
    }
    public List<HoatDongCT> selectByMaBN1(Integer maBN) {
        String sql = "select * from HoatDongCT where MaBN like ?";
        List<HoatDongCT> list = this.selectBySql(sql, maBN);
        return list;
    }

//    public List<DichVuCT> selectByKeyword(String keyword) {
//        String sql = "select * from DichVuCT where MaDV like ? or MaBN like ? or NgayDK like ?";
//        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
//    }

    public List<HoatDongCT> selectByMaBNAndHD(int maBN, int maHD) {
        String sql = "select * from HoatDongCT where MaBN like ? and MaHD like ?";
        return this.selectBySql(sql, maBN, maHD);
    }
    
    public List<HoatDongCT> selectByMaBN(Integer key) {
        String selectByMaBN = "select * from HoatDongCT where MaBN = ? order by NgayTH asc";
        List<HoatDongCT> list = this.selectBySql(selectByMaBN, key);
        return list;
    }
}
