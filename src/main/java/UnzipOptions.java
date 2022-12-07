import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;


public interface UnzipOptions extends DataflowPipelineOptions {
    @Description("Input for the pipeline")
    // @Default.String("src/main/resources/sspinput.txt")
    @Default.String("src/main/resources/*.zip")
    String getInput();
    void setInput(String input);

    @Description("Output for the pipeline")
    @Default.String("src/main/resources/")
    String getOutput();
    void setOutput(String output);
}
