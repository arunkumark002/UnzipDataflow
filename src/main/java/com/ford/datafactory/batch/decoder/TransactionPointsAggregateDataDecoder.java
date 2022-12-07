package com.ford.datafactory.batch.decoder;


import java.util.ArrayList;
import com.ford.datafactory.batch.bo.TransactionPointsAggregateBO;
import com.ford.datafactory.batch.util.*;
import org.codehaus.jackson.map.ObjectMapper;
import java.util.logging.Logger;


public class TransactionPointsAggregateDataDecoder implements IMultipleRecordDecoder{
	private static final String CLASS_NAME = TransactionPointsAggregateDataDecoder.class.getName();
	private static final Logger log = Logger.getLogger(CLASS_NAME);

	ObjectMapper objectMapper = new ObjectMapper();
	TransactionPointsAggregateBO transactionPointsAggregateBO;
	String processStatusDetails = "";
	//String headerTimestamp= null;
	boolean resultStatus = false; 
	
	public static final String headerDate = "HEADER_DATETIME";
	public static final String tptPoints = "TPT_POINTS";
	public static final String tptId = "TPT_ID";
	public static final String ptpCode = "PTP_CODE";
	public static final String tptAccId = "TPT_ACC_ID";
	public static final String tptAuditCd = "TPT_AUDIT_CD";
	public static final String tptAuditMd = "TPT_AUDIT_MD";
	public static final String tptBookDate = "TPT_BOOK_DATE";
	public static final String tptSrcTrnId = "TPT_SRC_TRN_ID";
	public static final String tptStatus = "TPT_STATUS";
	public static final String ctrCodeTransaction = "CTR_CODE_TRANSACTION";
	public static final String ctrCodeHome = "CTR_CODE_HOME";
	public static final String tptxPointsValue = "TPTX_POINTS_VALUE";
	public static final String tptexpirationdate = "TPT_EXPIRATION_DATE";
	
	/*public TransactionPointsAggregateDataDecoder(String headertimestamp){
		
		this.headerTimestamp = headertimestamp;
	}*/
	
