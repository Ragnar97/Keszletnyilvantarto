/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author ndavid97
 */
public class szállítóLevélBe extends javax.swing.JFrame {

    /**
     * Creates new form szállítóLevélKi
     */
    public szállítóLevélBe() {
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Megtekintés");
        getContentPane().add(jButton1, java.awt.BorderLayout.CENTER);

        jLabel1.setText("Beszállító keresése:");
        getContentPane().add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jLabel2.setText("Új Beszállító Felvétele:");
        getContentPane().add(jLabel2, java.awt.BorderLayout.PAGE_END);

        jButton2.setText("Megnyiás");
        getContentPane().add(jButton2, java.awt.BorderLayout.LINE_END);

        jLabel3.setText("Termék választás:");
        getContentPane().add(jLabel3, java.awt.BorderLayout.LINE_START);

        jButton3.setText("Előnézet");
        getContentPane().add(jButton3, java.awt.BorderLayout.CENTER);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Termék kód", "Termék neve", "Mennyiség", "Nettó", "Áfa", "Bruttó"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jLabel4.setText("Szállítólevél sorszáma:");
        getContentPane().add(jLabel4, java.awt.BorderLayout.CENTER);

        jLabel5.setText("Tételek:");
        getContentPane().add(jLabel5, java.awt.BorderLayout.CENTER);

        jButton4.setText("Megnyitás");
        getContentPane().add(jButton4, java.awt.BorderLayout.CENTER);

        jLabel6.setText("Fizetési mód:");
        getContentPane().add(jLabel6, java.awt.BorderLayout.CENTER);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Készpénz", "Kártya", " " }));
        getContentPane().add(jComboBox1, java.awt.BorderLayout.CENTER);

        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7, java.awt.BorderLayout.CENTER);

        jLabel8.setText("Bruttó végösszeg");
        getContentPane().add(jLabel8, java.awt.BorderLayout.CENTER);

        jButton5.setText("Véglegesítés");
        getContentPane().add(jButton5, java.awt.BorderLayout.CENTER);

        jButton6.setText("Hozzáadás Szállítólevélhez");
        getContentPane().add(jButton6, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(szállítóLevélBe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(szállítóLevélBe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(szállítóLevélBe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(szállítóLevélBe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new szállítóLevélBe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
