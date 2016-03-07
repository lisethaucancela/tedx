/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos.utilBeans;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

/**
 *
 * @author escorpiondj
 */
public class EmpleadoPickConverter implements Converter {

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
//        if (component instanceof PickList) {
//            Object dualList = ((PickList) component).getValue();
//            DualListModel dl = (DualListModel) dualList;
//            for (Object o : dl.getSource()) {
//                String id = "";
//                if (o instanceof CRelacionDependenciaConsulta) {
//                    id += ((CRelacionDependenciaConsulta) o).getCodigo();
//                }
//                if (submittedValue.equals(id)) {
//                    ret = o;
//                    break;
//                }
//            }
//            if (ret == null) {
//                for (Object o : dl.getTarget()) {
//                    String id = "";
//                    if (o instanceof CRelacionDependenciaConsulta) {
//                        id += ((CRelacionDependenciaConsulta) o).getCodigo();
//                    }
//                    if (submittedValue.equals(id)) {
//                        ret = o;
//                        break;
//                    }
//                }
//            }
//        }
//        return ret;
//    }
//
//    @Override
//    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
//        String str = "";
//        if (value instanceof CRelacionDependenciaConsulta) {
//            str = "" + ((CRelacionDependenciaConsulta) value).getCodigo();
//        }
//        return str;
//    }
}
