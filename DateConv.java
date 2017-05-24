import java.util.Calendar;
import java.util.Date;
import java.lang.Math;


public class DateConv {
    public static void main(String[] args){

    String date = null;
    String dateNum = null;
    String[] dateSplit = null;
    Integer prevDate = null;

    Calendar currentTime = Calendar.getInstance();
    System.out.println("Current time: " + currentTime.getTime());
    System.out.println();

    for (int a = 0; a < 5; a++) {
        Calendar cal = Calendar.getInstance();
        Double randDub = Math.floor(Math.random() * 6);
        if (randDub == 0.0) {
            date = "22 seconds ago";
        } else if (randDub == 1.0) {
            date = "22 minutes ago";
        } else if (randDub == 2.0) {
            date = "22 hours ago";
        } else if (randDub == 3.0) {
            date = "22 days ago";
        } else if (randDub == 4.0) {
            date = "22 weeks ago";
        } else if (randDub == 5.0) {
            date = "22 months ago";
        }

        if (date.contains("second")) {
            dateSplit = date.split(" second");
            dateNum = dateSplit[0];
            prevDate = new Integer(dateNum);
            cal.add(Calendar.SECOND, - prevDate);
        } else if (date.contains("minute")) {
            dateSplit = date.split(" minute");
            dateNum = dateSplit[0];
            prevDate = new Integer(dateNum);
            cal.add(Calendar.MINUTE, - prevDate);
        } else if (date.contains("hour")) {
            dateSplit = date.split(" hour");
            dateNum = dateSplit[0];
            prevDate = new Integer(dateNum);
            cal.add(Calendar.HOUR, - prevDate);
        } else if (date.contains("day")) {
            dateSplit = date.split(" day");
            dateNum = dateSplit[0];
            prevDate = new Integer(dateNum);
            cal.add(Calendar.DAY_OF_YEAR, - prevDate);
        } else if (date.contains("week")) {
            dateSplit = date.split(" week");
            dateNum = dateSplit[0];
            prevDate = new Integer(dateNum);
            cal.add(Calendar.WEEK_OF_YEAR, - prevDate);
        } else if (date.contains("month")) {
            dateSplit = date.split(" month");
            dateNum = dateSplit[0];
            prevDate = new Integer(dateNum);
            cal.add(Calendar.MONTH, - prevDate);
        }

        Date reformDate = cal.getTime();

        System.out.println(date + " | " + reformDate);
        }
    }
}