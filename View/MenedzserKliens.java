
package View;

import Controller.BejelentkezesController;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import Controller.termekHozzaadController;
import Controller.termékKezelController;
import Controller.SzallitoLevelController;
import Controller.vevoHozzaadController;
import Controller.KategoriaController;
import Controller.BeszallitoHozzaadController;
import Controller.BeszallitoKezelController;
import Controller.FelhasznaloHozzaadController;
import Controller.FelhasznaloKezelController;
import Controller.JelentesController;
import Controller.VevoKezelController;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class MenedzserKliens extends javax.swing.JFrame {
    
    /**
     * Creates new form adminKliens
     */
    public MenedzserKliens() {
        initComponents();
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenedzserKliens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenedzserKliens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenedzserKliens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenedzserKliens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        setLocationRelativeTo(jMenu1);
        setTitle("Menedzser kliens");
     

    }

   
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        termékekMenü = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        szállítólevélMenü = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        felhaszHozzáadItem = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        kijelentkezésMenü = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/0770_NikeWarehouse_s1-this-one.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 510));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, -1, -1));

        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenuBar1.setPreferredSize(new java.awt.Dimension(104, 60));

        termékekMenü.setText("Termékek");
        termékekMenü.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jMenuItem1.setText("Hozzáadás");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        termékekMenü.add(jMenuItem1);

        jMenuItem2.setText("Készlet");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        termékekMenü.add(jMenuItem2);

        jMenuItem8.setText("Ketegória hozzáad");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        termékekMenü.add(jMenuItem8);

        jMenuBar1.add(termékekMenü);

        szállítólevélMenü.setText("Szállítólevél");
        szállítólevélMenü.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jMenuItem11.setText("Kiszállítás");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        szállítólevélMenü.add(jMenuItem11);

        jMenuBar1.add(szállítólevélMenü);

        jMenu1.setText("Felhasználók");
        jMenu1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        felhaszHozzáadItem.setText("Hozzáadás");
        felhaszHozzáadItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                felhaszHozzáadItemActionPerformed(evt);
            }
        });
        jMenu1.add(felhaszHozzáadItem);

        jMenuItem9.setText("Nézet");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem9);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Vevők");
        jMenu2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jMenuItem6.setText("Hozzáadás");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setText("Nézet");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Jelentések");
        jMenu3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        jMenuItem4.setText("Elszámolások");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Beszállítók");
        jMenu4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jMenuItem5.setText("Hozzáadás");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuItem12.setText("Nézet");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem12);

        jMenuBar1.add(jMenu4);

        kijelentkezésMenü.setText("Kijelentkezés");
        kijelentkezésMenü.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        kijelentkezésMenü.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kijelentkezésMenüMouseClicked(evt);
            }
        });
        kijelentkezésMenü.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kijelentkezésMenüActionPerformed(evt);
            }
        });
        jMenuBar1.add(kijelentkezésMenü);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        try {
            new termékKezelController();
            
//        tMódosít.getMódosítButton().setEnabled(false);
        } catch (SQLException ex) {
            Logger.getLogger(MenedzserKliens.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

       new termekHozzaadController();
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void felhaszHozzáadItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_felhaszHozzáadItemActionPerformed
new FelhasznaloHozzaadController();
        
    }//GEN-LAST:event_felhaszHozzáadItemActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        new JelentesController();
        
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void kijelentkezésMenüActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kijelentkezésMenüActionPerformed
//        try {
//           BejelentkezesController bc= new BejelentkezesController();
//          
//        } catch (SQLException ex) {
//            Logger.getLogger(MenedzserKliens.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_kijelentkezésMenüActionPerformed

    private void kijelentkezésMenüMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kijelentkezésMenüMouseClicked
        try {
            BejelentkezesController bc= new BejelentkezesController();
        } catch (SQLException ex) {
            Logger.getLogger(MenedzserKliens.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_kijelentkezésMenüMouseClicked

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        try {
           
new SzallitoLevelController();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
       new vevoHozzaadController();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        try {
            new VevoKezelController();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        try {
            new FelhasznaloKezelController();
        } catch (SQLException ex) {
            Logger.getLogger(MenedzserKliens.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        new BeszallitoHozzaadController();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        try {
            new BeszallitoKezelController();
        } catch (SQLException ex) {
            Logger.getLogger(MenedzserKliens.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        new KategoriaController();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenedzserKliens().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem felhaszHozzáadItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu kijelentkezésMenü;
    private javax.swing.JMenu szállítólevélMenü;
    private javax.swing.JMenu termékekMenü;
    // End of variables declaration//GEN-END:variables
}
