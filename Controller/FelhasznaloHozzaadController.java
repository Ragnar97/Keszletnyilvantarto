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
import View.FelhasznaloHozzaadDialog;
import Model.felhasznaloModell;
import Adatbazis.felhasznaloDAO;
import View.MenedzserKliens;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import FajlKezeles.Titkositas;
import javax.swing.JOptionPane;
public class FelhasznaloHozzaadController {
    private FelhasznaloHozzaadDialog nezet=null;
    private felhasznaloModell modell=null;
    private felhasznaloDAO dao=null;
    private Titkositas titkosit=null;

    public FelhasznaloHozzaadController() {
        nezet=new FelhasznaloHozzaadDialog(new MenedzserKliens(), true);
        dao=new felhasznaloDAO();
        titkosit=new Titkositas();
        esemenyKezeles();
        nezet.setVisible(true);
    }
    
    public void esemenyKezeles(){
        nezet.getBtnKuldes().addActionListener(e -> {
            modell = new felhasznaloModell(nezet.getTfFelhNev().getText(), 
                    titkosit.titkositas(new String(nezet.getPsJelszo().getPassword())), 
                    nezet.getCbJog().getSelectedIndex(), nezet.getTfNev().getText(), 0);
            try {
                dao.Beszuras(modell);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(nezet, ex);
            }
    });
    }
    
    
}
