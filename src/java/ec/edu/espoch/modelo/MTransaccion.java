/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.modelo;

import ec.edu.espoch.accesodatos.AccesoDatos;
import ec.edu.espoch.accesodatos.ConjuntoResultado;
import ec.edu.espoch.accesodatos.Parametro;
import ec.edu.espoch.entidades.CTransaccion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MTransaccion {

    public static boolean insertarTransccion(CTransaccion transaccion) throws Exception {
        boolean respuesta = false;
        String dia, mes, annio, str;
        Calendar c2 = new GregorianCalendar();
        dia = Integer.toString(c2.get(Calendar.DATE));
        mes = Integer.toString(c2.get(Calendar.MONTH) + 1);
        annio = Integer.toString(c2.get(Calendar.YEAR));
        str = "" + annio + "-" + mes + "-" + dia;
        try {
            ArrayList<Parametro> lstP = new ArrayList<>();
            String sql = "SELECT tedx.fn_insert_ttransaccion(?,?,?,?)";

            lstP.add(new Parametro(1, transaccion.getTtransaccioncedula()));
            lstP.add(new Parametro(2, "2015-10-15"));
            lstP.add(new Parametro(3, transaccion.getTtransacciontipo()));
            lstP.add(new Parametro(4, transaccion.getTtransaccionobjeto()));
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
