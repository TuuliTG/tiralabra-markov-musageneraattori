/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markovgeneraattori.generaattori;

import markovgeneraattori.tietorakenteet.Taulukkolista;

/**
 *
 * @author tgtuuli
 */
public class RytmiArpoja {
    
    
    private byte[][] tahdit;

    public RytmiArpoja() {
        tahdit = new byte[100][16];
        this.init();
    }
    
    public Taulukkolista<Byte> haeRytmi(int tahtienLkm) {
        Taulukkolista<Byte> rytmi = new Taulukkolista<>();
        
        for (int i = 0; i < tahtienLkm; i++) {
            byte[] r = this.arvoTahti();
            for (int j = 0; j < r.length; j++) {
                if(r[j] == 0){
                    break;
                }
                rytmi.lisaa(r[j]);
            }
        }
        
        return rytmi;
        
    }
    
    private byte[] arvoTahti() {
        int arpa = this.satunnainen(26);
        return tahdit[arpa];
    }
    
    private int satunnainen(int arvo){
        return (int) (System.nanoTime() % arvo);
    }
    
    private void  init() {
        
        tahdit[0][0] = 2;
        tahdit[0][1] = 2;
        
        tahdit[1][0] = 3;
        tahdit[1][1] = -4;
        
        tahdit[2][0] = 2;
        tahdit[2][1] = 4;
        tahdit[2][2] = 4;
        
        tahdit[3][0] = 4;
        tahdit[3][1] = 4;
        tahdit[3][2] = 2;
        
        tahdit[4][0] = 2;
        tahdit[4][1] = 8;
        tahdit[4][2] = 8;
        tahdit[4][3] = 4;
        
        tahdit[5][0] = 2;
        tahdit[5][1] = 4;
        tahdit[5][2] = 8;
        tahdit[5][3] = 8;
        
        tahdit[6][0] = 2;
        tahdit[6][1] = 4;
        tahdit[6][2] = -4;
        
        tahdit[7][0] = 3;
        tahdit[7][1] = 8;
        tahdit[7][2] = 8;
        
        tahdit[8][0] = 8;
        tahdit[8][1] = 8;
        tahdit[8][2] = 8;
        tahdit[8][3] = 8;
        tahdit[8][4] = 8;
        tahdit[8][5] = 8;
        tahdit[8][6] = 8;
        tahdit[8][7] = 8;
        
        tahdit[9][0] = 8;
        tahdit[9][1] = 8;
        tahdit[9][2] = 8;
        tahdit[9][3] = 8;
        tahdit[9][4] = 4;
        tahdit[9][5] = 4;
        
        tahdit[10][0] = 8;
        tahdit[10][1] = 8;
        tahdit[10][2] = 8;
        tahdit[10][3] = 8;
        tahdit[10][4] = 8;
        tahdit[10][5] = 8;
        tahdit[10][6] = 4;
        
        tahdit[11][0] = 8;
        tahdit[11][1] = 8;
        tahdit[11][2] = 4;
        tahdit[11][3] = 8;
        tahdit[11][4] = 8;
        tahdit[11][5] = 4;
        
        tahdit[12][0] = 4;
        tahdit[12][1] = 4;
        tahdit[12][2] = 4;
        tahdit[12][3] = 4;
        
        tahdit[13][0] = 4;
        tahdit[13][1] = 4;
        tahdit[13][2] = 8;
        tahdit[13][3] = 8;
        tahdit[13][4] = 8;
        tahdit[13][5] = 8;
        
        tahdit[14][0] = 4;
        tahdit[14][1] = -4;
        tahdit[14][2] = 8;
        tahdit[14][3] = 8;
        tahdit[14][4] = 4;
        
        tahdit[15][0] = 3;
        tahdit[15][1] = 4;
        
        tahdit[16][0] = 4;
        tahdit[16][1] = -4;
        tahdit[16][2] = 8;
        tahdit[16][3] = 8;
        tahdit[16][4] = 4;
        
        tahdit[17][0] = 8;
        tahdit[17][1] = 8;
        tahdit[17][2] = 8;
        tahdit[17][3] = 8;
        tahdit[17][4] = 4;
        tahdit[17][5] = -4;
        
        tahdit[18][0] = 4;
        tahdit[18][1] = 4;
        tahdit[18][2] = 4;
        tahdit[18][3] = -4;
        
        tahdit[19][0] = 8;
        tahdit[19][1] = 8;
        tahdit[19][2] = 8;
        tahdit[19][3] = 8;
        tahdit[19][4] = 2;
        
        tahdit[20][0] = 4;
        tahdit[20][1] = 8;
        tahdit[20][2] = 8;
        tahdit[20][3] = 6;
        tahdit[20][4] = 8;
        
        tahdit[21][0] = 4;
        tahdit[21][1] = 8;
        tahdit[21][2] = 8;
        tahdit[21][3] = 8;
        tahdit[21][4] = 8;
        tahdit[21][5] = 8;
        tahdit[21][6] = 8;
        
        tahdit[22][0] = 6;
        tahdit[22][1] = 8;
        tahdit[22][2] = 4;
        tahdit[22][3] = 4;
        
        tahdit[23][0] = 4;
        tahdit[23][1] = 8;
        tahdit[23][2] = 8;
        tahdit[23][3] = 4;
        tahdit[23][4] = 4;
        
        tahdit[24][0] = 4;
        tahdit[24][1] = 8;
        tahdit[24][2] = 8;
        tahdit[24][3] = 2;
        
        tahdit[25][0] = 8;
        tahdit[25][1] = 8;
        tahdit[25][2] = 8;
        tahdit[25][3] = 8;
        tahdit[25][4] = 4;
        tahdit[25][5] = 8;
        tahdit[25][6] = 8;
        
        tahdit[26][0] = 6;
        tahdit[26][1] = 8;
        tahdit[26][2] = 4;
        tahdit[26][3] = 8;
        tahdit[26][4] = 8;
               
    }
}
