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
public class RendelesModell {
    private int rendelesID;
    private String kelt;
    private int termekID;
    private int vevoID;
    private int mennyiseg;
    private int vegOsszeg;
    private int torolt;

    public RendelesModell() {
        this(0,0, 0, 0, 0);
    }

    
    
    public RendelesModell(int rendelesID,int termekID, int vevoID, int mennyiseg, int vegOsszeg) {
        this(rendelesID, null, termekID, vevoID, mennyiseg, vegOsszeg,0);
    }

    public RendelesModell(int rendelesID, String kelt, int termekID, int vevoID, int mennyiseg, int vegOsszeg,int torolt) {
        this.rendelesID = rendelesID;
        this.kelt = kelt;
        this.termekID = termekID;
        this.vevoID = vevoID;
        this.mennyiseg = mennyiseg;
        this.vegOsszeg = vegOsszeg;
        this.torolt=torolt;
    }

    public int getRendelesID() {
        return rendelesID;
    }

    public String getKelt() {
        return kelt;
    }

    public int getTermekID() {
        return termekID;
    }

    public int getVevoID() {
        return vevoID;
    }

    public int getMennyiseg() {
        return mennyiseg;
    }

    public int getVegOsszeg() {
        return vegOsszeg;
    }

    public int getTorolt() {
        return torolt;
    }

    @Override
    public String toString() {
        return "RendelesModell{" + "rendelesID=" + rendelesID + ", kelt=" + kelt + ", termekID=" + termekID + ", vevoID=" + vevoID + ", mennyiseg=" + mennyiseg + ", vegOsszeg=" + vegOsszeg + ", torolt=" + torolt + '}';
    }

    
    
    
            
}
