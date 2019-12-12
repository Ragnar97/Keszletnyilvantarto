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
import Adatbazis.vevoDAO;
import Model.vevoModell;
import View.VevoKezelDialog;
import View.MenedzserKliens;
import java.sql.SQLException;
import View.VevoKezelDialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Controller.Validalas;
public class VevoKezelController {
    private VevoKezelDialog nezet=null;
    private vevoModell modell=null;
    private vevoDAO dao=null;
    private tablaMuveletek tabla=null;
    private Validalas vizsgálat=null;

    public VevoKezelController() throws SQLException {
        
        nezet=new VevoKezelDialog(new MenedzserKliens(), true);
//        super.getNezet().setVisible(false);
//        nezet.setVisible(true);
        vizsgálat=new Validalas();
        dao=new vevoDAO();
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
                tabla.tablaFeltolt(dao.adatokTablaba(dao.Szures(nezet.getCbSzures().getSelectedItem().toString(), nezet.getTfSzures().getText())), nezet.getTblVevo());
                
                    
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
            vevoModell torles=dao.Lekerdezes().get(nezet.getTblVevo().getSelectedRow());
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
            vevoModell modosit=new vevoModell(Integer.parseInt(nezet.getJlID().getText()),nezet.getTfNev().getText(),seged,0, nezet.getTfEmail().getText(), nezet.getTfTelefon().getText(), Integer.parseInt(nezet.getTfIrsz().getText()), nezet.getTfVaros().getText(), nezet.getTfUtca().getText(), Integer.parseInt(nezet.getTfHazSzam().getText()), nezet.getTfWeblap().getText());
            
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
