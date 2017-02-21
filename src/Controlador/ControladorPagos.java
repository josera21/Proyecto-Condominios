/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.DaoCliente;
import DAO.DaoCuota;
import DAO.DaoCuotaVivienda;
import DAO.DaoFormaPago;
import DAO.DaoUrbanizacion;
import DAO.DaoVivienda;
import Librerias.Validaciones;
import Modelo.Cuota;
import Modelo.CuotaVivienda;
import Vista.jPagos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author joser
 */
public class ControladorPagos implements ActionListener, KeyListener {
    
    private jPagos formPago;
    
    public ControladorPagos() throws SQLException {
        formPago = new jPagos();
        formPago.agregarListener(this);
        formPago.setVisible(true);
        cargarFormaPago();
        
        formPago.getjTextFieldCedula().addKeyListener(new KeyAdapter() {
           
            @Override
            public void keyPressed(KeyEvent e){
               
                try {
                    cedulaKeyPressed(e);
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorPagos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            @Override
            public void keyTyped(KeyEvent e){
                Validaciones.ValidarSoloNumeros(e, formPago.getjTextFieldCedula().getText());
            }
            
        });
        
        formPago.getjComboBoxUrb().addItemListener((ItemEvent e) -> {
            if(e.getStateChange() == ItemEvent.SELECTED) {
                try {
                    cargarViviendasDelPropietario(formPago.getjTextFieldCedula().getText(),
                                                  formPago.getjComboBoxUrb().getSelectedItem().toString());
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorConsultarGExtra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
    }
            
    
    private void cedulaKeyPressed(KeyEvent key) throws SQLException {
        ResultSet regCliente;
        DaoCliente daoCliente = new DaoCliente();
        String cadena;
        
        cadena = formPago.getjTextFieldCedula().getText().trim();
        
        if(key.getKeyChar()==10 && cadena.length()<=8 && cadena.length()>=7){
            regCliente = daoCliente.buscarCliente(cadena);
            if(regCliente.next()){
                if(regCliente.getString("status").equalsIgnoreCase("A")){
                    JOptionPane.showMessageDialog(null, "Por favor Seleccione una Urbanizacion", 
                                                  "Propietario Encontrador", 1);
                    formPago.getjTextFieldCedula().setText(regCliente.getString("cedula").trim());
                    cargarUrbanizacionesDelCliente(cadena);
                    formPago.getjTextFieldCedula().setEditable(false);
                }
                else {
                    Validaciones.Aviso("El cliente que intentas buscar se encuentra "
                            + "Eliminado", "Atencion");
                    formPago.getjTextFieldCedula().requestFocusInWindow();
                }
            }
            else {
                Validaciones.Aviso("Cliente no encontrado", "Atencion");
                formPago.getjTextFieldCedula().setText("");
                formPago.getjTextFieldCedula().requestFocusInWindow();
                cancelar();
            }
        }
        
    }
    
    private void cargarViviendasDelPropietario(String ced, String idUrb) throws SQLException {
        ResultSet regVi;
        DaoVivienda daoVi = new DaoVivienda();
        String idVi;
        formPago.getjComboBoxVivienda().removeAllItems();
        formPago.getjComboBoxVivienda().addItem("Viviendas");
        try {
            regVi = daoVi.Consultar_Viviendas_Propietario(ced, idUrb);
            while(regVi.next()) {
            idVi = regVi.getString("ID_Vivienda");
            formPago.getjComboBoxVivienda().addItem(idVi);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(formPago, ex);
        }
    }
    
    private void cargarUrbanizacionesDelCliente(String ced) throws SQLException {
        ResultSet regVi;
        DaoVivienda daoVi = new DaoVivienda();
        String idurb;
        
        try {
            regVi = daoVi.Consultar_Urbanizaciones_Propietario(ced);
            while(regVi.next()) {
            idurb = regVi.getString("ID_Urbanizacion");
            formPago.getjComboBoxUrb().addItem(idurb);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(formPago, ex);
        }
        
    } 
    
    private void cargarFormaPago() throws SQLException {
        ResultSet rs;
        DaoFormaPago daoF = new DaoFormaPago();
        String forma;
        
        try {
            rs = daoF.cargarFormaPago();
            while(rs.next()) {
            forma = rs.getString("nombreforma");
            formPago.getjComboBoxForma().addItem(forma);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(formPago, ex);
        }
    }
    
    private void cancelar(){
        formPago.getjTextFieldCedula().setEditable(true);
        formPago.getjTextFieldCedula().setText("");
        formPago.getjTextFieldFecha().setText("");
        formPago.getjComboBoxForma().setSelectedIndex(0);

        formPago.getjComboBoxUrb().removeAllItems();
        formPago.getjComboBoxVivienda().removeAllItems();
        
        formPago.getjComboBoxUrb().addItem("Urbanizaciones");
        formPago.getjComboBoxVivienda().addItem("Viviendas");
    }
    
    private void generar() {
        CuotaVivienda cv;
        Cuota cuota = new Cuota();
        DaoCuotaVivienda daoCV = new DaoCuotaVivienda();
        DaoCuota daoC = new DaoCuota();
        String cadena, propieSelecc;
        String fechaTope;
        double monto;
        
        if(Validaciones.ValidarCamposVacios(formPago.getjTextFieldFecha())){
            Validaciones.Aviso("Hay campos vacios", "Gestion de Pagos");
            return;
        }
        
        cadena = formPago.getjTextFieldFecha().getText().trim();
    
        if(!Validaciones.isDate(cadena)){
            Validaciones.Aviso("Error en la Fecha", "Gestion de Pagos");
            formPago.getjTextFieldFecha().requestFocusInWindow();
            return;
        }
        
        cadena = formPago.getjComboBoxUrb().getSelectedItem().toString();
        if(cadena.equalsIgnoreCase("Urbanizaciones")){
            Validaciones.Aviso("No se ha seleccionado una Urbanizacion", "Gestion de Pagos");
            formPago.getjComboBoxUrb().requestFocusInWindow();
            return;
        }
        
        cadena = formPago.getjComboBoxVivienda().getSelectedItem().toString();
        if(cadena.equalsIgnoreCase("Viviendas")){
            Validaciones.Aviso("No se ha seleccionado una Vivienda", "Gestion de Pagos");
            formPago.getjComboBoxUrb().requestFocusInWindow();
            return;
        }
        
        propieSelecc =  (String)formPago.getjTextFieldCedula().getText().trim();
        String fecha = formPago.getjTextFieldFecha().getText();
        String forma =formPago.getjComboBoxForma().getSelectedItem().toString();
        
        Date fechaCorrecta = Validaciones.CnvStringFecha(fecha);
        fechaTope = Validaciones.sumarFechasDias(fechaCorrecta, generarDiasAleatorios()).toString();
        
        monto = cuota.generarMonto();
        
        cv = new CuotaVivienda(fecha, fecha, fechaTope, forma, monto);
        
        daoCV.insertarCuotaVivienda(cv, cadena, propieSelecc);
        daoC.insertarCuota(cv);
        Validaciones.Aviso("Registro del Gasto Fijo exitoso!", "Gestion de Gastos");
        cancelar();
    }
    
    private int generarDiasAleatorios() {
        return Validaciones.Generar_Aleatorio_Interevalo(1, 30);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource().equals(formPago.getjButtonGenerar())) {
           generar();
       }
       
       if(e.getSource().equals(formPago.getjButtonCancelar())) {
           cancelar();
       }
       
       if(e.getSource().equals(formPago.getjButtonSalir())) {
           new ControladorPrincipal();
           formPago.dispose();
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
