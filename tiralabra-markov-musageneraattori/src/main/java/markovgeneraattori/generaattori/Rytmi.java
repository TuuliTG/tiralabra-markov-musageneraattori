/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markovgeneraattori.generaattori;

/**
 * 
 * @author tgtuuli
 */
public class Rytmi {
    
    
   public double getKestoDoublena(byte rytmi) {
       if(rytmi == 1 || rytmi == -1) {
           return 4;
       }
       if(rytmi == 2 || rytmi == -2) {
           return 2;
       }
       if(rytmi == 3 || rytmi == -3) {
           return 3;
       }
       if(rytmi == 4 || rytmi == -4) {
           return 1;
       }
       if(rytmi == 6 || rytmi == -6) {
           return 1.5;
       }
       if(rytmi == 8 || rytmi == -8) {
           return 0.5;
       }
       if(rytmi == 12 || rytmi == -12) {
           return 0.75;
       }
       if(rytmi == 16 || rytmi == -16) {
           return 0.25;
       } else {
           return 0;
       }
       
   }
   
   public byte getRytmiBytena(double kesto) {
       if(kesto == 4) {
           return 1;
       }
       if(kesto == 3) {
           return 3;
       }
       if(kesto == 2) {
           return 2;
       }
       if(kesto == 1) {
           return 4;
       }
       if(kesto == 1.5) {
           return 6;
       }
       if(kesto == 0.5) {
           return 8;
       }
       if(kesto == 0.75) {
           return 12;
       }
       if(kesto == 0.25) {
           return 16;
       } else {
           return 0;
       }
       
   }
   
   public String muunnaBytestaMerkiksi(byte rytmi) {
       if(rytmi == 1) {
           return "1";
       }
       if(rytmi == 2) {
           return "2";
       }
       if(rytmi == 3) {
           return "2.";
       }
       if(rytmi == 4) {
           return "4";
       }
       if(rytmi == 6) {
           return "8.";
       }
       if(rytmi == 8) {
           return "8";
       }
       if(rytmi == 12) {
           return "8.";
       }
       if(rytmi == 16) {
           return "16";
       } else {
           return "0";
       }
   }
   
   
}
