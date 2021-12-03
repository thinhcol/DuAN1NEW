/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DuAnDAO.*;
import Entity.*;
import Helper.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author admin
 */
public class BenhNhanJPanel extends javax.swing.JPanel {

    /**
     * Creates new form BenhNhanJPanel
     */
    public BenhNhanJPanel() {
        initComponents();
        this.load();
        this.setStatus(true);
        fillComboBox();
        fillComboBoxNghe();
        this.fillCobTenBN();
        datevotrai.setDate(new Date());

        if (ShareHelper.isLogin1()) {
            btnInsert.setVisible(false);
            btnUpdate.setVisible(false);
            btnDelete.setVisible(false);
            btnClear.setVisible(false);
        } else {
            btnInsert.setVisible(true);
            btnUpdate.setVisible(true);
            btnDelete.setVisible(true);
        }
    }
    JFileChooser fileChooser = new JFileChooser();
    int index = 0;
    BenhNhanDAO dao = new BenhNhanDAO();
    NgheDAO nghedao = new NgheDAO();
    PhongDAO phongdao = new PhongDAO();
    LichSuDAO lsdao = new LichSuDAO();
    boolean isLoad = false;

    void load() {
        DefaultTableModel model = (DefaultTableModel) tblGridView.getModel();
        model.setRowCount(0);
        if (ShareHelper.isLogin()) {
            String timKiem = txtTimKiem.getText();
            try {
                List<BenhNhan> list = dao.selectByKeyword(timKiem, timKiem, timKiem);
                for (BenhNhan bn : list) {
                    Object[] row = {
                        bn.getMaBN(),
                        bn.getMaPhong(),
                        bn.getMaNghe(),
                        bn.getHoTen(),
                        bn.isGioiTinh() ? "Nam" : "Nữ",
                        DateHelper.toString(bn.getNgayVT()),
                        bn.getThoiGianO(),
                        isBNRaTrai(bn) ? "Đã ra trại" : "Đang trong trại",
                        bn.getDiaChi(),
                        bn.getCMND()

                    };
                    model.addRow(row);
                }
            } catch (Exception e) {
                DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
                e.printStackTrace();
            }
        } else if (ShareHelper.isLogin1()) {
            try {
                List<BenhNhan> list = dao.findlistById(ShareHelper.nguoidung.getMabn());
                for (BenhNhan bn : list) {
                    Object[] row = {
                        bn.getMaBN(),
                        bn.getMaPhong(),
                        bn.getMaNghe(),
                        bn.getHoTen(),
                        bn.isGioiTinh() ? "Nam" : "Nữ",
                        DateHelper.toString(bn.getNgayVT()),
                        bn.getThoiGianO(),
                        isBNRaTrai(bn) ? "Đã ra trại" : "Đang trong trại",
                        bn.getDiaChi(),
                        bn.getCMND()
                    };
                    model.addRow(row);
                }
            } catch (Exception e) {
                DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
                e.printStackTrace();
            }
        }

    }
    XSSFWorkbook workbook;

    private CellStyle headerCellStyle() {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THICK);
        cellStyle.setBorderLeft(BorderStyle.THICK);
        cellStyle.setBorderRight(BorderStyle.THICK);
        cellStyle.setBorderTop(BorderStyle.THICK);

