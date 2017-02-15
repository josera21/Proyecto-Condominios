/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author josera
 */
public class Calle {
    
    private int idCalle, nroCasas;
    
    public Calle(int idCalle, int nroCasas) {
        this.idCalle = idCalle;
        this.nroCasas = nroCasas;
    }
    
    public Calle() {
        super();
    }

    /**
     * @return the idCalle
     */
    public int getIdCalle() {
        return idCalle;
    }

    /**
     * @param idCalle the idCalle to set
     */
    public void setIdCalle(int idCalle) {
        this.idCalle = idCalle;
    }

    /**
     * @return the nroCasas
     */
    public int getNroCasas() {
        return nroCasas;
    }

    /**
     * @param nroCasas the nroCasas to set
     */
    public void setNroCasas(int nroCasas) {
        this.nroCasas = nroCasas;
    }
    
}
