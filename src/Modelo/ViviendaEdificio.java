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
    
    private String idApto;
    private int piso;
    
    public ViviendaEdificio(String idVivienda, int nroHabitaciones,int nroBannios,
                            String tipoVivienda, String nroTelefono,String idApto, 
                            int piso) {
        super(idVivienda, nroHabitaciones, nroBannios, tipoVivienda, nroTelefono);
        this.idApto = idApto;
        this.piso = piso;
    }

    /**
     * @return the idApto
     */
    public String getIdApto() {
        return idApto;
    }

    /**
     * @param idApto the idApto to set
     */
    public void setIdApto(String idApto) {
        this.idApto = idApto;
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
