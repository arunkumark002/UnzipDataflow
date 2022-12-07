
import com.ford.datafactory.batch.transform.*;
import com.ford.datafactory.batch.util.DictionaryItemTransformDoFunction;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.FileIO;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.values.PCollection;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class text_text_local {
    static String str = "src/main/resources/output";
    /*private static final TupleTag<FailsafeElement<String, String>> VERIFY_COLUMN_COUNT_DEADLETTER_OUT =
            new TupleTag<FailsafeElement<String, String>>() {
            };*/
    static  int count =0;
    public static void main(String[] args) throws com.fasterxml.jackson.core.JsonProcessingException {
        // Start by defining the options for the pipeline.
//        PipelineOptions options = PipelineOptionsFactory.create();

        // takes in arguments from command line.
//      PipelineOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().create();

        /*String data = "{\"insertId\":\"1am96gdc4kr\",\"labels\":{\"dataflow.googleapis.com/job_id\":\"2022-09-08_08_58_37-4369315246414288431\",\"dataflow.googleapis.com/job_name\":\"ssp-ing-nscvtefsspchgbalb2ctb-f839676e7fa846079643416473ad8da9\",\"dataflow.googleapis.com/log_type\":\"system\",\"dataflow.googleapis.com/region\":\"us-central1\"},\"logName\":\"projects/prj-dfdl-73-ssp-d-0073/logs/dataflow.googleapis.com%2Fjob-message\",\"receiveTimestamp\":\"2022-09-08T16:01:49.14448264Z\",\"resource\":{\"labels\":{\"job_id\":\"2022-09-08_08_58_37-4369315246414288431\",\"job_name\":\"ssp-ing-nscvtefsspchgbalb2ctb-f839676e7fa846079643416473ad8da9\",\"project_id\":\"471167364752\",\"region\":\"us-central1\",\"step_id\":\"\"},\"type\":\"dataflow_step\"},\"severity\":\"DEBUG\",\"textPayload\":\"Executing success step success22\",\"timestamp\":\"2022-09-08T16:01:48.601470848Z\"}";
        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        com.fasterxml.jackson.databind.JsonNode jsonNode = mapper.readTree(data);
        com.fasterxml.jackson.databind.JsonNode resource = jsonNode.get("resource");
        com.fasterxml.jackson.databind.JsonNode label = resource.get("labels");
        com.fasterxml.jackson.databind.JsonNode job_name = label.get("job_name");
        System.out.println("job_name ... "+job_name);
        GCSLogMessage gcsLogMessage = mapper.readValue(data, GCSLogMessage.class);
        String objectName = gcsLogMessage.name;
        String sourceBucketName = gcsLogMessage.bucket;
        String trigger_file[] = gcsLogMessage.name.split("\\.");
        String jobName = trigger_file[0].split("-")[0];
        String loadDate = trigger_file[0].split("-")[1];*/

        /*final List<String> LINES = Arrays.asList(
                "To be, or not to be: that is the question: ",
                "Whether 'tis nobler in the mind to suffer ",
                "The slings and arrows of outrageous fortune, ",
                "Or to take arms against a sea of troubles, ");*/

        /*PipelineOptionsFactory.register(MyOptions.class);
        MyOptions options = PipelineOptionsFactory.fromArgs(args)
                .withValidation()
                .as(MyOptions.class);

        // Then create the pipeline.
        Pipeline p = Pipeline.create(options);

        // Create the PCollection 'lines' by applying a 'Read' transform.
        PCollection<String> lines = p.apply("ReadMyFile", TextIO.read().from(options.getInput()));
        PCollection<String> incomeRecords = lines.apply("SSP CB Transformation", MapElements.via(new SSPChargeBalanceTransformationJSON()));
        PCollection<String> transformedRecords = incomeRecords.apply("Remove Header & Footer",Filter.by(new removeEmptyLine()));

        PCollection<String> output = transformedRecords.apply("Move data files to archive path",
                ParDo.of(
                        new DoFn<String, String>() {
                            // Define the DoFn that logs the ValueProvider value.
                            @ProcessElement
                            public void process(ProcessContext c) {

                                System.out.println("c.element() ::"+c.element());

                                if(count ==0){
                                    System.out.println("count .. "+count++);
                                }
                                c.output(c.element());
                            }
                        }));
        output.apply("SSP CB Transformation", MapElements.via(
                new SimpleFunction<String, String>() {
                    @Override
                    public String apply(String line) {
                        System.out.println("line :: "+line);
                        return line;
                    }
                }));

        // incomeRecords.setCoder(NullableCoder.of(StringUtf8Coder.of()));
       // PCollection<String> output = incomeRecords.apply(ToString.iterables());

        //PCollection<String> output =   output.apply(Regex.replaceAll(",", "\n"));
        // Apply Create, passing the list and the coder, to create the PCollection.
//        PCollection<String> mem_lines = p.apply("ReadMyMemory", Create.of(LINES)).setCoder(StringUtf8Coder.of());

        output.apply(TextIO.write().to(str));*/

        PipelineOptionsFactory.register(MyOptions.class);
        MyOptions options = PipelineOptionsFactory.fromArgs(args)
                .withValidation()
                .as(MyOptions.class);

        // Then create the pipeline.
        //Pipeline p = Pipeline.create(options);


        // Create the PCollection 'lines' by applying a 'Read' transform.
        /*PCollection<String> lines = p.apply("ReadMyFile", TextIO.read().from(options.getInput()));
        PCollection<String> transformedRecords = lines.apply("Transformation", MapElements.via(new DictionaryItemTransformFunction()));
        System.out.println("transformedRecords-->"+transformedRecords.toString());*/
       // transformedRecords.setCoder(NullableCoder.of(StringUtf8Coder.of()));
        //transformedRecords.apply(TextIO.write().to(str));
        //writing into single output file "withNumShards(no of files)" or withoutSharding()
        //transformedRecords.apply(TextIO.write().withNumShards(1).to(options.getOutput()));
        //transformedRecords.apply(TextIO.write().to("src/main/resources/output.txt"));

        ////Another type
        //multiplePipelines(options);
       // runRewardsDataPipeline(options);
       // runPipeline(options, "src/main/resources/CLM_FORD_QA_DWH_RecognitionTiers_0000001394_20220909004000.txt",new RecognitionTiersTransformDoFn(),"src/main/resources/OutRecognitionTiers.txt");
       // runPipeline(options, "src/main/resources/CLM_FORD_QA_DWH_TransactionRule_0000001394_20220909003000.txt",new TransactionRuleTransformDoFn(),"src/main/resources/OutTransactionRule.txt");
        //runPipeline(options, "src/main/resources/CLM_FORD_QA_DWH_TransactionPromotionCounters_0000001394_20220909003200.txt",new TransactionPromotionCountersTransformDoFn(),"src/main/resources/OutTransactionPromotionCounters.txt");
        //runPipeline(options, "src/main/resources/CLM_FORD_QA_DWH_TransactionPointsBalances_0000001394_20220909003200.txt",new TransactionPointsBalancesTransformDoFn(),"src/main/resources/OutTransactionPointsBalances.txt");
        //runPipeline(options, "src/main/resources/CLM_FORD_QA_DWH_TransactionPointsAggregate_0000001394_20220909003200.txt",new TransactionPointsAggregateTransformDoFn(),"src/main/resources/OutTransactionPointsAggregate.txt");
        //runPipeline(options, "src/main/resources/CLM_FORD_QA_DWH_TransactionErrors_0000001394_20220909003200.txt",new TransactionErrorsTransformDoFn(),"src/main/resources/OutTransactionErrors.txt");
//        runPipeline(options, "src/main/resources/CLM_FORD_QA_DWH_TransactionBasketItemRedemption_0000001394_20220909003200.txt",new TransactionBasketItemRedemptionTransformDoFn(),"src/main/resources/OutTransactionBasketItemRedemption.txt");
        runPipeline(options, "src/main/resources/CLM_FORD_QA_DWH_TransactionAttributes_0000001394_20220909003200.txt",new TransactionAttributesTransformDoFn(),"src/main/resources/OutTransactionAttributes.txt");


        //Another type2

        /*File[] files = new File(options.getInput()).listFiles();
        PCollectionList<String> dataSet=null;
        for (File f: files) {
            String fileName = f.getName();
            PCollection<String> newPCollection = p.apply(FileIO.match().filepattern("*.txt"))
                    // The withCompression method is optional. By default, the Beam SDK detects compression from
                    // the filename.
                    .apply(FileIO.readMatches())
                    .apply(
                            ParDo.of(
                                    new DoFn<FileIO.ReadableFile, String>() {
                                        @ProcessElement
                                        public void process(@Element FileIO.ReadableFile file, ProcessContext c) throws IOException {
                                            // We can now access the file and its metadata.
                                            //String fileName = file.getMetadata().resourceId().getFilename();
                                            try (InputStream stream = Channels.newInputStream(file.open())) {
                                                BufferedReader streamReader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
                                                StringBuilder dataBuilder = new StringBuilder();
                                                String line = null;

                                                while ((line = streamReader.readLine()) != null) {

                                                    //String headerTimestamp = fileName.substring(0,fileName.lastIndexOf('.')).split("_")[1];
                                                    // c.output(headerTimestamp+","+line);
                                                    String record = new DictionaryItemDataDecoder().decode(file.getMetadata().resourceId().getFilename(),line);
                                                    if(null != record)
                                                        c.output(record);
                                                    //c.output(file.getMetadata().resourceId().getFilename().toString()+","+line);
                                                }

                                            } catch (Exception e) {
                                                throw new RuntimeException(e);
                                            }

                                        }
                                    }));

            if (dataSet==null) {
                dataSet=PCollectionList.of(newPCollection);
            } else {
                dataSet=dataSet.and(newPCollection);
            }
        }
        PCollection<String> completeDataset= dataSet.apply(Flatten.<String>pCollections());
        completeDataset.apply(TextIO.write().withoutSharding().to("src/main/resources/output.txt"));*/







        //p.run().waitUntilFinish();
        //p1.run().waitUntilFinish();

    }

    private static void multiplePipelines(MyOptions options) {
        Pipeline p = Pipeline.create(options);
        PCollection<String> linesForPipelineOne = p.apply(FileIO.match().filepattern(options.getInput()))
                // The withCompression method is optional. By default, the Beam SDK detects compression from
                // the filename.
                .apply(FileIO.readMatches())
                .apply(ParDo.of(new DictionaryItemTransformDoFunction()));
        //.apply(ParDo.of(new DictionaryItemTransformDoFunction()))
        linesForPipelineOne.apply(TextIO.write().withoutSharding().to("src/main/resources/output.txt"));

        Pipeline p1 = Pipeline.create(options);
        PCollection<String> linesForPipelineTwo = p1.apply(FileIO.match().filepattern(options.getInput()))
                // The withCompression method is optional. By default, the Beam SDK detects compression from
                // the filename.
                .apply(FileIO.readMatches())
                .apply(ParDo.of(new DictionaryItemTransformDoFunction()));
        //.apply(ParDo.of(new DictionaryItemTransformDoFunction()))
        linesForPipelineTwo.apply(TextIO.write().withoutSharding().to("src/main/resources/output1.txt"));
        p.run().waitUntilFinish();
        p1.run().waitUntilFinish();
    }

    private static void runRewardsDataPipeline(MyOptions options){
        Pipeline p = Pipeline.create(options);

         p.apply(FileIO.match().filepattern("src/main/resources/CLM_FORD_QA_DWH_Reward_0000001394_20220908203000.txt"))
                .apply(FileIO.readMatches())
                .apply(ParDo.of(new RewardTransformDoFn()))
        .apply(TextIO.write().withoutSharding().to("src/main/resources/rewardsData.txt"));
         p.run().waitUntilFinish();

    }

    private static void runPipeline(MyOptions options, String input, DoFn<FileIO.ReadableFile , String> doFn, String output){
        Pipeline p = Pipeline.create(options);

        p.apply(FileIO.match().filepattern(input))
                .apply(FileIO.readMatches())
                .apply(ParDo.of(doFn))
                .apply(TextIO.write().withoutSharding().to(output));
        p.run().waitUntilFinish();

    }
}

