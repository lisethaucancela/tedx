/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package recursos;

/**
 *
 * @author Paúl Paguay
 */
public class BaseSistema {

     public static String obtenerAccionPagina(String valor){
        return "<input type=\"text\" id=\"accion\" name=\"accion\" value=\""+valor+"\" style=\"visibility: hidden\" />";
    }

    public static  String redireccionalogin(String url){
        return  "<script language='JavaScript'>window.open('"+url+"','_top'); </script>";
    }

     public static String obtenerImagen(String imagen, String title){
        return "<img src=\"recursos/imgs/"+imagen+"\" title=\""+title+"\" />";
    }
     public static String ObtenerScriptConfirmarCodigo(String titulo,String url){
         return " <script language=\"JavaScript\" > "+
    "function eliminar(codigo) "+
    "{  if(confirm(\""+titulo+"\")) "+
             "window.open('"+url+"&codigo='+codigo,'_self');"+
	"else "+
           "alert(\"La acción ha sido cancelada\");"+
    "}</script>";
     }
     public static String obtenerscriptmodal(int ancho, int alto){
         return "<script language=\"JavaScript\" >"+
        "function abrirmodal(url) {\n"+
       "var WinSettings = \"center:yes;resizable:no;dialogHeight:"+alto+
        "px;dialogWidth:"+ancho+"px\" \n" +
       "window.showModalDialog(url, \"\", WinSettings)"+
       " } </script>";
     }

}
