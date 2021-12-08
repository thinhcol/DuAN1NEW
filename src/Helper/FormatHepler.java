/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Lenovo
 */
public class FormatHepler {

    public static String formatMoney(double money) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        return currencyVN.format(money);
    }
    
    public static String formatMoney(long money) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        return currencyVN.format(money);
    }
    
    public static String formatMoney(int money) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        return currencyVN.format(money);
    }
    public static String formatMoney(String money) {
        double mn = Double.parseDouble(money);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        return currencyVN.format(mn);
    }
    public static double formatMoneyToDouble(String money) {
        money = money.trim();
        money = money.replace("đ","");
        money = money.replace(".", "");
        money = money.replace(" ", "");
        return Double.parseDouble(money);
    }
}
