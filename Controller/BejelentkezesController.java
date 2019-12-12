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
import Model.felhasznaloModell;
import View.BejelentkezesDialog;
import View.MenedzserKliens;
import Model.felhasznaloModell;
import Adatbazis.felhasznaloDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import View.Bejelentkezes;
import View.MenedzserKliens;
import FajlKezeles.Titkositas;
import View.eladoKliens;

public class BejelentkezesController {
    private Bejelentkezes bejelentAblak;
    private felhasznaloModell bejelentAdatok=null;
    private felhasznaloModell modell=null;
    private felhasznaloDAO dao=null;
    private String felhNev;
    private String jelszo;
    private String nyomtatottNev;
    private int jog;
    private ArrayList<felhasznaloModell> felhLista=null;
    private MenedzserKliens ablakMenedzser;
    private eladoKliens ablakElado;
    private Titkositas titkos=null;
    public BejelentkezesController() throws SQLException {

bejelentAblak=new Bejelentkezes();
        dao=new felhasznaloDAO();
        felhLista=dao.Lekerdezes();
        titkos=new Titkositas();
//        System.out.println(felhLista.size());
        egyeztetes();
        
        bejelentAblak.setVisible(true);
        
    }
    
    public void egyeztetes(){
         bejelentAblak.getBtnBejelentkezes().addActionListener(e->{
        felhNev=bejelentAblak.getTfFelhNev().getText();
//             System.out.println(felhNev);
        jelszo=new String(bejelentAblak.getTfJelszo().getPassword());
             
        int n=0;
        String üzenet="";
//        System.out.println(titkos.titkositas(jelszo)); 
             try {
                 felhLista=dao.Lekerdezes();
             } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(ablakElado, ex);
             }
             for (felhasznaloModell felhasznalo : felhLista) {
                 if(felhasznalo.getFelhNev().equals(felhNev) && felhasznalo.getJelszo().equals(titkos.titkositas(jelszo))){
                     n++;
                     nyomtatottNev=felhasznalo.getNyomtatottNev();
                     jog=felhasznalo.getJogkor();
                     break;
                 }
             }
             if(n==0){
                 üzenet="Hibás felhasználó név vagy jelszó";
             }
             else{üzenet="Sikeres bejelentkezés";}
             if(n!=0 && jog==1){
             ablakMenedzser= new MenedzserKliens();
             ablakMenedzser.setVisible(true);
             bejelentAblak.dispose();
             }
             else if(n!=0 && jog==0){ablakElado=new eladoKliens();
             ablakElado.setVisible(true);
             bejelentAblak.dispose();
             }
          JOptionPane.showMessageDialog(bejelentAblak, üzenet,"Üzenet" , 1);
        }); 
    }
    
 
}
