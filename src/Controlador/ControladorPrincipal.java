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

/**
 *
 * @author JoseR
 */
public class ControladorPrincipal implements ActionListener {
    
    private jPrincipal formPrincipal;
    private Listas lisClie;

    public ControladorPrincipal(){
        lisClie = new Listas();
        lisClie.cargarClientes();
        
        formPrincipal=new jPrincipal();
        formPrincipal.agregarListener(this);
        formPrincipal.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(formPrincipal.getjMenuItemCliente())){
            new ControladorCliente(lisClie);
        }
    }
}
