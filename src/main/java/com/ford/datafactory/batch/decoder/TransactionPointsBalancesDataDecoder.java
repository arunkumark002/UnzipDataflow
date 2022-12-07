package com.ford.datafactory.batch.decoder;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.ford.datafactory.batch.bo.TransactionPointsBalancesBO;
import com.ford.datafactory.batch.util.CsvUtil;
import com.ford.datafactory.batch.util.CvdpConstants;
import com.ford.datafactory.batch.util.FordPassAprConstants;
import com.ford.datafactory.batch.util.FordPassAprHelper;
import org.codehaus.jackson.map.ObjectMapper;



public class TransactionPointsBalancesDataDecoder  implements IMultipleRecordDecoder {
	private static final String CLASS_NAME = TransactionPointsBalancesDataDecoder.class.getName();
	private static final Logger log = Logger.getLogger(CLASS_NAME);
	
	ObjectMapper objectMapper = new ObjectMapper();
	TransactionPointsBalancesBO transactionPointsBalancesBO;
	String processStatusDetails = "";
	//String headerTimestamp= null;
	boolean resultStatus = false; 
	
	public static final String headerDate = "Timestamp_Header_CT";
	public static final String tpbId = "TPB_ID";
	public static final String ptpCode = "PTP_CODE";
	public static final String tpbTrnId = "TPB_TRN_ID";
	public static final String tpbPoints = "TPB_POINTS";
	public static final String ctrCodeTransaction = "CTR_CODE_TRANSACTION";
	public static final String ctr_code_home = "CTR_CODE_HOME";
	public static final String partition_year = "PARTITION_YEAR";
	public static final String prtCode = "PRT_CODE";
	
	/*public TransactionPointsBalancesDataDecoder(String headertimestamp) {
		
		this.headerTimestamp = headertimestamp;
	}*/
	
	
	
