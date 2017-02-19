/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.DaoGastoExtraordinario;
import DAO.DaoUrbanizacion;
import Librerias.Validaciones;
import Modelo.GastoExtraordinario;
import Vista.jGastoExtraordinario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author josera
 */
public class ControladorGastoExtraordinario implements ActionListener, KeyListener {
    
    private jGastoExtraordinario formGastoExtra;
    
    public ControladorGastoExtraordinario() throws SQLException {
        formGastoExtra = new jGastoExtraordinario();
        formGastoExtra.agregarListener(this);
        formGastoExtra.setVisible(true);
        
        enabled(false);
        cargarUrbanizacion();
        
        formGastoExtra.getjTextFieldCodigo().addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    codigoKeyPressed(e);
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorGastoExtraordinario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        formGastoExtra.getjTextFieldDescripcion().addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyTyped(KeyEvent e) {
                Validaciones.ValidarSoloLetras(e);
            }
        });
        
        formGastoExtra.getjTextFieldMonto().addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyTyped(KeyEvent e) {
                Validaciones.ValidarSoloNumeros(e, formGastoExtra.getjTextFieldMonto().getText());
            }
        });
        
        
    }
    
    
    private void cargarUrbanizacion() throws SQLException {
        ResultSet regUrb;
        DaoUrbanizacion daoUrb = new DaoUrbanizacion();
        String idurb;
        
        try {
            regUrb = daoUrb.cargarIdUrbanizacion();
            while(regUrb.next()) {
            idurb = regUrb.getString("idurbanizacion");
            formGastoExtra.getjComboBoxUrb().addItem(idurb);
            }
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(formGastoExtra, ex);
        }
    } 
        
        
        
    
    
    public void codigoKeyPressed(KeyEvent key) throws SQLException {
        ResultSet regGastoEx;
        DaoGastoExtraordinario daoExtra = new DaoGastoExtraordinario();
        String cadena;
        
        cadena = formGastoExtra.getjTextFieldCodigo().getText().trim();
        
        if(key.getKeyChar()==10 && cadena.length()==11) {
           String urbSeleccionada =  (String)formGastoExtra.getjComboBoxUrb().getSelectedItem();
           regGastoEx = daoExtra.buscarGastoExtra(cadena, urbSeleccionada);
           if(regGastoEx.next()) {
               if(regGastoEx.getString("estatus").equals("A")){
                   formGastoExtra.getjTextFieldCodigo().setText(regGastoEx.getString("idgastoextra"));
                   String item = regGastoEx.getString("idurbanizacion");
                   formGastoExtra.getjComboBoxUrb().setSelectedItem(item);
                   formGastoExtra.getjTextFieldDescripcion().setText(regGastoEx.getString("descripcion"));
                   formGastoExtra.getjTextFieldFecha().setText(regGastoEx.getString("fecha"));
                   formGastoExtra.getjTextFieldMonto().setText(regGastoEx.getString("monto"));
                   
                   formGastoExtra.getjButtonGuardar().setText("Modificar");
               }
               else {
                   Validaciones.Aviso("El Gasto extra que intentas buscar se encuentra "
                            + "Eliminado", "Atencion");
                   formGastoExtra.getjTextFieldCodigo().requestFocusInWindow();
               }    
           }
           else {
               Validaciones.Aviso("Gasto extra no encontrado", "Atencion");
               formGastoExtra.getjTextFieldFecha().requestFocusInWindow();
               enabled(true);
               return;
           }
           enabled(false);
        }
        
    }
    
    private void guardar() throws SQLException {
        GastoExtraordinario gastoEx;
        DaoGastoExtraordinario daoExtra = new DaoGastoExtraordinario();
        ResultSet regGastoEx;
        String cadena, urbSeleccionada;
        double monto;
        
        if(formGastoExtra.getjButtonGuardar().getText().equalsIgnoreCase("Modificar")) {
            enabled(true);
            formGastoExtra.getjButtonGuardar().setText("Guardar");
            return;
        }
        
        if(Validaciones.ValidarCamposVacios(formGastoExtra.getjTextFieldCodigo(),
                                            formGastoExtra.getjTextFieldDescripcion(),
                                            formGastoExtra.getjTextFieldFecha(),
                                            formGastoExtra.getjTextFieldMonto())){
            Validaciones.Aviso("Hay campos vacios", "Gestion de Cliente");
            return;
        }
        
        cadena = formGastoExtra.getjTextFieldFecha().getText().trim();
        
        if(!Validaciones.isDate(cadena)){
            Validaciones.Aviso("Error en la Fecha", "Gestion de Gastos");
            formGastoExtra.getjTextFieldFecha().requestFocusInWindow();
            return;
        }
        
        cadena = formGastoExtra.getjComboBoxUrb().getSelectedItem().toString();
        if(cadena.equalsIgnoreCase("Urbanizaciones")){
            Validaciones.Aviso("No se ha seleccionado una Urbanizacion", "Gestion de Gastos");
            formGastoExtra.getjComboBoxUrb().requestFocusInWindow();
            return;
        }
        
        cadena = formGastoExtra.getjTextFieldCodigo().getText().trim();
        urbSeleccionada = (String)formGastoExtra.getjComboBoxUrb().getSelectedItem();
        monto = Double.parseDouble(formGastoExtra.getjTextFieldMonto().getText());
        
        gastoEx = new GastoExtraordinario(cadena, formGastoExtra.getjTextFieldDescripcion().getText(),
                                          formGastoExtra.getjTextFieldFecha().getText(),monto);
        
        regGastoEx = daoExtra.buscarGastoExtra(cadena, urbSeleccionada);
        if(!regGastoEx.next()) {
            daoExtra.insertarGasto(gastoEx, urbSeleccionada);
            Validaciones.Aviso("Registro del Gasto Extra exitoso!", "Gestion de Gastos");
            cancelar();
        }
        else {
            daoExtra.modificarGastoExtra(gastoEx, urbSeleccionada);
            Validaciones.Aviso("Gasto Extra modificado exitosamente!", "Gestion de Gastos");
        }
        
    }
    
    private void eliminar() throws SQLException {
        String cadena, urbSeleccionada;
        double monto;
        GastoExtraordinario gastoEx;
        DaoGastoExtraordinario daoExtra = new DaoGastoExtraordinario();
        ResultSet regGastoEx;
        
        cadena = formGastoExtra.getjTextFieldCodigo().getText().trim();
        
        urbSeleccionada = (String)formGastoExtra.getjComboBoxUrb().getSelectedItem();
        monto = Double.parseDouble(formGastoExtra.getjTextFieldMonto().getText());
        
        gastoEx = new GastoExtraordinario(cadena, formGastoExtra.getjTextFieldDescripcion().getText(),
                                          formGastoExtra.getjTextFieldFecha().getText(),monto);
        
        regGastoEx = daoExtra.buscarGastoExtra(cadena, urbSeleccionada);
        if(regGastoEx.next()) {
            int resp = Validaciones.DosOpciones("Aceptar", "Cancelar","Confirmar Eliminacion");
            if(resp == 0) {
                daoExtra.eliminarGastoExtra(gastoEx, urbSeleccionada);
                Validaciones.Aviso("Gasto extra eliminado exitosamente!", "Gestion de Gastos");
                cancelar();
            }    
        }
    }
    
    private void cancelar() {
        enabled(false);
        formGastoExtra.getjTextFieldCodigo().setText("");
        formGastoExtra.getjTextFieldDescripcion().setText("");
        formGastoExtra.getjTextFieldFecha().setText("");
        formGastoExtra.getjTextFieldMonto().setText("");
        formGastoExtra.getjComboBoxUrb().setSelectedIndex(0);
        formGastoExtra.getjButtonGuardar().setText("Guardar");
    }
    
    public void enabled(boolean status) {
        formGastoExtra.getjTextFieldCodigo().setEditable(!status);
        formGastoExtra.getjTextFieldDescripcion().setEditable(status);
        formGastoExtra.getjTextFieldFecha().setEditable(status);
        formGastoExtra.getjTextFieldMonto().setEditable(status);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(formGastoExtra.getjButtonGuardar())){
            try {
                guardar();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(e.getSource().equals(formGastoExtra.getjButtonCancelar())){
            cancelar();
        }
        
        if(e.getSource().equals(formGastoExtra.getjButtonEliminar())){
            try {
                eliminar();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(e.getSource().equals(formGastoExtra.getjButtonSalir())){
            new ControladorPrincipal();
            formGastoExtra.dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
