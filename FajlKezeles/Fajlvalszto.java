/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FajlKezeles;

import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ndavid97
 */
public class Fajlvalszto {
    
    public String ablak() {
        String utvonal = "";

        UIManager.put("FileChooser.openDialogTitleText", "Forrásfájl megnyitása");
        UIManager.put("FileChooser.lookInLabelText", "Aktuális mappa:");
        UIManager.put("FileChooser.openButtonText", "Megnyitás");
        UIManager.put("FileChooser.cancelButtonText", "Mégse");
        UIManager.put("FileChooser.fileNameLabelText", "Fájl neve:");
        UIManager.put("FileChooser.filesOfTypeLabelText", "Fájl típusa:");

        JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Szöveges fájlok", "csv", "txt", "xlsx"));
        fc.setAcceptAllFileFilterUsed(false);
        if (fc.showOpenDialog(fc) == JFileChooser.APPROVE_OPTION) {
            File fajl = fc.getSelectedFile();
            
            if (fajl != null) {
                utvonal = fajl.getAbsolutePath();
            }
        }
        return utvonal;
    }
}
