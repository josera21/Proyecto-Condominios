/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.DaoGastoFijo;
import DAO.DaoUrbanizacion;
import Librerias.Validaciones;
import Modelo.Condominio;
import Modelo.GastoMensual;
import Vista.jGastoFijo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author joser
 */
public class ControladorGastoFijo implements ActionListener {
    
    private jGastoFijo formGastoFijo;
    
    public ControladorGastoFijo() throws SQLException {
        formGastoFijo = new jGastoFijo();
        formGastoFijo.agregarListener(this);
        formGastoFijo.setVisible(true);
        
        cargarUrbanizacion();
    }

    
    private void cargarUrbanizacion() throws SQLException {
        ResultSet regUrb;
        DaoUrbanizacion daoUrb = new DaoUrbanizacion();
        String idurb;
        
        try {
            regUrb = daoUrb.cargarIdUrbanizacion();
            while(regUrb.next()) {
            idurb = regUrb.getString("idurbanizacion");
            formGastoFijo.getjComboBoxUrb().addItem(idurb);
            }
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(formGastoFijo, ex);
        }
    } 
    
    private void guardar() throws SQLException {
        GastoMensual gastoFi;
        DaoGastoFijo daoFijo = new DaoGastoFijo();
        ResultSet regGastoFi;
        String cadena, urbSeleccionada;
        double[] monto = new double[5];
        boolean aprobar;
        
        if(Validaciones.ValidarCamposVacios(formGastoFijo.getjTextFieldFecha())){
            Validaciones.Aviso("Hay campos vacios", "Gestion de Gasto");
            return;
        }
        
        cadena = formGastoFijo.getjTextFieldFecha().getText().trim();
    
        if(!Validaciones.isDate(cadena)){
            Validaciones.Aviso("Error en la Fecha", "Gestion de Gastos");
            formGastoFijo.getjTextFieldFecha().requestFocusInWindow();
            return;
        }
        
        cadena = formGastoFijo.getjComboBoxUrb().getSelectedItem().toString();
        if(cadena.equalsIgnoreCase("Urbanizaciones")){
            Validaciones.Aviso("No se ha seleccionado una Urbanizacion", "Gestion de Gastos");
            formGastoFijo.getjComboBoxUrb().requestFocusInWindow();
            return;
        }
        
        urbSeleccionada = (String)formGastoFijo.getjComboBoxUrb().getSelectedItem();
        String fecha = formGastoFijo.getjTextFieldFecha().getText();
        regGastoFi = daoFijo.buscarGastoMesFijo(urbSeleccionada, fecha);
        aprobar = Validaciones.validarGastosExtrasMes(fecha,
                                            formGastoFijo.getjComboBoxUrb(), 1, regGastoFi);
        if(!aprobar) {
            Validaciones.Aviso("No se puede Registrar otro Gasto este Mes", "Gestion de Gastos");
            return;
        }
        
        //Llenando montos aleatorios
        for(int i= 0; i<5; i++){
           monto[i] = seleccionGasto(i);
        }
      
        gastoFi = new GastoMensual(fecha);
        
        daoFijo.insertarGasto(gastoFi, urbSeleccionada, monto[0], monto[1],
                              monto[2], monto[3], monto[4]);
        Validaciones.Aviso("Registro del Gasto Fijo exitoso!", "Gestion de Gastos");
        cancelar();
    }
    
    private double seleccionGasto(int index) {
        Condominio co = new Condominio();
        double monto = 0;
        
        switch(index){
            case 1: monto = co.calcularGastoLuz();
            break;
            case 2: monto = co.calcularGastoAgua();
            break;
            case 3: monto = co.calcularGastoVigilancia();
            break;
            case 4: monto = co.calcularGastoAseo();
            break;
            case 5: monto = co.calcularGastoJardineria();
            break;
        }
        
        return monto;
    }
    
    private void cancelar() {
        formGastoFijo.getjTextFieldFecha().setText("");
        formGastoFijo.getjComboBoxUrb().setSelectedIndex(0);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(formGastoFijo.getjButtonGenerar())){
            try {
                guardar();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorGastoFijo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(e.getSource().equals(formGastoFijo.getjButtonSalir())){
            new ControladorPrincipal();
            formGastoFijo.dispose();
        }
    }
    
}
