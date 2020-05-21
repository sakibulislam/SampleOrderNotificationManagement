import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.InputStreamReader;

import java.io.ObjectOutputStream;
import java.io.OutputStream;

import java.io.OutputStreamWriter;

import java.io.UnsupportedEncodingException;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.*;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCDataControl;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichSelectOneListbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.model.UploadedFile;

import java.util.Date;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import model.services.AppModuleImpl;

import oracle.adf.model.binding.DCBindingContainer;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.server.ViewObjectImpl;

import oracle.jbo.server.ViewRowImpl;

import oracle.ods.virtualization.engine.Entry;

import oracle.sqlj.runtime.Oracle;

public class UploadBean {
    private RichTable statusLineDetailTable;
    private RichInputFile merReqFileUpload;
    private RichSelectOneRadio excelFormatChoiceBind;
    private RichSelectOneListbox excelListBox;
    private UISelectItems excelFormatChVal;

    public UploadBean() {

        //System.out.println("upload bean is initialized ");
    }


    SampleStatusLineVoExcelFormat SampleStatusLineVoExcelFormatObj =
        new SampleStatusLineVoExcelFormat();


    private LinkedHashMap<String, String> sampleStatLineVoExcelMap;


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


    public void fileUploadMaker(ValueChangeEvent valueChangeEvent) {


        UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();
        try {

            parseFile(file.getInputStream());
            
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(statusLineDetailTable);

        } catch (Exception e) {
            
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


        merReqFileUpload.setValue(null);
        AdfFacesContext.getCurrentInstance().addPartialTarget(merReqFileUpload);


    }


    public void parseFile(java.io.InputStream file) {

        BufferedReader reader =
            new BufferedReader(new InputStreamReader(file));
        String strLine;

        int lineNumber = 0;
        Row hrw = null, lineRow = null;


        ViewObject lineVo = appM.getMnjOntSampleStatusLineVO1();


        String[] excelRowColumnHeaders = null, excelRowColumnValues = null;
        String colHeader, colValue, lineVoAttrName;
        
        
        sampleStatLineVoExcelMap =SampleStatusLineVoExcelFormatObj.getSampleStatusLineExcelMapForFormat(this.getExcelFormat());
        // sampleStatLineVoExcelMap contains the csv format that user wants to  upload 
          
     if( sampleStatLineVoExcelMap.keySet().size()==0 ){
           
            showMessage("Excel format not generated yet " , "info" );
           return;
        }


        try {
            while ((strLine = reader.readLine()) != null) {

                lineNumber++;
             
                // the first line is excel header row
                if (lineNumber == 1) {

                    // excelRowColumnHeaders = new String[100];
                     excelRowColumnHeaders = strLine.split(",");

                }

                // rest of the lines are values
                else if (lineNumber > 1) {

                    lineRow = lineVo.createRow();
                    
                

                    excelRowColumnValues = strLine.split(",");
                  
                       

                    for (int i = 0; i < excelRowColumnValues.length; i++) {

                        colHeader = excelRowColumnHeaders[i];
                        
                        colValue = excelRowColumnValues[i];

                        lineVoAttrName = sampleStatLineVoExcelMap.get(colHeader);
                        
                        

                     
                        System.out.println("===========================     i   " +i);
                       System.out.println("===========================     colHeader   " + colHeader);
                           System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><<><><><><><><><>><><><><" );
                        System.out.println("===========================     lineVoAttrName   " +  lineVoAttrName);
                  
                         System.out.println("===========================      colValue   " + colValue);
                        
                             
                             lineRow.setAttribute(lineVoAttrName, colValue);
                             

                        try {
                            //  no mapping  attribute found for excel header
                            if (!lineVoAttrName.equals(null)) {
                                    
                                    
                           
                                    oracle.jbo.domain.Date d =  castToJBODate( colValue) ;
                                    if(d==null){
                                        lineRow.setAttribute(lineVoAttrName, colValue);
                                    }
                                    else{
                                        lineRow.setAttribute(lineVoAttrName, d); 
                                    }
                                    
                                                               
                                
                            }


                        } catch (Exception e) {     
                            
                           e.printStackTrace();

                        }


                    }


                }

            }


            reader = null;

        } catch (Exception e) {

            e.printStackTrace();
            FacesContext fctx = FacesContext.getCurrentInstance();
            fctx.addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data Error in Uploaded file",
                                             e.getMessage()));

        }

    }

    public void setStatusLineDetailTable(RichTable statusLineDetailTable) {
        this.statusLineDetailTable = statusLineDetailTable;
    }

    public RichTable getStatusLineDetailTable() {
        return statusLineDetailTable;
    }

    /**
     *Converts a String to oracle.jbo.domain.Date
     * @param String
     * @return oracle.jbo.domain.Date
     */
    public oracle.jbo.domain.Date castToJBODate(String aDate) {


        DateFormat formatter;

        java.util.Date date;

        if (aDate != null) {

            try {

                formatter = new SimpleDateFormat("dd-MMM-yy");
                date = formatter.parse(aDate);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                oracle.jbo.domain.Date jboDate =
                    new oracle.jbo.domain.Date(sqlDate);

                System.out.println("#### Date: ####" + jboDate);
                return jboDate;
            } catch (Exception e) {

              //  e.printStackTrace();
                ;
            }

        }

        return null;
    }


    private String replaceEmptyTextWithDummy(String strline) {
        System.out.println();

        String newString;

        System.out.println("=================================in  replaceEmptyTextWithDummy  method  ");
        String[] stringLine = strline.split(",");

        for (int count = 0; count < stringLine.length; count++) {
            System.out.println();
            System.out.println("strLine[] index number --->" + count +
                               ", value ---> " + stringLine[count]);
            System.out.println();

        }

        StringBuilder sb = new StringBuilder();
    

        for (String str : stringLine) {

            if (str.equals("")) {
                str = "Dummytext";

            }
            System.out.println();

            sb.append(str);
            sb.append(",");


            System.out.println();
        }
        System.out.println();
        System.out.println(sb.length());
        System.out.println();
        sb.deleteCharAt(sb.length() - 1);


        newString = sb.toString();

        return newString;

    }


    private void setRowAttributeValue(Row hrw, String attributeName,
                                      String theToken) {

        if (!theToken.equals("Dummytext")) {
            hrw.setAttribute(attributeName, theToken);
        }


    }

    private void setRowAttributeValue(Row hrw, String attributeName,
                                      oracle.jbo.domain.Date theToken) {

        if (!theToken.equals("Dummytext")) {
            hrw.setAttribute(attributeName, theToken);
        }


    }

    public void setMerReqFileUpload(RichInputFile merReqFileUpload) {
        this.merReqFileUpload = merReqFileUpload;
    }

    public RichInputFile getMerReqFileUpload() {
        return merReqFileUpload;
    }

    public void customSampleReqCsvFileDownload(FacesContext facesContext,
                                               OutputStream outputStream) throws IOException {
        // Add event code here...


        createExcelFile(outputStream);


    }

    public void setExcelFormatChoiceBind(RichSelectOneRadio excelFormatChoiceBind) {
        this.excelFormatChoiceBind = excelFormatChoiceBind;
    }

    public RichSelectOneRadio getExcelFormatChoiceBind() {
        return excelFormatChoiceBind;
    }

    private void createExcelFile(OutputStream outputStream) throws IOException {

        BufferedWriter writer;

        int excelFormatNo = this.getExcelFormat();


        writer =
                new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

        switch (excelFormatNo) {

        case 1:

            sampleStatLineVoExcelMap =
                    SampleStatusLineVoExcelFormatObj.getSampleStatusLineExcelMapForFormat(excelFormatNo);

           // System.out.println("===========   sampleStatLineVoExcelMap.keySet().size()" +
             //                  sampleStatLineVoExcelMap.keySet().size());

            createExcelFileForFormat(sampleStatLineVoExcelMap , writer );

            break;


        case 2:

            sampleStatLineVoExcelMap =
                    SampleStatusLineVoExcelFormatObj.getSampleStatusLineExcelMapForFormat(excelFormatNo);
            
        
            
            createExcelFileForFormat(sampleStatLineVoExcelMap, writer);

            break;

        case 3:


            sampleStatLineVoExcelMap =
                    SampleStatusLineVoExcelFormatObj.getSampleStatusLineExcelMapForFormat(excelFormatNo);

            createExcelFileForFormat(sampleStatLineVoExcelMap, writer);
            break;


        case 4:
            sampleStatLineVoExcelMap =
                    SampleStatusLineVoExcelFormatObj.getSampleStatusLineExcelMapForFormat(excelFormatNo);

            createExcelFileForFormat(sampleStatLineVoExcelMap, writer);
            break;
        
            case 5:
                sampleStatLineVoExcelMap =
                        SampleStatusLineVoExcelFormatObj.getSampleStatusLineExcelMapForFormat(excelFormatNo);

                createExcelFileForFormat(sampleStatLineVoExcelMap, writer);
                break;
        
            case 6:

                sampleStatLineVoExcelMap =
                        SampleStatusLineVoExcelFormatObj.getSampleStatusLineExcelMapForFormat(excelFormatNo);

               // System.out.println("===========   sampleStatLineVoExcelMap.keySet().size()" +
                 //                  sampleStatLineVoExcelMap.keySet().size());

                createExcelFileForFormat(sampleStatLineVoExcelMap , writer );

                break;

        default:
            break;

        }


    }

    private int getExcelFormat() {

          String  format = appM.getMnjOntSampleStatusVO1().getCurrentRow().getAttribute("ExcelFormatChoice").toString();
        System.out.println("Format ---> " + format);
        /*  radio button index starts from 0 , 1 is added to start index from 1 so that 1 is returned for Format 1  */
        System.out.println("Format Value ---> " + (Integer)this.excelFormatChoiceBind.getValue());
        return (Integer)this.excelFormatChoiceBind.getValue() + 1;

    }


    private void createExcelFileForFormat(LinkedHashMap<String, String> sampleStatLineVoExcelMap,
                                          BufferedWriter writer) throws IOException {


        Iterator it;
        it = sampleStatLineVoExcelMap.entrySet().iterator();
        while (it.hasNext()) {

            Map.Entry entrySet = (Map.Entry)it.next();
            writer.write(entrySet.getKey().toString());
            writer.write(",");


        }

        writer.newLine();
        writer.flush();
        writer.close();
    


    }
    
    
    public  void showMessage(String messege , String severity ) {
        
        
        FacesMessage fm = new FacesMessage(messege);
        
        if(severity.equals("info")){
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
        }
        else if(severity.equals("warn")){
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
        }
        else if(severity.equals("error")){
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
        }
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
        
    }


    public void SelectAllRequisitions(ActionEvent actionEvent) {
        OperationBinding operationBinding = executeOperation("SelectAllRequisitions");
        operationBinding.getParamsMap().put("flag", "Y"); // key --> flag value ---> Y
        
        System.out.println("Call Select All " );
        operationBinding.execute();
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(statusLineDetailTable);
    }
    
    /**
     * Generic Method to execute operation
     * */
    public OperationBinding executeOperation(String operation) {
        OperationBinding createParam =
            getBindingsCont().getOperationBinding(operation);


        return createParam;

    }
    
    /*****Generic Method to Get BindingContainer**/
    public BindingContainer getBindingsCont() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void deSelectAllRequisitions(ActionEvent actionEvent) {
        OperationBinding operationBinding = executeOperation("SelectAllRequisitions");
                operationBinding.getParamsMap().put("flag", "N");
                
                System.out.println();
                System.out.println("Call De-Select All " );
                operationBinding.execute();
                AdfFacesContext.getCurrentInstance().addPartialTarget(statusLineDetailTable);
    }

    public void deleteOnCondition(ActionEvent actionEvent) {
        ViewObject lineVO =  appM.getMnjOntSampleStatusLineVO1();
                
                Row[] rows = lineVO.getAllRowsInRange();
                try {
                    for(Row row : rows){
                        
                            row.remove();
                            System.out.println("Remove Row Successfully....");
                        
                        
                    }
                    
                   
                } catch (Exception e) {
                    
                    e.printStackTrace();
                }
        AdfFacesContext.getCurrentInstance().addPartialTarget(statusLineDetailTable);      
    }
}
