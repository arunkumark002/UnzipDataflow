
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
package com.ford.datafactory.batch.util;
//import com.ford.datafactory.batch.TextTransformToText;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.CopyWriter;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.util.Arrays;

public class StorageUtils {
    private static final Storage storage = StorageOptions.getDefaultInstance().getService();
  //  private static final Logger logger = LoggerFactory.getLogger(TextTransformToText.class);

    //    public static BlobId createBlobIdFromPath(String filePath) {
//        String[] path = filePath.replace("gs://", "").split("/");
//
//        String bucket = path[0];
//        String objectName = String.join("/", Arrays.copyOfRange(path, 1, path.length));
//
//        return BlobId.of(bucket, objectName);
//    }
    public static String Bucket_name(String filePath) {
        String[] path = filePath.replace("gs://", "").split("/");

        String bucket = path[0];
        return bucket;
    }
    public static String object_name(String filePath) {
        String[] path = filePath.replace("gs://", "").split("/");
        String objectName = String.join("/", Arrays.copyOfRange(path, 1, path.length));

        return objectName;
    }

    public static void copyFile(String sourcePath, String destinationPath) {
        String sourceBucketName = Bucket_name(sourcePath);
        String targetBucketName = Bucket_name(destinationPath);
        String sourceObjectName = object_name(sourcePath);
        Blob blob = storage.get(sourceBucketName, sourceObjectName);
        CopyWriter copyWriter = blob.copyTo(targetBucketName,blob.getName());
        Blob copiedBlob = copyWriter.getResult();
        blob.delete();
    }
    public static void moveFile(String filePattern, String archivePath,String type) {
        String sourceObjectName = "";
        String sourceBucketName = Bucket_name(filePattern);
        String targetBucketName = Bucket_name(archivePath);
        //logger.info("StorageUtils::moveFile: Source bucket: "+sourceBucketName);
        //logger.info("StorageUtils::moveFile: Target bucket: "+targetBucketName);
        if(type.equalsIgnoreCase("trigger")) {
            sourceObjectName = object_name(filePattern);
            Blob blob = storage.get(sourceBucketName, sourceObjectName);
            CopyWriter copyWriter = blob.copyTo(targetBucketName,blob.getName());
            Blob copiedBlob = copyWriter.getResult();
            //logger.info("Moved Trigger "+ sourceObjectName + " from bucket " + sourceBucketName + " to " + blob.getName() + " in bucket " + copiedBlob.getBucket());
            blob.delete();
        }
        else {
            sourceObjectName = object_name(filePattern);
            //logger.info("StorageUtils::moveFile: sourceObjectName: "+sourceObjectName);
            Page<Blob> blobs =
                    storage.list(
                            sourceBucketName,
                            Storage.BlobListOption.prefix(sourceObjectName));

            for (Blob blob : blobs.iterateAll()) {
                if (!blob.getName().equalsIgnoreCase(sourceObjectName)) {
                    System.out.println(blob.getName());
                    CopyWriter copyWriter = blob.copyTo(targetBucketName,blob.getName());
                    Blob copiedBlob = copyWriter.getResult();
                   // logger.info("Moved object "+ sourceObjectName + " from bucket " + sourceBucketName + " to " + blob.getName() + " in bucket " + copiedBlob.getBucket());
                }
                blob.delete();
            }




        }
    }


}
