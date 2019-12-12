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
import Model.TermekModel;
import Model.beszallitoModell;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class beszallitoDAO implements DAO<beszallitoModell>{
private ElerhetosegDAO elerhetoseg=new ElerhetosegDAO();
      @Override
   public void vegrehajtSQL(String sql) throws SQLException{
      
        Connection con=AdatbazisKapcsolat.getKapcsolat();
        PreparedStatement st=con.prepareStatement(sql);
        
         st.execute();
         con.close();
             }
   @Override
    public ArrayList<beszallitoModell> vegrehajtVisszaadSQL(String sql) throws SQLException {
        
      Connection con=null;
      Statement st=null;
      ResultSet rs=null;
      ArrayList<beszallitoModell> lista=new ArrayList<>();
      
       
            con=AdatbazisKapcsolat.getKapcsolat();
            st=con.createStatement();

           rs=st.executeQuery(sql);
           
           while(rs.next()){
               if(rs.getByte(4)==0){
             lista.add(new beszallitoModell(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getByte(4),rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getString(11)));
               }
           }
        
       
       rs.close();
       st.close();
       con.close(); 
       
     
return lista;}

    @Override
    public void Beszuras(beszallitoModell obj) throws SQLException {
         ArrayList<elerhetosegAlapModell> segedLista=new ArrayList<>();
        elerhetoseg.Beszuras(obj);
                segedLista=elerhetoseg.vegrehajtVisszaadSQL("SELECT * FROM Elérhetőség WHERE elérhetőségID = (SELECT MAX(elérhetőségID) FROM Elérhetőség)");
        int id=segedLista.get(0).getElerhetosegID();
        String sql="Insert into Beszállító(beszállítónév,elérhetőségID) values('"+obj.getBeszallitoNev()+"','"+id+"')";
                
        vegrehajtSQL(sql);
    }

    @Override
    public void Frissites(beszallitoModell obj) throws SQLException {
        String sql="Update Beszállítók set beszállítónév='"+obj.getBeszallitoNev()+"' where beszállítóID='"+obj.getBeszallitoNev()+"'"
                +"Update Elérhetőség set email='"+obj.getEmail()+"',telefon='"+obj.getTelefon()+"',irsz='"+obj.getIrsz()+"',város='"+obj.getVarros()+"',utca='"+obj.getUtca()+"',házszám='"+obj.getHazszam()+"',weblap='"+obj.getWeblap()+"'"
                + "where elérhetőségID='"+obj.getElerhetosegID()+"'";
        vegrehajtSQL(sql);
    }

    @Override
    public void Torles(beszallitoModell obj) throws SQLException {
       String sql="Update Beszállító set [törölt-e]=1 where '"+obj.getBeszallitoID()+"'=termékID";
        vegrehajtSQL(sql);
    }

    @Override
    public ArrayList<beszallitoModell> Lekerdezes() throws SQLException {
          ArrayList<beszallitoModell> beszallLista=null;
          String sql="Select Beszállító.beszállítóID, Beszállító.beszállítónév, Beszállító.elérhetőségID,[törölt-e],email,telefon,irsz,város,utca,házszám,weblap\n" +
"from Beszállító,Elérhetőség \n" +
"where Beszállító.elérhetőségID=Elérhetőség.elérhetőségID";
          beszallLista=vegrehajtVisszaadSQL(sql);
    return beszallLista;}

    @Override
    public ArrayList<beszallitoModell> Lekerdezes2(beszallitoModell obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<beszallitoModell> Szures(String oszlop, String ertek) throws SQLException {
          String sql="";
        Pattern minta=Pattern.compile("[<=>]");
        Matcher egyezes=minta.matcher(ertek);
        if (egyezes.find()) {
             sql="Select Beszállító.beszállítóID, Beszállító.beszállítónév, Beszállító.elérhetőségID,[törölt-e],email,telefon,irsz,város,utca,házszám,weblap\n" +
"from Beszállító,Elérhetőség \n" +
"where Beszállító.elérhetőségID=Elérhetőség.elérhetőségID and "+oszlop+" "+ertek+"";
        }
        else{
            sql="Select Beszállító.beszállítóID, Beszállító.beszállítónév, Beszállító.elérhetőségID,[törölt-e],email,telefon,irsz,város,utca,házszám,weblap\n" +
"from Beszállító,Elérhetőség \n" +
"where Beszállító.elérhetőségID=Elérhetőség.elérhetőségID and "+oszlop+"  Like '%"+ertek+"%'";
        }
       ArrayList<beszallitoModell> lekérdLista=null;
   
   lekérdLista=vegrehajtVisszaadSQL(sql);
        return lekérdLista;
    }
    public Object[][] adatokTablaba(ArrayList<beszallitoModell> lekerdezes) throws SQLException{
   
   ArrayList<beszallitoModell> lista=lekerdezes;
   for (Iterator<beszallitoModell> iterator = lista.iterator(); iterator.hasNext();) {
            beszallitoModell next = iterator.next();
            if(next.getTorolt()!=0){
            iterator.remove();
            }
        }
   Object[][] sor = new Object[lista.size()][9];
   
   for (int i = 0; i < lista.size(); i++) {
           sor[i][0] = lista.get(i).getBeszallitoID();
            sor[i][1] = lista.get(i).getBeszallitoNev();
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
