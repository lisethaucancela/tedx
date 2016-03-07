/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos.utilBeans;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author escorpionDJ
 */
@ManagedBean
@javax.faces.bean.ViewScoped
public class WizardBean implements Serializable {

    private boolean skip;

    public WizardBean() {
        try {
            System.out.println("paso el wizard");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public WizardBean(boolean skip) {
        try {
            System.out.println("paso el wizard");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.skip = skip;
    }
    private static final Logger logger = Logger.getLogger(WizardBean.class.getName());

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {

        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        logger.log(Level.INFO, "Current wizard step:{0}", event.getOldStep());
        logger.log(Level.INFO, "Next step:{0}", event.getNewStep());

        if (skip) {
            skip = false;   //reset in case user goes back  
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public void save(ActionEvent actionEvent) {
        System.out.print("entro al save");
    }
}
