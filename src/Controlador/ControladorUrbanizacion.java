/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.DaoUrbanizacion;
import Librerias.Validaciones;
import Modelo.Urbanizacion;
import Vista.jUrbanizacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josera
 */
public class ControladorUrbanizacion implements KeyListener, ActionListener {
    
    private jUrbanizacion formUrb;
    
    public ControladorUrbanizacion() {
        formUrb = new jUrbanizacion();
        formUrb.agregarListener(this);
        formUrb.setVisible(true);
        
        enabled(false);
        
        formUrb.getjTextFieldCodigo().addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    codigoKeyPressed(e);
                } catch(SQLException ex){
                    Logger.getLogger(ControladorUrbanizacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        formUrb.getjTextFieldNombre().addKeyListener(new KeyAdapter(){
            
            @Override
            public void keyTyped(KeyEvent e) {
                Validaciones.ValidarSoloLetras(e);
            }
        });
        
        formUrb.getjTextFieldDireccion().addKeyListener(new KeyAdapter(){
            
            @Override
            public void keyTyped(KeyEvent e) {
                Validaciones.ValidarSoloLetras(e);
            }
        });
        
        
    }
    
    
    public void enabled(boolean status) {
        formUrb.getjTextFieldCodigo().setEditable(!status);
        formUrb.getjTextFieldNombre().setEditable(status);
        formUrb.getjTextFieldDireccion().setEditable(status);
    }
    
    private void codigoKeyPressed(KeyEvent key) throws SQLException {
        ResultSet regUrb;
        DaoUrbanizacion daoUrb = new DaoUrbanizacion();
        String cadena;
        
        cadena = formUrb.getjTextFieldCodigo().getText().trim();
        
        if(key.getKeyChar()==10 && cadena.length() == 6){
            regUrb = daoUrb.buscarUrbanizacion(cadena);
            if(regUrb.next()){
                if(regUrb.getString("estatus").equalsIgnoreCase("A")){
                    formUrb.getjTextFieldCodigo().setText(regUrb.getString("idurbanizacion"));
                    formUrb.getjTextFieldNombre().setText(regUrb.getString("nombre"));
                    formUrb.getjTextFieldDireccion().setText(regUrb.getString("direccion"));
                    
                    formUrb.getjButtonGuardar().setText("Modificar");
                }
                else {
                    Validaciones.Aviso("La urbanizacion que intentas buscar se "
                            + "encuentra eliminada", "Atencion");
                    formUrb.getjTextFieldCodigo().requestFocusInWindow();
                }
            }
            else {
                Validaciones.Aviso("Urbanizacion no encontrada", "Atencion");
                formUrb.getjTextFieldNombre().requestFocusInWindow();
                enabled(true);
                return;
            }
            enabled(false);
        }
        
    }
    
    private void guardar() throws SQLException {
        Urbanizacion urb;
        DaoUrbanizacion daoUrb = new DaoUrbanizacion();
        ResultSet regUrb;
        String cadena;
        
        if(formUrb.getjButtonGuardar().getText().equalsIgnoreCase("Modificar")){
            enabled(true);
            formUrb.getjButtonGuardar().setText("Guardar");
            return;
        }
        
        if(Validaciones.ValidarCamposVacios(formUrb.getjTextFieldCodigo(),
                                            formUrb.getjTextFieldNombre(),
                                            formUrb.getjTextFieldDireccion())){
        Validaciones.Aviso("Hay campos vacios", "Gestion de Urbanizacion");
        return;
        }
        
        cadena = formUrb.getjTextFieldCodigo().getText().trim();
        urb = new Urbanizacion(formUrb.getjTextFieldCodigo().getText(),
                               formUrb.getjTextFieldNombre().getText(),
                               formUrb.getjTextFieldDireccion().getText());
        
        regUrb =  daoUrb.buscarUrbanizacion(cadena);
        if(!regUrb.next()){
            daoUrb.insertar(urb);
            Validaciones.Aviso("Registro de la Urbanizacion exitosa!", "Gestion de Registro");
        }
        else {
            daoUrb.modificarUrbanizacion(urb);
            Validaciones.Aviso("Modificacion de la Urbanizacion exitosa!", "Gestion de Registro");
        }
    }
    
    private void eliminar() throws SQLException {
        Urbanizacion urb;
        DaoUrbanizacion daoUrb = new DaoUrbanizacion();
        ResultSet regUrb;
        String cadena;
        
        cadena = formUrb.getjTextFieldCodigo().getText().trim();
        urb = new Urbanizacion(formUrb.getjTextFieldCodigo().getText(),
                               formUrb.getjTextFieldNombre().getText(),
                               formUrb.getjTextFieldDireccion().getText());
        
        regUrb =  daoUrb.buscarUrbanizacion(cadena);
        if(regUrb.next()){
            int resp = Validaciones.DosOpciones("Aceptar", "Cancelar","Confirmar Eliminacion");
            if(resp == 0) {
                daoUrb.eliminarUrbanizacion(urb);
                Validaciones.Aviso("Urbanizacion eliminada exitosamente!", "Gestion de Registro");
            }   
            
        }
    }
    
    private void cancelar(){
        enabled(false);
        formUrb.getjTextFieldCodigo().requestFocusInWindow();
        formUrb.getjTextFieldCodigo().setText("");
        formUrb.getjTextFieldNombre().setText("");
        formUrb.getjTextFieldDireccion().setText("");
        formUrb.getjButtonGuardar().setText("Guardar");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(formUrb.getjButtonGuardar())){
            try {
                guardar();
            }catch(SQLException ex){
                Logger.getLogger(ControladorUrbanizacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(e.getSource().equals(formUrb.getjButtonEliminar())){
            try {
                eliminar();
            }catch(SQLException ex){
                Logger.getLogger(ControladorUrbanizacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(e.getSource().equals(formUrb.getjButtonCancelar())){
            cancelar();
        }
        
        if(e.getSource().equals(formUrb.getjButtonSalir())){
            new ControladorPrincipal();
            formUrb.dispose();
        }
        
    }
    
}
