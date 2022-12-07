
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


import com.google.auto.value.AutoValue;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;

import org.apache.beam.sdk.options.ValueProvider;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BigQueryConverters {


    @AutoValue
    public abstract static class VerifyRecordColumnsCount
            extends PTransform<PCollection<FailsafeElement<String, String>>, PCollectionTuple> {

        private static final Logger logger = LoggerFactory.getLogger(VerifyRecordColumnsCount.class);

        public abstract TupleTag<FailsafeElement<String, String>> successTag();

        public abstract TupleTag<FailsafeElement<String, String>> failureTag();

        //public abstract ValueProvider<TableSchema> tableSchema();

        public abstract ValueProvider<Character> delimiter();

        public abstract ValueProvider<Character> quote();

        public abstract ValueProvider<String> nullString();


        @AutoValue.Builder
        public abstract static class Builder {
            public abstract Builder setSuccessTag(TupleTag<FailsafeElement<String, String>> successTag);

            public abstract Builder setFailureTag(TupleTag<FailsafeElement<String, String>> failureTag);

           // public abstract Builder setTableSchema(ValueProvider<TableSchema> validationSchema);

            public abstract Builder setDelimiter(ValueProvider<Character> delimiter);

            public abstract Builder setQuote(ValueProvider<Character> quote);

            public abstract Builder setNullString(ValueProvider<String> nullString);

            public abstract VerifyRecordColumnsCount build();
        }

        /*public static Builder newBuilder() {
            return new AutoValue_BigQueryConverters_VerifyRecordColumnsCount.Builder();
        }*/

        @Override
        public PCollectionTuple expand(PCollection<FailsafeElement<String, String>> input) {
            return input.apply("VerifyRecordColumnsCount",
                    ParDo.of(new DoFn<FailsafeElement<String, String>, FailsafeElement<String, String>>() {
                        private int columnsCount;
                        //private CSVFormat csvFormat;


                        @ProcessElement
                        public void processElement(DoFn.ProcessContext c) {
                            //FailsafeElement<String, String> element = c.element();
                           // String originalPayload = element.getOriginalPayload();
                            //logger.debug("original payload: " + originalPayload);
                            try {
                                /*CSVParser parser = CSVParser.parse(originalPayload, csvFormat);
                                CSVRecord record = parser.getRecords().get(0);*/
                                //logger.debug("record column count: " + record.size() + ", expected column count: " + columnsCount);

								/*StringBuffer newPayload = new StringBuffer();
								for (int i=0; i<record.size();i++)
								{
									logger.debug("Element is: "+ record.get(i));
									newPayload.append(record.get(i));
									if (i!=record.size()-1) {
										newPayload.append(delimiter().get());
									}
								}

								logger.debug("New payload: " + newPayload.toString());
								*/

                                /*if (record.size() != columnsCount) {
                                    logger.info("Error record is: "+originalPayload);
                                    logger.error("Record columns count doesn't match destination schema");

                                    throw new Exception("Record columns count doesn't match destination schema"+"...record.size :"+record.size()+" ..columnsCount :: "+columnsCount);
                                }

                                c.output(FailsafeElement.of(element.getOriginalPayload(), element.getOriginalPayload()));*/
                            } catch (Exception e) {
                                /*logger.error("VerifyJSONRecords: Failed JSON validation "+e.getMessage());
                                c.output(failureTag(), FailsafeElement.of(element).setErrorMessage(e.getMessage())
                                        .setStacktrace(Throwables.getStackTraceAsString(e)));*/
                            }
                        }
                    }).withOutputTags(successTag(), TupleTagList.of(failureTag())));
        }
    }
}
