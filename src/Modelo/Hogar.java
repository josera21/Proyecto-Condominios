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
public class Hogar {
    
    protected int nroBanos, nroCuartos;
    protected boolean amueblada;
    protected String color;
    
    public Hogar(int banos, int cuartos, boolean amueblada, String color){
        super();
        this.nroBanos = banos;
        this.nroCuartos = cuartos;
        this.amueblada = amueblada;
        this.color = color;
    }
    
    public Hogar(){
        
    }
    
    public void setBanios(int banos){
        this.nroBanos = banos;
    }
    
    public int getBanios(){
        return nroBanos;
    }
    
    public void setCuartos(int cuartos){
        this.nroCuartos = cuartos;
    }
    
    public int getCuartos(){
        return nroCuartos;
    }
    
    public void setAmueblada(boolean amu){
        this.amueblada = amu;
    }
    
    public boolean getAmueblada(){
        return amueblada;
    }
    
    public void setColor(String color){
        this.color = color;
    }
    
    public String getColor(){
        return color;
    }
}
