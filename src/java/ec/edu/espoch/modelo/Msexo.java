/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.modelo;

import ec.edu.espoch.accesodatos.AccesoDatos;
import ec.edu.espoch.accesodatos.ConjuntoResultado;
import ec.edu.espoch.entidades.CSexo;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author William
 */
public class Msexo {
    
    public static ArrayList<CSexo> obtenerSexo() throws Exception {
        ArrayList<CSexo> lst = new ArrayList<CSexo>();
        try {
            String sql = "SELECT * from tedx.fn_select_tsexo();";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarSexo(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

    public static ArrayList<CSexo> llenarSexo(ConjuntoResultado rs) throws Exception {
        ArrayList<CSexo> lst = new ArrayList<CSexo>();
        CSexo sexo = null;
        try {
            while (rs.next()) {
                sexo = new CSexo(rs.getInt(0),
                        rs.getString(1)
                        
                );
                lst.add(sexo);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }
    
    public static CSexo obtSexo(int codigo) throws Exception {
        CSexo sexo = null;
        try {
            String sql = "select * from tedx.tsexo where tsexoid='" + codigo + "'";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            while (rs.next()) {
                sexo = new CSexo(rs.getInt(0), rs.getString(1) );
            }

        } catch (SQLException e) {
            throw e;
        }
        return sexo;
    }
}
