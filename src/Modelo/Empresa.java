/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author joser
 */
public class Empresa {
    
    private String nombre,rif;
    
    public Empresa(String nombre, String rif){
        super();
        this.nombre = nombre;
        this.rif = rif;
    }
    
    public Empresa(){
        
    }
    
    public void setNombre(String nomb){
        this.nombre = nomb;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setRif(String rif){
        this.rif = rif;
    }
    
    public String getRif(){
        return rif;
    }
    
}
