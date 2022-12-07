package com.ford.datafactory.batch.decoder;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.ford.datafactory.batch.bo.TransactionCouponRedemptionBO;
import com.ford.datafactory.batch.util.*;
import org.codehaus.jackson.map.ObjectMapper;

public class TransactionCouponRedemptionDataDecoder implements IMultipleRecordDecoder {
	
	private static final String CLASS_NAME = TransactionCouponRedemptionDataDecoder.class.getName();
	private static final Logger log = Logger.getLogger(CLASS_NAME);
	
	ObjectMapper objectMapper = new ObjectMapper();
	TransactionCouponRedemptionBO transactionCouponRedemptionBO;
	String processStatusDetails = "";
	String headerTimestamp= null;
	boolean resultStatus = false; 

	public static final String headerDate = "Timestamp_Header_CT";
	public static final String tcpId = "TCP_ID";
	public static final String tcpCpnBarcode = "TCP_COUPON_BARCODE";
	public static final String tcpCpnId = "TCP_COUPON_ID";
	public static final String tcpCtpCode = "TCP_COUPON_TYPE_CODE";
	public static final String tcpTrnId = "TCP_TRANSACTION_ID";
	public static final String tcpUseResult = "TCP_USE_RESULT";
	public static final String tcpxCusId = "TCPX_CUSTOMER_ID";
	public static final String tcpxInvoiceNo = "TCPX_INVOICE_NUMBER";
	public static final String ctryCodeTransaction = "COUNTRY_CODE_TRANSACTION";
	public static final String ctryCodeHome = "COUNTRY_CODE_HOME";

	
	/*public TransactionCouponRedemptionDataDecoder(String headertimestamp) {
		
		this.headerTimestamp = headertimestamp;
	}*/

