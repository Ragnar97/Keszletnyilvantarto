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
public class beszallitoModell extends elerhetosegAlapModell {

    private int beszallitoID;
    private String beszallitoNev;
    private int torolt;

    public beszallitoModell() {
        this(null, null, null, 0, null, null, 0,null);
    }

    public beszallitoModell(String beszallitonev, String email, String telefon, int irsz, String város, String utca,int hazszam, String weblap) {
        this(0,  beszallitonev,  0,0,  email,  telefon,  irsz,  város,  utca,hazszam,  weblap);

    }

    public beszallitoModell(int beszallitoID, String beszallitonev, int elerhetosegID,int torolt, String email, String telefon, int irsz, String város, String utca,int hazszam, String weblap) {
        super(elerhetosegID, email, telefon, irsz, város, utca, hazszam, weblap);
        this.beszallitoID = beszallitoID;
        this.beszallitoNev = beszallitonev;
        this.torolt=torolt;
    }

    public int getBeszallitoID() {
        return beszallitoID;
    }

    public String getBeszallitoNev() {
        return beszallitoNev;
    }

    public int getTorolt() {
        return torolt;
    }

    @Override
    public String toString() {
        return beszallitoID + "," + beszallitoNev + "," + torolt;
    }

 

    

}
