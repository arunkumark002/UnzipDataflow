package com.ford.datafactory.batch.decoder;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.ford.datafactory.batch.bo.TransactionBO;
import com.ford.datafactory.batch.util.CsvUtil;
import com.ford.datafactory.batch.util.CvdpConstants;
import com.ford.datafactory.batch.util.FordPassAprConstants;
import com.ford.datafactory.batch.util.FordPassAprHelper;
import org.codehaus.jackson.map.ObjectMapper;

public class TransactionDataDecoder implements IMultipleRecordDecoder {

	private static final String CLASS_NAME = TransactionDataDecoder.class.getName();
	private static final Logger log = Logger.getLogger(CLASS_NAME);

	ObjectMapper objectMapper = new ObjectMapper();
	TransactionBO transactionBO;

	//String headerTimestamp = null;
	String dynamic_ttp_code = null;
	String dynamic_ttp_name = null;
	String dynamic_ttp_desc = null;
	boolean resultStatus = false;

	public static final String headerDate = "Timestamp_Header_CT";
	public static final String trnId = "TRN_ID";
	public static final String prtCode = "PRT_CODE";
	public static final String trnAccID = "TRN_ACC_ID";
	public static final String trnChannel = "TRN_CHANNEL";
	public static final String trnComments = "TRN_COMMENTS";
	public static final String trnCustomerId = "TRN_CUS_ID";
	public static final String trnDate = "TRN_DATE";
	public static final String trnDiscount = "TRN_DISCOUNT_VALUE";
	public static final String trnLocId = "TRN_LOC_ID";
	public static final String trnProcessDate = "TRN_PROCESS_DATE";
	public static final String trnReceiptId = "TRN_RECEIPT_ID";
	public static final String trnSrcTrlId = "TRN_SRC_TRL_ID";
	public static final String trnSrcTrnId = "TRN_SRC_TRN_ID";
	public static final String trnStatus = "TRN_STATUS";
	public static final String trnTotalValue = "TRN_TOTAL_VALUE";
	public static final String trnType = "TRN_TYPE";
	public static final String trnName = "TRN_NAME";
	public static final String trnDescription = "TRN_DESCRIPTION";
	public static final String trnUserId = "TRN_USR_ID";
	public static final String ctrCodeAccount = "CTR_CODE_ACCOUNT";
	public static final String trnxSourceCode = "TRNX_APP_SOURCE_CODE";
	public static final String trnxCorrId = "TRNX_CORRELATION_ID";
	public static final String ctrCode = "CTR_CODE";
	public static final String trnxCurrency = "TRNX_CURRENCY";
	public static final String trnxDatt1 = "TRNX_DATT1";
	public static final String trnxDatt2 = "TRNX_DATT2";
	public static final String trnxEarnCode = "TRNX_EARN_CODE";
	public static final String trnxNatt1 = "TRNX_NATT1";
	public static final String trnxNatt2 = "TRNX_NATT2";
	public static final String trnxNatt3 = "TRNX_NATT3";
	public static final String trnxNatt4 = "TRNX_NATT4";
	public static final String trnxNatt5 = "TRNX_NATT5";
	public static final String trnxReason = "TRNX_REASON_CODE";
	public static final String trnxReceiptId = "TRNX_RECEIPT_ID";
	public static final String trnxSatt1 = "TRNX_SATT1";
	public static final String trnxSatt2 = "TRNX_SATT2";
	public static final String trnxSatt3 = "TRNX_SATT3";
	public static final String trnxSatt4 = "TRNX_SATT4";
	public static final String trnxSatt5 = "TRNX_SATT5";
	public static final String trnxSatt6 = "TRNX_SATT6";
	public static final String trnxSatt7 = "TRNX_SATT7";
	public static final String trnxSatt8 = "TRNX_SATT8";
	public static final String trnxTotalDiscount = "TRNX_TOTAL_DISCOUNT_BASKET";
	public static final String trnxTotalQuantity = "TRNX_TOTAL_QUANTITY";
	public static final String trnxTotalValueNet = "TRNX_TOTAL_VALUE_NET";
	public static final String trnxSource = "TRNX_TRN_SOURCE";
	public static final String trnxZone = "TRNX_ZONE_ID";
	public static final String trnxBookDate = "TRNX_BOOK_DATE";
	public static final String trnxRequestDate = "TRNX_REQUEST_DATE";
	public static final String trnxActivityCode = "TRNX_ACTIVITY_CODE";
	public static final String trnCashierID = "TRN_CASHIER_ID";
	public static final String trnxPrtInvoice = "TRNX_PRT_INVOICE_NO";
	public static final String trnxPayInvoice = "TRNX_PAY_INVOICE_ID";
	public static final String trnxRewardPgm = "TRNX_REWARD_PROGRAM";
	public static final String trnxExtTxGroup = "TRNX_EXT_TX_GROUP_NO";
	public static final String prtCodeFPR = "PRT_CODE_FPR";
	public static final String trnxTotalPointsValue = "TRNX_TOTAL_POINTS_VALUE";
	public static final String trnxprocdateoffsetUtc = "TRNX_PROC_DATE_OFFSET_UTC";
	public static final String trnxMiscAmount = "trnx_misc_amount";
	// FPA _ FPR (FordPass Rewards) enhancement
	public static final String trnAttr1 = "TRN_ATTR01";
	public static final String trnAttr2 = "TRN_ATTR02";
	public static final String trnAttr3 = "TRN_ATTR03";
	public static final String trnxAdditionalAttributes = "TRNX_ADDITIONAL_ATTRIBUTES";

