/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateConverter {
    
    public static String reformat (String dateStr, String dateFormStr) throws ParseException {
        
        String sec = "sec";
        String min = "min";
        String hour = "hour";
        String anHour = "an hour";
        String hr = "hr";
        String day = "day";
        String yesterday = "yesterday";
        String week = "week";
        String month = "month";
        String year = "year";
        
        boolean dateMetricBool = (dateStr.contains(sec) || dateStr.contains(min) || dateStr.contains(hour) || dateStr.contains(hr) 
            || dateStr.contains(hr) || dateStr.contains(day) || dateStr.contains(week) 
            || dateStr.contains(month) || dateStr.contains(year));
        boolean commaBool = dateStr.contains(",");
        
        boolean dateHasStrBool = dateMetricBool && commaBool == false;
        boolean dateCommaBool = dateMetricBool == false && commaBool;
        boolean dateNoCommaBool = dateMetricBool == false && commaBool == false;
        
        SimpleDateFormat dateParseForm = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateParse = null;
        String pubDate = null;
        
        if (dateHasStrBool) {
            
            Calendar cal = Calendar.getInstance();
            dateStr = dateStr.toLowerCase();
            Integer prevInt = null;
            
            cal = Calendar.getInstance();
            if (dateStr.contains(sec)) {
                prevInt = new Integer(dateStr.split(" " + sec)[0]);
                cal.add(Calendar.SECOND, - prevInt);
            } else if (dateStr.contains(min)) {
                prevInt = new Integer(dateStr.split(" " + min)[0]);
                cal.add(Calendar.MINUTE, - prevInt);
            } else if (dateStr.contains(anHour)) {
                cal.add(Calendar.MINUTE, - 1);
            } else if (dateStr.contains(hr)) {
                prevInt = new Integer(dateStr.split(" " + hr)[0]);
                cal.add(Calendar.HOUR, - prevInt);
            } else if (dateStr.contains(hour)) {
                prevInt = new Integer(dateStr.split(" " + hour)[0]);
                cal.add(Calendar.HOUR, - prevInt);
            } else if (dateStr.contains(day) && dateStr.contains(yesterday) == false) {
                prevInt = new Integer(dateStr.split(" " + day)[0]);
                cal.add(Calendar.DAY_OF_YEAR, - prevInt);
            } else if (dateStr.contains(yesterday)) {
                cal.add(Calendar.DAY_OF_YEAR, - 1);
            } else if (dateStr.contains(week)) {
                prevInt = new Integer(dateStr.split(" " + week)[0]);
                cal.add(Calendar.WEEK_OF_YEAR, - prevInt);
            } else if (dateStr.contains(month)) {
                prevInt = new Integer(dateStr.split(" " + month)[0]);
                cal.add(Calendar.MONTH, - prevInt);
            } else if (dateStr.contains(year)) {
                prevInt = new Integer(dateStr.split(" " + year)[0]);
                cal.add(Calendar.YEAR, - prevInt);
            }

            dateParse = cal.getTime();
            pubDate = dateFormat.format(cal.getTime());
        } else if (dateCommaBool) {
            dateParseForm = new SimpleDateFormat(dateFormStr);
            dateParse = dateParseForm.parse(dateStr);
            pubDate = dateFormat.format(dateParse);
        } else if (dateNoCommaBool) {
            dateParseForm = new SimpleDateFormat(dateFormStr);
            dateParse = dateParseForm.parse(dateStr);
            pubDate = dateFormat.format(dateParse);
        }
        
        return pubDate;
    }
    
    public static void main(String[] args) throws ParseException {
        System.out.println(DateConverter.reformat("April 20, 1969", "MMM dd, yyyy"));
        System.out.println(DateConverter.reformat("2 years ago", ""));
        System.out.println(DateConverter.reformat("May 31 1969", "MMM dd yyyy"));
        System.out.println(DateConverter.reformat("1/2/2018", "MM/dd/yyyy"));
        System.out.println(DateConverter.reformat("yesterday", "MM/dd/yyyy"));
        System.out.println(DateConverter.reformat("58 mins ago", "MM/dd/yyyy"));
    }
}
