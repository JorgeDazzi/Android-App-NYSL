package dazzi.com.nysl.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date {

    public String getDatesParam(){
        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());

        String date = (String)(dtFormat.format(calendar.getTime()));
        return date;

    }

    public String getDatesParam(int days){
        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        calendar.add(Calendar.DATE, days);

        String date = (String)(dtFormat.format(calendar.getTime()));
        return date;

    }

}