	//FPA –  Enhancement CR# TA1990032 - US1068023 - Addition of prg_code
	public static final String prgCode = "PRG_CODE";

	//FPA - FPR ( FordPass Rewards - Pre-Feature Analysis) enhancement CR# F206322
	public static final String  trnxSatt9  = "TRNX_SATT9";
	public static final String  trnxSatt10 = "TRNX_SATT10";
	public static final String  trnxSatt11 = "TRNX_SATT11";
	public static final String  trnxSatt12 = "TRNX_SATT12";
	public static final String  trnxSatt13 = "TRNX_SATT13";
	public static final String  trnxSatt14 = "TRNX_SATT14";
	public static final String  trnxSatt15 = "TRNX_SATT15";
	public static final String  trnxSatt16 = "TRNX_SATT16";
	public static final String  trnxSatt17 = "TRNX_SATT17";
	public static final String  trnxSatt18 = "TRNX_SATT18";
	public static final String  trnxSatt19 = "TRNX_SATT19";
	public static final String  trnxSatt20 = "TRNX_SATT20";
	public static final String  trnxNatt6  = "TRNX_NATT6";
	public static final String  trnxNatt7  = "TRNX_NATT7";
	public static final String  trnxNatt8  = "TRNX_NATT8";
	public static final String  trnxNatt9  = "TRNX_NATT9";
	public static final String  trnxNatt10  = "TRNX_NATT10";
	public static final String  trnxDatt3  = "TRNX_DATT3";
	public static final String  trnxDatt4  = "TRNX_DATT4";
	public static final String  trnxDatt5  = "TRNX_DATT5";

	//PA - FPA enhancement CR# F233806

	public static final String  trn_attr04  = "TRN_ATTR04";
	public static final String  trn_attr05  = "TRN_ATTR05";


	/*public TransactionDataDecoder(String headertimestamp, String dynamic_ttp_code, String dynamic_ttp_name,
								  String dynamic_ttp_desc) {

		this.headerTimestamp = headertimestamp;
		this.dynamic_ttp_code = dynamic_ttp_code;
		this.dynamic_ttp_name = dynamic_ttp_name;
		this.dynamic_ttp_desc = dynamic_ttp_desc;

	}*/

