/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;




public class TermekModel {
    private int termekID;
    private String termekNev;
    private String kategoria;
    private int mennyiseg;
    private int egysegar;
    private String leiras;
    private int torolt;


    public TermekModel(){
        this(null, null, 0, 0, null);
        
    }
    
    
    
    public TermekModel(String termékNév, String kategória, int mennyiség, int egységár, String leírás) {
    this(0, termékNév, kategória, mennyiség, egységár, leírás,0);
    }


    public TermekModel(int termekID, String termekNev, String kategoria, int mennyiseg, int egysegar, String leiras, int torolt) {
//if(termékID!=0 && termékNév!=null && kategória!=null && mennyiség!=0 && egységár!=0 && leírás!=null){
    this.termekID=termekID;
    this.termekNev=termekNev;
    this.kategoria=kategoria;
    this.mennyiseg=mennyiseg;
    this.egysegar=egysegar;
    this.leiras=leiras;
    this.torolt=torolt;       
//        setTermékID(termékID);
//        setTermékNév(termékNév);
//        setKategória(kategória);
//        setMennyiség(mennyiség);
//        setEgységár(egységár);
//        setLeírás(leírás);
    }

    public void setTermekID(int termékID) {
        this.termekID = termékID;
    }
    
    public void setTermekNev(String termékNév) {
        this.termekNev = termékNév;
    }

    public void setKategoria(String kategória) {
        this.kategoria = kategória;
    }

    public void setMennyiseg(int mennyiség) {
        this.mennyiseg = mennyiség;
    }

    public void setEgysegar(int egységár) {
        this.egysegar = egységár;
    }

    public void setLeiras(String leírás) {
        this.leiras = leírás;
    }
    

    public int getTermekID() {
        return termekID;
    }

    public String getTermekNev() {
        return termekNev;
    }

    public String getKategoria() {
        return kategoria;
    }

    public int getMennyiseg() {
        return mennyiseg;
    }

    public int getEgysegar() {
        return egysegar;
    }
    
    public void abFeltolt(){
        
    }

    public String getLeiras() {
        return leiras;
    }

    public int getTorolt() {
        return torolt;
    }
    

    @Override
    public String toString() {
        return  termekID + "" + termekNev + "" + kategoria + "" + mennyiseg + "" + egysegar + "" + leiras;
    }
} 

