/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Librerias.Validaciones;
import Vista.jCasa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josera
 */
public class ControladorCasa implements ActionListener, KeyListener {
    
    private jCasa formCasa;
    
    public ControladorCasa() {
        formCasa = new jCasa();
        formCasa.agregarListener(this);
        formCasa.setVisible(true);
        
        enabled(false);
        
        formCasa.getjTextFieldCodigo().addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyPressed(KeyEvent e){
                try {
                    codigoKeyPressed(e);
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorCasa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        formCasa.getjTextFieldHabitaciones().addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyTyped(KeyEvent e){
                Validaciones.ValidarSoloNumeros(e, formCasa.getjTextFieldHabitaciones().getText());
            }
            
        });
        
        formCasa.getjTextFieldBannios().addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyTyped(KeyEvent e){
                Validaciones.ValidarSoloNumeros(e, formCasa.getjTextFieldBannios().getText());
            }
            
        });
        
        formCasa.getjTextFieldTelefono().addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyTyped(KeyEvent e){
                Validaciones.ValidarSoloNumeros(e, formCasa.getjTextFieldTelefono().getText());
            }
            
        });
    }
    
    public void enabled(boolean status) {
        formCasa.getjTextFieldCodigo().setEditable(!status);
        formCasa.getjTextFieldCalle().setEditable(status);
        formCasa.getjTextFieldHabitaciones().setEditable(status);
        formCasa.getjTextFieldBannios().setEditable(status);
        formCasa.getjTextFieldTelefono().setEditable(status);
        formCasa.getjComboBoxTipo().setEditable(status);
    }
    
    private void codigoKeyPressed(KeyEvent key) throws SQLException {
        
        String cadena;
        
        cadena = formCasa.getjTextFieldCodigo().getText().trim();
        
        if(key.getKeyChar()==10 && cadena.length()==8) {
            
        }
        
    }
    
    private void guardar() throws SQLException {
        
    }
    
    private void eliminar() throws SQLException {
        
    }
    
    private void cancelar() {
        formCasa.getjTextFieldCodigo().setText("");
        formCasa.getjTextFieldCalle().setText("");
        formCasa.getjTextFieldHabitaciones().setText("");
        formCasa.getjTextFieldBannios().setText("");
        formCasa.getjTextFieldTelefono().setText("");
        formCasa.getjComboBoxTipo().setSelectedIndex(0);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(formCasa.getjButtonGuardar())){
            try {
                guardar();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorCasa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(e.getSource().equals(formCasa.getjButtonEliminar())){
            try {
                eliminar();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorCasa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(e.getSource().equals(formCasa.getjButtonCancelar())){
            cancelar();
        }
        
        if(e.getSource().equals(formCasa.getjButtonSalir())){
            new ControladorPrincipal();
            formCasa.dispose();
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


