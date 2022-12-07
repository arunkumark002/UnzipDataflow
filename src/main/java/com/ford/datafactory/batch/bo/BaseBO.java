package com.ford.datafactory.batch.bo;



public class BaseBO {
	
	private String sha_key;
	
	private String raw_payload;
	
	private String header_timestamp_utc;
	
	private String process_status;
	
	private String process_status_details;
	
	private String process_status_date_time_utc;

	public String getSha_key() {
		return sha_key;
	}

	public void setSha_key(String sha_key) {
		this.sha_key = sha_key;
	}

	
	public String getRaw_payload() {
		return raw_payload;
	}

	public void setRaw_payload(String raw_payload) {
		this.raw_payload = raw_payload;
	}

	public String getHeader_timestamp_utc() {
		return header_timestamp_utc;
	}

	public void setHeader_timestamp_utc(String header_timestamp_utc) {
		this.header_timestamp_utc = header_timestamp_utc;
	}

	public String getProcess_status() {
		return process_status;
	}

	public void setProcess_status(String process_status) {
		this.process_status = process_status;
	}

	public String getProcess_status_details() {
		return process_status_details;
	}

	public void setProcess_status_details(String process_status_details) {
		this.process_status_details = process_status_details;
	}

	public String getProcess_status_date_time_utc() {
		return process_status_date_time_utc;
	}

	public void setProcess_status_date_time_utc(String process_status_date_time_utc) {
		this.process_status_date_time_utc = process_status_date_time_utc;
	}

	
}
