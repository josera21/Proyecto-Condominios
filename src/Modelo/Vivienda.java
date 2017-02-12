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
public class Vivienda {
    
    private String idVivienda;
    private String tipoVivienda;
    private String nroTelefono;
    private int nroHabitaciones;
    private int nroBannios;
    
    public Vivienda(String idVivienda, int nroHabitaciones, int nroBannios,
                    String tipoVivienda, String nroTelefono) {
        
        this.idVivienda = idVivienda;
        this.nroHabitaciones = nroHabitaciones;
        this.nroBannios = nroBannios;
        this.tipoVivienda = tipoVivienda;
        this.nroBannios = nroBannios;
    }
    
    public Vivienda() {
        super();
    }

    /**
     * @return the idVivienda
     */
    public String getIdVivienda() {
        return idVivienda;
    }

    /**
     * @param idVivienda the idVivienda to set
     */
    public void setIdVivienda(String idVivienda) {
        this.idVivienda = idVivienda;
    }

    /**
     * @return the tipoVivienda
     */
    public String getTipoVivienda() {
        return tipoVivienda;
    }

    /**
     * @param tipoVivienda the tipoVivienda to set
     */
    public void setTipoVivienda(String tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    /**
     * @return the nroTelefono
     */
    public String getNroTelefono() {
        return nroTelefono;
    }

    /**
     * @param nroTelefono the nroTelefono to set
     */
    public void setNroTelefono(String nroTelefono) {
        this.nroTelefono = nroTelefono;
    }

    /**
     * @return the nroHabitaciones
     */
    public int getNroHabitaciones() {
        return nroHabitaciones;
    }

    /**
     * @param nroHabitaciones the nroHabitaciones to set
     */
    public void setNroHabitaciones(int nroHabitaciones) {
        this.nroHabitaciones = nroHabitaciones;
    }

    /**
     * @return the nroBannios
     */
    public int getNroBannios() {
        return nroBannios;
    }

    /**
     * @param nroBannios the nroBannios to set
     */
    public void setNroBannios(int nroBannios) {
        this.nroBannios = nroBannios;
    }
    
}
