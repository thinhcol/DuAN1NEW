package com.duan.main;

import Helper.DialogHelper;
import Helper.ShareHelper;
import UI.BenhAnJPanel;
import UI.BenhNhanJPanel;
import UI.ChaoJdialog;
import UI.DangNhap;
import UI.DichVuJPanel;
import UI.GioiThieuJPanel;
import UI.Hm;
import UI.HoaDonJPanel;
import UI.HoatDongJPanel;
import UI.Home;
import UI.NgheJPanel;
import UI.NhanVienJPanel;
import UI.PhongJPanel;
import UI.ThanNhanJPanel;
import com.duan.event.EventMenu;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Main extends javax.swing.JFrame {

    public Main() {
        initComponents();
        init();
        setBackground(new Color(0, 0, 0, 0));
        showForm(new Hm());
         ImageIcon icon = ShareHelper.read(ShareHelper.user.getHinh());
        menu1.nicknam(ShareHelper.user.getHoTen(), ShareHelper.user.isChucVu() ? "Cán bộ" : "Quản giáo",icon);
    }

    private void showForm(Component com) {
        body.removeAll();
        body.add(com);
        body.repaint();
        body.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.duan.swing.PanelBorder();
        menu1 = new com.duan.component.Menu();
        body = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        body.setMaximumSize(new java.awt.Dimension(1038, 804));
        body.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 1643, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE, 899, Short.MAX_VALUE)
            .addComponent(body, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        menu1.setSelectedIndex(0);
        menu1.addEvent(new EventMenu() {
            @Override
            public void menuIndexChange(int index) {
                if (ShareHelper.isLogin()) {
                    if (index == 0) {
                        showForm(new Hm());
                    } else if (index == 1) {
                        showForm(new BenhNhanJPanel());
                    } else if (index == 2) {
                        showForm(new BenhAnJPanel());
                    } else if (index == 3) {
                        showForm(new PhongJPanel());
                    } else if (index == 4) {
                        showForm(new NhanVienJPanel());
                    } else if (index == 5) {
                        showForm(new NgheJPanel());
                    } else if (index == 6) {
                        showForm(new GioiThieuJPanel());
                    } else if (index == 7) {
                        showForm(new HoaDonJPanel());
                    } else if (index == 8) {
                        showForm(new DichVuJPanel());
                    } else if (index == 9) {
                        showForm(new HoatDongJPanel());
                    } else if (index == 10) {
                        showForm(new ThanNhanJPanel());
                    }else if(index == 11){
                        exit();
                    }else if(index == 12){
                        System.exit(0);
                    }
                }else  if (ShareHelper.isLogin1()) {
                    if (index == 0) {
                        showForm(new Hm());
                    } else if (index == 1) {
                        showForm(new BenhNhanJPanel());
                    } else if (index == 2) {
                        showForm(new BenhAnJPanel());
                    } else if (index == 3) {
                        showForm(new PhongJPanel());
                    } else if (index == 4) {
                        JOptionPane.showMessageDialog(null, "Mục dành cho ban quan lý");
                    } else if (index == 5) {
                        showForm(new NgheJPanel());
                    } else if (index == 6) {
                        showForm(new GioiThieuJPanel());
                    } else if (index == 7) {
                        showForm(new HoaDonJPanel());
                    } else if (index == 8) {
                        showForm(new DichVuJPanel());
                    } else if (index == 9) {
                        showForm(new HoatDongJPanel());
                    } else if (index == 10) {
                        JOptionPane.showMessageDialog(null, "Mục dành cho ban quan lý");
                    }else if(index == 11){
                        exit();
                    }else if(index == 12){
                        System.exit(0);
                    }
                }

            }
        });
    }//GEN-LAST:event_formWindowOpened

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    private void init() {
        new Timer(1000, new ActionListener() {
            SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss a");

            @Override
            public void actionPerformed(ActionEvent e) {
//                lblDongHo.setText(format.format(new Date()));
            }
        }).start();
        setLocationRelativeTo(null);
        new ChaoJdialog(this, true).setVisible(true);
        new DangNhap(this, true).setVisible(true);
    }

    Timer tm;
    int x = 0;
    String[] list = {
        "src\\Image\\9.png",
        "src\\Image\\10.jpg",
        "src\\Image\\12.jpg"
    };
    void exit() {
        if (DialogHelper.confirm(this, "Bạn có muốn đăng xuất khỏi ứng dụng không?")) {
            this.dispose();
            new Main().setVisible(true);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane body;
    private com.duan.component.Menu menu1;
    private com.duan.swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