        org.apache.poi.ss.usermodel.Font font = workbook.createFont();
        font.setBold(true);
        font.setFontName("Times New Roman");
        font.setFontHeight((short) 350);
        cellStyle.setFont(font);
        return cellStyle;
    }

    private CellStyle coCellStyle() {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THICK);
        cellStyle.setBorderRight(BorderStyle.THICK);
        cellStyle.setBorderTop(BorderStyle.THIN);
        org.apache.poi.ss.usermodel.Font font = workbook.createFont();
        font.setFontName("Times New Roman");
        font.setFontHeight((short) 250);
        cellStyle.setFont(font);
        return cellStyle;
    }

    void excel() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save as");
        FileNameExtensionFilter f = new FileNameExtensionFilter("xls", "xlsx");
        FileOutputStream out = null;
        chooser.setFileFilter(f);
        int excel = chooser.showSaveDialog(null);
        if (excel == JFileChooser.APPROVE_OPTION) {
            try {
                workbook = new XSSFWorkbook();
                XSSFSheet spreadsheet1 = workbook.createSheet("Danh sách Bệnh nhân");
                XSSFRow rows1 = null;
                Cell cells1 = null;
                CellStyle cs = headerCellStyle();
                CellStyle csc = coCellStyle();
                rows1 = spreadsheet1.createRow((short) 1);
                rows1.setHeight((short) 500);
                cells1 = rows1.createCell(0, CellType.STRING);
                cells1.setCellValue("Thông tin bệnh nhân");
                rows1 = spreadsheet1.createRow((short) 2);
                rows1.setHeight((short) 500);
                cells1 = rows1.createCell(0, CellType.STRING);
                cells1.setCellValue("Mã bệnh nhân");
                cells1.setCellStyle(cs);
                cells1 = rows1.createCell(1, CellType.STRING);
                cells1.setCellValue("Mã phòng");
                cells1.setCellStyle(cs);
                cells1 = rows1.createCell(2, CellType.STRING);
                cells1.setCellValue("Mã nghề");
                cells1.setCellStyle(cs);
                cells1 = rows1.createCell(3, CellType.STRING);
                cells1.setCellValue("Họ và tên");
                cells1.setCellStyle(cs);
                cells1 = rows1.createCell(4, CellType.STRING);
                cells1.setCellValue("Giới tính");
                cells1.setCellStyle(cs);
                cells1 = rows1.createCell(5, CellType.STRING);
                cells1.setCellValue("Ngày vô trại");
                cells1.setCellStyle(cs);
                cells1 = rows1.createCell(6, CellType.STRING);
                cells1.setCellValue("Thời gian ở");
                cells1.setCellStyle(cs);
                cells1 = rows1.createCell(7, CellType.STRING);
                cells1.setCellValue("Địa chỉ");
                cells1.setCellStyle(cs);
                cells1 = rows1.createCell(8, CellType.STRING);
                cells1.setCellValue("Chứng minh nhân dân");
                cells1.setCellStyle(cs);

                List<BenhNhan> list = dao.selectAll();
                for (int i = 0; i < list.size(); i++) {
                    BenhNhan dt = list.get(i);
                    rows1 = spreadsheet1.createRow((short) 3 + i);
                    rows1.setHeight((short) 500);
                    cells1 = rows1.createCell(0);
                    cells1.setCellValue(dt.getMaBN());
                    cells1.setCellStyle(csc);
                    cells1 = rows1.createCell(1);
                    cells1.setCellValue(dt.getMaPhong());
                    cells1.setCellStyle(csc);
                    cells1 = rows1.createCell(2);
                    cells1.setCellValue(dt.getMaNghe());
                    cells1.setCellStyle(csc);
                    cells1.setCellStyle(csc);
                    cells1 = rows1.createCell(3);
                    cells1.setCellValue(dt.getHoTen());
                    cells1.setCellStyle(csc);
                    cells1 = rows1.createCell(4);
                    cells1.setCellValue(dt.isGioiTinh() ? "Nam" : " Nữ");
                    cells1.setCellStyle(csc);
                    cells1 = rows1.createCell(5);
                    cells1.setCellValue(DateHelper.toString(dt.getNgayVT()));
                    cells1.setCellStyle(csc);
                    cells1 = rows1.createCell(6);
                    cells1.setCellValue(dt.getThoiGianO());
                    cells1.setCellStyle(csc);
                    cells1 = rows1.createCell(7);
                    cells1.setCellValue(dt.getDiaChi());
                    cells1.setCellStyle(csc);
                    cells1 = rows1.createCell(8);
                    cells1.setCellValue(dt.getCMND());
                    cells1.setCellStyle(csc);
                }
                for (int i = 0; i < 9; i++) {
                    spreadsheet1.autoSizeColumn(i);
                }
                out = new FileOutputStream(chooser.getSelectedFile() + ".xlsx");
                workbook.write(out);
                out.close();
                JOptionPane.showMessageDialog(this, "Xuất file thành công");
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ổ đĩa");
        }
    }

    void insert() {
        if (CheckHelper.checkNullText(txtHoTen)
                && CheckHelper.checkNullText(txtDiaChi)
                && CheckHelper.checkNullText(txtCMND)
                && CheckHelper.checkNullText(txtThoiGianO)
                && checkNullHinh()) {
            BenhNhan model = getModel();
            String cmnd = model.getCMND();
            BenhNhan benhNhan = dao.findlistByCMND(cmnd);
            if (benhNhan == null) {
                try {
                    dao.insert(model);
                    BenhNhan bn = dao.findlistByCMND(cmnd);
                    model.setMaBN(bn.getMaBN());
                    System.out.println(model.getMaBN());
                    insertLichSu(model);
                    this.load();
                    this.clear();
                    DialogHelper.alert(this, "Thêm mới thành công!");
                } catch (Exception e) {
                    DialogHelper.alert(this, "Thêm mới thất bại!");
                    e.printStackTrace();
                }
            } else {
                if (isBNRaTrai(benhNhan)) {
                    try {
                        model.setMaBN(benhNhan.getMaBN());
                        dao.update(model);
                        insertLichSu(model);
                        this.load();
                        this.clear();
                        DialogHelper.alert(this, "Thêm mới thành công!");
                    } catch (Exception e) {
                        DialogHelper.alert(this, "Thêm mới thất bại!");
                        e.printStackTrace();
                    }
                } else {
                    DialogHelper.alert(this, "Bệnh nhân đang trong trại");
                }
            }
        }
    }

    boolean isBNRaTrai(BenhNhan bn) {
        Date ngayVaoTrai = bn.getNgayVT();
        int soThangO = bn.getThoiGianO();
        Date hienTai = new Date();
        Date ngayRaTrai = DateHelper.addMonths(ngayVaoTrai, soThangO);
//        System.out.println("Ngày vào trại: " + ngayVaoTrai + "\n" + "Số tháng ở: " + soThangO + "\n" + "Ngày ra trại: " + ngayRaTrai);
        int soThang = DateHelper.subtrDateToMonth(hienTai, ngayRaTrai);
//        System.out.println("số tháng: " + soThang);
        if (soThang < 0) {
            return false;
        } else {
            return true;
        }
    }

    void insertLichSu(BenhNhan bn) {
        LichSu lichSu = new LichSu();
        lichSu.setMaBN(bn.getMaBN());
        lichSu.setHoTen(bn.getHoTen());
        lichSu.setNgayVT(bn.getNgayVT());
        lichSu.setNgayRT(DateHelper.addMonths(bn.getNgayVT(), 1));
        try {
            lsdao.insert(lichSu);
        } catch (Exception e) {
            System.out.println("Không thể thêm vào lịch sử");
            System.out.println(e);
        }
    }

    void update() {
        if (CheckHelper.checkNullText(txtHoTen)
                && CheckHelper.checkNullText(txtDiaChi)
                && CheckHelper.checkNullText(txtCMND)
                && CheckHelper.checkNullText(txtThoiGianO)
                && checkNullHinh()) {
            int mabn = (int) tblGridView.getValueAt(this.index, 0);
            BenhNhan model = getModel();
            model.setMaBN(mabn);
            try {
                dao.update(model);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Cập nhật thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Cập nhật thất bại!");
                e.printStackTrace();
            }
        }
    }

    void delete() {
        if (ShareHelper.isManager()) {
            if (DialogHelper.confirm(this, "Bạn có muốn xóa hay không?")) {
                int mabn = (Integer) tblGridView.getValueAt(this.index, 0);
                try {
                    dao.delete(mabn);
                    this.load();
                    this.clear();
                    DialogHelper.alert(this, "Xóa thành công!");
                } catch (Exception e) {
                    DialogHelper.alert(this, "Xóa thất bại!");
                }
            }
        }else{
            DialogHelper.alert(this, "Bạn không có quyền xóa");
        }
    }

    void clear() {
        this.setModel(new BenhNhan());
        cboSoPhong.setSelectedIndex(0);
        cboTenNghe.setSelectedIndex(0);
        this.setStatus(true);
    }

    void edit() {
        try {
            int mabn = (int) tblGridView.getValueAt(this.index, 0);
            BenhNhan model = dao.selectByID(mabn);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
                tblGridView.setRowSelectionInterval(index, index);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
            e.printStackTrace();
        }
    }

    void fillComboBox() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboSoPhong.getModel();
        model.removeAllElements();
        if (ShareHelper.isLogin()) {
            try {
                List<Phong> list = phongdao.select();
                for (Phong cd : list) {
                    model.addElement(cd);
                }
            } catch (Exception e) {
                DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
                e.printStackTrace();
            }
        } else if (ShareHelper.isLogin1()) {
            cboSoPhong.setEnabled(false);
        }

    }

    void fillComboBoxNghe() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboTenNghe.getModel();
        model.removeAllElements();
        if (ShareHelper.isLogin()) {
            try {
                List<Nghe> list = nghedao.select();
                for (Nghe cd : list) {
                    model.addElement(cd);
                }
            } catch (Exception e) {
                DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
                e.printStackTrace();
            }
        } else if (ShareHelper.isLogin1()) {
            cboTenNghe.setEnabled(false);
        }
    }

    void setModel(BenhNhan model) {
        cboSoPhong.setToolTipText(String.valueOf(model.getMaBN()));
        cboSoPhong.getModel().setSelectedItem(phongdao.findById(model.getMaPhong()));
        cboTenNghe.setToolTipText(String.valueOf(model.getMaBN()));
        cboTenNghe.getModel().setSelectedItem(nghedao.findById(model.getMaNghe()));
        txtHoTen.setText(model.getHoTen());
        cboGioiTinh.setSelectedIndex(model.isGioiTinh() ? 0 : 1);
        try {
            Date nsinh = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(DateHelper.toString(model.getNgayVT()));
            datevotrai.setDate(nsinh);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        txtDiaChi.setText(model.getDiaChi());
        txtCMND.setText(model.getCMND());
        lblHinh.setToolTipText(model.getHinh());
        if (model.getHinh() != null) {
            lblHinh.setToolTipText(model.getHinh());
            ImageIcon icon = ShareHelper.read(model.getHinh());
            Image im = icon.getImage();
            Image image = im.getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), im.SCALE_SMOOTH);
            ImageIcon icon1 = new ImageIcon(image);
            lblHinh.setIcon(icon1);
        }
        txtThoiGianO.setText(String.valueOf(model.getThoiGianO()));
    }

    BenhNhan getModel() {
        SimpleDateFormat ngay = new SimpleDateFormat("dd/MM/yyyy");
        String day = ngay.format(datevotrai.getDate());
        BenhNhan model = new BenhNhan();
        Phong phong = (Phong) cboSoPhong.getSelectedItem();
        model.setMaPhong(phong.getMaPhong());
        Nghe nghe = (Nghe) cboTenNghe.getSelectedItem();
        model.setMaNghe(nghe.getManghe());
        model.setHoTen(txtHoTen.getText());
        model.setGioiTinh(cboGioiTinh.getSelectedIndex() == 0);
        model.setNgayVT(DateHelper.toDate(day));
        model.setDiaChi(txtDiaChi.getText());
        model.setCMND(txtCMND.getText());
        model.setThoiGianO(Integer.valueOf(txtThoiGianO.getText()));
        model.setHinh(lblHinh.getToolTipText());
        return model;
    }

    void setStatus(boolean insertable) {
        txtHoTen.setEditable(insertable);
        btnInsert.setEnabled(insertable);
        btnUpdate.setEnabled(!insertable);
        btnDelete.setEnabled(!insertable);
        boolean first = this.index > 0;
        boolean last = this.index < tblGridView.getRowCount() - 1;
        btnFirst.setEnabled(!insertable && first);
        btnPrev.setEnabled(!insertable && first);
        btnNext.setEnabled(!insertable && last);
        btnLast.setEnabled(!insertable && last);
        btnLichSu.setEnabled(!insertable);
    }

    void selectImage() {
        int show = fileChooser.showOpenDialog(null);
        if (show == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            ShareHelper.save(file);
            ImageIcon icon = ShareHelper.read(file.getName());
            Image im = icon.getImage();
            Image image = im.getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), im.SCALE_SMOOTH);
            ImageIcon icon1 = new ImageIcon(image);
            lblHinh.setIcon(icon1);
            lblHinh.setToolTipText(file.getName());
        }
    }

    private void viewLichSu() {
        CardLayout cardLayout = (CardLayout) setLayout.getLayout();
        cardLayout.next(setLayout);
    }

    void fillTableBenhAn() {
        DefaultTableModel model = (DefaultTableModel) tblGridView1.getModel();
        model.setRowCount(0);
        try {
            BenhNhan bn = (BenhNhan) cboTenBN.getSelectedItem();
            int idBN = bn.getMaBN();
//            List<LichSu> list = lsdao.selectAll();
            List<LichSu> list = lsdao.selctById(idBN);
            for (LichSu ls : list) {
                Object[] row = {
                    ls.getMaBN(),
                    ls.getHoTen(),
                    DateHelper.toString(ls.getNgayVT()),
                    DateHelper.toString(ls.getNgayRT()),};
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
            e.printStackTrace();
        }
    }

    void fillCobTenBN() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboTenBN.getModel();
        model.removeAllElements();
        List<BenhNhan> list = dao.selectAll();
        for (BenhNhan bn : list) {
            model.addElement(bn);
        }
        isLoad = true;
    }

    void selectCbo() {
        if (isLoad) {
            this.fillTableBenhAn();
        }
    }

