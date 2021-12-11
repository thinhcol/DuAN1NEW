/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DuAnDAO.BenhNhanDAO;
import DuAnDAO.ThanNhanDAO;
import DuAnDAO.NgheDAO;
import DuAnDAO.PhongDAO;
import Entity.BenhAn;
import Entity.BenhNhan;
import Entity.ThanNhan;
import Entity.Nghe;
import Entity.Phong;
import Entity.ThanNhan;
import Helper.CheckHelper;
import Helper.DateHelper;
import Helper.DialogHelper;
import Helper.ShareHelper;
import java.awt.Color;
import static java.awt.Color.pink;
import static java.awt.Color.white;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
public class ThanNhanJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ThanNhanJPanel
     */
    public ThanNhanJPanel() {
        initComponents();
        this.load();
        this.setStatus(true);
        fillComboBox();

    }
    JFileChooser fileChooser = new JFileChooser();
    int index = 0;
    ThanNhanDAO dao = new ThanNhanDAO();
    BenhNhanDAO bndao = new BenhNhanDAO();
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
                XSSFSheet spreadsheet1 = workbook.createSheet("Danh sách thân nhân");
                XSSFRow rows1 = null;
                Cell cells1 = null;
                CellStyle cs = headerCellStyle();
                CellStyle csc = coCellStyle();
                rows1 = spreadsheet1.createRow((short) 1);
                rows1.setHeight((short) 500);
                cells1 = rows1.createCell(0, CellType.STRING);
                cells1.setCellValue("Thông tin thân nhân");
                rows1 = spreadsheet1.createRow((short) 2);
                rows1.setHeight((short) 500);
                cells1 = rows1.createCell(0, CellType.STRING);
                cells1.setCellValue("Mã thân nhân");
                cells1.setCellStyle(cs);
                cells1 = rows1.createCell(1, CellType.STRING);
                cells1.setCellValue("Mã bệnh nhân");
                cells1.setCellStyle(cs);
                cells1 = rows1.createCell(2, CellType.STRING);
                cells1.setCellValue("Họ và tên");
                cells1.setCellStyle(cs);
                cells1 = rows1.createCell(3, CellType.STRING);
                cells1.setCellValue("Email");
                cells1.setCellStyle(cs);

                List<ThanNhan> list = dao.selectAll();
                for (int i = 0; i < list.size(); i++) {
                    ThanNhan dt = list.get(i);
                    rows1 = spreadsheet1.createRow((short) 3 + i);
                    rows1.setHeight((short) 500);
                    cells1 = rows1.createCell(0);
                    cells1.setCellValue(dt.getMatn());
                    cells1.setCellStyle(csc);
                    cells1 = rows1.createCell(1);
                    cells1.setCellValue(dt.getMabn());
                    cells1.setCellStyle(csc);
                    cells1 = rows1.createCell(2);
                    cells1.setCellValue(dt.getHoten());
                    cells1.setCellStyle(csc);
                    cells1 = rows1.createCell(3);
                    cells1.setCellValue(dt.getEmail());
                    cells1.setCellStyle(csc);

                }
                for (int i = 0; i < 4; i++) {
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

    void load() {
        DefaultTableModel model = (DefaultTableModel) tblGridView.getModel();
        model.setRowCount(0);
        try {
            List<ThanNhan> list = dao.selectAll();
            for (ThanNhan bn : list) {
                Object[] row = {
                    bn.getMatn(),
                    ShareHelper.hidePass(bn.getPass()),
                    bn.getHoten(),
                    bn.getEmail(),
                    bn.getMabn()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
            e.printStackTrace();
        }
    }

    void insert() {
        if (CheckHelper.checkNullText(txtMaTN)
                && CheckHelper.checkNullPass(txtpass)
                && CheckHelper.checkNullText(txtHoten)
                && CheckHelper.checkNullText(txtEmail)) {
            if (CheckHelper.checkMaTN(txtMaTN)
                    && CheckHelper.checkPass(txtpass)
                    && CheckHelper.checkName(txtHoten)
                    && CheckHelper.checkEmail(txtEmail)) {
                if (checkTrungMa(txtMaTN)) {
                    ThanNhan model = getModel();
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
            }
        }
    }

    void update() {
        if (CheckHelper.checkNullText(txtMaTN)
                && CheckHelper.checkNullPass(txtpass)
                && CheckHelper.checkNullText(txtHoten)
                && CheckHelper.checkNullText(txtEmail)) {
            if (CheckHelper.checkMaTN(txtMaTN)
                    && CheckHelper.checkPass(txtpass)
                    && CheckHelper.checkName(txtHoten)
                    && CheckHelper.checkEmail(txtEmail)) {

                String matn = (String) tblGridView.getValueAt(this.index, 0);
                ThanNhan model = getModel();
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
    }

    void delete() {
        if (ShareHelper.isManager()) {
            DialogHelper.alert(this, "Bạn không có quyền xóa");
        } else {
            if (DialogHelper.confirm(this, "Bạn có muốn xóa hay không?")) {
                String matn = (String) tblGridView.getValueAt(this.index, 0);
                try {
                    dao.delete(matn);
                    this.load();
                    this.clear();
                    DialogHelper.alert(this, "Xóa thành công!");
                } catch (Exception e) {
                    DialogHelper.alert(this, "Xóa thất bại!");
                }
            }

        }
    }

    void clear() {
        this.setModel(new ThanNhan());
        this.setStatus(true);
    }

    void edit() {
        try {
            String matn = (String) tblGridView.getValueAt(this.index, 0);
            ThanNhan model = dao.selectByID(matn);
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
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboTenBN.getModel();
        model.removeAllElements();
        try {
            List<BenhNhan> list = bndao.selectAll();
            for (BenhNhan cd : list) {
                model.addElement(cd);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
            e.printStackTrace();
        }

    }

    void setModel(ThanNhan model) {
        txtHoten.setText(model.getHoten());
        cboTenBN.setToolTipText(String.valueOf(model.getMatn()));
        cboTenBN.getModel().setSelectedItem(bndao.selectByID(model.getMabn()));
        txtEmail.setText(model.getEmail());
        txtpass.setText(model.getPass());
        txtMaTN.setText(model.getMatn());
    }

    ThanNhan getModel() {

        ThanNhan model = new ThanNhan();
        BenhNhan bn = (BenhNhan) cboTenBN.getSelectedItem();
        model.setMabn(bn.getMaBN());

        model.setHoten(txtHoten.getText());
        model.setEmail(txtEmail.getText());
        model.setPass(txtpass.getText());
        model.setMatn(txtMaTN.getText());
        model.setMabn(bn.getMaBN());
        return model;
    }

    void setStatus(boolean insertable) {

        btnInsert.setEnabled(insertable);
        btnUpdate.setEnabled(!insertable);
        btnDelete.setEnabled(!insertable);
        boolean first = this.index > 0;
        boolean last = this.index < tblGridView.getRowCount() - 1;
        btnFirst.setEnabled(!insertable && first);
        btnPrev.setEnabled(!insertable && first);
        btnNext.setEnabled(!insertable && last);
        btnLast.setEnabled(!insertable && last);
    }

    public boolean checkTrungMa(JTextField txt) {
        txtMaTN.setBackground(white);
        if (dao.selectByID(txt.getText()) == null) {
            return true;
        } else {
            txt.setBackground(pink);
            DialogHelper.alert(this, "Mã thân nhân đã tồn tại.");
            return false;
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

        jLabel17 = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        txtHoten = new javax.swing.JTextField();
        lblGioiTinh = new javax.swing.JLabel();
        txtMaTN = new javax.swing.JTextField();
        lblDiaChi = new javax.swing.JLabel();
        lblCMND = new javax.swing.JLabel();
        cboTenBN = new javax.swing.JComboBox();
        btnClear = new javax.swing.JButton();
        btnInsert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        lblEmailThanNhan1 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtpass = new javax.swing.JPasswordField();
        btnExcel1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGridView = new rojeru_san.complementos.RSTableMetro();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Monospaced", 1, 48)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(50, 133, 253));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("QUẢN LÝ THÂN NHÂN");

        lblHoTen.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblHoTen.setForeground(new java.awt.Color(102, 102, 102));
        lblHoTen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/New Icon/hoTen.png"))); // NOI18N
        lblHoTen.setText("Họ tên");

        txtHoten.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtHoten.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(50, 133, 253)));

        lblGioiTinh.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblGioiTinh.setForeground(new java.awt.Color(102, 102, 102));
        lblGioiTinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/New Icon/maBN.png"))); // NOI18N
        lblGioiTinh.setText("Bệnh nhân");

        txtMaTN.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtMaTN.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(50, 133, 253)));

        lblDiaChi.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblDiaChi.setForeground(new java.awt.Color(102, 102, 102));
        lblDiaChi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/New Icon/maThanNhan.png"))); // NOI18N
        lblDiaChi.setText("Mã thân nhân");

        lblCMND.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblCMND.setForeground(new java.awt.Color(102, 102, 102));
        lblCMND.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/New Icon/matKhau.png"))); // NOI18N
        lblCMND.setText("Mật khẩu");

        cboTenBN.setEditable(true);
        cboTenBN.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cboTenBN.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(50, 133, 253)));
        cboTenBN.setFocusable(false);
        cboTenBN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenBNActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(50, 133, 253));
        btnClear.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("MỚI");
        btnClear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnClear.setContentAreaFilled(false);
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

        btnInsert.setBackground(new java.awt.Color(50, 133, 253));
        btnInsert.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnInsert.setForeground(new java.awt.Color(255, 255, 255));
        btnInsert.setText("THÊM");
        btnInsert.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnInsert.setContentAreaFilled(false);
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

        btnUpdate.setBackground(new java.awt.Color(50, 133, 253));
        btnUpdate.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("CẬP NHẬT");
        btnUpdate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnUpdate.setContentAreaFilled(false);
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

        btnDelete.setBackground(new java.awt.Color(50, 133, 253));
        btnDelete.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("XÓA");
        btnDelete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnDelete.setContentAreaFilled(false);
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
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/New Icon/timKiem.png"))); // NOI18N

        txtTimKiem.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(50, 133, 253)));
        txtTimKiem.setMaximumSize(new java.awt.Dimension(100, 100));

        btnNext.setBackground(new java.awt.Color(50, 133, 253));
        btnNext.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/New Icon/next.png"))); // NOI18N
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

        btnLast.setBackground(new java.awt.Color(50, 133, 253));
        btnLast.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnLast.setForeground(new java.awt.Color(255, 255, 255));
        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/New Icon/end.png"))); // NOI18N
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

        btnFirst.setBackground(new java.awt.Color(50, 133, 253));
        btnFirst.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnFirst.setForeground(new java.awt.Color(255, 255, 255));
        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/New Icon/start.png"))); // NOI18N
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

        btnPrev.setBackground(new java.awt.Color(50, 133, 253));
        btnPrev.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnPrev.setForeground(new java.awt.Color(255, 255, 255));
        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/New Icon/back.png"))); // NOI18N
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

        lblEmailThanNhan1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblEmailThanNhan1.setForeground(new java.awt.Color(102, 102, 102));
        lblEmailThanNhan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/New Icon/email.png"))); // NOI18N
        lblEmailThanNhan1.setText("Email");

        txtEmail.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(50, 133, 253)));

        txtpass.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtpass.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(50, 133, 253)));

        btnExcel1.setBackground(new java.awt.Color(50, 133, 253));
        btnExcel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnExcel1.setForeground(new java.awt.Color(255, 255, 255));
        btnExcel1.setText("Excel");
        btnExcel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnExcel1.setContentAreaFilled(false);
        btnExcel1.setOpaque(true);
        btnExcel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExcel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExcel1MouseExited(evt);
            }
        });

        tblGridView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã thân nhân", "Mật khẩu", "Họ tên", "Email", "Bệnh nhân"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGridView.setColorBackgoundHead(new java.awt.Color(51, 133, 253));
        tblGridView.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tblGridView.setColorBordeHead(new java.awt.Color(51, 133, 253));
        tblGridView.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tblGridView.setColorFilasForeground1(new java.awt.Color(51, 133, 253));
        tblGridView.setColorFilasForeground2(new java.awt.Color(51, 133, 253));
        tblGridView.setColorSelBackgound(new java.awt.Color(51, 133, 253));
        tblGridView.setFuenteFilas(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        tblGridView.setFuenteFilasSelect(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        tblGridView.setFuenteHead(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        tblGridView.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblGridView.setRowHeight(25);
        tblGridView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGridViewMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGridView);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblCMND)
                            .addComponent(lblGioiTinh)
                            .addComponent(lblHoTen)
                            .addComponent(lblEmailThanNhan1)
                            .addComponent(lblDiaChi)
                            .addComponent(cboTenBN, 0, 547, Short.MAX_VALUE)
                            .addComponent(txtMaTN)
                            .addComponent(txtpass)
                            .addComponent(txtEmail)
                            .addComponent(txtHoten))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(409, 409, 409)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnExcel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 1241, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addGap(0, 0, 0)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(588, 588, 588)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblGioiTinh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTenBN, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDiaChi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaTN, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCMND)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtpass, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblHoTen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblEmailThanNhan1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(155, 155, 155)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(207, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnClearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearMouseEntered
        btnClear.setBackground(new Color(0, 0, 153));
    }//GEN-LAST:event_btnClearMouseEntered

    private void btnClearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearMouseExited
        btnClear.setBackground(new Color(51, 133, 253));
    }//GEN-LAST:event_btnClearMouseExited

    private void btnInsertMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInsertMouseEntered
        btnInsert.setBackground(new Color(0, 0, 153));
    }//GEN-LAST:event_btnInsertMouseEntered

    private void btnInsertMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInsertMouseExited
        btnInsert.setBackground(new Color(51, 133, 253));
    }//GEN-LAST:event_btnInsertMouseExited

    private void btnUpdateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseEntered
        btnUpdate.setBackground(new Color(0, 0, 153));
    }//GEN-LAST:event_btnUpdateMouseEntered

    private void btnUpdateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseExited
        btnUpdate.setBackground(new Color(51, 133, 253));
    }//GEN-LAST:event_btnUpdateMouseExited

    private void btnDeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseEntered
        btnDelete.setBackground(new Color(0, 0, 153));
    }//GEN-LAST:event_btnDeleteMouseEntered

    private void btnDeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseExited
        btnDelete.setBackground(new Color(51, 133, 253));
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

    private void cboTenBNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenBNActionPerformed

    }//GEN-LAST:event_cboTenBNActionPerformed

    private void tblGridViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGridViewMouseClicked
        if (evt.getClickCount() == 2) {
            this.index = tblGridView.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();
                tblGridView.setRowSelectionInterval(index, index);
            }
        }
    }//GEN-LAST:event_tblGridViewMouseClicked

    private void btnExcel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcel1MouseEntered
        btnExcel1.setBackground(new Color(0, 0, 153));
    }//GEN-LAST:event_btnExcel1MouseEntered

    private void btnExcel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcel1MouseExited
        btnExcel1.setBackground(new Color(51, 133, 253));
    }//GEN-LAST:event_btnExcel1MouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExcel1;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cboTenBN;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCMND;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblEmailThanNhan1;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHoTen;
    private rojeru_san.complementos.RSTableMetro tblGridView;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoten;
    private javax.swing.JTextField txtMaTN;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JPasswordField txtpass;
    // End of variables declaration//GEN-END:variables
}
