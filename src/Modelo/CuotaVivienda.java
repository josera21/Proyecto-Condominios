/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Librerias.Validaciones;

/**
 *
 * @author joser
 */
public class CuotaVivienda extends Cuota {
    
    private String fechaPago;
    
    public CuotaVivienda(String fechaPago, String fechaEmision,String fechaTope, 
                         String formaPago, double monto) {
        super(fechaEmision, fechaTope, formaPago, monto);
        
        this.fechaPago = fechaPago;
    }
 
    /**
     * @return the fechaPago
     */
    public String getFechaPago() {
        return fechaPago;
    }

    /**
     * @param fechaPago the fechaPago to set
     */
    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }
    
}
