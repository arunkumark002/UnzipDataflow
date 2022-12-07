package com.ford.datafactory.batch.decoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.ford.datafactory.batch.bo.TransactionRecognitionTiersBO;
import com.ford.datafactory.batch.util.*;
import org.codehaus.jackson.map.ObjectMapper;

public class TransactionRecognitionTiersDecoder implements IMultipleRecordDecoder {

	private static final String CLASS_NAME = TransactionRecognitionTiersDecoder.class.getName();
	private static final Logger log = Logger.getLogger(CLASS_NAME);
	
	//private String headerTimestamp = null;
	private ObjectMapper objectMapper = new ObjectMapper();
	private TransactionRecognitionTiersBO transcRecogTiersBO;
	boolean resultStatus = false;

	private static String COLUMN_HEADER_TS = "Timestamp_Header_CT";
	private static final String TRC_ID    = "TRC_ID";
	private static final String TRN_ID    = "TRN_ID";
	public static final String TRL_CODE   = "TRL_CODE";
	public static final String RGS_CODE   = "RGS_CODE";
	public static final String RGT_CODE   = "RGT_CODE";
	public static final String TER_CONDITION_FORMULA = "TER_CONDITION_FORMULA";
	public static final String TER_PROCESS_LEVEL = "TER_PROCESS_LEVEL";
	
	private static String CTR_CODE  = "CTR_CODE";
	private static String PARTITION_CNTRY_C = "PARTITION_CNTRY_C";
	private static String PARTITION_YR  = "PARTITION_YR";
	private final List<String> TRANS_REG_LEVELS = Arrays.asList("A", "I", "C", "T");
	

	/*public TransactionRecognitionTiersDecoder(String headerTimestamp) {
		this.headerTimestamp = headerTimestamp;
	}*/

