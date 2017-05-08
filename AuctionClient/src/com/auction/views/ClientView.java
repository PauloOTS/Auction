/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auction.views;

import javax.swing.JOptionPane;

/**
 *
 * @author abacate
 */
public class ClientView extends javax.swing.JFrame {

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
            java.util.logging.Logger.getLogger(ClientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientView().setVisible(true);
            }
        });
    }
    
    /**
     * Creates new form ClientView
     */
    public ClientView() {
        
        initComponents();
        String name = null;
        while(name == null || "".equals(name)){
            name = JOptionPane.showInputDialog("What's your name?");
            if (name == null || "".equals(name)){
                JOptionPane.showMessageDialog(null, "Please, Insert a valid username.");
            }
        }
        
        this.lblUsername.setText("Welcome " + name + " !");
 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsername = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableAuctions = new javax.swing.JTable();
        menuBar = new javax.swing.JMenuBar();
        menuAuction = new javax.swing.JMenu();
        itemNewAuction = new javax.swing.JMenuItem();
        itemNewBid = new javax.swing.JMenuItem();
        itemEndAuction = new javax.swing.JMenuItem();
        menuView = new javax.swing.JMenu();
        itemAuctions = new javax.swing.JMenuItem();
        itemAuctionsInterested = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        lblUsername.setText("Username");
        lblUsername.setName("lblUsername"); // NOI18N

        tableAuctions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Auction ID", "Auctioneer", "Item", "Actual Value", "Actual Winner", "Time remaining"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableAuctions);

        menuAuction.setText("Auction");

        itemNewAuction.setText("New Auction");
        menuAuction.add(itemNewAuction);

        itemNewBid.setText("New Bid");
        itemNewBid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNewBidActionPerformed(evt);
            }
        });
        menuAuction.add(itemNewBid);

        itemEndAuction.setText("End Auction");
        menuAuction.add(itemEndAuction);

        menuBar.add(menuAuction);

        menuView.setText("View");

        itemAuctions.setText("Auctions");
        menuView.add(itemAuctions);

        itemAuctionsInterested.setText("Auctions Interested");
        menuView.add(itemAuctionsInterested);

        menuBar.add(menuView);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(301, 301, 301)
                .addComponent(lblUsername)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemNewBidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNewBidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemNewBidActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itemAuctions;
    private javax.swing.JMenuItem itemAuctionsInterested;
    private javax.swing.JMenuItem itemEndAuction;
    private javax.swing.JMenuItem itemNewAuction;
    private javax.swing.JMenuItem itemNewBid;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JMenu menuAuction;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuView;
    private javax.swing.JTable tableAuctions;
    // End of variables declaration//GEN-END:variables
}