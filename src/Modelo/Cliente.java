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
public class Cliente extends Persona {
    
    public Cliente(String cedula,String nombre,String apellido,String direccion,String telefono, 
                   String fechaNacimiento,String sexo) {
        super(cedula,nombre,apellido,direccion,telefono,fechaNacimiento,sexo);
    }
    
    public Cliente() {
    }
}
