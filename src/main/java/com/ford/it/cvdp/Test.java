package com.ford.it.cvdp;

import java.util.ArrayList;
import java.util.Arrays;
import com.ford.datafactory.batch.util.*;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.CopyWriter;

public class Test {
    public static  void main(String args[]) {
        String fileName="CLM_FORD_PROD_DWH_TransactionRecognitionTiers_0000002244_20220901203500.txt";
        String[] fileNameWithoutExt = fileName.substring(0,fileName.lastIndexOf('.')).split("_");
       //String timestamp = FordPassAprHelper.parseHeaderDateTimestamp("20210901203500" , "Timestamp");
    //System.out.println(fileNameWithoutExt[fileNameWithoutExt.length-1]);
        System.out.println(object_name("gs://prj-dfdl-181-fpa-d-181-comarch-dev/fpa/ongoing/20221012080947/CLM_GDIA_STAGE_DWH_export_20220102084500.zip"));
        System.out.println(object_name("gs://prj-dfdl-181-fpa-d-181-comarch-dev/fpa/unzipped_files"));
        String objName = "fpa/unzipped_files/CLM_FORD_QA_DWH_Activities_0000001394_20220909003000.txt";
        System.out.println(objName.substring(objName.lastIndexOf("/")+1));


        String sourceObjectName = object_name("gs://prj-dfdl-181-fpa-d-181-comarch-dev/fpa/unzipped_files");
        String blobName = "fpa/unzipped_files/";
        String filename = blobName.substring(blobName.lastIndexOf("/") + 1);
        System.out.println("filename: "+filename);
        if (!blobName.equalsIgnoreCase(sourceObjectName+"/")) {
            if (!filename.contains("PACKLIST")) {
                System.out.println("if: " + filename);
            } else {
                System.out.println("else: " + filename);
            }
        }


        String zipObjName = ("fpa/ongoing/*/*.zip").replace("/*.zip", "");
        System.out.println("zipObjName: "+zipObjName);

        /*String record="92372451,,150940201,A,,150951201,2022-09-08 02:00:00,0.0,,2022-09-08 02:00:02,,,,B,0.0,PE,,USA,,,USA,,,2022-09-08 00:00:00,,,,,,,,,,,,,,,,,,,0.0,,,2022-09-08 02:00:02,2022-09-08 02:00:02,,,,,F,,,-499.56,-300,,,,,,REWARDS,1662620400813607,,,,0,,,,,,,,,,,,,,,,,,,,,,";
        ArrayList<String> rewardData = CsvUtil.parseCsvRecord(record.toString());
        System.out.println(rewardData.size());
        System.out.println(objName.contains("activities"));*/
    }

    public static String object_name(String filePath) {
        String[] path = filePath.replace("gs://", "").split("/");
        String objectName = String.join("/", Arrays.copyOfRange(path, 1, path.length));

        return objectName;
    }
}
