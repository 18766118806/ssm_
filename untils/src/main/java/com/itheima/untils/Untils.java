package com.itheima.untils;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/2 17:55
 **/
public class Untils {
    public static Date StrToDate(String str,String format) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat (format);
        Date date =  simpleDateFormat.parse (str);
        return date;
    }
    public static String DateToStr(Date date, String format) throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat (format);
        String dateStr = simpleDateFormat.format (date);
        return dateStr;
    }

}
