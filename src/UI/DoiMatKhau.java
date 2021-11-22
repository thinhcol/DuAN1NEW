/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DuAnDAO.NhanVienDAO;
import Entity.NhanVien;
import Helper.DialogHelper;
import Helper.ShareHelper;
import java.awt.Color;

/**
 *
 * @author admin
 */
public class DoiMatKhau extends javax.swing.JFrame {

    /**
     * Creates new form QuenMatKhauJFrame
     */
    public DoiMatKhau() {
        initComponents();
        init();
    }
    NhanVienDAO dao=new NhanVienDAO();
    public void init(){
        
        
        NhanVien nv = dao.selectByID("NghiaPL");
        ShareHelper.user = nv;
      
        
        txtMaNV.setText(ShareHelper.user.getMaNV());
    }
    public void doiMatKhau(){
        txtXacNhanMKM.setBackground(Color.white);
        txtMatKhau.setBackground(Color.white);
        
        String matKhau=new String(txtMatKhau.getPassword());
        String matKhauMoi=new String(txtMatKhauMoi.getPassword());
        String xacNhanMKM=new String(txtXacNhanMKM.getPassword());
        
        if(matKhau.equals(ShareHelper.user.getMatKhau())){
            if(matKhauMoi.equals(xacNhanMKM)){
                ShareHelper.user.setMatKhau(matKhauMoi);
                dao.update(ShareHelper.user);
                DialogHelper.alert(this, "Đổi mật khẩu thành công!!");
                this.dispose();
            }else{
                txtXacNhanMKM.setBackground(Color.pink);
                DialogHelper.alert(this, "Mật khẩu xác nhận không trùng mật khẩu");
            }
        }else{
            txtMatKhau.setBackground(Color.pink);
            DialogHelper.alert(this, "Mật khẩu cũ nhập không chính xác!");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtMatKhauMoi = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        txtXacNhanMKM = new javax.swing.JPasswordField();
        btnDoiMatKhau = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_user_125px_1.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 120));

        jLabel4.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(219, 0, 108));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Xin chào ! Chúng tôi là Euphoria");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 430, -1));

        jLabel3.setFont(new java.awt.Font("Monospaced", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(219, 0, 108));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Đổi mật khẩu");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 430, -1));

        jLabel6.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel6.setText("Tên đăng nhập");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        txtMaNV.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        txtMaNV.setBorder(null);
        jPanel1.add(txtMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 240, 30));

        jLabel2.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel2.setText("Mật khẩu mới");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, 30));

        txtMatKhauMoi.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        txtMatKhauMoi.setBorder(null);
        jPanel1.add(txtMatKhauMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 240, 30));

        jLabel7.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel7.setText("Mật khẩu hiện tại ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, 30));

        txtMatKhau.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        txtMatKhau.setBorder(null);
        jPanel1.add(txtMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 240, 30));

        jLabel8.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel8.setText("Xác nhận");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, 30));

        txtXacNhanMKM.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        txtXacNhanMKM.setBorder(null);
        jPanel1.add(txtXacNhanMKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, 240, 30));

        btnDoiMatKhau.setBackground(new java.awt.Color(236, 165, 200));
        btnDoiMatKhau.setFont(new java.awt.Font("Monospaced", 1, 16)); // NOI18N
        btnDoiMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        btnDoiMatKhau.setText("ĐỔI MẬT KHẨU");
        btnDoiMatKhau.setContentAreaFilled(false);
        btnDoiMatKhau.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDoiMatKhau.setOpaque(true);
        btnDoiMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDoiMatKhauMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDoiMatKhauMouseExited(evt);
            }
        });
        btnDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMatKhauActionPerformed(evt);
            }
        });
        jPanel1.add(btnDoiMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, 160, 40));

        btnThoat.setBackground(new java.awt.Color(236, 135, 192));
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_delete_24px_1.png"))); // NOI18N
        btnThoat.setContentAreaFilled(false);
        btnThoat.setOpaque(true);
        btnThoat.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_delete_24px.png"))); // NOI18N
        btnThoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThoatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThoatMouseExited(evt);
            }
        });
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        jPanel1.add(btnThoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 30, -1));

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/1.png"))); // NOI18N
        jPanel1.add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 450));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(431, 450));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDoiMatKhauMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDoiMatKhauMouseEntered
        btnDoiMatKhau.setForeground(new Color(231, 255, 1));
        btnDoiMatKhau.setBackground(new Color(247, 149, 157));
    }//GEN-LAST:event_btnDoiMatKhauMouseEntered

    private void btnDoiMatKhauMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDoiMatKhauMouseExited
        btnDoiMatKhau.setForeground(new Color(255, 255, 255));
        btnDoiMatKhau.setBackground(new Color(236,135,192));
    }//GEN-LAST:event_btnDoiMatKhauMouseExited

    private void btnThoatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatMouseEntered

    }//GEN-LAST:event_btnThoatMouseEntered

    private void btnThoatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThoatMouseExited

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMatKhauActionPerformed
        // TODO add your handling code here:
        doiMatKhau();
    }//GEN-LAST:event_btnDoiMatKhauActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DoiMatKhau().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDoiMatKhau;
    private javax.swing.JButton btnThoat;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JPasswordField txtMatKhauMoi;
    private javax.swing.JPasswordField txtXacNhanMKM;
    // End of variables declaration//GEN-END:variables
}
