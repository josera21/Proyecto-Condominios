/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Librerias.Validaciones;
import Modelo.CuotaVivienda;

/**
 *
 * @author joser
 */
public class DaoCuotaVivienda extends ClassConexionDAO {
    
    public void insertarCuotaVivienda(CuotaVivienda cv,String idVi ,String idpropietario) {
        String sql = "INSERT INTO cuotavivienda(idvivienda, idformapago, idpropietario,"
                + "fechapago, estatus) VALUES(";
        sql = sql + Validaciones.Apost(idVi) + ",";
        sql = sql + Validaciones.Apost(cv.getFormaPago()) + ",";
        sql = sql + Validaciones.Apost(idpropietario) + ",";
        sql = sql + Validaciones.Apost(cv.getFechaPago()) + ",";
        sql = sql + Validaciones.Apost("A") + ")";
        
        PackageConeccion.ConeccionBD.ejecutar(sql);
    }
    
}
