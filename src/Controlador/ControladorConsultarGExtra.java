/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.DaoGastoExtraordinario;
import DAO.DaoUrbanizacion;
import Librerias.Validaciones;
import Vista.jConsultarGastosExtras;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author josera
 */
public class ControladorConsultarGExtra implements ActionListener {
    
    private jConsultarGastosExtras formConsulta;
    private DefaultTableModel modelo;
    private JTable tabla;
    
    public ControladorConsultarGExtra() throws SQLException {
        formConsulta = new jConsultarGastosExtras();
        formConsulta.agregarListener(this);
        formConsulta.setVisible(true);
        cargarIdUrbanizacion();
        
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        
        // Agregando las columnas de la tabla
        modelo.addColumn("Descripcion");
        modelo.addColumn("Monto");
        modelo.addColumn("Fecha");
        
        formConsulta.getjComboBoxUrb().addItemListener((ItemEvent e) -> {
            if(e.getStateChange() == ItemEvent.SELECTED) {
                try {
                    llenarTabla();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorConsultarGExtra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    private void cargarIdUrbanizacion() throws SQLException {
        ResultSet regUrb;
        DaoUrbanizacion daoUrb = new DaoUrbanizacion();
        String idurb;
        
        try {
            regUrb = daoUrb.cargarIdUrbanizacion();
            while(regUrb.next()) {
            idurb = regUrb.getString("idurbanizacion");
            formConsulta.getjComboBoxUrb().addItem(idurb);
            }
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(formConsulta, ex);
        }
    }
    
    
    public void vaciarTabla(){
        while (modelo.getRowCount() > 0) modelo.removeRow(0);
    }
    
    private void llenarTabla() throws SQLException {
        ResultSet rs;
        DaoGastoExtraordinario daoExtra = new DaoGastoExtraordinario();
        String urbSeleccionada;
        vaciarTabla();
        tabla = new JTable(modelo);
        
        try {
            urbSeleccionada =  (String)formConsulta.getjComboBoxUrb().getSelectedItem();
        
            rs = daoExtra.gastoExtraPorUrb(urbSeleccionada);
            formConsulta.getjScrollPane().setViewportView(tabla);
            while(rs.next()) {
                Object[] fila = new Object[3];
                fila[0] = rs.getString("descripcion"); 
                fila[1] = rs.getDouble("monto");
                fila[2] = rs.getString("fecha");
                modelo.addRow(fila);
            }
        
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(formConsulta, ex);
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(formConsulta.getjButtonSalir())){
            new ControladorPrincipal();
            formConsulta.dispose();
        }       
    }
    
}
