import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;

public interface MyOptions extends PipelineOptions {
    @Description("Input for the pipeline")
   // @Default.String("src/main/resources/sspinput.txt")
    @Default.String("src/main/resources/CLM_FORD_QA_DWH_DictionaryItem_0000001394_20220909003000.txt")
    String getInput();
    void setInput(String input);

    @Description("Output for the pipeline")
    @Default.String("src/main/resources/output.txt")
    String getOutput();
    void setOutput(String output);
}