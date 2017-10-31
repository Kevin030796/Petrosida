package org.toba.psd.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.taowi.hcm.imported.classes.TimeUtil;
import org.toba.psd.model.X_HC_AccomodationPoint;
import org.toba.psd.model.X_HC_OtherPoint;
import org.toba.psd.model.X_HC_TransportPoint;
import org.toba.psd.model.X_HC_TravelRequest;

public class PSD_CalculateExpenseRequestTravel extends SvrProcess{
	
	private int p_HC_TravelRequest_ID = 0;
	
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
		p_HC_TravelRequest_ID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
		
		if(p_HC_TravelRequest_ID <= 0)
			return "Error: No Travel Request Selected";
		
		X_HC_TravelRequest travelRequest = new X_HC_TravelRequest(getCtx(), p_HC_TravelRequest_ID, get_TrxName());
		BigDecimal totalExpense = new BigDecimal(0);
		//calculateTransportPointExpense
		totalExpense = totalExpense.add(calculateTransportPointExpense(travelRequest));
		//calculate AccomodationExpense
		totalExpense = totalExpense.add(calculateAccomodationExpense(travelRequest));
		//calculate OtherPointExpense
		totalExpense = totalExpense.add(calculateOtherPointExpense(travelRequest));
		
		totalExpense = totalExpense.add(new BigDecimal(travelRequest
				.get_Value(X_HC_TravelRequest.COLUMNNAME_TotalDailyExpense)
				.toString()));
		
		travelRequest.set_ValueOfColumn(X_HC_TravelRequest.COLUMNNAME_TotalExpense, totalExpense);
		travelRequest.set_ValueOfColumn(X_HC_TravelRequest.COLUMNNAME_HC_ApprovedPrePayment, totalExpense);
		travelRequest.saveEx();
		return "Success Process Calculate";
	}//doIt
	
	/**
	 * Calculate all transport point expense
	 * @param travelRequest
	 */
	private BigDecimal calculateTransportPointExpense(X_HC_TravelRequest travelRequest){
		
		List<X_HC_TransportPoint> transportPoints = new ArrayList<X_HC_TransportPoint>();
		String whereClause = X_HC_TravelRequest.COLUMNNAME_HC_TravelRequest_ID +"=? AND "
				+ ""+X_HC_TransportPoint.COLUMNNAME_IsActive+"='Y'";
		transportPoints = new Query(getCtx(), X_HC_TransportPoint.Table_Name, whereClause, get_TrxName())
						.setParameters(travelRequest.get_ID())
						.list();
		
		BigDecimal total = new BigDecimal(0);
		if(transportPoints.size() > 0) {
			for(X_HC_TransportPoint transportPoint : transportPoints) {
				total = total.add(transportPoint.getTotalPrice());
			}
			travelRequest.set_ValueOfColumn(X_HC_TravelRequest.COLUMNNAME_TotalTransportExpense, total);
			travelRequest.saveEx();
			return total;
		}else{
			return new BigDecimal(0);
		}
		
	}//calculateTransportPointExpense
	
	/**
	 * Calculate all total accomodation expense
	 * @param travelRequest
	 */
	private BigDecimal calculateAccomodationExpense(X_HC_TravelRequest travelRequest){
		
		List<X_HC_AccomodationPoint> AccomodationPoints = new ArrayList<X_HC_AccomodationPoint>();
		String whereClause = X_HC_TravelRequest.COLUMNNAME_HC_TravelRequest_ID +"=? AND "
				+ ""+X_HC_AccomodationPoint.COLUMNNAME_IsActive+"='Y'";
		AccomodationPoints = new Query(getCtx(), X_HC_AccomodationPoint.Table_Name, whereClause, get_TrxName())
						.setParameters(travelRequest.get_ID())
						.list();
		
		BigDecimal total = new BigDecimal(0);
		Timestamp startDate = null;
		Timestamp endDate = null;
		if(AccomodationPoints.size() > 0) {
			for(X_HC_AccomodationPoint AccomodationPoint : AccomodationPoints) {
				startDate = AccomodationPoint.getStartDate();
				endDate = AccomodationPoint.getEndDate();
				int calcDay = TimeUtil.getDaysBetween(startDate, endDate)+1;
				BigDecimal days = new BigDecimal(calcDay);
				total = total.add(AccomodationPoint.getTotalPrice().multiply(days));
			}
			travelRequest.set_ValueOfColumn(X_HC_TravelRequest.COLUMNNAME_TotalAccomodationExpense, total);
			travelRequest.saveEx();
			return total;
		}else{
			return new BigDecimal(0);
		}
	}//calculateAccomodationExpense
	
	/**
	 * @param travelRequest
	 */
	private BigDecimal calculateOtherPointExpense(X_HC_TravelRequest travelRequest){
		List<X_HC_OtherPoint> otherPoints = new ArrayList<X_HC_OtherPoint>();
		String whereClause = X_HC_TravelRequest.COLUMNNAME_HC_TravelRequest_ID +"=? AND "
				+ ""+X_HC_OtherPoint.COLUMNNAME_IsActive+"='Y'";
		otherPoints = new Query(getCtx(), X_HC_OtherPoint.Table_Name, whereClause, get_TrxName())
						.setParameters(travelRequest.get_ID())
						.list();
		
		BigDecimal total = new BigDecimal(0);
		if(otherPoints.size() > 0) {
			for(X_HC_OtherPoint otherPoint : otherPoints) {
				total = total.add(otherPoint.getPrice());
			}
			travelRequest.set_ValueOfColumn(X_HC_TravelRequest.COLUMNNAME_TotalOtherPoint, total);
			travelRequest.saveEx();
			return total;
		}else{
			return new BigDecimal(0);
		}
	}//calculateOtherPointExpense
	
}//endClass
