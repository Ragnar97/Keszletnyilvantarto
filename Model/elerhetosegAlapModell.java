/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author ndavid97
 */
public class elerhetosegAlapModell {
    private int elerhetosegID;
     private String email;
    private String telefon;
    private int irsz;
    private String varos;
    private String utca;
    private int hazszam;
    private String weblap;

    public elerhetosegAlapModell() {
        this(0,null, null, 0, null, null, 0,null);
       
    }

    public elerhetosegAlapModell(int elerhetosegID,String email, String telefon, int irsz, String varos, String utca,int hazszam, String weblap) {
        this.elerhetosegID=elerhetosegID;
        this.email = email;
        this.telefon = telefon;
        this.irsz = irsz;
        this.varos = varos;
        this.utca = utca;
        this.hazszam=hazszam;
        this.weblap = weblap;
        
    }

    public String getVaros() {
        return varos;
    }

    public int getElerhetosegID() {
        return elerhetosegID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public int getIrsz() {
        return irsz;
    }

    public void setIrsz(int irsz) {
        this.irsz = irsz;
    }

    public String getVarros() {
        return varos;
    }

    public int getHazszam() {
        return hazszam;
    }

    public void setVáros(String város) {
        this.varos = város;
    }

    public String getUtca() {
        return utca;
    }

    public void setUtca(String utca) {
        this.utca = utca;
    }

    public String getWeblap() {
        return weblap;
    }

    public void setWeblap(String weblap) {
        this.weblap = weblap;
    }

    @Override
    public String toString() {
        return "el\u00e9rhet\u0151s\u00e9gAlapModell{" + elerhetosegID+"email=" + email + ", telefon=" + telefon + ", irsz=" + irsz + ", v\u00e1ros=" + varos + ", utca=" + utca + ",házszám="+hazszam+", weblap=" + weblap + '}';
    }
    
    
}
