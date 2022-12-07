import com.ford.it.cvdp.decoder.SSPChargeBalanceB2CDecoder;
import com.ford.it.cvdp.json.SSPChargeBalanceB2CBO;
import org.apache.beam.sdk.transforms.SimpleFunction;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SSPChargeBalanceTransformationJSON extends SimpleFunction<String, String> {

    ArrayList<Map<String, String>> outputArrayLst = new ArrayList<Map<String, String>>();
    Map<String, String> outputMap = new HashMap<String, String>();
    String errors ="";

    @Override
    public String apply(String input) {

        ObjectMapper objectMapper = new ObjectMapper();
        StringBuffer buffer = new StringBuffer();
        String output = null;
        //Text text = new Text();
        outputArrayLst = new ArrayList<Map<String, String>>();
        outputMap = new HashMap<String, String>();
        String json = null;
        if (null != input) {
            String record = input.toString().trim();
            if (null != record) {
                try {
                    JsonNode commonJSONNode = null;
                    JsonNode commonJSONData = objectMapper.readTree(record);
                    JsonNode embeddedNode = commonJSONData.get("_embedded");
                    Iterator<Map.Entry<String, JsonNode>> nodes = embeddedNode.getFields();
                    while (nodes.hasNext()) {
                        Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) nodes.next();
                        commonJSONNode = entry.getValue();
                        break;
                    }
                    if (null != commonJSONNode
                            && !commonJSONNode.toString().equals("[]")
                            && !commonJSONNode.toString().replace("\"", "").equals("")) {
                        Iterator<JsonNode> mpJsonNode = commonJSONNode.getElements();
                        while (mpJsonNode.hasNext()) {
                            JsonNode entry = mpJsonNode.next();
                            try {
                                json = new SSPChargeBalanceB2CDecoder().decode(entry.toString(), record);
                                //buffer.append(json+"\n");
                                //text.setText(json);
                                //System.out.println("text : "+text.toString());
                                if(output == null) {
                                    output = json;
                                }else{
                                    output = output +  "\n" + json;
                                }
                               // System.out.println("output .. "+output +"...............end output");
                            } catch (final Exception e) {
                                e.printStackTrace();
                                System.out.println(e.getMessage());
                            }
                        }
                    }else {
                        SSPChargeBalanceB2CBO chargeBalanceBO = new SSPChargeBalanceB2CBO();
                        chargeBalanceBO.setRaw_payload(record);
                        chargeBalanceBO.setProcess_status_details("Invalid");
                        output = objectMapper.writeValueAsString(chargeBalanceBO);
                    }
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //System.out.println("out size : "+"..."+output);
        return output;
    }
}