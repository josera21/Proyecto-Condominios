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
public class Edificio {
    
    private String idEdificio;
    private int nroPisos, nroAptoPiso;
    
    public Edificio(String idEdificio, int nroPisos, int nroAptoPiso) {
        this.idEdificio = idEdificio;
        this.nroPisos = nroPisos;
        this.nroAptoPiso = nroAptoPiso;
    }

    /**
     * @return the idEdificio
     */
    public String getIdEdificio() {
        return idEdificio;
    }

    /**
     * @param idEdificio the idEdificio to set
     */
    public void setIdEdificio(String idEdificio) {
        this.idEdificio = idEdificio;
    }

    /**
     * @return the nroPisos
     */
    public int getNroPisos() {
        return nroPisos;
    }

    /**
     * @param nroPisos the nroPisos to set
     */
    public void setNroPisos(int nroPisos) {
        this.nroPisos = nroPisos;
    }

    /**
     * @return the nroAptoPiso
     */
    public int getNroAptoPiso() {
        return nroAptoPiso;
    }

    /**
     * @param nroAptoPiso the nroAptoPiso to set
     */
    public void setNroAptoPiso(int nroAptoPiso) {
        this.nroAptoPiso = nroAptoPiso;
    }
    
}
