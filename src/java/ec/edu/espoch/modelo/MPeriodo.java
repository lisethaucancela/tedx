/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.modelo;

import ec.edu.espoch.accesodatos.AccesoDatos;
import ec.edu.espoch.accesodatos.ConjuntoResultado;
import ec.edu.espoch.accesodatos.Parametro;
import ec.edu.espoch.entidades.CPeriodo;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author William
 */
public class MPeriodo {

    private static Object exConec;

    public static java.sql.Date sqlDate(java.util.Date calendarDate) {
        return new java.sql.Date(calendarDate.getTime());
    }

    //<editor-fold defaultstate="collapsed" desc="Método Obtener Maximo Participantes">
    public static int obtMaxParticipantes(int periodoId) throws Exception {
        int maximo = 0;
        CPeriodo objPeriodo = null;
        try {
            ArrayList<Parametro> lstP = new ArrayList<>();
            String sql = "SELECT * from tedx.fn_select_max_participantes_x_periodo(?)";
            lstP.add(new Parametro(1, periodoId));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            while (rs.next()) {
                maximo = rs.getInt(0);
            }
        } catch (SQLException e) {
            throw e;
        }
        return maximo;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método Obtener Periodo">
    public static ArrayList<CPeriodo> obtenerPeriodo() throws Exception {
        ArrayList<CPeriodo> lst = new ArrayList<CPeriodo>();
        try {
            String sql = "SELECT * from tedx.fn_select_periodo();";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarPeriodo(rs);
            rs = null;
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
        return lst;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método Llenar Periodo">
    public static ArrayList<CPeriodo> llenarPeriodo(ConjuntoResultado rs) throws Exception {
        ArrayList<CPeriodo> lst = new ArrayList<CPeriodo>();
        CPeriodo obj = null;
        try {
            while (rs.next()) {
                obj = new CPeriodo(rs.getInt(0),
                        rs.getString(1),
                        rs.getDate(2),
                        rs.getDate(3),
                        rs.getInt(4),
                        rs.getBoolean(5));
                lst.add(obj);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Método Insertar Periodo">
    public static boolean insertarPeriodo(CPeriodo periodo) throws Exception {
        boolean respuesta = false;
        //DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); = df.format(periodo.getTperiodofechainicio());
        Date fechaI = sqlDate(periodo.getTperiodofechainicio());
        Date fechaF = sqlDate(periodo.getTperiodofechafin());

        try {
            String sql = "SELECT tedx.fn_insert_tperiodo(?,?,?,?,?);";
            // Date date = String.format( periodo.getTperiodofechainicio().getTime();
            ArrayList<Parametro> lspar = new ArrayList<Parametro>();
            lspar.add(new Parametro(1, periodo.getTperiododescripcion()));
            lspar.add(new Parametro(2, fechaI));
            lspar.add(new Parametro(3, fechaF));
            lspar.add(new Parametro(4, periodo.getTperiodomaxparticipantes()));
            lspar.add(new Parametro(5, periodo.isTperiodoestado()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lspar);
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

    //<editor-fold defaultstate="collapsed" desc="Método editar Periodo">
    public static boolean editarPeriodo(CPeriodo periodo) throws Exception {
        boolean respuesta = false;
        Date fechaI = sqlDate(periodo.getTperiodofechainicio());
        Date fechaF = sqlDate(periodo.getTperiodofechafin());

        try {
            String sql = "SELECT tedx.fn_update_periodo(?,?,?,?,?,?);";
            ArrayList<Parametro> lspar = new ArrayList<Parametro>();
            lspar.add(new Parametro(1, periodo.getTperiodoid()));
            lspar.add(new Parametro(2, periodo.getTperiododescripcion()));
            lspar.add(new Parametro(3, fechaI));
            lspar.add(new Parametro(4, fechaF));
            lspar.add(new Parametro(5, periodo.getTperiodomaxparticipantes()));
            lspar.add(new Parametro(6, periodo.isTperiodoestado()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lspar);
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

    //<editor-fold defaultstate="collapsed" desc="Método Eliminar Periodo">
    public static boolean eliminarPeriodo(CPeriodo periodo) throws Exception {
        boolean respuesta = false;
        try {
            String sql = "SELECT tedx.fn_delete_periodo(?);";
            ArrayList<Parametro> lspar = new ArrayList<Parametro>();
            lspar.add(new Parametro(1, periodo.getTperiodoid()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lspar);
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
