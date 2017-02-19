/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Librerias;

import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author joser
 */
public class Validaciones {
    
    public static void Aviso(String Mensaje,String CaptionWin) {

      JOptionPane.showMessageDialog(null,
      Mensaje,
      CaptionWin,
      JOptionPane.WARNING_MESSAGE);
    }
     
//-----------------------------------     
public static String getFechaActual() 
{
      Date ahora = new Date();
      SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
      return formateador.format(ahora);
}          
//--------------------------------
public static boolean isDate(String fechax)
{
    try {
      SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
      formatoFecha.setLenient(false);
      formatoFecha.parse(fechax);
      return true;
    } catch (ParseException e) 
     {
      return false;
     }  
}     
//----------------------------------
public static Date CnvStringFecha(String fecha)
{
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    Date fechaDate = null;
    try {
        fechaDate = formato.parse(fecha);
    }
    catch (ParseException ex)
    {
        System.out.println(ex);
    }
    return fechaDate;
}
//---------------------------------
public static int DialogConfirm(String Pregunta,String Titulo) 
 {              
   int respuesta = JOptionPane.showConfirmDialog(null,
                                                 Pregunta,
                                                 Titulo,
                                                 JOptionPane.YES_NO_OPTION,     //Botones que apareceran                       
                                                 JOptionPane.QUESTION_MESSAGE); //Icono que se desea que aparezca
      
      return respuesta;
    }
//----------------------------------
public static int Opciones(String Op1,String Op2,String Op3, String CaptionWin)
{        
  int optionType = JOptionPane.DEFAULT_OPTION;
 int messageType = JOptionPane.PLAIN_MESSAGE; // no standard icon

Object[] selValues = { Op1, Op2, Op3 };

// Shows message, choices appear as buttons
int res = JOptionPane.showOptionDialog(null, "Selecciona una Opcion", CaptionWin,
                                       optionType, messageType,null ,
                                       selValues, selValues[0]);

return res;
}    


public static int DosOpciones(String Op1, String Op2, String CaptionWin) 
{
    int optionType = JOptionPane.DEFAULT_OPTION;
    int messageType = JOptionPane.QUESTION_MESSAGE;
    Object[] selValues = {Op1, Op2};
    
    int res = JOptionPane.showOptionDialog(null, "Seleccione una Opcion", CaptionWin, 
                                            optionType, messageType, null, 
                                            selValues, selValues[0]);
    
    return res;
}

//-------------------------------
public static int Generar_Aleatorio(int limite) 
      { 
         return (int) (Math.random()*limite+1)-1;
      }
//----------------------------------------
public static int Generar_Aleatorio_Interevalo(int min, int max) {
    return ThreadLocalRandom.current().nextInt(min, max + 1);
}

public static Date sumarFechasDias(Date fch, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, dias);
        return new java.sql.Date(cal.getTimeInMillis());
    } 
//-----------------------------------------
public static String Apost(String Texto)
{
  return "'"+Texto+"'";
}       
   
// AGREGANDO MAS VALIDACIONES

public static void ValidarSoloNumeros(KeyEvent e, String cadena){
    
    char digito;
    
    if(cadena.length() == 8){
        e.consume();
    }
    
    digito = e.getKeyChar();
    if(digito <'0' || digito > '9')
        e.consume();
    
}

public static void ValidarSoloLetras(KeyEvent e){
    
    char c = e.getKeyChar();
     
    if(Character.isDigit(c)){
         e.consume();
    }
}

public static boolean ValidarCamposVacios(JTextField... jText) {
    for (JTextField textField : jText) {
        if (textField.getText().isEmpty()) {
            return true;
        }
    }
    return false;
}

}
