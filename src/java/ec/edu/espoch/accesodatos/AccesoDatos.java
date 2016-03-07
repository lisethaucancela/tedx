/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.accesodatos;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author William
 */
public class AccesoDatos {
    //<editor-fold defaultstate="collapsed" desc="Funcion para ejecutar Procedimientos">
     /*
     metodo de que permite ejecutar una funcion con parametros
     */
    public static boolean ejecutaComando (String comando, ArrayList<Parametro> parametros) throws Exception{
     boolean respuesta = false;
        PreparedStatement prts = null;
        Connection con = null;
         try {
             Global global = new Global();
            //registro el driver
            Class.forName(global.getDRIVER());
            try {
                con = DriverManager.getConnection(global.getURL(), global.getUSER(), global.getPASS());
                //preparo el comando 
                prts = con.prepareStatement(comando);
                //mando mis parametos a la funcion 
                for (Parametro parm : parametros) {
                    prts.setObject(parm.getPosicion(), parm.getValor());

                }
                //ejecutando la sentencia
                int i = prts.executeUpdate();

                if (i > 0) {
                    respuesta = true;
                }

                prts.close();
                prts = null;

            } catch (SQLException exCon) {
                throw exCon;
            } finally {
                try {

                    if (con != null) {
                        //verifico si la conexion no nesta cerrada
                        if (!(con.isClosed())) {
                            con.close();
                        }
                        con = null;
                    }
                } catch (Exception ex) {
                    throw ex;
                }
            }

        } catch (ClassNotFoundException e) {
            throw e;
        }
        return respuesta;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Funcion para ejecutar consulta con resultados sin parametros">
    public static ConjuntoResultado ejecutaQuery(String query) throws Exception {

        ConjuntoResultado conj = new ConjuntoResultado();
        //El ResultSet contiene dentro los registros leidos de la base de datos
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = null;

        try {
           
            Global global = new Global();
            //global.getProperties();
            //registro el driver
            Class.forName(global.getDRIVER());

            try {
                //a clase DriverManager lleva seguimiento de los controladores disponibles y
                //maneja solicitudes de conexión entre controladores adecuados y bases de datos
                //o servidores de bases de datos. 
                //El parámetro url del método getConnection() es un URL de base de datos que especifica 
                //el subprotocolo (el mecanismo de conectividad de bases de datos), el identificador 
                //de la base de datos o del servidor de bases de datos y una lista de propiedades.*/

                con = DriverManager.getConnection(global.getURL(), global.getUSER(), global.getPASS());
        //Class.forName("org.postgresql.Driver");
                //try {
                //a clase DriverManager lleva seguimiento de los controladores disponibles y
                //maneja solicitudes de conexión entre controladores adecuados y bases de datos
                //o servidores de bases de datos. 
                //El parámetro url del método getConnection() es un URL de base de datos que especifica 
                //el subprotocolo (el mecanismo de conectividad de bases de datos), el identificador 
                //de la base de datos o del servidor de bases de datos y una lista de propiedades.

              //  //con = DriverManager.getConnection(global.getURL(),global.getUSER(),global.getPASS());
                // con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hojadevida",
                //          "postgres","Admin123456");
                //Clase que representa una sentencia precompilada de SQL 
                pst = con.prepareStatement(query);
                rs = pst.executeQuery();

                conj.Fill(rs);
                rs.close();
                pst.close();
                rs = null;
                pst = null;

            } catch (SQLDataException exSQL) {
                throw exSQL;
            } finally {
                try {

                    if (con != null) {
                        //verifico si la conexion no nesta cerrada
                        if (!(con.isClosed())) {
                            con.close();
                        }
                        con = null;
                    }
                } catch (Exception ex) {
                    throw ex;
                }
            }

        } catch (ClassNotFoundException e) {
            throw e;
        }

        return conj;

    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Funcion para ejecutar consulta con resultados conparametros">
    public static ConjuntoResultado ejecutaQuery(String query, ArrayList<Parametro> parametros) throws Exception {

        ConjuntoResultado conj = new ConjuntoResultado();
        //El ResultSet contiene dentro los registros leidos de la base de datos
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = null;

        try {
            Global global = new Global();
            //registro el driver
            Class.forName(global.getDRIVER());

            //registro el driver
            try {
                //a clase DriverManager lleva seguimiento de los controladores disponibles y
                //maneja solicitudes de conexión entre controladores adecuados y bases de datos
                //o servidores de bases de datos. 
                //El parámetro url del método getConnection() es un URL de base de datos que especifica 
                //el subprotocolo (el mecanismo de conectividad de bases de datos), el identificador 
                //de la base de datos o del servidor de bases de datos y una lista de propiedades.

                con = DriverManager.getConnection(global.getURL(), global.getUSER(), global.getPASS());

                pst = con.prepareStatement(query);
                for (Parametro param : parametros) {
                    pst.setObject(param.getPosicion(), param.getValor());
                }

                rs = pst.executeQuery();
                conj.Fill(rs);
                rs.close();
                pst.close();
                rs = null;
                pst = null;

            } catch (SQLDataException exSQL) {
                throw exSQL;
            } finally {
                try {

                    if (con != null) {
                        //verifico si la conexion no nesta cerrada
                        if (!(con.isClosed())) {
                            con.close();
                        }
                        con = null;
                    }
                } catch (Exception ex) {
                    ex.getMessage();
                }
            }

        } catch (ClassNotFoundException e) {
            throw e;
        }

        return conj;

    }

//</editor-fold>  
}
