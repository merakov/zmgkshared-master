package managedbeans;

import java.io.Serializable;
import java.time.LocalDate;

import db.Company;
import org.eclnt.ccee.db.dofw.DOFWSql;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.pagebean.PageBean;

import javax.faces.event.ActionEvent;

@CCGenClass (expressionBase="#{d.AddNewUI}")

public class AddNewUI extends PageBean implements Serializable
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
    private String name;
    private Integer year;
    private Integer bulstat;
    private LocalDate dateEst;
    private Company company = new Company();
    private IListener m_listener;
    private CompanyUI companyUI;
    private String someNewProperty;

    // ------------------------------------------------------------------------
    // constructors & initialization
    // ------------------------------------------------------------------------

    public AddNewUI(CompanyUI companyUI)
    {
        this.companyUI = companyUI;
    }

    public String getPageName() { return "/addnew.jsp"; }
    public String getRootExpressionUsedInPage() { return "#{d.AddNewUI}"; }

    // ------------------------------------------------------------------------
    // public usage
    // ------------------------------------------------------------------------

    /* Initialization of the bean. Add any parameter that is required within your scenario. */
    public void prepare(IListener listener)
    {
        m_listener = listener;
    }

    public void onSaveAction(javax.faces.event.ActionEvent event) {
        if (getName() == null || getName().equals("")) {
            Statusbar.outputAlert("Field Name is empty");
            return;
        }
        if (getYear() == null || getYear() == 0) {
            Statusbar.outputAlert("Field Year is empty");
            return;
        }
        if (getBulstat() == null || getBulstat() == 0) {
            Statusbar.outputAlert("Field Bulstat is empty");
            return;
        }
        if (getDateEst() == null || getDateEst().toString().equals("")) {
            Statusbar.outputAlert("Field DateEst is empty");
            return;
        }
        try {
            DOFWSql.saveObject(company);
            companyUI.insertRow(company);
            Statusbar.outputMessageWithPopup("The new company was added");
        } catch (Exception e) {
            Statusbar.outputAlert(e.getMessage());
        }

    }

    public Company getCompany() {
        return company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        company.setName(name);
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        try {
            this.year = year;
            company.setYear(year);
        } catch (Exception e) {
            this.year = 0;
            company.setYear(0);
        }

    }

    public Integer getBulstat() {
        return bulstat;
    }

    public void setBulstat(Integer bulstat) {
        try {
            this.bulstat = bulstat;
            company.setBulstat(bulstat);
        } catch (Exception e) {
            this.bulstat = 0;
            company.setBulstat(0);
        }
    }

    public LocalDate getDateEst() {
        return dateEst;
    }

    public void setDateEst(LocalDate dateEst) {
        this.dateEst = dateEst;
        company.setDateEst(dateEst);
    }

    // ------------------------------------------------------------------------
    // private usage
    // ------------------------------------------------------------------------
}
