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
        
        for (int pgNum = 1; muRelInt >= 0; pgNum++) {
            
            Document aotyGet = Jsoup.connect("http://www.albumoftheyear.org/releases/" + pgNum + "/")
                .userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36")
                .referrer("www.google.com")
                .get();
            
            Elements albumBlock = aotyGet.select("div.wideLeft div.albumBlock");
            
            for (int index = 0; index < albumBlock.size(); index++) {
                
                Element albumGet = albumBlock.get(index);
                
                Elements critBlock = albumGet.select("div.ratingRowContainer div.ratingRow:contains(critic score)");
                Elements userBlock = albumGet.select("div.ratingRowContainer div.ratingRow:contains(user score)");
                
                String criticScore = critBlock.select("div.ratingBlock div.rating").text();                
                String criticSum = critBlock.select("div.ratingText").text();

                String userScore = userBlock.select("div.ratingBlock div.rating").text();                
                String userSum = userBlock.select("div.ratingText").text();
                
                boolean critSumEmpty = critBlock.isEmpty();
                boolean userSumEmpty = userBlock.isEmpty();

                int caseNum = 0;
                
                String[] critSumSplit = null;
                String critSumNum = null;
                Integer critSumInt = null;

                String[] userSumSplit = null;
                String userSumNum = null;
                Integer userSumInt = null;
                
                if (critSumEmpty == false && userSumEmpty == true) {
                    caseNum = 1;
                } else if (critSumEmpty == true && userSumEmpty == false) {
                    caseNum = 2;
                } else if (critSumEmpty == false && userSumEmpty == false) {
                    caseNum = 3;
                } else if (critSumEmpty == true && userSumEmpty == true) {
                    caseNum = 4;
                }
                
                boolean critMinBool = false;
                boolean userMinBool = false;
                
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
                
                if (critMinBool == true || userMinBool == true) {
                    
                    String artistName = albumGet.select("a div.artistTitle").text();
                    String albumName = albumGet.select("a div.albumTitle").text();
                    String relURL = albumGet.select("div.image a").attr("href");
                    
                    Document albumPg = Jsoup.connect(aotyBaseURL + relURL)
                        .userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36")
                        .referrer("www.google.com")
                        .get();
                    
                    String relDateGet = albumPg.select("div.rightBox div.detailRow:contains(Release Date)").text();
                    String dateGetSub = " " + albumPg.select("div.rightBox div.detailRow:contains(Release Date) span").text();
                    String releaseDate = relDateGet.replace(dateGetSub, "").replace(",", "");
                    String[] relDateSplit = releaseDate.split(" ");
                    String monthString = relDateSplit[0];
                    
                    Calendar calendar = Calendar.getInstance();

                    calendar.setTime(new SimpleDateFormat("MMM").parse(monthString));
                    int relMonth = calendar.get(Calendar.MONTH) + 1;

                    Integer relDay = new Integer(relDateSplit[1]);
                    Integer relYear = new Integer(relDateSplit[2]);

                    LocalDate dateSub = LocalDate.now();
                    LocalDate relDateFormat = LocalDate.of(relYear, relMonth, relDay);

                    DayOfWeek dateWeekDay = dateSub.getDayOfWeek();
                    
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
                    
                    if (muRelInt >= 0) {
                        System.out.println(artistName + " - " + albumName);
                    }
                }
            }
        }
    }
}