	/** 
	 * decode method which will be called from BaseTransform for decode the input line
	 *
	 */
	public String decode(String headerTimestamp,String line) throws Exception {
		final String METHOD_NAME = "decode";
		log.entering(CLASS_NAME, METHOD_NAME);
		
		processStatusDetails = "";
		transactionPointsAggregateBO = new TransactionPointsAggregateBO();
		
		try {
			
			ArrayList<String> transactionPointsAggregateData = CsvUtil.parseCsvRecord(line.toString());

			if (!validateSkipRecords(transactionPointsAggregateData)) {	
				if (transactionPointsAggregateData.size() == 13) {
					// Fail processing 
					validateAllMandatoryFields(transactionPointsAggregateData);
					validateMandatoryFields(transactionPointsAggregateData);
					
			        setVinMappings(transactionPointsAggregateData, headerTimestamp);
                    
                    setRawPayload(transactionPointsAggregateData);
                    
                    validateAndSetShaKey(transactionPointsAggregateData, headerTimestamp);
                    
                 // Finally -- set process states
					resultStatus = FordPassAprHelper.setProcessState();
					
					if (resultStatus){	
						
						transactionPointsAggregateBO.setProcess_status_details(FordPassAprHelper.setProcessDetails());	
						
						transactionPointsAggregateBO.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_FAILED);
					} else {		
						
						transactionPointsAggregateBO.setProcess_status_details(null);		
						
						transactionPointsAggregateBO.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_SUCCESSFUL);
					}
					
					transactionPointsAggregateBO.setProcess_status_date_time_utc(FordPassAprHelper
							.GetUTCdatetimeAsString());
					
					// reset 
					FordPassAprHelper.resetProcessState();
					
					return this.objectMapper.writeValueAsString(transactionPointsAggregateBO);
					
				}else {
					throw new Exception(
							FordPassAprConstants.TRANSACTION_POINTS_AGGREGATE + FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);
				}
				
		
			}else{
				return null;
			}
		} catch (Exception e) {
			log.severe(FordPassAprConstants.TRANSACTION_POINTS_AGGREGATE + FordPassAprConstants.PROCESSING_EXCEPTION);
			/*if (e.getMessage() == null)
				throw new Exception(
						FordPassAprConstants.TRANSACTION_POINTS_AGGREGATE + FordPassAprConstants.PROCESSING_EXCEPTION);		
				else
					throw new Exception(e.getMessage());*/
			return null;
		}
		
	}
	
	/** 
	 * Setting the Raw payload
	 *  
	 * @param transactionPointsAggregateData
	 */
	private void setRawPayload(ArrayList<String> transactionPointsAggregateData) {
		
		StringBuffer sbf = new StringBuffer();		
		sbf.append(transactionPointsAggregateData.get(0));
        for(int i=1; i < transactionPointsAggregateData.size(); i++){
                sbf.append(",").append(transactionPointsAggregateData.get(i));
        }		
        transactionPointsAggregateBO.setRaw_payload(sbf.toString());	
	}
	
	/** 
	 * Validating the Header & Tail
	 * 
	 * @param transactionPointsAggregateData
	 */
	private boolean validateSkipRecords(ArrayList<String> transactionPointsAggregateData)
			throws Exception {
		boolean skipRecord = false;
		if (transactionPointsAggregateData.get(0) != null) {
			if (transactionPointsAggregateData.get(0).equalsIgnoreCase(
					FordPassAprConstants.APR_HEADER)
					|| transactionPointsAggregateData.get(0).equalsIgnoreCase(
							FordPassAprConstants.APR_TAIL)) {
				skipRecord = true;
			} else {
				skipRecord = false;
			}
		} else {
			if (transactionPointsAggregateData.size() == 13) {
				skipRecord = false;

			} else {
				throw new Exception(FordPassAprConstants.TRANSACTION_POINTS_AGGREGATE
						+ FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);
			}
		}
		return skipRecord;
	}
		
	/** 
	 * Validating the Mandatory fields
	 *  
	 * @param transactionPointsAggregateData
	 */
	private void validateAllMandatoryFields(ArrayList<String> transactionPointsAggregateData) throws Exception{
		final String METHOD_NAME = "validateAllMandatoryFields";
		log.entering(CLASS_NAME, METHOD_NAME);
		
		if (transactionPointsAggregateData.get(0) == null && transactionPointsAggregateData.get(1) == null
				&& transactionPointsAggregateData.get(2) == null && transactionPointsAggregateData.get(3) == null && transactionPointsAggregateData.get(4) == null 
				&& transactionPointsAggregateData.get(7) == null &&transactionPointsAggregateData.get(8) == null
				&& transactionPointsAggregateData.get(9) == null && transactionPointsAggregateData.get(10) == null)

			throw new Exception(
					FordPassAprConstants.TRANSACTION_POINTS_AGGREGATE
							+ FordPassAprConstants.REQUIRED_FIELDS);		
	}
	
	//Sets record to transfail if any of the required field is empty	
	private void validateMandatoryFields(ArrayList<String> transactionPointsAggregateData){
		final String METHOD_NAME = "validateMandatoryFields";
		log.entering(CLASS_NAME, METHOD_NAME);		
		
		FordPassAprHelper.validateMandatoryField(transactionPointsAggregateData.get(0), tptPoints);
		FordPassAprHelper.validateMandatoryField(transactionPointsAggregateData.get(1), tptId);
		FordPassAprHelper.validateMandatoryField(transactionPointsAggregateData.get(2), ptpCode);
		FordPassAprHelper.validateMandatoryField(transactionPointsAggregateData.get(3), tptAccId);
		FordPassAprHelper.validateMandatoryField(transactionPointsAggregateData.get(4), tptAuditCd);
		FordPassAprHelper.validateMandatoryField(transactionPointsAggregateData.get(7), tptSrcTrnId);
		FordPassAprHelper.validateMandatoryField(transactionPointsAggregateData.get(8), tptStatus);
		FordPassAprHelper.validateMandatoryField(transactionPointsAggregateData.get(9), ctrCodeTransaction);
		FordPassAprHelper.validateMandatoryField(transactionPointsAggregateData.get(10), ctrCodeHome);
						
	}	
	
	/** 
	 * Generating the SHA_KEY
	 *  
	 * @param transactionPointsAggregateData
	 */
	private void validateAndSetShaKey(ArrayList<String> transactionPointsAggregateData, String headerTimestamp) throws Exception {
		final String METHOD_NAME = "validateShaKey";
		log.entering(CLASS_NAME, METHOD_NAME);
				try {
					
					transactionPointsAggregateBO.setSha_key(HashUtil.calculateSHA256(transactionPointsAggregateData.get(1)+transactionPointsAggregateData.get(5) +","+ headerTimestamp));

				} catch (Exception e) {
					
					e.printStackTrace();
					throw new Exception(
							FordPassAprConstants.TRANSACTION_POINTS_AGGREGATE
									+ FordPassAprConstants.PAYLOAD_SHAKEY_ERR_DETAILS);
				}
		}

	/** 
	 * Setting the transactionPointsAggregateBO
	 *  
	 * @param transactionPointsAggregateData
	 */
    private void setVinMappings(ArrayList<String> transactionPointsAggregateData, String headerTimestamp) {
		
		final String METHOD_NAME = "setVinMappings";
		
		log.entering(CLASS_NAME, METHOD_NAME);
		
		transactionPointsAggregateBO.setPartition_year(FordPassAprHelper.intParser(FordPassAprHelper.transformISOPartitionDate(
						headerTimestamp, headerDate), headerDate));
		
		transactionPointsAggregateBO.setHeader_timestamp_utc(FordPassAprHelper.parseHeaderDateTimestamp(headerTimestamp , headerDate));
		
		transactionPointsAggregateBO.setTpt_points(FordPassAprHelper.longParser(transactionPointsAggregateData.get(0), tptPoints));
		
		transactionPointsAggregateBO.setTpt_id(FordPassAprHelper.longParser(transactionPointsAggregateData.get(1),tptId));
		
		transactionPointsAggregateBO.setPtp_code(FordPassAprHelper.stringParser(transactionPointsAggregateData.get(2),ptpCode));
		
		transactionPointsAggregateBO.setTpt_acc_id(FordPassAprHelper.longParser(transactionPointsAggregateData.get(3),tptAccId));
		
		transactionPointsAggregateBO.setTpt_audit_cd(FordPassAprHelper.parseDateTimestamp(transactionPointsAggregateData.get(4),tptAuditCd));
		
		transactionPointsAggregateBO.setTpt_audit_md(FordPassAprHelper.parseDateTimestamp(transactionPointsAggregateData.get(5),tptAuditMd));
						
		transactionPointsAggregateBO.setTpt_book_date(FordPassAprHelper.parseDateTimestamp(transactionPointsAggregateData.get(6),tptBookDate));
		
		transactionPointsAggregateBO.setTpt_src_trn_id(FordPassAprHelper.longParser(transactionPointsAggregateData.get(7),tptSrcTrnId));
					
		transactionPointsAggregateBO.setTpt_status(FordPassAprHelper.stringParser(transactionPointsAggregateData.get(8),tptStatus));
		
		transactionPointsAggregateBO.setCtr_code_transaction(FordPassAprHelper.stringParser(transactionPointsAggregateData.get(9),ctrCodeTransaction));
		
		transactionPointsAggregateBO.setCtr_code_home(FordPassAprHelper.stringParser(transactionPointsAggregateData.get(10),ctrCodeHome));
		
		transactionPointsAggregateBO.setTptx_points_value(FordPassAprHelper.doubleParser(transactionPointsAggregateData.get(11),tptxPointsValue));

		transactionPointsAggregateBO.setTpt_expiration_date(FordPassAprHelper.parseDateTimestamp(transactionPointsAggregateData.get(12),tptexpirationdate));
	}




}
