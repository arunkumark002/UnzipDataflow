package com.ford.datafactory.batch.util;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

/**
 * TODO - Place class description here
 */

public class ConversionUtil {

    private static final char[] HEX_CHARACTERS = "0123456789ABCDEF".toCharArray();

    private static final int[][] BIT_OFFSETS = { {},
            {128, 64, 32, 16, 8, 4, 2, 1},
            {192, 96, 48, 24, 12, 6, 3},
            {224, 112, 56, 28, 14, 7},
            {240, 120, 60, 30, 15}
    };
    private static final int[] BIT_OFFSETS_OVER_BYTE = {0, 0, 0, 0, 0, 7, 3, 1};
    private static final int[] BIT_OFFSETS_OVER_BYTE_LAST = {128, 192, 224};

    // Index is number of bits from the right that should have 1s
    // RIGHT_BIT_MASK[1] = 0000 0001 = 1
    // RIGHT_BIT_MASK[3] = 0000 0111 = 7
    private static final int[] RIGHT_BIT_MASK = {0, 1, 3, 7, 15, 31, 63, 127, 255};

    private static DateFormat dateFormatOracle = new SimpleDateFormat(
            "dd-MMM-yy HH.mm.ss.SSS aa");

    /**
     * Validate String for maximum length.
     *
     * @param name
     * @param maxLength
     * @param value
     * @return String value
     * @throws Exception
     */
    public static String validateString(final String name, final int maxLength,
                                        final String value) throws Exception {

        if (value == null) {
            return null;
        }

        if (value.length() > maxLength) {
            throw new Exception(name + ": String too long. (" + value + ")");
        }

        return value;
    }

    /**
     * Convert String containing "1", "0" or null to a Boolean.
     *
     * @param name
     * @param value
     * @return Boolean
     * @throws Exception
     */
    public static Boolean stringToBoolean(final String name, final String value)
            throws Exception {

        if (value == null) {
            return null;
        }

        Boolean bool = null;

        if (value.equals("1")) {
            bool = true;
        } else if (value.equals("0")) {
            bool = false;
        } else {
            throw new Exception(name + ": Invalid Boolean (" + value + ")");
        }

        return bool;
    }

    /**
     * Convert a String to a Short Integer
     *
     * @param name
     * @param value
     * @return Short
     * @throws Exception
     */
    public static Short stringToShort(final String name, final String value) throws Exception {

        if (value == null) {
            return null;
        }

        Short small = null;

        try {
            small = Short.parseShort(value);
        } catch (final Exception e) {
            throw new Exception(name + ": Invalid Short (" + value + ")");
        }

        return small;
    }

    /**
     * Convert a String to a Byte.
     *
     * @param name
     * @param value
     * @return Byte
     * @throws Exception
     */
    public static Byte stringToByte(final String name, final String value) throws Exception {

        if (value == null) {
            return null;
        }

        Integer integer = null;

        try {
            integer = Integer.parseInt(value);
        } catch (final Exception e) {
            throw new Exception(name + ": Invalid Byte (" + value + ")");
        }

        if (integer < 0 || integer > 255) {
            throw new Exception(name + ": Invalid Byte (" + value + ")");
        }

        return (byte)integer.intValue();
    }

    /**
     * Convert a String to an Integer
     *
     * @param name
     * @param value
     * @return Integer
     * @throws Exception
     */
    public static Integer stringToInteger(final String name, final String value)
            throws Exception {

        if (value == null) {
            return null;
        }

        Integer integer = null;

        try {
            integer = Integer.parseInt(value);
        } catch (final Exception e) {
            throw new Exception(name + ": Invalid Integer (" + value + ")");
        }

        return integer;
    }

    /**
     * Convert a String to a Big Decimal
     *
     * @param name
     * @param value
     * @return BigDecimal
     * @throws Exception
     */
    public static BigDecimal stringToBigDecimal(final String name, final String value)
            throws Exception {

        if (value == null) {
            return null;
        }

        BigDecimal bigDecimal = null;

        try {
            bigDecimal = new BigDecimal(value);
        } catch (final Exception e) {
            throw new Exception(name + ": Invalid BigDecimal (" + value + ")");
        }

        return bigDecimal;
    }

    /**
     * Convert a String in Oracle Timestamp Format to a Timestamp
     *
     * @param name
     * @param value
     * @return Timestamp
     * @throws Exception
     */
    public static Timestamp stringToTimestamp(final String name, String value)
            throws Exception {

        if (value == null) {
            return null;
        }

        Timestamp timestamp = null;

        if (value.length() == 28) {
            value = value.substring(0, 22) + value.substring(25);
        } else if (value.length() == 31) {
            value = value.substring(0, 22) + value.substring(26);
        }

        try {
            final Date date = dateFormatOracle.parse(value);
            timestamp = new Timestamp(date.getTime());
        } catch (final Exception e) {
            throw new Exception(name + ": Invalid Timestamp (" + value + ")");
        }

        return timestamp;
    }

