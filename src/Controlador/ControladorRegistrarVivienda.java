/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Librerias.Validaciones;
import DAO.DaoCliente;
import Vista.jVivienda;
import Modelo.ViviendaCalle;
import Modelo.ViviendaEdificio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import DAO.DaoVivienda;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author josera
 */
public class ControladorRegistrarVivienda implements ActionListener, KeyListener,ItemListener {
    
    private jVivienda formCasa;
    
    public ControladorRegistrarVivienda() {
        formCasa = new jVivienda();
        formCasa.agregarListener(this);
        formCasa.setVisible(true);
        formCasa.setResizable(false);
        formCasa.setLocationRelativeTo(null);
        formCasa.agregarListener(this);
        LlenarComboBuscar();
        enabled(false);
        
        formCasa.getjComboBoxCalle().addItemListener((ItemEvent e) -> {
            if(e.getStateChange()==ItemEvent.SELECTED)
            {
                if(formCasa.getjComboBoxUrbanizacion().getSelectedItem().toString()!="Seleccione")
                {
                    String C = null;
                try {
                    C = Generar_Codigo_Vivienda_Calle(formCasa.getjComboBoxUrbanizacion().getSelectedItem().toString().trim(),
                            formCasa.getjComboBoxCalle().getSelectedItem().toString().trim());
                    formCasa.getjTextfieldIDPropietario().setEnabled(true);
                    formCasa.getjTextFieldCodigo().setText(C);
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorRegistrarVivienda.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                if(formCasa.getjComboBoxCalle().getSelectedItem().toString()=="Seleccione")
                {
                     formCasa.getjTextFieldCodigo().setText("");
                     formCasa.getjTextfieldIDPropietario().setEnabled(false);
                }
                
            }
        });

        formCasa.getjComboBoxPiso().addItemListener(((ItemEvent e) -> {
            if(formCasa.getjComboBoxEdificio().getSelectedItem().toString()!="Selecceione" &&
               formCasa.getjComboBoxUrbanizacion().getSelectedItem().toString()!="Seleccione")
            {
               String C = null;
                try {
                    C = Generar_Codigo_Vivienda_Edificio(formCasa.getjComboBoxUrbanizacion().getSelectedItem().toString().trim(),
                                                         formCasa.getjComboBoxEdificio().getSelectedItem().toString().trim(),
                                                         formCasa.getjComboBoxPiso().getSelectedItem().toString().trim());
                    formCasa.getjTextfieldIDPropietario().setEnabled(true);
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorRegistrarVivienda.class.getName()).log(Level.SEVERE, null, ex);
                }
                formCasa.getjTextFieldCodigo().setText(C);
            }
            if(formCasa.getjComboBoxPiso().getSelectedItem().toString()=="Seleccione")
            {
                formCasa.getjTextFieldCodigo().setText("");
                formCasa.getjTextfieldIDPropietario().setEnabled(false);
            }
        }));
        
        formCasa.getjComboBoxEdificio().addItemListener(((ItemEvent e) -> {
            if(e.getStateChange()==ItemEvent.SELECTED)
            {
                if(formCasa.getjComboBoxEdificio().getSelectedItem().toString()!="Seleccione")
                {
                    formCasa.getjComboBoxPiso().setEnabled(true);
                    if(formCasa.getjComboBoxPiso().getItemCount()==1)
                    {
                        Llenar_Combo_Pisos();
                    }
                    else
                    {
                        formCasa.getjComboBoxPiso().removeAllItems();
                        formCasa.getjComboBoxPiso().addItem("Selecione");
                        Llenar_Combo_Pisos();
                    }
                }
                else
                {
                    formCasa.getjComboBoxPiso().setEnabled(false);
                    formCasa.getjComboBoxPiso().setSelectedIndex(0);
                }
            }
        }));
        
        formCasa.getjComboBoxTipo().addItemListener((ItemEvent e)->{
            if(e.getStateChange()==ItemEvent.SELECTED)
            {
                if(formCasa.getjComboBoxTipo().getSelectedItem().toString()=="Seleccione")
                {
                    formCasa.getjComboBoxUrbanizacion().setSelectedIndex(0);
                    formCasa.getjComboBoxUrbanizacion().setEnabled(false);
                }
                else
                {
                    try {
                        formCasa.getjComboBoxUrbanizacion().setEnabled(true);
                        if(formCasa.getjComboBoxUrbanizacion().getItemCount()==1)
                        {
                        llenarComboUrbanizacion();
                        }
                        else
                        {
                            formCasa.getjComboBoxUrbanizacion().removeAllItems();
                            formCasa.getjComboBoxUrbanizacion().addItem("Seleccione");
                            llenarComboUrbanizacion();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorRegistrarVivienda.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        });
        formCasa.getjComboBoxUrbanizacion().addItemListener(((ItemEvent e) -> {
             if(e.getStateChange()==ItemEvent.SELECTED)
            {
                DaoVivienda dv=new DaoVivienda();
                ResultSet rs=null;
                if(formCasa.getjComboBoxUrbanizacion().getSelectedItem().toString()!="Seleccione")
                {
                    if((formCasa.getjComboBoxTipo().getSelectedItem().toString()=="CASA"))
                    {
                        formCasa.getjComboBoxCalle().setEnabled(true);
                        formCasa.getjComboBoxCalle().setSelectedIndex(0);
                         try {
                              if(formCasa.getjComboBoxCalle().getItemCount()>=2)
                             {
                                 formCasa.getjComboBoxCalle().removeAllItems();
                                 formCasa.getjComboBoxCalle().addItem("Seleccione");
                                 llenarComboCalle(formCasa.getjComboBoxUrbanizacion().getSelectedItem().toString());
                             }
                             else
                             {
                                 llenarComboCalle(formCasa.getjComboBoxUrbanizacion().getSelectedItem().toString());
                             }
                             
                        } catch (SQLException ex) {
                            Logger.getLogger(ControladorRegistrarVivienda.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if((formCasa.getjComboBoxTipo().getSelectedItem().toString()=="APTO"))
                    {
                        formCasa.getjComboBoxEdificio().setEnabled(true);
                        formCasa.getjComboBoxEdificio().setSelectedIndex(0);
                         try {
                             if(formCasa.getjComboBoxEdificio().getItemCount()>=2)
                             {
                                formCasa.getjComboBoxEdificio().removeAllItems();
                                formCasa.getjComboBoxEdificio().addItem("Seleccione");
                                llenarComboEdificios(formCasa.getjComboBoxUrbanizacion().getSelectedItem().toString());
                             }
                             else
                             {
                                 llenarComboEdificios(formCasa.getjComboBoxUrbanizacion().getSelectedItem().toString());
                             }
                                             
                        } catch (SQLException ex) {
                            Logger.getLogger(ControladorRegistrarVivienda.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                }
                else
                {
                    formCasa.getjComboBoxCalle().setEnabled(false);
                    formCasa.getjComboBoxCalle().setSelectedIndex(0);
                    formCasa.getjComboBoxEdificio().setEnabled(false);
                    formCasa.getjComboBoxEdificio().setSelectedIndex(0);
                }
            }
        }));
        
        formCasa.getjTextFieldCodigo().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                Validaciones.ValidarSoloLetras(e);
                try {
                    codigoKeyPressed(e);
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorRegistrarVivienda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        formCasa.getjTextfieldIDPropietario().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e)
            {
                idPropietarioKeyPressed(e);
            }
            @Override
            public void keyTyped(KeyEvent e){
                Validaciones.ValidarSoloNumeros(e, formCasa.getjTextfieldIDPropietario().getText());
            }
        });
        
        formCasa.getjTextFieldHabitaciones().addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyTyped(KeyEvent e){
                Validaciones.ValidarSoloNumeros(e, formCasa.getjTextFieldHabitaciones().getText());
            }
            
        });
        
        formCasa.getjTextFieldBannios().addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyTyped(KeyEvent e){
                Validaciones.ValidarSoloNumeros(e, formCasa.getjTextFieldBannios().getText());
            }
            
        });
        
        formCasa.getjTextFieldTelefono().addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyTyped(KeyEvent e){
                Validaciones.ValidarSoloNumeros(e, formCasa.getjTextFieldTelefono().getText());
            }
            
        });
    }
    private void idPropietarioKeyPressed(KeyEvent key) 
    {
            String cadena =formCasa.getjTextfieldIDPropietario().getText().trim();
            ResultSet rs;
      if(key.getKeyChar()==10 && cadena.length()==8)
      {
        try {             
            DaoCliente dc=new DaoCliente();
            rs=dc.buscarCliente(cadena);
            rs.next();
            int fila=rs.getRow();
            if(fila==1)
            {
                formCasa.getjTextFieldBannios().setEditable(true);
              
                formCasa.getjTextFieldTelefono().setEditable(true);
                formCasa.getjTextFieldHabitaciones().setEditable(true);
            }
            else
            {
                JOptionPane.showMessageDialog(formCasa,"Propietario no encontrado",
                                                  "Registro Vivienda",JOptionPane.INFORMATION_MESSAGE);
            }
        
        } catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(formCasa,ex.getMessage(),
                                                  "Registro Vivienda",JOptionPane.WARNING_MESSAGE);
        } 
      }
    }
    public void enabled(boolean status) {
      
        formCasa.getjTextFieldHabitaciones().setEditable(status);
        formCasa.getjTextFieldBannios().setEditable(status);
        formCasa.getjTextFieldTelefono().setEditable(status);
        formCasa.getjComboBoxTipo().setEditable(status);
    }
    
    private void codigoKeyPressed(KeyEvent key) throws SQLException {
        
        String cadena;
        
        cadena = formCasa.getjTextFieldCodigo().getText().trim();
        
        if(key.getKeyChar()==10 && cadena.length()==8) {
            
        }
        
    }
    
    private void guardar() throws SQLException 
    {
        DaoVivienda dv=new DaoVivienda();

            if(formCasa.getjComboBoxTipo().getSelectedItem().toString()=="CASA")
            {
                ViviendaCalle vc=new ViviendaCalle(formCasa.getjTextFieldCodigo().getText().trim(),
                                                Integer.parseInt(formCasa.getjTextFieldHabitaciones().getText().trim()),
                                                Integer.parseInt(formCasa.getjTextFieldBannios().getText().trim()), 
                                                formCasa.getjComboBoxTipo().getSelectedItem().toString().trim(),
                                                formCasa.getjTextFieldTelefono().getText().trim(),
                                                formCasa.getjComboBoxCalle().getSelectedItem().toString().trim());
                vc.getUrb().setIdUrbanizacion(formCasa.getjComboBoxUrbanizacion().getSelectedItem().toString().trim());
                vc.getCli().setCedula(formCasa.getjTextfieldIDPropietario().getText().trim());
                dv.insertarVivienda(vc);
                dv.insertarViviendaCalle(vc);
                JOptionPane.showMessageDialog(formCasa, "REGISTRO EXITOSO!!!","Registro de Vivienda",JOptionPane.INFORMATION_MESSAGE);
            }
            if(formCasa.getjComboBoxTipo().getSelectedItem().toString()=="APTO")
            {

                    String Piso=formCasa.getjComboBoxPiso().getSelectedItem().toString().trim();
                    if(Piso=="PB")
                    {
                        Piso="00";
                    }
                
                ViviendaEdificio ve=new ViviendaEdificio(formCasa.getjTextFieldCodigo().getText().trim(),
                                                     Integer.parseInt(formCasa.getjTextFieldHabitaciones().getText().trim()),
                                                     Integer.parseInt(formCasa.getjTextFieldBannios().getText().trim()),
                                                     formCasa.getjComboBoxTipo().getSelectedItem().toString().trim(),
                                                     formCasa.getjTextFieldTelefono().getText(),
                                                     Integer.parseInt(formCasa.getjComboBoxPiso().getSelectedItem().toString().trim()));
                ve.getCli().setCedula(formCasa.getjTextfieldIDPropietario().getText().trim());
                ve.getUrb().setIdUrbanizacion(formCasa.getjComboBoxUrbanizacion().getSelectedItem().toString().trim());
                dv.insertarVivienda(ve);
                dv.insertarVivienda(ve);
                JOptionPane.showMessageDialog(formCasa, "REGISTRO EXITOSO!!!","Registro de Vivienda",JOptionPane.INFORMATION_MESSAGE);
            }
    }
    
    private void eliminar() throws SQLException {
        
    }
    
    private void cancelar() {
        formCasa.getjTextFieldCodigo().setText("");

        formCasa.getjTextFieldHabitaciones().setText("");
        formCasa.getjTextFieldBannios().setText("");
        formCasa.getjTextFieldTelefono().setText("");
        formCasa.getjTextfieldIDPropietario().setText("");
        formCasa.getjTextfieldIDPropietario().setEnabled(false);
        
        formCasa.getjComboBoxTipo().setSelectedIndex(0);
        formCasa.getjComboBoxCalle().setSelectedIndex(0);
        formCasa.getjComboBoxEdificio().setSelectedIndex(0);
        formCasa.getjComboBoxUrbanizacion().setSelectedIndex(0);
        
        formCasa.getjComboBoxCalle().setEnabled(false);
        formCasa.getjComboBoxEdificio().setEnabled(false);
        formCasa.getjComboBoxUrbanizacion().setEnabled(false);
    }
    
    public void Llenar_Combo_Pisos()
    {
        DaoVivienda dv=new DaoVivienda();
        ResultSet rs=null;
        String Piso = null;
        int pisoNum=0;
        try {
            rs=dv.Consultar_Piso_Nro_Apto(formCasa.getjComboBoxEdificio().getSelectedItem().toString());
            rs.next();
            if(rs!=null)
            {
                Piso=rs.getString(2).trim();
                pisoNum=Integer.parseInt(Piso);
            }
                for(int i=0;i<pisoNum;i++)
                {
                    if(i==0)
                    {
                       formCasa.getjComboBoxPiso().addItem("PB"); 
                    }
                    else
                    {
                        if(i<=9)
                        {
                        formCasa.getjComboBoxPiso().addItem("0"+Integer.toString(i));
                        }
                        else
                        {
                            formCasa.getjComboBoxPiso().addItem(Integer.toString(i));
                        }
                    }
                }

        } catch (SQLException ex) {
            Logger.getLogger(ControladorRegistrarVivienda.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
        
    }
   
    public String Generar_Codigo_Vivienda_Calle(String Urbanizacion,String Edificio_Calle) throws SQLException
    {
        String rs=null;
        String Codigo_Vivienda=null;
        DaoVivienda dv=new DaoVivienda();
        rs=dv.Consultar_Ultimo_Codigo_Vivienda_Calle(Urbanizacion.substring(3),Edificio_Calle.substring(6));
        
          if(rs=="")
          {
            Codigo_Vivienda = "CASA" + "-" + Urbanizacion.substring(3) + "-" + 
                              Edificio_Calle.substring(6).trim()+ "-000";
          }
          else           
          {
            int CodNum=Integer.parseInt(rs);
            if(CodNum>=0 && CodNum<=8)
            {
                Codigo_Vivienda="CASA" + "-" + Urbanizacion.substring(3)+"-" +
                                Edificio_Calle.substring(6).trim()+"-00" + (CodNum+1);
            }
            if(CodNum>=9 && CodNum<=98)
            {
                Codigo_Vivienda="CASA" + "-" + Urbanizacion.substring(3) + "-" +
                                Edificio_Calle.substring(6).trim()+ "-0" + (CodNum+1);
            }
            if(CodNum>=99 && CodNum<=998)
            {
                Codigo_Vivienda="CASA" + "-" + Urbanizacion.substring(3) + "-" +
                                Edificio_Calle.substring(6).trim()+ "-" + (CodNum+1);
            }
          }
            return Codigo_Vivienda;
      }
  
   
    public String Generar_Codigo_Vivienda_Edificio(String Urbanizacion,String Edificio_Calle,String Piso) throws SQLException
    {
        
        String rs=null;
        String Codigo_Vivienda=null;
        DaoVivienda dv=new DaoVivienda();
        if(Piso=="PB")
        {
            Piso="00";
        }
         
               rs=dv.Consultar_Ultimo_Codigo_Vivienda_Edificio(Urbanizacion.substring(3),Edificio_Calle.substring(6),Piso);
               
               if(rs=="") //Varifica Si ya Existen Apartamentos Previamenet Guardados
                {
                    //Asigna Un Codigo Inicial del Primer APartamento Agregado
                    Codigo_Vivienda = "APTO" + "-" + Urbanizacion.substring(3) + "-" + 
                                      Edificio_Calle.substring(6).trim()+"-"+Piso+ "-000";
                    
                }
                else           
                {
                        int CodNum=Integer.parseInt(rs);
                        if(CodNum>=0 && CodNum<=8)
                        {
                            Codigo_Vivienda="APTO" + "-" + Urbanizacion.substring(3) + "-" +
                                            Edificio_Calle.substring(6).trim()+"-"+Piso+ "-00" + (CodNum+1);
                        }
                        if(CodNum>=9 && CodNum<=98)
                        {
                            Codigo_Vivienda="APTO" + "-" + Urbanizacion.substring(3) + "-" +
                                            Edificio_Calle.substring(6).trim() +"-"+Piso+ "-0" + (CodNum+1);
                        }
                        if(CodNum>=99 && CodNum<=998)
                        {
                            Codigo_Vivienda="APTO" + "-" + Urbanizacion.substring(3) + "-" +
                                            Edificio_Calle.substring(6).trim() +"-"+Piso+ "-" + (CodNum+1);
                        }                        
                }
            return Codigo_Vivienda;
   }
    
    //Este metodo Llna el Combobox de las ids de Todas las Urbanizaciones
    public void llenarComboUrbanizacion() throws SQLException
    {
        DaoVivienda dv=new DaoVivienda();
        ResultSet rs;
        try 
        {  
            rs=dv.Consultar_Codigos_Urbanizaciones();
            if(formCasa.getjComboBoxUrbanizacion().getItemCount()==1)
            {
               while(rs.next())
                {
                    formCasa.getjComboBoxUrbanizacion().addItem(rs.getString("ID_Urbanizacion").trim());
                } 
            }
            else
            {
                for(int i=1;i<=formCasa.getjComboBoxUrbanizacion().getItemCount()-1;i++)
                {
                    formCasa.getjComboBoxUrbanizacion().removeItemAt(i);
                }
                while(rs.next())
                {
                    formCasa.getjComboBoxUrbanizacion().addItem(rs.getString("ID_Urbanizacion"));
                }
            }
            
        } catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(formCasa, e.getMessage());
        } 
    }
    //Este metodo Llna el Combobox de las Ids de las Calles por cada Urbanizacion
    public void llenarComboCalle(String Urbanizacion) throws SQLException
    {
        DaoVivienda dv=new DaoVivienda();
        
        ResultSet rs=null;
        try 
        {   
                rs=dv.Cosultar_Calles_Urbanizacion(Urbanizacion);
                
               
                    while(rs.next())
                    {          
                        formCasa.getjComboBoxCalle().addItem(rs.getString("ID")); 
                    }
        }
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(formCasa, e.getMessage(),"Registro de Vivienda",JOptionPane.WARNING_MESSAGE);
        }
    }
     //Este metodo Llna el Combobox de las Ids de los Edificios por cada Urbanizacion
    public void llenarComboEdificios(String Urbanizacion) throws SQLException
    { 
        DaoVivienda dv=new DaoVivienda();
        ResultSet rs=null;
        try 
        {   
            rs=dv.Consultar_Edificios_Urbanizacion(Urbanizacion);
            while(rs.next())
            { 
                formCasa.getjComboBoxEdificio().addItem(rs.getString("ID")); 
            }      
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(formCasa, e.getMessage(),"Registro de Vivienda",JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(formCasa.getjButtonGuardar())){
            try 
            {
                guardar();
                return;
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(ControladorRegistrarVivienda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(e.getSource().equals(formCasa.getjButtonEliminar())){
            try {
                eliminar();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorRegistrarVivienda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(e.getSource().equals(formCasa.getjButtonCancelar())){
            cancelar();
        }
        
        if(e.getSource().equals(formCasa.getjButtonSalir())){
            new ControladorPrincipal();
            formCasa.dispose();
        }
       
    }
    public void LlenarComboBuscar() 
    {
        try {
            DaoVivienda dv =new DaoVivienda();
            ResultSet rs =dv.ConsultarViviendas();
            while(rs.next())
            {
                formCasa.getjComboboxBuscarCodigo().addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorRegistrarVivienda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}


