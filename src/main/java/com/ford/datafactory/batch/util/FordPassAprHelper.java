package com.ford.datafactory.batch.util;



import com.ford.datafactory.batch.bo.BaseBO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
//import org.apache.hadoop.mapreduce.Mapper.Context;
//import org.apache.hadoop.mapreduce.lib.input.FileSplit;
//import com.ford.it.logging.ILogger;
//import com.ford.it.logging.LogFactory;

public class FordPassAprHelper {

	private static final String CLASS_NAME = FordPassAprHelper.class.getName();
	//private static final ILogger log = LogFactory.getInstance().getLogger(CLASS_NAME);

	static DateFormat stdTimeFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	static DateFormat stdTimeFormatUTC = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	static DateFormat stdTimeFormatCT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	static DateFormat stdHeaderTimeFormatCT = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	static DateFormat stdDateFormatYMD = new SimpleDateFormat("yyyy-MM-dd");
	
	static DateFormat headerTimeFormat = new SimpleDateFormat("yyyyMMddHHmmss");

	static DateFormat stdDateFormat = new SimpleDateFormat("yyyy");

	static String processStatusDetails = "";

	static String pattern = "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}";

	static String YMDPattern="\\d{4}-\\d{2}-\\d{2}";
	static String headerPattern = "\\d{4}\\d{2}\\d{2}\\d{2}\\d{2}\\d{2}";

	/**
	 * Private Constructor - This class should not be instantiated.
	 */
	FordPassAprHelper() {

	}

	/*public static String getFileHeaderTimestamp(
			@SuppressWarnings("rawtypes") Context context) {
		final String METHOD_NAME = "getFileHeaderTimestamp";
		//log.entering(CLASS_NAME, METHOD_NAME);
		// Header- Get timestamp from filename
		String headertimestamp = "";

		final FileSplit inputFileSplit = (FileSplit) context.getInputSplit();
		final String inputFileName = inputFileSplit.getPath().getName();
		String inputFileNametrim = inputFileName.substring(0,
				inputFileName.lastIndexOf('.'));
		final String id[] = inputFileNametrim.split("_");
		headertimestamp = id[id.length - 1];
		return headertimestamp;
	}*/

	/*public static String getFileName(
			@SuppressWarnings("rawtypes") Context context) {
		final String METHOD_NAME = "getFileName";
		//log.entering(CLASS_NAME, METHOD_NAME);
		final FileSplit inputFileSplit = (FileSplit) context.getInputSplit();
		final String inputFileName = inputFileSplit.getPath().getName();
		String inputFileNametrim = inputFileName.substring(0,
				inputFileName.lastIndexOf('.'));
		return inputFileNametrim;
	}*/

	/*public static String getFileNameEnvironment(
			@SuppressWarnings("rawtypes") Context context) {
		final String METHOD_NAME = "getFileNameEnvironment";
		//log.entering(CLASS_NAME, METHOD_NAME);
		final FileSplit inputFileSplit = (FileSplit) context.getInputSplit();
		final String inputFileName = inputFileSplit.getPath().getName();
		String env = null;
		if (inputFileSplit.toString().contains("CVDPPRD")) {
			env = "PROD";
		} else if (inputFileSplit.toString().contains("CVDPQA")) {
			env = "QA";
		} else if (inputFileSplit.toString().contains("CVDPDEV")) {
			env = "SIT";
		}
		return env;

	}*/