    public static String intToHexString(final int value, final int length) {

        String s = Integer.toHexString(value);

        while (s.length() < length) {
            s = "0" + s;
        }

        return s.toUpperCase();
    }

    public static Calendar getUtcCalendar(final Calendar calendar) {

        if (calendar == null) {
            return null;
        }

        final DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        final String tempDateTime = dateFormat.format(calendar.getTime());

        dateFormat.setTimeZone(TimeZone.getDefault());
        final Calendar utcDateTime = new GregorianCalendar();

        try {
            utcDateTime.setTime(dateFormat.parse(tempDateTime));
        } catch (final Exception e) {
        }

        return utcDateTime;
    }

    public static Calendar getUtcCalendarStrict(final Calendar calendar) {

        if (calendar == null) {
            return null;
        }

        final DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        dateFormat.setLenient(false);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        final String tempDateTime = dateFormat.format(calendar.getTime());

        dateFormat.setTimeZone(TimeZone.getDefault());
        Calendar utcDateTime = new GregorianCalendar();

        try {
            utcDateTime.setTime(dateFormat.parse(tempDateTime));
        } catch (final Exception e) {
            utcDateTime = null;
        }

        return utcDateTime;
    }

    /**
     * hash ServerId
     *
     * @param serverId
     * @return Integer hash
     */
    public static Integer hashServerId(final String serverId) {

        if (serverId == null) {
            return null;
        }

        try {

            final String[] serverNodes = serverId.split("\\.");
            Integer temp =
                    Integer.parseInt(serverNodes[0].substring(serverNodes[0].length() - 3));
            temp = temp + serverNodes[0].charAt(0) * 1000;
            return temp;

        } catch (final Exception e) {
            return -1;
        }

    }

    /**
     * nullToUknown
     *
     * @param str
     *
     * @return String
     */
    public static String nullToUknown(final String str) {

        if (str == null) {
            return "Unknown";
        }

        return str;
    }

    /**
     * Creates a hex string from an array of bytes
     *
     * @param bytes
     * @param startByte
     * @param startBit
     * @param bitLength
     * @return String
     */
    public static String bytesToHexString(final byte[] bytes, final int startByte,
                                          final int startBit, final int bitLength) {

        final int hexLength = (bitLength + 3) / 4;
        final char[] hexChars = new char[hexLength];

        int currentByte = startByte;
        int currentBit = startBit;
        int nibbleSize = bitLength % 4;
        int v;

        if (nibbleSize == 0) {
            nibbleSize = 4;
        }

        for (int i = 0; i < hexLength; i++) {

            if ((currentBit + nibbleSize) > 8) { // Nibble crosses over multiple bytes
                v = bytes[currentByte] & BIT_OFFSETS_OVER_BYTE[currentBit];
                v = (v << currentBit + nibbleSize - 8);
                final int rightHalf =
                        ((bytes[currentByte + 1] & BIT_OFFSETS_OVER_BYTE_LAST[currentBit
                                + nibbleSize
                                - 9]) >>> (16 - currentBit - nibbleSize));
                v = v | rightHalf;

                currentByte += 1;
                currentBit += nibbleSize - 8;
            } else {
                v =
                        (bytes[currentByte] & BIT_OFFSETS[nibbleSize][currentBit]) >>> (8 - nibbleSize - currentBit);

                currentBit += nibbleSize;

                if (currentBit > 7) {
                    currentByte += 1;
                    currentBit -= 8;
                }
            }

            hexChars[i] = HEX_CHARACTERS[v];

            nibbleSize = 4;
        }

        return new String(hexChars);
    }

