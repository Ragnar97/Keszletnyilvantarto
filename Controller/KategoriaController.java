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
import View.KategoriaDialog;
import Model.KategoriaModell;
import View.MenedzserKliens;
import Adatbazis.KategDAO;
import Controller.Validalas;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class KategoriaController {
    private KategoriaDialog nezet=null;
    private KategoriaModell modell=null;
    private KategDAO dao=null;
    private Validalas vizsgalat=null;
    public KategoriaController() {
        nezet=new KategoriaDialog(new MenedzserKliens(), true);
        dao=new KategDAO();
        vizsgalat=new Validalas();
        esemenyKezeles();
        nezet.setVisible(true);
    }
    
    public void esemenyKezeles(){
    nezet.getjButton1().addActionListener(e->{
        try {
            boolean egyezes=false;
            for (int i = 0; i < dao.Lekerdezes().size(); i++) {
                if(dao.Lekerdezes().get(i).getKategoriaNev().equals(nezet.getjTextField1().getText())){egyezes=true;}
            }
            if(!egyezes){
                try {
                    modell=new KategoriaModell(nezet.getjTextField1().getText());
                    dao.Beszuras(modell);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(nezet, ex);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(nezet, ex);
        }
    });
    }
    
    
}
