/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Librerias.Validaciones;
import Modelo.GastoExtraordinario;
import Modelo.Urbanizacion;
import Vista.jGastoExtraordinario;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author josera
 */
public class DaoGastoExtraordinario extends ClassConexionDAO  {
    
    private jGastoExtraordinario formGastoExtra;
    
    public DaoGastoExtraordinario() {
         formGastoExtra = new jGastoExtraordinario();
    }
    
    public void insertarGasto(GastoExtraordinario gastoEx, String idUrb) {
        
        String sql = "INSERT INTO gastoextra(idgastoextra, idurbanizacion, monto,"
                + "descripcion, fecha, estatus) VALUES (";
        sql = sql + Validaciones.Apost(gastoEx.getIdGastoEx()) + ",";
        sql = sql + Validaciones.Apost(idUrb) + ",";
        sql = sql + Validaciones.Apost(String.valueOf(gastoEx.getMontoGastoEx())) + ",";
        sql = sql + Validaciones.Apost(gastoEx.getDescGasto()) + ",";
        sql = sql + Validaciones.Apost(gastoEx.getFechaGastoEx()) + ",";
        sql = sql + Validaciones.Apost("A") + ")";
        
        PackageConeccion.ConeccionBD.ejecutar(sql);
    }
    
    public ResultSet buscarGastoExtra(String idGastoEx, String idUrb) {
        
        ResultSet registro;
        
        String sql = "SELECT * FROM gastoextra WHERE idgastoextra="+Validaciones.Apost(idGastoEx) + " ";
        sql = sql + "AND idurbanizacion="+Validaciones.Apost(idUrb);
        
        registro = PackageConeccion.ConeccionBD.consultar(sql);
        return registro;
        
    }
    
    public ResultSet cargarGastoExtra() {
        ResultSet registro;
        
        String sql = "SELECT * FROM gastoextra";
        registro = PackageConeccion.ConeccionBD.consultar(sql);
        return registro;
    }
    
    public void modificarGastoExtra(GastoExtraordinario gastoEx, String idUrb) {
        String sql;
        
        sql = "UPDATE gastoextra SET ";
        sql = sql + "monto="+Validaciones.Apost(String.valueOf(gastoEx.getMontoGastoEx())) + ",";
        sql = sql + "descripcion="+Validaciones.Apost(gastoEx.getDescGasto()) + ",";
        sql = sql + "fecha="+Validaciones.Apost(gastoEx.getFechaGastoEx()) + " ";
        sql = sql + "WHERE idgastoextra="+Validaciones.Apost(gastoEx.getIdGastoEx())+ " ";
        sql = sql + "AND idurbanizacaion="+Validaciones.Apost(idUrb);
      
        PackageConeccion.ConeccionBD.ejecutar(sql);  
    }
    
    public void eliminarGastoExtra(GastoExtraordinario gastoEx, String idUrb) {
        String sql;
        
        sql = "UPDATE gastoextra SET ";
        sql = sql + "estatus="+Validaciones.Apost("E")+" ";
        sql = sql + "WHERE idgastoextra="+Validaciones.Apost(gastoEx.getIdGastoEx())+ " ";
        sql = sql + "AND idurbanizacion="+Validaciones.Apost(idUrb);
        
        PackageConeccion.ConeccionBD.ejecutar(sql);
    }
    
    public ResultSet gastoExtraPorUrb(String idUrb) throws SQLException {
       ResultSet registro;
        
        String sql = "SELECT * FROM gastoextra WHERE idurbanizacion="+Validaciones.Apost(idUrb);
               sql = sql + " AND estatus='A'";
        
        registro = PackageConeccion.ConeccionBD.consultar(sql);
        return registro;
    } 
}
