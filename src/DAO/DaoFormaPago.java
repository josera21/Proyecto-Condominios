/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;

/**
 *
 * @author joser
 */
public class DaoFormaPago extends ClassConexionDAO {
    
    public ResultSet cargarFormaPago() {
        ResultSet rs;
        String sql = "SELECT nombreforma FROM formapago ";
        rs = PackageConeccion.ConeccionBD.consultar(sql);
        return rs;
    }
    
}
