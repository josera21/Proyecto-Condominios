/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Vivienda;
import Modelo.ViviendaCalle;
import Modelo.ViviendaEdificio;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author josera
 */
public class DaoVivienda extends ClassConexionDAO {
    
     public void insertarVivienda(Vivienda vivienda) throws SQLException {
        PackageConeccion.ConeccionBD.getConeccion();
        String sql="INSERT INTO \"TVivienda\"\n" +
                   "VALUES ('"+vivienda.getIdVivienda()+"', '"+vivienda.getUrb().getId()+"', '"+vivienda.getCli().getCedula()+
                            "',"+vivienda.getNroHabitaciones()+","+ vivienda.getNroBannios()+",'"+vivienda.getTipoVivienda()+"', '"
                            + vivienda.getNroTelefono()+"','A')";
        PackageConeccion.ConeccionBD.ejecutar(sql);
        PackageConeccion.ConeccionBD.getConeccion().close();
    }
     public void insertarViviendaCalle(ViviendaCalle casa) throws SQLException
     {
        PackageConeccion.ConeccionBD.getConeccion();
        String sql="INSERT INTO \"TVivienda_Calle\" \n" +
                   "VALUES ('"+casa.getCalle().getIdCalle()+"','"+casa.getIdVivienda()+"','A')";
        PackageConeccion.ConeccionBD.ejecutar(sql);
        PackageConeccion.ConeccionBD.getConeccion().close();
     }
     public void insertarViviendaEdificio(ViviendaEdificio apto) throws SQLException
     {
        PackageConeccion.ConeccionBD.getConeccion();
        String sql="INSERT INTO \"TVivienda_Edificio\" VALUES ('"+apto.getEdificio() +"','"+ apto.getIdVivienda()+"','A'"+
                                                                 apto.getVe().getPiso()+")";
        PackageConeccion.ConeccionBD.ejecutar(sql); 
        PackageConeccion.ConeccionBD.getConeccion().close();
     }
     
    public void modificarVivienda(Vivienda casa) throws SQLException 
    {
        PackageConeccion.ConeccionBD.getConeccion();
        String sql="UPDATE \"TVivienda\" SET  \"Id_Propietario\"='"+casa.getCli().getCedula()+
                   "', \"Nro_Habitaciones\"="+casa.getNroHabitaciones()+",\"Nro_Bannos\"="+casa.getNroBannios()+
                   ", \"Telefono\"="+casa.getNroTelefono()+" WHERE \"Id_Vivienda\"='"+casa.getIdVivienda()+"'";
        PackageConeccion.ConeccionBD.ejecutar(sql);
        PackageConeccion.ConeccionBD.getConeccion().close();
    }
   
    
    public void eliminarVivienda(Vivienda casa) throws SQLException 
    {
        PackageConeccion.ConeccionBD.getConeccion();
        String sql="UPDATE \"TVivienda\" SET \"estatus\"='A' WHERE \"Id_Vivienda\"='"+casa.getIdVivienda()+"'";
        PackageConeccion.ConeccionBD.ejecutar(sql);
        PackageConeccion.ConeccionBD.getConeccion().close();
    }
  