class removeEmptyLine implements SerializableFunction<String,Boolean>{

    @Override
    public Boolean apply(String input) {
        // TODO Auto-generated method stub
        return input.length()>0;
    }

}

class SSPChargeBalanceTransformation extends SimpleFunction<String, String> {

    ArrayList<Map<String, String>> outputArrayLst = new ArrayList<Map<String, String>>();
    Map<String, String> outputMap = new HashMap<String, String>();
    boolean isFlag = false;
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
                                //System.out.println("line : "+entry.toString());
                                json = this.decode(entry.toString(), record);
                                //System.out.println("json : "+json);
                            } catch (final Exception e) {
                                //mutlipleOutputs.write("Bad", NullWritable.get(), FormatUtil.formatExceptionMessage(entry.toString(), inputFileName, "Error in Decode : "+e.getMessage()+" "+this.decoder+" "+this.decoderClass+ " "+this.dynamicDecoderClassAndSource),
                                //        this.badRecordFileName);
                            }
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
                transformedData =  value +"|";
            }
            buffer.append(transformedData+"CVDPPRD"+"\n");
        }
        /*String newLine = "";
        for(Map<String, String> map : json){
            String transformedData = "";
            if(!transformedData.equals(""))
                transformedData = transformedData +"\n";
            for (String value : map.values())  {
                transformedData = transformedData + value +"|";
            }
            newLine = transformedData;
        }
        System.out.println("newLine : "+newLine);
*/
        /*for (Map.Entry<String, String> set :  outputMap.entrySet()) {

            // Printing all elements of a Map
            System.out.println("Key : "+set.getKey() + " = " +" Value : "+ set.getValue());
            transformedData = transformedData + set.getValue().trim();
        }
        for (String url : outputMap.values())  {
                transformedData = transformedData + url.trim();
                //System.out.println(transformedData+"..."+transformedData);
        }*/
        /*for (String output :out){
            System.out.println("final output : "+output);
        }*/
        System.out.println("out size : "+out.size()+"..."+buffer.length());
        return buffer.toString();
    }

    public ArrayList<Map<String, String>> decode(final String line, String inputRecord) throws Exception {

        final String METHOD_NAME = "decode";


        try {
            outputMap = new HashMap<String, String>();
            JSONParser jParser = new JSONParser();
            final JSONObject chargeBalanceData = (JSONObject)jParser.parse(line);
            //System.out.println("chargeBalanceData : "+chargeBalanceData);
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

            /*for (Map.Entry<String, String> set :  outputMap.entrySet()) {

                // Printing all elements of a Map
                System.out.println("Key : "+set.getKey() + " = " +" Value : "+ set.getValue());
                transformedData = transformedData + set.getValue().trim();
            }*/

            /*for (String url : outputMap.values())  {
                transformedData = transformedData + url.trim();
                //System.out.println(transformedData+"..."+transformedData);
            }

            System.out.println("transformedData success : "+transformedData);
*/
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
