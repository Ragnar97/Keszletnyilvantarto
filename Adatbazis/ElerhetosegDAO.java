/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adatbazis;

import Model.elerhetosegAlapModell;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ndavid97
 */
public class ElerhetosegDAO implements DAO<elerhetosegAlapModell>{

    @Override
    public void vegrehajtSQL(String sql) throws SQLException {
         Connection con=AdatbazisKapcsolat.getKapcsolat();
        PreparedStatement st=con.prepareStatement(sql);
        
         st.execute();
         con.close();
    }

    @Override
    public ArrayList<elerhetosegAlapModell> vegrehajtVisszaadSQL(String sql) throws SQLException {
            Connection con=null;
      Statement st=null;
      ResultSet rs=null;
      ArrayList<elerhetosegAlapModell> lista=new ArrayList<>();
      
       try {
            con=AdatbazisKapcsolat.getKapcsolat();
            st=con.createStatement();

           rs=st.executeQuery(sql);
           
           while(rs.next()){
             lista.add(new elerhetosegAlapModell(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8)));
       
           }
 return lista;
       } catch (SQLException ex) {
           Logger.getLogger(TermekDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
       finally{
          try { rs.close(); } catch (Exception e) { }
    try { st.close(); } catch (Exception e) { }
    try { con.close(); } catch (Exception e) { }
       }
     
return lista;
    }

    @Override
    public void Beszuras(elerhetosegAlapModell obj) throws SQLException {
        String sql="Insert into Elérhetőség(email,telefon,irsz,város,utca,házszám,weblap) values('"+obj.getEmail()+"','"+obj.getTelefon()+"','"+obj.getIrsz()+"','"+obj.getVarros()+"','"+obj.getUtca()+"','"+obj.getHazszam()+"','"+obj.getWeblap()+"') ";
        vegrehajtSQL(sql);
    }

    @Override
    public void Frissites(elerhetosegAlapModell obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Torles(elerhetosegAlapModell obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<elerhetosegAlapModell> Lekerdezes() throws SQLException {
       String sql="Select * from Elérhetőség";
       ArrayList<elerhetosegAlapModell> lista= vegrehajtVisszaadSQL(sql);
    return lista;}

    @Override
    public ArrayList<elerhetosegAlapModell> Lekerdezes2(elerhetosegAlapModell obj) throws SQLException {
//         ArrayList<elerhetosegAlapModell> elerhetosegLista=null;
//        String sql="Select * from Elérhetőség ";
//        System.out.println(sql);
//        elerhetosegLista=vegrehajtVisszaadSQL(sql);
//        return elerhetosegLista;
    return null;}

    @Override
    public Object[][] adatokTablaba(ArrayList<elerhetosegAlapModell> lekerdezes) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<elerhetosegAlapModell> Szures(String oszlop, String kulcs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
