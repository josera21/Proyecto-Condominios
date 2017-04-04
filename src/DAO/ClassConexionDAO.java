/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author joser
 */
public class ClassConexionDAO {
    
    public ClassConexionDAO() {
        super();
        PackageConeccion.ConeccionBD.establecerPropiedadesConeccion("BDProperties", 
                                    "jdbc.driver", "jdbc.url", "jdbc.nombrebd", 
                                         "jdbc.usuario", "jdbc.password");
    }
}