	public static String parseDateTimestamp(String fieldvalue, String fieldname) {
		String tempResult = null;
		String result = null;
		Date date = null;
		if (fieldvalue == null)
			return null;
		stdTimeFormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));

		stdTimeFormatCT.setTimeZone(TimeZone.getTimeZone("CST"));

		try {
			if (!fieldvalue.matches(pattern))
				throw new Exception();
			date = stdTimeFormatCT.parse(fieldvalue);
			tempResult = stdTimeFormatCT.format(date);
			if (fieldvalue.equals(tempResult)) {
				// stdTimeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
				result = stdTimeFormatUTC.format(date);
			} else
				throw new Exception();
		} catch (final Exception e1) {
			processLogEvent(fieldname, FordPassAprConstants.EXCEPTION_TS_FIELD);
		}
		return result;
	}

	public static String parseDateFormatYMD(String fieldvalue, String fieldname) {
		String tempResult = null;
		String result = null;
		Date date = null;
		
		if (fieldvalue == null)
			return null;
	

		try {
			
			if (!fieldvalue.matches(YMDPattern))
				throw new Exception();
			date = stdDateFormatYMD.parse(fieldvalue);
			
			if (fieldvalue.equals(stdDateFormatYMD.format(date))) {
				
				result = stdDateFormatYMD.format(date);
				
			} else
				throw new Exception();
		} catch (final Exception e1) {
			processLogEvent(fieldname, FordPassAprConstants.EXCEPTION_TS_FIELD);
		}
		return result;
	}
	/*
	 * public static String MandatoryparseDateTimestamp( String fieldvalue,
	 * String fieldname) throws Exception { String tempResult = null; String
	 * result=null; Date date = null; if (fieldvalue == null) return null;
	 * stdTimeFormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
	 * 
	 * stdTimeFormatCT.setTimeZone(TimeZone.getTimeZone("CST"));
	 * 
	 * try { date = stdTimeFormatCT.parse(fieldvalue); tempResult =
	 * stdTimeFormatCT.format(date);
	 * 
	 * if(fieldvalue.equals(tempResult)){
	 * //stdTimeFormat.setTimeZone(TimeZone.getTimeZone("UTC")); result =
	 * stdTimeFormatUTC.format(date); } else throw new Exception(); } catch
	 * (final Exception e1) { processLogEvent(fieldname,
	 * FordPassAprConstants.EXCEPTION_TS_FIELD); e1.printStackTrace(); throw new
	 * Exception( fieldname + FordPassAprConstants.EXCEPTION_TS_FIELD); }
	 * 
	 * return result; }
	 */

	public static String parseHeaderDateTimestamp(String fieldvalue,
			String fieldname) { // throws Exception {

		String tempResult = null;
		String result = null;
		Date date = null;
		if (fieldvalue == null)
			return null;

		stdTimeFormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));

		stdHeaderTimeFormatCT.setTimeZone(TimeZone.getTimeZone("CST"));

		try {
			date = stdHeaderTimeFormatCT.parse(fieldvalue);
			tempResult = stdHeaderTimeFormatCT.format(date);
			if (fieldvalue.equals(tempResult)) {
				result = stdTimeFormatUTC.format(date);
			} else
				throw new Exception();
		} catch (final Exception e1) {
			/*
			 * e1.printStackTrace(); throw new Exception( fieldname +
			 * FordPassAprConstants.EXCEPTION_HEADER_TS_FIELD);
			 */
			processLogEvent(fieldvalue+" "+date+" "+tempResult+" "+result,
					e1.getMessage());
		}
		return result;
	}

	public static String GetUTCdatetimeAsString() {
		stdTimeFormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
		final String utcTime = stdTimeFormatUTC.format(new Date());
		return utcTime;
	}

	public static String transformISOPartitionDate(String fieldvalue,
			String fieldname) { // throws Exception {
		final String METHOD_NAME = "transformISOPartitionDate";
		//log.entering(CLASS_NAME, METHOD_NAME);
		String result = "1970";
		// String tempResult = null;
		Date date = null;
		if (fieldvalue == null)
			return result;
		// stdTimeFormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));

		// stdHeaderTimeFormatCT.setTimeZone(TimeZone.getTimeZone("CST"));

		try {
			/*
			 * date = stdHeaderTimeFormatCT.parse(fieldvalue);
			 * tempResult=stdHeaderTimeFormatCT.format(date);
			 * if(fieldvalue.equals(tempResult)){ result =
			 * stdTimeFormatUTC.format(date); date= stdTimeFormat.parse(result);
			 * result = stdDateFormat.format(date);
			 */
			date = headerTimeFormat.parse(fieldvalue);
			if (date != null)
				result = stdDateFormat.format(date);
		} catch (final Exception e) {
			processLogEvent(fieldname,
					FordPassAprConstants.EXCEPTION_PARTITIONHEADER_TS_FIELD);

		}/*
		 * else throw new Exception(); } catch (final Exception e1) {
		 * e1.printStackTrace(); throw new Exception( fieldname +
		 * FordPassAprConstants.EXCEPTION_PC_FIELD); }
		 */
		return result;

	}

	public static boolean validateHeaderTimestamp(String fieldvalue) {
		boolean valid = false;
		Date date = null;
		String tempDate = null;
		if (fieldvalue == null)
			valid = false;
		else {
			try {
				if (!fieldvalue.matches(headerPattern))
					throw new Exception();
				date = headerTimeFormat.parse(fieldvalue);
				tempDate = headerTimeFormat.format(date);
				if (fieldvalue.equals(tempDate))
					valid = true;
			} catch (final Exception e) {
				valid = false;
			}
		}
		return valid;
	}

	public static boolean formatDate(String fieldvalue) {
		Date date = null;
		try {
			date = headerTimeFormat.parse(fieldvalue);
			headerTimeFormat.format(date);
			return true;
		} catch (final Exception e1) {
			processLogEvent(
					FordPassAprConstants.EXCEPTION_HEADER_TS_FIELD_NAME,
					FordPassAprConstants.EXCEPTION_HEADER_TS_FIELD);
		}
		return false;
	}

	// Doesn't work well for numbers more than 9 digits
	public static Integer intParser(String fieldvalue, String fieldname) {
		if (fieldvalue == null)
			return null;
		try {
			return Integer.parseInt(fieldvalue.toString());
		} catch (final Exception nfe) {
			processLogEvent(fieldname,
					FordPassAprConstants.EXCEPTION_INTEGER_FIELD);
			return null;
		}

	}

	public static Long longParser(String fieldvalue, String fieldname) {
			if (fieldvalue == null) {

			return null;
		}
		try {
			return Long.parseLong(fieldvalue);
		} catch (final Exception nfe) {
			processLogEvent(fieldname,
					FordPassAprConstants.EXCEPTION_INTEGER_FIELD);
			return null;
		}

	}

	public static String stringParser(String fieldvalue, String fieldname) {
		// TODO Auto-generated method stub
		if (fieldvalue == null)
			return null;
		try {
			if (fieldvalue instanceof String) {
				return fieldvalue.toString();

			} else {
				processLogEvent(fieldname,
						FordPassAprConstants.EXCEPTION_STRING_FIELD);
				return null;
			}
		} catch (final Exception e) {
			processLogEvent(fieldname,
					FordPassAprConstants.EXCEPTION_STRING_FIELD);
			return null;

		}
	}

	public static Double doubleParser(String fieldvalue, String fieldname) {
		if (fieldvalue == null)
			return null;
		try {
			return Double.parseDouble(fieldvalue.toString());
		} catch (final Exception e) {
			processLogEvent(fieldname,
					FordPassAprConstants.EXCEPTION_INTEGER_FIELD);
			return null;
		}
	}

	public static String scratchGUID() {
		// TODO Auto-generated method stub
		String str = CvdpConstants.INVALID_TABLE_PII_REMOVED;
		return str;
	}

	// generating sha key
	public static void setShaKey(String shaData, String WorkflowName, BaseBO obj)
			throws Exception {
		String returnShakey = null;
		/*
		 * if (customAttrDynData.get(0) == null ||customAttrDynData.get(7) ==
		 * null ||customAttrDynData.get(9)== null) { throw new Exception(
		 * FordPassAprConstants.CUSTOM_ATTRIBUTES_DYNAMIC_DATATYPE +
		 * FordPassAprConstants.SHA_KEY_GENERATION_DETAILS); } else {
		 */
		try {

			returnShakey = HashUtil.calculateSHA256(shaData);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(WorkflowName
					+ FordPassAprConstants.PAYLOAD_SHAKEY_ERR_DETAILS);

		}
		obj.setSha_key(returnShakey);

	}

	// }

	public static String chunkAsOneString(ArrayList<String> Record) {

		// TODO Auto-generated method stub
		StringBuffer sbf1 = new StringBuffer();
		sbf1.append(Record.get(0));
		for (int i = 1; i < Record.size(); i++) {
			sbf1.append(",").append(Record.get(i));
		}
		return sbf1.toString();

	}
	
	public static String chunkAsOneStringExceptLastColumn(ArrayList<String> Record, int columnsToIgnore) {

		// TODO Auto-generated method stub
		StringBuffer sbf1 = new StringBuffer();
		sbf1.append(Record.get(0));
		for (int i = 1; i < Record.size()-columnsToIgnore; i++) {
			sbf1.append(",").append(Record.get(i));
		}
		return sbf1.toString();

	}

	public static void processLogEvent(final String field, final String message) {
		processStatusDetails = processStatusDetails + "[" + field + "]"
				+ message;
	}

	public static boolean setProcessState() {
		// TODO Auto-generated method stub
		boolean errormsg = false;

		if (!processStatusDetails.equals("")) {
			errormsg = true;
		} else {
			errormsg = false;
		}
		return errormsg;
	}

	public static String setProcessDetails() {
		// TODO Auto-generated method stub
		return processStatusDetails;
	}

	public static void resetProcessState() {
		// TODO Auto-generated method stub
		processStatusDetails = "";
	}

	public static void validateMandatoryField(String fieldvalue,
			String fieldname) {
		if (fieldvalue == null)
			processLogEvent(fieldname, FordPassAprConstants.REQUIRED_FIELDS);
	}
	
	public static String validateWinningMovesCtrCode(String fieldvalue,String fieldname){
		if(fieldvalue == null){
			processLogEvent(fieldname, FordPassAprConstants.EXCEPTION_COUNTRYCODE_FIELD);
			return fieldvalue;
		}
		if (fieldvalue.equals(FordPassAprConstants.USA_VALUE)){
			return stringParser(fieldvalue,fieldname);		          
		}
		else{
			processLogEvent(fieldname, FordPassAprConstants.EXCEPTION_COUNTRYCODE_FIELD);
		}
		return null;			
	}
	
	// Validate TEN_REVERSE_TYPE field value is N/A/L or not 
	public static void validateTenReverseTypeField(String fieldvalue,
			String fieldname) {
		if ( !((fieldvalue.equalsIgnoreCase("N")) ||
				(fieldvalue.equalsIgnoreCase("A")) ||
				(fieldvalue.equalsIgnoreCase("L"))) ){
			processLogEvent(fieldname, FordPassAprConstants.EXCEPTION_TEN_REVERSE_TYPE_FIELD);
		}	
	}
	
	//Populate the partition column for the internalAuditYear
	public static String internalAuditYear() {
			return stdDateFormat.format(ConversionUtil.getUtcCalendar(new GregorianCalendar()).getTime());
	}
	
	public static String findPartitionYear(String fieldvalue,String fieldname) {
		String partitionYear = "" ;
		try {
				Date parsedDate = stdTimeFormatUTC.parse(fieldvalue);
				partitionYear = stdDateFormat.format(parsedDate);
		}catch(final Exception e){
			 processLogEvent(fieldname, "Exception occurred while partitioning timestamp");
		}
		return partitionYear ;
	}
}
