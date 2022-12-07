/*
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ford.datafactory.batch;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.PCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class DictionaryItemTransformDataflow {
    private static final Logger logger = LoggerFactory.getLogger(DictionaryItemTransformDataflow.class);
    static boolean isInput = true;
    public static void main(String[] args) throws IOException, InterruptedException {
        DictionaryItemTransformationOption options = (DictionaryItemTransformationOption) PipelineOptionsFactory
                .fromArgs(args)
                .withValidation()
                .as(DictionaryItemTransformationOption.class);
        // TODO Auto-generated method stub
        Pipeline p = Pipeline.create((PipelineOptions) options);
        PCollection<String> input = p.apply(TextIO.read().from(options.getInputPath()));

        PCollection<String> output = input.apply(MapElements.via(new DictionaryItemTransformFunction()));

        /*PCollection<String> output = transformedRecords.apply("Move data files to archive path",
                    ParDo.of(
                            new DoFn<String, String>() {
                                    // Define the DoFn that logs the ValueProvider value.
                                    @ProcessElement
                                    public void process(ProcessContext c) {
                                        c.output(c.element());
                                        logger.info("c.element() :: "+c.element());
                                        System.out.println("c.element() :: "+c.element());
                                        ChargeBalanceTransformationOption ops = c.getPipelineOptions().as(ChargeBalanceTransformationOption.class);
                                        String archivePath    = ops.getArchivePath().get();
                                        String sourceFileName = ops.getInputPath().get();
                                        if (isInput && ops.getArchivePath().isAccessible() && !Strings.isNullOrEmpty(archivePath)) {
                                            try {
                                                logger.info("In pipeline source path ..." + sourceFileName);
                                                System.out.println("In pipeline source path ..." + sourceFileName);
                                                String sourceFilePath = sourceFileName.replace("*", "");
                                                logger.info("In pipeline archive path ..." + archivePath);
                                                System.out.println("In pipeline  archive path ..." + archivePath);
                                                //StorageUtils.moveFile(sourceFilePath, archivePath,"Data");
                                                StorageUtils.moveFile(ops.getTriggerFilePath().get(), ops.getTriggerArchivePath().get(),"trigger");
                                                isInput = false;
                                            } catch (Exception e) {
                                                throw new RuntimeException(e.getMessage());
                                            }
                                        }
                                        logger.info("Option StringValue was {}", ops.getInputPath());
                                    }
                                }));*/
        output.apply(TextIO.write().to(options.getOutputPath()).withoutSharding());
        p.run();

//
//		PCollection<String>trigger =  p.apply(Create.empty(StringUtf8Coder.of()));
        //output1.apply("");
        /*PCollection<Void> output1 = output.apply(MoveDataFiles.BatchLogControlRecord
                .newBuilder()
                .setSourceFileName(options.getInputPath())
                .setArchivePath(options.getArchivePath())
                .build());*/
        //PCollection<String> trigger1 =  p.apply(Create.empty(StringUtf8Coder.of()));
        //output.apply(TextIO.write().to("gs://prj-dfdl-73-ssp-d-0073-scav-trigger-dev").withoutSharding().withSuffix(".trg"));
    }
}
