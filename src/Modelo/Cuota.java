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
public class Cuota {
    
    private String fechaEmision, fechaTope, formaPago;
    private double monto;
    
    public Cuota(String fechaEmision, String fechaTope,
                 String formaPago, double monto) {
        this.fechaEmision = fechaEmision;
        this.fechaTope = fechaTope;
        this.formaPago = formaPago;
        this.monto = monto;
    }
    
    public Cuota() {
        super();
    }
    
    public double generarMonto(){
        return Validaciones.Generar_Aleatorio_Interevalo(40000, 80000);
    }
    
    
    /**
     * @return the fechaEmision
     */
    public String getFechaEmision() {
        return fechaEmision;
    }

    /**
     * @param fechaEmision the fechaEmision to set
     */
    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * @return the fechaTope
     */
    public String getFechaTope() {
        return fechaTope;
    }

    /**
     * @param fechaTope the fechaTope to set
     */
    public void setFechaTope(String fechaTope) {
        this.fechaTope = fechaTope;
    }

    /**
     * @return the formaPago
     */
    public String getFormaPago() {
        return formaPago;
    }

    /**
     * @param formaPago the formaPago to set
     */
    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    /**
     * @return the monto
     */
    public double getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(double monto) {
        this.monto = monto;
    }
    
}
