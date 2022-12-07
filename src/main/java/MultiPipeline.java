import org.apache.beam.runners.direct.DirectRunner;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;

public class MultiPipeline {
    public static void main(String[] args) {
        PipelineOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().create();
        //options.setRunner(DirectRunner.class);

        Pipeline pipeline = Pipeline.create(options);

        PCollection<String> linesForPipelineOne = pipeline.apply(Create.of("A1", "B1"));
        PCollection<String> linesToWriteFromPipelineOne = linesForPipelineOne.apply("Pipeline 1 transform",
                ParDo.of(new DoFn<String, String>() {

                    @ProcessElement
                    public void processElement(ProcessContext c) {
                        System.out.println("Pipeline one:" + c.element());
                        c.output(c.element() + " extra message.");
                    }

                }));
        linesToWriteFromPipelineOne.apply((TextIO.write().withoutSharding().to("src/main/resources/file.txt")));

        PCollection<String> linesForPipelineTwo = pipeline.apply(Create.of("A2", "B2"));
        linesForPipelineTwo.apply("Pipeline 2 transoform",
                ParDo.of(new DoFn<String, String>() {

                    @ProcessElement
                    public void processElement(ProcessContext c) {
                        System.out.println("Pipeline two:" + c.element());
                        c.output(c.element() + " extra message.");
                    }

                }));
        linesForPipelineTwo.apply((TextIO.write().withoutSharding().to("src/main/resources/file1.txt")));
        pipeline.run();
    }
}
