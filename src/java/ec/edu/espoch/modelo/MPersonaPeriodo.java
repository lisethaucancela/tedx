/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.modelo;

import ec.edu.espoch.accesodatos.AccesoDatos;
import ec.edu.espoch.accesodatos.ConjuntoResultado;
import ec.edu.espoch.accesodatos.Parametro;
import ec.edu.espoch.entidades.CPersonaPeriodo;
import java.sql.SQLException;
import java.util.ArrayList;

public class MPersonaPeriodo {

    //<editor-fold defaultstate="collapsed" desc="Método Obtener Registrados x Periodo">
    public static ArrayList<CPersonaPeriodo> obtenerRegistrados(int idPeriodo) throws Exception {
        ArrayList<CPersonaPeriodo> lst = new ArrayList<CPersonaPeriodo>();
        try {
            ArrayList<Parametro> lstP = new ArrayList<>();
            String sql = "SELECT * from tedx.fn_select_registrados(?);";
            lstP.add(new Parametro(1, 1));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            lst = llenarPersonaPeriodo(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }

        return lst;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método Obtener Participantes x Periodo">
    public static ArrayList<CPersonaPeriodo> obtenerParticipantes(int idPeriodo) throws Exception {
        ArrayList<CPersonaPeriodo> lst = new ArrayList<CPersonaPeriodo>();
        try {
            ArrayList<Parametro> lstP = new ArrayList<>();
            String sql = "SELECT * from tedx.fn_select_participantes(?);";
            lstP.add(new Parametro(1, 1));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            lst = llenarPersonaPeriodo(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }

        return lst;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método LlenarPersonaPeriodo">
    public static ArrayList<CPersonaPeriodo> llenarPersonaPeriodo(ConjuntoResultado rs) throws Exception {
        ArrayList<CPersonaPeriodo> lst = new ArrayList<CPersonaPeriodo>();
        CPersonaPeriodo obj = null;
        try {
            while (rs.next()) {
                obj = new CPersonaPeriodo(rs.getInt(0), MUsuario.obtUsuario(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                lst.add(obj);
            }
        } catch (Exception e) {
            lst.clear();
            System.err.println("e" + e.getMessage());
            throw e;            
        }
        return lst;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método Numero de Participantes">
    public static int numeroParticipantes(int idPeriodo) throws Exception {
        int resp = 0;
        try {
            String sql = "select count(*) from  tedx.tpersonaperiodo where tperiodoid = " + idPeriodo + " AND  tpersonaperiodoestado=2;";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            while (rs.next()) {
                resp = rs.getInt(0);
            }
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }

        return resp;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método Modificar Estado Persona-Periodo">
    public static boolean modificarEstadoPersonaPeriodo(int idperiodo, int idPersonaPeriodo, int estado) throws Exception {
        boolean respuesta = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<>();
            String sql = "SELECT tedx.fn_update_estadopersonaperiodo(?, ?, ?)";
            lstP.add(new Parametro(1, idperiodo));
            lstP.add(new Parametro(2, idPersonaPeriodo));
            lstP.add(new Parametro(3, estado));
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
    //</editor-fold>

}
