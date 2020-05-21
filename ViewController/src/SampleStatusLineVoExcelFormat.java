import java.util.LinkedHashMap;

public class SampleStatusLineVoExcelFormat {
    public SampleStatusLineVoExcelFormat() {
        super();
        
    }
    
private   LinkedHashMap<String , String> sampleStatusLineExcelMap ;

    public LinkedHashMap<String, String> getSampleStatusLineExcelMap() {
        
        
        return sampleStatusLineExcelMap;
        
    }
    
                                    
    
//      LinkedHashMap<String , String> sampleStatusLineExcelFormat1 ,
//                                           sampleStatusLineExcelFormat2,
//                                            sampleStatusLineExcelFormat3,
//                                           sampleStatusLineExcelFormat4;


    public  LinkedHashMap<String , String> getSampleStatusLineExcelMapForFormat(int excelFormat) {
        
       
        
        switch(excelFormat){
            
        case 1 :
             sampleStatusLineExcelMap  =   getExcelMapForFormat1();
            break;
        case 2 :
             sampleStatusLineExcelMap  =   getExcelMapForFormat2();
                break;
         case 3 :
             sampleStatusLineExcelMap  =   getExcelMapForFormat3();
                break;
        
       case 4 :
             sampleStatusLineExcelMap  =   getExcelMapForFormat4();
                break;
        
        case 5 :
            sampleStatusLineExcelMap  =   getExcelMapForFormat5();
               break;
        case 6 :
            sampleStatusLineExcelMap  =   getExcelMapForFormat6();
               break;
            
        }
        
        return sampleStatusLineExcelMap;
        
    }
      


 
    
    

    private LinkedHashMap<String, String> getExcelMapForFormat1() {
        sampleStatusLineExcelMap = new LinkedHashMap<String, String>();
        
        
//        sampleStatusLineExcelMap.put("Buyername", "BUYER NAME" );
//        sampleStatusLineExcelMap.put("Season", "SEASON" );
//         sampleStatusLineExcelMap.put("Brand", "BRAND" ); 
        sampleStatusLineExcelMap.put( "SAMPLE TYPE","SampleType");
        sampleStatusLineExcelMap.put( "STYLE", "StyleNumber");
        sampleStatusLineExcelMap.put("BRAND" , "Brand" );
        sampleStatusLineExcelMap.put("END USER/ DEPT" , "EndUser" );
        sampleStatusLineExcelMap.put( "ITEM DESCRIPTION" , "ItemDescription");
        sampleStatusLineExcelMap.put( "FABRIC SUPPLIER" , "FabricSupplier" );
        sampleStatusLineExcelMap.put( "FABRIC CODE" , "FabricReferenceNo" );
        sampleStatusLineExcelMap.put( "WASH NAME","ColorWash" );
        sampleStatusLineExcelMap.put( "SIZE" , "SizeNumber" );
        sampleStatusLineExcelMap.put("QTY" , "Qty" );
        sampleStatusLineExcelMap.put("HALF LEG QTY" , "HalfLegQty" );
        sampleStatusLineExcelMap.put("TUBE" , "Tube" );
        
        
       sampleStatusLineExcelMap.put("PACKAGE RCVD DATE" , "PackageReceivedDate" ); 
        sampleStatusLineExcelMap.put("QUOTATION REQ DATE" , "QuotationReqDate" ); 
        sampleStatusLineExcelMap.put("BUYER MERCHANDISER" , "BuyerMerchandiser" ); 
        sampleStatusLineExcelMap.put("W/S RCVD" , "WashReceivedDate" );
        sampleStatusLineExcelMap.put("LAST INFO RCVD DATE" , "LastInfoReceivedDate" ); 
        sampleStatusLineExcelMap.put("LAST INFO" , "LastInfo" );
        
        
        sampleStatusLineExcelMap.put("FABRIC COMPOSITION" , "FabricComposition" );
        sampleStatusLineExcelMap.put("WIDTH" , "Width" );
        sampleStatusLineExcelMap.put("WEIGHT" , "WeightValue" );
        sampleStatusLineExcelMap.put("WEIGHT TYPE" , "WeightType" );
        
        sampleStatusLineExcelMap.put("REMARKS" , "Remarks" );
        
        return sampleStatusLineExcelMap;
    }

