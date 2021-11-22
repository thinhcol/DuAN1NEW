/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import Entity.NhanVien;
import Entity.ThanNhan;
import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author ASUS
 */
public class ShareHelper {

//    public static final Image APP_ICON;
//
//    static {
//        // Tải biểu tượng ứng dụng
//        String file = "/com/edusys/icon/fpt.png";
//        APP_ICON = new ImageIcon(ShareHelper.class.getResource(file)).getImage();
//    }
//
//    public static boolean saveLogo(File file) {
//        File dir = new File("logos");
//        // Tạo thư mục nếu chưa tồn tại
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//        File newFile = new File(dir, file.getName());
//        try {
//            // Copy vào thư mục logos (đè nếu đã tồn tại)
//            Path source = Paths.get(file.getAbsolutePath());
//            Path destination = Paths.get(newFile.getAbsolutePath());
//            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
//            return true;
//        } catch (Exception ex) {
//            return false;
//        }
//    }
//    public static ImageIcon readLogo(String fileName) {
//        File path = new File("logos", fileName);
//        return new ImageIcon(path.getAbsolutePath());
//    }
//    public static ImageIcon read(String fileName){
//        File path = new File("", fileName);
//        return new ImageIcon(path.getAbsolutePath());
//    }
    public static boolean save(File file) {
        File dir = new File("logos");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(dir, file.getName());
        try {
            Path source = Paths.get(file.getAbsolutePath());
            Path destination = Paths.get(newFile.getAbsolutePath());
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static ImageIcon read(String fileName) {
        File path = new File("logos", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }

    public static ThanNhan nguoidung = null;
    public static NhanVien user = null;

    public static void clear() {
        ShareHelper.user = null;
    }
    public static void clear1() {
        ShareHelper.nguoidung = null;
    }
     public static boolean isLogin1() {
        return ShareHelper.nguoidung != null;
    }
    public static boolean isLogin() {
        return ShareHelper.user != null;
    }
    public static boolean isManager1() {
        return ShareHelper.isLogin1();
    }
    public static boolean isManager() {
        return ShareHelper.isLogin() && user.isChucVu();
    }
}
