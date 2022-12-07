package com.ford.datafactory.batch.transform;

import com.ford.datafactory.batch.decoder.TransactionAttributesDataDecoder;
import com.ford.datafactory.batch.decoder.TransactionPointsBalancesDataDecoder;
import com.ford.datafactory.batch.util.FordPassAprHelper;
import org.apache.beam.sdk.io.FileIO;
import org.apache.beam.sdk.transforms.DoFn;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.Channels;
import java.util.logging.Logger;

public class TransactionAttributesTransformDoFn extends DoFn<FileIO.ReadableFile , String> {
    private static final Logger logger = Logger.getLogger(TransactionAttributesTransformDoFn.class.getName());

    @ProcessElement
    public void processElement(@Element FileIO.ReadableFile file, ProcessContext c) {
// We can now access the file and its metadata.
        String fileName = file.getMetadata().resourceId().getFilename().toString();
        String[] fileNameWithoutExt = fileName.toString().substring(0, fileName.lastIndexOf('.')).split("_");
        String headerTimestamp = fileNameWithoutExt[fileNameWithoutExt.length - 1];

        if (fileName.contains("TransactionAttributes") &&
                headerTimestamp != null && FordPassAprHelper
                .validateHeaderTimestamp(headerTimestamp)) {
            try (InputStream stream = Channels.newInputStream(file.open())) {
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
                String line = null;

                while ((line = streamReader.readLine()) != null) {
                    // c.output(headerTimestamp+","+line);
                    //logger.info("line: " + line);
                    String record = new TransactionAttributesDataDecoder().decode(headerTimestamp, line);
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
}

