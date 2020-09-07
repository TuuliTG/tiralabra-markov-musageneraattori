/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markovgeneraattori;

/**
 *
 * @author tgtuuli
 */
public class Rytmi {
    
    
   public double getKestoAsDouble(byte rytmi) {
       if(rytmi == 0) {
           return 4;
       }
       if(rytmi == 1) {
           return 3;
       }
       if(rytmi == 2) {
           return 2;
       }
       if(rytmi == 3) {
           return 1;
       }
       if(rytmi == 4) {
           return 1.5;
       }
       if(rytmi == 5) {
           return 0.5;
       }
       if(rytmi == 6) {
           return 0.75;
       }
       if(rytmi == 7) {
           return 0.25;
       } else {
           return 0;
       }
       
   }
   
   public byte getRytmiBytena(double kesto) {
       if(kesto == 4) {
           return 0;
       }
       if(kesto == 3) {
           return 1;
       }
       if(kesto == 2) {
           return 2;
       }
       if(kesto == 1) {
           return 3;
       }
       if(kesto == 1.5) {
           return 4;
       }
       if(kesto == 0.5) {
           return 5;
       }
       if(kesto == 0.75) {
           return 6;
       }
       if(kesto == 0.25) {
           return 7;
       } else {
           return 0;
       }
       
   }
   
   
}
