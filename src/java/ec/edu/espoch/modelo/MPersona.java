/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.modelo;

import ec.edu.espoch.accesodatos.AccesoDatos;
import ec.edu.espoch.accesodatos.ConjuntoResultado;
import ec.edu.espoch.accesodatos.Parametro;
import ec.edu.espoch.entidades.CComentario;
import ec.edu.espoch.entidades.CCuenta;
import ec.edu.espoch.entidades.CPersona;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author William
 */
public class MPersona {

    public static boolean insertarPersona(CPersona persona) throws Exception {
        boolean respuesta = false;

        try {
            ArrayList<Parametro> lstP = new ArrayList<>();
            String sql = "SELECT * from tedx.fn_insert_tpersona(?,?,?,?,?,?)";
            lstP.add(new Parametro(1, persona.getCedula()));
            lstP.add(new Parametro(2, persona.getNombre()));
            lstP.add(new Parametro(3, persona.getApellido()));
            lstP.add(new Parametro(4, persona.getTitulo()));
            lstP.add(new Parametro(5, "tedx@espoch.edu.ec"));
            lstP.add(new Parametro(6, persona.getObjSexo().getTsexoId()));

            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            while (rs.next()) {
                if (rs.getBoolean(0) == true) {
                    respuesta = true;
                }
            }
            rs = null;
        } catch (SQLException e) {
            System.out.println("error" + e.getMessage());
        }
        return respuesta;

    }

    public static boolean insertarComentario(CComentario comentario, CPersona objId) throws Exception {
        boolean respuesta = false;
        CComentario objIdCom = new CComentario();

        try {

            objIdCom = obtComentario(objId.getCedula());
            ArrayList<Parametro> lstP = new ArrayList<>();
            String sql = "SELECT * from tedx.fn_insert_tcomentario(?,?)";
            lstP.add(new Parametro(1, comentario.getDescripcioncomentario()));
            lstP.add(new Parametro(2, objIdCom.getIdpersonacomentario()));

            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            while (rs.next()) {
                if (rs.getBoolean(0) == true) {
                    respuesta = true;
                }
            }
            rs = null;
        } catch (SQLException e) {
            System.out.println("error" + e.getMessage());
        }
        return respuesta;

    }

    

    public static boolean insertarCuenta(String usuario, CPersona objId) throws Exception {
        boolean respuesta = false;
        CComentario objIdCom = new CComentario();
        int aux = 2;
        String dia,mes,annio, fecha;
        //calendario
        Date fecha1;
        Calendar c2 = new GregorianCalendar();

        dia = Integer.toString(c2.get(Calendar.DATE));
        mes = Integer.toString(c2.get(Calendar.MONTH));
        annio = Integer.toString(c2.get(Calendar.YEAR));
        
        
        
        java.sql.Date hoy=java.sql.Date.valueOf(annio+"-"+mes+"-"+dia);
        //********

        try {

            objIdCom = obtComentario(objId.getCedula());
            ArrayList<Parametro> lstP = new ArrayList<>();
            String sql = "SELECT * from tedx.fn_insert_tcuenta(?,?,?,?,?)";
            lstP.add(new Parametro(1, usuario));
            lstP.add(new Parametro(2, "tedx123"));
            lstP.add(new Parametro(3, aux));
            lstP.add(new Parametro(4, objIdCom.getIdpersonacomentario()));
            lstP.add(new Parametro(5, hoy));

            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            while (rs.next()) {
                if (rs.getBoolean(0) == true) {
                    respuesta = true;
                }
            }
            rs = null;
        } catch (SQLException e) {
            System.out.println("error" + e.getMessage());
        }
        return respuesta;

    }
    
    public static CComentario obtComentario(String cedula) throws Exception {
        CComentario comentario = null;
        try {
            String sql = "select tpersonaid from tedx.tpersona where tpersonacedula='" + cedula + "'";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            while (rs.next()) {
                comentario = new CComentario(rs.getInt(0));

            }

        } catch (SQLException e) {
            throw e;
        }
        return comentario;
    }
    
    public static boolean actualizarPersona(CPersona persona) throws Exception {
        boolean respuesta = false;
        int aux=49;
        try {
            ArrayList<Parametro> lstP = new ArrayList<>();
            String sql = "SELECT * from tedx.fn_update_tpersonacorreo(?,?);";
            lstP.add(new Parametro(1, 49 ));
            lstP.add(new Parametro(2, persona.getEmail()));

            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            while (rs.next()) {
                if (rs.getBoolean(0) == true) {
                    respuesta = true;
                }
            }
            rs = null;
        } catch (SQLException e) {
            System.out.println("error" + e.getMessage());
        }
        return respuesta;
    }
    
   
            
            public static boolean actualizarPassword(CCuenta cuenta) throws Exception {
        boolean respuesta = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<>();
            String sql = "SELECT * from  tedx.fn_update_tcuentapassword(?,?,?);";
            lstP.add(new Parametro(1, 1));
            lstP.add(new Parametro(2, cuenta.getContrasenia()));
            lstP.add(new Parametro(3, 3));

            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            while (rs.next()) {
                if (rs.getBoolean(0) == true) {
                    respuesta = true;
                }
            }
            rs = null;
        } catch (SQLException e) {
            System.out.println("error" + e.getMessage());
        }
        return respuesta;
    }
}
