/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Adatbazis.beszallitoDAO;
import Model.beszallitoModell;
import View.BeszallitoNezetDialog;
import View.MenedzserKliens;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ndavid97
 */

public class BeszallitoNezetController {
    private  BeszallitoNezetDialog nezet=null;
    private  beszallitoModell beszallito=null;
    private beszallitoDAO dao=null;
    private tablaMuveletek tabla=null;

    public BeszallitoNezetController() {
        nezet=new BeszallitoNezetDialog(new MenedzserKliens(), true);
        dao=new beszallitoDAO();
        tabla=new tablaMuveletek();
        try {
            tabla.tablaFeltolt(dao.adatokTablaba(dao.Lekerdezes()), nezet.getTblVevo());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        esemenyKezeles();
        nezet.setTitle("Beszállítónézet");
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
                tabla.tablaFeltolt(dao.adatokTablaba(dao.Szures(nezet.getCbSzures().getSelectedItem().toString(), nezet.getTfSzures().getText())), nezet.getTblVevo());
                
                    
                }
                else{
                tabla.tablaFeltolt(dao.adatokTablaba(dao.Lekerdezes()), nezet.getTblVevo());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(nezet, ex);
                ex.printStackTrace();
            }
        });
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
          nezet.getTfWeblap().setText(nezet.getTblVevo().getValueAt(sorszám, 8).toString()+"");
 
    
    
    
    }
}
