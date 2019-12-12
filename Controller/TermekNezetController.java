/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Adatbazis.KategDAO;
import Adatbazis.TermekDAO;
import Model.KategoriaModell;
import Model.TermekModel;
import View.TermekNezetDialog;
import View.MenedzserKliens;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ndavid97
 */
public class TermekNezetController {
    private TermekModel modell;
    private TermekDAO dao = new TermekDAO();
    private KategoriaModell katModell = new KategoriaModell();
    private KategDAO kategDAO = new KategDAO();
    private Validalas vizsgálat = new Validalas();
    private TermekNezetDialog nezet = null;
    private kezelComboBox box = null;
    private tablaMuveletek tabla=null;
    private ArrayList<TermekModel> lekerdezes =null;

    public TermekNezetController() throws SQLException {
        nezet = new TermekNezetDialog(new MenedzserKliens(), true);
        box = new kezelComboBox();
        tabla = new tablaMuveletek();
        box.feltoltComboBoxKategoria(nezet.getScrKategoria(), kategDAO.Lekerdezes());
        tabla.tablaFeltolt(dao.adatokTablaba(dao.Lekerdezes()), nezet.getJtTermekek());
        eseményKezeles();
        nezet.setVisible(true);
    }
    
     
    public void eseményKezeles() {
        
        nezet.getBtnkereses().addActionListener(e->{
            try {
                
                if(!(nezet.getTfKeresMezo().getText().isEmpty())){
                    System.out.println(dao.Szures((String)nezet.getJcKeres().getSelectedItem(), nezet.getTfKeresMezo().getText()));
                    tabla.tablaFeltolt(dao.adatokTablaba(dao.Szures((String)nezet.getJcKeres().getSelectedItem(), nezet.getTfKeresMezo().getText())),nezet.getJtTermekek());
                }
                else{
                    tabla.tablaFeltolt(dao.adatokTablaba(dao.Lekerdezes()), nezet.getJtTermekek());
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
