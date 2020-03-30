package managedbeans;

import org.eclnt.workplace.WorkpageDispatcher;
import org.eclnt.workplace.IWorkpageContainer;
import org.eclnt.workplace.WorkpageDispatcher;
import org.eclnt.jsfserver.util.HttpSessionAccess;

/*
 * The dispatcher is referenced in faces-config.xml. When changing the package
 * of the dispatcher, then also update the faces-config.xml link!
 */
public class Dispatcher extends WorkpageDispatcher
{
    /**
     * This method needs to be implemented if you want to extend the page bean browser tool.
     */
    public static DispatcherInfo getStaticDispatcherInfo() { return new DispatcherInfo(Dispatcher.class); }
    
    // ------------------------------------------------------------------------
    // constructors
    // ------------------------------------------------------------------------

    /** Constructor that is used for the root object, e.g. "#{d}". */
    public Dispatcher()
    {
        // add any implementation...
    }

    /** Dispatcher that is used for the sub dispatcher objects, e.g. "#{d.d_1}". */
    public Dispatcher(IWorkpageContainer workpageContainer)
    {
        super(workpageContainer);
        // add any implementation...
    }

    
    // ------------------------------------------------------------------------
    // public usage
    // ------------------------------------------------------------------------

    /* Method for correctly casting the method from WorkpageDispatcher. */    
    public static Dispatcher getDialogSessionInstance() 
    {
        return (Dispatcher)WorkpageDispatcher.getDialogSessionInstance();
    }
  
}
