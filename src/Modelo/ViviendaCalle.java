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
    
    private String idCasa;
    
    public ViviendaCalle(String idVivienda, int nroHabitaciones,int nroBannios,
                            String tipoVivienda, String nroTelefono, String idCasa) {
        super(idVivienda, nroHabitaciones, nroBannios, tipoVivienda, nroTelefono);
        this.idCasa = idCasa;
    }

    /**
     * @return the idCasa
     */
    public String getIdCasa() {
        return idCasa;
    }

    /**
     * @param idCasa the idCasa to set
     */
    public void setIdCasa(String idCasa) {
        this.idCasa = idCasa;
    }
    
}