    public ResultSet ConsultarViviendas() throws SQLException
    {
        PackageConeccion.ConeccionBD.getConeccion();
        String sql="Select \"Id_Vivienda\" from \"TVivienda\" order by \"Id_Vivienda\" ASC";
        ResultSet rs=PackageConeccion.ConeccionBD.consultar(sql);
        PackageConeccion.ConeccionBD.getConeccion().close();
        return rs;
    }
    public String Consultar_Ultimo_Codigo_Vivienda_Calle(String Urb,String calle) throws SQLException
    {    
            PackageConeccion.ConeccionBD.getConeccion();
            String SQL="select substring(\"Id_Vivienda\" from 13 for 3) as \"ID\" " 
                 + " from \"TVivienda\" "
                 + " where substring(\"Id_Vivienda\" from 1 for 4)='CASA' "
                 + " and   substring(\"Id_Vivienda\" from 6 for 3)='"+Urb+"'"
                 + " and   substring(\"Id_Vivienda\" from 10 for 2)='"+calle+"'"
                 + " Order by \"Id_Vivienda\" desc limit 1";
            ResultSet Codigo=PackageConeccion.ConeccionBD.consultar(SQL);
            PackageConeccion.ConeccionBD.getConeccion().close();
            if(Codigo.next())
            {
                return Codigo.getString("ID").trim();
            }
            return "";
    }
    public String Consultar_Ultimo_Codigo_Vivienda_Edificio(String Urb,String Edificio,String Piso) throws SQLException
    {
           PackageConeccion.ConeccionBD.getConeccion();
            String SQL="select substring(\"Id_Vivienda\" from 16 for 3) as \"ID\"" 
                 + " from \"TVivienda\" "
                 + " where  substring(\"Id_Vivienda\" from 1 for 4)='APTO' "
                 + " and    substring(\"Id_Vivienda\" from 6 for 3)='"+Urb+"'"
                 + " and    substring(\"Id_Vivienda\" from 10 for 2)='"+Edificio+"'"
                 + " and    substring(\"Id_Vivienda\" from 13 for 2)='"+Piso+"'"
                 + " Order by \"Id_Vivienda\" desc limit 1";
            ResultSet Codigo=PackageConeccion.ConeccionBD.consultar(SQL);
            PackageConeccion.ConeccionBD.getConeccion().close();
             if(Codigo.next())
            {
                return Codigo.getString("ID").trim();
            }
            return "";
    }
    public ResultSet Consultar_Piso_Nro_Apto(String Edificio) throws SQLException
    {
        
        PackageConeccion.ConeccionBD.getConeccion();
        String sql="Select \"Nro_Aptos_Piso\" as \"NAP\",\"Nro_Pisos\" as \"AP\" from \"TEdificio\"\n" +
                   "where SubString(\"Id_Edificio\"from 7 for 2)::Integer<\"Nro_Aptos_Piso\" and" +
                   " \"Id_Edificio\"='"+Edificio+"'";
        
        ResultSet rs=PackageConeccion.ConeccionBD.consultar(sql);
        
        PackageConeccion.ConeccionBD.getConeccion().close();
        return rs;
    }
    public ResultSet Consultar_Codigos_Urbanizaciones() throws SQLException
    {
             PackageConeccion.ConeccionBD.getConeccion();
             String sql="SELECT \"ID_Urbanizacion\" FROM \"TUrbanizacion\" WHERE estatus='A'";
             ResultSet Codigos = PackageConeccion.ConeccionBD.consultar(sql);
             PackageConeccion.ConeccionBD.getConeccion().close();
             return Codigos;  
    }
    public ResultSet Cosultar_Calles_Urbanizacion(String Urbanizacion) throws SQLException
    {
        
        PackageConeccion.ConeccionBD.getConeccion();
        String sql="SELECT \"Id_Calle\" as \"ID\"  from \"TCalle\"" 
                  +"where SubString(\"Id_Calle\" from 3 for 3)=substring('"+Urbanizacion+"',4, 3)" +
                   "order by \"Id_Calle\" Asc";
        ResultSet Calles=PackageConeccion.ConeccionBD.consultar(sql);
        PackageConeccion.ConeccionBD.getConeccion().close();
        return Calles;
    }
    public ResultSet Consultar_Edificios_Urbanizacion(String Urbanizacion) throws SQLException
    {
        PackageConeccion.ConeccionBD.getConeccion();
        String sql="SELECT  \"Id_Edificio\" as \"ID\" from \"TEdificio\"" 
                  +"where SubString(\"Id_Edificio\" from 3 for 3)=SubString('"+Urbanizacion+"',4, 3)" +
                   "order by \"Id_Edificio\" Asc";
        ResultSet Edificios=PackageConeccion.ConeccionBD.consultar(sql);
        PackageConeccion.ConeccionBD.getConeccion().close();
        return Edificios;
    }
    
    public ResultSet Consultar_Urbanizaciones_Propietario(String idPropietario) throws SQLException {
        ResultSet registro;
        String id = idPropietario.trim();
        PackageConeccion.ConeccionBD.getConeccion();
        String sql = "SELECT DISTINCT \"Id_Urbanizacion\" FROM \"TVivienda\" WHERE "
                + "\"Id_Propietario\"='"+id+"' AND estatus='A'";
        registro = PackageConeccion.ConeccionBD.consultar(sql);
        PackageConeccion.ConeccionBD.getConeccion().close();
        return registro;
    }
    
    public ResultSet Consultar_Viviendas_Propietario(String idPropietario, String idUrbanizacion) throws SQLException {
        ResultSet registro;
        String id = idPropietario.trim();
        String idUrb = idUrbanizacion.trim();
        PackageConeccion.ConeccionBD.getConeccion();
        String sql = "SELECT \"Id_Vivienda\" FROM \"TVivienda\" WHERE "
                + "\"Id_Propietario\"='"+id+"' AND \"Id_Urbanizacion\"='"+idUrb+"'"
                + " AND estatus='A'";
        registro = PackageConeccion.ConeccionBD.consultar(sql);
        PackageConeccion.ConeccionBD.getConeccion().close();
        return registro;
    }
    
}
    