    private LinkedHashMap<String, String> getExcelMapForFormat2() {
        sampleStatusLineExcelMap = new LinkedHashMap<String, String>();
        
        sampleStatusLineExcelMap.put( "SAMPLE TYPE","SampleType");
        sampleStatusLineExcelMap.put( "STYLE", "StyleNumber");
        //sampleStatusLineExcelMap.put("BRAND" , "Brand" );
        sampleStatusLineExcelMap.put("END USER/ DEPT" , "EndUser" );
        sampleStatusLineExcelMap.put( "ITEM DESCRIPTION" , "ItemDescription");
        sampleStatusLineExcelMap.put( "FABRIC SUPPLIER" , "FabricSupplier" );
        sampleStatusLineExcelMap.put( "FABRIC CODE" , "FabricReferenceNo" );
        sampleStatusLineExcelMap.put( "WASH NAME","ColorWash" );
        sampleStatusLineExcelMap.put( "SIZE" , "SizeNumber" );
        sampleStatusLineExcelMap.put("QTY" , "Qty" );
        sampleStatusLineExcelMap.put("HALF LEG QTY" , "HalfLegQty" );
        sampleStatusLineExcelMap.put("TUBE" , "Tube" );
        
        
  
        
        
        
        sampleStatusLineExcelMap.put("FABRIC COMPOSITION" , "FabricComposition" );
        sampleStatusLineExcelMap.put("WIDTH" , "Width" );
        sampleStatusLineExcelMap.put("WEIGHT" , "WeightValue" );
        sampleStatusLineExcelMap.put("WEIGHT TYPE" , "WeightType" );
        
        sampleStatusLineExcelMap.put("REMARKS" , "Remarks" );
        
        return sampleStatusLineExcelMap;
    }

    private LinkedHashMap<String, String> getExcelMapForFormat3() {
        sampleStatusLineExcelMap = new LinkedHashMap<String, String>();
        
        sampleStatusLineExcelMap.put( "SAMPLE TYPE","SampleType");
        sampleStatusLineExcelMap.put( "STYLE", "StyleNumber");
        sampleStatusLineExcelMap.put("BRAND" , "Brand" );
        sampleStatusLineExcelMap.put("END USER/ DEPT" , "EndUser" );
        sampleStatusLineExcelMap.put( "ITEM DESCRIPTION" , "ItemDescription");
        sampleStatusLineExcelMap.put( "FABRIC SUPPLIER" , "FabricSupplier" );
        sampleStatusLineExcelMap.put( "FABRIC CODE" , "FabricReferenceNo" );
        sampleStatusLineExcelMap.put( "WASH NAME","ColorWash" );
        sampleStatusLineExcelMap.put( "SIZE" , "SizeNumber" );
        sampleStatusLineExcelMap.put("QTY" , "Qty" );
        sampleStatusLineExcelMap.put("HALF LEG QTY" , "HalfLegQty" );
        sampleStatusLineExcelMap.put("TUBE" , "Tube" );
        
        
        
        
        
        
        sampleStatusLineExcelMap.put("FABRIC COMPOSITION" , "FabricComposition" );
        sampleStatusLineExcelMap.put("WIDTH" , "Width" );
        sampleStatusLineExcelMap.put("WEIGHT" , "WeightValue" );
        sampleStatusLineExcelMap.put("WEIGHT TYPE" , "WeightType" );
        
        sampleStatusLineExcelMap.put("REMARKS" , "Remarks" );
        
        return sampleStatusLineExcelMap;
    }

    private LinkedHashMap<String, String> getExcelMapForFormat4() {
        sampleStatusLineExcelMap = new LinkedHashMap<String, String>();
        
        sampleStatusLineExcelMap.put( "SAMPLE TYPE","SampleType");
        sampleStatusLineExcelMap.put( "STYLE", "StyleNumber");
        //sampleStatusLineExcelMap.put("BRAND" , "Brand" );
        sampleStatusLineExcelMap.put("END USER/ DEPT" , "EndUser" );
        sampleStatusLineExcelMap.put( "ITEM DESCRIPTION" , "ItemDescription");
        sampleStatusLineExcelMap.put( "FABRIC SUPPLIER" , "FabricSupplier" );
        sampleStatusLineExcelMap.put( "FABRIC CODE" , "FabricReferenceNo" );
        sampleStatusLineExcelMap.put( "WASH NAME","ColorWash" );
        sampleStatusLineExcelMap.put( "SIZE" , "SizeNumber" );
        sampleStatusLineExcelMap.put("QTY" , "Qty" );
        sampleStatusLineExcelMap.put("HALF LEG QTY" , "HalfLegQty" );
        sampleStatusLineExcelMap.put("TUBE" , "Tube" );
        
        
        
        
        
        
        sampleStatusLineExcelMap.put("FABRIC COMPOSITION" , "FabricComposition" );
        sampleStatusLineExcelMap.put("WIDTH" , "Width" );
        sampleStatusLineExcelMap.put("WEIGHT" , "WeightValue" );
        sampleStatusLineExcelMap.put("WEIGHT TYPE" , "WeightType" );
        
        sampleStatusLineExcelMap.put("REMARKS" , "Remarks" );
        
        return sampleStatusLineExcelMap;
    }
    
