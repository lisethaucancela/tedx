/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos.utilBeans;

//import control.logica.clases.CGrupoLaboral;
import java.util.ArrayList;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.primefaces.component.autocomplete.AutoComplete;

/**
 *
 * @author escorpiondj
 */
public class GrupoLaboralConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
//        Object ret = null;
//        if (component instanceof AutoComplete) {
//            Object lista = ((AutoComplete) component).getSuggestions();
//            ArrayList lst = (ArrayList) lista;
//            for (Object o : lst) {
//                String id = "";
//                if (o instanceof CGrupoLaboral) {
//                    id += ((CGrupoLaboral) o).getIntCodigo();
//                }
//                if (submittedValue.equals(id)) {
//                    ret = o;
//                    break;
//                }
//            }
//        }
//        return ret;
//    }
//    
//    @Override
//    public String getAsString(FacesContext facesContext, UIComponent component, Object value){
//        String str = "";
//        if (value instanceof CGrupoLaboral){
//            str = "" + ((CGrupoLaboral)value).getIntCodigo();
//        }
//        return str;
//    }

}
