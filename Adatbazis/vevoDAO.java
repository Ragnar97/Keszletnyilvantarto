/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adatbazis;

import Model.beszallitoModell;
import Model.vevoModell;
import Model.elerhetosegAlapModell;
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
public class vevoDAO implements DAO<vevoModell>{
private ElerhetosegDAO elerhetoseg=new ElerhetosegDAO();

    @Override
    public void vegrehajtSQL(String sql) throws SQLException {
        Connection con=AdatbazisKapcsolat.getKapcsolat();
        PreparedStatement st=con.prepareStatement(sql);
        
         st.execute();
         con.close();
    }

    @Override
    public ArrayList<vevoModell> vegrehajtVisszaadSQL(String sql) throws SQLException {
          Connection con=null;
      Statement st=null;
      ResultSet rs=null;
      ArrayList<vevoModell> lista=new ArrayList<>();
      
       
            con=AdatbazisKapcsolat.getKapcsolat();
            st=con.createStatement();

           rs=st.executeQuery(sql);
           
           while(rs.next()){
               if(rs.getByte(4)==0){
             lista.add(new vevoModell(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getByte(4),rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getString(11)));
               }
           }
 
       
       
         rs.close();
        st.close();
        con.close();
       
     
return lista;
    }

    @Override
    public void Beszuras(vevoModell obj) throws SQLException {
        
        ArrayList<elerhetosegAlapModell> segedLista=new ArrayList<>();
        elerhetoseg.Beszuras(obj);
                segedLista=elerhetoseg.vegrehajtVisszaadSQL("SELECT * FROM Elérhetőség WHERE elérhetőségID = (SELECT MAX(elérhetőségID) FROM Elérhetőség)");
        int id=segedLista.get(0).getElerhetosegID();
        String sqlSeged="Insert into Vevő(vevőnév,elérhetőségID) values('"+obj.getVevonev()+"','"+id+"')";
        
        vegrehajtSQL(sqlSeged);
    }

    @Override
    public void Frissites(vevoModell obj) throws SQLException {
//        int elerhetosegID=végrehajtVisszaadSQL("Select * from Vevő where vevőID='"+obj.getElerhetosegID()+"' ").get(0).getElerhetosegID();
          String sql="Update Vevő set vevőnév='"+obj.getVevonev()+"' where vevőID='"+obj.getVevoID()+"'"
                +"Update Elérhetőség set email='"+obj.getEmail()+"',telefon='"+obj.getTelefon()+"',irsz='"+obj.getIrsz()+"',város='"+obj.getVarros()+"',utca='"+obj.getUtca()+"',házszám='"+obj.getHazszam()+"',weblap='"+obj.getWeblap()+"'"
                  + "where elérhetőségID='"+obj.getElerhetosegID()+"'";
        vegrehajtSQL(sql);
    }

    @Override
    public void Torles(vevoModell obj) throws SQLException {
        String sql="Update Vevő set [törölt-e]=1 where '"+obj.getVevoID()+"'=vevőID";
        vegrehajtSQL(sql);
    }

    @Override
    public ArrayList<vevoModell> Lekerdezes() throws SQLException {
        ArrayList<vevoModell> lista=null;
          String sql="Select vevőID,vevőnév,Vevő.elérhetőségID,[törölt-e],email,telefon,irsz,város,utca,házszám,weblap from vevő,Elérhetőség\n" +
"where Vevő.elérhetőségID=Elérhetőség.elérhetőségID";
         lista= vegrehajtVisszaadSQL(sql);
    return lista;
    }

    @Override
    public ArrayList<vevoModell> Lekerdezes2(vevoModell obj) throws SQLException {
        ArrayList<vevoModell> elerhetosegLista=null;
        
        
        return elerhetosegLista;
    }

    @Override
    public ArrayList<vevoModell> Szures(String oszlop, String kulcs) throws SQLException {
        String sql="Select vevőID,vevőnév,Vevő.elérhetőségID,[törölt-e],email,telefon,irsz,város,utca,házszám,weblap from Vevő,Elérhetőség\n" +
"where vevő.elérhetőségID=Elérhetőség.elérhetőségID and "+oszlop+" Like '"+kulcs+"'";
        System.out.println(sql);
       ArrayList<vevoModell> lekérdLista=null;
   
   lekérdLista=vegrehajtVisszaadSQL(sql);
        return lekérdLista;
    }
     public Object[][] adatokTablaba(ArrayList<vevoModell> lekerdezes) throws SQLException{
   
   ArrayList<vevoModell> lista=lekerdezes;
   for (Iterator<vevoModell> iterator = lista.iterator(); iterator.hasNext();) {
            vevoModell next = iterator.next();
            if(next.getTorolt()!=0){
            iterator.remove();
            }
        }
   Object[][] sor = new Object[lista.size()][9];
   
   for (int i = 0; i < lista.size(); i++) {
            
            sor[i][0] = lista.get(i).getVevoID();
            sor[i][1] = lista.get(i).getVevonev();
            sor[i][2] = lista.get(i).getEmail();
            sor[i][3] = lista.get(i).getTelefon();
            sor[i][4] = lista.get(i).getIrsz();
            sor[i][5] = lista.get(i).getVarros();
            sor[i][6] = lista.get(i).getUtca();
            sor[i][7] = lista.get(i).getHazszam();
            sor[i][8] = lista.get(i).getWeblap();
        }
   
   return sor;}
     }


