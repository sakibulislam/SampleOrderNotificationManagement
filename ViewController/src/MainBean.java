import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.StringTokenizer;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpSession;

import model.services.AppModuleImpl;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCDataControl;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.util.ResetUtils;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;

import oracle.jdbc.OracleTypes;

import org.apache.myfaces.trinidad.model.UploadedFile;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class MainBean {
    private RichTable statusLineDetailTable;
    private RichTable courierEntryTable;
    private String sampleReqNo = "";

    public MainBean() {
        
        //System.out.println("Main bean is initialized ");
    }


    public ApplicationModule getAppM() {
        DCBindingContainer bindingContainer =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        //BindingContext bindingContext = BindingContext.getCurrent();
        DCDataControl dc =
            bindingContainer.findDataControl("AppModuleDataControl"); // Name of application module in datacontrolBinding.cpx
        AppModuleImpl appM = (AppModuleImpl)dc.getDataProvider();
        return appM;
    }
    AppModuleImpl appM = (AppModuleImpl)this.getAppM();


    
   

    public void setStatusLineDetailTable(RichTable statusLineDetailTable) {
        this.statusLineDetailTable = statusLineDetailTable;
    }

    public RichTable getStatusLineDetailTable() {
        return statusLineDetailTable;
    }

    public void save(ActionEvent actionEvent) {
        // Add event code here...
        try {
            appM.getDBTransaction().commit();
            System.out.println("Commit Executed @Save");
        } catch (Exception e) {
            
            e.printStackTrace();
            appM.getDBTransaction().commit();
            System.out.println("Commit Executed @Exception");
        }
       
        
    }


    public void callAttachment(ActionEvent actionEvent) {
        System.out.println("@into callAttachment Button Action!");
    }

    public String callAttachment() throws IOException {
        System.out.println("@Into call Attachment method!");
        String doc= null;     
//        BindingContext bindingContext = BindingContext.getCurrent(); 
//        DCDataControl dc = bindingContext.findDataControl("AppModuleDataControl"); 
//        ApplicationModule am  = dc.getApplicationModule() ;
        ViewObject findViewObject =  appM.findViewObject("MnjOntSampleStatusVO1");
        
        try {
            doc = findViewObject.getCurrentRow().getAttribute("SampleDocNo").toString();
            System.out.println("====================================   doc "+doc);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
            
        }    
        
        
     
     
        String newPage =
         "http://192.168.200.115:7003/FileUploading-ViewController-context-root/faces/view1?doc=Sample_Req_No_&docNo=" + doc;
        // String newPage = "http://localhost:7101/PurchaseMemo-ViewController-context-root/faces/SearchPG?headerId="+getBomId().getValue();
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks = Service.getService(ctx.getRenderKit(), ExtendedRenderKitService.class);
        String url = "window.open('" + newPage + "','_blank','toolbar=no,location=no,menubar=no,alwaysRaised=yes,height=500,width=1100');";
        erks.addScript(FacesContext.getCurrentInstance(), url);
     
     
     
        
//        
             
        System.out.println("Attachment Appears!");
            
            return null;
    }

   public String callCourierEntry() throws IOException{
        System.out.println("@Into call Courier Entry....");
             
       String newPage =
         "http://192.168.200.115:7003/CourierInformation-ViewController-context-root/faces/SearchPG" ;
        
        // String newPage = "http://localhost:7101/PurchaseMemo-ViewController-context-root/faces/SearchPG?headerId="+getBomId().getValue();
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks = Service.getService(ctx.getRenderKit(), ExtendedRenderKitService.class);
        String url = "window.open('" + newPage + "','_blank','toolbar=no,location=no,menubar=no,alwaysRaised=yes,height=500,width=1100');";
        erks.addScript(FacesContext.getCurrentInstance(), url);

             
        System.out.println("Courier Entry Appears!");
            
        return null;  
    }
    
    public Row createCourierLines() {

        ViewObject CourierVO = appM.getMnjCourierInformationVO1();
        Row row =
        CourierVO.createRow(); //Creates a new Row object, but does not insert it into the Row Set
        CourierVO.insertRow(row); //Inserts a row to the Row Set, before the current row
        row.setNewRowState(Row.STATUS_INITIALIZED); //Sets a new unposted row, created in this transaction,
        //to either STATUS_NEW or STATUS_INITIALIZED(new row but temporary row)
        //mode
//        appM.getDBTransaction().commit();
        return row;
    }

    

    public void populate(ActionEvent actionEvent) {
        System.out.println("Into populate courier entry import..........");
        
        try {
            ViewObject vo = appM.getMnjOntSampleStatusLineVO1();
            RowSetIterator it = vo.createRowSetIterator("aa");
            
        
            
            while(it.hasNext()){
                    Row currentRow = it.next();
                    System.out.println("LineId: " + currentRow.getAttribute("LineId"));
                    setValuesInCourierEntryImport(currentRow); //method for inserting data into new form named Courier entry
                }
            it.closeRowSetIterator();
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        //setValuesInCourierEntryImport();
        courierEntryImportFormAppears();
        
   
        
    }
    
    public void  setValuesInCourierEntryImport(Row currentRow){
        
        
        System.out.println(" Into setValuesInCourierEntryImport...............");
        
        ViewObject HeaderVO =  appM.getMnjOntSampleStatusVO1();
//        ViewObject LineVO = appM.getMnjOntSampleStatusLineVO1();
        
        
        Row GetCurrentHeaderRow = HeaderVO.getCurrentRow();
//        Row GetCurrentLineRow = LineVO.getCurrentRow();
        
        
        String Buyer = GetCurrentHeaderRow.getAttribute("Buyername").toString();
        System.out.println("   " + Buyer);
        String BuyerId = GetCurrentHeaderRow.getAttribute("BuyerId").toString();
        System.out.println("   " + BuyerId);
        
        String UserName = GetCurrentHeaderRow.getAttribute("CreatedByUserName").toString();
        System.out.println("   " + UserName);
        String Season = GetCurrentHeaderRow.getAttribute("Season").toString();
        System.out.println("   " + Season);
        String Style = currentRow.getAttribute("StyleNumber").toString();
        System.out.println("   " + Style);
        sampleReqNo = currentRow.getAttribute("SampleDocNo").toString();
        System.out.println("   " + sampleReqNo);
        int CreatedBy = Integer.parseInt(GetCurrentHeaderRow.getAttribute("CreatedBy").toString());
        System.out.println("   " + CreatedBy);
        int lineId = Integer.parseInt(currentRow.getAttribute("LineId").toString());
        System.out.println("   LineId: " + lineId);
        
        try {
            
            Row CourierRow = createCourierLines();
            System.out.println("       @Into create courier lines....");
            CourierRow.setAttribute("CreatedBy", CreatedBy);
            
            CourierRow.setAttribute("SampleDocNo", sampleReqNo);
            CourierRow.setAttribute("ImportExport", "Import" );
            CourierRow.setAttribute("Buyer", Buyer );
            CourierRow.setAttribute("BuyerId", BuyerId );
        
            CourierRow.setAttribute("Season", Season );
            CourierRow.setAttribute("UserName", UserName );
            CourierRow.setAttribute("StyleNumber", Style );
        
            appM.getDBTransaction().commit();
        
        }catch(Exception e){
            e.printStackTrace();
            }
        
    }
    
    public void courierEntryImportFormAppears(){
            try{
                
                
                
                System.out.println("In courierEntryImportAppears form....... ");
                String newPage =
                 "http://192.168.200.115:7003/CourierInformation-ViewController-context-root/faces/SearchPG?sampleDocNo=" + sampleReqNo ;
                
                // String newPage = "http://localhost:7101/PurchaseMemo-ViewController-context-root/faces/SearchPG?headerId="+getBomId().getValue();
                FacesContext ctx = FacesContext.getCurrentInstance();
                ExtendedRenderKitService erks = Service.getService(ctx.getRenderKit(), ExtendedRenderKitService.class);
                String url = "window.open('" + newPage + "','_blank','toolbar=no,location=no,menubar=no,alwaysRaised=yes,height=600,width=1100');";
                erks.addScript(FacesContext.getCurrentInstance(), url);

                     
                System.out.println(" Courier Entry Import Appears!");
                
                AdfFacesContext.getCurrentInstance().addPartialTarget(courierEntryTable);
                
            } catch (Exception e) {
                
                e.printStackTrace();
            }
        }

    public void setCourierEntryTable(RichTable courierEntryTable) {
        this.courierEntryTable = courierEntryTable;
    }

    public RichTable getCourierEntryTable() {
        return courierEntryTable;
    }

    public void callCourierEntry(DialogEvent dialogEvent) {
        System.out.println("@Into call Courier Entry....");
        if (dialogEvent.getOutcome().name().equals("ok")) {
            System.out.println();
            System.out.println("In Dialog Event @OK ");
            String newPage =
             "http://192.168.200.115:7003/CourierInformation-ViewController-context-root/faces/SearchPG" ;
            
            // String newPage = "http://localhost:7101/PurchaseMemo-ViewController-context-root/faces/SearchPG?headerId="+getBomId().getValue();
            FacesContext ctx = FacesContext.getCurrentInstance();
            ExtendedRenderKitService erks = Service.getService(ctx.getRenderKit(), ExtendedRenderKitService.class);
            String url = "window.open('" + newPage + "','_blank','toolbar=no,location=no,menubar=no,alwaysRaised=yes,height=500,width=1100');";
            erks.addScript(FacesContext.getCurrentInstance(), url);

                 
            System.out.println("Courier Entry Appears!");
        }
             
        
    }

    public void SelectAllRequisitions(ActionEvent actionEvent) {
        // Add event code here...
    }

    public void courierEntryExportAppears(ActionEvent actionEvent) {
        try {
            
            
            ViewObject vo = appM.getMnjOntSampleStatusVO1();
            String userId = vo.getCurrentRow().getAttribute("CreatedBy").toString();
            System.out.println("UserId: " + userId);
            
            
            System.out.println("In courierEntryExportAppears.... ");
            String newPage =
             "http://192.168.200.115:7003/CourierInformation-ViewController-context-root/faces/ExportPG?userId=" + userId ;
            
            // String newPage = "http://localhost:7101/PurchaseMemo-ViewController-context-root/faces/SearchPG?headerId="+getBomId().getValue();
            FacesContext ctx = FacesContext.getCurrentInstance();
            ExtendedRenderKitService erks = Service.getService(ctx.getRenderKit(), ExtendedRenderKitService.class);
            String url = "window.open('" + newPage + "','_blank','toolbar=no,location=no,menubar=no,alwaysRaised=yes,height=600,width=1100');";
            erks.addScript(FacesContext.getCurrentInstance(), url);

                 
            System.out.println(" Courier Entry Export Appears!");
            
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }

    public String callAttachment2() {
        System.out.println("@Into call Attachment method!");
        String doc= null;     
        //        BindingContext bindingContext = BindingContext.getCurrent();
        //        DCDataControl dc = bindingContext.findDataControl("AppModuleDataControl");
        //        ApplicationModule am  = dc.getApplicationModule() ;
        ViewObject findViewObject =  appM.findViewObject("MnjOntSampleStatusViewOnlyVO1");
        
        try {
            doc = findViewObject.getCurrentRow().getAttribute("SampleDocNo").toString();
            System.out.println("====================================   doc "+doc);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
            
        }    
        
        
        
        
        String newPage =
         "http://192.168.200.115:7003/FileUploading-ViewController-context-root/faces/view1?doc=Sample_Req_No_&docNo=" + doc;
        // String newPage = "http://localhost:7101/PurchaseMemo-ViewController-context-root/faces/SearchPG?headerId="+getBomId().getValue();
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks = Service.getService(ctx.getRenderKit(), ExtendedRenderKitService.class);
        String url = "window.open('" + newPage + "','_blank','toolbar=no,location=no,menubar=no,alwaysRaised=yes,height=500,width=1100');";
        erks.addScript(FacesContext.getCurrentInstance(), url);
        
        
        
        
        //
             
        System.out.println("Attachment Appears!");
            
            return null;    
    }

    public void copyLineButtonAction(ActionEvent actionEvent) {
        System.out.println("Enter into copyLineButtonAction()......");
        try {
                  
           insetIntoSampleLineVo(appM.getMnjOntSampleStatusLineVO1());
            
        } catch (Exception e) {
           
            e.printStackTrace();
        }
    }
    
    public void insetIntoSampleLineVo(ViewObject vo){
        System.out.println("Enter into insetIntoSampleLineVo().....");
        Row currentRow = vo.getCurrentRow();
        Row cratedRow = vo.createRow();
        
        cratedRow.setAttribute("Serial", currentRow.getAttribute("Serial"));
        cratedRow.setAttribute("SampleType", currentRow.getAttribute("SampleType"));
        cratedRow.setAttribute("StyleNumber", currentRow.getAttribute("StyleNumber"));
        cratedRow.setAttribute("SpecificationPattern", currentRow.getAttribute("SpecificationPattern"));
        cratedRow.setAttribute("ItemDescription", currentRow.getAttribute("ItemDescription"));
        cratedRow.setAttribute("FabricSupplier", currentRow.getAttribute("FabricSupplier"));
        cratedRow.setAttribute("FabricReferenceNo", currentRow.getAttribute("FabricReferenceNo"));
        cratedRow.setAttribute("ColorWash", currentRow.getAttribute("ColorWash"));
        cratedRow.setAttribute("SizeNumber", currentRow.getAttribute("SizeNumber"));
        cratedRow.setAttribute("Qty", currentRow.getAttribute("Qty"));
        cratedRow.setAttribute("HalfLegQty", currentRow.getAttribute("HalfLegQty"));
        cratedRow.setAttribute("Tube", currentRow.getAttribute("Tube"));
        cratedRow.setAttribute("MockupQty", currentRow.getAttribute("MockupQty"));
        cratedRow.setAttribute("Brand", currentRow.getAttribute("Brand"));
        cratedRow.setAttribute("FabricComposition", currentRow.getAttribute("FabricComposition"));
        cratedRow.setAttribute("EndUser", currentRow.getAttribute("EndUser"));
        cratedRow.setAttribute("SampleDeadlineDate", currentRow.getAttribute("SampleDeadlineDate"));
        cratedRow.setAttribute("PackageReceivedDate", currentRow.getAttribute("PackageReceivedDate"));
        cratedRow.setAttribute("QuotationReqDate", currentRow.getAttribute("QuotationReqDate"));
        cratedRow.setAttribute("BuyerMerchandiser", currentRow.getAttribute("BuyerMerchandiser"));
        cratedRow.setAttribute("WashReceivedDate", currentRow.getAttribute("WashReceivedDate"));
        cratedRow.setAttribute("LastInfoReceivedDate", currentRow.getAttribute("LastInfoReceivedDate"));
        cratedRow.setAttribute("LastInfo", currentRow.getAttribute("LastInfo"));
        cratedRow.setAttribute("PocketingLeft", currentRow.getAttribute("PocketingLeft"));
        cratedRow.setAttribute("PocketingRight", currentRow.getAttribute("PocketingRight"));
        cratedRow.setAttribute("InsideWb", currentRow.getAttribute("InsideWb"));
        cratedRow.setAttribute("Thread", currentRow.getAttribute("Thread"));
        cratedRow.setAttribute("Button", currentRow.getAttribute("Button"));
        cratedRow.setAttribute("Rivet", currentRow.getAttribute("Rivet"));
        cratedRow.setAttribute("Zipper", currentRow.getAttribute("Zipper"));
        cratedRow.setAttribute("SpecialDetailing", currentRow.getAttribute("SpecialDetailing"));
        cratedRow.setAttribute("YdsStatus", currentRow.getAttribute("YdsStatus"));
        cratedRow.setAttribute("Width", currentRow.getAttribute("Width"));
        cratedRow.setAttribute("WeightValue", currentRow.getAttribute("WeightValue"));
        cratedRow.setAttribute("WeightType", currentRow.getAttribute("WeightType"));
        cratedRow.setAttribute("StyleDescription", currentRow.getAttribute("StyleDescription"));
        cratedRow.setAttribute("DesignSheet", currentRow.getAttribute("DesignSheet"));
        cratedRow.setAttribute("Remarks", currentRow.getAttribute("Remarks"));

        
        
        vo.insertRow(cratedRow);
        
    }
}
