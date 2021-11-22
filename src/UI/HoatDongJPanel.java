/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DuAnDAO.HoatDongDAO;
import Entity.HoatDong;
import Helper.DialogHelper;
import Helper.ShareHelper;
import java.awt.Color;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class HoatDongJPanel extends javax.swing.JPanel {

    /**
     * Creates new form HoatDongJPanel
     */
    public HoatDongJPanel() {
        initComponents();
        this.load();
        this.setStatus(true);
          if (ShareHelper.isLogin1()) {
            btnInsert.setVisible(false);
            btnUpdate.setVisible(false);
            btnDelete.setVisible(false);
            btnNew.setVisible(false);
        } else {
            btnInsert.setVisible(true);
            btnUpdate.setVisible(true);
            btnDelete.setVisible(true);
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

        jLabel65 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel68 = new javax.swing.JLabel();
        jTextField40 = new javax.swing.JTextField();
        btnNew = new javax.swing.JButton();
        btnInsert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblHoatDong = new javax.swing.JTable();
        jLabel70 = new javax.swing.JLabel();
        txtTenHD = new javax.swing.JTextField();
        jScrollPane15 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        btnDelete = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1400, 875));

        jLabel65.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 153, 153));
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel65.setText("QUẢN LÝ HOẠT ĐỘNG");

        jLabel67.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        jLabel67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_paid_bill_35px.png"))); // NOI18N
        jLabel67.setText("Tên hoạt động");

        jLabel68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_search_more_35px.png"))); // NOI18N

        jTextField40.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N

        btnNew.setBackground(new java.awt.Color(255, 255, 255));
        btnNew.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        btnNew.setText("MỚI");
        btnNew.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnNew.setContentAreaFilled(false);
        btnNew.setOpaque(true);
        btnNew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNewMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNewMouseExited(evt);
            }
        });
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
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

        tblHoatDong.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        tblHoatDong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã hoạt động", "Tên hoạt động", "Ghi chú"
            }
        ));
        tblHoatDong.setFocusable(false);
        tblHoatDong.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblHoatDong.setRowHeight(30);
        tblHoatDong.setSelectionBackground(new java.awt.Color(245, 165, 165));
        tblHoatDong.setShowVerticalLines(false);
        tblHoatDong.getTableHeader().setReorderingAllowed(false);
        tblHoatDong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoatDongMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tblHoatDong);

        jLabel70.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        jLabel70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_term_35px_1.png"))); // NOI18N
        jLabel70.setText("Ghi chú");

        txtTenHD.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane15.setViewportView(txtGhiChu);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel65, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel68)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator9)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel67)
                                    .addComponent(jLabel70))
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenHD)
                                    .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE))))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 1231, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel65)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel67))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel70)
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(121, 121, 121)
                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(164, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewMouseEntered
        btnNew.setBackground(new Color(229, 229, 229));
        btnNew.setForeground(new Color(0, 204, 106));
    }//GEN-LAST:event_btnNewMouseEntered

    private void btnNewMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewMouseExited
        btnNew.setBackground(new Color(255, 255, 255));
        btnNew.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_btnNewMouseExited

    private void btnInsertMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInsertMouseEntered
        btnInsert.setBackground(new Color(229, 229, 229));
        btnInsert.setForeground(new Color(0, 204, 106));
    }//GEN-LAST:event_btnInsertMouseEntered

    private void btnInsertMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInsertMouseExited

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

    private void tblHoatDongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoatDongMouseClicked
        if (evt.getClickCount() == 1) {
            this.index = tblHoatDong.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();
            }
        }
    }//GEN-LAST:event_tblHoatDongMouseClicked

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        clear();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        insert();
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        this.index = 0;
        this.edit();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        this.index--;
        this.edit();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        this.index++;
        this.edit();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        this.index = tblHoatDong.getRowCount() - 1;
        this.edit();
    }//GEN-LAST:event_btnLastActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTable tblHoatDong;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtTenHD;
    // End of variables declaration//GEN-END:variables

    int index = 0;
    HoatDongDAO dao = new HoatDongDAO();

    void load() {
        DefaultTableModel model = (DefaultTableModel) tblHoatDong.getModel();
        model.setRowCount(0);
        try {
            List<HoatDong> list = dao.select();
            for (HoatDong cd : list) {
                Object[] row = {
                    cd.getMaHoatDong(),
                    cd.getTenHoatDong(),
                    cd.getGhichu()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    
    void setModel(HoatDong model) {
        txtTenHD.setText(model.getTenHoatDong());
        txtGhiChu.setText(model.getGhichu());

    }

    HoatDong getModel() {
        HoatDong model = new HoatDong();
        model.setTenHoatDong(txtTenHD.getText());
        model.setGhichu(txtGhiChu.getText());
        return model;
    }
    
    void insert() {
        HoatDong model = getModel();
        try {
            dao.insert(model);
            this.load();
            this.clear();
            DialogHelper.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Thêm mới thất bại!");
        }
    }

    void clear() {
        this.setModel(new HoatDong());
        this.setStatus(true);
    }

    void setStatus(boolean insertable) {

        btnInsert.setEnabled(insertable);
        btnUpdate.setEnabled(!insertable);
        btnDelete.setEnabled(!insertable);
        boolean first = this.index > 0;
        boolean last = this.index < tblHoatDong.getRowCount() - 1;
        btnFirst.setEnabled(!insertable && first);
        btnPrev.setEnabled(!insertable && first);
        btnNext.setEnabled(!insertable && last);
        btnLast.setEnabled(!insertable && last);
    }
    
    void edit() {
        try {
            int maHD = (int) tblHoatDong.getValueAt(this.index, 0);
            HoatDong model = dao.findById(maHD);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
                tblHoatDong.setRowSelectionInterval(index, index);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void update() {
        int maHD = (int) tblHoatDong.getValueAt(index, 0);
        HoatDong model = getModel();
        model.setMaHoatDong(maHD);
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
            int maHD = (int) tblHoatDong.getValueAt(this.index, 0);
            try {
                dao.delete(maHD);
                this.load();
                clear();
                DialogHelper.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Xóa thất bại!");
            }
        }
    }
}
