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
    
    private String email, segNombre, segApellido;
    
    public Cliente(String cedula,String nombre, String segNombre,String apellido,
                   String segApellido, String direccion,String telefono, 
                   String fechaNacimiento,String sexo, String email) {
        super(cedula,nombre,apellido,direccion,telefono,fechaNacimiento,sexo);
        
        this.segNombre = segNombre;
        this.segApellido = segApellido;
        this.email = email;
    }
    
    public Cliente() {
    }
    
    public String getSegNombre() {
        return this.segNombre;
    }
    
    public String getSegApellido() {
        return this.segApellido;
    }
    
    public String getEmail() {
        return this.email;
    }
}
