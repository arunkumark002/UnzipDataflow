package com.ford.datafactory.batch.decoder;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.ford.datafactory.batch.bo.TransactionAttributesBO;
import com.ford.datafactory.batch.util.*;
import org.codehaus.jackson.map.ObjectMapper;

public class TransactionAttributesDataDecoder implements IMultipleRecordDecoder {
	
	private static final String CLASS_NAME = TransactionAttributesDataDecoder.class.getName();
	private static final Logger log = Logger.getLogger(CLASS_NAME);

	ObjectMapper objectMapper = new ObjectMapper();
	TransactionAttributesBO transactionAttributesBO;
	
	String processStatusDetails = "";
	//String headerTimestamp = null;
	
	boolean fieldResultStatus = false;
	
	public static final String headerDate = "HEADER_DATETIME";
	public static final String traId = "TRA_ID";
	public static final String traTrnId = "TRA_TRN_ID";
	public static final String traValue = "TRA_VALUE";
	public static final String teaAttrDomain = "TEA_ATTR_DOMAIN";
	public static final String teaConditionFormula = "TEA_CONDITION_FORMULA";
	public static final String teaProcessLevel = "TEA_PROCESS_LEVEL";
	public static final String teaTrlId = "TEA_TRL_ID";
	public static final String teaValueFormula = "TEA_VALUE_FORMULA";
	public static final String atrCode = "ATR_CODE";
	public static final String atrName = "ATR_NAME";
	public static final String atrDescription = "ATR_DESCRIPTION";
	public static final String atrDefaultValue = "ATR_DEFAULT_VALUE";
	public static final String ctrCodeTransaction = "CTR_CODE_TRANSACTION";
	public static final String ctrCodeHome = "CTR_CODE_HOME";
	
	
	/*public TransactionAttributesDataDecoder(String headertimestamp) {
		
		this.headerTimestamp = headertimestamp;		
	}*/
	
