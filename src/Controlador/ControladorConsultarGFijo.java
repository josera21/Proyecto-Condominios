/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.DaoGastoFijo;
import DAO.DaoUrbanizacion;
import Vista.jConsultarGastosFijos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
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
public class ControladorConsultarGFijo implements ActionListener {
    
    private jConsultarGastosFijos formConsulta;
    private DefaultTableModel modelo;
    private JTable tabla;
    
    public ControladorConsultarGFijo() throws SQLException {
        formConsulta = new jConsultarGastosFijos();
        formConsulta.agregarListener(this);
        formConsulta.setVisible(true);
        formConsulta.setResizable(false);
        formConsulta.setLocationRelativeTo(null);
        cargarIdUrbanizacion();
        
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        
        // Agregando las columnas de la tabla
        modelo.addColumn("Luz");
        modelo.addColumn("Agua");
        modelo.addColumn("Vigilancia");
        modelo.addColumn("Conserje");
        modelo.addColumn("Jardineria");
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
            idurb = regUrb.getString("ID_Urbanizacion");
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
        DaoGastoFijo daoFijo = new DaoGastoFijo();
        String urbSeleccionada;
        vaciarTabla();
        tabla = new JTable(modelo);
 
        try {
            urbSeleccionada =  (String)formConsulta.getjComboBoxUrb().getSelectedItem();
        
            rs = daoFijo.gastoFijoPorUrb(urbSeleccionada);
            formConsulta.getjScrollPane().setViewportView(tabla);
            while(rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getDouble("gastoluz"); 
                fila[1] = rs.getDouble("gastoagua");
                fila[2] = rs.getDouble("gastovigilancia");
                fila[3] = rs.getDouble("gastoconserje");
                fila[4] = rs.getDouble("gastojardineria");
                fila[5] = rs.getString("fecha");
                modelo.addRow(fila);
            }
        
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(formConsulta, ex);
        }
        
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(formConsulta.getjButtonSalir())) {
            new ControladorMenuGastos();
            formConsulta.dispose();
        }
    }
    
}
