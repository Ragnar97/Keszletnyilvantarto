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
import Adatbazis.felhasznaloDAO;
import View.FelhasznaloKezelDialog;
import Model.felhasznaloModell;
import View.MenedzserKliens;
import Controller.tablaMuveletek;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import FajlKezeles.Titkositas;

public class FelhasznaloKezelController {
    private FelhasznaloKezelDialog nezet=null;
    private felhasznaloModell modell=null;
    private felhasznaloDAO dao=null;
    private tablaMuveletek tabla=null;
    private Titkositas titkositas=null;
    

    public FelhasznaloKezelController() throws SQLException {
        nezet=new FelhasznaloKezelDialog(new MenedzserKliens(), true);
        dao=new felhasznaloDAO();
        tabla=new tablaMuveletek();
        tabla.tablaFeltolt(dao.adatokTablaba(dao.Lekerdezes()), nezet.getjTable1());
        titkositas=new Titkositas();
        esemenyKezeles();
        nezet.setVisible(true);
    }
    
    public void esemenyKezeles(){
        nezet.getBtnKeres().addActionListener(e->{
            
            if(!(nezet.getTfKeres().getText().isEmpty())){
                try {
                    tabla.tablaFeltolt(dao.adatokTablaba(dao.Szures((String)nezet.getJcKeres().getSelectedItem(), nezet.getTfKeres().getText())), nezet.getjTable1());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(nezet, ex);
                }
            }
            else{
                try {
                    tabla.tablaFeltolt(dao.adatokTablaba(dao.Lekerdezes()), nezet.getjTable1());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(nezet, ex);
                }
            }
        });
        nezet.getBtnModosit().addActionListener(e->{
            felhasznaloModell modositott=new felhasznaloModell(nezet.getTfFelhNev().getText(), titkositas.titkositas(new String(nezet.getPsJelszo().getPassword())), nezet.getCbJog().getSelectedIndex(), nezet.getTfNev().getText(), 0);
            try {
                dao.Frissites(modositott);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(nezet, ex);
            }
            
        });
        nezet.getBtnTorles().addActionListener(e->{
             ArrayList<felhasznaloModell> lekérdezés = new ArrayList<>();
            try {
                lekérdezés = dao.Lekerdezes();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(nezet, ex);
            }
            int count=0;
            for (felhasznaloModell lekérdezé : lekérdezés) {
                if(lekérdezé.getJogkor()==1){count++;}
            }
            if(count>1){
                felhasznaloModell torles = lekérdezés.get(nezet.getjTable1().getSelectedRow());
                int veglegesit=JOptionPane.showOptionDialog(nezet, "kiválasztott termék törlése", "Biztos benne?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                if(veglegesit==0){
                 try {
                     dao.Torles(torles);
                 } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(nezet, ex);
                 }}
                JOptionPane.showMessageDialog(nezet, "Kiválasztott termék törlése végrehajtva");
                 try {
                     tabla.tablaFeltolt(dao.adatokTablaba(dao.Lekerdezes()), nezet.getjTable1());
                 } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(nezet, ex);
                 }
                }
//                
        });
        nezet.getjTable1().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                System.out.println("működik");
                mezokFeltolt();
            }
        });
         
    }
    private void mezokFeltolt()  {
                 int sorszam = nezet.getjTable1().getSelectedRow();
          
                 nezet.getTfNev().setText(nezet.getjTable1().getValueAt(sorszam, 0).toString());
          nezet.getTfFelhNev().setText(nezet.getjTable1().getValueAt(sorszam, 1).toString());         
          nezet.getPsJelszo().setText(nezet.getjTable1().getValueAt(sorszam, 2).toString());
          
          nezet.getCbJog().setSelectedIndex((int) nezet.getjTable1().getValueAt(sorszam, 3));
            }
}
