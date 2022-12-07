package com.ford.datafactory.batch.decoder;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.codehaus.jackson.map.ObjectMapper;

import com.ford.datafactory.batch.util.CsvUtil;
import com.ford.datafactory.batch.util.CvdpConstants;
import com.ford.datafactory.batch.util.FordPassAprConstants;
import com.ford.datafactory.batch.util.FordPassAprHelper;
import com.ford.datafactory.batch.util.HashUtil;
import com.ford.datafactory.batch.bo.RecognitionTiersBO;
import java.util.logging.Logger;

public class RecognitionTiersDecoder  {

    private static final String CLASS_NAME = RecognitionTiersDecoder.class.getName();
    private static final Logger log = Logger.getLogger(CLASS_NAME);

    //private String headerTimestamp = null;
    private ObjectMapper objectMapper = new ObjectMapper();
    private RecognitionTiersBO recogTiersBo;
    // private String countryCode = null;
    private boolean resultStatus = false;

    private static String COLUMN_HEADER_TS = "Timestamp_Header_CT";
    public static final String RGT_ID = "RGT_ID";
    public static final String RGS_CODE = "RGS_CODE";
    public static final String RGS_NAME = "RGS_NAME";
    public static final String RGS_DESCRIPTION = "RGS_DESCRIPTION";
    public static final String RGS_DOMAIN = "RGS_DOMAIN";
    public static final String RGT_CODE = "RGT_CODE";
    public static final String RGT_NAME = "RGT_NAME";
    public static final String RGT_PRIORITY = "RGT_PRIORITY";
    public static final String RGT_DESCRIPTION = "RGT_DESCRIPTION";
    public static final String RGT_AUDIT_CD = "RGT_AUDIT_CD";
    public static final String RGT_AUDIT_CU = "RGT_AUDIT_CU";
    public static final String RGT_AUDIT_RD = "RGT_AUDIT_RD";
    public static final String RGT_AUDIT_RU = "RGT_AUDIT_RU";
    private static final String PARTITION_YR = "PARTITION_YR";
    private final List<String> RGS_DOMAIN_VALUES = Arrays.asList("A", "C");

    /*public RecognitionTiersDecoder(String headerTimestamp) {
        this.headerTimestamp = headerTimestamp;
    }*/


