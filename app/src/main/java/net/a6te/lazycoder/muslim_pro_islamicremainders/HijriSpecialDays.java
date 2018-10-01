package net.a6te.lazycoder.muslim_pro_islamicremainders;

import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar;

import java.util.Calendar;

public class HijriSpecialDays {


    public String isTodaySpecialDay(){

        String specialDayName = "no";
        UmmalquraCalendar cal = new UmmalquraCalendar();

        int todayHijriDate = cal.get(Calendar.DAY_OF_MONTH);
        int todayHijriMonth = cal.get(Calendar.MONTH)+1;

        if (todayHijriMonth == 1 && todayHijriDate == 3) specialDayName = "ashura";
        else if (todayHijriMonth == 3 && todayHijriDate == 12) specialDayName = "milad un nabi";
        else if (todayHijriMonth == 7 && todayHijriDate == 27) specialDayName = "lailat al miraj";
        else if (todayHijriMonth == 8 && todayHijriDate == 15) specialDayName = "shaban";
        else if (todayHijriMonth == 9){
            if (todayHijriDate == 1) specialDayName = "ramadan";
            else if (todayHijriDate == 27) specialDayName = "laylat al qadr";
        }
        else if (todayHijriMonth == 10 && todayHijriDate == 1) specialDayName = "eid ul fitr";
        else if (todayHijriMonth == 12){
            if (todayHijriDate == 8) specialDayName ="hajj";
            else if (todayHijriDate == 8) specialDayName ="arafat";
            else if (todayHijriDate == 9) specialDayName ="eid al adha";
        }

        return specialDayName;
    }
}
