/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adatbazis_Lekerdezesek;

/**
 *
 * @author ndavid97
 */
public class RaktarKeszlet {
    int id;
    String termekNev;
    int mennyiseg;

    public RaktarKeszlet(int id, String termekNev, int mennyiseg) {
        this.id = id;
        this.termekNev = termekNev;
        this.mennyiseg = mennyiseg;
    }

    @Override
    public String toString() {
        return "RaktarKeszlet{" + "id=" + id + ", termekNev=" + termekNev + ", mennyiseg=" + mennyiseg + '}';
    }
    
    
}
