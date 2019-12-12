/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Adatbazis.TermekDAO;
import View.MenedzserKliens;
import Adatbazis.vevoDAO;
import java.sql.SQLException;
import Controller.tablaMuveletek;
import Model.TermekModel;
import Model.vevoModell;
import View.SzallitoLevelDialog;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Header;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import FajlKezeles.ExcelBeOlvasKiir;
import Adatbazis.RendelesDAO;
import Model.RendelesModell;
import Controller.BejelentkezesController;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
public class SzallitoLevelController {
    private  SzallitoLevelDialog nezet=null;
    private tablaMuveletek tabla=new tablaMuveletek();
    private TermekDAO termekDao=new TermekDAO();
    private TermekModel termek=null;
    private ArrayList<TermekModel> termekLista=null;
    private vevoModell vevo=null;
    private vevoDAO vevoDao=new vevoDAO();
    private ArrayList<vevoModell> vevoList=null;
    private ExcelBeOlvasKiir excel=null;
    private vevoModell elado=null;
    private BejelentkezesController felhasznalo=null;
    private RendelesDAO rendeles=null;
    private RendelesModell rendTetel=null;
    private final int rendelesszam;
    private ArrayList<RendelesModell> rendelesLista=null;
    private TermekModel termekSeged =null;
    private double teljesVegAR=0;
    private static ArrayList<Integer> árak=new ArrayList<>();
    public SzallitoLevelController() throws SQLException {
        nezet=new SzallitoLevelDialog(new MenedzserKliens(), true);
        excel=new ExcelBeOlvasKiir();
        esemenyKezeles();
        elado=excel.beOlvasVevo("cegesAdatok.txt").get(0);
        tabla.tablaFeltolt(termekDao.adatokTablaba(termekDao.Lekerdezes()),nezet.getjTable2());
        tabla.tablaFeltolt(vevoDao.adatokTablaba(vevoDao.Lekerdezes()), nezet.getTblVevő());
        termekLista=new ArrayList<>();
        rendeles=new RendelesDAO();
        rendelesLista=rendeles.Lekerdezes();
        if(rendelesLista.size()!=0){
        rendelesszam=rendelesLista.get(rendelesLista.size()-1).getRendelesID()+1;}
        else{rendelesszam=1;}
        System.out.println(rendelesszam);
//        tablaElem();
//        run();
        nezet.setVisible(true);
         
    }
    public void esemenyKezeles() {
        nezet.getBtnHozzaAdas().addActionListener(e -> {

            try {
                tablaElem();
                termekSeged = termekDao.Lekerdezes().get(nezet.getjTable2().getSelectedRow());
                int mennyiség = Integer.parseInt(nezet.getTfMennyiseg().getText());
                rendTetel = new RendelesModell(rendelesszam, termekSeged.getTermekID(),
                        vevoDao.Lekerdezes().get(nezet.getTblVevő().getSelectedRow()).getVevoID(), mennyiség, mennyiség * termekSeged.getEgysegar());
                rendeles.Beszuras(rendTetel);
                if(mennyiség<=termekSeged.getMennyiseg()){
                termekSeged.setMennyiseg(termekSeged.getMennyiseg() - mennyiség);
                termek = termekSeged;
                termekDao.Frissites(termek);
                tabla.tablaFeltolt(termekDao.adatokTablaba(termekDao.Lekerdezes()), nezet.getjTable2());
                árak.add(mennyiség);}
                else{JOptionPane.showMessageDialog(nezet, "Nincs ennyi készleten", "Hiba", JOptionPane.ERROR_MESSAGE);}
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(nezet, ex, "hiba", JOptionPane.ERROR_MESSAGE);
            }
        });
            nezet.getBtnGeneralPdf().addActionListener(es -> {
                try {
                    PDF();
                } catch (SQLException ex) {
                   JOptionPane.showMessageDialog(nezet, ex, "hiba", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(nezet, ex, "hiba", JOptionPane.ERROR_MESSAGE);
                } catch (DocumentException ex) {
                    JOptionPane.showMessageDialog(nezet, ex, "hiba", JOptionPane.ERROR_MESSAGE);
                }
            });
            nezet.getBtnVevoSzures().addActionListener(es->{
                try {
                
                if(!(nezet.getTfKeresVevo().getText().isEmpty())){
//                    System.out.println(dao.Szűrés((String)nezet.getJcKeres().getSelectedItem(), nezet.getTfKeresMező().getText()));
                tabla.tablaFeltolt(vevoDao.adatokTablaba(vevoDao.Szures(nezet.getJcKeresVevo().getSelectedItem().toString(), nezet.getTfKeresVevo().getText())), nezet.getTblVevő());
                
                    
                }
                else{
                tabla.tablaFeltolt(vevoDao.adatokTablaba(vevoDao.Lekerdezes()), nezet.getTblVevő());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(nezet, ex, "hiba", JOptionPane.ERROR_MESSAGE);
            }
            });
            
            nezet.getBtnTermekSzures().addActionListener(es->{
                try {
                
                if(!(nezet.getTfKeresTermek().getText().isEmpty())){
                    System.out.println(termekDao.Szures((String)nezet.getJcKeresTermek().getSelectedItem(), nezet.getTfKeresTermek().getText()));
                    tabla.tablaFeltolt(termekDao.adatokTablaba(termekDao.Szures((String)nezet.getJcKeresTermek().getSelectedItem(), nezet.getTfKeresTermek().getText())),nezet.getTblTermek());
                }
                else{
                    tabla.tablaFeltolt(termekDao.adatokTablaba(termekDao.Lekerdezes()), nezet.getTblTermek());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(nezet, ex, "hiba", JOptionPane.ERROR_MESSAGE);
            }
            });
        
    }
    public void tablaElem() throws SQLException{
      
            int sorszám = 0;
            sorszám = nezet.getjTable2().getSelectedRow();
            ArrayList<TermekModel> lekérdezés = null;
            try {
                lekérdezés = termekDao.Lekerdezes();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(nezet, ex, "hiba", JOptionPane.ERROR_MESSAGE);
            }

            termek = lekérdezés.get(sorszám);
            termekLista.add(termek);
            
            System.out.println(termek.toString());

            int oszlop = nezet.getTblVevő().getColumnCount();
            ArrayList<vevoModell> lekérdezés2 = null;
            try {
                lekérdezés2 = vevoDao.Lekerdezes();
                vevo=lekérdezés2.get(nezet.getTblVevő().getSelectedRow());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(nezet, ex, "hiba", JOptionPane.ERROR_MESSAGE);
            }

         
    
       
   
    }
    
    public void PDF() throws SQLException, IOException, DocumentException{

        int termekDarab = nezet.getjTable2().getSelectedRowCount();
        Document doc = new Document();

        PdfWriter kiIr = PdfWriter.getInstance(doc, new FileOutputStream("Szállítólevél.pdf"));
        doc.open();

        Paragraph cim = new Paragraph("Szállítólevél", new Font(Font.FontFamily.TIMES_ROMAN, 25f, 0));
        cim.setAlignment(1);
        doc.add(cim);
        doc.add(new Chunk());

        PdfPTable alanyokTablazat = new PdfPTable(2);
        alanyokTablazat.setWidthPercentage(100);
        alanyokTablazat.addCell(new Paragraph("Cégnév név: " + elado.getVevonev() + "\n" + "Email-cím: " + elado.getEmail()
                + "\n" + "Telefon szám: " + elado.getTelefon() + "\n" + "Irányírószám: " + elado.getIrsz() + "\n" + "Város: " + elado.getVaros()
                + "\n" + "Utca: " + elado.getUtca() + "\n" + "Házszám: " + elado.getHazszam() + "\n" + "weboldal: " + elado.getWeblap()));
        alanyokTablazat.addCell(new Paragraph("Teljes név: " + vevo.getVevonev() + "\n" + "Email-cím: " + vevo.getEmail()
                + "\n" + "Telefon szám: " + vevo.getTelefon() + "\n" + "Irányírószám: " + vevo.getIrsz() + "\n" + "Város: " + vevo.getVaros()
                + "\n" + "Utca: " + vevo.getUtca() + "\n" + "Házszám: " + vevo.getHazszam() + "\n" + "weboldal: " + vevo.getWeblap()));
        alanyokTablazat.setSpacingAfter(50f);
        doc.add(alanyokTablazat);
        PdfPTable termekTablazat = new PdfPTable(7);
        termekTablazat.setWidthPercentage(100);
        termekTablazat.addCell("termék azonosító");
        termekTablazat.addCell("Termék megnevezés");
        termekTablazat.addCell("Kategória");
        termekTablazat.addCell("Leírás");
        termekTablazat.addCell("egységár");
        termekTablazat.addCell("Rendelt mennyiség");
        termekTablazat.addCell("Bruttó végösszeg");

        for (int i = 0; i < termekLista.size(); i++) {
            termekTablazat.addCell(termekLista.get(i).getTermekID() + "");
            termekTablazat.addCell(termekLista.get(i).getTermekNev());
            termekTablazat.addCell(termekLista.get(i).getKategoria());
            termekTablazat.addCell(termekLista.get(i).getLeiras());
            termekTablazat.addCell(termekLista.get(i).getEgysegar() + "");
            termekTablazat.addCell(árak.get(i) + "");
            int bruttoAr = árak.get(i) * termekLista.get(i).getEgysegar();
            bruttoAr = bruttoAr + (bruttoAr / 100) * 27;
            teljesVegAR += bruttoAr;
            termekTablazat.addCell(bruttoAr + " FT");

        }
        termekTablazat.setSpacingAfter(30f);
        doc.add(termekTablazat);

        doc.add(new Paragraph("Teljes bruttó végösszeg: " + teljesVegAR + " FT"));
        doc.addCreationDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        doc.add(new Paragraph("Kelt:" + dateFormat.format(new Date())));
        doc.close();
        kiIr.close();

        }


      
        }

    
    
    

