/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

/**
 *
 * @author Máximo Mussini
 */
public class ReflectionUtil {    

    // Obtiene una propiedad de la clase a partir de un getter
    public static Object getValue(Object object, String fieldName) {
        try {
            String getterName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
            return object.getClass().getMethod(getterName).invoke(object);           
        } catch (Exception e) {
            return null;
        }
    }
}
