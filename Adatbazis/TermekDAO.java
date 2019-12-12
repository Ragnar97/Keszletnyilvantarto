/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adatbazis;

import Model.TermekModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.KategoriaModell;
import java.sql.ResultSetMetaData;
import java.util.Iterator;
import Controller.Validalas;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author ndavid97
 */
public class TermekDAO implements DAO<TermekModel>{
 private KategDAO kategoria=new KategDAO();
 private Validalas vizsgalat=new Validalas();
 private int id=0;
 
    @Override
   public void vegrehajtSQL(String sql) throws SQLException{
      
        Connection con=AdatbazisKapcsolat.getKapcsolat();
        PreparedStatement st=con.prepareStatement(sql);
        
         st.execute();
         con.close();
             }
   @Override
    public ArrayList<TermekModel> vegrehajtVisszaadSQL(String sql) throws SQLException{
        
      Connection con=null;
      Statement st=null;
      ResultSet rs=null;
      ArrayList<TermekModel> lista=new ArrayList<>();
      
       
            con=AdatbazisKapcsolat.getKapcsolat();
            st=con.createStatement();

           rs=st.executeQuery(sql);
          
           while(rs.next()){
               if(rs.getByte(7)==0){
             lista.add(new TermekModel(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getByte(7)));
               }
           }
            rs.close();
            st.close();
            con.close();
           
return lista;}  
    
    @Override
    public void Beszuras(TermekModel obj) throws SQLException {
        
        ArrayList<KategoriaModell> kategLista=null;
 kategLista=kategoria.vegrehajtVisszaadSQL("Select * from Kategória where kategórianév="+"'"+obj.getKategoria()+"'"+"");
id=kategLista.get(0).getKategoriaID();
        String sql="Insert into Termék(terméknév,kategóriaID,mennyiség,egységár,leírás) values('"+obj.getTermekNev()+"','"+id+"','"+obj.getMennyiseg()+"','"+obj.getEgysegar()+"','"+obj.getLeiras()+"') ";
               
                
        vegrehajtSQL(sql);
    }

    @Override
    public void Frissites(TermekModel obj) throws SQLException {
        ArrayList<KategoriaModell> kategLista=null;
        kategLista=kategoria.vegrehajtVisszaadSQL("Select * from Kategória where kategórianév="+"'"+obj.getKategoria()+"'"+"");
id=kategLista.get(0).getKategoriaID();
        String sql = "Update Termék set terméknév='"+obj.getTermekNev() + "',kategóriaID='"+id + "',mennyiség='"
                +obj.getMennyiseg() + "',egységár='"+obj.getEgysegar() + "',leírás='"+obj.getLeiras()+
                "' where termékID='"+obj.getTermekID()+"'";
        vegrehajtSQL(sql);
    }

    @Override
    public void Torles(TermekModel obj) throws SQLException {//Logikai törlés
        String sql="Update Termék set [törölt-e]=1 where '"+obj.getTermekID()+"'=termékID";
        vegrehajtSQL(sql);
        
    }

    @Override
    public ArrayList<TermekModel> Lekerdezes() throws SQLException {
         ArrayList<TermekModel> lekérdLista=null;
        String sql="Select termékID,terméknév,kategórianév,mennyiség,egységár,leírás,[törölt-e]"
            + " from Termék Inner Join Kategória ON Termék.kategóriaID=Kategória.kategóriaID";
        lekérdLista=vegrehajtVisszaadSQL(sql);
        return lekérdLista;
    }
    @Override
    public ArrayList<TermekModel> Lekerdezes2(TermekModel obj)throws SQLException{
        ArrayList<TermekModel> lekerdLista=null;
    String sql="Select * from Termék where id='"+obj.getTermekID()+"'";
         lekerdLista=vegrehajtVisszaadSQL(sql);
        return lekerdLista;
    }
    @Override
    public ArrayList<TermekModel> Szures(String oszlop, String ertek) throws SQLException {
        String sql = "";
        Pattern minta = Pattern.compile("[<=>]");
        Matcher egyezes = minta.matcher(ertek);
        if (egyezes.find()) {
            sql = "Select * from Termék where " + oszlop + "" + ertek + "";
        } else {
            sql = "Select termékID,terméknév,kategórianév,mennyiség,egységár,leírás,[törölt-e]"
            + " from Termék,Kategória where Termék.kategóriaID=Kategória.kategóriaID and " + oszlop + "  Like '%" + ertek + "%'";
        }
        System.out.println(sql);
        ArrayList<TermekModel> lekérdLista = null;

        lekérdLista = vegrehajtVisszaadSQL(sql);
        return lekérdLista;
    }

   public Object[][] adatokTablaba(ArrayList<TermekModel> lekerdezes) throws SQLException{
   
   ArrayList<TermekModel> lista=lekerdezes;
   for (Iterator<TermekModel> iterator = lista.iterator(); iterator.hasNext();) {
            TermekModel next = iterator.next();
            if(next.getTorolt()!=0){
            iterator.remove();
            }
        }
   Object[][] sor = new Object[lista.size()][6];
   
   for (int i = 0; i < lista.size(); i++) {
           sor[i][0] = lista.get(i).getTermekID();
            sor[i][1] = lista.get(i).getTermekNev();
            sor[i][2] = lista.get(i).getKategoria();
            sor[i][3] = lista.get(i).getMennyiseg();
            sor[i][4] = lista.get(i).getEgysegar();
            sor[i][5] = lista.get(i).getLeiras();
        }
   
   return sor;}
//    @Override
//    public ArrayList<TermékModel> getMind() throws SQLException {
//        ArrayList lekérdLista=null;
//        String sql="Select * from Termékek";
//        ResultSet rs= végrehajtVisszaadSQL(sql);
//    while(rs.next()){
//        TermékModel modell=new TermékModel(rs.getInt(1)+"\n"+rs.getString(2)+"\n"+rs.getString(3)+"\n"+rs.getInt(4)+"\n"+rs.getInt(5)+"\n"+rs.getString(6));
//        lekérdLista.add(modell);
//    }
//    return lekérdLista;}
//
//    @Override
//    public ResultSet getRrsAll() throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public ResultSet search(TermékModel obj, String colName, String key) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
}
