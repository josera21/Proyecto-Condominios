/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Librerias.Validaciones;
import Modelo.Cliente;
import Modelo.Listas;
import Vista.jCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
                cedulaKeyPressed(e);
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
        formCliente.getjTextFieldApellido().setEditable(status);
        formCliente.getjTextFieldDireccion().setEditable(status);
        formCliente.getjFormattedTextFieldTelefono().setEditable(status);
        formCliente.getjFormattedTextFieldFecha().setEditable(status);
        formCliente.getjComboBoxSexo().setEditable(status);
    }
    
    private void cedulaKeyPressed(KeyEvent key){
        
        Cliente cliente;
        String cadena;
        int posCliente;
        
        cadena = formCliente.getjTextFieldCedula().getText().trim();
        
        if(key.getKeyChar()==10 && cadena.length()<=8 && cadena.length()>=7){
           
            posCliente = listaCliente.existeCliente(cadena);
            if(posCliente == -1){
                enabled(true);
                formCliente.getjTextFieldNombre().requestFocusInWindow();
                return;
            }
            
            cliente = listaCliente.getListaCliente().get(posCliente);
            
            formCliente.getjTextFieldNombre().setText(cliente.getNombre());
            formCliente.getjTextFieldApellido().setText(cliente.getApellido());
            formCliente.getjTextFieldDireccion().setText(cliente.getDireccion());
            formCliente.getjFormattedTextFieldTelefono().setText(cliente.getTelefono());
            formCliente.getjComboBoxSexo().setSelectedItem(cliente.getSexo());
            
            enabled(false);
        }
        
    }
    
    private void guardar(){
        Cliente cliente;
        String cadena, sex;
        int existe;
        
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
        existe = listaCliente.existeCliente(cadena);
        
        sex = (String)formCliente.getjComboBoxSexo().getSelectedItem();
        if(existe == -1){
            cliente = new Cliente(cadena,formCliente.getjTextFieldNombre().getText(),
               formCliente.getjTextFieldApellido().getText(),
                formCliente.getjTextFieldDireccion().getText(),
                formCliente.getjFormattedTextFieldTelefono().getText(),
                formCliente.getjFormattedTextFieldFecha().getText(),
                    sex
                );
            listaCliente.getListaCliente().add(cliente);
            Validaciones.Aviso("Registro del Cliente exitoso!", "");
            cancelar();
        }
        else {
            Validaciones.Aviso("No se puede guardar, Cliente ya Existe.", "");
            return;
        }
    }
    
    private void cancelar(){
        enabled(false);
        formCliente.getjTextFieldCedula().requestFocusInWindow();
        formCliente.getjTextFieldCedula().setText("");  
        formCliente.getjTextFieldNombre().setText("");
        formCliente.getjTextFieldApellido().setText("");
        formCliente.getjTextFieldDireccion().setText(""); 
        formCliente.getjFormattedTextFieldTelefono().setText(""); 
        formCliente.getjFormattedTextFieldFecha().setText("");
        formCliente.getjComboBoxSexo().setSelectedIndex(0); 
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(formCliente.getjButtonGuardar())){
            guardar();
        }
        
        if(e.getSource().equals(formCliente.getjButtonCancelar())){
            cancelar();
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
