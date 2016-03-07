package recursos;

import javax.faces.event.*;
public class MyPhaseListener implements PhaseListener
{

    private static final long serialVersionUID = 1L;

    public MyPhaseListener()
    {
    }

    public void afterPhase(PhaseEvent event)
    {
        System.out.println((new StringBuilder("   Despues de la fase--> ")).append(event.getPhaseId().toString()).append("--Vista ").append(event.getFacesContext().getViewRoot().getViewId()).toString());
        if(event.getPhaseId() == PhaseId.RENDER_RESPONSE)
        {
           System.out.println("******Peticion Procesada!!****");
           System.out.println("<<<  <<<   <<    <<     <");
           System.out.println((""));
        }
    }

    public void beforePhase(PhaseEvent event)
    {
        if(event.getPhaseId() == PhaseId.RESTORE_VIEW)
        {
        	System.out.println((""));
        	System.out.println((" >   >   >>   >>   >>>    >>>"));
            System.out.println(("****  Procesando una nueva Peticion   ****"));
        }
        System.out.println((new StringBuilder("   Antes de la fase--> ")).append(event.getPhaseId().toString()).toString());
    }

    public PhaseId getPhaseId()
    {
        return PhaseId.ANY_PHASE;
    }

}
