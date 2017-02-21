/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author JoseR
 */
public class ViviendaCalle extends Vivienda {
    
    
    private Calle calle;
    public ViviendaCalle()
    {
                
    }
    public ViviendaCalle(String idVivienda, int nroHabitaciones,int nroBannios,
                            String tipoVivienda, String nroTelefono, String idCasa) {
        super(idVivienda, nroHabitaciones, nroBannios, tipoVivienda, nroTelefono);
        
        this.calle=new Calle();
    }

    public Calle getCalle() {
        return calle;
    }
}
