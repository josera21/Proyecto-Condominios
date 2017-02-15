/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.DaoCliente;
import Librerias.Validaciones;
import Modelo.Cliente;
import Modelo.Listas;
import Vista.jCliente;
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
 * @author joser
 */
public class ControladorCliente implements ActionListener, KeyListener {
    
    private jCliente formCliente;
    
    public ControladorCliente(){
        formCliente = new jCliente();
        formCliente.agregarListener(this);
        formCliente.setVisible(true);
        
        enabled(false);
        
        // KeyListener para validar
        
        formCliente.getjTextFieldCedula().addKeyListener(new KeyAdapter() {
           
            @Override
            public void keyPressed(KeyEvent e){
                try {
                    cedulaKeyPressed(e);
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            @Override
            public void keyTyped(KeyEvent e){
                Validaciones.ValidarSoloNumeros(e, formCliente.getjTextFieldCedula().getText());
            }
        });
        
        formCliente.getjTextFieldNombre().addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyTyped(KeyEvent e){
                Validaciones.ValidarSoloLetras(e);
            } 
        });
        
        formCliente.getjTextFieldSegNombre().addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyTyped(KeyEvent e){
                Validaciones.ValidarSoloLetras(e);
            } 
        });
        
        formCliente.getjTextFieldApellido().addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyTyped(KeyEvent e){
                Validaciones.ValidarSoloLetras(e);
            } 
        });
        
        formCliente.getjTextFieldSegApellido().addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyTyped(KeyEvent e){
                Validaciones.ValidarSoloLetras(e);
            } 
        });
        
        formCliente.getjFormattedTextFieldTelefono().addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyTyped(KeyEvent e){
                Validaciones.ValidarSoloNumeros(e, formCliente.getjFormattedTextFieldTelefono()
                .getText());
            }
        });
        
    }
    
    public void enabled(boolean status){
        formCliente.getjTextFieldCedula().setEditable(!status);
        formCliente.getjTextFieldNombre().setEditable(status);
        formCliente.getjTextFieldSegNombre().setEditable(status);
        formCliente.getjTextFieldApellido().setEditable(status);
        formCliente.getjTextFieldSegApellido().setEditable(status);
        formCliente.getjTextFieldDireccion().setEditable(status);
        formCliente.getjFormattedTextFieldTelefono().setEditable(status);
        formCliente.getjFormattedTextFieldFecha().setEditable(status);
        formCliente.getjComboBoxSexo().setEditable(status);
        formCliente.getjTextFieldEmail().setEditable(status);
    }
    
    private void cedulaKeyPressed(KeyEvent key) throws SQLException {
        
        ResultSet regCliente;
        DaoCliente daoCliente = new DaoCliente();
        String cadena;
        
        cadena = formCliente.getjTextFieldCedula().getText().trim();
        
        if(key.getKeyChar()==10 && cadena.length()<=8 && cadena.length()>=7){
            regCliente = daoCliente.buscarCliente(cadena);
            if(regCliente.next()){
                if(regCliente.getString("estatus").equalsIgnoreCase("A")){
                    formCliente.getjTextFieldCedula().setText(regCliente.getString("cedula"));
                    formCliente.getjTextFieldNombre().setText(regCliente.getString("nombre"));
                    formCliente.getjTextFieldSegNombre().setText(regCliente.getString("segNombre"));
                    formCliente.getjTextFieldApellido().setText(regCliente.getString("apellido"));
                    formCliente.getjTextFieldSegApellido().setText(regCliente.getString("segApellido"));
                    formCliente.getjTextFieldDireccion().setText(regCliente.getString("direccion"));
                    formCliente.getjFormattedTextFieldTelefono().setText(regCliente.getString("telefono"));
                    formCliente.getjFormattedTextFieldFecha().setText(regCliente.getString("fechanac"));
                    if(regCliente.getString("sexo").equals("M"))
                        formCliente.getjComboBoxSexo().setSelectedIndex(2);
                    else
                        formCliente.getjComboBoxSexo().setSelectedIndex(1);
                    formCliente.getjTextFieldEmail().setText(regCliente.getString("email"));
                
                    formCliente.getjButtonGuardar().setText("Modificar");
                }
                else {
                    Validaciones.Aviso("El cliente que intentas buscar se encuentra "
                            + "Eliminado", "Atencion");
                    formCliente.getjTextFieldCedula().requestFocusInWindow();
                }
            }
            else {
                Validaciones.Aviso("Cliente no encontrado", "Atencion");
                formCliente.getjTextFieldNombre().requestFocusInWindow();
                enabled(true);
                return;
            }
            
            enabled(false);
        }
        
    }
    
    private void guardar() throws SQLException {
        Cliente cliente;
        DaoCliente daoCliente = new DaoCliente();
        ResultSet regCliente;
        String cadena, sex;
        
        if(formCliente.getjButtonGuardar().getText().equalsIgnoreCase("Modificar")){
            enabled(true);
            formCliente.getjButtonGuardar().setText("Guardar");
            return;
        }
        
        if(Validaciones.ValidarCamposVacios(formCliente.getjTextFieldCedula(), 
                                   formCliente.getjTextFieldNombre(),
                                   formCliente.getjTextFieldSegNombre(),
                                   formCliente.getjTextFieldApellido(),
                                   formCliente.getjTextFieldSegApellido(),
                                   formCliente.getjTextFieldDireccion(),
                                   formCliente.getjFormattedTextFieldTelefono(),
                                   formCliente.getjFormattedTextFieldFecha(),
                                   formCliente.getjTextFieldEmail())) {
        //Aca empieza el if
        Validaciones.Aviso("Hay campos vacios", "Gestion de Cliente");
        return;
    }
       
        
        cadena = formCliente.getjFormattedTextFieldFecha().getText().trim();
        
        if(!Validaciones.isDate(cadena)){
            Validaciones.Aviso("Error en la Fecha de nacimiento", "Gestion de Cliente");
            formCliente.getjFormattedTextFieldFecha().requestFocusInWindow();
            return;
        }
        cadena = formCliente.getjComboBoxSexo().getSelectedItem().toString();
        if(cadena.equalsIgnoreCase("selecciona")){
            Validaciones.Aviso("No se ha seleccionado un Sexo", "Gestion de Cliente");
            formCliente.getjComboBoxSexo().requestFocusInWindow();
            return;
        }
        
        cadena = formCliente.getjTextFieldCedula().getText().trim();
        
        sex = (String)formCliente.getjComboBoxSexo().getSelectedItem();
        cliente = new Cliente(cadena,formCliente.getjTextFieldNombre().getText(),
                formCliente.getjTextFieldSegNombre().getText(),
                formCliente.getjTextFieldApellido().getText(),
                formCliente.getjTextFieldSegApellido().getText(),
                formCliente.getjTextFieldDireccion().getText(),
                formCliente.getjFormattedTextFieldTelefono().getText(),
                formCliente.getjFormattedTextFieldFecha().getText(),sex,
                formCliente.getjTextFieldEmail().getText()
                
                );
        
        regCliente = daoCliente.buscarCliente(cadena);
        if(!regCliente.next()){
            daoCliente.insertar(cliente);
            Validaciones.Aviso("Registro del Cliente exitoso!", "Gestion de Registro");
            cancelar();
        }
        else {
            daoCliente.modificarCliente(cliente);
            Validaciones.Aviso("Cliente modificado exitosamente!", "Gestion de Registro");
        }
     
    }
    
    private void eliminar() throws SQLException {
        String cadena, sex;
        Cliente cliente;
        DaoCliente daoCliente = new DaoCliente();
        ResultSet regCliente;
        
        cadena = formCliente.getjTextFieldCedula().getText().trim();
       
        sex = (String)formCliente.getjComboBoxSexo().getSelectedItem();
        cliente = new Cliente(cadena,formCliente.getjTextFieldNombre().getText(),
                formCliente.getjTextFieldSegNombre().getText(),
                formCliente.getjTextFieldApellido().getText(),
                formCliente.getjTextFieldSegApellido().getText(),
                formCliente.getjTextFieldDireccion().getText(),
                formCliente.getjFormattedTextFieldTelefono().getText(),
                formCliente.getjFormattedTextFieldFecha().getText(),sex,
                formCliente.getjTextFieldEmail().getText()
                );
        
        regCliente = daoCliente.buscarCliente(cadena);
        if(regCliente.next()) {
            int resp = Validaciones.DosOpciones("Aceptar", "Cancelar","Confirmar Eliminacion");
            if(resp == 0) {
                daoCliente.eliminarCliente(cliente);
                Validaciones.Aviso("Cliente eliminado exitosamente!", "Gestion de Cliente");
            }    
        }
    }
    
    private void cancelar(){
        enabled(false);
        formCliente.getjTextFieldCedula().requestFocusInWindow();
        formCliente.getjTextFieldCedula().setText("");  
        formCliente.getjTextFieldNombre().setText("");
        formCliente.getjTextFieldSegNombre().setText("");
        formCliente.getjTextFieldApellido().setText("");
        formCliente.getjTextFieldSegApellido().setText("");
        formCliente.getjTextFieldDireccion().setText(""); 
        formCliente.getjFormattedTextFieldTelefono().setText(""); 
        formCliente.getjFormattedTextFieldFecha().setText("");
        formCliente.getjComboBoxSexo().setSelectedIndex(0); 
        formCliente.getjTextFieldEmail().setText("");
        formCliente.getjButtonGuardar().setText("Guardar");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(formCliente.getjButtonGuardar())){
            try {
                guardar();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(e.getSource().equals(formCliente.getjButtonCancelar())){
            cancelar();
        }
        
        if(e.getSource().equals(formCliente.getjButtonEliminar())){
            try {
                eliminar();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(e.getSource().equals(formCliente.getjButtonSalir())){
            new ControladorPrincipal();
            formCliente.dispose();
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
