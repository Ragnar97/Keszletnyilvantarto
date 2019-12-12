/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author ndavid97
 */
public class KeszletNyilvantarto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Bejelentkezes beAblak= new Bejelentkezes();
       
       beAblak.setVisible(true);
       new MenedzserKliens().setVisible(true);
    }
    
}
