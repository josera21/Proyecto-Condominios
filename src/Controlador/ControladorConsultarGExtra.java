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

/**
 *
 * @author josera
 */
public class ControladorConsultarGExtra implements ActionListener {
    
    private jConsultarGastosExtras formConsulta;
    
    public ControladorConsultarGExtra() throws SQLException {
        formConsulta = new jConsultarGastosExtras();
        formConsulta.agregarListener(this);
        formConsulta.setVisible(true);
        cargarIdUrbanizacion();
        
        formConsulta.getjComboBoxUrb().addItemListener((ItemEvent e) -> {
            if(e.getStateChange() == ItemEvent.SELECTED) {
                try {
                    asignarValoresTabla();
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
    
    
    private void asignarValoresTabla() throws SQLException {
        ResultSet regExtra;
        DaoGastoExtraordinario daoExtra = new DaoGastoExtraordinario();
        int fila = 0;
        
        try {
            String urbSeleccionada;
            urbSeleccionada =  (String)formConsulta.getjComboBoxUrb().getSelectedItem();
            regExtra = daoExtra.gastoExtraPorUrb(urbSeleccionada);
            
            while(regExtra.next()) {
                formConsulta.getjTableGastoExtra().setValueAt(regExtra.getString("descripcion"), fila, 0);
                formConsulta.getjTableGastoExtra().setValueAt(regExtra.getString("monto"), fila, 1);
                formConsulta.getjTableGastoExtra().setValueAt(regExtra.getString("fecha"), fila, 2);
                fila++;
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
