    package com.ford.datafactory.batch.util;


    import com.ford.datafactory.batch.decoder.DictionaryItemDataDecoder;
    import org.apache.beam.sdk.io.FileIO;
    import org.apache.beam.sdk.transforms.DoFn;

    import java.io.BufferedReader;
    import java.io.InputStream;
    import java.io.InputStreamReader;
    import java.nio.channels.Channels;

    public class DictionaryItemTransformDoFunction extends DoFn<FileIO.ReadableFile , String> {

        @ProcessElement
        public void processElement(@Element FileIO.ReadableFile file, ProcessContext c) {
// We can now access the file and its metadata.

            try (InputStream stream = Channels.newInputStream(file.open())) {
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
                String line = null;

                while ((line = streamReader.readLine()) != null) {
                    // c.output(headerTimestamp+","+line);
                    String record = new DictionaryItemDataDecoder().decode(file.getMetadata().resourceId().getFilename().toString(),line);
                    if(null != record)
                        c.output(record);
                    //c.output(file.getMetadata().resourceId().getFilename().toString()+","+line);
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
