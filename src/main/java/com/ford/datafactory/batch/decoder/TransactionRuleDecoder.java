package com.ford.datafactory.batch.decoder;

import com.ford.datafactory.batch.bo.Sgm_code;
import com.ford.datafactory.batch.bo.TransactionRuleBO;
import com.ford.datafactory.batch.util.CsvUtil;
import com.ford.datafactory.batch.util.CvdpConstants;
import com.ford.datafactory.batch.util.FordPassAprConstants;
import com.ford.datafactory.batch.util.FordPassAprHelper;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;


public class TransactionRuleDecoder implements IMultipleRecordCountryDecoder{


	private static final String CLASS_NAME = DictionaryItemDataDecoder.class.getName();
	private static final Logger log = Logger.getLogger(CLASS_NAME);
	private  ArrayList<String> outputList = null;
	private ObjectMapper objectMapper = new ObjectMapper();
	private TransactionRuleBO transactionRuleBO;
	private String processStatusDetails = "";
	//private String headerTimestamp= null;
	//private String countryCode = null;
	 boolean resultStatus = false; 
	 JSONParser jsonParser = null;
	//TransactionRuleValues
	public static final String headerDate = "Timestamp_Header_CT";
	public static final String trlId = "TRL_ID";
	public static final String mcpCode = "MCP_CODE";
	public static final String trlAuditCd = "TRL_AUDIT_CD";
	public static final String trlAuditCu = "TRL_AUDIT_CU";
	public static final String trlAuditMd = "TRL_AUDIT_MD";
	public static final String trlAuditMu = "TRL_AUDIT_MU";
	public static final String trlAuditRd = "TRL_AUDIT_RD";
	public static final String trlAuditRu = "TRL_AUDIT_RU";
	public static final String trlCode = "TRL_CODE";
	public static final String trlConditionFormula = "TRL_CONDITION_FORMULA";
	public static final String trlDescription = "TRL_DESCRIPTION";
	public static final String trlEndDate = "TRL_END_DATE";
	public static final String trlName = "TRL_NAME";
	public static final String trlStartDate = "TRL_START_DATE";
	public static final String trlStatus = "TRL_STATUS";
	public static final String trlTrnChannel = "TRL_TRN_CHANNEL";
	public static final String trlTrnType = "TRL_TRN_TYPE";
	public static final String trgCode = "TRG_CODE";
	public static final String trgDescription = "TRG_DESCRIPTION";
	public static final String sgm_code="SGM_CODE";
	
	/*public TransactionRuleDecoder(String headertimestamp, String countryCode) {
		
		this.headerTimestamp = headertimestamp;
		this.countryCode=countryCode;
		this.outputList=new ArrayList<String>();
	}*/
	
