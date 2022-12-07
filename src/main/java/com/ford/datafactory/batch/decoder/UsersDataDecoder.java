package com.ford.datafactory.batch.decoder;

import com.ford.datafactory.batch.bo.UsersBO;
import com.ford.datafactory.batch.util.*;
import com.ford.it.cvdp.util.*;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.ArrayList;
import java.util.logging.Logger;

public class UsersDataDecoder implements IMultipleRecordDecoder {
	
	private static final String CLASS_NAME = UsersDataDecoder.class.getName();
	private static final Logger log = Logger.getLogger(CLASS_NAME);
	private  ArrayList<String> outputList = null;
	private ObjectMapper objectMapper = new ObjectMapper();
	private com.ford.datafactory.batch.bo.UsersBO UsersBO;
	@SuppressWarnings("unused")
	private String processStatusDetails = "";
	//private String headerTimestamp= null;
	private String countryCode = null;
	boolean resultStatus = false; 

	// User Details	
	public static final String headerDate = "HEADER_DATETIME";
	public static final String userId = "USR_ID";
	public static final String userAuditCreatedDate  = "USR_AUDIT_CD";
	public static final String userAuditCreatingUserId  = "USR_AUDIT_CU";
	public static final String userAuditModifiedDate  = "USR_AUDIT_MD";
	public static final String userAuditModifyingUserId	 = "USR_AUDIT_MU";
	public static final String userAuditRemovedDate  = "USR_AUDIT_RD";
	public static final String userAuditRemovingUserId  = "USR_AUDIT_RU";
	public static final String userPasswordBlockDate = "USR_BLOCK_DATE";	
	public static final String userCompanyId  = "USR_COM_ID";	
	public static final String userEmail  = "USR_EMAIL";	
	public static final String userEmailPermission  = "USR_EMAIL_PERMISSION";	
	public static final String userExternalId  = "USR_EXT_ID";	
	public static final String userFirstName  = "USR_FIRST_NAME";	
	public static final String userLastLoginDate  = "USR_LAST_LOGIN_DATE";	
	public static final String userLastName  = "USR_LAST_NAME";	
	public static final String userLanguageCode  = "USR_LNG_CODE";	
	public static final String userLocationId  = "USR_LOC_ID";	
	public static final String userLogin  = "USR_LOGIN";	
	public static final String userMobile  = "USR_MOBILE";	
	public static final String userModule  = "USR_MODULE";	
	public static final String userPhone  = "USR_PHONE";	
	public static final String userPasswordChangeDate  = "USR_PWD_CHANGE_DATE";	
	public static final String userPasswordFailureCounter  = "USR_PWD_FAILURE_COUNTER";	
	public static final String userStatus  = "USR_STATUS";	
	public static final String userTaskNotify  = "USR_TASK_NOTIFY";
	public static final String prgCode = "PRG_CODE";
	
	
	/*public UsersDataDecoder(String headertimestamp, String countryCode){
		this.headerTimestamp = headertimestamp;
		this.countryCode=countryCode;
		this.outputList=new ArrayList<String>();
	}*/
	
