/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FajlKezeles;

import Model.BeszerzesModell;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import Model.TermekModel;
import Model.vevoModell;
import Model.beszallitoModell;
import Model.elerhetosegAlapModell;
import java.util.AbstractList;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class ExcelBeOlvasKiir {
     private  List<String> nyersFájl;
     private String beszNev="";

    public String getBeszNev() {
        return beszNev;
    }
     
     public void beOlvasNyersFajl(String beFájl){
         System.out.println(beFájl);
        try {
            nyersFájl=Files.readAllLines(Paths.get(beFájl));
        } catch (IOException io) {
            System.out.println("Hiba a fájlnál");
            System.out.println(io);
            
        } 
     }
     
    public ArrayList<TermekModel>  beOlvasTermek(String beFájl){
        TermekModel modell;
        beOlvasNyersFajl(beFájl);
        ArrayList<TermekModel> excelLista=new ArrayList<>();
        if(nyersFájl!=null){
        for (String string : nyersFájl) {
            String [] tört=string.split(";");
            String tNév=tört[0];
            String kateg=tört[1];
            int menny=Integer.parseInt(tört[2]);
            int egysÁr=Integer.parseInt(tört[3]);
            String leírás=tört[4];
            beszNev=tört[5];
            modell=new TermekModel(tNév, kateg, menny, egysÁr, leírás);
            excelLista.add(modell);
            System.out.println(modell);
            ArrayList<BeszerzesModell> beszer;
            
        }}
return excelLista;}
    
    public  ArrayList<vevoModell> beolvasPartner(String beFájl){
        vevoModell vevo;
         beOlvasNyersFajl(beFájl);
        ArrayList<vevoModell> excelLista=new ArrayList<>();
        for (String string : nyersFájl) {
            String [] tört=string.split(";");
            String vNev=tört[0];
            String email=tört[1];
            String telefon=tört[2];
            int irsz=Integer.parseInt(tört[3]);
            String varos=tört[4];
            String utca=tört[5];
            int hazszam=Integer.parseInt(tört[6]);
            String weblap=tört[7];
            
            vevo=new vevoModell(vNev, email, telefon, irsz, varos, utca, hazszam, weblap);
      
            excelLista.add(vevo);
            
        }
return excelLista;
    }
    
     public ArrayList<vevoModell>  beOlvasVevo(String beFájl){
        vevoModell vevo;
         beOlvasNyersFajl(beFájl);
        ArrayList<vevoModell> excelLista=new ArrayList<>();
        for (String string : nyersFájl) {
            String [] tört=string.split(";");
            String vNev=tört[0];
            String email=tört[1];
            String telefon=tört[2];
            int irsz=Integer.parseInt(tört[3]);
            String varos=tört[4];
            String utca=tört[5];
            int hazszam=Integer.parseInt(tört[6]);
            String weblap=tört[7];
            
            vevo=new vevoModell(vNev, email, telefon, irsz, varos, utca, hazszam, weblap);
      
            excelLista.add(vevo);
            
        }
return excelLista;
}
     
     public ArrayList<beszallitoModell>  beOlvasBeszallito(String beFájl){
        beszallitoModell beszallito;
         beOlvasNyersFajl(beFájl);
        ArrayList<beszallitoModell> excelLista=new ArrayList<>();
        for (String string : nyersFájl) {
            String [] tört=string.split(";");
            String bNev=tört[0];
            String email=tört[1];
            String telefon=tört[2];
            int irsz=Integer.parseInt(tört[3]);
            String varos=tört[4];
            String utca=tört[5];
            int hazszam=Integer.parseInt(tört[6]);
            String weblap=tört[7];
          
            beszallito=new beszallitoModell(bNev, email, telefon, irsz, varos, utca, hazszam, weblap);
            excelLista.add(beszallito);
            System.out.println(beszallito);
            
        }
return excelLista;
}
   
}
