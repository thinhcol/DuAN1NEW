/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import com.toedter.calendar.JDateChooser;
import static java.awt.Color.pink;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class CheckHelper {

    public static String getRank(double diem) {
        String xepLoai = "Xuất sắc";
        if (diem < 0) {
            xepLoai = "Chưa nhập";

        } else if (diem < 3) {
            xepLoai = "Kém";

        } else if (diem < 5) {
            xepLoai = "Yếu";

        } else if (diem < 6.5) {

            xepLoai = "Trung bình";

        } else if (diem < 7.5) {
            xepLoai = "Khá";

        } else if (diem < 9) {
            xepLoai = "Giỏi";

        }
        return xepLoai;
    }

    public static boolean checkMaNV(JTextField txt) {

        String id = txt.getText();
        String rgx = "[a-zA-Z0-9]{1,15}";
        if (id.matches(rgx)) {
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(),  "Mã nhân viên phải có 1-15 kí tự\nchữ hoa, thường không dấu hoặc số.");
            return false;
        }
    }

    public static boolean checkMaNH(JTextField txt) {

        String id = txt.getText();
        String rgx = "[a-zA-Z0-9]{7}";
        if (id.matches(rgx)) {
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(), "Mã người học phải có đúng 7 kí tự\nchữ thường, chữ hoa hoặc số");
            return false;
        }
    }

    public static boolean checkMaCD(JTextField txt) {

        String id = txt.getText();
        String rgx = "[a-zA-Z0-9]{5}";
        if (id.matches(rgx)) {
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(), "Mã chuyên đề phải có đúng 5 kí tự\nchữ thường, chữ hoa hoặc số");
            return false;
        }
    }

    public static boolean checkPass(JPasswordField txt) {

        if (txt.getPassword().length > 5 && txt.getPassword().length < 25) {
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(),"Mật khẩu phải có từ 6-24 kí tự.");
            return false;
        }
    }

    public static boolean checkName(JTextField txt) {

        String id = txt.getText();
        String rgx = "^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]{10,25}$";
        if (id.matches(rgx)) {
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(), "Họ và tên phải là tên tiếng việt hoặc không đấu\ntừ 10-25 kí tự");
            return false;
        }
    }

    public static boolean checkTenCD(JTextField txt) {

        String id = txt.getText();
        String rgx = ".{3,50}";
        if (id.matches(rgx)) {
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(),"Tên chuyên đề phải từ 3-50 kí tự.");
            return false;
        }
    }

    public static boolean checkMoTaCD(JTextArea txt) {

        String id = txt.getText();
        String rgx = ".{3,255}";
        if (id.matches(rgx)) {
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(),"mô tả chuyên đề phải từ 3-255 kí tự.");
            return false;
        }
    }

    public static boolean checkSDT(JTextField txt) {

        String id = txt.getText();
        String rgx = "(086|096|097|098|032|033|034|035|036|037|038|039|089|090|093|070|079|077|078|076|088|091|094|083|084|085|081|082|092|056|058|099|059)[0-9]{7}";
        if (id.matches(rgx)) {
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(), "số điện thoại phải gồm 10 số\nđúng các đầu số của nhà mạng.");
            return false;
        }
    }

    public static boolean checkEmail(JTextField txt) {

        String id = txt.getText();
        String rgx = "^[a-zA-Z][a-zA-Z0-9_\\.]{2,32}@[a-zA-Z0-9]{2,10}(\\.[a-zA-Z0-9]{2,4}){1,2}$";
        if (id.matches(rgx)) {
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(), "email không đúng định dạng");
            return false;
        }
    }

    public static boolean checkThoiLuong(JTextField txt) {

        try {
            int hour = Integer.parseInt(txt.getText());
            if (hour >= 0) {
                return true;
            } else {

                DialogHelper.alert(txt.getRootPane(), "Thời lượng phải lớn hơn bằng 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            DialogHelper.alert(txt.getRootPane(), "Thời lượng phải là số nguyên.");
            return false;
        }
    }

    public static boolean checkHocPhi(JTextField txt) {

        try {
            float hp = Float.parseFloat(txt.getText());
            if (hp >= 0) {
                return true;
            } else {

                DialogHelper.alert(txt.getRootPane(), "Học phí phải là lớn hơn bằng 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            DialogHelper.alert(txt.getRootPane(), "Học phí phải là số thực.");
            return false;
        }
    }

    public static boolean checkDiem(JTextField txt) {
        float hp = Float.parseFloat(txt.getText());
        if ((hp >= 0 && hp <= 10) || hp == -1) {
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(), "Điểm phải là trong khoảng 0-10 hoặc chưa nhập (-1).");
            return false;
        }

    }

    public static boolean checkNullText(JTextField txt) {

        if (txt.getText().trim().length() > 0) {
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(), "Không được để trống " + txt.getName());
            return false;
        }
    }

    public static boolean checkNullText(JDateChooser txt) {

        if (txt.getDate() != null) {
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(), "Không được để trống ngày sinh" + txt.getName());
            return false;
        }
    }

    public static boolean checkNullText(JTextArea txt) {

        if (txt.getText().trim().length() > 0) {
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(), "Không được để trống " + txt.getName());
            return false;
        }
    }

    public static boolean checkNullPass(JPasswordField txt) {

        if (txt.getPassword().length > 0) {
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(), "Không được để trống mật khẩu" + txt.getName());
            return false;
        }
    }
}