	public String decode(String fileName,String line) throws Exception {
		final String METHOD_NAME = "decode";
		processStatusDetails = "";
		UsersBO = new UsersBO();
		String output = null;

		String[] fileNameWithoutExt = fileName.substring(0,fileName.lastIndexOf('.')).split("_");
		String headerTimestamp = fileNameWithoutExt[fileNameWithoutExt.length - 1];
		
		try {
			
			ArrayList<String> usersData = CsvUtil.parseCsvRecord(line.toString());
			
			if (!validateSkipRecords(usersData)) {	
				
				// FPA �  enhancement CR# F177282 - US1073590 - Addition of prg_code attribute // size increased from 25 to 26 on 18/07/2019
				if (usersData.size() == 26) {
					
					// Fail processing 
					validateAllMandatoryFields(usersData);
					
					validateMandatoryFields(usersData);
				
					setUsersMappings(usersData,headerTimestamp);
                    
                    setRawPayload(usersData);
                    
                 // FPA �  enhancement CR# F177282 - US1073590 - Addition of prg_code attribute
                    setProgramCode(usersData);
					
					// Finally -- set process states
					resultStatus = FordPassAprHelper.setProcessState();
					
					if (resultStatus){	
						
						UsersBO.setProcess_status_details(FordPassAprHelper.setProcessDetails());	
						
						UsersBO.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_FAILED);
					} else {		
						
						UsersBO.setProcess_status_details(null);		
						
						UsersBO.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_SUCCESSFUL);
					}
					
					UsersBO.setProcess_status_date_time_utc(FordPassAprHelper
							.GetUTCdatetimeAsString());
					
					// reset 
					FordPassAprHelper.resetProcessState();
					
					//return this.objectMapper.writeValueAsString(UsersBO);
						outputList.clear();
						if(FordPassAprConstants.NA_EU_VALUE==countryCode){
							log.info("comes to NA_EU country code check***");
							for(String code : FordPassAprConstants.NA_EU_CODES){
								log.info("setting to country code check***"+code);
								UsersBO.setCntry_c(code);
								try{
								UsersBO.setSha_key(HashUtil.calculateSHA256(chunckAsOneString(usersData)+code +","+ headerTimestamp));
								}catch (Exception e) {
									e.printStackTrace();
									throw new Exception(
											FordPassAprConstants.USERS_DATATYPE
													+ FordPassAprConstants.PAYLOAD_SHAKEY_ERR_DETAILS);
								}

								//outputList.add(this.objectMapper.writeValueAsString(UsersBO));
								output = this.objectMapper.writeValueAsString(UsersBO);
							}
						}
						else {
							UsersBO.setCntry_c(countryCode);
						    log.info("comes to OTHER country code check***");
						    try{
						    	UsersBO.setSha_key(HashUtil.calculateSHA256(chunckAsOneString(usersData)+countryCode +","+ headerTimestamp));
						}catch (Exception e) {
							e.printStackTrace();
							throw new Exception(
									FordPassAprConstants.USERS_DATATYPE
											+ FordPassAprConstants.PAYLOAD_SHAKEY_ERR_DETAILS);
						}
						    //outputList.add(this.objectMapper.writeValueAsString(UsersBO));
							output = this.objectMapper.writeValueAsString(UsersBO);
						    }	
						return output;
					}	
				else {
					throw new Exception(
							FordPassAprConstants.USERS_DATATYPE + FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);	
		
			}
			}
				return null;
		} catch (Exception e) {
			log.info("exception occured in Decoder***");
			log.info(e.getMessage());
			if (e.getMessage() == null)
				throw new Exception(
						FordPassAprConstants.USERS_DATATYPE + FordPassAprConstants.PROCESSING_EXCEPTION);		
				else
					throw new Exception(e.getMessage());
		}
		
	}

    							
      
    private boolean validateSkipRecords(ArrayList<String> usersData)
    		throws Exception {
    	boolean skipRecord = false;
		if (usersData.get(0) != null) {
			if (usersData.get(0).equalsIgnoreCase(FordPassAprConstants.APR_HEADER)
					|| usersData.get(0).equalsIgnoreCase(FordPassAprConstants.APR_TAIL)) {
				skipRecord = true;
			} else {
				skipRecord = false;
			}
		} else {
			if (usersData.size() == 26) {
				skipRecord = false;

			} else {
				throw new Exception(FordPassAprConstants.USERS_DATATYPE
						+ FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);

			}
		}
		return skipRecord;
	}
    
	private void setRawPayload(ArrayList<String> usersData) {
		
		StringBuffer sbf = new StringBuffer();		
		sbf.append(usersData.get(0));
        for(int i=1; i < usersData.size(); i++){
                sbf.append(",").append(usersData.get(i));
        }		
        UsersBO.setRaw_payload(sbf.toString());
		
	}


	private String chunckAsOneString(ArrayList<String> usersData) {
		
		StringBuffer sbf1 = new StringBuffer();	
		sbf1.append(usersData.get(0));
		 for(int i=1; i < usersData.size(); i++){
            sbf1.append(",").append(usersData.get(i));
    }
		return sbf1.toString();	
	}
	
	// FPA �  enhancement CR# F177282 - US1073590 - Addition of prg_code attribute
	private void setProgramCode(ArrayList<String> usersData) 
    {
    	final String METHOD_NAME = "setProgramCode";
    	log.entering(CLASS_NAME, METHOD_NAME);
    	
    	UsersBO.setPrg_code(FordPassAprHelper.stringParser(usersData.get(25), prgCode));
	}
	
	private void setUsersMappings(ArrayList<String> usersData, String headerTimestamp) {

    	final String METHOD_NAME = "setUsersBO";

    	log.entering(CLASS_NAME, METHOD_NAME);
    	
    	
    	UsersBO.setPartition_year(FordPassAprHelper.intParser(
				FordPassAprHelper.transformISOPartitionDate(
						headerTimestamp, headerDate), headerDate));
  
    	UsersBO.setHeader_timestamp_utc(FordPassAprHelper    			
    			.parseHeaderDateTimestamp(headerTimestamp, headerDate));
    	UsersBO.setUsr_id(FordPassAprHelper.longParser(
    			usersData.get(0), userId));
    	UsersBO.setUsr_audit_cd(FordPassAprHelper.parseDateTimestamp(
    			usersData.get(1), userAuditCreatedDate));
    	UsersBO.setUsr_audit_cu(FordPassAprHelper.longParser(
    			usersData.get(2), userAuditCreatingUserId));
    	UsersBO.setUsr_audit_md(FordPassAprHelper.parseDateTimestamp(
    			usersData.get(3), userAuditModifiedDate));
    	UsersBO.setUsr_audit_mu(FordPassAprHelper.longParser(
    			usersData.get(4), userAuditModifyingUserId));
    	UsersBO.setUsr_audit_rd(FordPassAprHelper.parseDateTimestamp(
    			usersData.get(5), userAuditRemovedDate));    	
    	UsersBO.setUsr_audit_ru(FordPassAprHelper.longParser(
    			usersData.get(6), userAuditRemovingUserId));
    	UsersBO.setUsr_block_date(FordPassAprHelper.parseDateTimestamp(
    			usersData.get(7), userPasswordBlockDate));
    	UsersBO.setUsr_com_id(FordPassAprHelper.longParser(
    			usersData.get(8), userCompanyId));
    	UsersBO.setUsr_email(FordPassAprHelper.stringParser(
    			usersData.get(9), userEmail));
    	UsersBO.setUsr_email_permission(FordPassAprHelper.stringParser(
    			usersData.get(10), userEmailPermission));
    	UsersBO.setUsr_ext_id(FordPassAprHelper.stringParser(
    			usersData.get(11), userExternalId));
    	UsersBO.setUsr_first_name(FordPassAprHelper.stringParser(
    			usersData.get(12), userFirstName));
    	UsersBO.setUsr_last_login_date(FordPassAprHelper.parseDateTimestamp(
    			usersData.get(13), userLastLoginDate));
    	UsersBO.setUsr_last_name(FordPassAprHelper.stringParser(
    			usersData.get(14), userLastName));
    	UsersBO.setUsr_lng_code(FordPassAprHelper
    			.stringParser(usersData.get(15), userLanguageCode));
    	UsersBO.setUsr_loc_id(FordPassAprHelper.longParser(
    			usersData.get(16), userLocationId));
    	UsersBO.setUsr_login(FordPassAprHelper
    			.stringParser(usersData.get(17), userLogin));
    	UsersBO.setUsr_mobile(FordPassAprHelper.stringParser(
    			usersData.get(18), userMobile));
    	UsersBO.setUsr_module(FordPassAprHelper.stringParser(
    			usersData.get(19), userModule));
    	UsersBO.setUsr_phone(FordPassAprHelper.stringParser(
    			usersData.get(20), userPhone));
    	UsersBO.setUsr_pwd_change_date(FordPassAprHelper
    			.parseDateTimestamp(usersData.get(21), userPasswordChangeDate));
    	UsersBO.setUsr_pwd_failure_counter(FordPassAprHelper.intParser(
    			usersData.get(22), userPasswordFailureCounter));
    	UsersBO.setUsr_status(FordPassAprHelper.stringParser(
    			usersData.get(23), userStatus));
    	UsersBO.setUsr_task_notify(FordPassAprHelper.stringParser(
    			usersData.get(24), userTaskNotify));  	

    	}
	
	private void validateAllMandatoryFields(ArrayList<String> usersData) throws Exception {
   	 final String METHOD_NAME = "validateAllMandatoryFields";
		 log.entering(CLASS_NAME, METHOD_NAME);
		 
		 if ( usersData.get(0) == null
		 		 && usersData.get(1) == null && usersData.get(12) == null
				 && usersData.get(14) == null && usersData.get(17) == null
				 && usersData.get(23) == null)
			 
			 throw new Exception(
					FordPassAprConstants.USERS_DATATYPE
							+ FordPassAprConstants.REQUIRED_FIELDS); 
	}
	

	//Sets record to transfail if any of the required field is empty	
	private void validateMandatoryFields(ArrayList<String> usersData){
		final String METHOD_NAME = "validateMandatoryFields";
		log.entering(CLASS_NAME, METHOD_NAME);

		FordPassAprHelper.validateMandatoryField(usersData.get(0), userId);
		FordPassAprHelper.validateMandatoryField(usersData.get(1), userAuditCreatedDate);
		FordPassAprHelper.validateMandatoryField(usersData.get(12), userFirstName);
		FordPassAprHelper.validateMandatoryField(usersData.get(14), userLastName);
		FordPassAprHelper.validateMandatoryField(usersData.get(17), userLogin);
		FordPassAprHelper.validateMandatoryField(usersData.get(23), userStatus);
							
	}
	
}