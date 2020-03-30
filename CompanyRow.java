package managedbeans;

import db.Company;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;

public class CompanyRow extends FIXGRIDItem implements java.io.Serializable {
    private Company company;
    private CompanyUI companyUI;

    CompanyRow(CompanyUI companyUI, Company company) {
        this.companyUI = companyUI;
        this.company = company;
        //company = new Company();
    }

    public Company getCompany() {
        return company;
    }

    @Override
    public void onRowSelect() {
        Statusbar.outputMessage("select " + + getCompany().getId() + " " + getCompany().getYear());
    }

    @Override
    public void onRowDeselect() {
        Statusbar.outputMessage("deselect " + getCompany().getId() + " " + getCompany().getYear());
    }

    @Override
    public void onRowExecute() {
        companyUI.selectCompanyRow(this);
    }

}
