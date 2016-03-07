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
import ec.edu.espoch.entidades.CPersona;
import ec.edu.espoch.entidades.CPersonaPeriodo;
import ec.edu.espoch.entidades.CUsuario;
import static ec.edu.espoch.modelo.MPersona.obtComentario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author William
 */
public class MUsuario {

    public static boolean insertarUsuario(CUsuario usuario) throws Exception {
        boolean respuesta = false;

        try {
            ArrayList<Parametro> lstP = new ArrayList<>();
            String sql = "SELECT * from tedx.fn_insert_tpersona(?,?,?,?,?)";
            lstP.add(new Parametro(1, usuario.getNombre()));
            lstP.add(new Parametro(2, usuario.getApellido()));
            lstP.add(new Parametro(3, usuario.getCedula()));
            lstP.add(new Parametro(4, usuario.getEmail()));
            lstP.add(new Parametro(5, usuario.getCiudad()));
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

    public static boolean existeCedula(String cedula) throws Exception {
        try {
            String sql = "SELECT count( tpersona.tpersonacedula)FROM tedx.tpersona\n"
                    + "WHERE tpersona.tpersonacedula = '" + cedula + "';";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            while (rs.next()) {
                if (rs.getInt(0) > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println("error" + e.getMessage());
        }
        return true;
    }

    public static CPersonaPeriodo obtIdUsuario(String cedula) throws Exception {
        CPersonaPeriodo idUsuario = null;
        try {
            String sql = "select tpersonaid from tedx.tpersona where tpersonacedula='" + cedula + "'";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            while (rs.next()) {
                idUsuario = new CPersonaPeriodo(rs.getInt(0));

            }

        } catch (SQLException e) {
            throw e;
        }
        return idUsuario;
    }

    public static boolean insertarPersonaPeriodo(CPersonaPeriodo objPerPeriodo, CUsuario objUsuario) throws Exception {
        boolean respuesta = false;
        int aux = 1;
        int aux1 = 1;
        CPersonaPeriodo obj = new CPersonaPeriodo();
        //captura fecha del sistema
        String dia, mes, annio, str;
        Calendar c2 = new GregorianCalendar();
        dia = Integer.toString(c2.get(Calendar.DATE));
        mes = Integer.toString(c2.get(Calendar.MONTH) + 1);
        annio = Integer.toString(c2.get(Calendar.YEAR));
        //java.sql.Date hoy =java.sql.Date.valueOf(annio+"-"+mes+"-"+dia); // este codigo es para enviar con el tipo date a la base de datos
        str = "" + annio + "-" + mes + "-" + dia;
        //+++++++++++++++++++++
        try {
            obj = obtIdUsuario(objUsuario.getCedula());
            aux = obj.getTpersonaid();
            ArrayList<Parametro> lstP = new ArrayList<>();
            String sql = "SELECT * from tedx.fn_insert_tpersonaperiodo(?,?,?,?,?,?)";
            lstP.add(new Parametro(1, aux));
            lstP.add(new Parametro(2, aux1));
            lstP.add(new Parametro(3, objPerPeriodo.getTpersonaperiodocomentario()));
            lstP.add(new Parametro(4, str));
            lstP.add(new Parametro(5, objPerPeriodo.getTpersonaperiodopensamiento()));
            lstP.add(new Parametro(6, aux1));

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

    public static CUsuario obtUsuario(int codigo) throws Exception {

        CUsuario objUsuario = null;
        try {
            ArrayList<Parametro> lstP = new ArrayList<>();
            String sql = "SELECT * from tedx.fn_select_obtpersona(?)";
            lstP.add(new Parametro(1, codigo));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            while (rs.next()) {
                objUsuario = new CUsuario(rs.getInt(0),
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
            }
        } catch (SQLException e) {
            throw e;
        }
        return objUsuario;
    }

    public static boolean updateUsuario(CUsuario usuario) throws Exception {
        boolean respuesta = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<>();
            String sql = "SELECT * from tedx.fn_update_tpersona(?,?,?,?,?,?)";
            
            lstP.add(new Parametro(1, usuario.getNombre()));
            lstP.add(new Parametro(2, usuario.getApellido()));
            lstP.add(new Parametro(3, usuario.getCedula()));
            lstP.add(new Parametro(4, usuario.getEmail()));
            lstP.add(new Parametro(5, usuario.getCiudad()));
            lstP.add(new Parametro(6, usuario.getCiudad()));
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
