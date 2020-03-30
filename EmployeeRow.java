package managedbeans;


import db.Company;
import db.Employee;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;

public class EmployeeRow extends FIXGRIDItem implements java.io.Serializable {
    private Employee employee;
    private EmployeeUI employeeUI;

    EmployeeRow(EmployeeUI employeeUI, Employee employee) {
        this.employeeUI = employeeUI;
        this.employee = employee;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    @Override
    public void onRowSelect() {

    }

    @Override
    public void onRowDeselect() {

    }

    @Override
    public void onRowExecute() {

    }
}
