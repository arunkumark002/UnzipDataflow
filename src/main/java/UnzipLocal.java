import com.ford.datafactory.batch.util.StorageUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.Compression;
import org.apache.beam.sdk.io.FileIO;
import org.apache.beam.sdk.io.FileSystems;
import org.apache.beam.sdk.io.fs.MoveOptions;
import org.apache.beam.sdk.io.fs.ResolveOptions;
import org.apache.beam.sdk.io.fs.ResourceId;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.util.MimeTypes;


import java.io.*;
import java.nio.channels.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipLocal {



    public static void main(String[] args) {
        PipelineOptionsFactory.register(UnzipOptions.class);
        UnzipOptions options = PipelineOptionsFactory.fromArgs(args)
                .withValidation()
                .as(UnzipOptions.class);
        Pipeline pipeline = Pipeline.create(options);
        pipeline.apply("MatchFile(s)", FileIO.match().filepattern(options.getInput()))
        .apply(FileIO.readMatches())
        .apply(ParDo.of(new Decompress(options.getOutput())))
        .apply(ParDo.of(new DoFn<String, String>(){
            @ProcessElement
            public void process(ProcessContext c) {
                UnzipOptions options = c.getPipelineOptions().as(UnzipOptions.class);
                StorageUtils.copyFile(options.getInput(),options.getOutput());
            }

        }));
        //pipeline.apply(ParDo.of(new Decompress(options.getOutput()))).withOutputTags(DECOMPRESS_MAIN_OUT_TAG, TupleTagList.of(DEADLETTER_TAG)));
        pipeline.run().waitUntilFinish();
    }

    public static class Decompress extends DoFn<FileIO.ReadableFile, String> {

        private final String destinationLocation;

        Decompress(String destinationLocation) {
            this.destinationLocation = destinationLocation;
        }

        @ProcessElement
        public void processElement(@Element FileIO.ReadableFile file,ProcessContext context) throws IOException {
            //ResourceId inputFile = context.element().resourceId();

            ResourceId inputFile = file.getMetadata().resourceId();

            // Output a record to the failure file if the file doesn't match a known compression.

            /*String p = context.element();
            GcsUtilFactory factory = new GcsUtilFactory();
            GcsUtil u = factory.create(c.getPipelineOptions());*/
            byte[] buffer = new byte[1000000000];
            try{
                Compression compression = Compression.detect(inputFile.toString());
                ReadableByteChannel readerChannel =
                        compression.readDecompressed(FileSystems.open(inputFile));

               // InputStream is = Channels.newInputStream(readerChannel);


                ///SeekableByteChannel sek = u.open(GcsPath.fromUri(p));
                //FileInputStream fis = new FileInputStream(file.getMetadata().resourceId().toString());
                //URL url= new URL(file.getMetadata().resourceId().toString());
                //InputStream is = Channels.newInputStream(file.getMetadata().resourceId().getFilename());

                /*Path p = Paths.get(URI.create(file.getMetadata().resourceId().toString()));
                SeekableByteChannel sek = FileChannel.open(p, StandardOpenOption.READ);
                InputStream is = Channels.newInputStream(sek);*/

                FileInputStream fis = new FileInputStream(inputFile.toString());
                BufferedInputStream bis = new BufferedInputStream(fis);
                ZipInputStream zis = new ZipInputStream(bis);
                ZipEntry ze = zis.getNextEntry();
                while(ze!=null){

                    //LOG.info("Unzipping File {}",ze.getName());
                    //WritableByteChannel wri = u.create(GcsPath.fromUri("gs://bucket_location/" + ze.getName()), getType(ze.getName()));
                    ResourceId outputDir = FileSystems.matchNewResource(destinationLocation, true);
                    ResourceId outputFile =
                            outputDir.resolve(ze.getName(), ResolveOptions.StandardResolveOptions.RESOLVE_FILE);
                    WritableByteChannel wri =  FileSystems.create(outputFile, MimeTypes.TEXT);
                    OutputStream os = Channels.newOutputStream(wri);
                    int len;
                    while((len=zis.read(buffer))>0){
                        os.write(buffer,0,len);
                    }

                    os.close();
                    wri.close();
                   // filesUnzipped++;
                    ze=zis.getNextEntry();


                }
                zis.closeEntry();
                zis.close();
                bis.close();
                fis.close();
context.output("Extracted");
            }
            catch(Exception e){
                e.printStackTrace();
            }
            //c.output(filesUnzipped);
            //System.out.println(filesUnzipped+"FilesUnzipped");
            //LOG.info("FilesUnzipped");


        /*private String getType(String fName){
            if(fName.endsWith(".zip")){
                return "application/x-zip-compressed";
            }
            else {
                return "text/plain";
            }
        }*/

            ///java
            /*try {

            final ZipFile zf = new ZipFile(inputFile.toString());

            final Enumeration<? extends ZipEntry> entries = zf.entries();
            ZipInputStream zipInput = null;

            while (entries.hasMoreElements())
            {
                final ZipEntry zipEntry=entries.nextElement();
                final String fileName = zipEntry.getName();
                // zipInput = new ZipInputStream(new FileInputStream(fileName));
                InputStream inputs=zf.getInputStream(zipEntry);
                //  final RandomAccessFile br = new RandomAccessFile(fileName, "r");
                BufferedReader br = new BufferedReader(new InputStreamReader(inputs, "UTF-8"));
                ResourceId outputDir = FileSystems.matchNewResource(destinationLocation, true);
                ResourceId outputFile =
                        outputDir.resolve(fileName, ResolveOptions.StandardResolveOptions.RESOLVE_FILE);
                //WritableByteChannel wr =  FileSystems.create(outputFile, MimeTypes.TEXT);
                //OutputStream os = Channels.newOutputStream(wr);

                FileWriter fr=new FileWriter(destinationLocation);
                BufferedWriter wr=new BufferedWriter(fr);
                String line;
                while((line = br.readLine()) != null)
                {
                    wr.write(line);
                    System.out.println(line);
                    wr.newLine();
                    wr.flush();
                }
                br.close();
                //zipInput.closeEntry();
            }


        } catch (IOException e) {
                throw new RuntimeException(e);
            }*/

               /* try {
                   ResourceId outputFile = decompress(inputFile);
                    context.output(outputFile.toString());
                } catch (IOException e) {
                    //LOG.error(e.getMessage());
                    //context.output(DEADLETTER_TAG, KV.of(inputFile.toString(), e.getMessage()));
                }*/
        }

        private ResourceId decompress(ResourceId inputFile) throws IOException {
            // Remove the compressed extension from the file. Example: demo.txt.gz -> demo.txt
            String outputFilename = Files.getNameWithoutExtension(inputFile.toString());

            // Resolve the necessary resources to perform the transfer.
            ResourceId outputDir = FileSystems.matchNewResource(destinationLocation, true);
            ResourceId outputFile =
                    outputDir.resolve(outputFilename, ResolveOptions.StandardResolveOptions.RESOLVE_FILE);
            ResourceId tempFile =
                    outputDir.resolve(
                            Files.getFileExtension(inputFile.toString()) + "-temp-" + outputFilename,
                            ResolveOptions.StandardResolveOptions.RESOLVE_FILE);

            // Resolve the compression
            Compression compression = Compression.detect(inputFile.toString());

            // Perform the copy of the decompressed channel into the destination.
            try (ReadableByteChannel readerChannel =
                         compression.readDecompressed(FileSystems.open(inputFile))) {
                try (WritableByteChannel writerChannel = FileSystems.create(tempFile, MimeTypes.TEXT)) {
                    ByteStreams.copy(readerChannel, writerChannel);
                }

                // Rename the temp file to the output file.
                FileSystems.rename(
                        ImmutableList.of(tempFile),
                        ImmutableList.of(outputFile),
                        MoveOptions.StandardMoveOptions.IGNORE_MISSING_FILES);
            } catch (IOException e) {
                String msg = e.getMessage();

                //LOG.error("Error occurred during decompression of {}", inputFile.toString(), e);
                //throw new IOException(sanitizeDecompressionErrorMsg(msg, inputFile, compression));
            }

            return outputFile;
        }
    }


}
