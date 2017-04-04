/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatronPrototype;

/**
 *
 * @author joser
 */
public interface Prototype {
    
    public void setId(String id);
    public void setNombre(String nombre);
    public void setDireccion(String direccion);
    public String getId();
    public String getNombre();
    public String getDireccion();
}
