package org.toba.psd.validator;

import org.adempiere.base.event.IEventTopics;
import org.compiere.model.PO;
import org.osgi.service.event.Event;
import org.taowi.hcm.payroll.model.MHCPayComponent;

/**
 * @author KevinY
 * Validator for model HC_PayComponent
 */
public class PSD_PayComponentValidator {
	
	
	/**
	 * 
	 * @param event
	 * @param po
	 * @return
	 * String msg
	 */
	public static String executeEvent(Event event, PO po) {
		String msg = "";
		MHCPayComponent PayComponent = (MHCPayComponent)po;
		if (event.getTopic().equals(IEventTopics.PO_AFTER_CHANGE)){
			msg = afterChange(PayComponent); 
		} else if (event.getTopic().equals(IEventTopics.PO_AFTER_NEW)) {
			msg = afterNew(PayComponent);
		}
		
		return msg;
	}//executeEvent
	
	/**
	 * 
	 * @param PayComponent
	 * @return
	 * empty String if Sucess
	 */
	public static String afterChange(MHCPayComponent PayComponent) {
		
		if(PayComponent.is_ValueChanged("IsPaid") || PayComponent.is_ValueChanged("IsForInvoice"))
			if(PayComponent.get_ValueAsBoolean("IsPaid") && PayComponent.get_ValueAsBoolean("IsForInvoice"))
				return "Please select only IsPaid or IsForInvoice true";
	
		return "";
	}//afterChange
	
	/**
	 * 
	 * @param PayComponent
	 * @return
	 * empty string if success
	 */
	public static String afterNew(MHCPayComponent PayComponent) {
		
		if(PayComponent.is_new())
			if(PayComponent.get_ValueAsBoolean("IsPaid") && PayComponent.get_ValueAsBoolean("IsForInvoice"))
				return "Please select only IsPaid or IsForInvoice true";
		return "";
	}//afterNew
	
}//endClass
