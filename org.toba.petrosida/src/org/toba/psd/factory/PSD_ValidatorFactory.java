package org.toba.psd.factory;

import org.adempiere.base.event.AbstractEventHandler;
import org.adempiere.base.event.IEventTopics;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.CLogger;
import org.osgi.service.event.Event;
import org.taowi.hcm.payroll.model.I_HC_PayComponent;
import org.taowi.hcm.payroll.model.MHCPayComponent;
import org.toba.psd.validator.PSD_PayComponentValidator;


public class PSD_ValidatorFactory extends AbstractEventHandler {
	private CLogger log = CLogger.getCLogger(PSD_ValidatorFactory.class);
	
	@Override
	protected void initialize() {
		
		//HCM
		//MHCPayComponent
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE	, I_HC_PayComponent.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_NEW	, I_HC_PayComponent.Table_Name);
		
		
		log.info("PROJECT MANAGEMENT EVENT MANAGER // INITIALIZED");
	}//intialize
	
	@Override
	protected void doHandleEvent(Event event) {
		String msg = "";	
		if (getPO(event).get_TableName().equals(MHCPayComponent.Table_Name)) {
			msg = PSD_PayComponentValidator.executeEvent(event, getPO(event));
		}

		if (msg.length() > 0)
			throw new AdempiereException(msg);

	}//doHandle
}//endClass
