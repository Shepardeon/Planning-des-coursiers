package app;

import java.awt.Color;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import metier.EnumAlgo;
import metier.RequeteDeliver2i;
import modele.Instance;

/**
 * Fenêtre d'accueil de l'application qui s'ouvre quand l'utilisateur lance
 * l'application.
 * 
 * @author cyril
 */
public class Accueil extends javax.swing.JFrame {

    /**
     * PARAMETRES
     */
    /**
     * Entité de persistence de la base de données
     */
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PlanningCoursiersPU");
    /**
     * Entité de management de persistence de la base de données
     */
    private final EntityManager em = emf.createEntityManager();
    /**
     * Instance de la connexion à la base de données
     */
    private RequeteDeliver2i requeteDeliver2i;
    /**
     * ouvre une fenêtre Accueil
     */
    public Accueil() {
        initComponents();
        initialisationFenetre();
        initConnexion();
        remplirInstanceList();
    }
    /**
     * Fonction qui initialise les paramètres de la fenêtre
     */
    private void initialisationFenetre() {
        this.setVisible(true); // Rendre visible
        this.setSize(800, 600); // Défini la taille
        this.setLocationRelativeTo(null); // Centre la fenêtre
        this.getContentPane().setBackground(new Color(255, 227, 171));
        this.setTitle("Deliver2i");
    }
    
    /**
     * Fonction qui initialise la connection à la base de données
     */
    private void initConnexion(){
        try {
            this.requeteDeliver2i = RequeteDeliver2i.getInstance();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, ex,"ClassNotFoundException",JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex,"SQLException",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Fonction qui remplit le JList avec les instanes présente dans la base de données.
     */
    private void remplirInstanceList(){
        try {
            List<Instance> liste = requeteDeliver2i.getInstanceList();
            DefaultListModel modele = new DefaultListModel();
            liste.forEach((instance) -> {
                modele.addElement(instance);
            });
            InstanceList.setModel(modele);
        } catch (SQLException ex) {           
        }
    }
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        InstanceList = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        importButton = new javax.swing.JButton();
        afficherInstanceButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 227, 171));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jScrollPane1.setViewportView(InstanceList);

        jLabel1.setText("Liste des Instances");

        importButton.setText("Importer");
        importButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        importButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                importButtonMouseClicked(evt);
            }
        });

        afficherInstanceButton.setText("Afficher l'instance");
        afficherInstanceButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        afficherInstanceButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                afficherInstanceButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(importButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(afficherInstanceButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(importButton)
                        .addGap(33, 33, 33)
                        .addComponent(afficherInstanceButton)))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void importButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_importButtonMouseClicked
        JDialog importInstance = new ImportInstance(this,true);
        remplirInstanceList();
    }//GEN-LAST:event_importButtonMouseClicked

    private void afficherInstanceButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_afficherInstanceButtonMouseClicked
        if(InstanceList.getSelectedValue() != null){
            AfficherInstance Fenetre = new AfficherInstance(InstanceList.getSelectedValue(),em);
        }
    }//GEN-LAST:event_afficherInstanceButtonMouseClicked

    
    /** GETTER **/
    public EntityManager getEm(){
        return this.em;
    }
    
    public static void main(String args[]) {
        ///////////////////////////////////////////////
        /// GENERATED CODE STARTS HERE
        //////////////////////////////////////////////
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
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Accueil().setVisible(true);
            }
        });
        ///////////////////////////////////////////////
        /// GENERATED CODE STOPS HERE
        //////////////////////////////////////////////
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<Instance> InstanceList;
    private javax.swing.JButton afficherInstanceButton;
    private javax.swing.JButton importButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
