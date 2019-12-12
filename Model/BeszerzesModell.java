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
public class BeszerzesModell {
    private int beszerzesID;
    private int beszallitoID;
    private int termekID;
    private int mennyiseg;
    private String datum;
    private int torolt;

    public BeszerzesModell() {
        this(0, 0, 0);
    }

    public BeszerzesModell(int beszallitoID, int termekID, int mennyiseg) {
        this(0, beszallitoID, termekID, mennyiseg, null,0);
    }

    public BeszerzesModell(int beszerzesID, int beszallitoID, int termekID, int mennyiseg, String datum,int torlot) {
        this.beszerzesID = beszerzesID;
        this.beszallitoID = beszallitoID;
        this.termekID = termekID;
        this.mennyiseg = mennyiseg;
        this.datum = datum;
        this.torolt=torolt;
    }

    public int getBeszerzesID() {
        return beszerzesID;
    }

    public int getBeszallitoID() {
        return beszallitoID;
    }

    public int getTermekID() {
        return termekID;
    }

    public int getMennyiseg() {
        return mennyiseg;
    }

    public String getDatum() {
        return datum;
    }

    public int getTorolt() {
        return torolt;
    }

    @Override
    public String toString() {
        return "BeszerzesModell{" + "beszerzesID=" + beszerzesID + ", beszallitoID=" + beszallitoID + ", termekID=" + termekID + ", mennyiseg=" + mennyiseg + ", datum=" + datum + ", torolt=" + torolt + '}';
    }

    
    
    

    
    
    
}
