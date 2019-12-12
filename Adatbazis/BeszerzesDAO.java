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
import Model.BeszerzesModell;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
public class BeszerzesDAO implements DAO<BeszerzesModell>{

    @Override
    public void vegrehajtSQL(String sql) throws SQLException {
                Connection con=AdatbazisKapcsolat.getKapcsolat();
        PreparedStatement st=con.prepareStatement(sql);
        
         st.execute();
         con.close();
    }

    @Override
    public ArrayList<BeszerzesModell> vegrehajtVisszaadSQL(String sql) throws SQLException {
        Connection con=null;
      Statement st=null;
      ResultSet rs=null;
      ArrayList<BeszerzesModell> lista=new ArrayList<>();
      
       
            con=AdatbazisKapcsolat.getKapcsolat();
            st=con.createStatement();

           rs=st.executeQuery(sql);
          
           while(rs.next()){
               if(rs.getByte(6)==0){
             lista.add(new BeszerzesModell(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5),rs.getByte(6)));
               }
       
           }

       
           rs.close();
        st.close();
        con.close();
       
     
return lista;
    }

    @Override
    public void Beszuras(BeszerzesModell obj) throws SQLException {
        String sql="Insert into Beszerzés (beszállítóID,TermékID,mennyiség) values ('"+obj.getBeszallitoID()+"','"+obj.getTermekID()+"','"+obj.getMennyiseg()+"')";
        vegrehajtSQL(sql);
    }

    @Override
    public void Frissites(BeszerzesModell obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Torles(BeszerzesModell obj) throws SQLException {
        String sql="Update Beszerzés set [törölt-e]=1";
        vegrehajtSQL(sql);
    }

    @Override
    public ArrayList<BeszerzesModell> Lekerdezes() throws SQLException {
        String sql="Select * from Rendelés";
        ArrayList<BeszerzesModell> lista=null;
        lista=vegrehajtVisszaadSQL(sql);
    return lista;}

    @Override
    public ArrayList<BeszerzesModell> Lekerdezes2(BeszerzesModell obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[][] adatokTablaba(ArrayList<BeszerzesModell> lekerdezes) throws SQLException {
        ArrayList<BeszerzesModell> lista=lekerdezes;
   for (Iterator<BeszerzesModell> iterator = lista.iterator(); iterator.hasNext();) {
            BeszerzesModell next = iterator.next();
            if(next.getTorolt()!=0){
            iterator.remove();
            }
        }
   Object[][] sor = new Object[lista.size()][5];
   
   for (int i = 0; i < lista.size(); i++) {
           sor[i][0] = lista.get(i).getBeszerzesID();
            sor[i][1] = lista.get(i).getBeszallitoID();
            sor[i][2] = lista.get(i).getTermekID();
            sor[i][3] = lista.get(i).getMennyiseg();
            sor[i][4] = lista.get(i).getDatum();
            
    }
    return sor;}
    @Override
    public ArrayList<BeszerzesModell> Szures(String oszlop, String kulcs) throws SQLException {
       String sql="Select * from Beszerzés where "+oszlop+""+kulcs+"";
         
        System.out.println(sql);
       ArrayList<BeszerzesModell> lekerdLista=null;
   
   lekerdLista=vegrehajtVisszaadSQL(sql);
    return lekerdLista;}
    
}
