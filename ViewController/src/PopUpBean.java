import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.PopupCanceledEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class PopUpBean {
    private RichOutputText noteWindowTextNone;

    public PopUpBean() {
    }

    public void setNoteWindowTextNone(RichOutputText noteWindowTextNone) {
        this.noteWindowTextNone = noteWindowTextNone;
    }

    public RichOutputText getNoteWindowTextNone() {
        return noteWindowTextNone;
    }
    
    public BindingContainer getBindings() {
           return BindingContext.getCurrent().getCurrentBindingsEntry();
       }

    public void AttachFetchListener(PopupFetchEvent popupFetchEvent) {
        BindingContainer bindings = getBindings();
             OperationBinding operationBinding =
                 bindings.getOperationBinding("getAttachments");         

             operationBinding.execute();
             
             if (!operationBinding.getErrors().isEmpty()) {
                 System.out.println("errore-->");
             }
            Object methodReturnValue = operationBinding.getResult();       
            noteWindowTextNone.setValue(methodReturnValue);
            AdfFacesContext.getCurrentInstance().addPartialTarget(noteWindowTextNone);
    }

    public void AttachCancelListener(PopupCanceledEvent popupCanceledEvent) {
        // Add event code here...
    }

    public void SelectAllRequisitions(ActionEvent actionEvent) {
        // Add event code here...
    }
}
