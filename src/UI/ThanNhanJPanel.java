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
import Helper.DateHelper;
import Helper.DialogHelper;
import Helper.ShareHelper;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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

    void load() {
        DefaultTableModel model = (DefaultTableModel) tblGridView.getModel();
        model.setRowCount(0);
        try {
            List<ThanNhan> list = dao.selectAll();
            for (ThanNhan bn : list) {
                Object[] row = {
                    bn.getMatn(),
                    bn.getPass(),
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

    void update() {
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

    void delete() {
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
        jScrollPane7 = new javax.swing.JScrollPane();
        tblGridView = new javax.swing.JTable();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        lblEmailThanNhan1 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtpass = new javax.swing.JPasswordField();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 153, 153));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("QUẢN LÝ THÂN NHÂN");

        lblHoTen.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        lblHoTen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_name_35px_1.png"))); // NOI18N
        lblHoTen.setText("Họ tên");

        txtHoten.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N

        lblGioiTinh.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        lblGioiTinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_gender_35px.png"))); // NOI18N
        lblGioiTinh.setText("Bệnh nhân");

        txtMaTN.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N

        lblDiaChi.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        lblDiaChi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_address_35px.png"))); // NOI18N
        lblDiaChi.setText("Mã thân nhân");

        lblCMND.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        lblCMND.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_term_35px_1.png"))); // NOI18N
        lblCMND.setText("Mật khẩu");

        cboTenBN.setEditable(true);
        cboTenBN.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        cboTenBN.setFocusable(false);
        cboTenBN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenBNActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(255, 255, 255));
        btnClear.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
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

        btnInsert.setBackground(new java.awt.Color(255, 255, 255));
        btnInsert.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
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

        btnUpdate.setBackground(new java.awt.Color(255, 255, 255));
        btnUpdate.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
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

        btnDelete.setBackground(new java.awt.Color(255, 255, 255));
        btnDelete.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
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
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_search_more_35px.png"))); // NOI18N

        txtTimKiem.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        txtTimKiem.setMaximumSize(new java.awt.Dimension(100, 100));

        tblGridView.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        tblGridView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"01", "01", "01", "Nguyễn Văn Sơn", "Nam"},
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

        lblEmailThanNhan1.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        lblEmailThanNhan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_new_message_35px.png"))); // NOI18N
        lblEmailThanNhan1.setText("Email");

        txtEmail.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCMND)
                            .addComponent(lblGioiTinh)
                            .addComponent(lblHoTen)
                            .addComponent(lblEmailThanNhan1)
                            .addComponent(lblDiaChi))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaTN)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                            .addComponent(cboTenBN, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtHoten)
                            .addComponent(txtpass))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
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
                                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1022, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 1236, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addGap(0, 0, 0)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGioiTinh)
                            .addComponent(cboTenBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDiaChi)
                            .addComponent(txtMaTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCMND)
                            .addComponent(txtpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHoTen)
                            .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEmailThanNhan1)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
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
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
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

    private void cboTenBNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenBNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTenBNActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cboTenBN;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblCMND;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblEmailThanNhan1;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JTable tblGridView;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoten;
    private javax.swing.JTextField txtMaTN;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JPasswordField txtpass;
    // End of variables declaration//GEN-END:variables
}
