/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Adatbazis.DAO;
import Adatbazis.KategDAO;
import Adatbazis.TermekDAO;
import Model.TermekModel;
import View.termekUrlap;
import View.TermekKezelDialog;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.KategoriaModell;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import View.MenedzserKliens;
import Controller.tablaMuveletek;
/**
 *
 * @author ndavid97
 */
public class termékKezelController {

    private TermekModel modell;
    private TermekDAO dao = new TermekDAO();
    private KategoriaModell katModell = new KategoriaModell();
    private KategDAO kategDAO = new KategDAO();
    private Validalas vizsgalat = new Validalas();
    private TermekKezelDialog nezet = null;
    private kezelComboBox box = null;
    private tablaMuveletek tabla=null;
     private ArrayList<TermekModel> lekerdezes =null;
    public termékKezelController() throws SQLException {

        nezet = new TermekKezelDialog(new MenedzserKliens(), true);
        box = new kezelComboBox();
        tabla = new tablaMuveletek();
        box.feltoltComboBoxKategoria(nezet.getScrKategoria(), kategDAO.Lekerdezes());
        tabla.tablaFeltolt(dao.adatokTablaba(dao.Lekerdezes()), nezet.getJtTermekek());
        esemenyKezeles();
        nezet.setVisible(true);
    }

    public void esemenyKezeles() {
        
        nezet.getBtnkereses().addActionListener(e -> {
            try {

                if (!(nezet.getTfKeresMezo().getText().isEmpty())) {
//                    System.out.println(dao.Szures((String) nezet.getJcKeres().getSelectedItem(), nezet.getTfKeresMezo().getText()));
                    tabla.tablaFeltolt(dao.adatokTablaba(dao.Szures((String) nezet.getJcKeres().getSelectedItem(), nezet.getTfKeresMezo().getText())), nezet.getJtTermekek());
                } else {
                    tabla.tablaFeltolt(dao.adatokTablaba(dao.Lekerdezes()), nezet.getJtTermekek());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(nezet, ex);
            }
        });
        nezet.getBtnTorles().addActionListener(e -> {
            try {
                ArrayList<TermekModel> lekérdezés = new ArrayList<>();
                lekérdezés = dao.Lekerdezes();
                TermekModel törlés = lekérdezés.get(nezet.getJtTermekek().getSelectedRow());
                int veglegesit=JOptionPane.showOptionDialog(nezet, "kiválasztott termék törlése", "Biztos benne?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                if(veglegesit==0){
                dao.Torles(törlés);
                JOptionPane.showMessageDialog(nezet, "Kiválasztott termék törlése végrehajtva");
                    tabla.tablaFeltolt(dao.adatokTablaba(dao.Lekerdezes()), nezet.getJtTermekek());
                }
               
               
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(nezet, ex);
            }

        });
        nezet.getBtnModosít().addActionListener(e -> {
            try {
                if(teljesEllenorzes()){
                    TermekModel modositas = new TermekModel(Integer.parseInt(nezet.getIdLbl().getText()),
                            nezet.getTfNev().getText(), (String) nezet.getScrKategoria().getSelectedItem(),
                            Integer.parseInt(nezet.getTfMennyiseg().getText()), Integer.parseInt(nezet.getTfEgysegAr().getText()),
                            nezet.getTareaLeírás().getText(), 0);
                    System.out.println(modositas);
                    int veglegesit = JOptionPane.showOptionDialog(nezet, "kiválasztott termék frissítésére készül", "Biztos benne?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                    if (veglegesit == 0) {
                        try {
                            dao.Frissites(modositas);
                        } catch (SQLException ex) {
                            Logger.getLogger(termékKezelController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        JOptionPane.showMessageDialog(nezet, "Kiválasztott termék módosítása végrehajtva");}
                    
                    try {
                        tabla.tablaFeltolt(dao.adatokTablaba(dao.Lekerdezes()), nezet.getJtTermekek());
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(nezet, ex);
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(nezet, ex);
            }
        });
        nezet.getJtTermekek().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {

                try {
                    mezokFeltolt();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(nezet, ex);
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
    public void mezokFeltolt() throws SQLException {

        int sorszam = nezet.getJtTermekek().getSelectedRow();

        TermekModel tablaElem = dao.Lekerdezes().get(sorszam);

        nezet.getIdLbl().setText(nezet.getJtTermekek().getValueAt(sorszam, 0).toString());
        nezet.getTfNev().setText(nezet.getJtTermekek().getValueAt(sorszam, 1).toString());
        nezet.getScrKategoria().setSelectedItem(tablaElem.getKategoria());
        nezet.getTfMennyiseg().setText(nezet.getJtTermekek().getValueAt(sorszam, 3).toString());
        nezet.getTfEgysegAr().setText(nezet.getJtTermekek().getValueAt(sorszam, 4).toString());
        nezet.getTaLeírás().setText(nezet.getJtTermekek().getValueAt(sorszam, 5).toString());
          
    }

}
