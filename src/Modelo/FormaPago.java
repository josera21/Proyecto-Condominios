/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author joser
 */
public class FormaPago {
    
    private String idFormaPago, nombreForma;
    
    public FormaPago(String idFormaPago, String nombreForma) {
        this.idFormaPago = idFormaPago;
        this.nombreForma = nombreForma;
    } 

    public FormaPago() {
        super();
    }
    
    /**
     * @return the idFormaPago
     */
    public String getIdFormaPago() {
        return idFormaPago;
    }

    /**
     * @param idFormaPago the idFormaPago to set
     */
    public void setIdFormaPago(String idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    /**
     * @return the nombreForma
     */
    public String getNombreForma() {
        return nombreForma;
    }

    /**
     * @param nombreForma the nombreForma to set
     */
    public void setNombreForma(String nombreForma) {
        this.nombreForma = nombreForma;
    }
    
    
    
}
