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
import View.BeszallitoKezelDialog;
import Adatbazis.beszallitoDAO;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Controller.Validalas;
import Model.beszallitoModell;
import Controller.TermekNezetController;
import View.MenedzserKliens;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
public class BeszallitoKezelController {
    private BeszallitoKezelDialog nezet=null;
    private beszallitoDAO dao=null;
    private Validalas vizsgálat=null;
    private tablaMuveletek tabla=null;
    private beszallitoModell modell=null;
    public BeszallitoKezelController() throws SQLException {
        nezet=new BeszallitoKezelDialog(new MenedzserKliens(), true);
        dao=new beszallitoDAO();
        vizsgálat=new Validalas();
        tabla=new tablaMuveletek();
        tabla.tablaFeltolt(dao.adatokTablaba(dao.Lekerdezes()), nezet.getTblVevo());
        esemenyKezeles();
        nezet.setVisible(true);
    }
    
    
    public void esemenyKezeles(){
    
    
    nezet.getTblVevo().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {

                try {
                    mezokFeltolt();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(nezet, ex);
                }
            }

        });
        nezet.getBtnSzures().addActionListener(e->{
            try {
                
                if(!(nezet.getTfSzures().getText().isEmpty())){
//                    System.out.println(dao.Szűrés((String)nezet.getJcKeres().getSelectedItem(), nezet.getTfKeresMező().getText()));
                tabla.tablaFeltolt(dao.adatokTablaba(dao.Szures((String)nezet.getCbSzures().getSelectedItem(), nezet.getTfSzures().getText())), nezet.getTblVevo());
                
                    
                }
                else{
                tabla.tablaFeltolt(dao.adatokTablaba(dao.Lekerdezes()), nezet.getTblVevo());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(nezet, ex);
            }
        });
        nezet.getBtnTorles().addActionListener(e->{
        try {
            beszallitoModell torles=dao.Lekerdezes().get(nezet.getTblVevo().getSelectedRow());
            dao.Torles(torles);
            tabla.tablaFeltolt(dao.adatokTablaba(dao.Lekerdezes()), nezet.getTblVevo());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(nezet, ex);
        }
                
        });
        nezet.getBtnModosit().addActionListener(e->{
            if(teljesEllenorzes()){
        try {
            int seged=dao.Lekerdezes().get(nezet.getTblVevo().getSelectedRow()).getElerhetosegID();
            beszallitoModell modosit=new beszallitoModell(Integer.parseInt(nezet.getJlID().getText()),nezet.getTfNev().getText(),seged,0, nezet.getTfEmail().getText(), nezet.getTfTelefon().getText(), Integer.parseInt(nezet.getTfIrsz().getText()), nezet.getTfVaros().getText(), nezet.getTfUtca().getText(), Integer.parseInt(nezet.getTfHazSzam().getText()), nezet.getTfWeblap().getText());
            
            dao.Frissites(modosit);
            tabla.tablaFeltolt(dao.adatokTablaba(dao.Lekerdezes()), nezet.getTblVevo());
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(nezet, ex);
        }}
        });
        
    }
      private boolean teljesEllenorzes() {
    String hiba = "";
        if (!vizsgálat.Ures(nezet.getTfNev().getText())) {
            hiba += "vevőnév mező üres mező";
        }
        if (vizsgálat.ellenorizHossz(nezet.getTfNev().getText(), 50)) {
            hiba += "vevőnév túl hosszú";
        }
        if (vizsgálat.ellenorizHossz(nezet.getTfWeblap().getText(), 50)) {
            hiba += "weblap mező túl hosszú";
        }
        if (!vizsgálat.Ures(nezet.getTfIrsz().getText())) {
            hiba += "Irányítószám mező üres mező";
        }
        if (vizsgálat.ellenorizHossz(nezet.getTfVaros().getText(), 50)) {
            hiba += "város mező túl hosszú";
        }
       if (!vizsgálat.Ures(nezet.getTfVaros().getText())) {
            hiba += "varos mező üres mező";
        }
       if (!vizsgálat.Ures(nezet.getTfUtca().getText())) {
            hiba += "utca mező üres mező";
        }
        if (!vizsgálat.szamE(nezet.getTfTelefon().getText())) {
            hiba += "házszám nem szám vagy nem nagyobb 0";
        }
        if (!vizsgálat.szamE(nezet.getTfIrsz().getText())) {
            hiba += "Irányítószám nem szám vagy nem nagyobb 0";
        }
        if (vizsgálat.ellenorizHossz(nezet.getTfUtca().getText(), 25)) {
            hiba += "utca mező túl hosszú";
        }
        if (hiba.isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(nezet, hiba, "Hiba!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    
    public void mezokFeltolt() throws SQLException {

        int sorszám = nezet.getTblVevo().getSelectedRow();
 
          nezet.getJlID().setText(nezet.getTblVevo().getValueAt(sorszám, 0).toString());
          nezet.getTfNev().setText(nezet.getTblVevo().getValueAt(sorszám, 1).toString());
          nezet.getTfEmail().setText(nezet.getTblVevo().getValueAt(sorszám, 2).toString());
          nezet.getTfTelefon().setText(nezet.getTblVevo().getValueAt(sorszám, 3).toString());
          nezet.getTfIrsz().setText(nezet.getTblVevo().getValueAt(sorszám, 4).toString());
          nezet.getTfVaros().setText(nezet.getTblVevo().getValueAt(sorszám, 5).toString());
          nezet.getTfUtca().setText(nezet.getTblVevo().getValueAt(sorszám, 6).toString());
          nezet.getTfHazSzam().setText(nezet.getTblVevo().getValueAt(sorszám, 7).toString());
          nezet.getTfWeblap().setText(nezet.getTblVevo().getValueAt(sorszám, 8).toString());
 
    }
}
