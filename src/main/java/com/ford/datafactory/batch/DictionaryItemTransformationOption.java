package com.ford.datafactory.batch;


import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.ValueProvider;

public interface  DictionaryItemTransformationOption extends PipelineOptions {
    @Description("The GCS location of the text you'd like to process")
    ValueProvider<String> getInputPath();
    void setInputPath(ValueProvider<String> value);

    @Description("The GCS location of the transformed data files")
    ValueProvider<String> getOutputPath();

    void setOutputPath(ValueProvider<String> value);
}
