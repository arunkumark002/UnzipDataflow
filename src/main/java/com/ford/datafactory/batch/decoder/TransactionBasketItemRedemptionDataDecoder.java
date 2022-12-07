package com.ford.datafactory.batch.decoder;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.ford.datafactory.batch.bo.TransactionBasketItemRedemptionBO;
import com.ford.datafactory.batch.util.*;
import org.codehaus.jackson.map.ObjectMapper;


public class TransactionBasketItemRedemptionDataDecoder implements IMultipleRecordDecoder {

	private static final String CLASS_NAME = TransactionBasketItemRedemptionDataDecoder.class
			.getName();
	private static final Logger log = Logger.getLogger(CLASS_NAME);

	ObjectMapper objectMapper = new ObjectMapper();
	TransactionBasketItemRedemptionBO basketItemBO;
	String processStatusDetails = "";
	//String headerTimestamp = null;
	boolean fieldResultStatus = false;
	private static final String partitionYear = "partition_year";
	private static final String headerTimeStamp = "Timestamp_Header_CT";
	private static final String uniqueId = "TPD_ID";
	private static final String pointsType = "PTP_CODE";
	private static final String productCodeFromPOS = "TPD_PCD_CODE";
	private static final String productCodeId = "TPD_PCD_ID";
	private static final String numberOfPoints = "TPD_POINTS";
	private static final String associatedProduct = "TPD_PRD_ID";
	private static final String quantityOfProduct = "TPD_QUANTITY";
	private static final String associatedTransaction = "TPD_TRN_ID";
	private static final String value = "TPD_VALUE";
	private static final String transactionCountryCode = "CTR_CODE_Transaction";
	private static final String homeCountryCode = "CTR_CODE_Home";

	/*public TransactionBasketItemRedemptionDataDecoder(String headertimestamp) {
		
		this.headerTimestamp = headertimestamp;
	}*/

