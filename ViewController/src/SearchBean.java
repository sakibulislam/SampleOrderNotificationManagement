import javax.faces.event.ActionEvent;

import model.services.AppModuleImpl;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCDataControl;

import oracle.jbo.ApplicationModule;
import oracle.jbo.ViewObject;

public class SearchBean {
    public SearchBean() {
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

    public void filterHeader(ActionEvent actionEvent) {
        ViewObject HeaderView =  am.getMnjOntSampleStatusVO1();
        ViewObject SearchView = am.getSearchView1();
        String buyer;
        String season;
        
                try{
                   buyer=SearchView.getCurrentRow().getAttribute("Buyer").toString();
                   
                }
                catch(Exception e) {
                  buyer = "novalue" ;
                  
                }
                  try{
                       season=SearchView.getCurrentRow().getAttribute("Season").toString();
                  }
                  catch(Exception e) {
                      season = "novalue";
                      
                  }
                  
                System.out.println(".........................................."+buyer); 
                System.out.println(".........................................."+season); 
                try{
                  
                    if ( buyer.equals("novalue")){
                            System.out.println("@Into novalue condition");
                            HeaderView.setWhereClause(null);
                        }
                  
                    else { 
                        System.out.println("@Into else condition");
                    HeaderView.setWhereClause("BUYERNAME = '"+ buyer +"' AND SEASON = '"+ season +"'" );
                    }
                    
                
                     HeaderView.executeQuery();
                  
                } catch (Exception e){
                    e.printStackTrace();
                  }
    }
                  
}
