/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author ndavid97
 */
import Adatbazis.AdatbazisKapcsolat;
import Adatbazis.TermekDAO;
import View.JelentesDialog;
import View.MenedzserKliens;
import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import Adatbazis_Lekerdezesek.RaktarKeszlet;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Header;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.PageAttributes;
import static java.awt.PageAttributes.MediaType.A4;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import javax.swing.JOptionPane;
public class JelentesController {
    private JelentesDialog nezet=null;
    private String[] lekerdLista=null;
    private TermekDAO termek=null;
    private RaktarKeszlet raktarKeszlet=null;
    private ArrayList<ArrayList<Object>> lekerdezesGyujto=new ArrayList<>();
    private ArrayList<String> alCimGyujto=new ArrayList<>();
    private ArrayList<String> foCimGyujto=new ArrayList<>();
    private  Connection con=null;
    private Statement st = null;
    private ResultSet rs = null;
    private String cimSor=null;
    
    public JelentesController() {
         nezet=new JelentesDialog(new MenedzserKliens(), true);
            esemenyKezeles();          
            nezet.setVisible(true);
    }

    public ArrayList<JCheckBox> foCimGyujtes(){
        ArrayList<JCheckBox> rublikak=new ArrayList<>();
        for (Component urlapElem : nezet.getjPanel1().getComponents()) {
            if(urlapElem instanceof JCheckBox) rublikak.add((JCheckBox) urlapElem);
        }
        
        
    return rublikak;}
    
    public String[] szovegDarabol(String szoveg){
        String [] tomb=szoveg.split(";");
        System.out.println(tomb[0]);
    return tomb;}
    