	/** 
	 * decode method which will be called from BaseTransform for decode the input line
	 * 
	 * @param line
	 */
	public String decode(String headerTimestamp,String line) throws Exception {
		final String METHOD_NAME = "decode";

		log.info("********** In the TransactionAttributes decode method **********" );
		processStatusDetails = "";		
		transactionAttributesBO = new TransactionAttributesBO();

		try {

			ArrayList<String> transactionAttributesData = CsvUtil.parseCsvRecord(line.toString());
			
			// Process if not Header or Tail
						if (!validateSkipRecords(transactionAttributesData)) {
							log.info(" ***** Passed header and failure test (TransactionAttributes) ***");							
							
							// validate input records columns count
							if (transactionAttributesData.size() == 14) {
								
								
																																
								log.info(" ***** Passed input columns test (TransactionAttributes) ***");
								// Fail processing
								validateAllMandatoryFields(transactionAttributesData);
								validateMandatoryFields(transactionAttributesData);
																													
								// Set partition field
								setPartitionMappings(transactionAttributesData, headerTimestamp);
																														
								log.info(" ***** Fail processing completed (TransactionAttributes) ***");
			
								// SHA key generation
								try {
									// Remove audit columns before generating the SHA KEY.
									transactionAttributesBO
											.setSha_key(HashUtil.calculateSHA256(chunckAsOneString(transactionAttributesData) +","+ headerTimestamp));

								} catch (Exception e) {
									log.info(" ***** Exception occured while generating the SHA KEY (TransactionAttributes) ***");
									e.printStackTrace();
									throw new Exception(
											FordPassAprConstants.TRANSACTION_ATTRIBUTES_DATATYPE
													+ FordPassAprConstants.PAYLOAD_SHAKEY_ERR_DETAILS);
								}
								log.info(" ***** SHA KEY has been generated (TransactionAttributes) ***");
								
								setTransactionAttributesBO(transactionAttributesData,headerTimestamp);
								log.info(" ***** BO has been set (TransactionAttributes) ******");
								setRawPayload(transactionAttributesData);
								log.info(" ***** Raw payload has been set (TransactionAttributes) ***");
								// Finally -- set process states
								fieldResultStatus = FordPassAprHelper.setProcessState();
								
								if (fieldResultStatus) {
									log.info(" ***** Trans failed occured (TransactionAttributes) ***");
									transactionAttributesBO
											.setProcess_status_details(FordPassAprHelper.setProcessDetails());

									transactionAttributesBO
											.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_FAILED);
								} else {
									log.info(" ***** Transfermation Successful (TransactionAttributes) ***");
									transactionAttributesBO.setProcess_status_details(null);

									transactionAttributesBO
											.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_SUCCESSFUL);
								}
								
								transactionAttributesBO
								.setProcess_status_date_time_utc(FordPassAprHelper
										.GetUTCdatetimeAsString());
						// reset the Process State
						FordPassAprHelper.resetProcessState();
						log.info(" ***** End decode method (TransactionAttributes) ***");
						
						return this.objectMapper
								.writeValueAsString(transactionAttributesBO);
						
												
					} else {
						throw new Exception(FordPassAprConstants.TRANSACTION_ATTRIBUTES_DATATYPE
								+ FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);
					}

				} else {
					return null;
				}
			} catch (Exception e) {
			log.severe(e.getMessage());
				/*if (e.getMessage() == null)
					throw new Exception(FordPassAprConstants.TRANSACTION_ATTRIBUTES_DATATYPE
							+ FordPassAprConstants.PROCESSING_EXCEPTION);
				else
					throw new Exception(e.getMessage());*/
			return null;
			}
								
	 }
	
	/** 
	 * Validating the Header & Tail
	 * 
	 * @param transactionAttributesData
	 */
    private boolean validateSkipRecords(ArrayList<String> transactionAttributesData)
    		throws Exception {
    	boolean skipRecord = false;
		if (transactionAttributesData.get(0) != null) {
			if (transactionAttributesData.get(0).equalsIgnoreCase(FordPassAprConstants.APR_HEADER)
					|| transactionAttributesData.get(0).equalsIgnoreCase(FordPassAprConstants.APR_TAIL)) {
				skipRecord = true;
			} else {
				skipRecord = false;
			}
		} else {
			if (transactionAttributesData.size() == 14) {
				skipRecord = false;

			} else {
				log.info("not equal to 14 columns, inside validate skip records method");
				throw new Exception(FordPassAprConstants.TRANSACTION_ATTRIBUTES_DATATYPE
						+ FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);

			}
		}
		return skipRecord;
	}
	

 	
	/** 
	 * Validating the Mandatory fields
	 *  
	 * @param transactionAttributesData
	 *
	 */
    private void validateAllMandatoryFields(ArrayList<String> transactionAttributesData) throws Exception {
   	
   	 final String METHOD_NAME = "validateAllMandatoryFields";
		 log.entering(CLASS_NAME, METHOD_NAME);		 
		 if (transactionAttributesData.get(0) == null && transactionAttributesData.get(1) == null 
				 && transactionAttributesData.get(5) == null && transactionAttributesData.get(6) == null 
				 && transactionAttributesData.get(8) == null && transactionAttributesData.get(9) == null 
				 && transactionAttributesData.get(12) == null && transactionAttributesData.get(13) == null)
			
			 throw new Exception(FordPassAprConstants.TRANSACTION_ATTRIBUTES_DATATYPE +	FordPassAprConstants.REQUIRED_FIELDS);
		 
		}
    
	//Sets record to transfail if any of the required field is empty	
	private void validateMandatoryFields(ArrayList<String> transactionAttributesData){
		final String METHOD_NAME = "validateMandatoryFields";
		log.entering(CLASS_NAME, METHOD_NAME);
			
		FordPassAprHelper.validateMandatoryField(transactionAttributesData.get(0), traId); 
		FordPassAprHelper.validateMandatoryField(transactionAttributesData.get(1), traTrnId);
		FordPassAprHelper.validateMandatoryField(transactionAttributesData.get(5), teaProcessLevel);
		FordPassAprHelper.validateMandatoryField(transactionAttributesData.get(6), teaTrlId);
		FordPassAprHelper.validateMandatoryField(transactionAttributesData.get(8), atrCode);
		FordPassAprHelper.validateMandatoryField(transactionAttributesData.get(9), atrName);
		FordPassAprHelper.validateMandatoryField(transactionAttributesData.get(12), ctrCodeTransaction);
		FordPassAprHelper.validateMandatoryField(transactionAttributesData.get(13), ctrCodeHome);
					
	}
	    
	/** 
	 * Setting the value to Partition partition_year
	 *  
	 * @param transactionAttributesData
	 * @param headerTimestamp
	 */
	private void setPartitionMappings(ArrayList<String> transactionAttributesData,
			String headerTimestamp) {
		final String METHOD_NAME = "setPartitionMappings";
		log.entering(CLASS_NAME, METHOD_NAME);
		
		// Add year field from header timestamp.
		transactionAttributesBO.setPartition_year(FordPassAprHelper.intParser(FordPassAprHelper.transformISOPartitionDate(headerTimestamp, headerDate), headerDate));
	}
	
	/** 
	 * Setting the TransactionAttributesBO
	 *  
	 * @param transactionAttributesData
	 */
    private void setTransactionAttributesBO(ArrayList<String> transactionAttributesData, String headerTimestamp) {

    	final String METHOD_NAME = "setTransactionAttributesBO";
    	
    	log.entering(CLASS_NAME, METHOD_NAME);
    	
    	
    	transactionAttributesBO.setHeader_timestamp_utc(FordPassAprHelper.parseHeaderDateTimestamp(headerTimestamp, headerDate));
    	    	
    	transactionAttributesBO.setTra_id(FordPassAprHelper.longParser(
    			transactionAttributesData.get(0), traId));
    	transactionAttributesBO.setTra_trn_id(FordPassAprHelper.longParser(
    			transactionAttributesData.get(1), traTrnId));
    	transactionAttributesBO.setTra_value(FordPassAprHelper.stringParser(
    			transactionAttributesData.get(2), traValue));
    	transactionAttributesBO.setTea_attr_domain(FordPassAprHelper.stringParser(
    			transactionAttributesData.get(3), teaAttrDomain));
    	transactionAttributesBO.setTea_condition_formula(FordPassAprHelper.stringParser(
    			transactionAttributesData.get(4), teaConditionFormula));
    	transactionAttributesBO.setTea_process_level(FordPassAprHelper.stringParser(
    			transactionAttributesData.get(5), teaProcessLevel));    	
    	transactionAttributesBO.setTea_trl_id(FordPassAprHelper.longParser(
    			transactionAttributesData.get(6), teaTrlId));
    	transactionAttributesBO.setTea_value_formula(FordPassAprHelper.stringParser(
    			transactionAttributesData.get(7), teaValueFormula));
    	transactionAttributesBO.setAtr_code(FordPassAprHelper.stringParser(
    			transactionAttributesData.get(8), atrCode));
    	transactionAttributesBO.setAtr_name(FordPassAprHelper.stringParser(
    			transactionAttributesData.get(9), atrName));
    	transactionAttributesBO.setAtr_description(FordPassAprHelper.stringParser(
    			transactionAttributesData.get(10), atrDescription));
    	transactionAttributesBO.setAtr_default_value(FordPassAprHelper.stringParser(
    			transactionAttributesData.get(11), atrDefaultValue));
    	transactionAttributesBO.setCtr_code_transaction(FordPassAprHelper.stringParser(
    			transactionAttributesData.get(12), ctrCodeTransaction));
    	transactionAttributesBO.setCtr_code_home(FordPassAprHelper.stringParser(
    			transactionAttributesData.get(13), ctrCodeHome));
    	}	
    
	/** 
	 * Setting the Raw payload
	 *  
	 * @param transactionAttributesData
	 */    
	private void setRawPayload(ArrayList<String> transactionAttributesData) {
		
		StringBuffer sbf = new StringBuffer();
		sbf.append(transactionAttributesData.get(0));
		for (int i = 1; i < transactionAttributesData.size(); i++) {
			sbf.append(",").append(transactionAttributesData.get(i));
		}
		transactionAttributesBO.setRaw_payload(sbf.toString());

	}
	
	
	private String chunckAsOneString(ArrayList<String> locationData) {
		
		StringBuffer sbf1 = new StringBuffer();	
		sbf1.append(locationData.get(0));
		 for(int i=1; i < locationData.size(); i++){
            sbf1.append(",").append(locationData.get(i));
    }
		return sbf1.toString();
		
	}
}
