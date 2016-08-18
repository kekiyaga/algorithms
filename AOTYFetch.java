/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sidelined;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author ddd
 */
public class AOTYFetch {
    public static void main(String[] args) throws Exception {
        
        String aotyBaseURL = "http://www.albumoftheyear.org";
        
        System.out.println(aotyBaseURL);
        System.out.println();
        
        int muRelInt = 0;
        
        //for loop to iterate through web pages, i.e. page 1, 2, 3, etc.
        for (int pgNum = 1; muRelInt >= 0; pgNum++) {
            
            //connecting to a website to view its entire HTML structure
            Document aotyGet = Jsoup.connect("http://www.albumoftheyear.org/releases/" + pgNum + "/")
                //methods used to enable Java to behave as a client PC instead of a robot
                .userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36")
                .referrer("www.google.com")
                .get();
            
            //parsing elements that contain each individual album
            Elements albumBlock = aotyGet.select("div.wideLeft div.albumBlock");
            
            //loop designed to get to each individual album
            for (int index = 0; index < albumBlock.size(); index++) {
                
                //method used to find individual albums based on their index
                Element albumGet = albumBlock.get(index);
                
                //parsing elements containing critic score values and the number of critics scoring the album
                Elements critBlock = albumGet.select("div.ratingRowContainer div.ratingRow:contains(critic score)");
                
                //parsing elements containing site users' personal score values and the number of users scoring the album
                Elements userBlock = albumGet.select("div.ratingRowContainer div.ratingRow:contains(user score)");
                
                
                
                //parsing elements containing an album's aggregate score from all critics
                String criticScore = critBlock.select("div.ratingBlock div.rating").text();
                
                //parsing element containing the sum of users scoring an album
                String criticSum = critBlock.select("div.ratingText").text();

                
                //parsing elements containing an album's aggregate score from all users
                String userScore = userBlock.select("div.ratingBlock div.rating").text();
                
                //parsing element containing the sum of users scoring an album
                String userSum = userBlock.select("div.ratingText").text();
                
                /* Booleans that confirm whether or not an album has a user or a critic score.  
                * Albums that have neither are skipped.  
                */
                boolean critSumEmpty = critBlock.isEmpty();
                boolean userSumEmpty = userBlock.isEmpty();

                int caseNum = 0;
                
                //
                String[] critSumSplit = null;
                String critSumNum = null;
                Integer critSumInt = null;

                String[] userSumSplit = null;
                String userSumNum = null;
                Integer userSumInt = null;

                /* If statements filtering out whether or not an album
                has been scored by users and/or critics at least once.  
                */
                if (critSumEmpty == false && userSumEmpty == true) {
                    caseNum = 1;
                } else if (critSumEmpty == true && userSumEmpty == false) {
                    caseNum = 2;
                } else if (critSumEmpty == false && userSumEmpty == false) {
                    caseNum = 3;
                } else if (critSumEmpty == true && userSumEmpty == true) {
                    caseNum = 4;
                }
                
                //booleans checking whether or not an album has been scored
                //a certain number of times
                boolean critMinBool = false;
                boolean userMinBool = false;
                
                //switch statement that extracts the user and/or critic score
                //based on which ones appear
                switch(caseNum) {
                    case 1: critSumSplit = criticSum.split("critic score ", 2);
                            critSumNum = critSumSplit[1].replace("(", "").replace(")", "");
                            critSumInt = new Integer(critSumNum);
                            
                            critMinBool = critSumInt >= 4;
                        break;
                    case 2: userSumSplit = userSum.split("user score ", 2);
                            userSumNum = userSumSplit[1].replace("(", "").replace(")", "");
                            userSumInt = new Integer(userSumNum);

                            userMinBool = userSumInt >= 4;
                        break;
                    case 3: critSumSplit = criticSum.split("critic score ", 2);
                            critSumNum = critSumSplit[1].replace("(", "").replace(")", "");
                            critSumInt = new Integer(critSumNum);

                            userSumSplit = userSum.split("user score ", 2);
                            userSumNum = userSumSplit[1].replace("(", "").replace(")", "");
                            userSumInt = new Integer(userSumNum);

                            critMinBool = critSumInt >= 4;
                            userMinBool = userSumInt >= 4;
                        break;
                    case 4:
                        break;
                }
                
                //printing the albums that have passed the critMinBool & userMinBool booleans
                if (critMinBool == true || userMinBool == true) {
                    
                    //artist name, album name, and relative URL strings
                    String artistName = albumGet.select("a div.artistTitle").text();
                    String albumName = albumGet.select("a div.albumTitle").text();
                    String relURL = albumGet.select("div.image a").attr("href");
                    
                    //navigating to the album's page on the website
                    Document albumPg = Jsoup.connect(aotyBaseURL + relURL)
                        .userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36")
                        .referrer("www.google.com")
                        .get();
                    
                    //
                    String relDateGet = albumPg.select("div.rightBox div.detailRow:contains(Release Date)").text();
                    String dateGetSub = " " + albumPg.select("div.rightBox div.detailRow:contains(Release Date) span").text();
                    String releaseDate = relDateGet.replace(dateGetSub, "").replace(",", "");
                    String[] relDateSplit = releaseDate.split(" ");
                    String monthString = relDateSplit[0];
                    
                    //using date & time classes to gather the current day of the week
                    Calendar calendar = Calendar.getInstance();

                    calendar.setTime(new SimpleDateFormat("MMM").parse(monthString));
                    int relMonth = calendar.get(Calendar.MONTH) + 1;

                    Integer relDay = new Integer(relDateSplit[1]);
                    Integer relYear = new Integer(relDateSplit[2]);

                    LocalDate dateSub = LocalDate.now();
                    LocalDate relDateFormat = LocalDate.of(relYear, relMonth, relDay);

                    DayOfWeek dateWeekDay = dateSub.getDayOfWeek();
                    
                    /* Subtracting the number of days from the day
                    new music is released.  
                    Music released a week before this date is not printed.  
                    */
                    int dayNum = 0;

                    switch (dateWeekDay) {
                        case FRIDAY: dayNum = 0;
                        break;
                        case SATURDAY: dayNum = 1;
                        break;
                        case SUNDAY: dayNum = 2;
                        break;
                        case MONDAY: dayNum = 3;
                        break;
                        case TUESDAY: dayNum = 4;
                        break;
                        case WEDNESDAY: dayNum = 5;
                        break;
                        case THURSDAY: dayNum = 6;
                        break;
                    }

                    LocalDate newMuDay = dateSub.minusDays(dayNum);
                    muRelInt = relDateFormat.compareTo(newMuDay);
                    
                    //printing albums from the past week only
                    if (muRelInt >= 0) {
                        System.out.println(artistName + " - " + albumName);
                    }
                }
            }
        }
    }
}