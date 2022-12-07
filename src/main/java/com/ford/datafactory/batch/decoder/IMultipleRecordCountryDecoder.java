package com.ford.datafactory.batch.decoder;

public interface IMultipleRecordCountryDecoder {

	    /**
	     * Decode
	     * 
	     * @param data
	     * @return String
	     * @throws Exception
	     */

		public abstract String decodeWithCntry(String timestamp,String data,String countryCode) throws Exception;


}
