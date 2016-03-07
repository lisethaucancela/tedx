/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paúl Paguay
 */
public class Tools {

    public static java.util.Date obtieneFecha(Object fecha) throws ParseException {
        java.util.Date dt = null;
        if (fecha != null) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d = format.parse(fecha.toString());
            dt = d;
        }
        return dt;
    }

    public static java.sql.Timestamp obtieneFechaTimeStamp(long fecha) throws ParseException {
        java.sql.Timestamp timeStampDate = new Timestamp(fecha * 1000);
//          java.sql.Timestamp timeStampDate = new Timestamp(fecha);
        return timeStampDate;
    }

//       public static java.sql.Timestamp obtieneFechaTimeStampTrunc(long fecha ) throws ParseException{
////        java.sql.Timestamp timeStampDate = new Timestamp(fecha*1000);
//          java.sql.Timestamp timeStampDate = new Timestamp(fecha);
//        return timeStampDate;
//    }
    
    public static java.sql.Time ObtieneHoraDadoString(String hora) {
        long lnMilisegundos = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        try {
            java.util.Date date = sdf.parse(hora);
            lnMilisegundos = date.getTime();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        java.sql.Time sqlTime = new java.sql.Time(lnMilisegundos);
        return sqlTime;
    }
    
    public static long obtieneFechaDividaPorMil(long fecha) {
        return fecha / 1000;
    }

    public static long obtieneFechaSistemaDividaPorMil(long fecha) {
        long fechaF = System.currentTimeMillis() / 1000;
        return fechaF;
    }

    public static String obtieneFechaconFormato(Object fecha, String formatosalida) throws ParseException {
        String dsal = "";
        if (fecha != null) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date d = format.parse(fecha.toString());
            SimpleDateFormat formatsal = new SimpleDateFormat("dd/MM/yyyy");
            dsal = formatsal.format(d);
        }
        return dsal;
    }

    public static java.sql.Timestamp obtieneFechaActualSegundos() throws ParseException {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());
        return sqlDate;
    }

    public static long obtieneFechaActualenMiliseg() throws ParseException {
        java.util.Date dt = new java.util.Date();
        long fecha = dt.getTime();
        return fecha;
    }
    
     public static long obtieneFechaActualenMilisegDivididaPOrMil() throws ParseException {
        java.util.Date dt = new java.util.Date();
        long fecha = dt.getTime()/1000;
        return fecha;
    }


    public static long obtieneFechaActualenMilisegDadoDateUtil(java.util.Date dt) throws ParseException {
        //java.util.Date dt= new java.util.Date();
        long fecha = dt.getTime();
        return fecha;
    }

    public static String obtieneFechaActual() {
        java.util.Date fechaActual = new java.util.Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String d = format.format(fechaActual);
        return d;
    }

    public static String obtieneFechaFormatoCompleto(Date fecha) {
        String res = "";
        try {
            java.util.Date fechaActual = obtieneFecha(fecha);
            SimpleDateFormat format = new SimpleDateFormat("dd & MMMM | yyyy");
            String d = format.format(fechaActual).replace("&", "de").replace("|", "del");
            res = d;
        } catch (Exception e) {
            res = "";
        }
        return res;


    }

    public static String sumaAnios(String fechaini, int anios) {
        String anio = fechaini.substring(0, 4);
        anio = Integer.toString(Integer.parseInt(anio) + anios);
        String fechafin = anio + fechaini.substring(4, 10);
        return fechafin;
    }

    public static String sumaAnios(java.sql.Date fechaini, int anios) {
        return sumaAnios(fechaini.toString(), anios);
    }

    public static String obtieneBit(Object opcion) throws ParseException {
        String eje = "1";
        if (opcion != null) {
            boolean band = (Boolean) opcion;
            if (band) {
                eje = "1";
            } else {
                eje = "0";
            }

        }
        return eje;
    }

    public static String verificaStringNoNull(Object value) {
        String res = "";
        if (value != null) {
            res = value.toString();
        }
        return res;
    }

    public static int verificaIntNoNull(Object value) {
        int res = 0;
        if (value != null) {
            res = (Integer) value;
        }
        return res;
    }

    public static Date verificaDateNoNull(Object value) {
        Date res = null;
        if (value != null) {
            try {
                res = Tools.obtieneFecha(value);
            } catch (ParseException ex) {
                Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return res;
    }

    public static String obtieneHora(String fecha_hora) {

        String res = "";
        if (fecha_hora.length() > 8) {
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            // DateFormat tim=  new SimpleDateFormat("hh:mm:ss");
            try {
                Date date = sdf.parse(fecha_hora);
                res = fecha_hora.substring(fecha_hora.indexOf(" "), fecha_hora.length() - 5);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                DateFormat tim = new SimpleDateFormat("hh:mm:ss");
                Date date = tim.parse(fecha_hora);
                res = fecha_hora.substring(0, 5);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public static String transcedulaConguion(String cedula) throws ParseException {
        if ((cedula.length() > 9) && (cedula.indexOf("-") < 0)) {
            cedula = cedula.substring(0, 9) + "-" + cedula.substring(9);
        }
        return cedula;
    }

    public static String transcedulaSinguion(String cedula) throws ParseException {
        return cedula.replace("-", "");
    }

    public static boolean isNumeric(String cadena) {
        try {
            Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static String eliminametacaracteres(String cadena) {
        String[] caracteres = {"Â¡", "!", "#", "$", "%", "&", "/", "(", ")", "|", "\\", "{", "}", "Â´", "â€œ", "<", ">", "_", "^", "Â¿", "?", "`", "~", "*", "[", "]"};

        for (String car : caracteres) {
            cadena = cadena.replace(car, "");
        }
//              String filter="Â¡!#$%&/()|\\{}Â´\"<>_^Â¿?`~*[].";
        return cadena;
    }

   
//Este es el método calcularEdad que se manda
  public static Integer calcularEdad(String fecha){
  Date fechaNac=null;
	        try {
            /**Se puede cambiar la mascara por el formato de la fecha
	            que se quiera recibir, por ejemplo año mes día "yyyy-MM-dd"
	            en este caso es día mes año*/
	            fechaNac = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
	        } catch (Exception ex) {
	            System.out.println("Error:"+ex);
	        }
	        Calendar fechaNacimiento = Calendar.getInstance();
	        //Se crea un objeto con la fecha actual
	        Calendar fechaActual = Calendar.getInstance();
	        //Se asigna la fecha recibida a la fecha de nacimiento.
	        fechaNacimiento.setTime(fechaNac);
	        //Se restan la fecha actual y la fecha de nacimiento
	        int año = fechaActual.get(Calendar.YEAR)- fechaNacimiento.get(Calendar.YEAR);
	        int mes =fechaActual.get(Calendar.MONTH)- fechaNacimiento.get(Calendar.MONTH);
	        int dia = fechaActual.get(Calendar.DATE)- fechaNacimiento.get(Calendar.DATE);
	        //Se ajusta el año dependiendo el mes y el día
	        if(mes<0 || (mes==0 && dia<0)){
	            año--;
	        }
	        //Regresa la edad en base a la fecha de nacimiento
	        return año;
	    }
	
public static java.util.Date transformarFecha(java.sql.Date date)
{
     //creating instances of java.util.Date which represents today's date and time
       
        java.util.Date utilDate = new java.util.Date(date.getTime());
       return  utilDate;
}
        
}
