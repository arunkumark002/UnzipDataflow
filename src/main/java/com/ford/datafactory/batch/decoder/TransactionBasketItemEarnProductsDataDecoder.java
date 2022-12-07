package com.ford.datafactory.batch.decoder;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.ford.datafactory.batch.bo.TransactionBasketItemEarnProductsBO;
import com.ford.datafactory.batch.util.CsvUtil;
import com.ford.datafactory.batch.util.CvdpConstants;
import com.ford.datafactory.batch.util.FordPassAprConstants;
import com.ford.datafactory.batch.util.FordPassAprHelper;
import org.codehaus.jackson.map.ObjectMapper;



public class TransactionBasketItemEarnProductsDataDecoder implements IMultipleRecordDecoder {
	
	private static final String CLASS_NAME = TransactionBasketItemEarnProductsDataDecoder.class.getName();
	private static final Logger log = Logger.getLogger(CLASS_NAME);
	
	ObjectMapper objectMapper = new ObjectMapper();
	TransactionBasketItemEarnProductsBO transactionBasketItemEarnProductsBO;
	String processStatusDetails = "";
	//String headerTimestamp= null;
	boolean resultStatus = false; 

	public static final String headerDate = "Timestamp_Header_CT";
	public static final String prtCode = "PRT_CODE";
	public static final String prdDescription = "PRD_DESCRIPTION";
	public static final String prdManufacturerComId = "PRD_MANUFACTURER_COM_ID";
	public static final String prdName = "PRD_NAME";
	public static final String prdUnitPrice = "PRD_UNIT_PRICE";
	public static final String prdxRealCode = "PRDX_REAL_CODE";
	public static final String prcDescription = "PRC_DESCRIPTION";
	public static final String prcName = "PRC_NAME";
	public static final String prcShortDescription = "PRC_SHORT_DESCRIPTION";
	public static final String prcxRealCode = "PRCX_REAL_CODE";
	public static final String tpdId = "TPD_ID";
	public static final String tpdQuantity = "TPD_QUANTITY";
	public static final String tpdTrnId = "TPD_TRN_ID";
	public static final String tpdValue = "TPD_VALUE";
	public static final String tpdxDiscount = "TPDX_DISCOUNT";
	public static final String tpdxUnitPriceNet = "TPDX_UNIT_PRICE_NET";
	public static final String tpdxValueNet = "TPDX_VALUE_NET";	
	public static final String ctryCodeTransaction = "COUNTRY_CODE_TRANSACTION";
	public static final String ctryCodeHome = "COUNTRY_CODE_HOME";
	public static final String tpdxUdbDetails = "TPDX_UDB_DETAILS";
	public static final String tpdxFordParts = "TPDX_FORD_PARTS";
	
	/*public TransactionBasketItemEarnProductsDataDecoder(String headertimestamp) {
		
		this.headerTimestamp = headertimestamp;
	}*/

