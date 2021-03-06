package model.services;

import java.sql.CallableStatement;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import model.services.common.AppModule;

import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewLinkImpl;


import javax.servlet.http.HttpSession;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;

import oracle.jbo.server.ViewObjectImpl;

import oracle.jdbc.OracleTypes;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Sep 03 11:08:20 BDT 2018
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class AppModuleImpl extends ApplicationModuleImpl implements AppModule {
    /**
     * This is the default constructor (do not remove).
     */
    public AppModuleImpl() {
    }

    /**
     * Container's getter for MnjOntSampleStatusVO1.
     * @return MnjOntSampleStatusVO1
     */
    public ViewObjectImpl getMnjOntSampleStatusVO1() {
        return (ViewObjectImpl)findViewObject("MnjOntSampleStatusVO1");
    }

    /**
     * Container's getter for MnjOntSampleStatusLineVO1.
     * @return MnjOntSampleStatusLineVO1
     */
    public ViewObjectImpl getMnjOntSampleStatusLineVO1() {
        return (ViewObjectImpl)findViewObject("MnjOntSampleStatusLineVO1");
    }

    /**
     * Container's getter for MnjOntSampleStatusDetailVO1.
     * @return MnjOntSampleStatusDetailVO1
     */
    public ViewObjectImpl getMnjOntSampleStatusDetailVO1() {
        return (ViewObjectImpl)findViewObject("MnjOntSampleStatusDetailVO1");
    }

    /**
     * Container's getter for BuyerLOV1.
     * @return BuyerLOV1
     */
    public ViewObjectImpl getBuyerLOV1() {
        return (ViewObjectImpl)findViewObject("BuyerLOV1");
    }

    /**
     * Container's getter for SeasonLOV1.
     * @return SeasonLOV1
     */
    public ViewObjectImpl getSeasonLOV1() {
        return (ViewObjectImpl)findViewObject("SeasonLOV1");
    }

    /**
     * Container's getter for MnjMfgSamplinFkLink1.
     * @return MnjMfgSamplinFkLink1
     */
    public ViewLinkImpl getMnjMfgSamplinFkLink1() {
        return (ViewLinkImpl)findViewLink("MnjMfgSamplinFkLink1");
    }

    /**
     * Container's getter for MnjMfgSampdetailFkLink1.
     * @return MnjMfgSampdetailFkLink1
     */
    public ViewLinkImpl getMnjMfgSampdetailFkLink1() {
        return (ViewLinkImpl)findViewLink("MnjMfgSampdetailFkLink1");
    }

    /**
     * Container's getter for SearchView1.
     * @return SearchView1
     */
    public ViewObjectImpl getSearchView1() {
        return (ViewObjectImpl)findViewObject("SearchView1");
    }

    /**
     * Container's getter for NotificationLOV1.
     * @return NotificationLOV1
     */
    public ViewObjectImpl getNotificationLOV1() {
        return (ViewObjectImpl)findViewObject("NotificationLOV1");
    }
    
    /* Method to Populate WHO Column */

    public void setSessionValues(String orgId, String userId, String respId,
                                 String respAppl) {

        System.out.println("UserId....." + userId);
        System.out.println("OrgID..." + orgId);

        


        if (userId != null) {
            FacesContext fctx = FacesContext.getCurrentInstance();
            ExternalContext ectx = fctx.getExternalContext();
            HttpSession userSession = (HttpSession)ectx.getSession(false);
            userSession.setAttribute("userId", userId);
             
            userSession.setAttribute("orgId", orgId);
            userSession.setAttribute("respId", respId);
  
            
            
       
        }
    }
    
    public String getAttachments() {


        String status = null;
        String doc = null;
        ViewObject vo = getMnjOntSampleStatusVO1();
        try {
            doc = vo.getCurrentRow().getAttribute("SampleDocNo").toString();
        } catch (Exception e) {
            
            System.out.println(e.getMessage());
        }


        String stmt = "BEGIN mnj_get_attach_names(:1, :2, :3); end;";

        CallableStatement cs =
            getDBTransaction().createCallableStatement(stmt, 1);
        try {
            cs.registerOutParameter(3, OracleTypes.VARCHAR);
            cs.setString(1, "MB");
            cs.setString(2, doc);
            cs.execute();
            status = cs.getString(3);
            cs.close();

        } catch (Exception e) {
            ;
        }
        return status;


    } //end of

    /* End Method to Populate WHO Column */


    /**
     * Container's getter for SampleMovementVO1.
     * @return SampleMovementVO1
     */
    public ViewObjectImpl getSampleMovementVO1() {
        return (ViewObjectImpl)findViewObject("SampleMovementVO1");
    }

    /**
     * Container's getter for MnjCourierInformationVO1.
     * @return MnjCourierInformationVO1
     */
    public ViewObjectImpl getMnjCourierInformationVO1() {
        return (ViewObjectImpl)findViewObject("MnjCourierInformationVO1");
    }
    
    public void SelectAllRequisitions(String flag) {


        ViewObject popupvo = getMnjOntSampleStatusLineVO1();
        //populatevo.executeQuery();

        //Row[] r = populatevo.getAllRowsInRange();
        RowSetIterator it = popupvo.createRowSetIterator("yy");


        while (it.hasNext()) {
            Row row = it.next();

            row.setAttribute("SelectBox", flag);
            System.out.println("Show Flag --> " + flag);
        }
        it.closeRowSetIterator();

    }


    /**
     * Container's getter for MnjOntSampleStatusViewOnlyVO1.
     * @return MnjOntSampleStatusViewOnlyVO1
     */
    public ViewObjectImpl getMnjOntSampleStatusViewOnlyVO1() {
        return (ViewObjectImpl)findViewObject("MnjOntSampleStatusViewOnlyVO1");
    }

    /**
     * Container's getter for MnjOntSampleStatusLineVO2.
     * @return MnjOntSampleStatusLineVO2
     */
    public ViewObjectImpl getMnjOntSampleStatusLineVO2() {
        return (ViewObjectImpl)findViewObject("MnjOntSampleStatusLineVO2");
    }

    /**
     * Container's getter for MnjMfgSamplinFkAssoc1Link1.
     * @return MnjMfgSamplinFkAssoc1Link1
     */
    public ViewLinkImpl getMnjMfgSamplinFkAssoc1Link1() {
        return (ViewLinkImpl)findViewLink("MnjMfgSamplinFkAssoc1Link1");
    }
}