	@Override
	public String decode(String headerTimestamp,String line) throws Exception {
		
		final String METHOD_NAME = "decode";
		log.entering(CLASS_NAME, METHOD_NAME);
	
		log.info(" comes to decode ***" );
		processStatusDetails = "";		
		transactionCouponRedemptionBO = new TransactionCouponRedemptionBO();
		
		try {
			
			ArrayList<String> transactionCouponRedemptionData = CsvUtil.parseCsvRecord(line.toString());


			

			
			
		
			// Process if not Header or Tail
			if (!validateSkipRecords(transactionCouponRedemptionData)) 
			{
				
				// Check if there are 10 fields before breakdown
				if (transactionCouponRedemptionData.size() == 10) {

					
					//validate mandatory fields
					validateAllMandatoryFields(transactionCouponRedemptionData);
					validateMandatoryFields(transactionCouponRedemptionData);
					
					// Set all transaction fields 
					setTransactionMappings(transactionCouponRedemptionData);					
	
					
					// Set partition field
					setPartitionMappings(transactionCouponRedemptionData,this.headerTimestamp);
				
					// set raw payload.                    
                    setRawPayload(transactionCouponRedemptionData);
                    
                 					
					// SHA key generation
					try {
						
						//transactionCouponRedemptionBO.setSha_key(HashUtil.calculateSHA256(transactionCouponRedemptionData.get(0)+  this.headerTimestamp));
						transactionCouponRedemptionBO.setSha_key(HashUtil.calculateSHA256(chunckAsOneString(transactionCouponRedemptionData) +","+ headerTimestamp));

					} catch (Exception e) {
						
						e.printStackTrace();
						throw new Exception(
								FordPassAprConstants.TRANSACTION_COUPON_REDEMPTION_DATATYPE
										+ FordPassAprConstants.PAYLOAD_SHAKEY_ERR_DETAILS);
					}
					
					// Finally -- set process states
					resultStatus = FordPassAprHelper.setProcessState();
					
					if (resultStatus){
						transactionCouponRedemptionBO.setProcess_status_details(FordPassAprHelper.setProcessDetails());
						transactionCouponRedemptionBO
								.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_FAILED);
					} else{
						transactionCouponRedemptionBO.setProcess_status_details(null);
						transactionCouponRedemptionBO
								.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_SUCCESSFUL);
					}

				   transactionCouponRedemptionBO.setProcess_status_date_time_utc(FordPassAprHelper
							.GetUTCdatetimeAsString());
					
					// reset process state
					FordPassAprHelper.resetProcessState();
					
					
					return this.objectMapper.writeValueAsString(transactionCouponRedemptionBO);
					    
				}
			else{

				throw new Exception(FordPassAprConstants.TRANSACTION_COUPON_REDEMPTION_DATATYPE + FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);
			}
		}
		else{
			return null;
		}
		
	}
		catch (Exception e) {
			log.severe(FordPassAprConstants.TRANSACTION_COUPON_REDEMPTION_DATATYPE + FordPassAprConstants.PROCESSING_EXCEPTION);
			/*if (e.getMessage() == null)
			throw new Exception(
					FordPassAprConstants.TRANSACTION_COUPON_REDEMPTION_DATATYPE + FordPassAprConstants.PROCESSING_EXCEPTION);		
			else
				throw new Exception(e.getMessage());*/
			return null;
		}



	}
	

	
	private void setTransactionMappings(ArrayList<String> transactionCouponRedemptionData) {
		final String METHOD_NAME = "setTransactionMappings";
		log.entering(CLASS_NAME, METHOD_NAME);
		
		
		
		transactionCouponRedemptionBO.setHeader_timestamp_utc(FordPassAprHelper.parseHeaderDateTimestamp(this.headerTimestamp , headerDate));
		
		transactionCouponRedemptionBO.setTcp_id(FordPassAprHelper.longParser(transactionCouponRedemptionData.get(0), tcpId));
		
		transactionCouponRedemptionBO.setTcp_cpn_barcode(FordPassAprHelper.stringParser(transactionCouponRedemptionData.get(1), tcpCpnBarcode));
		
		transactionCouponRedemptionBO.setTcp_cpn_id(FordPassAprHelper.longParser(transactionCouponRedemptionData.get(2), tcpCpnId));
		
		transactionCouponRedemptionBO.setTcp_ctp_code(FordPassAprHelper.stringParser(transactionCouponRedemptionData.get(3), tcpCtpCode));
		
		transactionCouponRedemptionBO.setTcp_trn_id(FordPassAprHelper.longParser(transactionCouponRedemptionData.get(4), tcpTrnId));
		
		transactionCouponRedemptionBO.setTcp_use_result(FordPassAprHelper.stringParser(transactionCouponRedemptionData.get(5), tcpUseResult));
		
		transactionCouponRedemptionBO.setTcpx_cus_id(FordPassAprHelper.longParser(transactionCouponRedemptionData.get(6), tcpxCusId));
				
		transactionCouponRedemptionBO.setTcpx_invoice_no(FordPassAprHelper.stringParser(transactionCouponRedemptionData.get(7), tcpxInvoiceNo));
		
		transactionCouponRedemptionBO.setCtr_code_transaction(FordPassAprHelper.stringParser(transactionCouponRedemptionData.get(8), ctryCodeTransaction ));
		
		//transactionCouponRedemptionBO.setCtr_code_home(FordPassAprHelper.stringParser(transactionCouponRedemptionData.get(9), ctryCodeHome));
		

		
	}
	
	// create partition columns
	private void setPartitionMappings(ArrayList<String> transactionCouponRedemptionData , String headerTimestamp) {
		final String METHOD_NAME = "setPartitionMappings";
		log.entering(CLASS_NAME, METHOD_NAME);
		
		transactionCouponRedemptionBO.setCtr_code_home(FordPassAprHelper.stringParser(transactionCouponRedemptionData.get(9), ctryCodeHome));
		// Add year field from header timestamp.
		transactionCouponRedemptionBO.setPartition_year(FordPassAprHelper.intParser(FordPassAprHelper.transformISOPartitionDate(this.headerTimestamp , headerDate), headerDate));
	}
	
	

	private String chunckAsOneString(ArrayList<String> transactionCouponRedemptionData) {
		
		StringBuffer sbf1 = new StringBuffer();	
		sbf1.append(transactionCouponRedemptionData.get(0));
		 for(int i=1; i < transactionCouponRedemptionData.size(); i++){
            sbf1.append(",").append(transactionCouponRedemptionData.get(i));
    }
		return sbf1.toString();
		
	}

	private void setRawPayload(ArrayList<String> transactionCouponRedemptionData) {
		
		StringBuffer sbf = new StringBuffer();
		sbf.append(transactionCouponRedemptionData.get(0));
		for (int i = 1; i < transactionCouponRedemptionData.size(); i++) {
			sbf.append(",").append(transactionCouponRedemptionData.get(i));
		}
		transactionCouponRedemptionBO.setRaw_payload(sbf.toString());

	}

	
	private boolean validateSkipRecords(ArrayList<String> transactionCouponRedemptionData)
			throws Exception {
		boolean skipRecord = false;
		if (transactionCouponRedemptionData.get(0) != null) {
			if (transactionCouponRedemptionData.get(0).equalsIgnoreCase(
					FordPassAprConstants.APR_HEADER)
					|| transactionCouponRedemptionData.get(0).equalsIgnoreCase(
							FordPassAprConstants.APR_TAIL)) {
				skipRecord = true;
			} else {
				skipRecord = false;
			}
		} else {
			if (transactionCouponRedemptionData.size() == 10) {
				skipRecord = false;

			} else {
				throw new Exception(FordPassAprConstants.TRANSACTION_COUPON_REDEMPTION_DATATYPE
						+ FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);

			}
		}
		return skipRecord;
	}
	
	

		
		private void validateAllMandatoryFields(ArrayList<String> transactionCouponRedmptionData) throws Exception{
			
			if ( transactionCouponRedmptionData.get(0) == null
					&& transactionCouponRedmptionData.get(1) == null
					&& transactionCouponRedmptionData.get(2) == null
					&& transactionCouponRedmptionData.get(4) == null
					&& transactionCouponRedmptionData.get(5) == null
					&& transactionCouponRedmptionData.get(6) == null
					&& transactionCouponRedmptionData.get(8) == null
					&& transactionCouponRedmptionData.get(9) == null
						)           
				throw new Exception(FordPassAprConstants.TRANSACTION_COUPON_REDEMPTION_DATATYPE + FordPassAprConstants.REQUIRED_FIELDS);		
		}
		
		//Sets record to transfail if any of the required field is empty	
		private void validateMandatoryFields(ArrayList<String> transactionCouponRedmptionData){
			final String METHOD_NAME = "validateMandatoryFields";
			log.entering(CLASS_NAME, METHOD_NAME);		
			
			FordPassAprHelper.validateMandatoryField(transactionCouponRedmptionData.get(0), tcpId);
			FordPassAprHelper.validateMandatoryField(transactionCouponRedmptionData.get(1), tcpCpnBarcode);
			FordPassAprHelper.validateMandatoryField(transactionCouponRedmptionData.get(2), tcpCpnId);
			FordPassAprHelper.validateMandatoryField(transactionCouponRedmptionData.get(4), tcpTrnId);
			FordPassAprHelper.validateMandatoryField(transactionCouponRedmptionData.get(5), tcpUseResult);
			FordPassAprHelper.validateMandatoryField(transactionCouponRedmptionData.get(6), tcpxCusId);
			FordPassAprHelper.validateMandatoryField(transactionCouponRedmptionData.get(8), ctryCodeTransaction);
			FordPassAprHelper.validateMandatoryField(transactionCouponRedmptionData.get(9), ctryCodeHome);
				
		}		
		
		
		
		
}
