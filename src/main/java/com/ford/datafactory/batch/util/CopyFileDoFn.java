package com.ford.datafactory.batch.util;

import org.apache.beam.sdk.transforms.DoFn;

public class CopyFileDoFn extends DoFn<String, String> {

    private final String sourcePath;
    private final String destinationPath;

    public CopyFileDoFn(String sourcePath, String destinationPath) {
        this.sourcePath = sourcePath;
        this.destinationPath = destinationPath;
    }

    @ProcessElement
    public void processElement( ProcessContext c) {

        StorageUtils.copyFile(sourcePath,destinationPath);
    }
}
