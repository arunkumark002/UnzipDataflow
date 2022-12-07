package com.ford.datafactory.batch.decoder;

import com.ford.datafactory.batch.bo.DictionaryItemBO;


import com.ford.datafactory.batch.util.*;
import org.codehaus.jackson.map.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
//import com.ford.it.logging.ILogger;
//import com.ford.it.logging.LogFactory;

public class DictionaryItemDataDecoder implements IMultipleRecordDecoder{

	private static final String CLASS_NAME = DictionaryItemDataDecoder.class.getName();
	//private static final ILogger log = LogFactory.getInstance().getLogger(CLASS_NAME);
	private  ArrayList<String> outputList = null;
	//private ObjectMapper objectMapper = new ObjectMapper();
	//private DictionaryItemBO dictionaryItemBO;
	//private String headerTimestamp= null;
	private String countryCode = null;
	//boolean resultStatus = false;

	public static final String headerDate = "HEADER_DATETIME";		
	public static final String dicDomain = "DIC_DOMAIN";
	public static final String dicDescription = "DIC_DESCRIPTION";
	public static final String ditID = "DIT_ID";
	public static final String ditCode = "DIT_CODE";
	public static final String ditName = "DIT_NAME";
	public static final String ditDescription = "DIT_DESCRIPTION";
	//FPA �  enhancement CR# F177282 - US1068018 - Addition of prg_code attribute
	public static final String prg_code = "PRG_CODE";

	static DateFormat stdTimeFormatUTC = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	static DateFormat stdHeaderTimeFormatCT = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	/*public DictionaryItemDataDecoder(String headerTimestamp) {
		//this.headerTimestamp = headerTimestamp.substring(0,headerTimestamp.lastIndexOf('.')).split("_")[1];
	}*/

