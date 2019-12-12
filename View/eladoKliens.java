
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
import Controller.BeszallitoNezetController;
import Controller.FelhasznaloHozzaadController;
import Controller.FelhasznaloKezelController;
import Controller.JelentesController;
import Controller.TermekNezetController;
import Controller.VevoKezelController;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class eladoKliens extends javax.swing.JFrame {
    private String nev;
    /**
     * Creates new form adminKliens
     */
    public eladoKliens() {
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
            java.util.logging.Logger.getLogger(eladoKliens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(eladoKliens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(eladoKliens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(eladoKliens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        setLocationRelativeTo(jMenu2);
        setTitle("Admin kliens");
     
       
    }

    

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        termékekMenü = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        szállítólevélMenü = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        kijelentkezésMenü = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/0770_NikeWarehouse_s1-this-one.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 480));

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

        jMenuItem2.setText("Készlet");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        termékekMenü.add(jMenuItem2);

        jMenuBar1.add(termékekMenü);

        szállítólevélMenü.setText("Szállítólevél");
        szállítólevélMenü.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jMenuItem10.setText("Beszállítás");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        szállítólevélMenü.add(jMenuItem10);

        jMenuItem11.setText("Kiszállítás");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        szállítólevélMenü.add(jMenuItem11);

        jMenuBar1.add(szállítólevélMenü);

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

        jMenu4.setText("Beszállítók");
        jMenu4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

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
            new TermekNezetController();
            
//        tMódosít.getMódosítButton().setEnabled(false);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(jMenu2, ex);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void kijelentkezésMenüActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kijelentkezésMenüActionPerformed
        this.dispose();
    }//GEN-LAST:event_kijelentkezésMenüActionPerformed

    private void kijelentkezésMenüMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kijelentkezésMenüMouseClicked
        
        try {
            BejelentkezesController bc= new BejelentkezesController();
        } catch (SQLException ex) {
            Logger.getLogger(MenedzserKliens.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_kijelentkezésMenüMouseClicked

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
       new szállítóLevélBe().setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        try {
            //        new szállítóLevélKi().setVisible(true);
//new SzallitoLevelDialog(this, rootPaneCheckingEnabled).setVisible(rootPaneCheckingEnabled);
//new NewJDialog2(this, rootPaneCheckingEnabled).setVisible(rootPaneCheckingEnabled);
new SzallitoLevelController();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(jMenu2, ex);
        }
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
       new vevoHozzaadController();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        try {
            new VevoKezelController();
        } catch (SQLException ex) {
            Logger.getLogger(eladoKliens.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        new BeszallitoNezetController();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new eladoKliens().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu kijelentkezésMenü;
    private javax.swing.JMenu szállítólevélMenü;
    private javax.swing.JMenu termékekMenü;
    // End of variables declaration//GEN-END:variables
}
