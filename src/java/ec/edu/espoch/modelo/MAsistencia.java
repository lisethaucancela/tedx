/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.modelo;

import ec.edu.espoch.accesodatos.AccesoDatos;
import ec.edu.espoch.accesodatos.ConjuntoResultado;
import ec.edu.espoch.accesodatos.Parametro;
import ec.edu.espoch.entidades.CAsistencia;
import ec.edu.espoch.entidades.CPersonaPeriodo;
import ec.edu.espoch.entidades.CUsuario;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author William
 */
public class MAsistencia {

    public static boolean insertarAsistencia(int idPeriodo, String cedula) throws Exception {
        boolean respuesta = false;
        CPersonaPeriodo obj = new CPersonaPeriodo();
        try {
            obj = MUsuario.obtIdUsuario(cedula);
            int aux=3;
            ArrayList<Parametro> lstP = new ArrayList<>();
            String sql = "SELECT tedx.fn_update_estadopersonaperiodo(?, ?, ?)";;
            lstP.add(new Parametro(1, idPeriodo));
            lstP.add(new Parametro(2, obj.getTpersonaid()));
            lstP.add(new Parametro(3, aux));

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

    public static boolean verificaAceptado(String cedula) throws Exception {
        CPersonaPeriodo obj = new CPersonaPeriodo();
        try {
            obj = MUsuario.obtIdUsuario(cedula);
            int aux=2;
            String sql = "SELECT \n"
                    + "  tpersonaperiodo.tpersonaid, \n"
                    + "  tpersonaperiodo.tpersonaperiodoestado\n"
                    + "FROM \n"
                    + "  tedx.tpersonaperiodo\n"
                    + "WHERE \n"
                    + "  tpersonaperiodo.tpersonaid = '"+obj.getTpersonaid()+"' AND \n"
                    + "  tpersonaperiodo.tpersonaperiodoestado = '"+aux+"';";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            while (rs.next()) {
                if (rs.getInt(1)== aux) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println("error" + e.getMessage());
        }
        return false;
    }

    //metodo para llenar la lista
    public static ArrayList<CUsuario> obtenerUsuario(String cedula) throws Exception {
        ArrayList<CUsuario> lst = new ArrayList<CUsuario>();
        try {
            CPersonaPeriodo obj = new CPersonaPeriodo();
            obj = MUsuario.obtIdUsuario(cedula);
            String sql = "SELECT \n"
                    + "  tpersona.tpersonaid, \n"
                    + "  tpersona.tpersonanombre, \n"
                    + "  tpersona.tpersonaapellido, \n"
                    + "  tpersona.tpersonacedula\n"
                    + "FROM \n"
                    + "  tedx.tpersona\n"
                    + "WHERE \n"
                    + "  tpersona.tpersonaid = '"+obj.getTpersonaid()+"';";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarUsuario(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

    public static ArrayList<CUsuario> llenarUsuario(ConjuntoResultado rs) throws Exception {
        ArrayList<CUsuario> lst = new ArrayList<CUsuario>();
        CUsuario usuario = null;
        try {
            while (rs.next()) {
                usuario = new CUsuario(rs.getInt(0),
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3));

                lst.add(usuario);
            }
        } catch (Exception e) {
            lst.clear();

            throw e;
        }
        return lst;
    }

    //fin lista llenar
    
}