	@Override
	public String decode(String headerTimestamp,String line) throws Exception {
		

		final String METHOD_NAME = "decode";
		log.entering(CLASS_NAME, METHOD_NAME);
		
		log.info(" comes to decode ***");
		processStatusDetails = "";

		basketItemBO = new TransactionBasketItemRedemptionBO();
		try {

			ArrayList<String> basketItemData = CsvUtil.parseCsvRecord(line
					.toString());

			// Process if not Header or Tail
			if (!validateSkipRecords(basketItemData)) {
				// validate input records columns count
				if (basketItemData.size() == 11) {
					// Fail processing
					validateAllMandatoryFields(basketItemData);
					validateMandatoryFields(basketItemData);
					
					// set the Rawpayload
					setRawPayload(basketItemData);
					// SHA key generation
					setShaKey(basketItemData,headerTimestamp);
					// set the BO
					setbasketItemBO(basketItemData,headerTimestamp);
					
					// Finally -- set process states
					fieldResultStatus = FordPassAprHelper.setProcessState();

					if (fieldResultStatus) {
						// set the process details
						basketItemBO
								.setProcess_status_details(FordPassAprHelper
										.setProcessDetails());
						// set the status to FAILED
						basketItemBO
								.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_FAILED);
					} else {
						basketItemBO.setProcess_status_details(null);
						// set the status to SUCCESS
						basketItemBO
								.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_SUCCESSFUL);
					}

					basketItemBO
							.setProcess_status_date_time_utc(FordPassAprHelper
									.GetUTCdatetimeAsString());
					// reset the Process State
					FordPassAprHelper.resetProcessState();
					return this.objectMapper.writeValueAsString(basketItemBO);
				} else {
					throw new Exception(
							FordPassAprConstants.TRANS_BASKET_ITEM_REDEMPTION_DATATYPE
									+ FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);
				}

			} else {
				return null;
			}
		} catch (Exception e) {
			log.severe(e.getMessage());
			/*if (e.getMessage() == null)
				throw new Exception(
						FordPassAprConstants.TRANS_BASKET_ITEM_REDEMPTION_DATATYPE
								+ FordPassAprConstants.PROCESSING_EXCEPTION);
			else
				throw new Exception(e.getMessage());*/
			return null;
		}
	}

	private void validateAllMandatoryFields(ArrayList<String> basketItemData) throws Exception {
		final String METHOD_NAME = "validateAllMandatoryFields";
		log.entering(CLASS_NAME, METHOD_NAME);
		if (basketItemData.get(0) == null && basketItemData.get(1) == null
				&& basketItemData.get(3) == null
				&& basketItemData.get(6) == null
				&& basketItemData.get(7) == null
				&& basketItemData.get(9) == null
				&& basketItemData.get(10) == null) {
			throw new Exception(
					FordPassAprConstants.TRANS_BASKET_ITEM_REDEMPTION_DATATYPE
							+ FordPassAprConstants.REQUIRED_FIELDS);
		}
	}
	
	//Sets record to transfail if any of the required field is empty	
	private void validateMandatoryFields(ArrayList<String> basketItemData){
		final String METHOD_NAME = "validateMandatoryFields";
		log.entering(CLASS_NAME, METHOD_NAME);		
		
		FordPassAprHelper.validateMandatoryField(basketItemData.get(0), uniqueId);
		FordPassAprHelper.validateMandatoryField(basketItemData.get(1), pointsType);
		FordPassAprHelper.validateMandatoryField(basketItemData.get(3), productCodeId);
		FordPassAprHelper.validateMandatoryField(basketItemData.get(6), quantityOfProduct);
		FordPassAprHelper.validateMandatoryField(basketItemData.get(7), associatedTransaction);
		FordPassAprHelper.validateMandatoryField(basketItemData.get(9), transactionCountryCode);
		FordPassAprHelper.validateMandatoryField(basketItemData.get(10), homeCountryCode);
					
	}	

	private void setRawPayload(ArrayList<String> basketItemData) {
		
		StringBuffer sbf = new StringBuffer();
		sbf.append(basketItemData.get(0));
		for (int i = 1; i < basketItemData.size(); i++) {
			sbf.append(",").append(basketItemData.get(i));
		}
		basketItemBO.setRaw_payload(sbf.toString());

	}

	private void setbasketItemBO(ArrayList<String> basketItemData, String headerTimestamp) {
		
		basketItemBO.setPartition_year(FordPassAprHelper.intParser(
				FordPassAprHelper.transformISOPartitionDate(
						headerTimestamp, headerTimestamp), partitionYear));
		basketItemBO
				.setHeader_timestamp_utc(FordPassAprHelper
						.parseHeaderDateTimestamp(headerTimestamp,
								headerTimeStamp));
		basketItemBO.setTpd_id(FordPassAprHelper.longParser(
				basketItemData.get(0), uniqueId));
		basketItemBO.setPtp_code(FordPassAprHelper.stringParser(
				basketItemData.get(1), pointsType));
		basketItemBO.setTpd_pcd_code(FordPassAprHelper.stringParser(
				basketItemData.get(2), productCodeFromPOS));
		basketItemBO.setTpd_pcd_id(FordPassAprHelper.longParser(
				basketItemData.get(3), productCodeId));
		basketItemBO.setTpd_points(FordPassAprHelper.longParser(
				basketItemData.get(4), numberOfPoints));
		basketItemBO.setTpd_prd_id(FordPassAprHelper.longParser(
				basketItemData.get(5), associatedProduct));
		basketItemBO.setTpd_quantity(FordPassAprHelper.doubleParser(
				basketItemData.get(6), quantityOfProduct));
		basketItemBO.setTpd_trn_id(FordPassAprHelper.longParser(
				basketItemData.get(7), associatedTransaction));
		basketItemBO.setTpd_value(FordPassAprHelper.doubleParser(
				basketItemData.get(8), value));
		basketItemBO.setCtr_code_transaction(FordPassAprHelper.stringParser(
				basketItemData.get(9), transactionCountryCode));
		basketItemBO.setCtr_code_home(FordPassAprHelper.stringParser(
				basketItemData.get(10), homeCountryCode));
	}

	private boolean validateSkipRecords(ArrayList<String> basketItemData)
			throws Exception {
		boolean skipRecord = false;
		if (basketItemData.get(0) != null) {
			if (basketItemData.get(0).equalsIgnoreCase(
					FordPassAprConstants.APR_HEADER)
					|| basketItemData.get(0).equalsIgnoreCase(
							FordPassAprConstants.APR_TAIL)) {
				skipRecord = true;
			} else {
				skipRecord = false;
			}
		} else {
			if (basketItemData.size() == 11) {
				skipRecord = false;

			} else {
				throw new Exception(
						FordPassAprConstants.TRANS_BASKET_ITEM_REDEMPTION_DATATYPE
								+ FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);

			}
		}
		return skipRecord;
	}

	private void setShaKey(ArrayList<String> basketItemData, String headerTimestamp) throws Exception {

		if (this.basketItemBO.getRaw_payload() == null) {
			throw new Exception(
					FordPassAprConstants.TRANS_BASKET_ITEM_REDEMPTION_DATATYPE
							+ FordPassAprConstants.SHA_KEY_GENERATION_DETAILS);
		} else {
			try {
				this.basketItemBO.setSha_key(HashUtil
						.calculateSHA256(this.basketItemBO.getRaw_payload() +","+ headerTimestamp));
			} catch (Exception e) {
				throw new Exception(
						FordPassAprConstants.TRANS_BASKET_ITEM_REDEMPTION_DATATYPE
								+ FordPassAprConstants.PAYLOAD_SHAKEY_ERR_DETAILS);
			}
		}
	}

}
