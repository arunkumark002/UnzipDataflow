package com.ford.datafactory.batch.decoder;

import com.ford.datafactory.batch.bo.RewardBO;
import com.ford.datafactory.batch.bo.RewardBO.Reward_custom_attributes;
import com.ford.datafactory.batch.bo.Sgm_code;
import com.ford.datafactory.batch.util.CsvUtil;
import com.ford.datafactory.batch.util.CvdpConstants;
import com.ford.datafactory.batch.util.FordPassAprConstants;
import com.ford.datafactory.batch.util.FordPassAprHelper;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;


public class RewardDataDecoder implements IMultipleRecordDecoder {

    private static final String CLASS_NAME = RewardDataDecoder.class.getName();
    private static final Logger log = Logger.getLogger(CLASS_NAME);
    private ArrayList<String> outputList = null;
    private ObjectMapper objectMapper = new ObjectMapper();
    private RewardBO rewardBO;
    String processStatusDetails = "";
    private String headerTimestamp = null;
    private String countryCode = null;
    boolean resultStatus = false;
    public static final String CONST_HEADERDATE = "HEADER_TIMESTAMP_UTC";
    public static final String CONST_UNIQUEID = "PCD_ID";
    public static final String CONST_PARTNERCODE = "PRT_CODE";
    public static final String CONST_AUDITCREATEDATE = "PRD_AUDIT_CD";
    public static final String CONST_AUDITCREATEUSER = "PRD_AUDIT_CU";
    public static final String CONST_AUDITMODIFYDATE = "PRD_AUDIT_MD";
    public static final String CONST_AUDITMODIFYUSER = "PRD_AUDIT_MU";
    public static final String CONST_AUDITREMOVEDATE = "PRD_AUDIT_RD";
    public static final String CONST_AUDITREMOVEUSER = "prd_AUDIT_RU";
    public static final String CONST_PRODUCTDESC = "PRD_DESCRIPTION";
    public static final String CONST_MANUFACTURER = "PRD_MANUFACTURER_COM_ID";
    public static final String CONST_PRODUCTNAME = "PRD_NAME";
    public static final String CONST_PRODUCTPRICE = "PRD_UNIT_PRICE";
    public static final String CONST_PRODUCTCODE = "PRDX_REAL_CODE";
    public static final String CONST_CATEGORYDESC = "PRC_DESCRIPTION";
    public static final String CONST_CATEGORYNAME = "PRC_NAME";
    public static final String CONST_CATSHORTDESC = "PRC_SHORT_DESCRIPTION";
    public static final String CONST_CATEGORYCODE = "PRCX_REAL_CODE";

    public static final String CONST_BRANDID = "RWD_BRAND_COM_ID";
    public static final String CONST_COUPONTYPEID = "RWD_CTP_ID";
    public static final String CONST_DELIVERYMETHOD = "RWD_DELIVERY_METHOD";
    public static final String CONST_ENDDATE = "RWD_END_DATE";
    public static final String CONST_MOBILEDISPLAYED = "RWD_MOBILE_DISPLAYED";
    public static final String CONST_STARTDATE = "RWD_START_DATE";
    public static final String CONST_REWARDSTATUS = "RWD_STATUS";
    public static final String CONST_SUPPLIERID = "RWD_SUPPLIER_COM_ID";
    public static final String CONST_REWARDTYPE = "RWD_TYPE";
    public static final String CONST_REWARDTAX = "RWD_VAT";

    public static final String CONST_AUDITREMOVEPRODUCT = "PCD_AUDIT_RD";
    public static final String CONST_PRICECHANNELS = "PCD_CHANNELS";
    public static final String CONST_PRICECODE = "PCD_CODE";
    public static final String CONST_PRICEPOINTS = "PCD_POINTS";
    public static final String CONST_POINTCODE = "PTP_CODE";
    public static final String CONST_COUNTRYCODE = "CTR_CODE_POINTS";
    public static final String CONST_PARTITIONYEAR = "PARTITION_YEAR";
    public static final String CONST_LOTTERYID = "RWD_LTR_ID";
    public static final String CONST_ATTRIBUTES = "REWARD_CUSTOM_ATTRIBUTES";
    public static final String CONST_CLAIMPERIOD = "RWDX_CLAIM_PERIOD";
    public static final String CONST_CLAIMREMINDER = "RWDX_CLAIM_REMINDER";
    public static final String CONST_SGMCODE = "SGM_CODE";
    public static final String CONST_PRGCODE = "PRG_CODE";
    // F261022 Enhancement: Addition of 2 columns pwd_logistic_id and pcd_rgt_id.
    public static final String CONST_RWDLOGISTICID = "RWD_LOGISTIC_ID";
    public static final String CONST_PCDRGTID = "PCD_RGT_ID";

