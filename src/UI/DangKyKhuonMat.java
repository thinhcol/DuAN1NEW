package UI;

import DuAnDAO.NhanVienDAO;
import Entity.NhanVien;
import Entity.luufile;
import Helper.DialogHelper;
import Helper.JdbcHelper;
import Helper.ShareHelper;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Array;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imencode;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import org.bytedeco.opencv.global.opencv_imgproc;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;
import org.opencv.core.Core;

public class DangKyKhuonMat extends javax.swing.JDialog {

    public DangKyKhuonMat(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
//        recognizer.read("src\\photo\\classifierLBPH.yml");
//        recognizer.setThreshold(80);
        if (ShareHelper.isLogin()) {
            lblid.setText(String.valueOf(ShareHelper.user.getMaid()));
        }else{
            JOptionPane.showMessageDialog(null, "Bạn không có quyền dùng");
        }
        
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
        webSource = new VideoCapture(0);
        myThread = new DaemonThread();
        Thread t = new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable = true;
        t.start();
    }
//     private DaemonThread myThread = null;
//    String ten, email, sdt;
//    int id;
//    VideoCapture webSource = null;
//    Mat frame = new Mat();
//    CascadeClassifier faceDetector = new CascadeClassifier("src\\photo\\haarcascade_frontalface_alt.xml");
//    LBPHFaceRecognizer recognizer = LBPHFaceRecognizer.create();
//    BytePointer mem = new BytePointer();
//    RectVector detectedFaces = new RectVector();
    JdbcHelper acp = new JdbcHelper();

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblphoto = new javax.swing.JLabel();
        btnsave = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnThoat = new javax.swing.JButton();
        lblid = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbldem = new javax.swing.JLabel();

        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Monospaced", 2, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Mã nhận diện");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, -1, 30));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(lblphoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 310));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 360, 310));

        btnsave.setBackground(new java.awt.Color(0, 0, 204));
        btnsave.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        btnsave.setForeground(new java.awt.Color(255, 255, 255));
        btnsave.setText("Lưu");
        btnsave.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnsave.setContentAreaFilled(false);
        btnsave.setOpaque(true);
        btnsave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnsaveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnsaveMouseExited(evt);
            }
        });
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });
        jPanel1.add(btnsave, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 380, 90, 30));

        jLabel2.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Xin chào! Chúng tôi là Euphoria");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, -1));

        jLabel3.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Quét tài khoản để truy cập ứng dụng ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 370, -1));

        btnThoat.setBackground(new java.awt.Color(255, 255, 255));
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/New Icon/icons8_delete_24px_1.png"))); // NOI18N
        btnThoat.setContentAreaFilled(false);
        btnThoat.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/New Icon/icons8_delete_24px.png"))); // NOI18N
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

        lblid.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        lblid.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblid.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(lblid, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, 70, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/z2948422196427_da90f7097cddad96de76f9bab4d0e64a.jpg"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 380, 420));

        lbldem.setFont(new java.awt.Font("Monospaced", 2, 13)); // NOI18N
        lbldem.setForeground(new java.awt.Color(0, 0, 153));
        lbldem.setText("0/25");
        jPanel1.add(lbldem, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 380, -1, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 420));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed

    }//GEN-LAST:event_btnsaveActionPerformed

    private void btnsaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsaveMouseEntered
        btnsave.setForeground(new Color(231, 255, 1));
        btnsave.setBackground(new Color(247, 149, 157));
    }//GEN-LAST:event_btnsaveMouseEntered

    private void btnsaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsaveMouseExited
        btnsave.setForeground(new Color(255, 255, 255));
        btnsave.setBackground(new Color(51, 0, 153));
    }//GEN-LAST:event_btnsaveMouseExited

    private void btnThoatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatMouseEntered

    }//GEN-LAST:event_btnThoatMouseEntered

    private void btnThoatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThoatMouseExited

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        myThread.runnable = false;
        webSource.release();
        this.dispose();
    }//GEN-LAST:event_btnThoatActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
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
            java.util.logging.Logger.getLogger(DangKyKhuonMat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            DangKyKhuonMat dialog = new DangKyKhuonMat(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnsave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbldem;
    private javax.swing.JLabel lblid;
    private javax.swing.JLabel lblphoto;
    // End of variables declaration//GEN-END:variables
     private DaemonThread myThread = null;
    int count = 1;
    VideoCapture webSource = null;
    Mat frame = new Mat();
    RectVector faceDetections = new RectVector();
    CascadeClassifier faceDetector = new CascadeClassifier("src\\photo\\haarcascade_frontalface_alt.xml");

    BytePointer mem = new BytePointer();
    RectVector detectedFaces = new RectVector();

    class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    if (webSource.grab()) {
                        try {
                            webSource.retrieve(frame);
                            Graphics g = lblphoto.getGraphics();
                            Mat imageColor = new Mat();
                            imageColor = frame;
                            Mat imageGray = new Mat();
                            cvtColor(imageColor, imageGray, COLOR_BGRA2GRAY);
                            RectVector faceDetections = new RectVector();
                            faceDetector.detectMultiScale(imageGray, faceDetections, 1.1, 1, 1, new Size(150, 150), new Size(500, 500));
                            for (int i = 0; i < faceDetections.size(); i++) {
                                Rect hi = faceDetections.get(0);
                                rectangle(imageColor, hi, new Scalar(255, 255, 255, 5));
                                Mat mat = new Mat(imageGray, hi);
                                opencv_imgproc.resize(mat, mat, new Size(160, 160));
                                if (btnsave.getModel().isPressed()) {
                                    if (count <= 25) {
                                        String cropped = "src\\photo\\person." + lblid.getText() + "." + count + ".jpg";
                                        boolean imwrite = imwrite(cropped, mat);
                                        lbldem.setText(String.valueOf(count) + "/25");
                                        count++;
                                    }
                                    if (count > 25) {
                                        new luufile().luu();
                                        JOptionPane.showMessageDialog(null, "Lưu thành công");
                                    }
                                }

                            }
                            imencode(".bmp", frame, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.getStringBytes()));
                            BufferedImage buff = (BufferedImage) im;
                            if (g.drawImage(buff, 0, 0, 360, 390, 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                                if (runnable == false) {
                                    this.wait();
                                }

                            }
                        } catch (Exception ex) {
                            System.out.println("Lỗi");
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
    }

//    public void initWebcam() {
//        Dimension size = WebcamResolution.QVGA.getSize();
//
//        webcam = Webcam.getWebcams().get(0);
//        webcam.setViewSize(size);
//
//        panel = new WebcamPanel(webcam);
//        panel.setPreferredSize(size);
//        panel.setFPSDisplayed(true);
//
//        jPanel2.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 290));
//
//        executor.execute(this);
//
//    }
//
//    @Override
//    public void run() {
//        do {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            Result result = null;
//            BufferedImage image = null;
//
//            if (webcam.isOpen()) {
//                if ((image = webcam.getImage()) == null) {
//                    continue;
//                }
//            }
//
//            LuminanceSource source = new BufferedImageLuminanceSource(image);
//            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//
//            try {
//                result = new MultiFormatReader().decode(bitmap);
//
//            } catch (NotFoundException e) {
//
//            }
//
//            if (result != null) {
//                lblMaNV.setText(result.getText());
//            }
//        } while (true);
//    }
//
//    @Override
//    public Thread newThread(Runnable r) {
//        Thread t = new Thread(r, "My Thread");
//        t.setDaemon(true);
//        return t;
//    }
    void exit() {
        if (DialogHelper.confirm(this, "Bạn có muốn thoát khỏi ứng dụng không?")) {
            System.exit(0);
        }
    }
}
