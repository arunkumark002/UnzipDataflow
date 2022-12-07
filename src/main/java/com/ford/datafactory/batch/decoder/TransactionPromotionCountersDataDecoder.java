package com.ford.datafactory.batch.decoder;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.ford.datafactory.batch.bo.TransactionPromotionCountersBO;
import com.ford.datafactory.batch.util.CsvUtil;
import com.ford.datafactory.batch.util.CvdpConstants;
import com.ford.datafactory.batch.util.FordPassAprConstants;
import com.ford.datafactory.batch.util.FordPassAprHelper;
import org.codehaus.jackson.map.ObjectMapper;


public class TransactionPromotionCountersDataDecoder implements IMultipleRecordDecoder {

	private static final String CLASS_NAME = TransactionPromotionCountersDataDecoder.class
			.getName();
	private static final Logger log = Logger.getLogger(CLASS_NAME);

	ObjectMapper objectMapper = new ObjectMapper();
	TransactionPromotionCountersBO promotionCountersBO;
	String processStatusDetails = "";
	//String headerTimestamp = null;
	boolean fieldResultStatus = false;
	

	public static final String headerDate = "HEADER_DATETIME";
	public static final String uniqueId = "TCH_ID";
	public static final String auditCreateDate = "TCH_AUDIT_CD";
	public static final String auditCreateUser = "TCH_AUDIT_CU";
	public static final String refferenceToCounterValue = "TCH_CAV_ID";
	public static final String changeDate = "TCH_CHANGE_DATE";
	public static final String counterValueChange = "TCH_CHANGE_VALUE";
	public static final String previousValue = "TCH_PREV_VALUE";
	public static final String sourceTransactionId = "TCH_SRC_TRN_ID";
	public static final String associatedCounter = "TCH_TEN_ID";
	public static final String associatedTransactionRule = "TCH_TRL_ID";
	public static final String countervalueAfterTransaction = "TCH_VALUE";
	public static final String domain = "TEN_DOMAIN";
	public static final String code = "TEN_CODE";
	public static final String order = "TEN_ORDER";
	public static final String cyclicFlag = "TEN_CYCLIC";
	public static final String conditionFormula = "TEN_CONDITION_FORMULA";
	public static final String reversibleFlag = "TEN_REVERSIBLE";
	public static final String valueFormula = "TEN_VALUE_FORMULA";
	public static final String limit = "TEN_LIMIT";
	public static final String description = "TEN_DESCRIPTION";
	public static final String multiplicationFactor = "TEN_ACCUM_MULTI";
	public static final String processingLevel = "TEN_PROCESS_LEVEL";
	public static final String promotionCode = "TEN_TRL_CODE";
	public static final String windowTimeUnit = "TEN_ACCUM_UNIT";
	public static final String promotionReference = "TEN_TRL_ID";
	public static final String transactionCTRCode="CTR_CODE_Transaction";
	public static final String homeCTRCode= "CTR_CODE_Home";
	//FPA _ FPR (FordPass Rewards) enhancement
	public static final String tenReverseType= "TEN_REVERSE_TYPE";
	

	// Constructor
	/*public TransactionPromotionCountersDataDecoder(String headertimestamp) {
		
		this.headerTimestamp = headertimestamp;
	}*/

