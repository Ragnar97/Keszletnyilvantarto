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
public class vevoModell extends elerhetosegAlapModell{
    private int vevoID;
    private String vevonev;
    private int torolt;
    

    public vevoModell() {
        this(null,  null, null, 0, null, null, 0,null);
    }

    
    public vevoModell(String vevonev,  String email, String telefon, int irsz, String város, String utca,int hazszam, String weblap) {
        this(0, vevonev, 0,0,  email,  telefon,  irsz,  város,  utca,hazszam,  weblap);
    }

    public vevoModell(int vevoID, String vevonev, int elerhetosegID,int torolt, String email, String telefon, int irsz, String város, String utca,int hazszam, String weblap) {
        super(elerhetosegID, email, telefon, irsz, város, utca, hazszam, weblap);
        this.vevoID = vevoID;
        this.vevonev = vevonev;
        this.torolt=torolt;
        
    }



    public int getVevoID() {
        return vevoID;
    }

    public String getVevonev() {
        return vevonev;
    }

    public int getTorolt() {
        return torolt;
    }

    @Override
    public String toString() {
        return "vevoModell{" + "vevoID=" + vevoID + ", vevonev=" + vevonev + ", torolt=" + torolt + '}'+super.toString();
    }

    
    
    
  

  
    
    

    

    
    
}

