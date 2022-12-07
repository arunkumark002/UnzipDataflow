    package com.ford.datafactory.batch;


    import com.ford.datafactory.batch.decoder.DictionaryItemDataDecoder;
    import org.apache.beam.sdk.transforms.SimpleFunction;

    public class DictionaryItemTransformFunction extends SimpleFunction<String, String> {

        @Override
        public String apply(String input) {

            String output = null;
            if (null != input) {
                String record = input.toString().trim();
                if (null != record) {
                           try {
                                    output = new DictionaryItemDataDecoder().decode("",record);
                                    if(null == output) {
                                        output = "Null Value";
                                    }
                                } catch (final Exception e) {
                                    e.printStackTrace();
                                    System.out.println(e.getMessage());
                                }
                }
            }
                    return output;
        }
    }
