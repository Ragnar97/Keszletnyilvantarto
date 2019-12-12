/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Adatbazis.AdatbazisKapcsolat;
import Adatbazis.DAO;
import Adatbazis.TermekDAO;
import Model.TermekModel;

import  View.termekHozzaadNezet;
import Adatbazis.AdatbazisKapcsolat;
import Adatbazis.KategDAO;
import Model.KategoriaModell;
import FajlKezeles.Fajlvalszto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import FajlKezeles.ExcelBeOlvasKiir;
import java.awt.Frame;
import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import View.termekUrlap;
import java.awt.Dialog;
import java.awt.Frame;
import Model.BeszerzesModell;
import Adatbazis.beszallitoDAO;
import Adatbazis.BeszerzesDAO;
import Adatbazis.BeszerzesDAO;
import View.MenedzserKliens;
public class termekHozzaadController{
  private TermekModel termekModell;
  private TermekDAO termekDao=new TermekDAO();
  private KategDAO kategDAO=new KategDAO();
  private beszallitoDAO beszallitoDao=new beszallitoDAO();
  private BeszerzesDAO beszerzesDao=new BeszerzesDAO();
  private BeszerzesModell beszerzesModell=null;
  private KategoriaModell kategmodell=new  KategoriaModell();
  private Validalas vizsgalat=new Validalas();
  private termekHozzaadNezet nezet=null;
  private ExcelBeOlvasKiir excel=new ExcelBeOlvasKiir();
  private kezelComboBox box=null;

    public termekHozzaadController() {
         
        try {
            termekDao = new TermekDAO();
//            beszallitoDao = new beszallitoDAO();
            beszerzesDao = new BeszerzesDAO();
            vizsgalat = new Validalas();

            nezet = new termekHozzaadNezet(new MenedzserKliens(), true);
            

            box = new kezelComboBox();
            box.feltoltComboBoxKategoria(nezet.getScrKategoria(), kategDAO.Lekerdezes());
            box.feltoltComboBoxBeszallito(nezet.getCbBeszallito(), beszallitoDao.Lekerdezes());
            
            esemenyKezeles();
            nezet.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(nezet, ex);
        }
    }
    
    public void esemenyKezeles(){
        nezet.getBtnHozzáadás().addActionListener(e -> {
            hozzaAd();
        });
        nezet.getBtnImport().addActionListener(e -> {
            Fajlvalszto fájlVálasztó = new Fajlvalszto();

            ArrayList<TermekModel> fajlLista = excel.beOlvasTermek(fájlVálasztó.ablak());

            if (!(fajlLista.isEmpty())) {
                for (int i = 0; i < fajlLista.size(); i++) {
                    try {
                        if(teljesEllenorzesImport(fajlLista.get(i))){
                            if(vizsgalat.egyezesVizsgTermek(fajlLista.get(i).getTermekNev(), termekDao.Lekerdezes())){
                                System.out.println(fajlLista.get(i).getTermekNev());
                                ArrayList<TermekModel> termekekLista = termekDao.vegrehajtVisszaadSQL("Select termékID,terméknév,kategórianév,mennyiség,egységár,leírás,[törölt-e]"
                                        + " from Termék Inner Join Kategória ON Termék.kategóriaID=Kategória.kategóriaID where terméknév='"+fajlLista.get(i).getTermekNev()+"'");
                                
                      System.out.println(termekekLista.get(0).toString());
                                TermekModel termekSeged2 = termekekLista.get(i);
                                int termekID = 0;
                      termekID=termekDao.vegrehajtVisszaadSQL("select * from Termék where termékID=(Select Max(termékID) from Termék)").get(0).getTermekID();
                      int plusz=fajlLista.get(i).getMennyiseg();
                      termekSeged2.setMennyiseg(termekSeged2.getMennyiseg()+plusz);
                       termekDao.Frissites(termekekLista.get(i));
                                String üzenet = "Ez már létező termék volt, ezért mennyiséggel frissült annak készlete";
                                int beszallitoID=beszallitoDao.vegrehajtVisszaadSQL("Select Beszállító.beszállítóID, Beszállító.beszállítónév, Beszállító.elérhetőségID,[törölt-e],email,telefon,irsz,város,utca,házszám,weblap\n" +
                          "from Beszállító,Elérhetőség \n" +
                          "where Beszállító.elérhetőségID=Elérhetőség.elérhetőségID and beszállítónév='"+excel.getBeszNev()+"'").get(0).getBeszallitoID();
                  beszerzesModell=new BeszerzesModell(beszallitoID, termekID, fajlLista.get(i).getMennyiseg());
                  beszerzesDao.Beszuras(beszerzesModell);
                            }
                            else if(!vizsgalat.egyezesVizsgTermek(fajlLista.get(i).getTermekNev(), termekDao.Lekerdezes())){
                                termekDao.Beszuras(fajlLista.get(i));
                                int termekID=0;
                                termekID=termekDao.vegrehajtVisszaadSQL("select * from Termék where termékID=(Select Max(termékID) from Termék)").get(0).getTermekID();
                   int beszallitoID=beszallitoDao.vegrehajtVisszaadSQL("Select Beszállító.beszállítóID, Beszállító.beszállítónév, Beszállító.elérhetőségID,[törölt-e],email,telefon,irsz,város,utca,házszám,weblap\n" +
                          "from Beszállító,Elérhetőség \n" +
                          "where Beszállító.elérhetőségID=Elérhetőség.elérhetőségID and beszállítónév='"+excel.getBeszNev()+"'").get(0).getBeszallitoID();
                  beszerzesModell=new BeszerzesModell(beszallitoID, termekID, fajlLista.get(i).getMennyiseg());
                  beszerzesDao.Beszuras(beszerzesModell);
                            }
                        }
                        
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(nezet, ex);
                    }
                }
            }
        });
    }

