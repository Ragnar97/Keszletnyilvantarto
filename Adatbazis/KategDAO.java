/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adatbazis;

import Model.TermekModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.KategoriaModell;
import java.sql.PreparedStatement;

/**
 *
 * @author ndavid97
 */
public class KategDAO implements DAO<KategoriaModell>{

    @Override
    public void vegrehajtSQL(String sql) throws SQLException {
         Connection con=AdatbazisKapcsolat.getKapcsolat();
        PreparedStatement st=con.prepareStatement(sql);
        
         st.execute();
         con.close();
    }

    @Override
    public ArrayList<KategoriaModell> vegrehajtVisszaadSQL(String sql) throws SQLException {
         Connection con=null;
      Statement st=null;
      ResultSet rs=null;
      ArrayList<KategoriaModell> lista=new ArrayList<>();
      
       
            con=AdatbazisKapcsolat.getKapcsolat();
            st=con.createStatement();

           rs=st.executeQuery(sql);
           
           while(rs.next()){
             lista.add(new KategoriaModell(rs.getInt(1),rs.getString(2)));
               System.out.println(rs.getInt(1)+" "+rs.getString(2));
       
           }

       
          rs.close();
        st.close();
        con.close(); 
       
     
return lista;
    }

    @Override
    public void Beszuras(KategoriaModell obj) throws SQLException {
        String sql="Insert into Kategória (kategórianév) values('"+obj.getKategoriaNev()+"')";
        vegrehajtSQL(sql);
    }

    @Override
    public void Frissites(KategoriaModell obj) throws SQLException {
//        String sql="Update Kategória set kategórianév='"+obj.getKategNév()+"' where";
//        végrehajtSQL(sql);
    }

    @Override
    public void Torles(KategoriaModell obj) throws SQLException {
        String sql="Delete from Kategória where kategóriaID='"+obj.getKategoriaID()+"'";
    }

    @Override
    public ArrayList<KategoriaModell> Lekerdezes() throws SQLException {
        ArrayList<KategoriaModell> lekérdLista=null;
        String sql="Select * from Kategória ";
        lekérdLista=vegrehajtVisszaadSQL(sql);
        return lekérdLista;
    }

    @Override
    public ArrayList<KategoriaModell> Lekerdezes2(KategoriaModell kateg) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 

   public  ArrayList<KategoriaModell> Szures(String cella,String kulcs) throws SQLException{
       String sql="Select * from Termékek where '"+cella+""+kulcs+"'";
       ArrayList<KategoriaModell> lekérdLista=null;
   
   lekérdLista=vegrehajtVisszaadSQL(sql);
        return lekérdLista;}
 public Object[][] adatokTablaba(ArrayList<KategoriaModell> lekerdezes) throws SQLException{
    
    
return null;}
}