	@Override
	public String decode(String headerTimestamp,String line) throws Exception {
		
		final String METHOD_NAME = "decode";
		log.entering(CLASS_NAME, METHOD_NAME);
		
		log.info(" comes to decode ***" );
		processStatusDetails = "";		
		transactionPointsBalancesBO = new TransactionPointsBalancesBO();
		
		try {
			
			ArrayList<String> transactionPointsBalancesData = CsvUtil.parseCsvRecord(line.toString());
			//Optimization Execution Time
			//PropertiesUtil.loadAllProps();
			
			/*final PropertyManager pm = PropertyManager.getInstance();
		
			final PropertyGroup dynamicGroup = pm.getGroup(CvdpConstants.PROPERTY_GROUP_DYNAMIC);*/
					
			// Process if not Header or Tail
			if (!validateSkipRecords(transactionPointsBalancesData)) 
			{
				
				// Check if there are 6 fields before breakdown
				if (transactionPointsBalancesData.size() == 7) {
					

					
					//validate mandatory fields
					validateAllMandatoryFields(transactionPointsBalancesData);
					validateMandatoryFields(transactionPointsBalancesData);
					
					if(transactionPointsBalancesData.get(6)==null ||transactionPointsBalancesData.get(6).trim()=="") {
						FordPassAprHelper.setShaKey(FordPassAprHelper.chunkAsOneStringExceptLastColumn(transactionPointsBalancesData,1) +","+ headerTimestamp,FordPassAprConstants.TRANSACTION_POINTS_BALANCES_DATATYPE,transactionPointsBalancesBO);
					}
					else{
					FordPassAprHelper.setShaKey(FordPassAprHelper.chunkAsOneString(transactionPointsBalancesData) +","+ headerTimestamp, FordPassAprConstants.TRANSACTION_POINTS_BALANCES_DATATYPE,transactionPointsBalancesBO);
					}
					// Set all transaction fields 
					setTransactionPointsBalancesMappings(transactionPointsBalancesData,headerTimestamp);
										
					// Set partition field
					setPartitionMappings(transactionPointsBalancesData,headerTimestamp);
				
					// set raw payload.                    
                    setRawPayload(transactionPointsBalancesData);
                    
                 	
					// Finally -- set process states
					resultStatus = FordPassAprHelper.setProcessState();
					
					if (resultStatus){
						transactionPointsBalancesBO.setProcess_status_details(FordPassAprHelper.setProcessDetails());
						transactionPointsBalancesBO
								.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_FAILED);
					} else{
						transactionPointsBalancesBO.setProcess_status_details(null);
						transactionPointsBalancesBO
								.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_SUCCESSFUL);
					}

				   transactionPointsBalancesBO.setProcess_status_date_time_utc(FordPassAprHelper
							.GetUTCdatetimeAsString());
					
					// reset process state
					FordPassAprHelper.resetProcessState();
					
					
					return this.objectMapper.writeValueAsString(transactionPointsBalancesBO);
					    
				}
			else{
				throw new Exception(
						FordPassAprConstants.TRANSACTION_POINTS_BALANCES_DATATYPE + FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);
			}
		}
		else{
			return null;
		}
		
	}
		catch (Exception e) {
			log.severe(FordPassAprConstants.TRANSACTION_POINTS_BALANCES_DATATYPE + FordPassAprConstants.PROCESSING_EXCEPTION);
			/*if (e.getMessage() == null)
			throw new Exception(
					FordPassAprConstants.TRANSACTION_POINTS_BALANCES_DATATYPE + FordPassAprConstants.PROCESSING_EXCEPTION);		
			else
				throw new Exception(e.getMessage());*/
			return null;

		}
		
		
	}
		
	private void setTransactionPointsBalancesMappings(ArrayList<String> transactionPointsBalancesData, String headerTimestamp) throws Exception {
		final String METHOD_NAME = "setTransactionPointsBalancesMappings";
		log.entering(CLASS_NAME, METHOD_NAME);
		
		
		
		transactionPointsBalancesBO.setHeader_timestamp_utc(FordPassAprHelper.parseHeaderDateTimestamp(headerTimestamp , headerDate));
		
		transactionPointsBalancesBO.setTpb_id(FordPassAprHelper.longParser(transactionPointsBalancesData.get(0), tpbId));
		
		transactionPointsBalancesBO.setPtp_code(FordPassAprHelper.stringParser(transactionPointsBalancesData.get(1), ptpCode));
		
		transactionPointsBalancesBO.setTpb_trn_id(FordPassAprHelper.longParser(transactionPointsBalancesData.get(2), tpbTrnId));
		
		transactionPointsBalancesBO.setTpb_points(FordPassAprHelper.longParser(transactionPointsBalancesData.get(3), tpbPoints));
		
		transactionPointsBalancesBO.setCtr_code_transaction(FordPassAprHelper.stringParser(transactionPointsBalancesData.get(4), ctrCodeTransaction));
		
		transactionPointsBalancesBO.setPrt_code(FordPassAprHelper.stringParser(transactionPointsBalancesData.get(6), prtCode));
	}
	
	// create partition columns
	private void setPartitionMappings(ArrayList<String> transactionPointsBalancesData , String headerTimestamp) throws Exception {
		final String METHOD_NAME = "setPartitionMappings";
		log.entering(CLASS_NAME, METHOD_NAME);
		
		
		transactionPointsBalancesBO.setCtr_code_home(FordPassAprHelper.stringParser(transactionPointsBalancesData.get(5),ctr_code_home ));
		
		// Add year field from header timestamp.
		transactionPointsBalancesBO.setPartition_year(FordPassAprHelper.intParser(FordPassAprHelper.transformISOPartitionDate(headerTimestamp , headerDate), headerDate));
	}

	private void setRawPayload(ArrayList<String> transactionPointsBalancesData) {
		
		StringBuffer sbf = new StringBuffer();
		sbf.append(transactionPointsBalancesData.get(0));
		for (int i = 1; i < transactionPointsBalancesData.size(); i++) {
			sbf.append(",").append(transactionPointsBalancesData.get(i));
		}
		transactionPointsBalancesBO.setRaw_payload(sbf.toString());

	}

	
	private boolean validateSkipRecords(ArrayList<String> transactionPointsBalancesData)
			throws Exception {
		boolean skipRecord = false;
		if (transactionPointsBalancesData.get(0) != null) {
			if (transactionPointsBalancesData.get(0).equalsIgnoreCase(
					FordPassAprConstants.APR_HEADER)
					|| transactionPointsBalancesData.get(0).equalsIgnoreCase(
							FordPassAprConstants.APR_TAIL)) {
				skipRecord = true;
			} else {
				skipRecord = false;
			}
		} else {
			if (transactionPointsBalancesData.size() == 7) {
				skipRecord = false;

			} else {
				throw new Exception(FordPassAprConstants.TRANSACTION_POINTS_BALANCES_DATATYPE
						+ FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);

			}
		}
		return skipRecord;
	}
	
		private void validateAllMandatoryFields(ArrayList<String> transactionPointsBalancesData) throws Exception {
			
			if ( transactionPointsBalancesData.get(0) == null
					&& transactionPointsBalancesData.get(1) == null
					&& transactionPointsBalancesData.get(2) == null
					&& transactionPointsBalancesData.get(3) == null
					&& transactionPointsBalancesData.get(4) == null
					&& transactionPointsBalancesData.get(5) == null
					)           
			
			throw new Exception(
					FordPassAprConstants.TRANSACTION_POINTS_BALANCES_DATATYPE
							+ FordPassAprConstants.REQUIRED_FIELDS);
		}
		
		//Sets record to transfail if any of the required field is empty	
		private void validateMandatoryFields(ArrayList<String> transactionPointsBalancesData){
			final String METHOD_NAME = "validateMandatoryFields";
			log.entering(CLASS_NAME, METHOD_NAME);		
			
			FordPassAprHelper.validateMandatoryField(transactionPointsBalancesData.get(0), tpbId);
			FordPassAprHelper.validateMandatoryField(transactionPointsBalancesData.get(1), ptpCode);
			FordPassAprHelper.validateMandatoryField(transactionPointsBalancesData.get(2), tpbTrnId);
			FordPassAprHelper.validateMandatoryField(transactionPointsBalancesData.get(3), tpbPoints);
			FordPassAprHelper.validateMandatoryField(transactionPointsBalancesData.get(4), ctrCodeTransaction);
			FordPassAprHelper.validateMandatoryField(transactionPointsBalancesData.get(5), ctr_code_home);
				
		}	
}
