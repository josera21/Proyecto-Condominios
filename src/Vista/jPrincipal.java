/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

/**
 *
 * @author JoseR
 */
public class jPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form jPrincipal
     */
    public jPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemCliente = new javax.swing.JMenuItem();
        jMenuItemCasa = new javax.swing.JMenuItem();
        jMenuItemUrb = new javax.swing.JMenuItem();
        jMenuItemGastosExtra = new javax.swing.JMenuItem();
        jMenuReportes = new javax.swing.JMenu();
        jMenuItemReporteGastoExtra = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Opciones");

        jMenuItemCliente.setText("Cliente");
        jMenu1.add(jMenuItemCliente);

        jMenuItemCasa.setText("Casa");
        jMenu1.add(jMenuItemCasa);

        jMenuItemUrb.setText("Urbanizacion");
        jMenu1.add(jMenuItemUrb);

        jMenuItemGastosExtra.setText("Gastos Extra");
        jMenu1.add(jMenuItemGastosExtra);

        jMenuBar1.add(jMenu1);

        jMenuReportes.setText("Reportes");

        jMenuItemReporteGastoExtra.setText("Gastos Extras");
        jMenuReportes.add(jMenuItemReporteGastoExtra);

        jMenuBar1.add(jMenuReportes);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 442, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    
    public JMenuItem getjMenuItemCliente(){
        return jMenuItemCliente;
    }
    
    public JMenuItem getjMenuItemCasa() {
        return jMenuItemCasa;
    }
    
    public JMenuItem getjMenuItemUrb() {
        return jMenuItemUrb;
    }
    
    public JMenuItem getjMenuItemGastoExtra() {
        return jMenuItemGastosExtra;
    }
    
    public JMenuItem getjMenuItemReporteGastoEx() {
        return jMenuItemReporteGastoExtra;
    }
    
    public void agregarListener(ActionListener action){
        this.jMenuItemCliente.addActionListener(action);
        this.jMenuItemCasa.addActionListener(action);
        this.jMenuItemUrb.addActionListener(action);
        this.jMenuItemGastosExtra.addActionListener(action);
        this.jMenuItemReporteGastoExtra.addActionListener(action);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemCasa;
    private javax.swing.JMenuItem jMenuItemCliente;
    private javax.swing.JMenuItem jMenuItemGastosExtra;
    private javax.swing.JMenuItem jMenuItemReporteGastoExtra;
    private javax.swing.JMenuItem jMenuItemUrb;
    private javax.swing.JMenu jMenuReportes;
    // End of variables declaration//GEN-END:variables
}
