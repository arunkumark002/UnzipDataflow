package com.ford.datafactory.batch.decoder;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.ford.datafactory.batch.bo.TransactionErrorsBO;
import com.ford.datafactory.batch.util.*;
import org.codehaus.jackson.map.ObjectMapper;

public class TransactionErrorsDataDecoder implements IMultipleRecordDecoder {

	private static final String CLASS_NAME = TransactionErrorsDataDecoder.class.getName();
	private static final Logger log = Logger.getLogger(CLASS_NAME);
	private ObjectMapper objectMapper = new ObjectMapper();
	private TransactionErrorsBO transactionErrorsBO;
	//private String headerTimestamp = null;
	private String resultStatus = "";

	public static final String headerDate = "HEADER_DATETIME";
	public static final String TER_ID = "ter_id";
	public static final String TRN_ID = "trn_id";
	public static final String TRL_ID = "trl_id";
	public static final String TRE_ERROR_CODE = "tre_error_code";
	public static final String TRE_CONDITION_FORMULA = "tre_condition_formula";
	public static final String TRE_PROCESS_LEVEL = "tre_process_level";
	public static final String TRE_TRN_STATUS = "tre_trn_status";
	public static final String TRE_BLOCK_ACCOUNT = "tre_block_account";
	public static final String TRE_BLOCK_CUSTOMER = "tre_block_customer";
	public static final String TRE_BLOCK_USER = "tre_block_user";
	public static final String TER_MESSAGE = "ter_message";

	/*public TransactionErrorsDataDecoder(String headertimestamp, String countryCode) {
		this.headerTimestamp = headertimestamp;
	}*/

