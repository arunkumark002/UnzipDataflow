package com.ford.datafactory.batch.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {


    /**
     * Calculate the stringToBeHashed to SHA-256.
     *
     * @param stringToBeHashed
     * @return String hashedString
     * @throws NoSuchAlgorithmException
     */
    public static String calculateSHA256(String stringToBeHashed) throws NoSuchAlgorithmException {

        if(stringToBeHashed == null)
            return null;

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        messageDigest.update(stringToBeHashed.getBytes());

        return convertToHex(messageDigest.digest());

    }


    /**
     * Convert the data to Hex.
     *
     * @param data
     * @return String hexStringBuffer
     */
    public static String convertToHex(byte[] data) {

        StringBuffer hexStringBuffer = new StringBuffer();

        for (int i = 0; i < data.length; i++) {

            int halfbyte = (data[i] >>> 4) & 0x0F;

            int two_halfs = 0;

            do {

                if ((0 <= halfbyte) && (halfbyte <= 9)) {

                    hexStringBuffer.append((char) ('0' + halfbyte));

                } else {

                    hexStringBuffer.append((char) ('a' + (halfbyte - 10)));
                }

                halfbyte = data[i] & 0x0F;

            } while (two_halfs++ < 1);

        }

        return hexStringBuffer.toString();

    }

}
