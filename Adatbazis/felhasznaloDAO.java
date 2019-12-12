/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adatbazis;


import Model.felhasznaloModell;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ndavid97
 */
public class felhasznaloDAO implements DAO<felhasznaloModell>{

    @Override
    public void vegrehajtSQL(String sql) throws SQLException {
        Connection con=AdatbazisKapcsolat.getKapcsolat();
        PreparedStatement st=con.prepareStatement(sql);
        
         st.execute();
         con.close();
    }

    @Override
    public ArrayList<felhasznaloModell> vegrehajtVisszaadSQL(String sql) throws SQLException {
        Connection con=null;
      Statement st=null;
      ResultSet rs=null;
      ArrayList<felhasznaloModell> lista=new ArrayList<>();
      
       
            con=AdatbazisKapcsolat.getKapcsolat();
            st=con.createStatement();

           rs=st.executeQuery(sql);
           
           while(rs.next()){
               if(rs.getByte(5)==0){
             lista.add(new felhasznaloModell(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4),rs.getByte(5)));
               }
           }

       
      
          rs.close();
        st.close();
        con.close();
       
     
return lista;
    }

    @Override
    public void Beszuras(felhasznaloModell obj) throws SQLException {
        String sql="Insert into Felhasználó (felhnév,jelszó,jogkör,nyomtatottnév) values('"+obj.getFelhNev()+"','"+obj.getJelszo()+"','"+obj.getJogkor()+"','"+obj.getNyomtatottNev()+"')";
        vegrehajtSQL(sql);
    }

    @Override
    public void Frissites(felhasznaloModell obj) throws SQLException {
        String sql="Update Felhasználó set jelszó='"+obj.getJelszo()+"',jogkör='"+obj.getJogkor()+"',nyomtatottnév='"+obj.getNyomtatottNev()+"' where felhnév='"+obj.getFelhNev()+"'";
        vegrehajtSQL(sql);
    }

    @Override
    public void Torles(felhasznaloModell obj) throws SQLException {
        String sql="Update Felhasználó set [törölt-e]=1 where felhnév='"+obj.getFelhNev()+"'";
        vegrehajtSQL(sql);
    }

    @Override
    public ArrayList<felhasznaloModell> Lekerdezes() throws SQLException {
        String sql="Select * from Felhasználó where [törölt-e]=0";
        ArrayList<felhasznaloModell> lekérdLista=null;
   
   lekérdLista=vegrehajtVisszaadSQL(sql);
   return lekérdLista;
    }

    @Override
    public ArrayList<felhasznaloModell> Lekerdezes2(felhasznaloModell obj) throws SQLException {
        return null;}

    @Override
    public ArrayList<felhasznaloModell> Szures(String oszlop, String kulcs) throws SQLException {
        String sql="Select * from Felhasználó where "+oszlop+" Like '"+kulcs+"%'";
        System.out.println(sql);
       ArrayList<felhasznaloModell> lekérdLista=null;
   
   lekérdLista=vegrehajtVisszaadSQL(sql);
        return lekérdLista;
    }
     public Object[][] adatokTablaba(ArrayList<felhasznaloModell> lekerdezes) throws SQLException{
   
   ArrayList<felhasznaloModell> lista=lekerdezes;
   for (Iterator<felhasznaloModell> iterator = lista.iterator(); iterator.hasNext();) {
            felhasznaloModell next = iterator.next();
            if(next.getTorolt()!=0){
            iterator.remove();
            }
        }
        Object[][] sor = new Object[lista.size()][4];
for (int i = 0; i < lista.size(); i++) {
        sor[i][0] = lista.get(i).getNyomtatottNev();
        sor[i][1] = lista.get(i).getFelhNev();
        sor[i][2] = lista.get(i).getJelszo();
        sor[i][3] = lista.get(i).getJogkor();
        
}
        return sor;
    }
    
}