    /**
     * Replace a particular set of bits with a random value
     *
     * @param bytes
     * @param startByte
     * @param startBit
     * @param bitLength
     * @return String
     * @throws Exception
     */
    public static void anonymizeBytes(final byte[] bytes, final int startByte, final int startBit, final int bitLength) throws Exception {

        int remainingBits = bitLength;
        int currentByte = startByte;
        int currentBit = startBit;

        Random random = new Random();

        if (startBit > 7 || startBit < 0) {
            throw new Exception("startBit must be >= 0 and < 8");
        }

        if (bitLength < 1) {
            throw new Exception("bitLength must be > 0");
        }

        //Trying to figure out the number of bytes that should be in the array
        int totalBitsFromStartByte = startBit + bitLength;
        //Length = 1-8 = 0 bytes after, 9-15 = 1 byte, etc....
        int numBytesAfterStartByte = ((totalBitsFromStartByte - 1) / 8);
        int endByte = startByte + numBytesAfterStartByte;

        //If bytes length = 1, then end byte can only be 0
        if (endByte >= bytes.length) {
            throw new Exception("Requested section extends beyond length of array");
        }

        //We're going to anonymize one byte at a time until there are no more bits left
        while (remainingBits > 0) {

            //Find the max bit chunk size...only > 0 the first time through and the startBit is not 0
            int maxBitChunk = 8 - currentBit;
            int currentBitChunk = maxBitChunk;

            if (currentBitChunk > remainingBits) {
                currentBitChunk = remainingBits;
            }

            int left = bytes[currentByte] >>> (8 - currentBit);
            int right = bytes[currentByte] & RIGHT_BIT_MASK[8 - currentBit - currentBitChunk];

            //Strip off the left
            int oldMiddle = bytes[currentByte] & RIGHT_BIT_MASK[8 - currentBit];

            //Then shift over to drop the right
            oldMiddle = oldMiddle >>> (8 - currentBit - currentBitChunk);

            //Replacing with all 1s
            int newMiddle = RIGHT_BIT_MASK[currentBitChunk];

            int newByte = (left << (8 - currentBit)) | (newMiddle << (8 - currentBit - currentBitChunk)) | right;
            bytes[currentByte] = (byte) newByte;

            currentByte++;
            currentBit = 0;
            remainingBits -= currentBitChunk;
        }
    }

    /**
     * truncate
     *
     * @param str
     * @param maxLength
     *
     * @return String
     */
    public static String truncate(final String str, final Integer maxLength) {

        if (str == null) {
            return null;
        }

        if (str.length() <= maxLength) {
            return str;
        }

        return str.substring(0, maxLength);
    }

    /**
     * Adds a random number to a given value, making sure the resulting value is less than max
     *
     * @param value
     * @param max
     * @return int
     */
    public static int obscureInteger(final int value, final int max) {

        final Random rand = new Random();

        final int randomNum = rand.nextInt(max / 2) + (max / 10);
        int newNum = value + randomNum;

        if (newNum > max) {
            newNum -= max;
        }

        return newNum;
    }

    /**
     * Adds a random number to a given value, making sure the resulting value is less than max
     *
     * @param value
     * @param max
     * @return String
     */
    public static String obscureHexInteger(final String value, final int max) {

        final int newLen = Integer.toHexString(max).length();
        final int newVal = obscureInteger(Integer.parseInt(value, 16), max);
        String newHex = Integer.toHexString(newVal);

        while (newHex.length() < newLen) {
            newHex = "0" + newHex;
        }

        return newHex.toUpperCase();
    }

    /**
     * Obscure a VIN
     *
     * @param vin
     * @return
     */
    public static String obscureVin(final String vin) {

        if (vin.length() != 17) {
            return vin;
        }

        return vin.substring(0, 11) + "012345";
    }

    /**
     * Returns sql timestamp for the date passed
     *
     * @param date
     * @return Timestamp
     */
    public static Timestamp dateToSQLTimestamp(final Date date) {
        return new Timestamp(date.getTime());
    }

    /**
     * Convert a String to an long
     *
     * @param name
     * @param value
     * @return Long
     * @throws Exception
     */
    public static long stringToLong(final String name, final String value) throws Exception {

        if (value == null) {
            return (Long)null;
        }

        long l;

        try {
            l = Long.parseLong(value);
        } catch (final Exception e) {
            throw new Exception("[ " + name + " ] : Could not parse Long. Received value : ("
                    + value + ")");
        }

        return l;
    }

    /**
     * Convert a String to an Double
     *
     * @param name
     * @param value
     * @return Double
     * @throws Exception
     */
    public static double stringToDouble(final String name, final String value)
            throws Exception {

        if (value == null) {
            return (Double)null;
        }

        double d;

        try {
            d = Double.parseDouble(value);
        } catch (final Exception e) {
            throw new Exception("[ " + name
                    + " ] : Could not parse Double. Received value : (" + value
                    + ")");
        }

        return d;
    }

    /**
     * For a given string checks to see if it is a valid VIN (17 characters and alpha numeric)
     *
     * @param vin
     * @return boolean
     */
    public static boolean isValidVin(String vin) {

        if (vin == null || vin.length() != 17 || !StringUtils.isAlphanumeric(vin)) {
            return false;
        }

        return true;
    }
}
