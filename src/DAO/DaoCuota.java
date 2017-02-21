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
public class DaoCuota extends ClassConexionDAO {
    
     public void insertarCuota(CuotaVivienda cv) {
        String sql = "INSERT INTO cuota(fechaemision, fechatope, monto, estatus) VALUES(";
        sql = sql + Validaciones.Apost(cv.getFechaEmision()) + ",";
        sql = sql + Validaciones.Apost(cv.getFechaTope()) + ",";
        sql = sql + Validaciones.Apost(String.valueOf(cv.getMonto())) + ",";
        sql = sql + Validaciones.Apost("A") + ")";
        
        PackageConeccion.ConeccionBD.ejecutar(sql);
    }
    
}
