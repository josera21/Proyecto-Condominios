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
public class ViviendaEdificio extends Vivienda {
   
    private int piso;
    private Edificio edificio;
    
    public ViviendaEdificio()
    {
        
    }
    public ViviendaEdificio(String idVivienda, int nroHabitaciones,int nroBannios,
                            String tipoVivienda, String nroTelefono, int piso) {
        super(idVivienda, nroHabitaciones, nroBannios, tipoVivienda, nroTelefono);
        this.piso = piso;
        this.edificio = new Edificio();
    }
    
    public Edificio getEdificio() {
        return edificio;
    }
    
   
    /**
     * @return the piso
     */
    public int getPiso() {
        return piso;
    }

    /**
     * @param piso the piso to set
     */
    public void setPiso(int piso) {
        this.piso = piso;
    }
    
    
}
