/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author josera
 */
public class Urbanizacion {
    
    private String idUrbanizacion, nombre, direccion;
    
    public Urbanizacion(String id, String nombre, String direccion) {
        this.idUrbanizacion = id;
        this.nombre = nombre;
        this.direccion = direccion;
    }
    
    public Urbanizacion() {
        super();
    }

    /**
     * @return the idUrbanizacion
     */
    public String getIdUrbanizacion() {
        return idUrbanizacion;
    }

    /**
     * @param idUrbanizacion the idUrbanizacion to set
     */
    public void setIdUrbanizacion(String idUrbanizacion) {
        this.idUrbanizacion = idUrbanizacion;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
    
}
