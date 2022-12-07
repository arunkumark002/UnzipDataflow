package com.ford.datafactory.batch.decoder;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.ford.datafactory.batch.bo.TransactionPromotionPointsBO;
import com.ford.datafactory.batch.util.CsvUtil;
import com.ford.datafactory.batch.util.CvdpConstants;
import com.ford.datafactory.batch.util.FordPassAprConstants;
import com.ford.datafactory.batch.util.FordPassAprHelper;
import org.codehaus.jackson.map.ObjectMapper;

public class TransactionPromotionPointsDataDecoder implements IMultipleRecordDecoder{
	
	private static final String CLASS_NAME = TransactionPromotionPointsDataDecoder.class.getName();
	private static final Logger log = Logger.getLogger(CLASS_NAME);
	
	ObjectMapper objectMapper = new ObjectMapper();
	TransactionPromotionPointsBO transactionPromotionPointsBO;
	String processStatusDetails = "";
	//String headerTimestamp= null;
	boolean resultStatus = false; 
	
	public static final String headerDate = "HEADER_DATETIME";
	public static final String teiTrlId = "TEI_TRL_ID";
	public static final String ptpCode = "PTP_CODE";
	public static final String trpId = "TRP_ID";
	public static final String trpPoints = "TRP_POINTS";
	public static final String trpTrnId = "TRP_TRN_ID";
	public static final String teiConditionFormula = "TEI_CONDITION_FORMULA";
	public static final String teiPointsFormula = "TEI_POINTS_FORMULA";
	public static final String teiProcessLevel = "TEI_PROCESS_LEVEL";
	public static final String ctrCodeTransaction = "CTR_CODE_TRANSACTION";
	public static final String ctrCodeHome = "CTR_CODE_HOME";
	public static final String prtCode="PRT_CODE";
	
	/*public TransactionPromotionPointsDataDecoder(String headertimestamp){
		
		this.headerTimestamp = headertimestamp;
	}*/

	@Override
	public String decode(String headerTimestamp,String line) throws Exception {
		final String METHOD_NAME = "decode";
		log.entering(CLASS_NAME, METHOD_NAME);
		
		processStatusDetails = "";
		transactionPromotionPointsBO = new TransactionPromotionPointsBO();
		
		try {
			
			ArrayList<String> transactionPromotionPointsData = CsvUtil.parseCsvRecord(line.toString());

			if (!validateSkipRecords(transactionPromotionPointsData)) {	
				if (transactionPromotionPointsData.size() == 11) {
					
					// Fail processing 
					validateAllMandatoryFields(transactionPromotionPointsData);
					validateMandatoryFields(transactionPromotionPointsData);
					
					
					// SHA key generation
					
						if(transactionPromotionPointsData.get(10)==null ||transactionPromotionPointsData.get(10).trim()=="") {
							FordPassAprHelper.setShaKey(FordPassAprHelper.chunkAsOneStringExceptLastColumn(transactionPromotionPointsData,1) +","+ headerTimestamp,FordPassAprConstants.TRANS_PROMOTION_POINTS_DATATYPE,transactionPromotionPointsBO);
						}
						else{
						FordPassAprHelper.setShaKey(FordPassAprHelper.chunkAsOneString(transactionPromotionPointsData) +","+ headerTimestamp, FordPassAprConstants.TRANS_PROMOTION_POINTS_DATATYPE,transactionPromotionPointsBO);
						}
						
                    settransactionPromotionPointsMappings(transactionPromotionPointsData, headerTimestamp);
                    
                    setRawPayload(transactionPromotionPointsData);
					
					// Finally -- set process states
					resultStatus = FordPassAprHelper.setProcessState();
					
					if (resultStatus){	
						
						transactionPromotionPointsBO.setProcess_status_details(FordPassAprHelper.setProcessDetails());	
						
						transactionPromotionPointsBO.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_FAILED);
					} else {		
						
						transactionPromotionPointsBO.setProcess_status_details(null);		
						
						transactionPromotionPointsBO.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_SUCCESSFUL);
					}
					
					transactionPromotionPointsBO.setProcess_status_date_time_utc(FordPassAprHelper
							.GetUTCdatetimeAsString());
					
					// reset 
					FordPassAprHelper.resetProcessState();
					
					return this.objectMapper.writeValueAsString(transactionPromotionPointsBO);
					
				}else {
					throw new Exception(
							FordPassAprConstants.TRANS_PROMOTION_POINTS_DATATYPE + FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);
				}
				
		
			}else{
				return null;
			}
		} catch (Exception e) {
			log.severe(FordPassAprConstants.TRANS_PROMOTION_POINTS_DATATYPE + e.getMessage());
			/*if (e.getMessage() == null)
				throw new Exception(
						FordPassAprConstants.TRANS_PROMOTION_POINTS_DATATYPE + FordPassAprConstants.PROCESSING_EXCEPTION);		
				else
					throw new Exception(e.getMessage());*/
			return null;
		}
		
	}
	
	private boolean validateSkipRecords(ArrayList<String> transactionPromotionPointsData)
			throws Exception {
		boolean skipRecord = false;
		if (transactionPromotionPointsData.get(0) != null) {
			if (transactionPromotionPointsData.get(0).equalsIgnoreCase(
					FordPassAprConstants.APR_HEADER)
					|| transactionPromotionPointsData.get(0).equalsIgnoreCase(
							FordPassAprConstants.APR_TAIL)) {
				skipRecord = true;
			} else {
				skipRecord = false;
			}
		} else {
			if (transactionPromotionPointsData.size() == 11) {
				skipRecord = false;

			} else {
				throw new Exception(FordPassAprConstants.TRANS_PROMOTION_POINTS_DATATYPE
						+ FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);

			}
		}
		return skipRecord;
	}
	
	private void setRawPayload(ArrayList<String> transactionPromotionPointsData) {
		
		StringBuffer sbf = new StringBuffer();		
		sbf.append(transactionPromotionPointsData.get(0));
        for(int i=1; i < transactionPromotionPointsData.size(); i++){
                sbf.append(",").append(transactionPromotionPointsData.get(i));
        }		
        transactionPromotionPointsBO.setRaw_payload(sbf.toString());	
	}
	
	
	
	private void validateAllMandatoryFields(ArrayList<String> transactionPromotionPointsData) throws Exception{
		final String METHOD_NAME = "validateAllMandatoryFields";
		log.entering(CLASS_NAME, METHOD_NAME);
		
		if ( 
				transactionPromotionPointsData.get(0) == null && 
				transactionPromotionPointsData.get(1) == null && 
				transactionPromotionPointsData.get(2) == null &&
				transactionPromotionPointsData.get(4) == null &&
				transactionPromotionPointsData.get(7) == null &&
				transactionPromotionPointsData.get(8) == null &&
				transactionPromotionPointsData.get(9) == null )

			throw new Exception(FordPassAprConstants.TRANS_PROMOTION_POINTS_DATATYPE + FordPassAprConstants.REQUIRED_FIELDS);		
	}
	

	//Sets record to transfail if any of the required field is empty	
	private void validateMandatoryFields(ArrayList<String> transactionPromotionPointsData){
		final String METHOD_NAME = "validateMandatoryFields";
		log.entering(CLASS_NAME, METHOD_NAME);		
		
		FordPassAprHelper.validateMandatoryField(transactionPromotionPointsData.get(0), teiTrlId);
		FordPassAprHelper.validateMandatoryField(transactionPromotionPointsData.get(1), ptpCode);
		FordPassAprHelper.validateMandatoryField(transactionPromotionPointsData.get(2), trpId);
		FordPassAprHelper.validateMandatoryField(transactionPromotionPointsData.get(4), trpTrnId);
		FordPassAprHelper.validateMandatoryField(transactionPromotionPointsData.get(7), teiProcessLevel);
		FordPassAprHelper.validateMandatoryField(transactionPromotionPointsData.get(8), ctrCodeTransaction);
		FordPassAprHelper.validateMandatoryField(transactionPromotionPointsData.get(9), ctrCodeHome);
				
	}
	
	

	
