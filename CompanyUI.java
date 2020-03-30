package managedbeans;

import java.io.Serializable;
import java.net.http.HttpClient;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import db.Company;
import org.eclnt.ccee.db.DBAction;
import org.eclnt.ccee.db.dofw.DOFWSql;
import org.eclnt.ccee.log.AppLog;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.ModalPopup;
import org.eclnt.jsfserver.defaultscreens.OKPopup;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.pagebean.PageBean;
import org.eclnt.jsfserver.util.HttpSessionAccess;

import javax.faces.event.ActionEvent;

import static org.eclnt.util.log.CLogConstants.LL_INF;

@CCGenClass (expressionBase="#{d.CompanyUI}")

public class CompanyUI extends PageBean implements Serializable
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
    private FIXGRIDListBinding<CompanyRow> gridCompanies = new FIXGRIDListBinding<CompanyRow>();
    private CompanyRow m_selCompanyRow;

    // ------------------------------------------------------------------------
    // constructors & initialization
    // ------------------------------------------------------------------------

    public CompanyUI() {
        List<Company> companies = DOFWSql.query(Company.class, new Object[] {});
        companies.forEach((company) -> {
            CompanyRow row = new CompanyRow(this, company);
            gridCompanies.getItems().add(row);
        });
    }

    public String getPageName() { return "/company.jsp"; }
    public String getRootExpressionUsedInPage() { return "#{d.CompanyUI}"; }

    // ------------------------------------------------------------------------
    // public usage
    // ------------------------------------------------------------------------

    /* Initialization of the bean. Add any parameter that is required within your scenario. */
    public void prepare(IListener listener) {
        m_listener = listener;
    }

    public FIXGRIDListBinding<CompanyRow> getGridCompanies() {
        return gridCompanies;
    }

    public CompanyRow getSelCompanyRow() {
        return m_selCompanyRow;
    }

    public void selectCompanyRow(CompanyRow companyRow) {
        //if (m_selCompanyRow != null) m_selCompanyRow.getChangeIndex().indicateChange();
        m_selCompanyRow = companyRow;
        m_selCompanyRow.getChangeIndex().indicateChange();
    }

    public void onDeleteAction(javax.faces.event.ActionEvent event) {
        FIXGRIDItem selectedItem = gridCompanies.getSelectedItem();
        Integer id = gridCompanies.getSelectedItem().getCompany().getId();
        final CompanyUI companyUI = this;
        OKPopup p = OKPopup.createInstance("Be careful!", "Please confirm deleting by pressing OK.", new OKPopup.IOKCancelListener() {
            @Override
            public void reactOnOK() {
                DOFWSql.delete(Company.class, new Object[] {"id", id});
                gridCompanies.getRows().remove(selectedItem);
                //HttpSessionAccess.reloadClient();
            }
            @Override public void reactOnCancel() {}
        });
        p.setShowCancel(true);
    }

    public void onSaveAction(javax.faces.event.ActionEvent event) {
        CompanyRow row = gridCompanies.getSelectedItem();
        Statusbar.outputMessage(
                "onSave " +
                        row.getCompany().getId() + " " +
                        row.getCompany().getName() + " " +
                        row.getCompany().getYear() + " " +
                        row.getCompany().getBulstat()

        );
        DOFWSql.saveObject(row.getCompany());
    }

    public void insertRow(Company company) {
        CompanyRow newRow = new CompanyRow(this, company);
        gridCompanies.getItems().add(newRow);
    }

    public void onAddAction(javax.faces.event.ActionEvent event) {
        AppLog.L.log(LL_INF, "*** ON ADD ***");
        final AddNewUI bean = new AddNewUI(this);
        //CompanyRow newRow = new CompanyRow(this, bean.getCompany());
        //final CompanyUI companyUI = this;
        ModalPopup p = openModalPopup(bean, "", 600, 500, new ModalPopup.IModalPopupListener() {
            @Override
            public void reactOnPopupClosedByUser() {
                closePopup(bean);
            }
        });
        p.setLeftTopReferenceCentered();

        // try {
        //     Company company = DOFWSql.queryOne(Company.class, new Object[] {"id", 30});
        //     Statusbar.outputMessage(company.getName());
        // } catch (NullPointerException npe) {
        //     Statusbar.outputError("30 not found");
        // }


    }

    //public void refreshGrid() {
    //    List<CompanyRow> rows = gridCompanies.getRows();
    //    rows.forEach((row) -> {
    //        gridCompanies.getRows().remove(row);
    //    });
    //    List<Company> companies = DOFWSql.query(Company.class, new Object[] {});
    //    companies.forEach((company) -> {
    //        CompanyRow row = new CompanyRow(this, company);
    //        gridCompanies.getItems().add(row);
    //    });
    //}

    // ------------------------------------------------------------------------
    // private usage
    // ------------------------------------------------------------------------


}
