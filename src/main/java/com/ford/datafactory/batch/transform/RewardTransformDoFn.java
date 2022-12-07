package com.ford.datafactory.batch.transform;

import com.ford.datafactory.batch.decoder.RewardDataDecoder;
import org.apache.beam.sdk.io.FileIO;
import org.apache.beam.sdk.transforms.DoFn;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.Channels;
import java.util.logging.Logger;

public class RewardTransformDoFn extends DoFn<FileIO.ReadableFile , String> {
    private static final Logger logger = Logger.getLogger(RewardTransformDoFn.class.getName());
    @ProcessElement
    public void processElement(@Element FileIO.ReadableFile file, ProcessContext c) {
// We can now access the file and its metadata.

        try (InputStream stream = Channels.newInputStream(file.open())) {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            String line = null;

            while ((line = streamReader.readLine()) != null) {
                // c.output(headerTimestamp+","+line);
                //logger.info("line: " + line);
                String record = new RewardDataDecoder().decode(file.getMetadata().resourceId().getFilename().toString(),line);
                if(null != record) {
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

