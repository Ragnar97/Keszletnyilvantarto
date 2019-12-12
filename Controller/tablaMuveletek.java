/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Adatbazis.TermekDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ndavid97
 */
public class tablaMuveletek {
    private TermekDAO dao=new TermekDAO();
    public DefaultTableModel tablaFeltolt(Object[][] termek,JTable tabla) throws SQLException {
       
        
        DefaultTableModel tábla = (DefaultTableModel) tabla.getModel();
           tábla.setRowCount(0);
        Object[][] vissza=termek;
        Object[] feltolt;
        
        for (int i = 0; i < vissza.length; i++) {
            feltolt=vissza[i];
            tábla.addRow(feltolt);
        }

        
    return tábla;}
    
    public void tablaFrissit(Object[][] termek,JTable tabla) throws SQLException{
        DefaultTableModel tabl=tablaFeltolt(termek,tabla);
        tabl.setRowCount(0);
        tablaFeltolt(termek,tabla);
        
    
    }
}
