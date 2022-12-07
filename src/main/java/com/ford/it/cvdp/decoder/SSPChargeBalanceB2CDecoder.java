package com.ford.it.cvdp.decoder;

import com.ford.it.cvdp.json.SSPChargeBalanceB2CBO;
import com.ford.it.cvdp.process.SSPIDecoder;
import com.ford.it.cvdp.util.SSPConstants;
import com.ford.it.cvdp.util.SSPHelper;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class SSPChargeBalanceB2CDecoder implements SSPIDecoder {

    private static final String CLASS_NAME = SSPChargeBalanceB2CDecoder.class.getName();
    ObjectMapper objectMapper = new ObjectMapper();
    SSPChargeBalanceB2CBO chargeBalanceBO;
    String processStatusDetails = "";

    public String decode(final String line, String inputRecord) throws Exception {

        final String METHOD_NAME = "decode";
        processStatusDetails = "";
        chargeBalanceBO = new SSPChargeBalanceB2CBO();
        String errors ="";

        try {
            JSONParser jParser = new JSONParser();
            final JSONObject chargeBalanceData = (JSONObject)jParser.parse(line);

            //Validate Mandatory Fields
           // validateAllMandatoryFields(chargeBalanceData);

            //Set Value Charge Balance Bo
            errors = setChargeBalanceBO(chargeBalanceData,errors);

            //setRawPayload(chargeBalancedData);
            try {
                chargeBalanceBO.setRaw_payload(inputRecord);
            }catch (Exception e){
                errors = errors + " "+"[Raw_Payload = "+" ::errormsg :: "+e.getMessage()+"]";
            }

			// RAW SHA key generation
			try {
				if (chargeBalanceData != null) {
					chargeBalanceBO.setInput_record_sha_key(SSPHelper.calculateSHA256(inputRecord));
				}
			} catch (Exception e) {
				errors = errors + " " + "[InputRecordShakey = " + " ::errormsg :: " + e.getMessage() + "]";
			}
            // SHA key generation
            try {
                if (chargeBalanceData != null) {
                    chargeBalanceBO.setSha_key(SSPHelper.calculateSHA256(line));
                }
            } catch (Exception e) {
                errors = errors + " "+"[Shakey = "+" ::errormsg :: "+e.getMessage()+"]";
            }

            // Set partition field
            try {
                chargeBalanceBO.setPartition_date((SSPHelper.transformToPartitionDateFormat(chargeBalanceBO.getCreate_timestamp(), "yyyy-MM-dd")));

            }catch (Exception e){
                errors = errors + " "+"[Partition_Date = "+" ::errormsg :: "+e.getMessage()+"]";
            }

            if (errors != null && !errors.equals("")) {
                chargeBalanceBO.setProcess_status_details(errors);
                chargeBalanceBO.setProcess_status(SSPConstants.FILE_STATUS_TRANSFORMATION_FAILED);
            } else {
                chargeBalanceBO.setProcess_status_details(null);
                chargeBalanceBO.setProcess_status(SSPConstants.FILE_STATUS_TRANSFORMATION_SUCCESSFUL);
            }
            chargeBalanceBO.setProcess_status_date_time_utc(SSPHelper.GetUTCdatetimeAsString());

            return this.objectMapper.writeValueAsString(chargeBalanceBO);

        } catch (Exception e) {
            if (e.getMessage() == null)
                throw new Exception(SSPConstants.CHRAGE_BALANCE_B2C_DATATYPE
                        + SSPConstants.PROCESSING_EXCEPTION);
            else
                throw new Exception(e.getMessage());
        }
    }

    private void validateAllMandatoryFields(JSONObject chargeBalanceData) throws Exception{
        final String METHOD_NAME = "validateAllMandatoryFields";

        if (chargeBalanceData.get("id") == null
                || chargeBalanceData.get("id").toString().equals("null")
                || chargeBalanceData.get("id").toString().isEmpty()

        ) throw new Exception(SSPConstants.CHRAGE_BALANCE_B2C_DATATYPE + SSPConstants.REQUIRED_FIELDS);
    }


    /**
     * Setting the ChargeBalanceB2CBo
     *
     * @param chargeBalanceData
     */
    private String setChargeBalanceBO(JSONObject chargeBalanceData, String errors) {
        final String METHOD_NAME = "setChargeBalanceBO";

        try {
            if (chargeBalanceData.get("id") != null) {
                chargeBalanceBO.setId(Integer.parseInt(
                        chargeBalanceData.get("id").toString().replace("\"", "")));
            }
        }catch (Exception e){
            errors = errors + " "+"[id = "+chargeBalanceData.get("id")+" ::errormsg :: "+e.getMessage()+"]";
        }

        try {
            if (chargeBalanceData.get("expirationDate") != null
                    && !chargeBalanceData.get("expirationDate").equals("null")
                    && !chargeBalanceData.get("expirationDate").toString().replace("\"", "").isEmpty()) {
                String expDate = chargeBalanceData.get("expirationDate").toString().replace("\"", "");
                Boolean validDate = SSPHelper.isValid(expDate,"yyyy-MM-dd");
                if(validDate) {
                    chargeBalanceBO.setExpiration_date(expDate);
                }else{
                    errors = errors + " "+"[expirationDate = "+chargeBalanceData.get("expirationDate")+" ::errormsg :: "+"Expiration Date is invalid format"+"]";
                }
            }
        }catch (Exception e){
            errors = errors + " "+"[expirationDate = "+chargeBalanceData.get("expirationDate")+" ::errormsg :: "+e.getMessage()+"]";
        }

        try {
            if (chargeBalanceData.get("customerId") != null
                    && !chargeBalanceData.get("customerId").equals("null")
                    && !chargeBalanceData.get("customerId").toString().replace("\"", "").isEmpty()) {
                chargeBalanceBO.setCustomer_id(
                        chargeBalanceData.get("customerId").toString().replace("\"", ""));
            }else{
                errors = errors + " "+"[customerId = "+chargeBalanceData.get("customerId")+" ::errormsg :: "+"Customer id  is null  or empty"+"]";
            }
        }catch (Exception e){
            errors = errors + " "+"[customerId = "+chargeBalanceData.get("customerId")+" ::errormsg :: "+e.getMessage()+"]";
        }

        try {
            if (chargeBalanceData.get("vin") != null
                    && !chargeBalanceData.get("vin").equals("null")
                    && !chargeBalanceData.get("vin").toString().replace("\"", "").isEmpty()) {
                chargeBalanceBO.setVin(
                        chargeBalanceData.get("vin").toString().replace("\"", ""));
            }else{
                errors = errors + " "+"[vin = "+chargeBalanceData.get("vin")+" ::errormsg :: "+"vin id  is null  or empty"+"]";
            }
        }catch (Exception e){
            errors = errors + " "+"[vin = "+chargeBalanceData.get("vin")+" ::errormsg :: "+e.getMessage()+"]";
        }

        try {
            if (chargeBalanceData.get("currentBalance") != null
                    && !chargeBalanceData.get("currentBalance").equals("null")
                    && !chargeBalanceData.get("currentBalance").toString().replace("\"", "").isEmpty()) {
                chargeBalanceBO.setCurrent_balance(Float.parseFloat(
                        chargeBalanceData.get("currentBalance").toString().replace("\"", "")));
            }else{
                errors = errors + " "+"[currentBalance = "+chargeBalanceData.get("currentBalance")+" ::errormsg :: "+"currentBalance is null"+"]";
            }
        }catch (Exception e){
            errors = errors + " "+"[currentBalance = "+chargeBalanceData.get("currentBalance")+" ::errormsg :: "+e.getMessage()+"]";
        }

        try {
            if (chargeBalanceData.get("totalBalance") != null
                    && !chargeBalanceData.get("totalBalance").equals("null")
                    && !chargeBalanceData.get("totalBalance").toString().replace("\"", "").isEmpty()) {
                chargeBalanceBO.setTotal_balance(Float.parseFloat(
                        chargeBalanceData.get("totalBalance").toString().replace("\"", "")));
            }else{
                errors = errors + " "+"[totalBalance = "+chargeBalanceData.get("totalBalance")+" ::errormsg :: "+"totalBalance Id is null"+"]";
            }
        }catch (Exception e){
            errors = errors + " "+"[totalBalance = "+chargeBalanceData.get("totalBalance")+" ::errormsg :: "+e.getMessage()+"]";
        }

        try {
            if (chargeBalanceData.get("subscriptionStatus") != null
                    && !chargeBalanceData.get("subscriptionStatus").equals("null")
                    && !chargeBalanceData.get("subscriptionStatus").toString().replace("\"", "").isEmpty()) {
                chargeBalanceBO.setSubscription_status(
                        chargeBalanceData.get("subscriptionStatus").toString().replace("\"", ""));
            }
        }catch (Exception e){
            errors = errors + " "+"[subscriptionStatus = "+chargeBalanceData.get("subscriptionStatus")+" ::errormsg :: "+e.getMessage()+"]";
        }

        try {
            if (chargeBalanceData.get("country") != null
                    && !chargeBalanceData.get("country").equals("null")
                    && !chargeBalanceData.get("country").toString().replace("\"", "").isEmpty()) {
                chargeBalanceBO.setCountry(
                        chargeBalanceData.get("country").toString().replace("\"", ""));
            }else{
                errors = errors + " "+"[country = "+chargeBalanceData.get("country")+" ::errormsg :: "+"country is null"+"]";
            }
        }catch (Exception e){
            errors = errors + " "+"[country = "+chargeBalanceData.get("country")+" ::errormsg :: "+e.getMessage()+"]";
        }

        try {
            if (chargeBalanceData.get("createTS") != null
                    && !chargeBalanceData.get("createTS").equals("null")
                    && !chargeBalanceData.get("createTS").toString().replace("\"", "").isEmpty()) {
                chargeBalanceBO.setCreate_timestamp(SSPHelper.transformToCalendarYYYYMMDDTHHMMSSxxxxxxxZ(
                        chargeBalanceData.get("createTS").toString().replace("\"", ""),"Create TimeStamp"));
            }
        }catch (Exception e){
            errors = errors + " "+"[createTS = "+chargeBalanceData.get("createTS")+" ::errormsg :: "+e.getMessage()+"]";
        }

        try {
            if (chargeBalanceData.get("updateTS") != null
                    && !chargeBalanceData.get("updateTS").equals("null")
                    && !chargeBalanceData.get("updateTS").toString().replace("\"", "").isEmpty()) {
                chargeBalanceBO.setUpdate_timestamp(SSPHelper.transformToCalendarYYYYMMDDTHHMMSSxxxxxxxZ(
                        chargeBalanceData.get("updateTS").toString().replace("\"", ""),"Update TimeStamp"));
            }
        }catch (Exception e){
            errors = errors + " "+"[updateTS = "+chargeBalanceData.get("updateTS")+" ::errormsg :: "+e.getMessage()+"]";
        }

        return errors;
    }

}