    public String decode(String fileName,String line) throws Exception {

        final String METHOD_NAME = "decode";
        recogTiersBo = new RecognitionTiersBO();
        String[] fileNameWithoutExt = fileName.substring(0,fileName.lastIndexOf('.')).split("_");
        String headerTimestamp = fileNameWithoutExt[fileNameWithoutExt.length - 1];

        try {

            ArrayList<String> parsedValue = CsvUtil.parseCsvRecord(line.toString());
            // Process if not Header or Tail
            if (!validateSkipRecords(parsedValue)) {
                // validate input records columns count
                if (parsedValue.size() == 13) {
                    // Fail processing
                    validateAllMandatoryFields(parsedValue);
                    validateMandatoryFields(parsedValue);
                    validateRecognizationDomain(parsedValue);

                    try {
                        recogTiersBo.setSha_key(HashUtil.calculateSHA256(chunckAsOneString(parsedValue) +","+ headerTimestamp));
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new Exception(FordPassAprConstants.RECOG_TIERS_DATATYPE + FordPassAprConstants.PAYLOAD_SHAKEY_ERR_DETAILS);
                    }
                    // set the BO
                    setRecogTiersMappings(parsedValue,headerTimestamp);
                    // set the Rawpayload
                    setRawPayload(parsedValue);
                    // Finally -- set process states
                    resultStatus = FordPassAprHelper.setProcessState();

                    if (resultStatus) {
                        // set the process details
                        recogTiersBo.setProcess_status_details(FordPassAprHelper.setProcessDetails());
                        recogTiersBo.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_FAILED);
                    } else
                    {
                        recogTiersBo.setProcess_status_details(null);
                        recogTiersBo.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_SUCCESSFUL);
                    }
                    recogTiersBo.setProcess_status_date_time_utc(FordPassAprHelper.GetUTCdatetimeAsString());

                    // reset the Process State
                    FordPassAprHelper.resetProcessState();

                    return this.objectMapper.writeValueAsString(recogTiersBo);
                }
                else {
                    throw new Exception(FordPassAprConstants.RECOG_TIERS_DATATYPE + FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            /*if (e.getMessage() == null)
                throw new Exception(FordPassAprConstants.RECOG_TIERS_DATATYPE + FordPassAprConstants.PROCESSING_EXCEPTION);
            else
                throw new Exception(e.getMessage());*/
            return "empty";
        }

    }

    /**
     * Setting the Raw Payload
     *
     */
    private void setRawPayload(ArrayList<String> parsedValue) {

        StringBuffer sbf = new StringBuffer();
        sbf.append(parsedValue.get(0));
        for (int i = 1; i < parsedValue.size(); i++) {
            sbf.append(",").append(parsedValue.get(i));
        }
        recogTiersBo.setRaw_payload(sbf.toString());
    }

    private String chunckAsOneString(ArrayList<String> parsedValue) {

        StringBuffer sbf1 = new StringBuffer();
        sbf1.append(parsedValue.get(0));
        for (int i = 1; i < parsedValue.size(); i++) {
            sbf1.append(",").append(parsedValue.get(i));
        }
        return sbf1.toString();
    }

    /**
     * Setting the badgesBO
     *
     */
    private void setRecogTiersMappings(ArrayList<String> parsedValue, String headerTimestamp)
            throws Exception {

        final String METHOD_NAME = "setRecogTiersMappings";

        log.entering(CLASS_NAME, METHOD_NAME);

        recogTiersBo.setRgt_id(FordPassAprHelper.longParser(parsedValue.get(0), RGT_ID));
        recogTiersBo.setRgs_code(FordPassAprHelper.stringParser(parsedValue.get(1), RGS_CODE));
        recogTiersBo.setRgs_name(FordPassAprHelper.stringParser(parsedValue.get(2), RGS_NAME));
        recogTiersBo.setRgs_description(FordPassAprHelper.stringParser(parsedValue.get(3), RGS_DESCRIPTION));
        recogTiersBo.setRgs_domain(FordPassAprHelper.stringParser(parsedValue.get(4), RGS_DOMAIN));
        recogTiersBo.setRgt_code(FordPassAprHelper.stringParser(parsedValue.get(5), RGT_CODE));

        recogTiersBo.setRgt_name(FordPassAprHelper.stringParser(parsedValue.get(6), RGT_NAME));
        recogTiersBo.setRgt_priority(FordPassAprHelper.longParser(parsedValue.get(7), RGT_PRIORITY));
        recogTiersBo.setRgt_description(FordPassAprHelper.stringParser(parsedValue.get(8), RGT_DESCRIPTION));

        recogTiersBo.setRgt_audit_cd(FordPassAprHelper.parseDateTimestamp(parsedValue.get(9), RGT_AUDIT_CD));

        recogTiersBo.setRgt_audit_cu(FordPassAprHelper.longParser(parsedValue.get(10), RGT_AUDIT_CU));
        recogTiersBo.setRgt_audit_rd(FordPassAprHelper.parseDateTimestamp(parsedValue.get(11), RGT_AUDIT_RD));

        recogTiersBo.setRgt_audit_ru(FordPassAprHelper.longParser(parsedValue.get(12), RGT_AUDIT_RU));

        recogTiersBo.setPartition_year(FordPassAprHelper.intParser(FordPassAprHelper.findPartitionYear(parsedValue.get(9), PARTITION_YR), PARTITION_YR));
        recogTiersBo.setHeader_timestamp_utc(FordPassAprHelper.parseHeaderDateTimestamp(headerTimestamp,COLUMN_HEADER_TS));
    }


    /**
     * Validating the Mandatory fields
     *
     *
     */
    private void validateAllMandatoryFields(ArrayList<String> parsedValue)
            throws Exception {
        final String METHOD_NAME = "validateAllMandatoryFields";
        log.entering(CLASS_NAME, METHOD_NAME);

        if (parsedValue.get(0) == null
                && parsedValue.get(1) == null
                && parsedValue.get(4) == null
                && parsedValue.get(5) == null
                && parsedValue.get(7) == null
                && parsedValue.get(9) == null) {
            throw new Exception(FordPassAprConstants.RECOG_TIERS_DATATYPE + FordPassAprConstants.REQUIRED_FIELDS);
        }
    }

    /**
     * Validating the Mandatory fields Sets record to transfail if any of the
     * required field is empty
     *
     */
    private void validateMandatoryFields(ArrayList<String> parsedValue) {
        final String METHOD_NAME = "validateMandatoryFields";
        log.entering(CLASS_NAME, METHOD_NAME);

        FordPassAprHelper.validateMandatoryField(parsedValue.get(0), RGT_ID);
        FordPassAprHelper.validateMandatoryField(parsedValue.get(1), RGS_CODE);
        FordPassAprHelper.validateMandatoryField(parsedValue.get(4), RGS_DOMAIN);
        FordPassAprHelper.validateMandatoryField(parsedValue.get(5), RGT_CODE);
        FordPassAprHelper.validateMandatoryField(parsedValue.get(7), RGT_PRIORITY);
        FordPassAprHelper.validateMandatoryField(parsedValue.get(9), RGT_AUDIT_CD);

    }

    private boolean validateSkipRecords(ArrayList<String> parsedValue)
            throws Exception {
        boolean skipRecord = false;
        if (parsedValue.get(0) != null) {
            if (parsedValue.get(0).equalsIgnoreCase(FordPassAprConstants.APR_HEADER)
                    || parsedValue.get(0).equalsIgnoreCase(FordPassAprConstants.APR_TAIL)) {
                skipRecord = true;
            } else {
                skipRecord = false;
            }
        } else {
            if (parsedValue.size() == 13) {
                skipRecord = false;

            } else {
                throw new Exception(FordPassAprConstants.RECOG_TIERS_DATATYPE + FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);
            }
        }
        return skipRecord;
    }

    /**
     * Validating the Recognization_Domain fields with the default levels &
     * incoming values
     *
     * @param parsedValueLst
     * @throws Exception
     */
    private void validateRecognizationDomain(ArrayList<String> parsedValueLst)
            throws Exception {
        final String METHOD_NAME = "validateTransLevel";
        log.entering(CLASS_NAME, METHOD_NAME);

        if (!RGS_DOMAIN_VALUES.contains(parsedValueLst.get(4))) {
            throw new Exception(FordPassAprConstants.RECOG_TIERS_DATATYPE + FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);
        }

    }


    public static void main(String [] ar) throws Exception{


        java.util.Scanner scanner = new java.util.Scanner(System.in);

        String input = scanner.nextLine();

        //RecognitionTiersDecoder decoder = new RecognitionTiersDecoder("20190614003214");
        RecognitionTiersDecoder decoder = new RecognitionTiersDecoder();
        String output = decoder.decode(null,input);
        System.out.println(output);
    }


}

