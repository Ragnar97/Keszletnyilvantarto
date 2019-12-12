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
import Adatbazis.ElerhetosegDAO;
import View.vevoHozzaadDialog;
import Adatbazis.vevoDAO;
import Model.vevoModell;
import View.MenedzserKliens;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Controller.Validalas;
import FajlKezeles.ExcelBeOlvasKiir;
import FajlKezeles.Fajlvalszto;
import Model.elerhetosegAlapModell;
import java.util.ArrayList;
public class vevoHozzaadController {
    private vevoHozzaadDialog nezet=null;
    private  vevoDAO dao=null;
    private vevoModell vevo=null;
    private Validalas vizsgálat=null;
     private ExcelBeOlvasKiir excel=null;
     private ElerhetosegDAO elerDao=null;
  
    public vevoHozzaadController() {
        nezet=new vevoHozzaadDialog(new MenedzserKliens(), true);
        esemenyKezeles();
        vizsgálat=new Validalas();
        dao=new vevoDAO();
        vevo=new vevoModell();
        elerDao=new ElerhetosegDAO();
        excel=new ExcelBeOlvasKiir();
        nezet.setVisible(true);
        
    }
    public void esemenyKezeles(){
    nezet.getKeziFelv().addActionListener(e->{
        System.out.println("Esemény");
        vevo=new vevoModell(nezet.getTfNev().getText(), nezet.getTfEmail().getText(), nezet.getTfTelefon().getText(), Integer.parseInt(nezet.getTfIrsz().getText()),nezet.getTfVaros().getText(), nezet.getTfUtca().getText(), Integer.parseInt(nezet.getTfHazSzam().getText()), nezet.getTfWeblap().getText());
        if(teljesEllenorzes(vevo)){
            
        
            elerhetosegAlapModell elerheto=new elerhetosegAlapModell(0, vevo.getEmail(), vevo.getTelefon(), vevo.getIrsz(), vevo.getVaros(), vevo.getUtca(), vevo.getHazszam(), vevo.getWeblap());
            int id=-2;
            try {
                id = vizsgálat.egyezesVizsgPartner(elerheto, elerDao.Lekerdezes());
            } catch (SQLException ex) {
                Logger.getLogger(vevoHozzaadController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(id<0){
                
                try {
                    dao.Beszuras(vevo);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(nezet, ex);
                }}
            else if(id>=0){
             try {
                
                
                   
                    
//                    vevoModell vevo2=new vevoModell(0, vevo.getVevonev(), id, 0, vevo.getEmail(), vevo.getTelefon(), vevo.getIrsz(), vevo.getVaros(), vevo.getUtca(), vevo.getHazszam(), vevo.getWeblap());
                    String sqlSeged="Insert into Vevő(vevőnév,elérhetőségID) values('"+vevo.getVevonev()+"','"+id+"')";
                    dao.vegrehajtSQL(sqlSeged);
                
            } catch (SQLException ex) {
                Logger.getLogger(vevoHozzaadController.class.getName()).log(Level.SEVERE, null, ex);
            }}
                
        
        System.out.println(vevo.toString());
        }});
    nezet.getBtnVevoImport().addActionListener(e->{
        Fajlvalszto fájlVálasztó = new Fajlvalszto();

            ArrayList<vevoModell> fajlLista = excel.beOlvasVevo(fájlVálasztó.ablak());

            if (!(fajlLista.isEmpty())) {
                int hiba=-1;
                for (int i = 0; i < fajlLista.size(); i++) {
                    if(!teljesEllenorzes(fajlLista.get(i))) hiba=i;
                }
                if(hiba>-1){JOptionPane.showMessageDialog(nezet, new String("Hiba a"+hiba+". elemnél"), "hiba", 0);}
                else{
                    for (int i = 0; i < fajlLista.size(); i++) {
                        int id=-2;
                         elerhetosegAlapModell elerheto=new elerhetosegAlapModell(0, fajlLista.get(i).getEmail(), fajlLista.get(i).getTelefon(), fajlLista.get(i).getIrsz(), fajlLista.get(i).getVaros(), fajlLista.get(i).getUtca(), fajlLista.get(i).getHazszam(), fajlLista.get(i).getWeblap());
                        try {
                             id=vizsgálat.egyezesVizsgPartner(elerheto, elerDao.Lekerdezes());
                        } catch (SQLException ex) {
                            Logger.getLogger(vevoHozzaadController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if(id<0){
                            try {
                                dao.Beszuras(fajlLista.get(i));
                            } catch (SQLException ex) {
                                Logger.getLogger(vevoHozzaadController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            }
                        else if(id>=0){
                            String sqlSeged="Insert into Vevő(vevőnév,elérhetőségID) values('"+fajlLista.get(i).getVevonev()+"','"+id+"')";
                            try {
                                dao.vegrehajtSQL(sqlSeged);
                            } catch (SQLException ex) {
                                Logger.getLogger(vevoHozzaadController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
    });
    }
    
     private boolean teljesEllenorzes(vevoModell vevo) {
    String hiba = "";
        if (!vizsgálat.Ures(vevo.getVevonev())) {
            hiba += "vevőnév mező üres mező";
        }
        if (vizsgálat.ellenorizHossz(vevo.getVevonev(), 50)) {
            hiba += "vevőnév túl hosszú";
        }
        if (vizsgálat.ellenorizHossz(vevo.getWeblap(), 50)) {
            hiba += "weblap mező túl hosszú";
        }
        if (!vizsgálat.Ures(vevo.getIrsz()+"")) {
            hiba += "Irányítószám mező üres mező";
        }
        if (vizsgálat.ellenorizHossz(vevo.getVaros(), 50)) {
            hiba += "város mező túl hosszú";
        }
       if (!vizsgálat.Ures(vevo.getVaros())) {
            hiba += "varos mező üres mező";
        }
       if (!vizsgálat.Ures(vevo.getUtca())) {
            hiba += "utca mező üres mező";
        }
        if (!vizsgálat.szamE(vevo.getTelefon())) {
            hiba += "Telefon nem szám vagy nem nagyobb 0";
        }
        if (!vizsgálat.szamE(vevo.getIrsz()+"")) {
            hiba += "Irányítószám nem szám vagy nem nagyobb 0";
        }
        if (vizsgálat.ellenorizHossz(vevo.getUtca(), 25)) {
            hiba += "utca mező túl hosszú";
        }
        if(!vizsgálat.email(vevo.getEmail())){
            hiba += "nem érvényes email cím";
        }
        if (hiba.isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(nezet, hiba, "Hiba!", JOptionPane.ERROR_MESSAGE);
            return false;
        }}
     
//     private boolean teljesEllenorzesImport(vevoModell vevo) {
//    String hiba = "";
//        if (!vizsgálat.Ures(vevo.getVevonev())) {
//            hiba += "vevőnév mező üres mező";
//        }
//        if (vizsgálat.ellenorizHossz(vevo.getVevonev(), 50)) {
//            hiba += "vevőnév túl hosszú";
//        }
//        if (vizsgálat.ellenorizHossz(vevo.getWeblap(), 50)) {
//            hiba += "weblap mező túl hosszú";
//        }
//        if (!vizsgálat.Ures(vevo.getIrsz()+"")) {
//            hiba += "Irányítószám mező üres mező";
//        }
//        if (vizsgálat.ellenorizHossz(vevo.getVaros(), 50)) {
//            hiba += "város mező túl hosszú";
//        }
//       if (!vizsgálat.Ures(vevo.getVaros())) {
//            hiba += "varos mező üres mező";
//        }
//       if (!vizsgálat.Ures(vevo.getUtca())) {
//            hiba += "utca mező üres mező";
//        }
//        if (!vizsgálat.szamE(vevo.getHazszam()+"")) {
//            hiba += "házszám nem szám vagy nem nagyobb 0";
//        }
//        if (!vizsgálat.szamE(vevo.getIrsz()+"")) {
//            hiba += "Irányítószám nem szám vagy nem nagyobb 0";
//        }
//        if (vizsgálat.ellenorizHossz(vevo.getUtca(), 25)) {
//            hiba += "utca mező túl hosszú";
//        }
//        if(!vizsgálat.email(nezet.getTfEmail().getText())){
//            hiba += "nem érvényes email cím";
//        }
//        if (hiba.isEmpty()) {
//            return true;
//        } else {
//            JOptionPane.showMessageDialog(nezet, hiba, "Hiba!", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }}
}