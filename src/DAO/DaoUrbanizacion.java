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
        
        String sql = "INSERT INTO \"TUrbanizacion\"(\"ID_Urbanizacion\", \"Nombre_Urbanizacion\", \"Direccion_Urbanizacion\", estatus) VALUES(";
                
                sql = sql + Validaciones.Apost(urb.getId()) + ",";
                sql = sql + Validaciones.Apost(urb.getNombre())+ ",";
                sql = sql + Validaciones.Apost(urb.getDireccion())+ ",";
                sql = sql + Validaciones.Apost("A") + ")";
                PackageConeccion.ConeccionBD.ejecutar(sql);
    }
    
    public ResultSet buscarUrbanizacion(String codigo){
        
        ResultSet registro;
        
        String sql = "SELECT * FROM \"TUrbanizacion\" WHERE \"ID_Urbanizacion\"="+Validaciones.Apost(codigo);
        
        registro = PackageConeccion.ConeccionBD.consultar(sql);
        return registro;
    }
    
    public ResultSet cargarIdUrbanizacion() {
        ResultSet registro;
        
        String sql = "SELECT \"ID_Urbanizacion\" FROM \"TUrbanizacion\" WHERE estatus='A'";
        registro = PackageConeccion.ConeccionBD.consultar(sql);
        return registro;
    }
    
    public void modificarUrbanizacion(Urbanizacion urb) {
        String sql;
      
        sql = "UPDATE \"TUrbanizacion\" SET ";
        sql = sql + "\"Nombre_Urbanizacion\"="+Validaciones.Apost(urb.getNombre())+",";
        sql = sql + "\"Direccion_Urbanizacion\"="+Validaciones.Apost(urb.getDireccion())+" ";
        sql = sql +"WHERE \"ID_Urbanizacion\"="+Validaciones.Apost(urb.getId());
      
       
        PackageConeccion.ConeccionBD.ejecutar(sql);
    }
    
    public void eliminarUrbanizacion(Urbanizacion urb) {
        String sql;
        
        sql = "UPDATE \"TUrbanizacion\" SET ";
        sql = sql + "estatus="+Validaciones.Apost("E")+" ";
        sql = sql + "WHERE \"ID_Urbanizacion\"="+Validaciones.Apost(urb.getId());
        
        PackageConeccion.ConeccionBD.ejecutar(sql);
    }
    
}
