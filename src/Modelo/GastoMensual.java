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
public class GastoMensual {
    
    private String idGasto;
    private String fechaGasto;
    
    public GastoMensual(String idGasto, String fechaGasto) {
        this.idGasto = idGasto;
        this.fechaGasto = fechaGasto;
    }
    
    public GastoMensual() {
        super();
    }

    /**
     * @return the idGasto
     */
    public String getIdGasto() {
        return idGasto;
    }

    /**
     * @param idGasto the idGasto to set
     */
    public void setIdGasto(String idGasto) {
        this.idGasto = idGasto;
    }

    /**
     * @return the fechaGasto
     */
    public String getFechaGasto() {
        return fechaGasto;
    }

    /**
     * @param fechaGasto the fechaGasto to set
     */
    public void setFechaGasto(String fechaGasto) {
        this.fechaGasto = fechaGasto;
    }
    
    
    
}
