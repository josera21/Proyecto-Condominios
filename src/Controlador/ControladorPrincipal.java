/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Listas;
import Vista.jPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JoseR
 */
public class ControladorPrincipal implements ActionListener {
    
    private jPrincipal formPrincipal;

    public ControladorPrincipal(){
        formPrincipal=new jPrincipal();
        formPrincipal.agregarListener(this);
        formPrincipal.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(formPrincipal.getjMenuItemCliente())){
            new ControladorCliente();
            formPrincipal.dispose();
        }
        
        if(e.getSource().equals(formPrincipal.getjMenuItemCasa())){
            new ControladorCasa();
            formPrincipal.dispose();
        }
        
        if(e.getSource().equals(formPrincipal.getjMenuItemUrb())){
            new ControladorUrbanizacion();
            formPrincipal.dispose();
        }
        
        if(e.getSource().equals(formPrincipal.getjMenuItemGastoExtra())){
            
            try {
                new ControladorGastoExtraordinario();
                formPrincipal.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        if(e.getSource().equals(formPrincipal.getjMenuItemReporteGastoEx())){
            try {
                new ControladorConsultarGExtra();
                formPrincipal.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
