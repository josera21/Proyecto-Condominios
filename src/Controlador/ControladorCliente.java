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
    private Listas listaCliente;
    
    public ControladorCliente(Listas listCliente){
        listaCliente = listCliente;
        formCliente = new jCliente();
        formCliente.agregarListener(this);
        formCliente.setVisible(true);
        listCliente.cargarClientes();
        
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
        
        formCliente.getjTextFieldApellido().addKeyListener(new KeyAdapter() {
            
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
        
        
        cadena = formCliente.getjTextFieldCedula().getText().trim();
        
        if(cadena.length() == 0){
            Validaciones.Aviso("Campo cedula esta Vacio", "");
            formCliente.getjTextFieldCedula().requestFocusInWindow();
            return;
        }
        
        cadena = formCliente.getjTextFieldNombre().getText().trim();
        
        if(cadena.length() == 0){
            Validaciones.Aviso("Campo nombre esta Vacio", "");
            formCliente.getjTextFieldNombre().requestFocusInWindow();
            return;
        }
        
        cadena = formCliente.getjTextFieldApellido().getText().trim();
        
        if(cadena.length() == 0){
            Validaciones.Aviso("Campo apellido esta Vacio", "");
            formCliente.getjTextFieldApellido().requestFocusInWindow();
            return;
        }
        
        cadena = formCliente.getjTextFieldDireccion().getText().trim();
        
        if(cadena.length() == 0){
            Validaciones.Aviso("Campo direccion esta Vacio", "");
            formCliente.getjTextFieldDireccion().requestFocusInWindow();
            return;
        }
        
        cadena = formCliente.getjFormattedTextFieldTelefono().getText().trim();
        
        if(cadena.length() == 0){
            Validaciones.Aviso("Campo Telefono esta Vacio", "");
            formCliente.getjFormattedTextFieldTelefono().requestFocusInWindow();
            return;
        }
        
        cadena = formCliente.getjFormattedTextFieldFecha().getText().trim();
        
        if(cadena.length() == 0){
            Validaciones.Aviso("Campo Fecha esta Vacio", "");
            formCliente.getjFormattedTextFieldFecha().requestFocusInWindow();
            return;
        }
        
        cadena = formCliente.getjFormattedTextFieldFecha().getText().trim();
        
        if(!Validaciones.isDate(cadena)){
            Validaciones.Aviso("Error en la Fecha de nacimiento", "");
            formCliente.getjFormattedTextFieldFecha().requestFocusInWindow();
            return;
        }
        
        cadena = (String)formCliente.getjComboBoxSexo().getSelectedItem();
        
        if("seleccione".equalsIgnoreCase(cadena)){
            Validaciones.Aviso("No se ha seleccionado un Sexo", "");
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
        
        if(e.getSource().equals(formCliente.getjButtonSalir())){
            new ControladorPrincipal();
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