	@Override
	public String decode(String headerTimestamp,String line) throws Exception {

		final String METHOD_NAME = "decode";

		transactionBO = new TransactionBO();

		try {

			ArrayList<String> transactionData = CsvUtil.parseCsvRecord(line.toString());

			//Execution time Optimization
			//PropertiesUtil.loadAllProps();

			// final PropertyManager pm = PropertyManager.getInstance();

			// final PropertyGroup dynamicGroup =
			// pm.getGroup(CvdpConstants.PROPERTY_GROUP_DYNAMIC);

			ArrayList<String> ttpCode = CsvUtil.parseCsvRecord(dynamic_ttp_code);
			ArrayList<String> ttpName = CsvUtil.parseCsvRecord(dynamic_ttp_name);
			ArrayList<String> ttpDesc = CsvUtil.parseCsvRecord(dynamic_ttp_desc);

			// Process if not Header or Tail
			if (!validateSkipRecords(transactionData)) {

				// Check if there are 82 fields before breakdown
				if (transactionData.size() == 84) {

					// validate mandatory fields
					validateAllMandatoryFields(transactionData);
					validateMandatoryFields(transactionData);

					// sha key generation

					// FPA _ FPR (FordPass Rewards) enhancement
					if ((transactionData.get(48) == null || transactionData.get(48).trim().equalsIgnoreCase(""))
							&& (transactionData.get(49) == null || transactionData.get(49).trim().equalsIgnoreCase(""))
							&& (transactionData.get(50) == null || transactionData.get(50).trim().equalsIgnoreCase(""))
							&& (transactionData.get(51) == null || transactionData.get(51).trim().equalsIgnoreCase(""))
							&& (transactionData.get(52) == null || transactionData.get(52).trim().equalsIgnoreCase(""))
							&& (transactionData.get(53) == null || transactionData.get(53).trim().equalsIgnoreCase(""))
							&& (transactionData.get(54) == null || transactionData.get(54).trim().equalsIgnoreCase(""))
							&& (transactionData.get(55) == null || transactionData.get(55).trim().equalsIgnoreCase(""))
							&& (transactionData.get(56) == null || transactionData.get(56).trim().equalsIgnoreCase(""))
							&& (transactionData.get(57) == null || transactionData.get(57).trim().equalsIgnoreCase(""))
							&& (transactionData.get(58) == null || transactionData.get(58).trim().equalsIgnoreCase(""))
							&& (transactionData.get(59) == null || transactionData.get(59).trim().equalsIgnoreCase(""))
							&& (transactionData.get(60) == null || transactionData.get(60).trim().equalsIgnoreCase("")))

					{
						//adding this.headerTimestamp in the end of shakey
						FordPassAprHelper.setShaKey(
								FordPassAprHelper.chunkAsOneStringExceptLastColumn(transactionData, 13) + "," + headerTimestamp,
								FordPassAprConstants.TRANSACTION_DATATYPE, transactionBO);
					} else {
						FordPassAprHelper.setShaKey(FordPassAprHelper.chunkAsOneString(transactionData) + "," + headerTimestamp,
								FordPassAprConstants.TRANSACTION_DATATYPE, transactionBO);

					}

					// Set all transaction fields
					setTransactionMappings(transactionData,headerTimestamp);

					// set ttp_name and ttp_description fields from java property file
					setTtpNameDesc(transactionData, ttpCode, ttpName, ttpDesc);

					// Set partition field
					setPartitionMappings(transactionData, headerTimestamp);

					// set raw payload.
					setRawPayload(transactionData);

					// Finally -- set process states
					resultStatus = FordPassAprHelper.setProcessState();

					if (resultStatus) {
						transactionBO.setProcess_status_details(FordPassAprHelper.setProcessDetails());
						transactionBO.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_FAILED);
					} else {
						transactionBO.setProcess_status_details(null);
						transactionBO.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_SUCCESSFUL);
					}

					transactionBO.setProcess_status_date_time_utc(FordPassAprHelper.GetUTCdatetimeAsString());

					// reset process state
					FordPassAprHelper.resetProcessState();

					return this.objectMapper.writeValueAsString(transactionBO);

				} else {
					throw new Exception(
							FordPassAprConstants.TRANSACTION_DATATYPE + FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);
				}
			} else {
				return null;
			}

		} catch (Exception e) {
			/*if (e.getMessage() == null)
				throw new Exception(
						FordPassAprConstants.TRANSACTION_DATATYPE + FordPassAprConstants.PROCESSING_EXCEPTION);
			else
				throw new Exception(e.getMessage());*/
			log.severe(e.getMessage());
			return null;

		}

	}

	private void setTtpNameDesc(ArrayList<String> transactionData, ArrayList<String> ttpCode, ArrayList<String> ttpName,
								ArrayList<String> ttpDesc) {
		transactionBO.setTtp_name(null);
		transactionBO.setTtp_desc(null);

		int IndexValue = ttpCode.indexOf(transactionData.get(15));

		if (IndexValue >= 0) {

			transactionBO.setTtp_name(ttpName.get(IndexValue));
			transactionBO.setTtp_desc(ttpDesc.get(IndexValue));
		} else {
			transactionBO.setTtp_name("UNDEFINED");
			transactionBO.setTtp_desc("UNDEFINED");

			FordPassAprHelper.processLogEvent(FordPassAprConstants.TRANSACTION_DATATYPE,
					FordPassAprConstants.TRANSACTION_TTP_ERR_DETAILS);
		}

	}

	private void setTransactionMappings(ArrayList<String> transactionData, String headerTimestamp) {
		final String METHOD_NAME = "setTransactionMappings";
		log.entering(CLASS_NAME, METHOD_NAME);

		transactionBO
				.setHeader_timestamp_utc(FordPassAprHelper.parseHeaderDateTimestamp(headerTimestamp, headerDate));

		transactionBO.setTrn_id(FordPassAprHelper.longParser(transactionData.get(0), trnId));

		transactionBO.setPrt_code(FordPassAprHelper.stringParser(transactionData.get(1), prtCode));

		transactionBO.setTrn_acc_id(FordPassAprHelper.longParser(transactionData.get(2), trnAccID));

		transactionBO.setTrn_channel(FordPassAprHelper.stringParser(transactionData.get(3), trnChannel));

		transactionBO.setTrn_comments(FordPassAprHelper.stringParser(transactionData.get(4), trnComments));

		transactionBO.setTrn_cus_id(FordPassAprHelper.longParser(transactionData.get(5), trnCustomerId));

		transactionBO.setTrn_date(FordPassAprHelper.parseDateTimestamp(transactionData.get(6), trnDate));

		transactionBO.setTrn_discount_value(FordPassAprHelper.doubleParser(transactionData.get(7), trnDiscount));

		transactionBO.setTrn_loc_id(FordPassAprHelper.longParser(transactionData.get(8), trnLocId));

		transactionBO.setTrn_process_date(FordPassAprHelper.parseDateTimestamp(transactionData.get(9), trnProcessDate));

		transactionBO.setTrn_receipt_id(FordPassAprHelper.stringParser(transactionData.get(10), trnReceiptId));

		transactionBO.setTrn_src_trl_id(FordPassAprHelper.longParser(transactionData.get(11), trnSrcTrlId));

		transactionBO.setTrn_src_trn_id(FordPassAprHelper.longParser(transactionData.get(12), trnSrcTrnId));

		transactionBO.setTrn_status(FordPassAprHelper.stringParser(transactionData.get(13), trnStatus));

		transactionBO.setTrn_total_value(FordPassAprHelper.doubleParser(transactionData.get(14), trnTotalValue));

		transactionBO.setTrn_type(FordPassAprHelper.stringParser(transactionData.get(15), trnType));

		transactionBO.setTrn_usr_id(FordPassAprHelper.longParser(transactionData.get(16), trnUserId));

		transactionBO.setCtr_code_account(FordPassAprHelper.stringParser(transactionData.get(17), ctrCodeAccount));

		transactionBO.setTrnx_app_source_code(FordPassAprHelper.stringParser(transactionData.get(18), trnxSourceCode));

		transactionBO.setTrnx_correlation_id(FordPassAprHelper.stringParser(transactionData.get(19), trnxCorrId));

		transactionBO.setTrnx_currency(FordPassAprHelper.stringParser(transactionData.get(21), trnxCurrency));

		transactionBO.setTrnx_datt1(FordPassAprHelper.parseDateTimestamp(transactionData.get(22), trnxDatt1));

		transactionBO.setTrnx_datt2(FordPassAprHelper.parseDateTimestamp(transactionData.get(23), trnxDatt2));

		transactionBO.setTrnx_earn_code(FordPassAprHelper.stringParser(transactionData.get(24), trnxEarnCode));

		transactionBO.setTrnx_natt1(FordPassAprHelper.doubleParser(transactionData.get(25), trnxNatt1));

		transactionBO.setTrnx_natt2(FordPassAprHelper.doubleParser(transactionData.get(26), trnxNatt2));

		transactionBO.setTrnx_natt3(FordPassAprHelper.doubleParser(transactionData.get(27), trnxNatt3));

		transactionBO.setTrnx_natt4(FordPassAprHelper.doubleParser(transactionData.get(28), trnxNatt4));

		transactionBO.setTrnx_natt5(FordPassAprHelper.doubleParser(transactionData.get(29), trnxNatt5));

		transactionBO.setTrnx_reason_code(FordPassAprHelper.stringParser(transactionData.get(30), trnxReason));

		transactionBO.setTrnx_receipt_id(FordPassAprHelper.stringParser(transactionData.get(31), trnxReceiptId));

		transactionBO.setTrnx_satt1(FordPassAprHelper.stringParser(transactionData.get(32), trnxSatt1));

		transactionBO.setTrnx_satt2(FordPassAprHelper.stringParser(transactionData.get(33), trnxSatt2));

		transactionBO.setTrnx_satt3(FordPassAprHelper.stringParser(transactionData.get(34), trnxSatt3));

		transactionBO.setTrnx_satt4(FordPassAprHelper.stringParser(transactionData.get(35), trnxSatt4));

		transactionBO.setTrnx_satt5(FordPassAprHelper.stringParser(transactionData.get(36), trnxSatt5));

		transactionBO.setTrnx_satt6(FordPassAprHelper.stringParser(transactionData.get(37), trnxSatt6));

		transactionBO.setTrnx_satt7(FordPassAprHelper.stringParser(transactionData.get(38), trnxSatt7));

		transactionBO.setTrnx_satt8(FordPassAprHelper.stringParser(transactionData.get(39), trnxSatt8));

		transactionBO.setTrnx_total_discount_basket(
				FordPassAprHelper.doubleParser(transactionData.get(40), trnxTotalDiscount));

		transactionBO
				.setTrnx_total_quantity(FordPassAprHelper.doubleParser(transactionData.get(41), trnxTotalQuantity));

		transactionBO
				.setTrnx_total_value_net(FordPassAprHelper.doubleParser(transactionData.get(42), trnxTotalValueNet));

		transactionBO.setTrnx_trn_source(FordPassAprHelper.stringParser(transactionData.get(43), trnxSource));

		transactionBO.setTrnx_zone_id(FordPassAprHelper.stringParser(transactionData.get(44), trnxZone));

		transactionBO.setTrnx_book_date(FordPassAprHelper.parseDateTimestamp(transactionData.get(45), trnxBookDate));

		transactionBO
				.setTrnx_request_date(FordPassAprHelper.parseDateTimestamp(transactionData.get(46), trnxRequestDate));

		transactionBO.setTrnx_activity_code(FordPassAprHelper.stringParser(transactionData.get(47), trnxActivityCode));

		transactionBO.setTrn_cashier_id(FordPassAprHelper.stringParser(transactionData.get(48), trnCashierID));

		transactionBO.setTrnx_prt_invoice_no(FordPassAprHelper.stringParser(transactionData.get(49), trnxPrtInvoice));

		transactionBO.setTrnx_pay_invoice_id(FordPassAprHelper.stringParser(transactionData.get(50), trnxPayInvoice));

		transactionBO.setTrnx_reward_program(FordPassAprHelper.stringParser(transactionData.get(51), trnxRewardPgm));

		transactionBO.setTrnx_ext_tx_group_no(FordPassAprHelper.stringParser(transactionData.get(52), trnxExtTxGroup));

		transactionBO.setPrt_code_fpr(FordPassAprHelper.stringParser(transactionData.get(53), prtCodeFPR));

		transactionBO.setTrnx_total_points_value(
				FordPassAprHelper.doubleParser(transactionData.get(54), trnxTotalPointsValue));

		transactionBO.setTrnx_proc_date_offset_utc(
				FordPassAprHelper.intParser(transactionData.get(55), trnxprocdateoffsetUtc));

		transactionBO.setTrnx_misc_amount(FordPassAprHelper.doubleParser(transactionData.get(56), trnxMiscAmount));

		// FPA _ FPR (FordPass Rewards) enhancement
		transactionBO.setTrn_attr01(FordPassAprHelper.stringParser(transactionData.get(57), trnAttr1));

		transactionBO.setTrn_attr02(FordPassAprHelper.stringParser(transactionData.get(58), trnAttr2));

		transactionBO.setTrn_attr03(FordPassAprHelper.stringParser(transactionData.get(59), trnAttr3));

		transactionBO.setTrnx_additional_attributes(
				FordPassAprHelper.stringParser(transactionData.get(60), trnxAdditionalAttributes));

		//FPA –  Enhancement CR# TA1990032 - US1068023 - Addition of prg_code
		transactionBO.setPrg_code(FordPassAprHelper.stringParser(transactionData.get(61), prgCode));

		//FPA - FPR ( FordPass Rewards - Pre-Feature Analysis) enhancement CR# F206322
		transactionBO.setTrnx_satt9(FordPassAprHelper.stringParser(transactionData.get(62), trnxSatt9));
		transactionBO.setTrnx_satt10(FordPassAprHelper.stringParser(transactionData.get(63), trnxSatt10));
		transactionBO.setTrnx_satt11(FordPassAprHelper.stringParser(transactionData.get(64), trnxSatt11));
		transactionBO.setTrnx_satt12(FordPassAprHelper.stringParser(transactionData.get(65), trnxSatt12));
		transactionBO.setTrnx_satt13(FordPassAprHelper.stringParser(transactionData.get(66), trnxSatt13));
		transactionBO.setTrnx_satt14(FordPassAprHelper.stringParser(transactionData.get(67), trnxSatt14));
		transactionBO.setTrnx_satt15(FordPassAprHelper.stringParser(transactionData.get(68), trnxSatt15));
		transactionBO.setTrnx_satt16(FordPassAprHelper.stringParser(transactionData.get(69), trnxSatt16));
		transactionBO.setTrnx_satt17(FordPassAprHelper.stringParser(transactionData.get(70), trnxSatt17));
		transactionBO.setTrnx_satt18(FordPassAprHelper.stringParser(transactionData.get(71), trnxSatt18));
		transactionBO.setTrnx_satt19(FordPassAprHelper.stringParser(transactionData.get(72), trnxSatt19));
		transactionBO.setTrnx_satt20(FordPassAprHelper.stringParser(transactionData.get(73), trnxSatt20));
		transactionBO.setTrnx_natt6(FordPassAprHelper.doubleParser(transactionData.get(74), trnxNatt6));
		transactionBO.setTrnx_natt7(FordPassAprHelper.doubleParser(transactionData.get(75), trnxNatt7));
		transactionBO.setTrnx_natt8(FordPassAprHelper.doubleParser(transactionData.get(76), trnxNatt8));
		transactionBO.setTrnx_natt9(FordPassAprHelper.doubleParser(transactionData.get(77), trnxNatt9));
		transactionBO.setTrnx_natt10(FordPassAprHelper.doubleParser(transactionData.get(78), trnxNatt10));
		transactionBO.setTrnx_datt3(FordPassAprHelper.parseDateTimestamp(transactionData.get(79), trnxDatt3));
		transactionBO.setTrnx_datt4(FordPassAprHelper.parseDateTimestamp(transactionData.get(80), trnxDatt4));
		transactionBO.setTrnx_datt5(FordPassAprHelper.parseDateTimestamp(transactionData.get(81), trnxDatt5));
		transactionBO.setTrn_attr04(FordPassAprHelper.stringParser(transactionData.get(82), trn_attr04));
		transactionBO.setTrn_attr05(FordPassAprHelper.stringParser(transactionData.get(83), trn_attr05));

	}

	// create partition columns
	private void setPartitionMappings(ArrayList<String> transactionData, String headerTimestamp) {
		final String METHOD_NAME = "setPartitionMappings";
		log.entering(CLASS_NAME, METHOD_NAME);

		transactionBO.setCtr_code(FordPassAprHelper.stringParser(transactionData.get(20), ctrCode));
		// Add year field from header timestamp.
		transactionBO.setPartition_year(FordPassAprHelper
				.intParser(FordPassAprHelper.transformISOPartitionDate(headerTimestamp, headerDate), headerDate));
	}

	private void setRawPayload(ArrayList<String> transactionData) {

		StringBuffer sbf = new StringBuffer();
		sbf.append(transactionData.get(0));
		for (int i = 1; i < transactionData.size(); i++) {
			sbf.append(",").append(transactionData.get(i));
		}
		transactionBO.setRaw_payload(sbf.toString());

	}

	private boolean validateSkipRecords(ArrayList<String> transactionData) throws Exception {
		boolean skipRecord = false;
		if (transactionData.get(0) != null) {
			if (transactionData.get(0).equalsIgnoreCase(FordPassAprConstants.APR_HEADER)
					|| transactionData.get(0).equalsIgnoreCase(FordPassAprConstants.APR_TAIL)) {
				skipRecord = true;
			} else {
				skipRecord = false;
			}
		} else {
			if (transactionData.size() == 84) {
				skipRecord = false;

			} else {
				throw new Exception(
						FordPassAprConstants.TRANSACTION_DATATYPE + FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);

			}
		}
		return skipRecord;
	}

	private void validateAllMandatoryFields(ArrayList<String> transactionData) throws Exception {

		if (transactionData.get(0) == null

				//&& transactionData.get(5) == null && transactionData.get(6) == null - F211923-Extension_of_columns
				&& transactionData.get(6) == null
				// Commented as part of "F148027 - FPA _ Transaction & Transaction Rule change"
				// && transactionData.get(8) == null
				// && transactionData.get(16) == null
				&& transactionData.get(17) == null && transactionData.get(20) == null) {
			throw new Exception(FordPassAprConstants.TRANSACTION_DATATYPE + FordPassAprConstants.REQUIRED_FIELDS);
		}

	}

	// Sets record to transfail if any of the required field is empty
	private void validateMandatoryFields(ArrayList<String> transactionData) {
		final String METHOD_NAME = "validateMandatoryFields";
		log.entering(CLASS_NAME, METHOD_NAME);

		FordPassAprHelper.validateMandatoryField(transactionData.get(0), trnId);
		//FordPassAprHelper.validateMandatoryField(transactionData.get(5), trnCustomerId);  - F211923-Extension_of_columns
		FordPassAprHelper.validateMandatoryField(transactionData.get(6), trnDate);
		// Commented as part of "F148027 - FPA _ Transaction & Transaction Rule change"
		// FordPassAprHelper.validateMandatoryField(transactionData.get(8), trnLocId);
		// FordPassAprHelper.validateMandatoryField(transactionData.get(16), trnUserId);
		FordPassAprHelper.validateMandatoryField(transactionData.get(17), ctrCodeAccount);
		FordPassAprHelper.validateMandatoryField(transactionData.get(20), ctrCode);

	}

}
