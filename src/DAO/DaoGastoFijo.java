/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Librerias.Validaciones;
import Modelo.GastoMensual;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author joser
 */
public class DaoGastoFijo {
    
    public void insertarGasto(GastoMensual gastoFi, String idUrb, double gastoLuz,
                               double gastoAgua, double gastoVigilancia, double gastoConserje,
                               double gastoJardineria) {
        
        String sql = "INSERT INTO gastofijo(idurbanizacion, gastoluz, gastoagua,"
                + "gastovigilancia, gastoconserje, gastojardineria, fecha, estatus) VALUES (";
        sql = sql + Validaciones.Apost(idUrb) + ",";
        sql = sql + Validaciones.Apost(String.valueOf(gastoLuz)) + ",";
        sql = sql + Validaciones.Apost(String.valueOf(gastoAgua)) + ",";
        sql = sql + Validaciones.Apost(String.valueOf(gastoVigilancia)) + ",";
        sql = sql + Validaciones.Apost(String.valueOf(gastoConserje)) + ",";
        sql = sql + Validaciones.Apost(String.valueOf(gastoJardineria)) + ",";
        sql = sql + Validaciones.Apost(gastoFi.getFechaGasto()) + ",";
        sql = sql + Validaciones.Apost("A") + ")";
        
        PackageConeccion.ConeccionBD.ejecutar(sql);
    }
    
    public ResultSet buscarGastoFijo(String idGastoFi, String idUrb) {
        
        ResultSet registro;
        
        String sql = "SELECT * FROM gastofijo WHERE idgastofijo="+Validaciones.Apost(idGastoFi) + " ";
        sql = sql + "AND idurbanizacion="+Validaciones.Apost(idUrb);
        
        registro = PackageConeccion.ConeccionBD.consultar(sql);
        return registro;
        
    }
    
    public ResultSet cargarGastoExtra() {
        ResultSet registro;
        
        String sql = "SELECT * FROM gastofijo";
        registro = PackageConeccion.ConeccionBD.consultar(sql);
        return registro;
    }
    
    
    public ResultSet gastoFijoPorUrb(String idUrb) throws SQLException {
       ResultSet registro;
        
        String sql = "SELECT * FROM gastofijo WHERE idurbanizacion="+Validaciones.Apost(idUrb);
               sql = sql + " AND estatus='A'";
        
        registro = PackageConeccion.ConeccionBD.consultar(sql);
        return registro;
    } 
    
    public ResultSet buscarGastoMesFijo(String idUrb, String mes) throws SQLException {
        ResultSet registro;
        String cadena = mes.substring(3, 5);
        cadena = cadena.trim();
        
        String sql = "SELECT * FROM gastofijo "
                + "WHERE idurbanizacion="+Validaciones.Apost(idUrb);
               sql = sql + " AND estatus='A' ";
               sql = sql + "AND extract(MONTH from fecha) ='"+cadena+"'";
        registro = PackageConeccion.ConeccionBD.consultar(sql);
        
        return registro;
    }
    
}
