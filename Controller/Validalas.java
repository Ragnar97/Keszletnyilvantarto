/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.JOptionPane;
import Model.TermekModel;
import Model.elerhetosegAlapModell;
/**
 *
 * @author ndavid97
 */

public class Validalas {
    private  String hibak="";
    public boolean Ures(String ertek){
     
    return ertek.length()>0;}
    
    public boolean egyezesVizsgTermek(String keresett, ArrayList<TermekModel> tartomany){
        for (int i = 0; i < tartomany.size(); i++) {
            if(tartomany.get(i).getTermekNev().equals(keresett)) return true;
        }
        
        return false;
        }
    public int egyezesVizsgPartner(elerhetosegAlapModell model, ArrayList<elerhetosegAlapModell> tartomany){
        int talalat;
        int eredmeny=-1;
        for (int i = 0; i < tartomany.size(); i++) {
            talalat=0;
//            if(tartomany.get(i).getTermekNev().equals(keresett)) return true;
            if(model.getEmail().equals(tartomany.get(i).getEmail())) {System.out.println(model.getEmail());talalat++;}
            if(model.getTelefon().equals(tartomany.get(i).getTelefon())) {System.out.println(model.getTelefon());talalat++;}
            if(model.getIrsz()==tartomany.get(i).getIrsz()){ System.out.println(model.getIrsz());talalat++;}
            if(model.getVaros().equals(tartomany.get(i).getVaros())){ System.out.println(model.getVaros());talalat++;}
            if(model.getUtca().equals(tartomany.get(i).getUtca())){ System.out.println(model.getUtca());talalat++;}
            if(model.getHazszam()==tartomany.get(i).getHazszam()){ System.out.println(model.getHazszam());talalat++;}
            if(model.getWeblap().equals(tartomany.get(i).getWeblap())){ System.out.println(model.getWeblap());talalat++;}
            System.out.println(tartomany.get(i).getWeblap());
            if(talalat==7) {eredmeny=tartomany.get(i).getElerhetosegID();
                            break;}
            
        }
        
        return eredmeny;
        }
     
    public boolean szamE(String szam) {
        try {
            return Integer.parseInt(szam) > 0;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    public boolean email(String email){
        
  return email.contains("@") ;}
    
    public boolean ellenorizHossz(String szoveg,int hossz){
    
    return szoveg.length()>=hossz;}
    
    
    
    
  
    
}
