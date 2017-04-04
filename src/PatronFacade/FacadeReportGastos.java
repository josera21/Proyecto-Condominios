/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatronFacade;

import Controlador.ControladorConsultarGExtra;
import Controlador.ControladorConsultarGFijo;
import java.sql.SQLException;

/**
 *
 * @author joser
 */
public class FacadeReportGastos {
    
    ControladorConsultarGExtra ce;
    ControladorConsultarGFijo cf;
    
    public void showConsultarGExtra() throws SQLException {
        ce = new ControladorConsultarGExtra();
    }
    
    public void showConsultarGFijo() throws SQLException {
        cf = new ControladorConsultarGFijo();
    }
    
}
