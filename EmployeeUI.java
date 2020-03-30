package managedbeans;

import java.io.Serializable;
import java.util.List;

import db.Employee;
import org.eclnt.ccee.db.dofw.DOFWSql;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.pagebean.PageBean;

import javax.faces.event.ActionEvent;

@CCGenClass (expressionBase="#{d.EmployeeUI}")

public class EmployeeUI extends PageBean implements Serializable
{
    // ------------------------------------------------------------------------
    // inner classes
    // ------------------------------------------------------------------------
    
    /* Listener to the user of the page bean. */
    public interface IListener
    {
    }
    
    // ------------------------------------------------------------------------
    // members
    // ------------------------------------------------------------------------
    
    private IListener m_listener;
    private FIXGRIDListBinding<EmployeeRow> gridEmployees = new FIXGRIDListBinding<EmployeeRow>();

    // ------------------------------------------------------------------------
    // constructors & initialization
    // ------------------------------------------------------------------------

    public EmployeeUI() {
        List<Employee> employees = DOFWSql.query(Employee.class, new Object[] {});
        employees.forEach((employee) -> {
            EmployeeRow row = new EmployeeRow(this, employee);
            getGridEmployees().getItems().add(row);
        });
    }

    public String getPageName() { return "/employee.jsp"; }
    public String getRootExpressionUsedInPage() { return "#{d.EmployeeUI}"; }

    // ------------------------------------------------------------------------
    // public usage
    // ------------------------------------------------------------------------

    /* Initialization of the bean. Add any parameter that is required within your scenario. */
    public void prepare(IListener listener)
    {
        m_listener = listener;
    }

    public FIXGRIDListBinding<EmployeeRow> getGridEmployees() {
        return gridEmployees;
    }

    // ------------------------------------------------------------------------
    // private usage
    // ------------------------------------------------------------------------
}
