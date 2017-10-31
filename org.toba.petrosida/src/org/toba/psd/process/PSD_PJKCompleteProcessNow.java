package org.toba.psd.process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.toba.psd.model.X_HC_PJK_AccomodationPoint;
import org.toba.psd.model.X_HC_PJK_OtherPoint;
import org.toba.psd.model.X_HC_PJK_TransportPoint;
import org.toba.psd.model.X_HC_PJK_TravelRequest;

public class PSD_PJKCompleteProcessNow extends SvrProcess{
	private int p_HC_PJK_TravelRequest_ID = 0;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null && para[i].getParameter_To() == null)
				;
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
		p_HC_PJK_TravelRequest_ID = getRecord_ID();
	}//prepare
	
	@Override
	protected String doIt() throws Exception {
		
		if(p_HC_PJK_TravelRequest_ID <= 0){
			throw new AdempiereException("Error: No PJK Travel Request selected");
		}
		
		X_HC_PJK_TravelRequest PJKtravelRequest = new X_HC_PJK_TravelRequest(getCtx(), p_HC_PJK_TravelRequest_ID, get_TrxName());
		
		BigDecimal totalExpense = new BigDecimal(0);
		//calculateTransportPointExpense
		totalExpense = totalExpense.add(calculateTransportPointExpense(PJKtravelRequest));
		//calculate AccomodationExpense
		totalExpense = totalExpense.add(calculateAccomodationExpense(PJKtravelRequest));
		//calculate OtherPointExpense
		totalExpense = totalExpense.add(calculateOtherPointExpense(PJKtravelRequest));
		
		totalExpense = totalExpense.add(new BigDecimal(PJKtravelRequest
				.get_Value(X_HC_PJK_TravelRequest.COLUMNNAME_RealizationDailyExpense)
				.toString()));
		
		PJKtravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_TotalRealizationExpense, totalExpense);
		PJKtravelRequest.setStatus("Req");
		PJKtravelRequest.setIsComplete(true);
		PJKtravelRequest.saveEx();
		return "";
	}//doit

	/**
	 * Calculate all transport point expense
	 * @param travelRequest
	 */
	private BigDecimal calculateTransportPointExpense(X_HC_PJK_TravelRequest PJKtravelRequest){
		
		List<X_HC_PJK_TransportPoint> transportPoints = new ArrayList<X_HC_PJK_TransportPoint>();
		String whereClause = X_HC_PJK_TravelRequest.COLUMNNAME_HC_PJK_TravelRequest_ID +"=? AND "
				+ ""+X_HC_PJK_TransportPoint.COLUMNNAME_IsActive+"='Y'";
		transportPoints = new Query(getCtx(), X_HC_PJK_TransportPoint.Table_Name, whereClause, get_TrxName())
						.setParameters(PJKtravelRequest.get_ID())
						.list();
		
		BigDecimal total = new BigDecimal(0);
		if(transportPoints.size() > 0) {
			for(X_HC_PJK_TransportPoint transportPoint : transportPoints) {
				total = total.add(new BigDecimal(transportPoint.get_Value("RealizationTransportExpense").toString()));
			}
			PJKtravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_RealizationTransportExpense, total);
			PJKtravelRequest.saveEx();
			return total;
		}else{
			return new BigDecimal(0);
		}
		
	}//calculateTransportPointExpense

	/**
	 * Calculate all total accomodation expense
	 * @param travelRequest
	 */
	private BigDecimal calculateAccomodationExpense(X_HC_PJK_TravelRequest PJKtravelRequest){
		
		List<X_HC_PJK_AccomodationPoint> AccomodationPoints = new ArrayList<X_HC_PJK_AccomodationPoint>();
		String whereClause = X_HC_PJK_TravelRequest.COLUMNNAME_HC_PJK_TravelRequest_ID +"=? AND "
				+ ""+X_HC_PJK_AccomodationPoint.COLUMNNAME_IsActive+"='Y'";
		AccomodationPoints = new Query(getCtx(), X_HC_PJK_AccomodationPoint.Table_Name, whereClause, get_TrxName())
						.setParameters(PJKtravelRequest.get_ID())
						.list();
		
		BigDecimal total = new BigDecimal(0);
		if(AccomodationPoints.size() > 0) {
			for(X_HC_PJK_AccomodationPoint AccomodationPoint : AccomodationPoints) {
				total = total.add(new BigDecimal(
						AccomodationPoint.get_Value("RealizationAccomodationExpense").toString()));
			}
			PJKtravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_RealizationAccomodationExpense, total);
			PJKtravelRequest.saveEx();
			return total;
		}else{
			return new BigDecimal(0);
		}
	}//calculateAccomodationExpense

	/**
	 * @param travelRequest
	 */
	private BigDecimal calculateOtherPointExpense(X_HC_PJK_TravelRequest PJKtravelRequest){
		List<X_HC_PJK_OtherPoint> otherPoints = new ArrayList<X_HC_PJK_OtherPoint>();
		String whereClause = X_HC_PJK_TravelRequest.COLUMNNAME_HC_PJK_TravelRequest_ID +"=? AND "
				+ ""+X_HC_PJK_OtherPoint.COLUMNNAME_IsActive+"='Y'";
		otherPoints = new Query(getCtx(), X_HC_PJK_OtherPoint.Table_Name, whereClause, get_TrxName())
						.setParameters(PJKtravelRequest.get_ID())
						.list();
		
		BigDecimal total = new BigDecimal(0);
		if(otherPoints.size() > 0) {
			for(X_HC_PJK_OtherPoint otherPoint : otherPoints) {
				total = total.add(new BigDecimal(otherPoint.get_Value("RealizationOtherExpense").toString()));
			}
			PJKtravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_RealizationOtherExpense, total);
			PJKtravelRequest.saveEx();
			return total;
		}else{
			return new BigDecimal(0);
		}
	}//calculateOtherPointExpense
	
}//endClass