	public String decode(String headerTimestamp,String input) throws Exception {
		final String METHOD_NAME = "decode";
		log.entering(CLASS_NAME, METHOD_NAME);

		log.info(" comes to decode ***");
		transactionErrorsBO = new TransactionErrorsBO();

		ArrayList<String> transactionErrorsData = CsvUtil.parseCsvRecord(input);
		if (!validateSkipRecords(transactionErrorsData)) {

			if (transactionErrorsData.size() == 11) {
				validateAllMandatoryFields(transactionErrorsData);
				validateMandatoryFields(transactionErrorsData);
				setTransactionErrorsMapping(transactionErrorsData,headerTimestamp);
				setRawPayload(transactionErrorsData);
				

				// Finally -- set process states
				resultStatus = FordPassAprHelper.setProcessDetails();
				if (resultStatus == null || resultStatus.equals("")) {
					transactionErrorsBO.setProcess_status_details(null);
					transactionErrorsBO.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_SUCCESSFUL);
					transactionErrorsBO.setSha_key(HashUtil.calculateSHA256(input +","+ headerTimestamp));
				} else {
					transactionErrorsBO.setProcess_status_details(resultStatus);
					throw new Exception(transactionErrorsBO.getProcess_status_details());
				}

				transactionErrorsBO.setProcess_status_date_time_utc(FordPassAprHelper.GetUTCdatetimeAsString());
				return this.objectMapper.writeValueAsString(transactionErrorsBO);
			} else {
				log.severe(FordPassAprConstants.TRANSACTION_ERRORS_DATATYPE
						+ FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);
				/*throw new Exception(FordPassAprConstants.TRANSACTION_ERRORS_DATATYPE
						+ FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);*/
				return null;

			}
		} else {
			return null;
		}
	}

	private boolean validateSkipRecords(ArrayList<String> transactionErrorsData) throws Exception {
		boolean skipRecord = false;
		if (transactionErrorsData.get(0) != null) {
			if (transactionErrorsData.get(0).equalsIgnoreCase(FordPassAprConstants.APR_HEADER)
					|| transactionErrorsData.get(0).equalsIgnoreCase(FordPassAprConstants.APR_TAIL)) {
				System.out.println("Skip Record true");
				skipRecord = true;
			} else {
				System.out.println("else:: Skip Record false");
				skipRecord = false;
			}
		} else {
			if (transactionErrorsData.size() == 11) {
				skipRecord = false;

			} else {
				System.out.println("else:: throw exception");
				throw new Exception(FordPassAprConstants.TRANSACTION_ERRORS_DATATYPE
						+ FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);

			}
		}
		return skipRecord;
	}

	private void setRawPayload(ArrayList<String> data) {

		StringBuffer sbf = new StringBuffer();
		sbf.append(data.get(0));
		for (int i = 1; i < data.size(); i++) {
			sbf.append(",").append(data.get(i));
		}
		transactionErrorsBO.setRaw_payload(sbf.toString());

	}

	private void setTransactionErrorsMapping(ArrayList<String> data,String headerTimestamp) throws Exception {

		final String METHOD_NAME = "setTransactionErrorsMapping";

		log.entering(CLASS_NAME, METHOD_NAME);

		transactionErrorsBO
				.setHeader_timestamp_utc(FordPassAprHelper.parseHeaderDateTimestamp(headerTimestamp, headerDate));

		transactionErrorsBO.setTer_id(FordPassAprHelper.longParser(data.get(0), TER_ID));
		transactionErrorsBO.setTrl_id(FordPassAprHelper.longParser(data.get(1), TRN_ID));
		transactionErrorsBO.setTrn_id(FordPassAprHelper.longParser(data.get(2), TRL_ID));
		transactionErrorsBO.setTre_error_code(FordPassAprHelper.stringParser(data.get(3), TRE_ERROR_CODE));
		transactionErrorsBO
				.setTre_condition_formula(FordPassAprHelper.stringParser(data.get(4), TRE_CONDITION_FORMULA));
		transactionErrorsBO.setTre_process_level(FordPassAprHelper.stringParser(data.get(5), TRE_PROCESS_LEVEL));
		transactionErrorsBO.setTre_trn_status(FordPassAprHelper.stringParser(data.get(6), TRE_TRN_STATUS));
		transactionErrorsBO.setTre_block_account(FordPassAprHelper.stringParser(data.get(7), TRE_BLOCK_ACCOUNT));
		transactionErrorsBO.setTre_block_customer(FordPassAprHelper.stringParser(data.get(8), TRE_BLOCK_CUSTOMER));
		transactionErrorsBO.setTre_block_user(FordPassAprHelper.stringParser(data.get(9), TRE_BLOCK_USER));
		transactionErrorsBO.setTer_message(FordPassAprHelper.stringParser(data.get(10), TER_MESSAGE));
		
	}

	private void validateAllMandatoryFields(ArrayList<String> data) throws Exception {
		final String METHOD_NAME = "validateAllMandatoryFields";
		log.entering(CLASS_NAME, METHOD_NAME);

		if (data.get(0) == null && data.get(1) == null && data.get(2) == null && data.get(5) == null && data.get(6) == null && data.get(7) == null
				&& data.get(8) == null && data.get(9) == null) {
			throw new Exception(
					FordPassAprConstants.TRANSACTION_ERRORS_DATATYPE + FordPassAprConstants.REQUIRED_FIELDS);
		}
	}

	// Sets record to transfail if any of the required field is empty
	private void validateMandatoryFields(ArrayList<String> data) {
		final String METHOD_NAME = "validateMandatoryFields";
		log.entering(CLASS_NAME, METHOD_NAME);
		FordPassAprHelper.validateMandatoryField(data.get(0), TER_ID);
		FordPassAprHelper.validateMandatoryField(data.get(1), TRN_ID);
		FordPassAprHelper.validateMandatoryField(data.get(2), TRL_ID);
		FordPassAprHelper.validateMandatoryField(data.get(5), TRE_PROCESS_LEVEL);
		FordPassAprHelper.validateMandatoryField(data.get(6), TRE_TRN_STATUS);
		FordPassAprHelper.validateMandatoryField(data.get(7), TRE_BLOCK_ACCOUNT);
		FordPassAprHelper.validateMandatoryField(data.get(8), TRE_BLOCK_CUSTOMER);
		FordPassAprHelper.validateMandatoryField(data.get(9), TRE_BLOCK_USER);
	}
	
	/*
	 * public static void main(String[] args) throws Exception { String s =
	 * "468143,21331520,34151,0,,T,T,0,0,0,Fraud"; TransactionErrorsDataDecoder
	 * decoder = new TransactionErrorsDataDecoder("20200224072510", "NA_EU"); String
	 * res = decoder.decode(s); System.out.println(res);
	 * 
	 * }
	 */
	 
}