	@Override
	public String decode(String headerTimestamp,String line) throws Exception {

		final String METHOD_NAME = "decode";
		log.entering(CLASS_NAME, METHOD_NAME);
		log.info(" comes to decode ***");

		transcRecogTiersBO = new TransactionRecognitionTiersBO();

		try {

			ArrayList<String> parsedValueLst = CsvUtil.parseCsvRecord(line.toString());
			// Process if not Header or Tail
			if (!validateSkipRecords(parsedValueLst)) {
				// validate input records columns count
				if (parsedValueLst.size() == 9) {
					// Fail processing
					validateAllMandatoryFields(parsedValueLst);
					validateMandatoryFields(parsedValueLst);
					validateTransLevel(parsedValueLst);

					try {
						transcRecogTiersBO.setSha_key(HashUtil.calculateSHA256(FordPassAprHelper.chunkAsOneString(parsedValueLst) +","+ headerTimestamp));
					} catch (Exception e) {
						e.printStackTrace();
						throw new Exception(FordPassAprConstants.TRANSACTION_RECOG_TIERS_DATATYPE + FordPassAprConstants.PAYLOAD_SHAKEY_ERR_DETAILS);
					}
					// set the BO
					setTransRecogTiersFields(parsedValueLst,headerTimestamp);
					// set the Rawpayload
					setRawPayload(parsedValueLst);
					// Finally -- set process states
					resultStatus = FordPassAprHelper.setProcessState();

					if (resultStatus) {
						// set the process details
						transcRecogTiersBO.setProcess_status_details(FordPassAprHelper.setProcessDetails());
						transcRecogTiersBO.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_FAILED);
					} else {
						transcRecogTiersBO.setProcess_status_details(null);
						transcRecogTiersBO.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_SUCCESSFUL);
					}
					transcRecogTiersBO.setProcess_status_date_time_utc(FordPassAprHelper.GetUTCdatetimeAsString());

					// reset the Process State
					FordPassAprHelper.resetProcessState();

					return this.objectMapper.writeValueAsString(transcRecogTiersBO);
				}
				else {
					throw new Exception(FordPassAprConstants.TRANSACTION_RECOG_TIERS_DATATYPE + FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);
				}

			} else {
				return null;
			}
		} catch (Exception e) {
			if (e.getMessage() == null)
				throw new Exception(FordPassAprConstants.TRANSACTION_RECOG_TIERS_DATATYPE + FordPassAprConstants.PROCESSING_EXCEPTION);
			else
				throw new Exception(e.getMessage());
		}

	}

	/**
	 * Setting the Raw Payload
	 * 
	 */
	private void setRawPayload(ArrayList<String> parsedValueLst) {

		StringBuffer sbf = new StringBuffer();
		sbf.append(parsedValueLst.get(0));
		for (int i = 1; i < parsedValueLst.size(); i++) {
			sbf.append(",").append(parsedValueLst.get(i));
		}
		transcRecogTiersBO.setRaw_payload(sbf.toString());
	}

	/**
	 * Setting the TransactionRecognizationBO
	 * 
	 */
	private void setTransRecogTiersFields(ArrayList<String> parsedValueLst,String headerTimestamp)
			throws Exception {

		final String METHOD_NAME = "setTransRecogTiersFields";
		log.entering(CLASS_NAME, METHOD_NAME);

		transcRecogTiersBO.setTrc_id(FordPassAprHelper.longParser(parsedValueLst.get(0), TRC_ID));
		transcRecogTiersBO.setTrn_id(FordPassAprHelper.longParser(parsedValueLst.get(1), TRN_ID));
		transcRecogTiersBO.setTrl_code(FordPassAprHelper.stringParser(parsedValueLst.get(2), TRL_CODE));
		transcRecogTiersBO.setRgs_code(FordPassAprHelper.stringParser(parsedValueLst.get(3), RGS_CODE));
		transcRecogTiersBO.setRgt_code(FordPassAprHelper.stringParser(parsedValueLst.get(4), RGT_CODE));
		
		transcRecogTiersBO.setTer_condition_formula(FordPassAprHelper.stringParser(parsedValueLst.get(5), TER_CONDITION_FORMULA));
		transcRecogTiersBO.setTer_process_level(FordPassAprHelper.stringParser(parsedValueLst.get(6), TER_PROCESS_LEVEL));
		transcRecogTiersBO.setCtr_code(FordPassAprHelper.stringParser(parsedValueLst.get(7), CTR_CODE));
		
		transcRecogTiersBO.setPartition_cntry_c(FordPassAprHelper.stringParser(parsedValueLst.get(8), PARTITION_CNTRY_C));
		transcRecogTiersBO.setPartition_year(FordPassAprHelper.intParser(FordPassAprHelper.internalAuditYear(), PARTITION_YR));
		
		transcRecogTiersBO.setHeader_timestamp_utc(FordPassAprHelper.parseHeaderDateTimestamp(headerTimestamp,COLUMN_HEADER_TS));
	}

	/**
	 * Validating the Mandatory fields
	 * 
	 */
	private void validateAllMandatoryFields(ArrayList<String> parsedValueLst)
			throws Exception {
		final String METHOD_NAME = "validateAllMandatoryFields";
		log.entering(CLASS_NAME, METHOD_NAME);

		if (parsedValueLst.get(0) == null && parsedValueLst.get(1) == null
				&& parsedValueLst.get(2) == null
				&& parsedValueLst.get(6) == null
				&& parsedValueLst.get(7) == null
				&& parsedValueLst.get(8) == null) {
			throw new Exception(FordPassAprConstants.TRANSACTION_RECOG_TIERS_DATATYPE + FordPassAprConstants.REQUIRED_FIELDS);
		}
	}

	/**
	 * Validating the Mandatory fields Sets record to transfail if any of the
	 * required field is empty
	 * 
	 */
	private void validateMandatoryFields(ArrayList<String> parsedValueLst) {
		final String METHOD_NAME = "validateMandatoryFields";
		log.entering(CLASS_NAME, METHOD_NAME);

		FordPassAprHelper.validateMandatoryField(parsedValueLst.get(0), TRC_ID);
		FordPassAprHelper.validateMandatoryField(parsedValueLst.get(1), TRN_ID);
		FordPassAprHelper.validateMandatoryField(parsedValueLst.get(2), TRL_CODE);
		FordPassAprHelper.validateMandatoryField(parsedValueLst.get(6), TER_PROCESS_LEVEL);
		
		FordPassAprHelper.validateMandatoryField(parsedValueLst.get(7), CTR_CODE);
		FordPassAprHelper.validateMandatoryField(parsedValueLst.get(8), PARTITION_CNTRY_C);
	}

	private boolean validateSkipRecords(ArrayList<String> parsedValueLst)
			throws Exception {
		boolean skipRecord = false;
		if (parsedValueLst.get(0) != null) {
			if (parsedValueLst.get(0).equalsIgnoreCase(FordPassAprConstants.APR_HEADER)
					|| parsedValueLst.get(0).equalsIgnoreCase(FordPassAprConstants.APR_TAIL)) {
				skipRecord = true;
			} else {
				skipRecord = false;
			}
		} else {
			if (parsedValueLst.size() == 9) {
				skipRecord = false;

			} else {
				throw new Exception(FordPassAprConstants.TRANSACTION_RECOG_TIERS_DATATYPE + FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);
			}
		}
		return skipRecord;
	}

	/**
	 * Validating the TER_PROCESS_LEVEL fields with the default levels with
	 * incoming values
	 * 
	 * @param parsedValueLst
	 * @throws Exception
	 */
	private void validateTransLevel(ArrayList<String> parsedValueLst)
			throws Exception {
		final String METHOD_NAME = "validateTransLevel";
		log.entering(CLASS_NAME, METHOD_NAME);

		if (!TRANS_REG_LEVELS.contains(parsedValueLst.get(6))) {
			throw new Exception(FordPassAprConstants.TRANSACTION_RECOG_TIERS_DATATYPE + FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);
		}
	}

}
