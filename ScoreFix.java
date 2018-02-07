/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Kuteesa Kiyaga
 * @date February 7, 2018
 * @function A class designed to sort items based on their average score multiplied by the number of times it is scored.
 */
public class ScoreFix {
    
    //variable that stores the compared value
    private double compVal;
    //variable that stores the current maximum value
    private double maxVal;
    
    //method that multiplies the score with the number of critics
    //while adjusting the scale to 100
    public double scoreFix (double score, double scale, int sum) {
        //variable stores the value that the score multiplies itself by
        //to be within a scale of 100
        double scaleHund = (100/scale);
        //variable stores the score multiplied by the scaleHund value
        double scoreHund = score * scaleHund;
        //variable stores the value of the scored (scaled to 100) multiplied by the sum
        double scoreDiv = scoreHund * sum;
        
        //return the scoreDiv variable as the methdod output
        return scoreDiv;
    }
    
    //method that takes a maximum value, a value to be compared to 
    public double maxInt(double maxNum, double compNum, int index, int loopSize) {
        
        //initialize the maximum number to the first passed maxNum value
        //when the method is called
        if ((index == 0)) {
            maxVal = maxNum;
        }
        
        //variable stores the number being compared to the maximum value
        compVal = compNum;
        
        //set the maximuum value to the compared value if the compared value 
        //exceeds the current maximum value
        if (compVal > maxVal) {
            maxVal = compVal;
        }
        
        //return the maximum value after the loop is complete
        return maxVal;
    }
    
    public static void main(String[] args) {
        //declare a new class to use its score fix & maximum value methods
        ScoreFix scoreFix = new ScoreFix();
        
        //averaged score value from specified number of critics
        double score;
        //the maximum possible score a value can achieve
        int scale;
        //number of critics that scored an item
        int sum;
        //variable that stores 
        double fixedScore;
        
        //create a loop that repeats the block within its braces five times
        for (int a = 0; a < 5; a++) {
            //assign a value between twenty and thirty to the score value
            score = 20 + (Math.random() * 10);
            //assign a value between 100 and 130 for the sum value
            sum = (int) (100 + (Math.random() * 30));
            //set the scale 
            scale = 30;
            
            //print the score, scale, and sum
            System.out.println("Score: " + score + " | Scale: " + scale + " | Sum: " + sum);
            
            //store the scoreFix method's returned value in a variable
            fixedScore = scoreFix.scoreFix(score, scale, sum);
            
            //print the initial current maximum value
            System.out.print("Current maximum value: " + scoreFix.maxVal + " | Current compared value: ");
            
            //call a method to determine whether or not the compared value exceeds the current maximum value
            scoreFix.maxInt(fixedScore, fixedScore, a, 5);
            
            //print the compared value
            System.out.print(scoreFix.compVal);
            System.out.println();
            System.out.println();
        }
    }
}
