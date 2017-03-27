/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBean;

import JavaBean.BotonInternet;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

/**
 * expone expone explicitamente las caracteristicas del bean
 * @author Yamelis Calderon
 */
public class BotonInternetBeanInfo  extends SimpleBeanInfo
{
       private Class beanClass = BotonInternet.class;
       
       public BotonInternetBeanInfo() 
       {
            super();
            
        }     

       //Metodo que devuelve los descriptores para cada propiedad,caracteristicas del bean   
       public PropertyDescriptor[] getPropertyDescriptors() 
             {
		    try  {
		      PropertyDescriptor link = new PropertyDescriptor("link", beanClass, "getlink", "setlink");
		      
		    
		           
		      PropertyDescriptor[] pds = new PropertyDescriptor[] 
                      {
		        link,
		        
		      };
		      return pds;
		    }
		    catch (IntrospectionException ex) {
		      ex.printStackTrace();
		      return null;
		    }
		}	  
	  
}
