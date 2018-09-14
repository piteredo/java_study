package java_study.dates;

import java.util.Date;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

class Main {
  public static void main(String[] args) {
    Date date1 = new Date();
    System.out.println(date1);

    Date date2 = new Date(1000);
    System.out.println(date2);

    System.out.println( date1.after(date2) );
    System.out.println( date1.before(date2) );
    System.out.println( date1.compareTo(date2) );
    System.out.println( date1.getTime() );
    date1.setTime(1001);
    System.out.println(date1);

    System.out.println(  Calendar.getInstance()  );
    System.out.println(  Calendar.getInstance(Locale.UK)  );
    TimeZone tx = TimeZone.getTimeZone("Eulope/London");
    System.out.println(  Calendar.getInstance(tx)  );

    Calendar ca = Calendar.getInstance();
    int year = ca.get(Calendar.YEAR);
    int month = ca.get(Calendar.MONTH) + 1;
    int date = ca.get(Calendar.DATE);
    int hour = ca.get(Calendar.HOUR_OF_DAY);
    int minute = ca.get(Calendar.MINUTE);
    int second = ca.get(Calendar.SECOND);
    StringBuffer dow = new StringBuffer();
    switch(ca.get(Calendar.DAY_OF_WEEK)){
      case Calendar.MONDAY: dow.append("monday"); break;
      case Calendar.TUESDAY: dow.append("tuesday"); break;
      case Calendar.WEDNESDAY: dow.append("wednesday"); break;
      case Calendar.THURSDAY: dow.append("thursday"); break;
      case Calendar.FRIDAY: dow.append("friday"); break;
      case Calendar.SATURDAY: dow.append("saturday"); break;
      case Calendar.SUNDAY: dow.append("sunday"); break;
    }
    System.out.println( year +"/"+ month +"/"+ date +"/"+ dow +"/"+ hour +"/"+ minute +"/"+ second);
    System.out.println(Calendar.SUNDAY);

    //SimpleDateFormat
    //TimeZone
  }
}
