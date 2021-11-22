/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class DateHelper {
    

    static final SimpleDateFormat DATE_FORMATER = new SimpleDateFormat("dd/MM/yyyy");

    public static Date toDate(String date, String... pattern) {
        try {
            if (pattern.length > 0) {
                DATE_FORMATER.applyPattern(pattern[0]);
            }
            if (date == null) {
                return DateHelper.now();
            }
            return DATE_FORMATER.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String toString(Date date, String... pattern) {
        if (pattern.length > 0) {
            DATE_FORMATER.applyPattern(pattern[0]);
        }
        if (date == null) {
            date = DateHelper.now();
        }
        return DATE_FORMATER.format(date);
    }

    public static Date now() {
        return new Date();
    }

    public static Date addDays(Date date, int days) {
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        return date;
    }

    public static Date add(int days) {
        Date now = DateHelper.now();
        now.setTime(now.getTime() + days * 24 * 60 * 60 * 1000);
        return now;
    }
    public static int subtrDateToMonth(Date ngayBiTru, Date ngayTru) {
        int thang = 0;

        int yearNgayBiTru = ngayBiTru.getYear();
        int monthNgayBiTru = ngayBiTru.getMonth() + 1;
        int dateNgayBiTru = ngayBiTru.getDate();

        int yearNgayTru = ngayTru.getYear();
        int monthNgayTru = ngayTru.getMonth() + 1;
        int dateNgayTru = ngayTru.getDate();

        if (yearNgayTru == yearNgayBiTru) {
            thang = monthNgayBiTru - monthNgayTru;
        } else if (yearNgayBiTru > yearNgayTru) {
            int khoangNam = yearNgayBiTru - yearNgayTru;
            thang = khoangNam * 12 + monthNgayBiTru - monthNgayTru;
        }
        if (dateNgayTru >= dateNgayBiTru) {
            thang--;
        }
        return thang;
    }
    
    public static Date addMonths(Date date, int months){
        
        date.setMonth(date.getMonth() + months);
        
        return date;
    }
}