      public void esemenyKezeles() {
          nezet.getBtnGeneralPdf().addActionListener(e->{
        
              if( nezet.getJcbRaktarKeszlet().isSelected()){
                  try {
                      lekerdezesGyujto.add((ArrayList<Object>)(Object)raktarKeszlet());
                  } catch (SQLException ex) {
                      JOptionPane.showMessageDialog(nezet, ex);
                  }
                  try {
//                      lekerdezes();
                      generalPDF();
                  } catch (FileNotFoundException ex) {
                      JOptionPane.showMessageDialog(nezet, ex);
                  } catch (DocumentException ex) {
                      JOptionPane.showMessageDialog(nezet, ex);
                  }
                  catch (NullPointerException ex) {
                      JOptionPane.showMessageDialog(nezet, ex);
                  }
            }
              
              if(nezet.getJcbKeszletBecsles().isSelected()){
                  try {
                      lekerdezesGyujto.add((ArrayList<Object>)(Object)keszletBecsles());
                  } catch (SQLException ex) {
                      JOptionPane.showMessageDialog(nezet, ex);
                  }
              foCimGyujtes();
                  try {
                      generalPDF();
                  } catch (FileNotFoundException ex) {
                      JOptionPane.showMessageDialog(nezet, ex);
                  } catch (DocumentException ex) {
                      JOptionPane.showMessageDialog(nezet, ex);
                  }
              }
              if(nezet.getJcBeszerzes().isSelected()){
                  try {
                      lekerdezesGyujto.add((ArrayList<Object>)(Object)beszerzes());
                  } catch (SQLException ex) {
                      Logger.getLogger(JelentesController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  foCimGyujtes();
                  try {
                      generalPDF();
                  } catch (FileNotFoundException ex) {
                      JOptionPane.showMessageDialog(nezet, ex);
                  } catch (DocumentException ex) {
                      JOptionPane.showMessageDialog(nezet, ex);
                  }
              }
              if(nezet.getJcbVasarloTop5().isSelected()){
                  
                      try {
                          lekerdezesGyujto.add((ArrayList<Object>)(Object)vevoTop5());
                      } catch (SQLException ex) {
                          JOptionPane.showMessageDialog(nezet, ex);
                      }
//                 
              }
              
              if(nezet.getJcbBeszallitoTop5().isSelected()){
                  
                      try {
                          String nev="To5 beszállító";
//                          lekerdezesGyujto.add();
                          lekerdezesGyujto.add((ArrayList<Object>)(Object)beszallitoTop5());
                      } catch (SQLException ex) {
                          JOptionPane.showMessageDialog(nezet, ex);
                      }
//                 
              }
              if(nezet.getJcbTermekTop5().isSelected()){
                  
                      try {
                          lekerdezesGyujto.add((ArrayList<Object>)(Object)termekTop5());
                      } catch (SQLException ex) {
                          JOptionPane.showMessageDialog(nezet, ex);
                      }
//                      
                  
              }
              if(nezet.getJcbBevetelHavi().isSelected()){
                  
                     
                  try {
                      lekerdezesGyujto.add((ArrayList<Object>)(Object)haviBevetel());
                      
//                      
                  } catch (SQLException ex) {
                      Logger.getLogger(JelentesController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                      
                 
              }
              if(nezet.getJcbBevetelNegyedEv().isSelected()){
                  
                      try {
                          lekerdezesGyujto.add((ArrayList<Object>)(Object)negyedEv());
                      } catch (SQLException ex) {
                          JOptionPane.showMessageDialog(nezet, ex);
                      }
//                    
              }
              
              try {
                  generalPDF();
                  lekerdezesGyujto=new ArrayList<>();
                  alCimGyujto=new ArrayList<>();
                  foCimGyujto=new ArrayList<>();
              } catch (FileNotFoundException ex) {
                  Logger.getLogger(JelentesController.class.getName()).log(Level.SEVERE, null, ex);
              } catch (DocumentException ex) {
                  Logger.getLogger(JelentesController.class.getName()).log(Level.SEVERE, null, ex);
              }
              
          });
           
      }
      public void generalPDF() throws FileNotFoundException, DocumentException{ 
          Document doc;
          doc = new Document();
          PdfWriter kiIr = PdfWriter.getInstance(doc, new FileOutputStream("Kimutatas.pdf"));
          doc.open();
          
          doc.add(new Header("készítő", "Nagy Dávid"));
          Paragraph cim = new Paragraph("Kimutatás", new Font(Font.FontFamily.TIMES_ROMAN, 25f, 0));
          cim.setAlignment(1);
          doc.add(cim);
          doc.add(new Chunk());
          
          for (int i = 0; i < lekerdezesGyujto.size(); i++) {
              doc.add(new Paragraph(foCimGyujto.get(i)));
              String [] cimek=alCimGyujto.get(i).split(";");
              PdfPTable cimsorTablazat = new PdfPTable(cimek.length);
              cimsorTablazat.setWidthPercentage(100);
              for (int a = 0; a < cimek.length; a++) {
                  cimsorTablazat.addCell(cimek[a]);
              }
              doc.add(new Paragraph("\n"));
              
              
               for (int j = 0; j <lekerdezesGyujto.get(i).size(); j++) {
//                  doc.add(new Paragraph(lekerdezesGyujto.get(i).get(j).toString()));
                  String elem=(String) lekerdezesGyujto.get(i).get(j);
                  String [] elemTomb=elem.split(";");
                   for (int k = 0; k < elemTomb.length; k++) {
                       cimsorTablazat.addCell(elemTomb[k]);
                   }
                  
//                  doc.add(new Paragraph("\n"));
              }
               cimsorTablazat.setSpacingAfter(30f);
               doc.add(cimsorTablazat);
//              doc.add(new Paragraph(lekerdezesGyujto.get(i).toString()));
//              doc.add(new Paragraph("\n"));
          }

          doc.add(new Paragraph("\n"));
          doc.add(new Paragraph(new Date().toString()));
          doc.addCreationDate();
          doc.close();
          kiIr.close();
      }
     public ArrayList<String> raktarKeszlet() throws SQLException {
         
         ArrayList<String> eredmenyLista = new ArrayList<>();
         con = AdatbazisKapcsolat.getKapcsolat();
         st = con.createStatement();
         String sql = "Select termékID,terméknév,kategórianév, mennyiség from Termék\n"
                 + "Inner join Kategória on Kategória.kategóriaID=Termék.kategóriaID\n"
                 + "group by termékID,kategórianév,terméknév,mennyiség";
         rs = st.executeQuery(sql);
         String eredmeny="";
         while (rs.next()) {
              eredmeny +=rs.getString(1)+""+ rs.getString(2)+""+rs.getString(3);
             eredmenyLista.add(eredmeny);
         }
         rs.close();
         st.close();
         con.close();

    return eredmenyLista;}
     
     public ArrayList<String> keszletBecsles() throws SQLException {
         
         ArrayList<String> eredmeny = new ArrayList<>();
         con = AdatbazisKapcsolat.getKapcsolat();
         st = con.createStatement();
         String sql = "Select avg(egységár)*avg(mennyiség) from termék";
         rs = st.executeQuery(sql);

         while (rs.next()) {
             String végösszeg = rs.getString(1) + "FT";
             eredmeny.add(végösszeg);
         }
         rs.close();
         st.close();
         con.close();
                 
         return eredmeny;}
     
     public ArrayList<String> vevoTop5() throws SQLException {

         ArrayList<String> eredmeny = new ArrayList<>();
         con = AdatbazisKapcsolat.getKapcsolat();
         st = con.createStatement();
         foCimGyujto.add("Top 5 Vevő");
         cimSor="vevőID"+";"+"vevőnév"+";"+"Rendelés Darabszám";
         alCimGyujto.add(cimSor);
         String sql = "Select Top 5 Rendelés.vevőID,vevő.vevőnév ,count(rendelés.vevőID) \n"
                 + "from Rendelés\n"
                 + "Inner join vevő on Vevő.vevőID=Rendelés.vevőID\n"
                 + "group by Rendelés.vevőID,Vevő.vevőnév\n"
                 + "order by count(rendelés.vevőID) desc";
         rs = st.executeQuery(sql);

         while (rs.next()) {
             String végösszeg = rs.getString(1) + ";" + rs.getString(2) + ";" + rs.getString(3) + "";
             eredmeny.add(végösszeg);
         }
         rs.close();
         st.close();
         con.close();
//         cimSor=null;

         return eredmeny;}
    
     public ArrayList<String> beszallitoTop5() throws SQLException {

        ArrayList<String> eredmeny = new ArrayList<>();
        con = AdatbazisKapcsolat.getKapcsolat();
        st = con.createStatement();
        foCimGyujto.add("Top 5 Beszállító");
         cimSor="beszállítóID"+";"+"beszállítónév"+";"+"Beszállítás Alkalom";
         alCimGyujto.add(cimSor);
        String sql = "Select Top 5 Beszerzés.beszállítóID,Beszállító.beszállítónév ,count(Beszerzés.beszállítóID) \n"
                + "from Beszerzés\n"
                + "Inner join Beszállító on Beszerzés.beszállítóID=Beszállító.beszállítóID\n"
                + "group by Beszerzés.beszállítóID,Beszállító.beszállítónév\n"
                + "order by count(Beszerzés.beszállítóID) desc";
        rs = st.executeQuery(sql);

        while (rs.next()) {
            String végösszeg = rs.getString(1) + ";" + rs.getString(2) + ";" + rs.getString(3) + "";
            eredmeny.add(végösszeg);
        }
        rs.close();
        st.close();
        con.close();

        return eredmeny;
    }
     
     
     public ArrayList<String> beszerzes() throws SQLException {

        ArrayList<String> eredmeny = new ArrayList<>();
        con = AdatbazisKapcsolat.getKapcsolat();
        st = con.createStatement();
        String sql = "select * from Beszerzés";
        rs = st.executeQuery(sql);

        while (rs.next()) {
            String ki = rs.getString(1) + "->Beszerzés Azonosító" + rs.getString(2) + "->Beszállító Azonosító" + rs.getString(3) + "->Termék Azonosító,"+ rs.getString(4) + "->mennyiség,"+ rs.getString(5) + "\n";
            eredmeny.add(ki);
        }
        rs.close();
        st.close();
        con.close();

        return eredmeny;
    }
     

     public ArrayList<String> termekTop5() throws SQLException {

         ArrayList<String> eredmeny = new ArrayList<>();

        con = AdatbazisKapcsolat.getKapcsolat();
        st = con.createStatement();
        foCimGyujto.add("Top 5 Termék");
        cimSor="termékID"+";"+"terméknév"+";"+"Darabszám";
        alCimGyujto.add(cimSor);
        String sql = "Select Top 5 Rendelés.termékID,Termék.terméknév ,count(Rendelés.termékID) from Rendelés\n"
                + "Inner join Termék on Rendelés.termékID=Termék.termékID\n"
                + "group by Rendelés.termékID,Termék.terméknév\n"
                + "order by count(Rendelés.termékID) desc";
         rs = st.executeQuery(sql);

        while (rs.next()) {
            String végösszeg = rs.getString(1) + ";" + rs.getString(2) + ";" + rs.getString(3) + "";
            eredmeny.add(végösszeg);
        }
        rs.close();
        st.close();
        con.close();

        return eredmeny;
    }
     
         public ArrayList<String> negyedEv() throws SQLException {

         ArrayList<String> eredmeny = new ArrayList<>();

        con = AdatbazisKapcsolat.getKapcsolat();
        st = con.createStatement();
        String sql = "SELECT datepart(YEAR,kelt),DATEPART(QUARTER,kelt),\n" +
"         \n" +
"         SUM(végösszeg) AS végösszeg\n" +
"    FROM Rendelés\n" +
"\n" +
"group by DATEPART(Year,kelt), DATEPART(QUARTER,kelt)";
         rs = st.executeQuery(sql);

        while (rs.next()) {
            String végösszeg = rs.getString(1) + "." + rs.getString(2) + ":" + rs.getString(3) + "FT";
            eredmeny.add(végösszeg);
        }
        rs.close();
        st.close();
        con.close();

        return eredmeny;
    }
         
    public ArrayList<String> haviBevetel() throws SQLException {

         ArrayList<String> eredmeny = new ArrayList<>();

        con = AdatbazisKapcsolat.getKapcsolat();
        st = con.createStatement();
        String sql = "SELECT YEAR(GETDATE()) as év,\n" +
"         MONTH(GETDATE()) hónap,\n" +
"         SUM(végösszeg) AS végösszeg\n" +
"    FROM Rendelés\n" +
"\n" +
"ORDER BY datepart(Year,getdate()), datepart(month,getdate()),SUM(végösszeg)";
         rs = st.executeQuery(sql);

        while (rs.next()) {
            String végösszeg = rs.getString(1) + "." + rs.getString(2) + ":" + rs.getString(3) + "FT";
            eredmeny.add(végösszeg);
        }
        rs.close();
        st.close();
        con.close();

        return eredmeny;
    }
     
}
