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
import View.VevoNezetDialog;
import View.MenedzserKliens;
import Model.vevoModell;
import Adatbazis.vevoDAO;
import java.sql.SQLException;
import Controller.tablaMuveletek;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
public class VevoNezetController {
    private VevoNezetDialog nezet=null;
    private vevoModell modell=null;
    private vevoDAO lekerdezes=null;
    private tablaMuveletek tabla=null;

    public VevoNezetController() throws SQLException {
        nezet=new VevoNezetDialog(new MenedzserKliens(), true);
        lekerdezes=new vevoDAO();
        tabla=new tablaMuveletek();
        tabla.tablaFeltolt(lekerdezes.adatokTablaba(lekerdezes.Lekerdezes()), nezet.getTblVevo());
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
                tabla.tablaFeltolt(lekerdezes.adatokTablaba(lekerdezes.Szures(nezet.getCbSzures().getSelectedItem().toString(), nezet.getTfSzures().getText())), nezet.getTblVevo());
                }
                else{
                tabla.tablaFeltolt(lekerdezes.adatokTablaba(lekerdezes.Lekerdezes()), nezet.getTblVevo());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(nezet, ex);
                JOptionPane.showMessageDialog(nezet, ex);
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
          nezet.getTfWeblap().setText(nezet.getTblVevo().getValueAt(sorszám, 8).toString());
 
    
    
    
    }

   
    
}
