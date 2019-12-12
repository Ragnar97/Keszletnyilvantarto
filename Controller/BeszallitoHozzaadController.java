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
import View.BeszallitoDialog;
import Model.beszallitoModell;
import Adatbazis.beszallitoDAO;
import View.MenedzserKliens;
import FajlKezeles.ExcelBeOlvasKiir;
import FajlKezeles.Fajlvalszto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Controller.Validalas;
import Model.elerhetosegAlapModell;
public class BeszallitoHozzaadController {
    private BeszallitoDialog nezet=null;
    private beszallitoModell besz=null;
    private beszallitoDAO dao=null;
    private ExcelBeOlvasKiir excel=null;
    private Validalas vizsgalat=null;
    private ElerhetosegDAO elerDao=null;
    public BeszallitoHozzaadController() {
        nezet=new BeszallitoDialog(new MenedzserKliens(), true);
        dao=new beszallitoDAO();
        excel=new ExcelBeOlvasKiir();
        vizsgalat=new Validalas();
        elerDao=new ElerhetosegDAO();
        esemenyKezeles();
        nezet.setVisible(true);
    }
    
     public void esemenyKezeles(){
    nezet.getBtnHozzaad().addActionListener(e->{
                besz=new beszallitoModell(nezet.getTfNev().getText(), nezet.getTfEmail().getText(), nezet.getTfTelefon().getText(), Integer.parseInt(nezet.getTfIrsz().getText()),nezet.getTfVaros().getText(), nezet.getTfUtca().getText(), Integer.parseInt(nezet.getTfHazSzam().getText()), nezet.getTfWeblap().getText());

        if(teljesEllenorzes(besz)){
            
            elerhetosegAlapModell elerheto=new elerhetosegAlapModell(0, besz.getEmail(), besz.getTelefon(), besz.getIrsz(), besz.getVaros(), besz.getUtca(), besz.getHazszam(), besz.getWeblap());
           int id=-2;
            try {
                id = vizsgalat.egyezesVizsgPartner(elerheto, elerDao.Lekerdezes());
            } catch (SQLException ex) {
                Logger.getLogger(vevoHozzaadController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(id<0){
                
                try {
                    dao.Beszuras(besz);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(nezet, ex);
                }}
            else if(id>=0){
             try {
                
                
                   
                    
//                    vevoModell vevo2=new vevoModell(0, vevo.getVevonev(), id, 0, vevo.getEmail(), vevo.getTelefon(), vevo.getIrsz(), vevo.getVaros(), vevo.getUtca(), vevo.getHazszam(), vevo.getWeblap());
                    String sqlSeged="Insert into Beszállító(beszállítónév,elérhetőségID) values('"+besz.getBeszallitoNev()+"','"+id+"')";
                    dao.vegrehajtSQL(sqlSeged);
                
            } catch (SQLException ex) {
                Logger.getLogger(vevoHozzaadController.class.getName()).log(Level.SEVERE, null, ex);
            }}
                
        
        System.out.println(besz.toString());
        }
    });
    nezet.getBtnImport().addActionListener(e->{
        Fajlvalszto fájlVálasztó = new Fajlvalszto();

            ArrayList<beszallitoModell> fajlLista = excel.beOlvasBeszallito(fájlVálasztó.ablak());

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
                             id=vizsgalat.egyezesVizsgPartner(elerheto, elerDao.Lekerdezes());
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
                            String sqlSeged="Insert into Beszállító(beszállítónév,elérhetőségID) values('"+fajlLista.get(i).getBeszallitoNev()+"','"+id+"')";
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
       private boolean teljesEllenorzes(beszallitoModell besz){
    String hiba = "";
        if (!vizsgalat.Ures(besz.getBeszallitoNev())) {
            hiba += "vevőnév mező üres mező";
        }
        if (vizsgalat.ellenorizHossz(besz.getBeszallitoNev(), 50)) {
            hiba += "vevőnév túl hosszú";
        }
        if (vizsgalat.ellenorizHossz(besz.getWeblap(), 50)) {
            hiba += "weblap mező túl hosszú";
        }
        if (!vizsgalat.Ures(besz.getIrsz()+"")) {
            hiba += "Irányítószám mező üres mező";
        }
        if (vizsgalat.ellenorizHossz(besz.getVaros(), 50)) {
            hiba += "város mező túl hosszú";
        }
       if (!vizsgalat.Ures(besz.getVaros())) {
            hiba += "varos mező üres mező";
        }
       if (!vizsgalat.Ures(besz.getUtca())) {
            hiba += "utca mező üres mező";
        }
        if (!vizsgalat.szamE(besz.getTelefon())) {
            hiba += "Telefon nem szám vagy nem nagyobb 0";
        }
        if (!vizsgalat.szamE(besz.getIrsz()+"")) {
            hiba += "Irányítószám nem szám vagy nem nagyobb 0";
        }
        if (vizsgalat.ellenorizHossz(besz.getUtca(), 25)) {
            hiba += "utca mező túl hosszú";
        }
        if(!vizsgalat.email(besz.getEmail())){
            hiba += "nem érvényes email cím";
        }
        if (hiba.isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(nezet, hiba, "Hiba!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
       
       
    
    
}