	public String decode(String headerTimestamp1,String line) throws Exception  {
		final String METHOD_NAME = "decode";
		//log.entering(CLASS_NAME, METHOD_NAME);
		boolean resultStatus = false;
		//String headerTimestamp = headerTimestamp1.substring(0,headerTimestamp1.lastIndexOf('.')).split("_")[1];
		String[] fileNameWithoutExt = headerTimestamp1.substring(0,headerTimestamp1.lastIndexOf('.')).split("_");
		String headerTimestamp = fileNameWithoutExt[fileNameWithoutExt.length - 1];
		//log.info(" comes to decode ***" );
		String output = null;
		if(!FordPassAprHelper.validateHeaderTimestamp(headerTimestamp)) {
			return null;
		}

		try {

			ArrayList<String> dicItemData = CsvUtil.parseCsvRecord(line.toString());

			if (!validateSkipRecords(dicItemData)) {
				DictionaryItemBO dictionaryItemBO = new DictionaryItemBO();
				ObjectMapper objectMapper = new ObjectMapper();

				if (dicItemData.size() <= 7) {

					// Fail processing 
					validateAllMandatoryFields(dicItemData);
					validateMandatoryFields(dicItemData);

					setDictionaryItemMappings(dicItemData,headerTimestamp, dictionaryItemBO);

					setRawPayload(dicItemData, dictionaryItemBO);

					// Finally -- set process states
					resultStatus = FordPassAprHelper.setProcessState();

					if (resultStatus){	

						dictionaryItemBO.setProcess_status_details(FordPassAprHelper.setProcessDetails());	

						dictionaryItemBO
						.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_FAILED);
					} else {		

						dictionaryItemBO.setProcess_status_details(null);		

						dictionaryItemBO
						.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_SUCCESSFUL);
					}

					dictionaryItemBO.setProcess_status_date_time_utc(FordPassAprHelper
							.GetUTCdatetimeAsString());

					// reset 
					FordPassAprHelper.resetProcessState();

					//return this.objectMapper.writeValueAsString(dictionaryItemBO);
					//outputList.clear();
					if(FordPassAprConstants.NA_EU_VALUE==countryCode){
						//log.info("comes to NA_EU country code check***");
						for(String code : FordPassAprConstants.NA_EU_CODES){
							//log.info("setting to country code check***"+code);
							dictionaryItemBO.setCntry_c(code);
							try{
								dictionaryItemBO.setSha_key(HashUtil.calculateSHA256(chunckAsOneString(dicItemData)+code +","+ headerTimestamp));
							}catch (Exception e) {
								e.printStackTrace();
								throw new Exception(
										FordPassAprConstants.DICITEM_DATATYPE
										+ FordPassAprConstants.PAYLOAD_SHAKEY_ERR_DETAILS);
							}

							//outputList.add(this.objectMapper.writeValueAsString(dictionaryItemBO));
							output = objectMapper.writeValueAsString(dictionaryItemBO);
						}
					}
					else {
						dictionaryItemBO.setCntry_c(countryCode);
						//log.info("comes to OTHER country code check***");
						try{
							dictionaryItemBO.setSha_key(HashUtil.calculateSHA256(chunckAsOneString(dicItemData)+countryCode +","+ headerTimestamp));
						}catch (Exception e) {
							e.printStackTrace();
							throw new Exception(
									FordPassAprConstants.DICITEM_DATATYPE
									+ FordPassAprConstants.PAYLOAD_SHAKEY_ERR_DETAILS);
						}
						//outputList.add(this.objectMapper.writeValueAsString(dictionaryItemBO));
						output = objectMapper.writeValueAsString(dictionaryItemBO);
					}	
					return output;
				}	else {
					throw new Exception(
							FordPassAprConstants.DICITEM_DATATYPE + FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);	

				}
			} else {
				return null;
			}

		} catch (Exception e) {
			//log.info("exceprion occured in Decoder***");
			//log.info(e.getMessage());
			/*if (e.getMessage() == null){
				throw new Exception(
						FordPassAprConstants.DICITEM_DATATYPE + FordPassAprConstants.PROCESSING_EXCEPTION);	}	
			else
				throw new Exception(e.getMessage());*/
			return "empty";
		}

	}

	private boolean validateSkipRecords(ArrayList<String> dicItemData)
			throws Exception {
		boolean skipRecord = false;
		if (dicItemData.get(0) != null) {
			if (dicItemData.get(0).equalsIgnoreCase(
					FordPassAprConstants.APR_HEADER)
					|| dicItemData.get(0).equalsIgnoreCase(
							FordPassAprConstants.APR_TAIL)) {
				System.out.println("Skip Record true");
				skipRecord = true;
			} else {
				System.out.println("else:: Skip Record false");
				skipRecord = false;
			}
		} else {
			if (dicItemData.size() == 7) {
				skipRecord = false;

			} else {	System.out.println("else:: throw exception");
			throw new Exception(FordPassAprConstants.DICITEM_DATATYPE
					+ FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);

			}
		}
		return skipRecord;
	}

	private void setRawPayload(ArrayList<String> dicItemData, DictionaryItemBO dictionaryItemBO) {

		StringBuffer sbf = new StringBuffer();		
		sbf.append(dicItemData.get(0));
		for(int i=1; i < dicItemData.size(); i++){
			sbf.append(",").append(dicItemData.get(i));
		}		
		dictionaryItemBO.setRaw_payload(sbf.toString());

	}


	private String chunckAsOneString(ArrayList<String> dicItemData) {

		StringBuffer sbf1 = new StringBuffer();	
		sbf1.append(dicItemData.get(0));
		for(int i=1; i < dicItemData.size(); i++){
			sbf1.append(",").append(dicItemData.get(i));
		}
		return sbf1.toString();

	}


	private void setDictionaryItemMappings(ArrayList<String> dicItemData, String headerTimestamp, DictionaryItemBO dictionaryItemBO) throws Exception {

		final String METHOD_NAME = "setDictionaryItemMappings";

		//log.entering(CLASS_NAME, METHOD_NAME);

		dictionaryItemBO.setHeader_timestamp_utc(parseHeaderDateTimestamp(headerTimestamp , headerDate));

		dictionaryItemBO.setUnique_id(FordPassAprHelper.longParser(dicItemData.get(0), ditID));

		dictionaryItemBO.setDic_description(FordPassAprHelper.stringParser(dicItemData.get(1), dicDescription));

		dictionaryItemBO.setDic_domain(FordPassAprHelper.stringParser(dicItemData.get(2), dicDomain));

		dictionaryItemBO.setDit_code(FordPassAprHelper.stringParser(dicItemData.get(3), ditCode));

		dictionaryItemBO.setDit_description(FordPassAprHelper.stringParser(dicItemData.get(4), ditDescription));

		dictionaryItemBO.setDit_name(FordPassAprHelper.stringParser(dicItemData.get(5), ditName));
		
		//FPA �  enhancement CR# F177282 - US1068018 - Addition of prg_code attribute
		dictionaryItemBO.setPrg_code(FordPassAprHelper.stringParser(dicItemData.get(6), prg_code));
	}


	private void validateAllMandatoryFields(ArrayList<String> dicItemData) throws Exception{
		final String METHOD_NAME = "validateAllMandatoryFields";
		//log.entering(CLASS_NAME, METHOD_NAME);

		if ( dicItemData.get(0) == null && dicItemData.get(2) == null
				&& dicItemData.get(3) == null)
		{
			throw new Exception(
					FordPassAprConstants.DICITEM_DATATYPE
					+ FordPassAprConstants.REQUIRED_FIELDS);
		}
	}

	//Sets record to transfail if any of the required field is empty	
	private void validateMandatoryFields(ArrayList<String> dicItemData){
		final String METHOD_NAME = "validateMandatoryFields";
		//log.entering(CLASS_NAME, METHOD_NAME);
		FordPassAprHelper.validateMandatoryField(dicItemData.get(0), ditID);
		FordPassAprHelper.validateMandatoryField(dicItemData.get(2), dicDomain);
		FordPassAprHelper.validateMandatoryField(dicItemData.get(3), ditCode);

	}

	public static String parseHeaderDateTimestamp(String fieldvalue,
												  String fieldname) { // throws Exception {

		String tempResult = null;
		String result = null;
		Date date = null;
		if (fieldvalue == null)
			return null;

		stdTimeFormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));

		stdHeaderTimeFormatCT.setTimeZone(TimeZone.getTimeZone("CST"));

		try {
			date = stdHeaderTimeFormatCT.parse(fieldvalue);
			tempResult = stdHeaderTimeFormatCT.format(date);
			if (fieldvalue.equals(tempResult)) {
				result = stdTimeFormatUTC.format(date);
			} else
				throw new Exception();
		} catch (final Exception e1) {
			/*
			 * e1.printStackTrace(); throw new Exception( fieldname +
			 * FordPassAprConstants.EXCEPTION_HEADER_TS_FIELD);
			 */
			FordPassAprHelper.processLogEvent(fieldvalue+" "+date+" "+tempResult+" "+result,
					e1.getMessage());
		}
		return result;
	}


}
