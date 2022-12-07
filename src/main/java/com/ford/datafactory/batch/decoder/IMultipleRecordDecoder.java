package com.ford.datafactory.batch.decoder;

public interface IMultipleRecordDecoder {

	    /**
	     * Decode
	     * 
	     * @param data
	     * @return String
	     * @throws Exception
	     */
	    public abstract String decode(String filename,String data) throws Exception;


}
