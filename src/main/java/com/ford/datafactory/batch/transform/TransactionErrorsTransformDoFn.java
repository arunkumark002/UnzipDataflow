package com.ford.datafactory.batch.transform;

import com.ford.datafactory.batch.decoder.TransactionErrorsDataDecoder;
import com.ford.datafactory.batch.decoder.TransactionRuleDecoder;
import com.ford.datafactory.batch.util.FordPassAprConstants;
import com.ford.datafactory.batch.util.FordPassAprHelper;
import org.apache.beam.sdk.io.FileIO;
import org.apache.beam.sdk.transforms.DoFn;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.Channels;
import java.util.logging.Logger;

public class TransactionErrorsTransformDoFn extends DoFn<FileIO.ReadableFile , String> {
    private static final Logger logger = Logger.getLogger(TransactionErrorsTransformDoFn.class.getName());
    @ProcessElement
    public void processElement(@Element FileIO.ReadableFile file, ProcessContext c) {
// We can now access the file and its metadata.
        String fileName = file.getMetadata().resourceId().getFilename().toString();
        String[] fileNameWithoutExt = fileName.toString().substring(0,fileName.lastIndexOf('.')).split("_");
        String headerTimestamp = fileNameWithoutExt[fileNameWithoutExt.length - 1];
        String countryCode = getCountryCode(file.getMetadata().resourceId().getFilename().toString());

        if(fileName.contains("TransactionErrors") &&
                headerTimestamp != null && FordPassAprHelper
                .validateHeaderTimestamp(headerTimestamp) && countryCode != null ) {
            try (InputStream stream = Channels.newInputStream(file.open())) {
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
                String line = null;

                while ((line = streamReader.readLine()) != null) {
                    // c.output(headerTimestamp+","+line);
                    //logger.info("line: " + line);
                    String record = new TransactionErrorsDataDecoder().decode(headerTimestamp, line);
                    if (null != record) {
                        //logger.info("record: " + record);
                        c.output(record);
                    }
                    //c.output(file.getMetadata().resourceId().getFilename().toString()+","+line);
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String getCountryCode(String fileName){
        String value=null;
        if(fileName!=null){

            if(fileName.contains(FordPassAprConstants.NA_EU_FILE_PATTERN)){
                if(fileName.contains(FordPassAprConstants.CHN_FILE_PATTERN)){
                    value=FordPassAprConstants.CHN_VALUE;
                }
                else{
                    value=FordPassAprConstants.NA_EU_VALUE;
                }
            }

        }
        return value;
    }
}

