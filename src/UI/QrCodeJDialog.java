package UI;

import DuAnDAO.NhanVienDAO;
import Entity.NhanVien;
import Helper.DialogHelper;
import Helper.ShareHelper;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.time.Clock;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class QrCodeJDialog extends javax.swing.JDialog implements Runnable, ThreadFactory {

    private WebcamPanel panel = null;
    private Webcam webcam = null;

    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);

    public QrCodeJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initWebcam();
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnDangNhap = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnThoat = new javax.swing.JButton();
        lblMaNV = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Monospaced", 2, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(50, 133, 253));
        jLabel1.setText("Mã đăng nhập");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, -1, 30));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 360, 290));

        btnDangNhap.setBackground(new java.awt.Color(0, 0, 204));
        btnDangNhap.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        btnDangNhap.setForeground(new java.awt.Color(255, 255, 255));
        btnDangNhap.setText("Đăng nhập");
        btnDangNhap.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnDangNhap.setContentAreaFilled(false);
        btnDangNhap.setOpaque(true);
        btnDangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDangNhapMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDangNhapMouseExited(evt);
            }
        });
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });
        jPanel1.add(btnDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 370, 90, -1));

        jLabel2.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Xin chào! Chúng tôi là Euphoria");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, -1));

        jLabel3.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(92, 107, 192));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Quét tài khoản để truy cập ứng dụng ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 370, -1));

        btnThoat.setBackground(new java.awt.Color(255, 255, 255));
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_delete_24px_1.png"))); // NOI18N
        btnThoat.setContentAreaFilled(false);
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
        jPanel1.add(btnThoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 0, 30, -1));

        lblMaNV.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        lblMaNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMaNV.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(lblMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 370, 150, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/z2948460409551_881c61d3080d060f89955ddb0a621b15.jpg"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 380, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 410));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        login();
    }//GEN-LAST:event_btnDangNhapActionPerformed

    private void btnDangNhapMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangNhapMouseEntered
        btnDangNhap.setForeground(new Color(231, 255, 1));
        btnDangNhap.setBackground(new Color(247, 149, 157));
    }//GEN-LAST:event_btnDangNhapMouseEntered

    private void btnDangNhapMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangNhapMouseExited
        btnDangNhap.setForeground(new Color(255, 255, 255));
        btnDangNhap.setBackground(new Color(51,0,153));
    }//GEN-LAST:event_btnDangNhapMouseExited

    private void btnThoatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatMouseEntered

    }//GEN-LAST:event_btnThoatMouseEntered

    private void btnThoatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThoatMouseExited

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        exit();
    }//GEN-LAST:event_btnThoatActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QrCodeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            QrCodeJDialog dialog = new QrCodeJDialog(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangNhap;
    private javax.swing.JButton btnThoat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblMaNV;
    // End of variables declaration//GEN-END:variables

    public void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();

        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        jPanel2.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 290));

        executor.execute(this);

    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            try {
                result = new MultiFormatReader().decode(bitmap);

            } catch (NotFoundException e) {

            }

            if (result != null) {
                lblMaNV.setText(result.getText());
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

    NhanVienDAO dao = new NhanVienDAO();

    void login() {
        String manv = lblMaNV.getText();
        try {
            NhanVien nhanVien = dao.selectByID(manv);
            if (nhanVien != null) {
                ShareHelper.user = nhanVien;              
                DialogHelper.alert(this, "Đăng nhập thành công!");
                this.dispose();
                webcam.close();
            } else {
                DialogHelper.alert(this, "Sai tên đăng nhập!");
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void exit() {
        if (DialogHelper.confirm(this, "Bạn có muốn thoát khỏi ứng dụng không?")) {
            System.exit(0);
        }
    }
}
