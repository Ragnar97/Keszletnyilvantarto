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
public class KategoriaModell {
    private int kategoriaID;
    private String kategoriaNev;

    public KategoriaModell() {
        this(null);
    }

    public KategoriaModell(String kategNév) {
        this(0, kategNév);
    }

    public KategoriaModell(int kategoriaID, String kategNév) {
        this.kategoriaID = kategoriaID;
        this.kategoriaNev = kategNév;
    }

    public int getKategoriaID() {
        return kategoriaID;
    }

    public String getKategoriaNev() {
        return kategoriaNev;
    }

    @Override
    public String toString() {
        return  kategoriaID + "," + kategoriaNev;
    }
    

    
   
    
    
}