    private boolean teljesEllenorzes() throws SQLException{
    String hiba = "";
        if (!vizsgalat.Ures(nezet.getTfNev().getText())) {
            hiba += "termék mező üres mező";
        }
        if (vizsgalat.ellenorizHossz(nezet.getTfNev().getText(), 50)) {
            hiba += "termék mező túl hosszú";
        }
//        if(vizsgalat.egyezesVizsg(nezet.getTfNév().getText(), termekDao.Lekerdezes())){
//            hiba+="termék már létezik ilyen néven";
//        }
        if (!vizsgalat.szamE(nezet.getTfMennyiseg().getText())) {
            hiba += "Mennyiség nem szám vagy nem nagyobb 0";
        }
        if (!vizsgalat.szamE(nezet.getTfMennyiseg().getText())) {
            hiba += "Egységár nem szám vagy nem nagyobb 0";
        }
        
        if (vizsgalat.ellenorizHossz(nezet.getTaLeírás().getText(), 100)) {
            hiba += "Leírás mező nem elég hoszú";
        }
        if (hiba.isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(nezet, hiba, "Hiba!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    private boolean teljesEllenorzesImport(TermekModel termek) throws SQLException{
    String hiba = "";
        if (!vizsgalat.Ures(termek.getTermekNev())) {
            hiba += "terméknév mező üres mező";
        }
        if (vizsgalat.ellenorizHossz(termek.getTermekNev(),50)) {
            hiba += "terméknév mező túl hosszú";
        }
//        if(vizsgalat.egyezesVizsg(nezet.getTfNév().getText(), termekDao.Lekerdezes())){
//            hiba+="termék már létezik ilyen néven";
//        }
        if (!vizsgalat.szamE(termek.getMennyiseg()+"")) {
            hiba += "Mennyiség nem szám vagy nem nagyobb 0";
        }
        if (!vizsgalat.szamE(termek.getEgysegar()+"")) {
            hiba += "Egységár nem szám vagy nem nagyobb 0";
        }
        
        if (vizsgalat.ellenorizHossz(termek.getLeiras(), 100)) {
            hiba += "Leírás mező nem elég hoszú";
        }
        if (hiba.isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(nezet, hiba, "Hiba a "+termek.getTermekNev()+" nevű elemnél", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public void hozzaAd() {
        String üzenet = "";
        int termekID;
        ArrayList<TermekModel> termekekLista=null;
        TermekModel termekSeged2=null;
      try {
          if (teljesEllenorzes()) {   
              try {
                  
                  termekModell = new TermekModel(nezet.getTfNev().getText(),
                          (String) nezet.getScrKategoria().getSelectedItem(),
                          Integer.parseInt(nezet.getTfMennyiseg().getText()),
                          Integer.parseInt(nezet.getTfEgysegAr().getText()),
                          nezet.getTareaLeírás().getText());
                  if(!vizsgalat.egyezesVizsgTermek(nezet.getTfNev().getText(), termekDao.Lekerdezes())){
                  termekDao.Beszuras(termekModell);
                   termekID=termekDao.vegrehajtVisszaadSQL("select * from Termék where termékID=(Select Max(termékID) from Termék)").get(0).getTermekID();
                   int beszallitoID=beszallitoDao.vegrehajtVisszaadSQL("Select Beszállító.beszállítóID, Beszállító.beszállítónév, Beszállító.elérhetőségID,[törölt-e],email,telefon,irsz,város,utca,házszám,weblap\n" +
                          "from Beszállító,Elérhetőség \n" +
                          "where Beszállító.elérhetőségID=Elérhetőség.elérhetőségID and beszállítónév='"+(String)nezet.getCbBeszallito().getSelectedItem()+"'").get(0).getBeszallitoID();
                  beszerzesModell=new BeszerzesModell(beszallitoID, termekID, termekModell.getMennyiseg());
                  beszerzesDao.Beszuras(beszerzesModell);
                  üzenet = "Sikere termék és beszerzés felvitele";
                  JOptionPane.showMessageDialog(nezet, üzenet);
                  
                  }
                  else if((vizsgalat.egyezesVizsgTermek(nezet.getTfNev().getText(), termekDao.Lekerdezes()))){ 
                      System.out.println("Select * from Termék where terméknév='"+nezet.getTfNev().getText()+"'");
                 termekekLista =termekDao.vegrehajtVisszaadSQL("Select termékID,terméknév,kategórianév,mennyiség,egységár,leírás,[törölt-e]"
            + " from Termék Inner Join Kategória ON Termék.kategóriaID=Kategória.kategóriaID where terméknév='"+termekModell.getTermekNev()+"'");
                      System.out.println(termekekLista.get(0).toString());
                      termekSeged2=termekekLista.get(0);
                       termekID=0;
                      termekID=termekDao.vegrehajtVisszaadSQL("select * from Termék where termékID=(Select Max(termékID) from Termék)").get(0).getTermekID();
                      
                      termekSeged2.setMennyiseg(termekSeged2.getMennyiseg()+Integer.parseInt(nezet.getTfMennyiseg().getText()));
                       termekDao.Frissites(termekekLista.get(0));
                  üzenet="Ez már létező termék volt, ezért mennyiséggel frissült annak készlete";
                  int beszallitoID=beszallitoDao.vegrehajtVisszaadSQL("Select Beszállító.beszállítóID, Beszállító.beszállítónév, Beszállító.elérhetőségID,[törölt-e],email,telefon,irsz,város,utca,házszám,weblap\n" +
                          "from Beszállító,Elérhetőség \n" +
                          "where Beszállító.elérhetőségID=Elérhetőség.elérhetőségID and beszállítónév='"+(String)nezet.getCbBeszallito().getSelectedItem()+"'").get(0).getBeszallitoID();
                  beszerzesModell=new BeszerzesModell(beszallitoID, termekID, termekModell.getMennyiseg());
                  beszerzesDao.Beszuras(beszerzesModell);
                  
                  JOptionPane.showMessageDialog(nezet, üzenet);}
              } catch (SQLException ex) {
                  JOptionPane.showMessageDialog(nezet, ex, "Hiba", JOptionPane.ERROR_MESSAGE);
                  
              }
               
          }
//          if(vizsgalat.egyezesVizsg(nezet.getTfNév().getText(), termekDao.select(termekModell))){
//          ArrayList<TermékModel> termekekLista = termekDao.vegrehajtVisszaadSQL("Select * from Termék where terméknév='"+nezet.getTfNév()+"'");
//          JOptionPane.showConfirmDialog(nezet, box)
          
      } catch (SQLException ex) {
          JOptionPane.showMessageDialog(nezet, ex, üzenet, JOptionPane.ERROR_MESSAGE);
      }
        
    }
}



   