   /* public RewardDataDecoder(String headertimestamp, String countryCode) {

        this.headerTimestamp = headertimestamp;
        this.countryCode = countryCode;
    }*/

    public String decode(String fileName,String line) throws Exception {
        final String METHOD_NAME = "decode";
        log.entering(CLASS_NAME, METHOD_NAME);

        log.info(" comes to decode ***");
        processStatusDetails = "";
        rewardBO = new RewardBO();
        String output = null;

        String[] fileNameWithoutExt = fileName.substring(0,fileName.lastIndexOf('.')).split("_");
        String headerTimestamp = fileNameWithoutExt[fileNameWithoutExt.length - 1];

        try {

            ArrayList<String> rewardData = CsvUtil.parseCsvRecord(line
                    .toString());
            if (!validateSkipRecords(rewardData)) {

                if (rewardData.size() == 40) {



                    // Validate Mandatory Fields
                    validateAllMandatoryFields(rewardData);
                    validateMandatoryFields(rewardData);

                    // Set All Reward Mapping Fields
                    setRewardMappings(rewardData);

                    // Set partition field
                    setPartitionMappings(rewardData, this.headerTimestamp);

                    // Set raw payload
                    setRawPayload(rewardData);


                    // Finally -- set process states
                    resultStatus = FordPassAprHelper.setProcessState();

                    if (resultStatus) {
                        rewardBO.setProcess_status_details(FordPassAprHelper
                                .setProcessDetails());
                        rewardBO.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_FAILED);
                    } else {

                        rewardBO.setProcess_status_details(null);
                        rewardBO.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_SUCCESSFUL);
                    }

                    rewardBO.setProcess_status_date_time_utc(FordPassAprHelper
                            .GetUTCdatetimeAsString());

                    // reset
                    FordPassAprHelper.resetProcessState();
                    // return this.objectMapper.writeValueAsString(rewardBO);

                    if (FordPassAprConstants.NA_EU_VALUE == countryCode) {
                        //For NA_EU file, only USA country code will be created. Only one row will be created
						/*for (String code : FordPassAprConstants.NA_EU_CODES) {


							rewardBO.setCntry_c(code);
						rewardBO.setCntry_c("USA");
							try {
								rewardBO.setSha_key(HashUtil
										.calculateSHA256(chunkAsOneString(rewardData)
												+ code));
							} catch (Exception e) {
								e.printStackTrace();
								throw new Exception(
										FordPassAprConstants.REWARD_DATATYPE
												+ FordPassAprConstants.PAYLOAD_SHAKEY_ERR_DETAILS);
							}

							outputList.add(this.objectMapper
									.writeValueAsString(rewardBO));

*/
                        countryCode = FordPassAprConstants.USA_VALUE;
                        rewardBO.setCtr_code_points(countryCode);
                        FordPassAprHelper.setShaKey(FordPassAprHelper.chunkAsOneString(rewardData)+countryCode +","+ this.headerTimestamp,FordPassAprConstants.REWARD_DATATYPE,rewardBO);

                        //outputList.add(this.objectMapper.writeValueAsString(rewardBO));
                        output = this.objectMapper.writeValueAsString(rewardBO);


                    } else {

                        rewardBO.setCtr_code_points(countryCode);

                        //sha key generation
                        FordPassAprHelper.setShaKey(FordPassAprHelper.chunkAsOneString(rewardData)+countryCode +","+ this.headerTimestamp,FordPassAprConstants.REWARD_DATATYPE,rewardBO);

                        /*outputList.add(this.objectMapper.writeValueAsString(rewardBO));*/
                        output = this.objectMapper.writeValueAsString(rewardBO);
                    }
                    return output;
                }else{
                    throw new Exception(
                            FordPassAprConstants.REWARD_DATATYPE + FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);
                }
            } else {
                return null;

            }
        } catch (Exception e) {
            log.info("exceprion occured in Decoder***");
            /*if (e.getMessage() == null)
                throw new Exception(FordPassAprConstants.REWARD_DATATYPE
                        + FordPassAprConstants.PROCESSING_EXCEPTION);
            else
                throw new Exception(e.getMessage());*/
            return "empty";

        }

    }

    private boolean validateSkipRecords(ArrayList<String> rewardData)
            throws Exception {
        boolean skipRecord = false;
        if (rewardData.get(0) != null) {
            if (rewardData.get(0).equalsIgnoreCase(
                    FordPassAprConstants.APR_HEADER)
                    || rewardData.get(0).equalsIgnoreCase(
                    FordPassAprConstants.APR_TAIL)) {
                skipRecord = true;
            } else {
                skipRecord = false;
            }
        } else {
            if (rewardData.size() == 40) {
                skipRecord = false;

            } else {
                throw new Exception(FordPassAprConstants.REWARD_DATATYPE
                        + FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);

            }
        }
        return skipRecord;
    }



    private void setRawPayload(ArrayList<String> rewardData) {

        StringBuffer sbf = new StringBuffer();
        sbf.append(rewardData.get(0));
        for (int i = 1; i < rewardData.size(); i++) {
            sbf.append(",").append(rewardData.get(i));
        }
        rewardBO.setRaw_payload(sbf.toString());

    }

	/*private String chunkAsOneString(ArrayList<String> rewardData) {

		StringBuffer sbf1 = new StringBuffer();
		sbf1.append(rewardData.get(0));
		for (int i = 1; i < rewardData.size(); i++) {
			sbf1.append(",").append(rewardData.get(i));
		}
		return sbf1.toString();

	}*/

    private void validateAllMandatoryFields(ArrayList<String> rewardData)
            throws Exception {
        final String METHOD_NAME = "validateAllMandatoryFields";
        log.entering(CLASS_NAME, METHOD_NAME);
        if (rewardData.get(0) == null
                && rewardData.get(1) == null && rewardData.get(2) == null
                && rewardData.get(19) == null && rewardData.get(21) == null
                && rewardData.get(23) == null && rewardData.get(25) == null
                && rewardData.get(29) == null && rewardData.get(31) == null) {
            throw new Exception(FordPassAprConstants.REWARD_DATATYPE
                    + FordPassAprConstants.REQUIRED_FIELDS);
        }
    }


    //Sets record to transfail if any of the required field is empty
    private void validateMandatoryFields(ArrayList<String> rewardData){
        final String METHOD_NAME = "validateMandatoryFields";
        log.entering(CLASS_NAME, METHOD_NAME);

        FordPassAprHelper.validateMandatoryField(rewardData.get(0), CONST_UNIQUEID);
        //FordPassAprHelper.validateMandatoryField(rewardData.get(1), CONST_PARTNERCODE); //- F211923-Extension_of_columns
        FordPassAprHelper.validateMandatoryField(rewardData.get(2), CONST_AUDITCREATEDATE);
        FordPassAprHelper.validateMandatoryField(rewardData.get(19), CONST_DELIVERYMETHOD);
        FordPassAprHelper.validateMandatoryField(rewardData.get(21), CONST_MOBILEDISPLAYED);
        FordPassAprHelper.validateMandatoryField(rewardData.get(23), CONST_REWARDSTATUS);
        FordPassAprHelper.validateMandatoryField(rewardData.get(25), CONST_REWARDTYPE);
        FordPassAprHelper.validateMandatoryField(rewardData.get(29), CONST_PRICECODE);
        //FordPassAprHelper.validateMandatoryField(rewardData.get(31), CONST_POINTCODE); //- F211923-Extension_of_columns
    }

    private void setRewardMappings(ArrayList<String> rewardData) {
        final String METHOD_NAME = "setRewardMappings";
        log.entering(CLASS_NAME, METHOD_NAME);

        rewardBO.setHeader_timestamp_utc(FordPassAprHelper
                .parseHeaderDateTimestamp(this.headerTimestamp,
                        CONST_HEADERDATE));

        rewardBO.setPcd_id(FordPassAprHelper.longParser(rewardData.get(0),
                CONST_UNIQUEID));

        rewardBO.setPrt_code(FordPassAprHelper.stringParser(rewardData.get(1),
                CONST_PARTNERCODE));

        rewardBO.setPrd_audit_cd(FordPassAprHelper.parseDateTimestamp(
                rewardData.get(2), CONST_AUDITCREATEDATE));

        rewardBO.setPrd_audit_cu(FordPassAprHelper.longParser(
                rewardData.get(3), CONST_AUDITCREATEUSER));

        rewardBO.setPrd_audit_md(FordPassAprHelper.parseDateTimestamp(
                rewardData.get(4), CONST_AUDITMODIFYDATE));

        rewardBO.setPrd_audit_mu(FordPassAprHelper.longParser(
                rewardData.get(5), CONST_AUDITMODIFYUSER));

        rewardBO.setPrd_audit_rd(FordPassAprHelper.parseDateTimestamp(
                rewardData.get(6), CONST_AUDITREMOVEDATE));

        rewardBO.setPrd_audit_ru(FordPassAprHelper.longParser(
                rewardData.get(7), CONST_AUDITREMOVEUSER));

        rewardBO.setPrd_description(FordPassAprHelper.stringParser(
                rewardData.get(8), CONST_PRODUCTDESC));

        rewardBO.setPrd_manufacturer_com_id(FordPassAprHelper.longParser(
                rewardData.get(9), CONST_MANUFACTURER));

        rewardBO.setPrd_name(FordPassAprHelper.stringParser(rewardData.get(10),
                CONST_PRODUCTNAME));


		/*rewardBO.setPrd_unit_price(FordPassAprHelper.longParser(
				rewardData.get(11), CONST_PRODUCTPRICE));*/   //F211923 -Changes

        rewardBO.setPrd_unit_price(FordPassAprHelper.doubleParser(
                rewardData.get(11), CONST_PRODUCTPRICE));


        rewardBO.setPrdx_real_code(FordPassAprHelper.stringParser(
                rewardData.get(12), CONST_PRODUCTCODE));

        rewardBO.setPrc_description(FordPassAprHelper.stringParser(
                rewardData.get(13), CONST_CATEGORYDESC));

        rewardBO.setPrc_name(FordPassAprHelper.stringParser(rewardData.get(14),
                CONST_CATEGORYNAME));

        rewardBO.setPrc_short_description(FordPassAprHelper.stringParser(
                rewardData.get(15), CONST_CATSHORTDESC));

        rewardBO.setPrcx_real_code(FordPassAprHelper.stringParser(
                rewardData.get(16), CONST_CATEGORYCODE));

        rewardBO.setRwd_brand_com_id(FordPassAprHelper.longParser(
                rewardData.get(17), CONST_BRANDID));

        rewardBO.setRwd_ctp_id(FordPassAprHelper.longParser(rewardData.get(18),
                CONST_COUPONTYPEID));

        rewardBO.setRwd_delivery_method(FordPassAprHelper.stringParser(
                rewardData.get(19), CONST_DELIVERYMETHOD));

        rewardBO.setRwd_end_date(FordPassAprHelper.parseDateTimestamp(
                rewardData.get(20), CONST_ENDDATE));

        rewardBO.setRwd_mobile_displayed(FordPassAprHelper.stringParser(
                rewardData.get(21), CONST_MOBILEDISPLAYED));

        rewardBO.setRwd_start_date(FordPassAprHelper.parseDateTimestamp(
                rewardData.get(22), CONST_STARTDATE));

        rewardBO.setRwd_status(FordPassAprHelper.stringParser(
                rewardData.get(23), CONST_REWARDSTATUS));

        rewardBO.setRwd_supplier_com_id(FordPassAprHelper.longParser(
                rewardData.get(24), CONST_SUPPLIERID));

        rewardBO.setRwd_type(FordPassAprHelper.stringParser(rewardData.get(25),
                CONST_REWARDTYPE));

        rewardBO.setRwd_vat(FordPassAprHelper.longParser(rewardData.get(26),
                CONST_REWARDTAX));

        rewardBO.setPcd_audit_rd(FordPassAprHelper.parseDateTimestamp(
                rewardData.get(27), CONST_AUDITREMOVEPRODUCT));

        rewardBO.setPcd_channels(FordPassAprHelper.stringParser(
                rewardData.get(28), CONST_PRICECHANNELS));

        rewardBO.setPcd_code(FordPassAprHelper.stringParser(rewardData.get(29),
                CONST_PRICECODE));

        rewardBO.setPcd_points(FordPassAprHelper.longParser(rewardData.get(30),
                CONST_PRICEPOINTS));

        rewardBO.setPtp_code(FordPassAprHelper.stringParser(rewardData.get(31),
                CONST_POINTCODE));

        //rewardBO.setCtr_code_points(FordPassAprHelper.stringParser(
        //rewardData.get(32), CONST_COUNTRYCODE));

        rewardBO.setRwd_ltr_id(FordPassAprHelper.longParser(rewardData.get(32),
                CONST_LOTTERYID));

        rewardBO.setReward_custom_attributes(ValidateReward_custom_attributes(rewardData.get(33)));

        rewardBO.setRwdx_claim_period(FordPassAprHelper.longParser(rewardData.get(34),
                CONST_CLAIMPERIOD));

        rewardBO.setRwdx_claim_reminder(FordPassAprHelper.longParser(rewardData.get(35),
                CONST_CLAIMREMINDER));

        rewardBO.setSgm_code(validateSgm_code(rewardData.get(36)));
        rewardBO.setPrg_code(FordPassAprHelper.stringParser(rewardData.get(37),CONST_PRGCODE));

        // F261022 Enhancement: Addition of 2 columns pwd_logistic_id and pcd_rgt_id.
        rewardBO.setRwd_logistic_id(FordPassAprHelper.longParser(rewardData.get(38),
                CONST_RWDLOGISTICID));

        rewardBO.setPcd_rgt_id(FordPassAprHelper.longParser(rewardData.get(39),
                CONST_PCDRGTID));
    }

    private void setPartitionMappings(ArrayList<String> rewardData,
                                      String headerTimestamp) {
        final String METHOD_NAME = "setPartitionMappings";
        log.entering(CLASS_NAME, METHOD_NAME);

		/*rewardBO.setCtr_code_points(FordPassAprHelper.stringParser(
				rewardData.get(32), CONST_COUNTRYCODE));*/
        rewardBO.setPartition_year(FordPassAprHelper.intParser(
                FordPassAprHelper.transformISOPartitionDate(
                        this.headerTimestamp, CONST_HEADERDATE),
                CONST_HEADERDATE));
    }


    private Sgm_code validateSgm_code(String jsonVal){
        Sgm_code dummyBO = null;
        if(jsonVal!=null){
            jsonVal = jsonVal.replaceAll("\"\"", "\"");
            try {
                dummyBO =objectMapper.readValue(jsonVal, Sgm_code.class);
            } catch (JsonParseException e) {
                // TODO Auto-generated catch block
                FordPassAprHelper.processLogEvent(CONST_SGMCODE,FordPassAprConstants.EXCEPTION_COMPLEX_FIELD);
                return dummyBO;
            } catch (JsonMappingException e) {
                // TODO Auto-generated catch block
                FordPassAprHelper.processLogEvent(CONST_SGMCODE,FordPassAprConstants.EXCEPTION_COMPLEX_FIELD);
                return dummyBO;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                FordPassAprHelper.processLogEvent(CONST_SGMCODE,FordPassAprConstants.EXCEPTION_COMPLEX_FIELD);
                return dummyBO;
            }
        }
        return dummyBO;
    }

    private Reward_custom_attributes ValidateReward_custom_attributes(String jsonVal){
        Reward_custom_attributes dummyBO = null;
        if(jsonVal!=null){
            jsonVal = jsonVal.replaceAll("\"\"", "\"");
            try{
                jsonVal = jsonVal.replace("code","atr_code").replace("value","rxa_value").replace("attr_id","rxa_atr_id");


                dummyBO =objectMapper.readValue(jsonVal, Reward_custom_attributes.class);
            }catch (JsonParseException e) {
                // TODO Auto-generated catch block
                FordPassAprHelper.processLogEvent(CONST_ATTRIBUTES,FordPassAprConstants.EXCEPTION_COMPLEX_FIELD);
                return dummyBO;
            } catch (JsonMappingException e) {
                // TODO Auto-generated catch block
                FordPassAprHelper.processLogEvent(CONST_ATTRIBUTES,FordPassAprConstants.EXCEPTION_COMPLEX_FIELD);
                return dummyBO;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                FordPassAprHelper.processLogEvent(CONST_ATTRIBUTES,FordPassAprConstants.EXCEPTION_COMPLEX_FIELD);
                return dummyBO;
            }
        }
        return dummyBO;
    }



}

