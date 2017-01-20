/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author joser
 */
public class Listas {
    
    private LinkedList<Cliente> ListaCliente = new LinkedList<Cliente>();
    
    
    public LinkedList<Cliente> getListaCliente(){
        return ListaCliente;
    }
    
public void cargarClientes(){
        
        {
        Cliente cliente = new Cliente();
        
        String via;
        int cnt;
        File archivo = null;
        FileReader fr = null;
        BufferedReader buffer = null;
        
        try { 
            //Abriendo el archivo
            via = System.getProperty("user.dir")+"/src/Archivos/Cliente.txt";
            archivo = new File(via);
            fr = new FileReader(archivo);
            buffer = new BufferedReader(fr);
            
            /*
            Linea de datos en el Txt
            String cedula, 
            String nombre,
            String apellido
            String direccion, 
            String telefono,
            String fechaNacimiento, 
            String sexo
            */
            
            String linea;
            cnt = 0;
            
            while((linea = buffer.readLine())!=null){
                cnt++;
                
                switch(cnt){
                    case 1: cliente.setCedula(linea);
                    break;
                    case 2: cliente.setNombre(linea);
                    break;
                    case 3: cliente.setApellido(linea);
                    break;
                    case 4: cliente.setDireccion(linea);
                    break;
                    case 5: cliente.setTelefono(linea);
                    break;
                    case 6: cliente.setFechaNacimiento(linea);
                    break;
                    case 7: cliente.setSexo(linea);
                            ListaCliente.add(cliente);
                            cliente = new Cliente();
                            cnt = 0;
                    break;
                }
            }
        }
        
        catch(Exception e){
        } finally {
            // Aqui se cierra el fichero
            // Se cerrara si todo va bien
            // o si falta una excepcion
            try{
                if(null != fr){
                    fr.close();
                }
            }
            catch(Exception e){
            }
        }
      }
}
    
    public int existeCliente(String cedCli){
        int pos;
        Cliente cliente = new Cliente();
        String cedLista;
        
        for(pos = 0; pos < ListaCliente.size(); pos++){
            cliente = ListaCliente.get(pos);
            cedLista = cliente.getCedula();
            
            if(cedLista.equals(cedCli))
                return pos;
        }
        return -1;
    }
}

