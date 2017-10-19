package com.zey.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 赵二盈 on 2017/10/13.
 */

public class DateUtils {
    public static String GetCurrentDate(){
        String time=" ";
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat();
        time=sdf.format(date);
        return time;
    }
}
