/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Adatbazis.DAO;
import Adatbazis.KategDAO;
import Adatbazis.beszallitoDAO;
import Model.KategoriaModell;
import Model.beszallitoModell;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author ndavid97
 */
public class kezelComboBox {
//     private KategoriaModell kategoriak=new KategoriaModell();
//     private beszallitoModell beszallitok=new beszallitoModell();
    public void feltoltComboBoxKategoria(JComboBox box,ArrayList lekerdezes) {
        ArrayList <KategoriaModell>lista=new ArrayList<>();
        lista=lekerdezes;
        for (int i = 0; i < lista.size(); i++) {
            box.addItem(lista.get(i).getKategoriaNev());           
        }
    }
    
     public void feltoltComboBoxBeszallito(JComboBox box,ArrayList lekerdezes) {
        ArrayList <beszallitoModell> lista=new ArrayList<>();
        lista=lekerdezes;
        for (int i = 0; i < lista.size(); i++) {
            box.addItem(lista.get(i).getBeszallitoNev());           
        }
    }
}
