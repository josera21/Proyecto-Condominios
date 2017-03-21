/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatronMemento;

import java.util.LinkedList;

/**
 *
 * @author joser
 */
public class SaveCliente {
    
    private LinkedList<MementoCliente> estado = new LinkedList<>();
    private static int index = 0;
    
    public void addMemento(MementoCliente meme) {
        estado.add(meme);
        index++;
    }
    
    public MementoCliente getMemento() {
        if(index > 0)
            index--;
        
        return estado.get(index);
    }
    
    public boolean vacio() {
        return estado.isEmpty();
    }
}
