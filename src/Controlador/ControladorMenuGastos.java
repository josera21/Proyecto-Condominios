/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import PatronFacade.FacadeReportGastos;
import Vista.jFacadeMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joser
 */
public class ControladorMenuGastos implements ActionListener, KeyListener {
    
    private jFacadeMenu jFacade;
    
    public ControladorMenuGastos() {
        jFacade = new jFacadeMenu();
        jFacade.agregarListener(this);
        jFacade.setVisible(true);
        jFacade.setResizable(false);
        jFacade.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        FacadeReportGastos fReport = new FacadeReportGastos();
        
        if(e.getSource().equals(jFacade.getjButtonConsulVolver())) {
            new ControladorPrincipal();
            jFacade.dispose();
        }
        
        if(e.getSource().equals(jFacade.getjButtonConsulGE())) {
            try {
                fReport.showConsultarGExtra();
                jFacade.dispose();
            } catch(SQLException ex) {
                Logger.getLogger(ControladorMenuGastos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(e.getSource().equals(jFacade.getjButtonConsulGF())) {
            try {
                fReport.showConsultarGFijo();
                jFacade.dispose();
            } catch(SQLException ex) {
                Logger.getLogger(ControladorMenuGastos.class.getName()).log(Level.SEVERE, null, ex);
            }
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