	@Override
	public String decodeWithCntry(String headerTimestamp,String line, String countryCode) throws Exception {
		
		final String METHOD_NAME = "decode";

	   processStatusDetails = "";
		transactionRuleBO = new TransactionRuleBO();
		String output = null;

		try {
			
			ArrayList<String> transactionRuleData = CsvUtil.parseCsvRecord(line.toString());

			// Process if not Header or Tail
			if (!validateSkipRecords(transactionRuleData)) 
			{
				
				// Check if there are 20 fields before breakdown
				if (transactionRuleData.size() == 20) {
					
					
					//validate mandatory fields
					validateAllMandatoryFields(transactionRuleData);
					validateMandatoryFields(transactionRuleData);
					
					
					// Set all transaction fields 
					setTransactionRuleMappings(transactionRuleData,headerTimestamp);
										
					// Set partition field
					setPartitionMappings(transactionRuleData,headerTimestamp);
				
					// set raw payload.                    
                    setRawPayload(transactionRuleData);
                    
					
					// Finally -- set process states
					resultStatus = FordPassAprHelper.setProcessState();
					
					if (resultStatus){
						transactionRuleBO.setProcess_status_details(FordPassAprHelper.setProcessDetails());
						transactionRuleBO
								.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_FAILED);
					} else{
						transactionRuleBO.setProcess_status_details(null);
						transactionRuleBO
								.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_SUCCESSFUL);
					}

					transactionRuleBO.setProcess_status_date_time_utc(FordPassAprHelper
							.GetUTCdatetimeAsString());
					
					// reset process state
					FordPassAprHelper.resetProcessState();
					
					
					//outputList.clear();
			if(FordPassAprConstants.NA_EU_VALUE==countryCode){
				//For NA_EU file, only USA country code will be created. Only one row will be created 
						/*for(String code : FordPassAprConstants.NA_EU_CODES){
							transactionRuleBO.setCntry_c(code);
							//sha key generation
							FordPassAprHelper.setShaKey(FordPassAprHelper.chunkAsOneString(transactionRuleData)+code,FordPassAprConstants.TRANS_RULE_DATATYPE,transactionRuleBO);
							
							outputList.add(this.objectMapper.writeValueAsString(transactionRuleBO));
						}*/
				countryCode = FordPassAprConstants.USA_VALUE;
				transactionRuleBO.setCntry_c(countryCode);
				FordPassAprHelper.setShaKey(FordPassAprHelper.chunkAsOneString(transactionRuleData)+countryCode +","+ headerTimestamp,FordPassAprConstants.TRANS_RULE_DATATYPE,transactionRuleBO);
				
				//outputList.add(this.objectMapper.writeValueAsString(transactionRuleBO));
				output = this.objectMapper.writeValueAsString(transactionRuleBO);
				}
			else{
				
				transactionRuleBO.setCntry_c(countryCode);
			  //sha key generation
				FordPassAprHelper.setShaKey(FordPassAprHelper.chunkAsOneString(transactionRuleData)+countryCode +","+ headerTimestamp,FordPassAprConstants.TRANS_RULE_DATATYPE,transactionRuleBO);
				
			    
			    //outputList.add(this.objectMapper.writeValueAsString(transactionRuleBO));
				output = this.objectMapper.writeValueAsString(transactionRuleBO);
			 }	
					return output;
				}	
			else {
				throw new Exception(
						FordPassAprConstants.TRANS_RULE_DATATYPE + FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);	
	
		}
			}
			return null;
		}catch (Exception e) {
			log.severe(e.getMessage());
			/*if (e.getMessage() == null)
			throw new Exception(
					FordPassAprConstants.TRANS_RULE_DATATYPE + FordPassAprConstants.PROCESSING_EXCEPTION);		
			else
				throw new Exception(e.getMessage());*/
			return null;

		}
		
		
	}
	
	
	private boolean validateSkipRecords(ArrayList<String> transactionRuleData)
			throws Exception {
		boolean skipRecord = false;
		if (transactionRuleData.get(0) != null) {
			if (transactionRuleData.get(0).equalsIgnoreCase(
					FordPassAprConstants.APR_HEADER)
					|| transactionRuleData.get(0).equalsIgnoreCase(
							FordPassAprConstants.APR_TAIL)) {
				skipRecord = true;
			} else {
				skipRecord = false;
			}
		} else {
			if (transactionRuleData.size() == 20) {
				skipRecord = false;

			} else {
				throw new Exception(FordPassAprConstants.TRANS_RULE_DATATYPE
						+ FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);

			}
		}
		return skipRecord;
	}

	

	private void setRawPayload(ArrayList<String> transactionRuleData) {
		
		StringBuffer sbf = new StringBuffer();
		sbf.append(transactionRuleData.get(0));
		for (int i = 1; i < transactionRuleData.size(); i++) {
			sbf.append(",").append(transactionRuleData.get(i));
		}
		transactionRuleBO.setRaw_payload(sbf.toString());

	}
	
	

	private void setTransactionRuleMappings(ArrayList<String> transactionRuleData, String headerTimestamp) {
		final String METHOD_NAME = "setTransactionRuleMappings";
		log.entering(CLASS_NAME, METHOD_NAME);
		
		
		
		transactionRuleBO.setHeader_timestamp_utc(FordPassAprHelper.parseHeaderDateTimestamp(headerTimestamp , headerDate));

		transactionRuleBO.setTrl_id(FordPassAprHelper.longParser(transactionRuleData.get(0), trlId));

		transactionRuleBO.setMcp_code(FordPassAprHelper.stringParser(transactionRuleData.get(1), mcpCode));

		transactionRuleBO.setTrl_audit_cd(FordPassAprHelper.parseDateTimestamp(transactionRuleData.get(2), trlAuditCd));

		transactionRuleBO.setTrl_audit_cu(FordPassAprHelper.longParser(transactionRuleData.get(3), trlAuditCu));

		transactionRuleBO.setTrl_audit_md(FordPassAprHelper.parseDateTimestamp(transactionRuleData.get(4), trlAuditMd));

		transactionRuleBO.setTrl_audit_mu(FordPassAprHelper.longParser(transactionRuleData.get(5), trlAuditMu));

		transactionRuleBO.setTrl_audit_rd(FordPassAprHelper.parseDateTimestamp(transactionRuleData.get(6), trlAuditRd));

		transactionRuleBO.setTrl_audit_ru(FordPassAprHelper.longParser(transactionRuleData.get(7), trlAuditRu));

		transactionRuleBO.setTrl_code(FordPassAprHelper.stringParser(transactionRuleData.get(8), trlCode));

		transactionRuleBO.setTrl_condition_formula(FordPassAprHelper.stringParser(transactionRuleData.get(9), trlConditionFormula));

		transactionRuleBO.setTrl_description(FordPassAprHelper.stringParser(transactionRuleData.get(10), trlDescription));
		
		transactionRuleBO.setTrl_end_date(FordPassAprHelper.parseDateTimestamp(transactionRuleData.get(11), trlEndDate));

		transactionRuleBO.setTrl_name(FordPassAprHelper.stringParser(transactionRuleData.get(12), trlName));

		transactionRuleBO.setTrl_start_date(FordPassAprHelper.parseDateTimestamp(transactionRuleData.get(13), trlStartDate));

		transactionRuleBO.setTrl_status(FordPassAprHelper.stringParser(transactionRuleData.get(14), trlStatus));

		transactionRuleBO.setTrl_trn_channel(FordPassAprHelper.stringParser(transactionRuleData.get(15), trlTrnChannel));

		transactionRuleBO.setTrl_trn_type(FordPassAprHelper.stringParser(transactionRuleData.get(16), trlTrnType));

		transactionRuleBO.setTrg_code(FordPassAprHelper.stringParser(transactionRuleData.get(17), trgCode));

		transactionRuleBO.setTrg_description(FordPassAprHelper.stringParser(transactionRuleData.get(18), trgDescription));
		
		transactionRuleBO.setSgm_code(validateSgm_code(transactionRuleData.get(19)));
				
		}
	
	// create partition columns
	private void setPartitionMappings(ArrayList<String> transactionRuleData , String headerTimestamp) {
		final String METHOD_NAME = "setPartitionMappings";
		log.entering(CLASS_NAME, METHOD_NAME);
		
	
		// Add year field from header timestamp.
		transactionRuleBO.setPartition_year(FordPassAprHelper.intParser(FordPassAprHelper.transformISOPartitionDate(headerTimestamp , headerDate), headerDate));
	}
	
	private void validateAllMandatoryFields(ArrayList<String> transactionRuleData) throws Exception {
		
		if ( transactionRuleData.get(0) == null
				&& transactionRuleData.get(2) == null
				&& transactionRuleData.get(8) == null
				&& transactionRuleData.get(12) == null
				&& transactionRuleData.get(13) == null
				&& transactionRuleData.get(14) == null
				&& transactionRuleData.get(16) == null
				//Commented as part of "F148027 - FPA _ Transaction & Transaction Rule change"
				//&& transactionRuleData.get(17) == null
				)
			/*FordPassAprHelper.processLogEvent(FordPassAprConstants.TRANS_RULE_DATATYPE,
					FordPassAprConstants.REQUIRED_FIELDS_ERR_DETAILS);*/
		throw new Exception(
				FordPassAprConstants.TRANS_RULE_DATATYPE
						+ FordPassAprConstants.REQUIRED_FIELDS);
	}
	
	//Sets record to transfail if any of the required field is empty	
	private void validateMandatoryFields(ArrayList<String> transactionRuleData){
		final String METHOD_NAME = "validateMandatoryFields";
		log.entering(CLASS_NAME, METHOD_NAME);		
		
		FordPassAprHelper.validateMandatoryField(transactionRuleData.get(0), trlId);
		FordPassAprHelper.validateMandatoryField(transactionRuleData.get(2), trlAuditCd);
		FordPassAprHelper.validateMandatoryField(transactionRuleData.get(8), trlCode);
		FordPassAprHelper.validateMandatoryField(transactionRuleData.get(12), trlName);
		FordPassAprHelper.validateMandatoryField(transactionRuleData.get(13), trlStartDate);
		FordPassAprHelper.validateMandatoryField(transactionRuleData.get(14), trlStatus);
		FordPassAprHelper.validateMandatoryField(transactionRuleData.get(16), trlTrnType);
		//Commented as part of "F148027 - FPA _ Transaction & Transaction Rule change"
		//FordPassAprHelper.validateMandatoryField(transactionRuleData.get(17), trgCode);
				
	}
	private Sgm_code validateSgm_code(String jsonVal){
			Sgm_code dummyBO = null;			
			if(jsonVal!=null){
			jsonVal = jsonVal.replaceAll("\"\"", "\"");		
		try {
			dummyBO =objectMapper.readValue(jsonVal, Sgm_code.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			FordPassAprHelper.processLogEvent(sgm_code,FordPassAprConstants.EXCEPTION_COMPLEX_FIELD);
			return dummyBO;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			FordPassAprHelper.processLogEvent(sgm_code,FordPassAprConstants.EXCEPTION_COMPLEX_FIELD);
			return dummyBO;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			FordPassAprHelper.processLogEvent(sgm_code,FordPassAprConstants.EXCEPTION_COMPLEX_FIELD);
			return dummyBO;
		}
			}
		return dummyBO;					
	}
}
