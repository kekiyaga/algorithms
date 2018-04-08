/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randomprograms;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kuteesa Kiyaga
 * @Date April 7th, 2018
 * @Function Simultaneously run multiple forms of the same code block to demonstrate how long each form ran for
 */
public class ConcurrentCount implements Runnable {
    
    //method that will simultaneously run multiple threads
    //containing the statements within it
    public void run() {
        //generate a random number of milliseconds ranging from 2000 to 6000 milliseconds
        int randNum = 2000 + ((int) (Math.random() * 5)) * 1000;
        
        //variable storing the date & time for EST timezone
        //prior to the program waiting a random number of milliseconds
        ZonedDateTime initialTime = Instant.now().atZone(ZoneId.of("US/Eastern"));
        
        //surround sleep method with try-catch block in case of a InterruptedException error
        try {
            //make concurrent thread wait a set number of milliseconds
            //as defined by randNum's value
            Thread.sleep(randNum);
        } catch (InterruptedException ex) {
            //log the the caught exception as severe for the InterruptedException
            Logger.getLogger(ConcurrentCount.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //variable storing the date & time for EST timezone
        //after the program waits a random number of milliseconds
        ZonedDateTime timeAfterWait = Instant.now().atZone(ZoneId.of("US/Eastern"));
        //variable storing the duration between the times before and after the program waited
        Duration duration = Duration.between(initialTime, timeAfterWait);
        
        
        
        //variable to store the formatter used to trandform the minutes and seconds into double digits
        DecimalFormat doubleDigitFormat = new DecimalFormat("00");
        //format the minutes into double digits
        String minutes = doubleDigitFormat.format(duration.toMinutes());
        //format the seconds into double digits
        String seconds = doubleDigitFormat.format(duration.get(ChronoUnit.SECONDS));
        
        
        //variable to format the times before and after the program waits
        //ex. April 07, 2018 10:05:03 PM
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm:ss a");
        //time before program waited formatted using timeFormat
        String initialTimeFormatted = initialTime.format(timeFormat);
        //time before program waited formatted using timeFormat
        String afterWaitFormatted = timeAfterWait.format(timeFormat);
        
        //print the times before and after the program waits, the duration in minutes & seconds, 
        //and how many threads are simultaneously running at once prior to the program finishing
        System.out.println("Initial time: " + initialTimeFormatted 
                + " | Time after waiting " + (randNum/1000) + " seconds: " + afterWaitFormatted 
                + " | Thread duration: " + minutes + ":" + seconds 
                + " | Active concurrent threads: " + Thread.activeCount());
    }
    
    public static void main(String args[]) {
        //repeat thread execution five times
        for (int a = 0; a < 5; a++) {
            //execute a thread based on the run method's code block
            (new Thread(new ConcurrentCount())).start();
        }
    }
}
