import com.fasterxml.jackson.annotation.JsonProperty;


public class GCSLogMessage {

    @JsonProperty("name")
    public String name;
    @JsonProperty("bucket")
    public String bucket;
    @JsonProperty("job_name")
    public String job_name;

}
