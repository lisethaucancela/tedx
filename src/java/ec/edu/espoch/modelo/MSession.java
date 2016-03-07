/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.modelo;

import ec.edu.espoch.accesodatos.AccesoDatos;
import ec.edu.espoch.accesodatos.ConjuntoResultado;
import ec.edu.espoch.entidades.CSession;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author William
 */
public class MSession {

    public static ArrayList<CSession> obtenerUsuario(CSession objSession) throws Exception {
        ArrayList<CSession> lst = new ArrayList<CSession>();
        try {
            String sql = "SELECT \n"
                    + "  tpersona.tpersonacedula, \n"
                    + "  tpersona.tpersonanombre, \n"
                    + "  tpersona.tpersonaapellido, \n"
                    + "  tpersona.tpersonaemail, \n"
                    + "  ttipousuario.ttipousuariodescripcion, \n"
                    + "  tcuenta.tcuentapassword, \n"
                    + "  tcuenta.tcuentanombreusuario, \n"
                    + "  tcuenta.tcuentaid\n"
                    + "FROM \n"
                    + "  tedx.tcuenta, \n"
                    + "  tedx.tpersona, \n"
                    + "  tedx.ttipousuario, \n"
                    + "  tedx.tcuentatipousuario\n"
                    + "WHERE \n"
                    + "  tcuenta.tcuentaid = tcuentatipousuario.tcuentatipousuarioid AND\n"
                    + "  tpersona.tpersonaid = tcuenta.tpersonaid AND\n"
                    + "  ttipousuario.ttipousuarioid = tcuentatipousuario.ttipousuarioid AND\n"
                    + "  tpersona.tpersonaid = tcuenta.tpersonaid AND \n"
                    + "  tcuenta.tcuentaid = tcuentatipousuario.tcuentaid AND \n"
                    + "  ttipousuario.ttipousuarioid = tcuentatipousuario.ttipousuarioid AND \n"
                    + "  tcuenta.tcuentanombreusuario = '"+objSession.getCuentaUsuario()+"' AND \n"
                    + "  tcuenta.tcuentapassword = '"+objSession.getPassword()+"';";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarUsuario(rs);

            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

    public static ArrayList<CSession> llenarUsuario(ConjuntoResultado rs) throws Exception {
        ArrayList<CSession> lst = new ArrayList<CSession>();
        CSession usuario = null;
        try {
            while (rs.next()) {
                usuario = new CSession(rs.getString(0), rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
                lst.add(usuario);
            }
        } catch (Exception e) {
            lst.clear();
            //  integracion.auditoria.log ublog = new integracion.auditoria.log();
            // ublog.write("Modulo", "llenarModulos", e.getClass().getName(), e.getMessage());
            throw e;
        }
        return lst;
    }

    public static boolean autenticar(CSession objCedula) {
        boolean resultado = false;

        if (objCedula.getCedula().equals("admin") && (objCedula.getPassword().equals("123456"))) {
            resultado = true;
        }

        return resultado;
    }

}
