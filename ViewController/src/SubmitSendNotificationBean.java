import java.sql.CallableStatement;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import model.services.AppModuleImpl;

import oracle.adf.model.BindingContext;
import oracle.adf.model.DataControlFrame;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCDataControl;

import oracle.adf.view.rich.component.rich.data.RichColumn;

import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;

import oracle.jdbc.OracleTypes;

public class SubmitSendNotificationBean {
    private RichColumn headerSearchTable;
    private RichTable headerSearchPageTable;
    private RichCommandButton submitSendNotificationBean;

    public SubmitSendNotificationBean() {
        //System.out.println("Submit Send Notification Bean initialized");
        
    }
     
    public ApplicationModule getAppM(){
        DCBindingContainer bindingContainer =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        //BindingContext bindingContext = BindingContext.getCurrent();
        DCDataControl dc =
            bindingContainer.findDataControl("AppModuleDataControl"); // Name of application module in datacontrolBinding.cpx
        AppModuleImpl appM = (AppModuleImpl)dc.getDataProvider();
        return appM;
    }
    
    AppModuleImpl am = (AppModuleImpl)this.getAppM();
    
    public void submitSendNotification(ActionEvent actionEvent) {
        System.out.println("@Into submitSendNotification Button.... ");

        ViewObject HeaderView =  am.getMnjOntSampleStatusVO1();
        
        
        String SubmitStatus, SubmitStatusMsg, HeaderId;
        HeaderId =  HeaderView.getCurrentRow().getAttribute("HeaderId").toString();
        System.out.println("Current Header Id --->" + HeaderId);
        //sampleNotificationProcedure() method calls
        String ProcedureExecutionMsg = sampleNotificationProcedure(HeaderId); 
        System.out.println(ProcedureExecutionMsg);
        
        String message = "Notification Sent Successfully!";
        FacesMessage fm = new FacesMessage(message);
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
        
        try{
            SubmitStatus = HeaderView.getCurrentRow().getAttribute("SampleStatus").toString();
            SubmitStatusMsg = HeaderView.getCurrentRow().getAttribute("SubmitStatusMessage").toString();
            Row HeaderRow = HeaderView.getCurrentRow();
            
            if (SubmitStatus.equals("N")){
                System.out.println("@Into Sample Status ---> 'N' Condition");
                System.out.println("SubmitStatus Before--->"+ SubmitStatus);
                System.out.println("SubmitStatusMsg Before--->"+ SubmitStatusMsg);
                HeaderRow.setAttribute("SampleStatus", "Y");
                HeaderRow.setAttribute("SubmitStatusMessage", "Yes");
                System.out.println("SubmitStatus After--->"+ HeaderRow.getAttribute("SampleStatus").toString());
                System.out.println("SubmitStatusMsg After--->"+ HeaderRow.getAttribute("SubmitStatusMessage").toString());
                am.getDBTransaction().commit();
                System.out.println("Commit Executed @'N' Condition");
                
            }
//            else if (HeaderRow.getAttribute("SampleStatus").toString().equals("Y")){
//                    System.out.println("@Into Sample Status ---> 'Y' Condition");
//                    am.getDBTransaction().commit();
//                    System.out.println("Commit Executed @'Y' Condition");
//                    String message = "Notification Sent Successfully!";
//                    FacesMessage fm = new FacesMessage(message);
//                    fm.setSeverity(FacesMessage.SEVERITY_INFO);
//                    FacesContext context = FacesContext.getCurrentInstance();
//                    context.addMessage(null, fm);
//                }
        }
        catch(Exception e) {
          System.out.println(e.getMessage());
          System.out.println("@Into Exception...");
          am.getDBTransaction().commit();
          System.out.println("Commit Executed @Exception");
            
        }
        
    }
    
    public String sampleNotificationProcedure(String HeaderId){
        
            System.out.println("@Into Procedure...");
            //Procedure Sample Notification starts
            String stmt = "BEGIN proc_sample_notification(:1); end;";

            CallableStatement cs =
                am.getDBTransaction().createCallableStatement(stmt,1);
            try {
            //                    cs.registerOutParameter(3, OracleTypes.VARCHAR);
                  cs.setString(1, HeaderId);
            //                    cs.setString(2, doc);
                cs.execute();
                //status = cs.getString(3);
                cs.close();
                //System.out.println("Procedure Executed!");

            } catch (Exception e) {
                e.printStackTrace();
            }
        String confirmMsg = "Procedure executed successfully!";
        return confirmMsg;
        }

    public void setHeaderSearchTable(RichColumn headerSearchTable) {
        this.headerSearchTable = headerSearchTable;
    }

    public RichColumn getHeaderSearchTable() {
        return headerSearchTable;
    }

    public void setHeaderSearchPageTable(RichTable headerSearchPageTable) {
        this.headerSearchPageTable = headerSearchPageTable;
    }

    public RichTable getHeaderSearchPageTable() {
        return headerSearchPageTable;
    }
    
    public void commit() {
       BindingContext bc = BindingContext.getCurrent();
       String dcfName = bc.getCurrentDataControlFrame();
       DataControlFrame dcf = bc.findDataControlFrame(dcfName);
       dcf.commit();
    }

    public void setSubmitSendNotificationBean(RichCommandButton submitSendNotificationBean) {
        this.submitSendNotificationBean = submitSendNotificationBean;
    }

    public RichCommandButton getSubmitSendNotificationBean() {
        return submitSendNotificationBean;
    }
}
