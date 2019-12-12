/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adatbazis;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ndavid97
 */
public  interface DAO <A>{
    
    public void vegrehajtSQL(String sql) throws SQLException;
    public ArrayList<A> vegrehajtVisszaadSQL(String sql) throws SQLException;
    public void Beszuras(A obj) throws SQLException;
    public void Frissites(A obj) throws SQLException;
    public void Torles(A obj) throws SQLException;
    public ArrayList<A> Lekerdezes() throws SQLException;
    public ArrayList<A> Lekerdezes2(A obj)throws SQLException;
    public Object[][] adatokTablaba(ArrayList<A> termek)throws SQLException;
    
//    ArrayList<A> getMind() throws SQLException;
//    ResultSet getRrsAll() throws SQLException;
//    
   public ArrayList<A>  Szures(String oszlop,String kulcs) throws SQLException;
}