    private LinkedHashMap<String, String> getExcelMapForFormat5() {
        sampleStatusLineExcelMap = new LinkedHashMap<String, String>();
        
        sampleStatusLineExcelMap.put( "SERIAL","Serial");
        sampleStatusLineExcelMap.put( "SAMPLE TYPE","SampleType");
        sampleStatusLineExcelMap.put( "STYLE", "StyleNumber");
        sampleStatusLineExcelMap.put( "SPEC PATTERN","SpecificationPattern");
        sampleStatusLineExcelMap.put( "FABRIC SUPPLIER" , "FabricSupplier" );
        sampleStatusLineExcelMap.put( "FABRIC CODE" , "FabricReferenceNo" );
        sampleStatusLineExcelMap.put( "WASH NAME","ColorWash" );
        sampleStatusLineExcelMap.put( "SIZE" , "SizeNumber" );
        sampleStatusLineExcelMap.put("QTY" , "Qty" );
        sampleStatusLineExcelMap.put("HALF LEG QTY" , "HalfLegQty" );
        
        sampleStatusLineExcelMap.put("TUBE" , "Tube" );
        sampleStatusLineExcelMap.put("MOCKUP QTY" , "MockupQty" );
        
        sampleStatusLineExcelMap.put("END USER/ DEPT" , "EndUser" );
        
        sampleStatusLineExcelMap.put("SAMPLE DEADLINE DATE" , "SampleDeadlineDate" );
        sampleStatusLineExcelMap.put("THREAD" , "Thread" );
        sampleStatusLineExcelMap.put("BUTTON" , "Button" );
        sampleStatusLineExcelMap.put("RIVET" , "Rivet" );
        sampleStatusLineExcelMap.put("ZIPPER" , "Zipper" );
        sampleStatusLineExcelMap.put("SPECIAL DETAILING" , "SpecialDetailing" );
        sampleStatusLineExcelMap.put("YDS STATUS" , "YdsStatus" );
        
        sampleStatusLineExcelMap.put("STYLE DESCRIPTION" , "StyleDescription" );
        sampleStatusLineExcelMap.put("DESIGN SHEET" , "DesignSheet" );
        sampleStatusLineExcelMap.put("REMARKS" , "Remarks" );
        
        return sampleStatusLineExcelMap;
        
        
    }
    
    private LinkedHashMap<String, String> getExcelMapForFormat6() {
        sampleStatusLineExcelMap = new LinkedHashMap<String, String>();
        
        sampleStatusLineExcelMap.put( "SAMPLE TYPE","SampleType");
        sampleStatusLineExcelMap.put( "STYLE", "StyleNumber");
        sampleStatusLineExcelMap.put("BRAND" , "Brand" );
        sampleStatusLineExcelMap.put("END USER/ DEPT" , "EndUser" );
        sampleStatusLineExcelMap.put( "ITEM DESCRIPTION" , "ItemDescription");
        sampleStatusLineExcelMap.put( "FABRIC SUPPLIER" , "FabricSupplier" );
        sampleStatusLineExcelMap.put( "FABRIC CODE" , "FabricReferenceNo" );
        sampleStatusLineExcelMap.put( "WASH NAME","ColorWash" );
        sampleStatusLineExcelMap.put( "POCKETING LEFT","PocketingLeft" );
        sampleStatusLineExcelMap.put( "POCKETING RIGHT","PocketingRight" );
        sampleStatusLineExcelMap.put( "INSIDE WB","InsideWb" );
        sampleStatusLineExcelMap.put( "SIZE" , "SizeNumber" );
        sampleStatusLineExcelMap.put("QTY" , "Qty" );
        sampleStatusLineExcelMap.put("HALF LEG QTY" , "HalfLegQty" );
        sampleStatusLineExcelMap.put("TUBE" , "Tube" );
        sampleStatusLineExcelMap.put("FABRIC COMPOSITION" , "FabricComposition" );
        sampleStatusLineExcelMap.put("WIDTH" , "Width" );
        sampleStatusLineExcelMap.put("WEIGHT" , "WeightValue" );
        sampleStatusLineExcelMap.put("WEIGHT TYPE" , "WeightType" );
        
        sampleStatusLineExcelMap.put("REMARKS" , "Remarks" );
        
        return sampleStatusLineExcelMap;
    }
    
}
