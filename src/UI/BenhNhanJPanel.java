/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DuAnDAO.BenhNhanDAO;
import DuAnDAO.NgheDAO;
import DuAnDAO.PhongDAO;
import Entity.BenhAn;
import Entity.BenhNhan;
import Entity.Nghe;
import Entity.Phong;
import Helper.DateHelper;
import Helper.DialogHelper;
import Helper.ShareHelper;
import java.awt.Color;
import java.awt.Image;
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

    void load() {
        DefaultTableModel model = (DefaultTableModel) tblGridView.getModel();
        model.setRowCount(0);
        if (ShareHelper.isLogin()) {
            try {
                List<BenhNhan> list = dao.selectAll();
                for (BenhNhan bn : list) {
                    Object[] row = {
                        bn.getMaBN(),
                        bn.getMaPhong(),
                        bn.getMaNghe(),
                        bn.getHoTen(),
                        bn.isGioiTinh() ? "Nam" : "Nữ",
                        DateHelper.toString(bn.getNgayVT()),
                        bn.getThoiGianO(),
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
        BenhNhan model = getModel();
        try {
            dao.insert(model);
            this.load();
            this.clear();
            DialogHelper.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Thêm mới thất bại!");
            e.printStackTrace();
        }
    }

    void update() {
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

    void delete() {
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
    }

    void clear() {
        this.setModel(new BenhNhan());
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        txtDiaChi = new javax.swing.JTextField();
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
        pnlLichSu = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.CardLayout());

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

        txtDiaChi.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N

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

        tblGridView.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        tblGridView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"01", "01", "01", "Nguyễn Văn Sơn", "Nam", "22/12/2019", null, "Đống Đa - Hà Nội", "372008705"},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã bệnh nhân", "Mã phòng", "Mã nghề", "Họ tên", "Giới tính", "Ngày vô trại", "Thời gian ở", "Địa chỉ", "CMND"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
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
            .addComponent(lblHinh, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnLichSu.setBackground(new java.awt.Color(255, 255, 255));
        btnLichSu.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        btnLichSu.setText("Lịch Sử");
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlQuanLyLichSuLayout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(btnLichSu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCMND)
                            .addComponent(lblDiaChi)
                            .addComponent(lblNgayVoTrai)
                            .addComponent(lblGioiTinh)
                            .addComponent(lblHoTen)
                            .addComponent(lblTenNghe)
                            .addComponent(lblSoPhong)
                            .addComponent(lblEmailThanNhan1))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboSoPhong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(cboTenNghe, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(pnlQuanLyLichSu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtHoTen, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtThoiGianO, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtCMND, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cboGioiTinh, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(datevotrai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(309, 309, 309)
                                        .addComponent(btnExcel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addComponent(jScrollPane7)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(0, 0, 0)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1376, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboSoPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSoPhong))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblTenNghe)
                                    .addComponent(cboTenNghe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblHoTen)
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblGioiTinh)
                                    .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblNgayVoTrai)
                                    .addComponent(datevotrai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblDiaChi)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblCMND)
                                    .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblEmailThanNhan1)
                                    .addComponent(txtThoiGianO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66)
                        .addComponent(pnlQuanLyLichSu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        setLayout.add(jPanel2, "card2");

        javax.swing.GroupLayout pnlLichSuLayout = new javax.swing.GroupLayout(pnlLichSu);
        pnlLichSu.setLayout(pnlLichSuLayout);
        pnlLichSuLayout.setHorizontalGroup(
            pnlLichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlLichSuLayout.setVerticalGroup(
            pnlLichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setLayout.add(pnlLichSu, "card3");

        add(setLayout, "card4");
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
        // TODO add your handling code here:
        LichSu();
        setLayout.removeAll();
        setLayout.add(pnlLichSu);
        setLayout.repaint();
        setLayout.revalidate();
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

    public void LichSu() {
        int id = (int) tblGridView.getValueAt(index, 0);
        pnlLichSu = new LichSuBenhNhanJPanel(id);
        setLayout.add(pnlLichSu);
        setLayout.removeAll();
        setLayout.validate();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnLichSu;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cboGioiTinh;
    private javax.swing.JComboBox cboSoPhong;
    private javax.swing.JComboBox cboTenNghe;
    private com.toedter.calendar.JDateChooser datevotrai;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane7;
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
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtThoiGianO;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
