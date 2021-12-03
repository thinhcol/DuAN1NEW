/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import com.toedter.calendar.JDateChooser;
import static java.awt.Color.pink;
import static java.awt.Color.white;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class CheckHelper {

    public static boolean checkNullText(JTextField txt) {
        if (txt.getText().trim().length() > 0) {
            txt.setBackground(white);
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(), "Không được để trống ");
            txt.setBackground(pink);
            txt.requestFocus();
            return false;
        }
    }

    public static boolean checkNullText(JTextArea txt) {
        if (txt.getText().trim().length() > 0) {
            txt.setBackground(white);
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(), "Không được để trống ");
            txt.setBackground(pink);
            txt.requestFocus();
            return false;
        }
    }

    public static boolean checkNullPass(JPasswordField txt) {

        if (txt.getPassword().length > 0) {
            txt.setBackground(white);
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(), "Không được để trống mật khẩu");
            txt.setBackground(pink);
            txt.requestFocus();
            return false;
        }
    }

    public static boolean checkMaNV(JTextField txt) {
        String id = txt.getText();
        String rgx = "[a-zA-Z0-9]{1,10}";
        if (id.matches(rgx)) {
            txt.setBackground(white);
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(), "Mã nhân viên phải có 1-10 kí tự.\nChữ hoa, thường, không dấu hoặc số.");
            txt.setBackground(pink);
            txt.requestFocus();
            return false;
        }
    }

    public static boolean checkPass(JPasswordField txt) {
        if (txt.getPassword().length > 2 && txt.getPassword().length < 15) {
            txt.setBackground(white);
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(), "Mật khẩu phải có từ 3-15 kí tự.");
            txt.setBackground(pink);
            txt.requestFocus();
            return false;
        }
    }

    public static boolean checkName(JTextField txt) {
        String id = txt.getText();
        String rgx = "^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]{3,25}$";
        if (id.matches(rgx)) {
            txt.setBackground(white);
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(), "Họ và tên phải là tên tiếng việt hoặc không đấu, không có ký tự đặc biệt\nTừ 3-25 kí tự");
            txt.setBackground(pink);
            txt.requestFocus();
            return false;
        }
    }

    public static boolean checkCMND(JTextField txt) {

        String id = txt.getText();
        String rgx = "[0-9]{9, 12}";
        if (id.matches(rgx)) {
            txt.setBackground(white);
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(), "CMND có 9 hoặc 12 số. Vui lòng nhập đầy đủ");
            txt.setBackground(pink);
            txt.requestFocus();
            return false;
        }
    }

    public static boolean checkSDT(JTextField txt) {

        String id = txt.getText();
        String rgx = "(086|096|097|098|032|033|034|035|036|037|038|039|089|090|093|070|079|077|078|076|088|091|094|083|084|085|081|082|092|056|058|099|059)[0-9]{7}";
        if (id.matches(rgx)) {
            txt.setBackground(white);
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(), "Số điện thoại phải gồm 10 số\nĐúng các đầu số của nhà mạng.");
            txt.setBackground(pink);
            txt.requestFocus();
            return false;
        }
    }

    public static boolean checkEmail(JTextField txt) {

        String id = txt.getText();
        String rgx = "^[a-zA-Z][a-zA-Z0-9_\\.]{2,32}@[a-zA-Z0-9]{2,10}(\\.[a-zA-Z0-9]{2,4}){1,2}$";
        if (id.matches(rgx)) {
            txt.setBackground(white);
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(), "Email không đúng định dạng");
            txt.setBackground(pink);
            txt.requestFocus();
            return false;
        }
    }

    public static boolean checkGia(JTextField txt) {

        try {
            float gia = Float.parseFloat(txt.getText());
            if (gia >= 0) {
                txt.setBackground(white);
                return true;
            } else {
                DialogHelper.alert(txt.getRootPane(), "Giá phải lớn hơn 0");
                txt.setBackground(pink);
                txt.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            DialogHelper.alert(txt.getRootPane(), "Giá là số và không có ký tự đặc biệt");
            txt.setBackground(pink);
            txt.requestFocus();
            return false;
        }
    }

    public static boolean checkThoiGianO(JTextField txt) {
        try {
            int tg = Integer.parseInt(txt.getText());
            if (tg >= 1) {
                return true;
            } else {
                DialogHelper.alert(txt.getRootPane(), "Thời gian ở phải lớn hơn hoặc bằng 1.");
                return false;
            }
        } catch (NumberFormatException e) {
            DialogHelper.alert(txt.getRootPane(), "Thời gian ở phải là số nguyên.");
            return false;
        }
    }

    public static boolean checkMaPhong(JTextField txt) {

        String id = txt.getText();
        String rgx = "(R)[0-9]{3}";
        if (id.matches(rgx)) {
            txt.setBackground(white);
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(), "Mã phòng gồm 4 ký tự.\nVới R đứng đầu và 3 số tiếp theo\nVui lòng nhập đúng định dạng.");
            txt.setBackground(pink);
            txt.requestFocus();
            return false;
        }
    }

    public static boolean checkTenNghe(JTextField txt) {
        String id = txt.getText();
        String rgx = "^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]{3,50}$";
        if (id.matches(rgx)) {
            txt.setBackground(white);
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(), "Tên nghề phải là tên tiếng việt hoặc không đấu\nTừ 3-50 kí tự");
            txt.setBackground(pink);
            txt.requestFocus();
            return false;
        }
    }
    
    public static boolean checkMaTN(JTextField txt) {

        String id = txt.getText();
        String rgx = "(TN)[0-9]{2}";
        if (id.matches(rgx)) {
            txt.setBackground(white);
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(), "Mã thân nhân gồm 4 ký tự.\n Với TN đứng đầu và 2 số tiếp theo\nVui lòng nhập đúng định dạng.");
            txt.setBackground(pink);
            txt.requestFocus();
            return false;
        }
    }


}