private void settransactionPromotionPointsMappings(ArrayList<String> transactionPromotionPointsData, String headerTimestamp) {
		
		final String METHOD_NAME = "setTransactionPromotionPointsMappings";
		
		log.entering(CLASS_NAME, METHOD_NAME);
		
		transactionPromotionPointsBO.setPartition_year(FordPassAprHelper.intParser(
				FordPassAprHelper.transformISOPartitionDate(
						headerTimestamp, headerDate), headerDate));
		transactionPromotionPointsBO.setHeader_timestamp_utc(FordPassAprHelper.parseHeaderDateTimestamp(headerTimestamp , headerDate));
		
		transactionPromotionPointsBO.setTei_trl_id(FordPassAprHelper.longParser(transactionPromotionPointsData.get(0),teiTrlId));
		
		transactionPromotionPointsBO.setPtp_code(FordPassAprHelper.stringParser(transactionPromotionPointsData.get(1), ptpCode));
		
		transactionPromotionPointsBO.setTrp_id(FordPassAprHelper.longParser(transactionPromotionPointsData.get(2),trpId));
					
		transactionPromotionPointsBO.setTrp_points(FordPassAprHelper.longParser(transactionPromotionPointsData.get(3),trpPoints));
				
		transactionPromotionPointsBO.setTrp_trn_id(FordPassAprHelper.longParser(transactionPromotionPointsData.get(4),trpTrnId));
		
		transactionPromotionPointsBO.setTei_condition_formula(FordPassAprHelper.stringParser(transactionPromotionPointsData.get(5),teiConditionFormula));
		
		transactionPromotionPointsBO.setTei_points_formula(FordPassAprHelper.stringParser(transactionPromotionPointsData.get(6), teiPointsFormula));
		
		transactionPromotionPointsBO.setTei_process_level(FordPassAprHelper.stringParser(transactionPromotionPointsData.get(7),teiProcessLevel));
							
		transactionPromotionPointsBO.setCtr_code_transaction(FordPassAprHelper.stringParser(transactionPromotionPointsData.get(8),ctrCodeTransaction));
		
		transactionPromotionPointsBO.setCtr_code_home(FordPassAprHelper.stringParser(transactionPromotionPointsData.get(9),ctrCodeHome));
		
		transactionPromotionPointsBO.setPrt_code(FordPassAprHelper.stringParser(transactionPromotionPointsData.get(10),prtCode));
		
		
	}

}
