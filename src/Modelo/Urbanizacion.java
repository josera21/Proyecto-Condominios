/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import PatronPrototype.Prototype;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josera
 */
public class Urbanizacion implements Prototype, Cloneable {
    
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
     * @return the nombre
     */
    @Override
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the direccion
     */
    @Override
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    @Override
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    } 

    @Override
    public void setId(String id) {
       this.idUrbanizacion = id;
    }

    @Override
    public String getId() {
        return idUrbanizacion;
    }

    @Override
    public Prototype clone() throws CloneNotSupportedException {
        return (Prototype) super.clone();
    }
}
