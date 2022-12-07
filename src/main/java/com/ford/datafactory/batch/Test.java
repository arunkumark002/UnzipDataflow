package com.ford.datafactory.batch;


import java.util.Arrays;
import com.ford.datafactory.batch.util.*;

public class Test {
    public static  void main(String args[]) {
        String fileName="CLM_FORD_PROD_DWH_TransactionRecognitionTiers_0000002244_20220901203500.txt";
        String[] fileNameWithoutExt = fileName.substring(0,fileName.lastIndexOf('.')).split("_");
        //String timestamp = FordPassAprHelper.parseHeaderDateTimestamp("20210901203500" , "Timestamp");
        //System.out.println(fileNameWithoutExt[fileNameWithoutExt.length-1]);
        System.out.println(object_name("gs://prj-dfdl-181-fpa-d-181-comarch-dev/fpa/ongoing/20221012080947/CLM_GDIA_STAGE_DWH_export_20220102084500.zip"));
        String objName = "fpa/unzipped_files/CLM_FORD_QA_DWH_Activities_0000001394_20220909003000.txt";
        System.out.println(objName.substring(objName.lastIndexOf("/")+1));

        String record="5003,FPR,Ford Pass Rewards Tiers,,C,BLUE,Blue tier,0,Blue tier assigned after reaching 200 qualifiers,2021-03-18 09:38:31,56001,,";
        CsvUtil.parseCsvRecord(record.toString());
    }

    public static String object_name(String filePath) {
        String[] path = filePath.replace("gs://", "").split("/");
        String objectName = String.join("/", Arrays.copyOfRange(path, 1, path.length));

        return objectName;
    }
}

