/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author ndavid97
 */
import Controller.BejelentkezesController;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
public class Main {
    private static  BejelentkezesController bejelentkezes=null;

    public static void main(String[] args) {
        try {
            bejelentkezes=new BejelentkezesController();
        } catch (SQLException ex) {
            new JDialog(bejelentkezes.getBejelentAblak(), "Adatbázis hiba történt");
        }
    }
    
    
    
}
