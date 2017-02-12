/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Librerias.Validaciones;
import Modelo.Cliente;
import java.sql.ResultSet;

/**
 *
 * @author joser
 */
public class DaoCliente extends ClassConexionDAO {
    
    public void insertar(Cliente cliente){
        
        String sql = "INSERT INTO cliente(cedula, nombre, segnombre, apellido,"
                + "segapellido, direccion, telefono,fechanac, sexo, email, estatus) VALUES(";
                
                sql = sql + Validaciones.Apost(cliente.getCedula()) + ",";
                sql = sql + Validaciones.Apost(cliente.getNombre())+ ",";
                sql = sql + Validaciones.Apost(cliente.getSegNombre())+ ",";
                sql = sql + Validaciones.Apost(cliente.getApellido())+ ",";
                sql = sql + Validaciones.Apost(cliente.getSegApellido())+ ",";
                sql = sql + Validaciones.Apost(cliente.getDireccion())+ ",";
                sql = sql + Validaciones.Apost(cliente.getTelefono())+ ",";
                sql = sql + Validaciones.Apost(cliente.getFechaNacimiento()) + ",";
                sql = sql + Validaciones.Apost(cliente.getSexo()) + ",";
                sql = sql + Validaciones.Apost(cliente.getEmail()) + ",";
                sql = sql + Validaciones.Apost("A") + ")";
                PackageConeccion.ConeccionBD.ejecutar(sql);
        
    }
    
    public ResultSet buscarCliente(String cedula){
        
        ResultSet registro;
        
        String sql = "SELECT * FROM cliente WHERE cedula="+Validaciones.Apost(cedula);
        
        registro = PackageConeccion.ConeccionBD.consultar(sql);
        return registro;
    }
    
    public ResultSet cargarCliente() {
        ResultSet registro;
        
        String sql = "SELECT * FROM cliente";
        registro = PackageConeccion.ConeccionBD.consultar(sql);
        return registro;
    }
    
    public void modificarCliente(Cliente cliente) {
        String sql;
      
        sql = "UPDATE cliente SET ";
        sql = sql + "nombre="+Validaciones.Apost(cliente.getNombre())+",";
        sql = sql + "segnombre="+Validaciones.Apost(cliente.getSegNombre())+ ",";
        sql = sql + "apellido="+Validaciones.Apost(cliente.getApellido())+",";
        sql = sql + "segapellido="+Validaciones.Apost(cliente.getSegApellido())+ ",";
        sql = sql + "direccion="+Validaciones.Apost(cliente.getDireccion())+",";
        sql = sql + "telefono="+Validaciones.Apost(cliente.getTelefono())+",";
        sql = sql + "fechanac="+Validaciones.Apost(cliente.getFechaNacimiento())+",";
        sql = sql + "sexo="+Validaciones.Apost(cliente.getSexo())+",";
        sql = sql + "email="+Validaciones.Apost(cliente.getEmail()) + " ";
        sql = sql +"WHERE cedula="+Validaciones.Apost(cliente.getCedula());
      
       
        PackageConeccion.ConeccionBD.ejecutar(sql);
    }
    
    public void eliminarCliente(Cliente cliente) {
        String sql;
        
        sql = "UPDATE cliente SET ";
        sql = sql + "estatus="+Validaciones.Apost("E")+" ";
        sql = sql + "WHERE cedula="+Validaciones.Apost(cliente.getCedula());
        
        PackageConeccion.ConeccionBD.ejecutar(sql);
    }
}
