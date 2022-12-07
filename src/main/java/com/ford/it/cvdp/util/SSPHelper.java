package com.ford.it.cvdp.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class SSPHelper {

	private static final String CLASS_NAME = SSPHelper.class.getName();

	static DateFormat stdTimeFormatUTC = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static DateFormat stdTimeFormatCT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static DateFormat stdDateFormatYMD = new SimpleDateFormat("yyyy-MM-dd");
	private static final String DATE_TIME_FORMAT_IN = "yyyy-MM-dd'T'HH:mm:ssZ";

	static String pattern = "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}";
	static String YMDPattern="\\d{4}-\\d{2}-\\d{2}";


	public static String transformToPartitionDateFormat(final String strUTCDate,final String format) throws ParseException {
		Calendar datetime=convertStringToCalender(strUTCDate,format);
		final SimpleDateFormat dtf_partition = new SimpleDateFormat(format);
		final Date date = datetime.getTime();
		return dtf_partition.format(date);
	}

	public static Calendar convertStringToCalender(String strUTCDate,final String format) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = sdf.parse(strUTCDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static String parseDateTimestamp(String fieldvalue, String fieldname) {
		String tempResult = null;
		String result = null;
		Date date = null;
		if (fieldvalue == null)
			return null;
		stdTimeFormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
		stdTimeFormatCT.setTimeZone(TimeZone.getTimeZone("CST"));

		try {
			//if (!fieldvalue.matches(pattern))
			//	throw new Exception();
			date = stdTimeFormatCT.parse(fieldvalue);
			tempResult = stdTimeFormatCT.format(date);
			if (fieldvalue.equals(tempResult)) {
				result = stdTimeFormatUTC.format(date);
			} else
				throw new Exception();
		} catch (final Exception e1) {
			//processLogEvent(fieldname, SSPConstants.EXCEPTION_TS_FIELD);
		}
		return result;
	}

	public static String parseDateFormatYMD(String fieldvalue, String fieldname) throws Exception {
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
			throw new Exception(e1.getMessage());
		}
		return result;
	}

	public static String GetUTCdatetimeAsString() {
		stdTimeFormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
		final String utcTime = stdTimeFormatUTC.format(new Date());
		return utcTime;
	}

	public static String ConvertDateToUTC(String dateTs) {
		dateTs = dateTs.replace("T", " ");
		if(dateTs.contains("Z")){
			dateTs = dateTs.replace("Z","");
		}
		final DateTime dt1 = transformToDateTime(dateTs, "yyyy-MM-dd HH:mm:ss.SSS");
		final SimpleDateFormat dtf_partition = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		final Date date = dt1.toDate();
		String parseDt = dtf_partition.format(date);
		// System.out.println("In dateToLongDiagnosisTactic : The result  before is : " + parseDt);
		//resDtLg = date.getTime();
		// System.out.println("In dateToLongDiagnosisTactic : The resDtLg  after is : " + resDtLg);
		return parseDt;
	}

	public static DateTime transformToDateTime(final String datetime, final String format) {

		DateTime result = null;

		final DateTimeFormatter dtf = DateTimeFormat.forPattern(format);
		result = dtf.parseDateTime(datetime);

		return result;

	}

	/*public static Calendar transformToCalendarYYYYMMDDTHHMMSSxxxxxxxZ1(final String datetime, final String columnName)
			throws ParseException {

		Calendar result1 = Calendar.getInstance();
		String dtStr = datetime.substring(0,19);
		String znStr = datetime.substring(20,25);
		String dt = dtStr.replace("\"", "");
		char sg = datetime.charAt(19);
		String znHr = datetime.substring(20,22);
		String znMin = datetime.substring(23,25);
		String newHrStr = null;
		String newMinStr = null;
		if(sg == '+'){
			newHrStr = "-"+znHr;
			newMinStr = "-"+znMin;
		} else if(sg == '-') {
			newHrStr = "+"+znHr;
			newMinStr = "+"+znMin;
		}

		String dttime = dt+"Z";

		System.out.println("dttijme" + dttime);

		final DateTime dt1 = transformToDateTime(dttime.replace("\"",""), DATE_TIME_FORMAT_IN);

		//System.out.println(" Time " + dt1);
		result1 = dt1.toGregorianCalendar();
		Calendar result = ConversionUtil.getUtcCalendar(result1);

		//System.out.println("In transformToCalendarYYYYMMDDTHHMMSSxxxxxxxZ1 : The result  before is : " + result);
		result.add(Calendar.HOUR_OF_DAY, Integer.parseInt(newHrStr));
		result.add(Calendar.MINUTE, Integer.parseInt(newMinStr));

		//System.out.println("In transformToCalendarYYYYMMDDTHHMMSSxxxxxxxZ : The result after is : " + result);
		return result;

	}*/

	public static String transformToCalendarYYYYMMDDTHHMMSSxxxxxxxZ(final String datetime, final String columnName)
			throws ParseException {

		Calendar result1 = Calendar.getInstance();
		String dtStr = datetime.replace("\"", "");
		dtStr = dtStr.replace("T", " ");
		if(dtStr.contains("Z")){
			dtStr = dtStr.replace("Z","");
		}

		return dtStr;

	}

	public static boolean isValid(String dateStr,String dateFormat) {
		DateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);
		try {
			sdf.parse(dateStr);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public static String calculateSHA256(String stringToBeHashed) throws NoSuchAlgorithmException {
		if (stringToBeHashed == null) {
			return null;
		} else {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(stringToBeHashed.getBytes());
			return convertToHex(messageDigest.digest());
		}
	}

	public static String convertToHex(byte[] data) {
		StringBuffer hexStringBuffer = new StringBuffer();

		for(int i = 0; i < data.length; ++i) {
			int halfbyte = data[i] >>> 4 & 15;
			int var4 = 0;

			do {
				if (0 <= halfbyte && halfbyte <= 9) {
					hexStringBuffer.append((char)(48 + halfbyte));
				} else {
					hexStringBuffer.append((char)(97 + (halfbyte - 10)));
				}

				halfbyte = data[i] & 15;
			} while(var4++ < 1);
		}

		return hexStringBuffer.toString();
	}
}
