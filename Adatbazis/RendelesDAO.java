/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adatbazis;

/**
 *
 * @author ndavid97
 */
import Model.RendelesModell;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
public class RendelesDAO implements DAO<RendelesModell>{

    @Override
    public void vegrehajtSQL(String sql) throws SQLException {
        Connection con=AdatbazisKapcsolat.getKapcsolat();
        PreparedStatement st=con.prepareStatement(sql);
        
         st.execute();
         con.close();
    }

    @Override
    public ArrayList<RendelesModell> vegrehajtVisszaadSQL(String sql) throws SQLException {
        Connection con=null;
      Statement st=null;
      ResultSet rs=null;
      ArrayList<RendelesModell> lista=new ArrayList<>();
      
       
            con=AdatbazisKapcsolat.getKapcsolat();
            st=con.createStatement();

           rs=st.executeQuery(sql);
          
           while(rs.next()){
               if(rs.getByte(7)==0){
             lista.add(new RendelesModell(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6),rs.getByte(7)));
               }
       
           }
 
       
           rs.close();
        st.close();
        con.close(); 
       
     
return lista;
    }

    @Override
    public void Beszuras(RendelesModell obj) throws SQLException {
        String sql="Insert into Rendelés (rendelésID,termékID,vevőID,mennyiség,végösszeg) values('"+obj.getRendelesID()+"','"+obj.getTermekID()+"','"+obj.getVevoID()+"','"+obj.getMennyiseg()+"','"+obj.getVegOsszeg()+"')";
        vegrehajtSQL(sql);
    }

    @Override
    public void Frissites(RendelesModell obj) throws SQLException {
        String sql="Update Rendelés set ";
        vegrehajtSQL(sql);
    }

    @Override
    public void Torles(RendelesModell obj) throws SQLException {
        String sql="Update Rendelés set [törölt-e]=1";
        vegrehajtSQL(sql);
    }

    @Override
    public ArrayList<RendelesModell> Lekerdezes() throws SQLException {
        String sql="Select * from Rendelés";
        ArrayList<RendelesModell> lista=null;
        lista=vegrehajtVisszaadSQL(sql);
    return lista;}

    @Override
    public ArrayList<RendelesModell> Lekerdezes2(RendelesModell obj) throws SQLException {
//        ArrayList<RendelesModell> lista=null;
//        String sql="Select * from Rendelés";
//        lista=végrehajtVisszaadSQL(sql);
//    return lista;
    return null;}

    @Override
    public Object[][] adatokTablaba(ArrayList<RendelesModell> lekerdezes) throws SQLException {
           ArrayList<RendelesModell> lista=lekerdezes;
   for (Iterator<RendelesModell> iterator = lista.iterator(); iterator.hasNext();) {
            RendelesModell next = iterator.next();
            if(next.getTorolt()!=0){
            iterator.remove();
            }
        }
   Object[][] sor = new Object[lista.size()][6];
   
   for (int i = 0; i < lista.size(); i++) {
           sor[i][0] = lista.get(i).getRendelesID();
            sor[i][1] = lista.get(i).getKelt();
            sor[i][2] = lista.get(i).getVevoID();
            sor[i][3] = lista.get(i).getTermekID();
            sor[i][4] = lista.get(i).getMennyiseg();
            sor[i][4] = lista.get(i).getVegOsszeg();
            
    }
    return sor;
    }

    @Override
    public ArrayList<RendelesModell> Szures(String oszlop, String kulcs) throws SQLException {
        String sql="Select * from Rendelés where "+oszlop+""+kulcs+"";
         
        System.out.println(sql);
       ArrayList<RendelesModell> lekerdLista=null;
   
   lekerdLista=vegrehajtVisszaadSQL(sql);
    return lekerdLista;}
    
}
