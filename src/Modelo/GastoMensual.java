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
    
    private String fechaGasto;
    
    public GastoMensual(String fechaGasto) {
        this.fechaGasto = fechaGasto;
    }
    
    public GastoMensual() {
        super();
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
