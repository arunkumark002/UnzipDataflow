import org.apache.beam.sdk.transforms.SimpleFunction;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class SSPChargeBalanceTransformation1 extends SimpleFunction<String, String> {

    ArrayList<Map<String, String>> outputArrayLst = new ArrayList<Map<String, String>>();
    Map<String, String> outputMap = new HashMap<String, String>();
    String errors ="";

    @Override
    public String apply(String input) {

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<String> out = new ArrayList<String>();
        StringBuffer buffer = new StringBuffer();
        outputArrayLst = new ArrayList<Map<String, String>>();
        outputMap = new HashMap<String, String>();
        ArrayList<Map<String, String>> json = new ArrayList<Map<String, String>>();
        if (null != input) {
            String record = input.toString().trim();
            if (null != record) {
                try {
                    JsonNode commonJSONNode = null;
                    JsonNode commonJSONData = objectMapper.readTree(record);
                    JsonNode embeddedNode = commonJSONData.get("_embedded");
                    Iterator<Map.Entry<String, JsonNode>> nodes = embeddedNode.getFields();
                    while (nodes.hasNext()) {
                        Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) nodes.next();
                        commonJSONNode = entry.getValue();
                        break;
                    }
                    if (null != commonJSONNode
                            && !commonJSONNode.toString().equals("[]")
                            && !commonJSONNode.toString().replace("\"", "").equals("")) {
                        Iterator<JsonNode> mpJsonNode = commonJSONNode.getElements();
                        while (mpJsonNode.hasNext()) {
                            JsonNode entry = mpJsonNode.next();
                            try {
                                json = this.decode(entry.toString(), record);
                            } catch (final Exception e) {
                                e.printStackTrace();                            }
                        }
                    }
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        for(Map<String, String> map : json){
            String transformedData = "";
            for (String value : map.values())  {
                transformedData = transformedData + value +"|";
            }
            buffer.append(transformedData+"CVDPPRD"+"\n");
        }
        System.out.println("out size : "+"..."+buffer.length());
        return buffer.toString();
    }

    public ArrayList<Map<String, String>> decode(final String line, String inputRecord) throws Exception {

        final String METHOD_NAME = "decode";
        try {
            outputMap = new HashMap<String, String>();
            JSONParser jParser = new JSONParser();
            final JSONObject chargeBalanceData = (JSONObject)jParser.parse(line);
            //Validate Mandatory Fields
            validateAllMandatoryFields(chargeBalanceData);

            //Set Value Charge Balance Bo
            errors = setChargeBalanceBO(chargeBalanceData,errors);

            //setRawPayload(chargeBalancedData);
            try {
                outputMap.put("rawpayload", inputRecord);
            }catch (Exception e){
                errors = errors + " "+"[Raw_Payload = "+" ::errormsg :: "+e.getMessage()+"]";
            }

            // RAW SHA key generation
            try {
                if (chargeBalanceData != null) {
                    outputMap.put("Input_record_sha_key",calculateSHA256(inputRecord));
                }
            } catch (Exception e) {
                errors = errors + " " + "[InputRecordShakey = " + " ::errormsg :: " + e.getMessage() + "]";
            }
            // SHA key generation
            try {
                if (chargeBalanceData != null) {
                    outputMap.put("Sha_key",calculateSHA256(line));
                }
            } catch (Exception e) {
                errors = errors + " "+"[Shakey = "+" ::errormsg :: "+e.getMessage()+"]";
            }

            // Set partition field
            try {
                outputMap.put("Partition_date",((SSPHelper.transformToPartitionDateFormat(outputMap.get("Create_timestamp"), "yyyy-MM-dd"))));

            }catch (Exception e){
                errors = errors + " "+"[Partition_Date = "+" ::errormsg :: "+e.getMessage()+"]";
            }

            if (errors != null && !errors.equals("")) {
                outputMap.put("Process_status_details",errors);
                outputMap.put("Process_status","TransFail");
            } else {
                outputMap.put("Process_status_details","");
                outputMap.put("Process_status","TransGood");
            }
            outputMap.put("Process_status_date_time_utc",SSPHelper.GetUTCdatetimeAsString());
            outputArrayLst.add(outputMap);
        } catch (Exception e) {
            System.out.println("error : "+e.getMessage());
            if (e.getMessage() == null)
                throw new Exception(SSPConstants.CHRAGE_BALANCE_B2C_DATATYPE
                        + SSPConstants.PROCESSING_EXCEPTION);
            else
                throw new Exception(e.getMessage());
        }
        return outputArrayLst;
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
                outputMap.put("Id", chargeBalanceData.get("id").toString().replace("\"", ""));
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
                    outputMap.put("Expiration_date",expDate);
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
                outputMap.put("Customer_id", chargeBalanceData.get("customerId").toString().replace("\"", ""));
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
                outputMap.put("Vin", chargeBalanceData.get("vin").toString().replace("\"", ""));
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
                outputMap.put("Current_balance", chargeBalanceData.get("currentBalance").toString().replace("\"", ""));
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
                outputMap.put("Total_balance", chargeBalanceData.get("totalBalance").toString().replace("\"", ""));
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
                outputMap.put("Subscription_status", chargeBalanceData.get("subscriptionStatus").toString().replace("\"", ""));
            }
        }catch (Exception e){
            errors = errors + " "+"[subscriptionStatus = "+chargeBalanceData.get("subscriptionStatus")+" ::errormsg :: "+e.getMessage()+"]";
        }

        try {
            if (chargeBalanceData.get("country") != null
                    && !chargeBalanceData.get("country").equals("null")
                    && !chargeBalanceData.get("country").toString().replace("\"", "").isEmpty()) {
                outputMap.put("Country", chargeBalanceData.get("country").toString().replace("\"", ""));
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
                outputMap.put("Create_timestamp",(SSPHelper.transformToCalendarYYYYMMDDTHHMMSSxxxxxxxZ(
                        chargeBalanceData.get("createTS").toString().replace("\"", ""),"Create TimeStamp")));
            }
        }catch (Exception e){
            errors = errors + " "+"[createTS = "+chargeBalanceData.get("createTS")+" ::errormsg :: "+e.getMessage()+"]";
        }

        try {
            if (chargeBalanceData.get("updateTS") != null
                    && !chargeBalanceData.get("updateTS").equals("null")
                    && !chargeBalanceData.get("updateTS").toString().replace("\"", "").isEmpty()) {
                outputMap.put("Update_timestamp",(SSPHelper.transformToCalendarYYYYMMDDTHHMMSSxxxxxxxZ(
                        chargeBalanceData.get("updateTS").toString().replace("\"", ""),"Update TimeStamp")));
            }
        }catch (Exception e){
            errors = errors + " "+"[updateTS = "+chargeBalanceData.get("updateTS")+" ::errormsg :: "+e.getMessage()+"]";
        }

        return errors;
    }

    public static String calculateSHA256(String stringToBeHashed) throws NoSuchAlgorithmException {
        if (stringToBeHashed == null) {
            return null;
        } else {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(stringToBeHashed.getBytes());
            return convertToHex(messageDigest.digest());
        }
    }

    public static String convertToHex(byte[] data) {
        StringBuffer hexStringBuffer = new StringBuffer();

        for(int i = 0; i < data.length; ++i) {
            int halfbyte = data[i] >>> 4 & 15;
            int var4 = 0;

            do {
                if (0 <= halfbyte && halfbyte <= 9) {
                    hexStringBuffer.append((char)(48 + halfbyte));
                } else {
                    hexStringBuffer.append((char)(97 + (halfbyte - 10)));
                }

                halfbyte = data[i] & 15;
            } while(var4++ < 1);
        }

        return hexStringBuffer.toString();
    }
}