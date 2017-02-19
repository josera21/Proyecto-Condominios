/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Librerias.Validaciones;

/**
 *
 * @author josera
 */
public class Condominio {
    
    private double acumGasto = 0;
    private GastoExtraordinario gastoEx[];
    
    
    public double calcularGastoLuz() {
        return Validaciones.Generar_Aleatorio_Interevalo(40000, 96000);
    }
    
    public double calcularGastoAgua() {
        return Validaciones.Generar_Aleatorio_Interevalo(31200, 61500);
    }
    
    public double calcularGastoAseo() {
        return Validaciones.Generar_Aleatorio_Interevalo(21600, 52300);
    }
    
    public double calcularGastoVigilancia() {
        return Validaciones.Generar_Aleatorio_Interevalo(90000, 120000);
    }
    
    public double calcularGastoJardineria() {
        return Validaciones.Generar_Aleatorio_Interevalo(30000, 60000);
    }
    
    public double calcularGastoExtra() {
        // el numero de gastos extra que tendra sera Random. 
        int nroGasto = Validaciones.Generar_Aleatorio(3);
        for(int i=0; i<nroGasto; i++) {
            acumGasto = gastoEx[i].getMontoGastoEx();
        }
        return acumGasto;
    }
    
    public double calcularGastoMensual() {
        double gasto;
        gasto = calcularGastoLuz() + calcularGastoAgua() + calcularGastoAseo() +
                calcularGastoVigilancia() + calcularGastoJardineria() + 
                calcularGastoExtra();
        return gasto;
    }
    
}
