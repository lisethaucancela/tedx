/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 *
 * @author Maximo Mussini
 */
public class ResourceUtil {

    public static String getString(String key){
        return getString(key, "/Bundle");
    }
        
    public static String getString(Enum<?> bundleKey){
        return getString(bundleKey.getClass().getSimpleName() + "_" + bundleKey.name(), "/Bundle");
    }
    
    public static String getSelectItemString(Enum<?> bundleKey){
        return getString(bundleKey.getClass().getSimpleName() + "_SelectItem_" + bundleKey.name(), "/Bundle");
    }
    
    public static String getString(String key, String resourcePath){
        if (key == null)
            return null;
        try {
            return ResourceBundle.getBundle(resourcePath).getString(key);
        } catch (Exception e) {
            Logger.getLogger(e.getMessage());
            return "???" + key + "???";
        }
    }
}
