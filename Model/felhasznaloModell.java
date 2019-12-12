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
public class felhasznaloModell {
    private String felhNev;
    private String jelszo;
    private int jogkor;
    private String nyomtatottNev;
    int torolt;

    public felhasznaloModell() {
        this(null, null, 0, null,0);
    }

    public felhasznaloModell(String felhNev, String jelszo, int jogkor, String nyomtatottNev,int torolt) {
        this.felhNev = felhNev;
        this.jelszo = jelszo;
        this.jogkor = jogkor;
        this.nyomtatottNev = nyomtatottNev;
        this.torolt=torolt;
    }

    public String getFelhNev() {
        return felhNev;
    }

    public String getJelszo() {
        return jelszo;
    }

    public int getJogkor() {
        return jogkor;
    }

    public String getNyomtatottNev() {
        return nyomtatottNev;
    }

    public int getTorolt() {
        return torolt;
    }

    @Override
    public String toString() {
        return "felhasznaloModell{" + "felhNev=" + felhNev + ", jelszo=" + jelszo + ", jogkor=" + jogkor + ", nyomtatottNev=" + nyomtatottNev + '}';
    }
    
    
    
}