	// decode method which will be called from BaseTransform for decode the
	// input line
	public String decode(String headerTimestamp,String line) throws Exception {
		final String METHOD_NAME = "decode";
		log.entering(CLASS_NAME, METHOD_NAME);
		
		log.info(" comes to decode ***");
		processStatusDetails = "";
		promotionCountersBO = new TransactionPromotionCountersBO();

		try {

			ArrayList<String> promotionData = CsvUtil.parseCsvRecord(line
					.toString());

				if (!validateSkipRecords(promotionData)) {
				// validate input records columns count
				if (promotionData.size() == 28) {
					// Fail processing
					validateAllMandatoryFields(promotionData);
					validateMandatoryFields(promotionData);
					
					// Validate TEN_REVERSE_TYPE field value is N/A/L
					validateTenReverseTypeField(promotionData);
					
					//Raw payload 
					setRawPayload(promotionData);
					// SHA key generation
					if(promotionCountersBO.getRaw_payload()!=null){
					
					try{
                		//FPA _ FPR (FordPass Rewards) enhancement
						if ((promotionData.get(27)==null) || (promotionData.get(27).trim().equalsIgnoreCase(""))){
							FordPassAprHelper.setShaKey(FordPassAprHelper.chunkAsOneStringExceptLastColumn(promotionData,1) +","+ headerTimestamp,FordPassAprConstants.PROMOTION_DATATYPE,promotionCountersBO);
						}else{
							FordPassAprHelper.setShaKey(FordPassAprHelper.chunkAsOneString(promotionData) +","+ headerTimestamp,FordPassAprConstants.PROMOTION_DATATYPE,promotionCountersBO);
						}
					}catch(Exception e){
						e.printStackTrace();
						throw new Exception(
								FordPassAprConstants.TRANS_PROMOTION_COUNTERS_DATATYPE
										+ FordPassAprConstants.PAYLOAD_SHAKEY_ERR_DETAILS);
					}
					}
					else
					{
						throw new Exception(
								FordPassAprConstants.TRANS_PROMOTION_COUNTERS_DATATYPE
										+ FordPassAprConstants.SHA_KEY_GENERATION_DETAILS);
						
					}
					
					setPromotionCountersBO(promotionData,headerTimestamp);
					
					// Finally -- set process states				
					fieldResultStatus = FordPassAprHelper.setProcessState();

					if (fieldResultStatus) {
						promotionCountersBO
								.setProcess_status_details(FordPassAprHelper.setProcessDetails());

						promotionCountersBO
								.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_FAILED);
					} else {
						promotionCountersBO.setProcess_status_details(null);

						promotionCountersBO
								.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_SUCCESSFUL);
					}

					promotionCountersBO
							.setProcess_status_date_time_utc(FordPassAprHelper
									.GetUTCdatetimeAsString());
					// reset the Process State
					FordPassAprHelper.resetProcessState();
					return this.objectMapper
							.writeValueAsString(promotionCountersBO);
					
				} else {
					throw new Exception(FordPassAprConstants.TRANS_PROMOTION_COUNTERS_DATATYPE
							+ FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);
				}

			} else {
				return null;
			}
		} catch (Exception e) {
			log.severe(FordPassAprConstants.TRANS_PROMOTION_COUNTERS_DATATYPE + e.getMessage());
			/*if (e.getMessage() == null)
				throw new Exception(FordPassAprConstants.TRANS_PROMOTION_COUNTERS_DATATYPE
						+ FordPassAprConstants.PROCESSING_EXCEPTION);
			else
				throw new Exception(e.getMessage());*/
			return null;
		}

	}

	private void setRawPayload(ArrayList<String> promotionData) {
		
		StringBuffer sbf = new StringBuffer();
		sbf.append(promotionData.get(0));
		for (int i = 1; i < promotionData.size(); i++) {
			sbf.append(",").append(promotionData.get(i));
		}
		promotionCountersBO.setRaw_payload(sbf.toString());

	}

	private void setPromotionCountersBO(ArrayList<String> promotionData, String headerTimestamp) {

		final String METHOD_NAME = "setPromotionCountersBO";

		log.entering(CLASS_NAME, METHOD_NAME);
		
		//set the partition year from time stamp
		promotionCountersBO.setPartition_year(FordPassAprHelper.intParser(
				FordPassAprHelper.transformISOPartitionDate(headerTimestamp, headerDate), headerDate));
		promotionCountersBO.setHeader_timestamp_utc(FordPassAprHelper.parseHeaderDateTimestamp(headerTimestamp, headerDate));
		promotionCountersBO.setTch_id(FordPassAprHelper.longParser(
				promotionData.get(0), uniqueId));
		promotionCountersBO.setTch_audit_cd(FordPassAprHelper.parseDateTimestamp(
				promotionData.get(1), auditCreateDate));
		promotionCountersBO.setTch_audit_cu(FordPassAprHelper.longParser(
				promotionData.get(2), auditCreateUser));
		promotionCountersBO.setTch_cav_id(FordPassAprHelper.longParser(
				promotionData.get(3), refferenceToCounterValue));
		promotionCountersBO.setTch_change_date(FordPassAprHelper.parseDateTimestamp(
				promotionData.get(4), changeDate));
		promotionCountersBO.setTch_change_value(FordPassAprHelper.doubleParser(
				promotionData.get(5), counterValueChange));
		promotionCountersBO.setTch_prev_value(FordPassAprHelper.doubleParser(
				promotionData.get(6), previousValue));
		promotionCountersBO.setTch_src_trn_id(FordPassAprHelper.longParser(
				promotionData.get(7), sourceTransactionId));
		promotionCountersBO.setTch_trl_cnt_id(FordPassAprHelper.longParser(
				promotionData.get(8), associatedCounter));
		promotionCountersBO.setTch_trl_id(FordPassAprHelper.longParser(
				promotionData.get(9), associatedTransactionRule));
		promotionCountersBO.setTch_value(FordPassAprHelper.doubleParser(
				promotionData.get(10), countervalueAfterTransaction));
		promotionCountersBO.setTen_domain(FordPassAprHelper.stringParser(
				promotionData.get(11), domain));
		promotionCountersBO.setTen_code(FordPassAprHelper.stringParser(
				promotionData.get(12), code));
		promotionCountersBO.setTen_order(FordPassAprHelper.intParser(
				promotionData.get(13), order));
		promotionCountersBO.setTen_cyclic(FordPassAprHelper.stringParser(
				promotionData.get(14), cyclicFlag));
		promotionCountersBO.setTen_condition_formula(FordPassAprHelper
				.stringParser(promotionData.get(15), conditionFormula));
		promotionCountersBO.setTenx_reversible(FordPassAprHelper.stringParser(
				promotionData.get(16), reversibleFlag));
		promotionCountersBO.setTen_value_formula(FordPassAprHelper
				.stringParser(promotionData.get(17), valueFormula));
		promotionCountersBO.setTen_limit(FordPassAprHelper.longParser(
				promotionData.get(18), limit));
		promotionCountersBO.setTen_description(FordPassAprHelper.stringParser(
				promotionData.get(19), description));
		promotionCountersBO.setTen_accum_multi(FordPassAprHelper.intParser(
				promotionData.get(20), multiplicationFactor));
		promotionCountersBO.setTen_process_level(FordPassAprHelper
				.stringParser(promotionData.get(21), processingLevel));
		promotionCountersBO.setTen_trl_code(FordPassAprHelper.stringParser(
				promotionData.get(22), promotionCode));
		promotionCountersBO.setTen_accum_unit(FordPassAprHelper.stringParser(
				promotionData.get(23), windowTimeUnit));
		promotionCountersBO.setTen_trl_id(FordPassAprHelper.longParser(
				promotionData.get(24), promotionReference));
		promotionCountersBO.setCtr_code_transaction(FordPassAprHelper.stringParser(
				promotionData.get(25),transactionCTRCode));
		promotionCountersBO.setCtr_code_home(FordPassAprHelper.stringParser(
				promotionData.get(26), homeCTRCode));
		//FPA _ FPR (FordPass Rewards) enhancement
		promotionCountersBO.setTen_reverse_type(FordPassAprHelper.stringParser(
				promotionData.get(27), tenReverseType));
		
	}

	private void validateAllMandatoryFields(ArrayList<String> promotionData) throws Exception {
		final String METHOD_NAME = "validateAllMandatoryFields";
		log.entering(CLASS_NAME, METHOD_NAME);
		
		if ( promotionData.get(0) == null
				&& promotionData.get(1) == null && promotionData.get(3) == null
				&& promotionData.get(4) == null && promotionData.get(5) == null
				&& promotionData.get(6) == null && promotionData.get(7) == null && promotionData.get(8) == null
				&& promotionData.get(9) == null
				&& promotionData.get(10) == null
				//&& promotionData.get(12) == null //FPA enchanement #F233806
				&& promotionData.get(13) == null
				&& promotionData.get(14) == null
				&& promotionData.get(21) == null
				&& promotionData.get(22) == null
				&& promotionData.get(23) == null
				&& promotionData.get(24) == null&& promotionData.get(25) == null && promotionData.get(26) == null) {
			throw new Exception(FordPassAprConstants.TRANS_PROMOTION_COUNTERS_DATATYPE+
					FordPassAprConstants.REQUIRED_FIELDS);
		}
	}

	//Sets record to transfail if any of the required field is empty	
	private void validateMandatoryFields(ArrayList<String> promotionData){
		final String METHOD_NAME = "validateMandatoryFields";
		log.entering(CLASS_NAME, METHOD_NAME);		
		
		FordPassAprHelper.validateMandatoryField(promotionData.get(0), uniqueId);
		FordPassAprHelper.validateMandatoryField(promotionData.get(1), auditCreateDate);
		FordPassAprHelper.validateMandatoryField(promotionData.get(3), refferenceToCounterValue);
		FordPassAprHelper.validateMandatoryField(promotionData.get(4), changeDate);
		FordPassAprHelper.validateMandatoryField(promotionData.get(5), counterValueChange);
		FordPassAprHelper.validateMandatoryField(promotionData.get(6), previousValue);
		FordPassAprHelper.validateMandatoryField(promotionData.get(7), sourceTransactionId);
		FordPassAprHelper.validateMandatoryField(promotionData.get(8), associatedCounter);
		FordPassAprHelper.validateMandatoryField(promotionData.get(9), associatedTransactionRule);
		FordPassAprHelper.validateMandatoryField(promotionData.get(10), countervalueAfterTransaction);
		//FordPassAprHelper.validateMandatoryField(promotionData.get(12), code); //FPA enchanement #F233806
		FordPassAprHelper.validateMandatoryField(promotionData.get(13), order);
		FordPassAprHelper.validateMandatoryField(promotionData.get(14), cyclicFlag);
		//Commented as part of F142427
		//FordPassAprHelper.validateMandatoryField(promotionData.get(16), reversibleFlag);
		FordPassAprHelper.validateMandatoryField(promotionData.get(21), processingLevel);
		FordPassAprHelper.validateMandatoryField(promotionData.get(22), promotionCode);
		FordPassAprHelper.validateMandatoryField(promotionData.get(23), windowTimeUnit);
		FordPassAprHelper.validateMandatoryField(promotionData.get(24), promotionReference);
		FordPassAprHelper.validateMandatoryField(promotionData.get(25), transactionCTRCode);
		FordPassAprHelper.validateMandatoryField(promotionData.get(26), homeCTRCode);
	}
	
	private boolean validateSkipRecords(ArrayList<String> promotionData)
			throws Exception {
		boolean skipRecord = false;
		if (promotionData.get(0) != null) {
			if (promotionData.get(0).equalsIgnoreCase(
					FordPassAprConstants.APR_HEADER)
					|| promotionData.get(0).equalsIgnoreCase(
							FordPassAprConstants.APR_TAIL)) {
				skipRecord = true;
			} else {
				skipRecord = false;
			}
		} else {
			if (promotionData.size() == 28){
				skipRecord = false;

			} else {
				throw new Exception(FordPassAprConstants.TRANS_PROMOTION_COUNTERS_DATATYPE
						+ FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);

			}
		}
		return skipRecord;
	}
	
	// Validate TEN_REVERSE_TYPE field value is N/A/L or not 
	private void validateTenReverseTypeField(ArrayList<String> promotionData){
		FordPassAprHelper.validateTenReverseTypeField(promotionData.get(27), tenReverseType);
	}

}
