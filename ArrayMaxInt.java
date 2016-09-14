/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sidelined;

/**
 *
 * @author ddd
 */
public class ArrayMaxInt {
    public static void main(String[] args) {
        
        //for (int x = 0; x < 13; x++) {
            String meme = "15669926;112657;1052572;1640579;3949570;336140;20681670;176257;1415060;7007797;149244;1212956;4119265";

            String[] split = meme.split(";");

            int[] intArr = new int[split.length];

            int maxNum = new Integer(split[0]);
            int compNum = 0;

            for (int i = 0; i < split.length; i++) {
                compNum = new Integer(split[i]);

                if (compNum > maxNum) {
                    maxNum = new Integer(split[i]);
                    System.out.println(maxNum + " / " + i);
                }
            }
        //}
    }
}