//    private boolean validateData() {
//        if (CheckHelper.checkNullText(txtHoTen)) {
//            return false;
//        } else if (CheckHelper.checkNullText(txtDiaChi)) {
//            return false;
//        } else if (CheckHelper.checkNullText(txtCMND)) {
//            return false;
//        } else if (CheckHelper.checkCMND(txtCMND)) {
//            return false;
//        } else if (CheckHelper.checkNullText(txtThoiGianO)) {
//            return false;
//        } else if (CheckHelper.checkThoiGianO(txtThoiGianO)) {
//            return false;
//        } else if (checkNullHinh()) {
//            return false;
//        } else {
//            return true;
//        }
//
//    }
    public boolean checkNullHinh() {
        if (lblHinh.getToolTipText() != null) {
            return true;
        } else {
            DialogHelper.alert(this, "Không được để trống hình.");
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        lblSoPhong = new javax.swing.JLabel();
        lblTenNghe = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        lblGioiTinh = new javax.swing.JLabel();
        lblNgayVoTrai = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        lblCMND = new javax.swing.JLabel();
        txtCMND = new javax.swing.JTextField();
        cboGioiTinh = new javax.swing.JComboBox();
        btnClear = new javax.swing.JButton();
        btnInsert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblGridView = new javax.swing.JTable();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        cboSoPhong = new javax.swing.JComboBox();
        cboTenNghe = new javax.swing.JComboBox();
        datevotrai = new com.toedter.calendar.JDateChooser();
        lblEmailThanNhan1 = new javax.swing.JLabel();
        txtThoiGianO = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lblHinh = new javax.swing.JLabel();
        pnlQuanLyLichSu = new javax.swing.JPanel();
        btnLichSu = new javax.swing.JButton();
        btnExcel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        pnlLichSu = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblGridView1 = new javax.swing.JTable();
        btnLichSu1 = new javax.swing.JButton();
        cboTenBN = new javax.swing.JComboBox();

        setBackground(new java.awt.Color(255, 255, 255));

        setLayout.setLayout(new java.awt.CardLayout());

        jLabel17.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 153, 153));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("QUẢN LÝ BỆNH NHÂN");

        lblSoPhong.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        lblSoPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_home_35px.png"))); // NOI18N
        lblSoPhong.setText("Số phòng");

        lblTenNghe.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        lblTenNghe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_work_35px.png"))); // NOI18N
        lblTenNghe.setText("Tên nghề");

        lblHoTen.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        lblHoTen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_name_35px_1.png"))); // NOI18N
        lblHoTen.setText("Họ tên");

        txtHoTen.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N

        lblGioiTinh.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        lblGioiTinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_gender_35px.png"))); // NOI18N
        lblGioiTinh.setText("Giới tính");

        lblNgayVoTrai.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        lblNgayVoTrai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_calendar_8_35px.png"))); // NOI18N
        lblNgayVoTrai.setText("Ngày vô trại");

        lblDiaChi.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        lblDiaChi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_address_35px.png"))); // NOI18N
        lblDiaChi.setText("Địa chỉ");

        lblCMND.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        lblCMND.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_term_35px_1.png"))); // NOI18N
        lblCMND.setText("CMND");

        txtCMND.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N

        cboGioiTinh.setEditable(true);
        cboGioiTinh.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
        cboGioiTinh.setFocusable(false);
        cboGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGioiTinhActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(255, 255, 255));
        btnClear.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        btnClear.setText("MỚI");
        btnClear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnClear.setContentAreaFilled(false);
        btnClear.setFocusPainted(false);
        btnClear.setOpaque(true);
        btnClear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnClearMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnClearMouseExited(evt);
            }
        });
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnInsert.setBackground(new java.awt.Color(255, 255, 255));
        btnInsert.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        btnInsert.setText("THÊM");
        btnInsert.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnInsert.setContentAreaFilled(false);
        btnInsert.setFocusable(false);
        btnInsert.setOpaque(true);
        btnInsert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnInsertMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInsertMouseExited(evt);
            }
        });
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(255, 255, 255));
        btnUpdate.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        btnUpdate.setText("CẬP NHẬT");
        btnUpdate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnUpdate.setContentAreaFilled(false);
        btnUpdate.setFocusable(false);
        btnUpdate.setOpaque(true);
        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUpdateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUpdateMouseExited(evt);
            }
        });
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 255, 255));
        btnDelete.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        btnDelete.setText("XÓA");
        btnDelete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnDelete.setContentAreaFilled(false);
        btnDelete.setFocusable(false);
        btnDelete.setOpaque(true);
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDeleteMouseExited(evt);
            }
        });
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_search_more_35px.png"))); // NOI18N

        txtTimKiem.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        txtTimKiem.setMaximumSize(new java.awt.Dimension(100, 100));
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        tblGridView.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        tblGridView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"01", "01", "01", "Nguyễn Văn Sơn", "Nam", "22/12/2019", null, null, "Đống Đa - Hà Nội", "372008705"},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã bệnh nhân", "Mã phòng", "Mã nghề", "Họ tên", "Giới tính", "Ngày vô trại", "Thời gian ở", "Trạng thái", "Địa chỉ", "CMND"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGridView.setFocusable(false);
        tblGridView.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblGridView.setRowHeight(30);
        tblGridView.setSelectionBackground(new java.awt.Color(245, 165, 165));
        tblGridView.setShowVerticalLines(false);
        tblGridView.getTableHeader().setReorderingAllowed(false);
        tblGridView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGridViewMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblGridView);

        btnNext.setBackground(new java.awt.Color(255, 255, 255));
        btnNext.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_right_35px.png"))); // NOI18N
        btnNext.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnNext.setContentAreaFilled(false);
        btnNext.setOpaque(true);
        btnNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNextMouseExited(evt);
            }
        });
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setBackground(new java.awt.Color(255, 255, 255));
        btnLast.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_last_1_35px.png"))); // NOI18N
        btnLast.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnLast.setContentAreaFilled(false);
        btnLast.setOpaque(true);
        btnLast.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLastMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLastMouseExited(evt);
            }
        });
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnFirst.setBackground(new java.awt.Color(255, 255, 255));
        btnFirst.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_first_1_35px.png"))); // NOI18N
        btnFirst.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnFirst.setContentAreaFilled(false);
        btnFirst.setOpaque(true);
        btnFirst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFirstMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFirstMouseExited(evt);
            }
        });
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrev.setBackground(new java.awt.Color(255, 255, 255));
        btnPrev.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_left_35px.png"))); // NOI18N
        btnPrev.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnPrev.setContentAreaFilled(false);
        btnPrev.setOpaque(true);
        btnPrev.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPrevMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPrevMouseExited(evt);
            }
        });
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        cboSoPhong.setEditable(true);
        cboSoPhong.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        cboSoPhong.setFocusable(false);

        cboTenNghe.setEditable(true);
        cboTenNghe.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        cboTenNghe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bác sĩ", "Kỹ sư", "Giám đốc", "Thợ điện", "Thợ sửa ống nước" }));
        cboTenNghe.setFocusable(false);

        datevotrai.setBackground(new java.awt.Color(255, 255, 255));
        datevotrai.setDateFormatString("dd/MM/yyyy");

        lblEmailThanNhan1.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        lblEmailThanNhan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_new_message_35px.png"))); // NOI18N
        lblEmailThanNhan1.setText("Thời gian ở");

        txtThoiGianO.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblHinh.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblHinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnLichSu.setBackground(new java.awt.Color(255, 255, 255));
        btnLichSu.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        btnLichSu.setText("Xem Lịch Sử Bệnh Nhân");
        btnLichSu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnLichSu.setContentAreaFilled(false);
        btnLichSu.setFocusPainted(false);
        btnLichSu.setOpaque(true);
        btnLichSu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLichSuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLichSuMouseExited(evt);
            }
        });
        btnLichSu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLichSuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlQuanLyLichSuLayout = new javax.swing.GroupLayout(pnlQuanLyLichSu);
        pnlQuanLyLichSu.setLayout(pnlQuanLyLichSuLayout);
        pnlQuanLyLichSuLayout.setHorizontalGroup(
            pnlQuanLyLichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnLichSu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlQuanLyLichSuLayout.setVerticalGroup(
            pnlQuanLyLichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuanLyLichSuLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnLichSu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        btnExcel.setBackground(new java.awt.Color(255, 255, 255));
        btnExcel.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        btnExcel.setText("Excel");
        btnExcel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnExcel.setContentAreaFilled(false);
        btnExcel.setOpaque(true);
        btnExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExcelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExcelMouseExited(evt);
            }
        });
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        jScrollPane1.setViewportView(txtDiaChi);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 1376, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(1028, 1028, 1028)
                .addComponent(jLabel16)
                .addGap(5, 5, 5)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSoPhong)
                    .addComponent(lblTenNghe)
                    .addComponent(lblHoTen)
                    .addComponent(lblGioiTinh)
                    .addComponent(lblNgayVoTrai)
                    .addComponent(lblCMND)
                    .addComponent(lblEmailThanNhan1)
                    .addComponent(lblDiaChi))
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(datevotrai, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboSoPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboTenNghe, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cboGioiTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                        .addComponent(txtThoiGianO, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtCMND, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(44, 44, 44)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(pnlQuanLyLichSu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(306, 306, 306)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel17)
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblSoPhong)
                        .addGap(25, 25, 25)
                        .addComponent(lblTenNghe)
                        .addGap(50, 50, 50)
                        .addComponent(lblHoTen)
                        .addGap(25, 25, 25)
                        .addComponent(lblGioiTinh)
                        .addGap(25, 25, 25)
                        .addComponent(lblNgayVoTrai)
                        .addGap(25, 25, 25)
                        .addComponent(lblCMND)
                        .addGap(25, 25, 25)
                        .addComponent(lblEmailThanNhan1)
                        .addGap(25, 25, 25)
                        .addComponent(lblDiaChi))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(cboSoPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(cboTenNghe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(datevotrai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(txtThoiGianO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlQuanLyLichSu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(13, 13, 13)
                                .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );

        setLayout.add(jPanel2, "card2");

        jLabel18.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 153, 153));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Lịch sử bệnh nhân");

        tblGridView1.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        tblGridView1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã bệnh nhân", "Họ Tên", "Ngày Vào Trại", "Ngày Ra Trại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGridView1.setFocusable(false);
        tblGridView1.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblGridView1.setRowHeight(30);
        tblGridView1.setSelectionBackground(new java.awt.Color(245, 165, 165));
        tblGridView1.setShowVerticalLines(false);
        tblGridView1.getTableHeader().setReorderingAllowed(false);
        tblGridView1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGridView1MouseClicked(evt);
            }
        });
        tblGridView1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblGridView1KeyPressed(evt);
            }
        });
        jScrollPane8.setViewportView(tblGridView1);

        btnLichSu1.setBackground(new java.awt.Color(255, 255, 255));
        btnLichSu1.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        btnLichSu1.setText("Quay lại danh sách bệnh nhân");
        btnLichSu1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnLichSu1.setContentAreaFilled(false);
        btnLichSu1.setFocusPainted(false);
        btnLichSu1.setOpaque(true);
        btnLichSu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLichSu1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLichSu1MouseExited(evt);
            }
        });
        btnLichSu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLichSu1ActionPerformed(evt);
            }
        });

        cboTenBN.setEditable(true);
        cboTenBN.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        cboTenBN.setFocusable(false);
        cboTenBN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenBNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLichSuLayout = new javax.swing.GroupLayout(pnlLichSu);
        pnlLichSu.setLayout(pnlLichSuLayout);
        pnlLichSuLayout.setHorizontalGroup(
            pnlLichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLichSuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 1376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pnlLichSuLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane8)
                .addGap(18, 18, 18)
                .addGroup(pnlLichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLichSu1, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                    .addComponent(cboTenBN, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );
        pnlLichSuLayout.setVerticalGroup(
            pnlLichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLichSuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addGap(50, 50, 50)
                .addGroup(pnlLichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlLichSuLayout.createSequentialGroup()
                        .addComponent(cboTenBN, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnLichSu1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(300, Short.MAX_VALUE))
        );

        setLayout.add(pnlLichSu, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(setLayout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(setLayout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnClearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearMouseEntered
        btnClear.setBackground(new Color(229, 229, 229));
        btnClear.setForeground(new Color(0, 204, 106));
    }//GEN-LAST:event_btnClearMouseEntered

    private void btnClearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearMouseExited
        btnClear.setBackground(new Color(255, 255, 255));
        btnClear.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_btnClearMouseExited

    private void btnInsertMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInsertMouseEntered
        btnInsert.setBackground(new Color(229, 229, 229));
        btnInsert.setForeground(new Color(0, 204, 106));
    }//GEN-LAST:event_btnInsertMouseEntered

    private void btnInsertMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInsertMouseExited
        btnInsert.setBackground(new Color(255, 255, 255));
        btnInsert.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_btnInsertMouseExited

    private void btnUpdateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseEntered
        btnUpdate.setBackground(new Color(229, 229, 229));
        btnUpdate.setForeground(new Color(0, 204, 106));
    }//GEN-LAST:event_btnUpdateMouseEntered

    private void btnUpdateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseExited
        btnUpdate.setBackground(new Color(255, 255, 255));
        btnUpdate.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_btnUpdateMouseExited

    private void btnDeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseEntered
        btnDelete.setBackground(new Color(229, 229, 229));
        btnDelete.setForeground(new Color(0, 204, 106));
    }//GEN-LAST:event_btnDeleteMouseEntered

    private void btnDeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseExited
        btnDelete.setBackground(new Color(255, 255, 255));
        btnDelete.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_btnDeleteMouseExited

    private void btnNextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextMouseEntered

    private void btnNextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextMouseExited

    private void btnLastMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLastMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLastMouseEntered

    private void btnLastMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLastMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLastMouseExited

    private void btnFirstMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFirstMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFirstMouseEntered

    private void btnFirstMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFirstMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFirstMouseExited

    private void btnPrevMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrevMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrevMouseEntered

    private void btnPrevMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrevMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrevMouseExited

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:q
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        this.index = 0;
        this.edit();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        this.index--;
        this.edit();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        this.index++;
        this.edit();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        this.index = tblGridView.getRowCount() - 1;
        this.edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void tblGridViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGridViewMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.index = tblGridView.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();
                tblGridView.setRowSelectionInterval(index, index);
            }
        }
    }//GEN-LAST:event_tblGridViewMouseClicked

    private void cboGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGioiTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboGioiTinhActionPerformed

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        // TODO add your handling code here:
        selectImage();
    }//GEN-LAST:event_lblHinhMouseClicked

    private void btnLichSuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLichSuMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLichSuMouseEntered

    private void btnLichSuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLichSuMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLichSuMouseExited

    private void btnLichSuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLichSuActionPerformed
        this.viewLichSu();
        BenhNhan bn = dao.selectByID((int) tblGridView.getValueAt(this.index, 0));
        cboTenBN.getModel().setSelectedItem(bn);
        selectCbo();
    }//GEN-LAST:event_btnLichSuActionPerformed

    private void btnExcelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExcelMouseEntered

    private void btnExcelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExcelMouseExited

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        // TODO add your handling code here:
        excel();
    }//GEN-LAST:event_btnExcelActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        this.load();
        this.clear();
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void tblGridView1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGridView1MouseClicked
        // TODO add your handling code here:
        load();
    }//GEN-LAST:event_tblGridView1MouseClicked

    private void tblGridView1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblGridView1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblGridView1KeyPressed

    private void btnLichSu1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLichSu1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLichSu1MouseEntered

    private void btnLichSu1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLichSu1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLichSu1MouseExited

    private void btnLichSu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLichSu1ActionPerformed
        this.viewLichSu();
    }//GEN-LAST:event_btnLichSu1ActionPerformed

    private void cboTenBNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenBNActionPerformed
        this.selectCbo();
    }//GEN-LAST:event_cboTenBNActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnLichSu;
    private javax.swing.JButton btnLichSu1;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cboGioiTinh;
    private javax.swing.JComboBox cboSoPhong;
    private javax.swing.JComboBox cboTenBN;
    private javax.swing.JComboBox cboTenNghe;
    private com.toedter.calendar.JDateChooser datevotrai;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lblCMND;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblEmailThanNhan1;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblNgayVoTrai;
    private javax.swing.JLabel lblSoPhong;
    private javax.swing.JLabel lblTenNghe;
    private javax.swing.JPanel pnlLichSu;
    private javax.swing.JPanel pnlQuanLyLichSu;
    private javax.swing.JPanel setLayout;
    private javax.swing.JTable tblGridView;
    private javax.swing.JTable tblGridView1;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtThoiGianO;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

}
