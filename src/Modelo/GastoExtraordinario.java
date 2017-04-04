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
public class GastoExtraordinario {
    
    private String idGastoEx;
    private String descGasto;
    private String fechaGastoEx;
    private double montoGastoEx;
    
    public GastoExtraordinario(String idGastoEx, String descGasto, String fechaGastoEx,
                                double montoGastoEx) {
        this.idGastoEx = idGastoEx;
        this.descGasto = descGasto;
        this.fechaGastoEx = fechaGastoEx;
        this.montoGastoEx = montoGastoEx;
    }

    /**
     * @return the idGastoEx
     */
    public String getIdGastoEx() {
        return idGastoEx;
    }

    /**
     * @param idGastoEx the idGastoEx to set
     */
    public void setIdGastoEx(String idGastoEx) {
        this.idGastoEx = idGastoEx;
    }

    /**
     * @return the descGasto
     */
    public String getDescGasto() {
        return descGasto;
    }

    /**
     * @param descGasto the descGasto to set
     */
    public void setDescGasto(String descGasto) {
        this.descGasto = descGasto;
    }

    /**
     * @return the fechaGastoEx
     */
    public String getFechaGastoEx() {
        return fechaGastoEx;
    }

    /**
     * @param fechaGastoEx the fechaGastoEx to set
     */
    public void setFechaGastoEx(String fechaGastoEx) {
        this.fechaGastoEx = fechaGastoEx;
    }

    /**
     * @return the montoGastoEx
     */
    public double getMontoGastoEx() {
        return montoGastoEx;
    }

    /**
     * @param montoGastoEx the montoGastoEx to set
     */
    public void setMontoGastoEx(double montoGastoEx) {
        this.montoGastoEx = montoGastoEx;
    }
    
}
