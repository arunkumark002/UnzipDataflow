package com.ford.datafactory.batch.decoder;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.ford.datafactory.batch.bo.TransactionBadgesBO;
import com.ford.datafactory.batch.util.*;
import org.codehaus.jackson.map.ObjectMapper;


public class TransactionBadgesDataDecoder implements IMultipleRecordCountryDecoder{

	private static final String CLASS_NAME = TransactionBadgesDataDecoder.class.getName();
	private static final Logger log = Logger.getLogger(CLASS_NAME);
	private  ArrayList<String> outputList = null;
	private ObjectMapper objectMapper = new ObjectMapper();
	private TransactionBadgesBO transactionBadgesBO;
	//private String headerTimestamp= null;
	//private String countryCode = null;
	boolean resultStatus = false; 
	
	public static final String headerDate = "HEADER_DATETIME";		
	public static final String trbId = "TRB_ID";
	public static final String trlCode = "TRL_CODE";
	public static final String tbaOrder = "TBA_ORDER";
	public static final String tbaProcessLevel = "TBA_PROCESS_LEVEL";
	public static final String trnId = "TRN_ID";
	public static final String bdgId = "BDG_ID";
	public static final String ctrCode = "CTR_CODE";

	
	/*public TransactionBadgesDataDecoder(String headertimestamp, String countryCode) {
		
		this.headerTimestamp = headertimestamp;
		this.countryCode=countryCode;
		this.outputList=new ArrayList<String>();
	}*/

	
	public String decodeWithCntry(String headerTimestamp,String line,String countryCode) throws Exception  {
		final String METHOD_NAME = "decode";
		log.entering(CLASS_NAME, METHOD_NAME);
		
		log.info(" comes to decode ***" );
		transactionBadgesBO = new TransactionBadgesBO();
		String output = null;
		
		try {
			
			ArrayList<String> transactionBadgesData = CsvUtil.parseCsvRecord(line.toString());

			
			if (!validateSkipRecords(transactionBadgesData)) {	
				
				if (transactionBadgesData.size() == 6) {
					
					/*PropertiesUtil.loadAllProps();
					
					final PropertyManager pm = PropertyManager.getInstance();
				
					final PropertyGroup dynamicGroup = pm.getGroup(CvdpConstants.PROPERTY_GROUP_DYNAMIC);*/
					
					//ArrayList<String> transactionbadgesCtrCode = CsvUtil.parseCsvRecord(dynamicGroup.getString(FordPassAprConstants.TRANSACTION_BADGES_PROPERTY_VALUE));

					ArrayList<String> transactionbadgesCtrCode = CsvUtil.parseCsvRecord(FordPassAprConstants.TRANSACTION_BADGES_PROPERTY_VALUE);
					
					// Fail processing 
					validateAllMandatoryFields(transactionBadgesData);
					validateMandatoryFields(transactionBadgesData);
				
                    setTransactionBadgesMappings(transactionBadgesData,headerTimestamp);
                    
                    setRawPayload(transactionBadgesData);
					
					// Finally -- set process states
					resultStatus = FordPassAprHelper.setProcessState();
					
					if (resultStatus){	
						
						transactionBadgesBO.setProcess_status_details(FordPassAprHelper.setProcessDetails());	
						
						transactionBadgesBO
								.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_FAILED);
					} else {		
						
						transactionBadgesBO.setProcess_status_details(null);		
						
						transactionBadgesBO
								.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_SUCCESSFUL);
					}
					
					transactionBadgesBO.setProcess_status_date_time_utc(FordPassAprHelper
							.GetUTCdatetimeAsString());
					
					// reset 
					FordPassAprHelper.resetProcessState();
					
					//return this.objectMapper.writeValueAsString(transactionBadgesBO);
						//outputList.clear();
						if(FordPassAprConstants.NA_EU_VALUE==countryCode){
							log.info("comes to NA_EU country code check***");
							for(String code : transactionbadgesCtrCode){
								log.info("setting to country code check***"+code);
								transactionBadgesBO.setCtr_code(code);
								try{
								transactionBadgesBO.setSha_key(HashUtil.calculateSHA256(chunckAsOneString(transactionBadgesData)+code +","+ headerTimestamp));
								}catch (Exception e) {
									e.printStackTrace();
									throw new Exception(
											FordPassAprConstants.TRANSACTION_BADGES_DATATYPE
													+ FordPassAprConstants.PAYLOAD_SHAKEY_ERR_DETAILS);
								}

								//outputList.add(this.objectMapper.writeValueAsString(transactionBadgesBO));
								output = this.objectMapper.writeValueAsString(transactionBadgesBO);
							}
						}
						else {
							transactionBadgesBO.setCtr_code(countryCode);
						    log.info("comes to OTHER country code check***");
						    try{
						    transactionBadgesBO.setSha_key(HashUtil.calculateSHA256(chunckAsOneString(transactionBadgesData)+countryCode +","+ headerTimestamp));
						}catch (Exception e) {
							e.printStackTrace();
							throw new Exception(
									FordPassAprConstants.TRANSACTION_BADGES_DATATYPE
											+ FordPassAprConstants.PAYLOAD_SHAKEY_ERR_DETAILS);
						}
						    //outputList.add(this.objectMapper.writeValueAsString(transactionBadgesBO));
							output = this.objectMapper.writeValueAsString(transactionBadgesBO);
						    }	
						//return outputList;
					return output;
					}	else {
						throw new Exception(
								FordPassAprConstants.TRANSACTION_BADGES_DATATYPE + FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);	
			
				}
				}else {return null;}
				
		} catch (Exception e) {
			log.severe(e.getMessage());
			/*log.info("exception occured in Decoder***");
			log.info(e.getMessage());
			if (e.getMessage() == null){
				throw new Exception(
						FordPassAprConstants.TRANSACTION_BADGES_DATATYPE + FordPassAprConstants.PROCESSING_EXCEPTION);	}	
				else
					throw new Exception(e.getMessage());*/
			return null;
		}
		
	}

	private boolean validateSkipRecords(ArrayList<String> transactionBadgesData)
			throws Exception {
		boolean skipRecord = false;
		if (transactionBadgesData.get(0) != null) {
			if (transactionBadgesData.get(0).equalsIgnoreCase(
					FordPassAprConstants.APR_HEADER)
					|| transactionBadgesData.get(0).equalsIgnoreCase(
							FordPassAprConstants.APR_TAIL)) {
				System.out.println("Skip Record true");
				skipRecord = true;
			} else {
				System.out.println("else:: Skip Record false");
				skipRecord = false;
			}
		} else {
			if (transactionBadgesData.size() == 6) {
				skipRecord = false;

			} else {	System.out.println("else:: throw exception");
				throw new Exception(FordPassAprConstants.TRANSACTION_BADGES_DATATYPE
						+ FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);

			}
		}
		return skipRecord;
	}

	private void setRawPayload(ArrayList<String> transactionBadgesData) {
		
		StringBuffer sbf = new StringBuffer();		
		sbf.append(transactionBadgesData.get(0));
        for(int i=1; i < transactionBadgesData.size(); i++){
                sbf.append(",").append(transactionBadgesData.get(i));
        }		
        transactionBadgesBO.setRaw_payload(sbf.toString());
		
	}


	private String chunckAsOneString(ArrayList<String> transactionBadgesData) {
		
		StringBuffer sbf1 = new StringBuffer();	
		sbf1.append(transactionBadgesData.get(0));
		 for(int i=1; i < transactionBadgesData.size(); i++){
            sbf1.append(",").append(transactionBadgesData.get(i));
    }
		return sbf1.toString();
		
	}


	private void setTransactionBadgesMappings(ArrayList<String> transactionBadgesData, String headerTimestamp) throws Exception {
		
		final String METHOD_NAME = "setTransactionBadgesMappings";
		
		log.entering(CLASS_NAME, METHOD_NAME);
		
		transactionBadgesBO.setHeader_timestamp_utc(FordPassAprHelper.parseHeaderDateTimestamp(headerTimestamp , headerDate));
		
		transactionBadgesBO.setTrb_id(FordPassAprHelper.longParser(transactionBadgesData.get(0), trbId));
		
		transactionBadgesBO.setTrl_code(FordPassAprHelper.stringParser(transactionBadgesData.get(1), trlCode));

		transactionBadgesBO.setTba_order(FordPassAprHelper.longParser(transactionBadgesData.get(2), tbaOrder));
		
		transactionBadgesBO.setTba_process_level(FordPassAprHelper.stringParser(transactionBadgesData.get(3), tbaProcessLevel));
		
		transactionBadgesBO.setTrn_id(FordPassAprHelper.longParser(transactionBadgesData.get(4), trnId));
		
		transactionBadgesBO.setBdg_id(FordPassAprHelper.longParser(transactionBadgesData.get(5), bdgId));
	}


	private void validateAllMandatoryFields(ArrayList<String> transactionBadgesData) throws Exception{
		final String METHOD_NAME = "validateAllMandatoryFields";
		log.entering(CLASS_NAME, METHOD_NAME);
		
		if ( transactionBadgesData.get(0) == null &&
				transactionBadgesData.get(2) == null &&
				transactionBadgesData.get(3) == null &&
				transactionBadgesData.get(4) == null &&
				transactionBadgesData.get(5) == null  )
		{
			throw new Exception(
					FordPassAprConstants.TRANSACTION_BADGES_DATATYPE
							+ FordPassAprConstants.REQUIRED_FIELDS);
		}
	}
	
	//Sets record to transfail if any of the required field is empty	
	private void validateMandatoryFields(ArrayList<String> transactionBadgesData){
		final String METHOD_NAME = "validateMandatoryFields";
		log.entering(CLASS_NAME, METHOD_NAME);		
		FordPassAprHelper.validateMandatoryField(transactionBadgesData.get(0), trbId);
		FordPassAprHelper.validateMandatoryField(transactionBadgesData.get(2), tbaOrder);
		FordPassAprHelper.validateMandatoryField(transactionBadgesData.get(3), tbaProcessLevel);
		FordPassAprHelper.validateMandatoryField(transactionBadgesData.get(4), trnId);
		FordPassAprHelper.validateMandatoryField(transactionBadgesData.get(5), bdgId);
							
	}
	
	
	
}
