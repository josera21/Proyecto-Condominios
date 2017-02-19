/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Librerias.Validaciones;
import Modelo.Urbanizacion;
import java.sql.ResultSet;

/**
 *
 * @author josera
 */
public class DaoUrbanizacion extends ClassConexionDAO {
    
    public void insertar(Urbanizacion urb){
        
        String sql = "INSERT INTO urbanizacion(idurbanizacion, nombre, direccion, estatus) VALUES(";
                
                sql = sql + Validaciones.Apost(urb.getIdUrbanizacion()) + ",";
                sql = sql + Validaciones.Apost(urb.getNombre())+ ",";
                sql = sql + Validaciones.Apost(urb.getDireccion())+ ",";
                sql = sql + Validaciones.Apost("A") + ")";
                PackageConeccion.ConeccionBD.ejecutar(sql);
    }
    
    public ResultSet buscarUrbanizacion(String codigo){
        
        ResultSet registro;
        
        String sql = "SELECT * FROM urbanizacion WHERE idurbanizacion="+Validaciones.Apost(codigo);
        
        registro = PackageConeccion.ConeccionBD.consultar(sql);
        return registro;
    }
    
    public ResultSet cargarIdUrbanizacion() {
        ResultSet registro;
        
        String sql = "SELECT idurbanizacion FROM urbanizacion WHERE estatus='A'";
        registro = PackageConeccion.ConeccionBD.consultar(sql);
        return registro;
    }
    
    public void modificarUrbanizacion(Urbanizacion urb) {
        String sql;
      
        sql = "UPDATE urbanizacion SET ";
        sql = sql + "nombre="+Validaciones.Apost(urb.getNombre())+",";
        sql = sql + "direccion="+Validaciones.Apost(urb.getDireccion())+" ";
        sql = sql +"WHERE idurbanizacion="+Validaciones.Apost(urb.getIdUrbanizacion());
      
       
        PackageConeccion.ConeccionBD.ejecutar(sql);
    }
    
    public void eliminarUrbanizacion(Urbanizacion urb) {
        String sql;
        
        sql = "UPDATE urbanizacion SET ";
        sql = sql + "estatus="+Validaciones.Apost("E")+" ";
        sql = sql + "WHERE idurbanizacion="+Validaciones.Apost(urb.getIdUrbanizacion());
        
        PackageConeccion.ConeccionBD.ejecutar(sql);
    }
    
}