	@Override
	public String decode(String headerTimestamp,String line) throws Exception {
		
		final String METHOD_NAME = "decode";
		log.entering(CLASS_NAME, METHOD_NAME);
	
		log.info(" comes to decode ***" );
		processStatusDetails = "";		
		transactionBasketItemEarnProductsBO = new TransactionBasketItemEarnProductsBO();
		
		try {
			
			ArrayList<String> transactionBasketItemEarnProductsData = CsvUtil.parseCsvRecord(line.toString());

		
			// Process if not Header or Tail
			if (!validateSkipRecords(transactionBasketItemEarnProductsData)) 
			{
			
				if (transactionBasketItemEarnProductsData.size() == 21) {
					

					
					//validate mandatory fields
					validateAllMandatoryFields(transactionBasketItemEarnProductsData);
					validateMandatoryFields(transactionBasketItemEarnProductsData);
					
					// Set all transaction fields 
					setTransactionMappings(transactionBasketItemEarnProductsData,headerTimestamp);
	
					
					// Set partition field
					setPartitionMappings(transactionBasketItemEarnProductsData,headerTimestamp);
				
					// set raw payload.                    
                    setRawPayload(transactionBasketItemEarnProductsData);
                    
                 					
					// SHA key generation
						
						if ((transactionBasketItemEarnProductsData.get(19)==null || transactionBasketItemEarnProductsData.get(19).trim()=="" )&&
						(transactionBasketItemEarnProductsData.get(20)==null || transactionBasketItemEarnProductsData.get(20).trim()=="" )){
							FordPassAprHelper.setShaKey(FordPassAprHelper.chunkAsOneStringExceptLastColumn(transactionBasketItemEarnProductsData,2) +","+ headerTimestamp,FordPassAprConstants.TRANS_BASKET_ITEM_EARN_PRODUCTS_DATATYPE,transactionBasketItemEarnProductsBO);
						}
						else
						{
							FordPassAprHelper.setShaKey(FordPassAprHelper.chunkAsOneString(transactionBasketItemEarnProductsData) +","+ headerTimestamp, FordPassAprConstants.TRANS_BASKET_ITEM_EARN_PRODUCTS_DATATYPE,transactionBasketItemEarnProductsBO);
							
						}
										
					
					// Finally -- set process states
					resultStatus = FordPassAprHelper.setProcessState();
					
					if (resultStatus){
						transactionBasketItemEarnProductsBO.setProcess_status_details(FordPassAprHelper.setProcessDetails());
						transactionBasketItemEarnProductsBO
								.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_FAILED);
					} else{
						transactionBasketItemEarnProductsBO.setProcess_status_details(null);
						transactionBasketItemEarnProductsBO
								.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_SUCCESSFUL);
					}

					transactionBasketItemEarnProductsBO.setProcess_status_date_time_utc(FordPassAprHelper
							.GetUTCdatetimeAsString());
					
					// reset process state
					FordPassAprHelper.resetProcessState();
					
					
					return this.objectMapper.writeValueAsString(transactionBasketItemEarnProductsBO);
					    
				}
			else{
				throw new Exception(
						FordPassAprConstants.TRANS_BASKET_ITEM_EARN_PRODUCTS_DATATYPE + FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);
			}
		}
		else{
			return null;
		}
		
	}
		catch (Exception e) {
			log.severe(e.getMessage());
			/*if (e.getMessage() == null)
			throw new Exception(
					FordPassAprConstants.TRANS_BASKET_ITEM_EARN_PRODUCTS_DATATYPE + FordPassAprConstants.PROCESSING_EXCEPTION);		
			else
				throw new Exception(e.getMessage());*/
return null;
		}
		
		
	}
	

	
	private void setTransactionMappings(ArrayList<String> transactionBasketItemEarnProductsData, String headerTimestamp) {
		final String METHOD_NAME = "setTransactionMappings";
		log.entering(CLASS_NAME, METHOD_NAME);
		
		
		
		transactionBasketItemEarnProductsBO.setHeader_timestamp_utc(FordPassAprHelper.parseHeaderDateTimestamp(headerTimestamp , headerDate));
		
		transactionBasketItemEarnProductsBO.setPrt_code(FordPassAprHelper.stringParser(transactionBasketItemEarnProductsData.get(0), prtCode));
		
		transactionBasketItemEarnProductsBO.setPrd_description(FordPassAprHelper.stringParser(transactionBasketItemEarnProductsData.get(1), prdDescription));
		
		transactionBasketItemEarnProductsBO.setPrd_manufacturer_com_id(FordPassAprHelper.longParser(transactionBasketItemEarnProductsData.get(2), prdManufacturerComId));
		
		transactionBasketItemEarnProductsBO.setPrd_name(FordPassAprHelper.stringParser(transactionBasketItemEarnProductsData.get(3), prdName));
		
		transactionBasketItemEarnProductsBO.setPrd_unit_price(FordPassAprHelper.doubleParser(transactionBasketItemEarnProductsData.get(4), prdUnitPrice));
		
		transactionBasketItemEarnProductsBO.setPrdx_real_code(FordPassAprHelper.stringParser(transactionBasketItemEarnProductsData.get(5), prdxRealCode));
		
		transactionBasketItemEarnProductsBO.setPrc_description(FordPassAprHelper.stringParser(transactionBasketItemEarnProductsData.get(6), prcDescription));
				
		transactionBasketItemEarnProductsBO.setPrc_name(FordPassAprHelper.stringParser(transactionBasketItemEarnProductsData.get(7), prcName));
		
		transactionBasketItemEarnProductsBO.setPrc_short_description(FordPassAprHelper.stringParser(transactionBasketItemEarnProductsData.get(8), prcShortDescription));
		
		transactionBasketItemEarnProductsBO.setPrcx_real_code(FordPassAprHelper.stringParser(transactionBasketItemEarnProductsData.get(9), prcxRealCode));
		
		transactionBasketItemEarnProductsBO.setTpd_id(FordPassAprHelper.longParser(transactionBasketItemEarnProductsData.get(10), tpdId));
		
		transactionBasketItemEarnProductsBO.setTpd_quantity(FordPassAprHelper.doubleParser(transactionBasketItemEarnProductsData.get(11), tpdQuantity));
		
		transactionBasketItemEarnProductsBO.setTpd_trn_id(FordPassAprHelper.longParser(transactionBasketItemEarnProductsData.get(12), tpdTrnId));
		
		transactionBasketItemEarnProductsBO.setTpd_value(FordPassAprHelper.doubleParser(transactionBasketItemEarnProductsData.get(13), tpdValue));
		
		transactionBasketItemEarnProductsBO.setTpdx_discount(FordPassAprHelper.doubleParser(transactionBasketItemEarnProductsData.get(14), tpdxDiscount));
		
		transactionBasketItemEarnProductsBO.setTpdx_unit_price_net(FordPassAprHelper.doubleParser(transactionBasketItemEarnProductsData.get(15), tpdxUnitPriceNet));
		
		transactionBasketItemEarnProductsBO.setTpdx_value_net(FordPassAprHelper.doubleParser(transactionBasketItemEarnProductsData.get(16), tpdxValueNet));
		
		transactionBasketItemEarnProductsBO.setCtr_code_transaction(FordPassAprHelper.stringParser(transactionBasketItemEarnProductsData.get(17), ctryCodeTransaction));
		
		//transactionBasketItemEarnProductsBO.setCtr_code_home(FordPassAprHelper.stringParser(transactionBasketItemEarnProductsData.get(18), ctryCodeHome));
		
		transactionBasketItemEarnProductsBO.setTpdx_udb_details(FordPassAprHelper.stringParser(transactionBasketItemEarnProductsData.get(19), tpdxUdbDetails));
		transactionBasketItemEarnProductsBO.setTpdx_ford_parts(FordPassAprHelper.stringParser(transactionBasketItemEarnProductsData.get(20), tpdxFordParts));
		
	}
	
	// create partition columns
	private void setPartitionMappings(ArrayList<String> transactionBasketItemEarnProductsData , String headerTimestamp) {
		final String METHOD_NAME = "setPartitionMappings";
		log.entering(CLASS_NAME, METHOD_NAME);
		
		transactionBasketItemEarnProductsBO.setCtr_code_home(FordPassAprHelper.stringParser(transactionBasketItemEarnProductsData.get(18), ctryCodeHome));
		// Add year field from header timestamp.
		transactionBasketItemEarnProductsBO.setPartition_year(FordPassAprHelper.intParser(FordPassAprHelper.transformISOPartitionDate(headerTimestamp , headerDate), headerDate));
	}
	

	private String chunckAsOneString(ArrayList<String> transactionBasketItemEarnProductsData) {
		
		StringBuffer sbf1 = new StringBuffer();	
		sbf1.append(transactionBasketItemEarnProductsData.get(0));
		 for(int i=1; i < transactionBasketItemEarnProductsData.size(); i++){
            sbf1.append(",").append(transactionBasketItemEarnProductsData.get(i));
    }
		return sbf1.toString();
		
	}

	private void setRawPayload(ArrayList<String> transactionBasketItemEarnProductsData) {
		
		StringBuffer sbf = new StringBuffer();
		sbf.append(transactionBasketItemEarnProductsData.get(0));
		for (int i = 1; i < transactionBasketItemEarnProductsData.size(); i++) {
			sbf.append(",").append(transactionBasketItemEarnProductsData.get(i));
		}
		transactionBasketItemEarnProductsBO.setRaw_payload(sbf.toString());

	}

	
	private boolean validateSkipRecords(ArrayList<String> transactionBasketItemEarnProductsData)
			throws Exception {
		boolean skipRecord = false;
		if (transactionBasketItemEarnProductsData.get(0) != null) {
			if (transactionBasketItemEarnProductsData.get(0).equalsIgnoreCase(
					FordPassAprConstants.APR_HEADER)
					|| transactionBasketItemEarnProductsData.get(0).equalsIgnoreCase(
							FordPassAprConstants.APR_TAIL)) {
				skipRecord = true;
			} else {
				skipRecord = false;
			}
		} else {
			if (transactionBasketItemEarnProductsData.size() == 21) {
				skipRecord = false;

			} else {
				throw new Exception(FordPassAprConstants.TRANS_BASKET_ITEM_EARN_PRODUCTS_DATATYPE
						+ FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);

			}
		}
		return skipRecord;
	}
	
	

		
		private void validateAllMandatoryFields(ArrayList<String> transactionBasketItemEarnProductsData) throws Exception{
			
			if ( transactionBasketItemEarnProductsData.get(0) == null
					&& transactionBasketItemEarnProductsData.get(10) == null
					&& transactionBasketItemEarnProductsData.get(11) == null
					&& transactionBasketItemEarnProductsData.get(12) == null
					&& transactionBasketItemEarnProductsData.get(17) == null
					&& transactionBasketItemEarnProductsData.get(18) == null
						)           
				throw new Exception(FordPassAprConstants.TRANS_BASKET_ITEM_EARN_PRODUCTS_DATATYPE + FordPassAprConstants.REQUIRED_FIELDS);		
		}
		

		//Sets record to transfail if any of the required field is empty	
		private void validateMandatoryFields(ArrayList<String> transactionBasketItemEarnProductsData){
			final String METHOD_NAME = "validateMandatoryFields";
			log.entering(CLASS_NAME, METHOD_NAME);		
			
			FordPassAprHelper.validateMandatoryField(transactionBasketItemEarnProductsData.get(0), prtCode);
			FordPassAprHelper.validateMandatoryField(transactionBasketItemEarnProductsData.get(10), tpdId);
			FordPassAprHelper.validateMandatoryField(transactionBasketItemEarnProductsData.get(11), tpdQuantity);
			FordPassAprHelper.validateMandatoryField(transactionBasketItemEarnProductsData.get(12), tpdTrnId);
			FordPassAprHelper.validateMandatoryField(transactionBasketItemEarnProductsData.get(17), ctryCodeTransaction);
			FordPassAprHelper.validateMandatoryField(transactionBasketItemEarnProductsData.get(18), ctryCodeHome);
				
		}		
		
		
		
		
}
