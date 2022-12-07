package com.ford.it.cvdp.process;

public interface SSPIDecoder {
    String process_status = "processStatus";
    String process_details = "processDeatails";
    String process_time = "processTime";

    String decode(String var1,String var2) throws Exception;
}

