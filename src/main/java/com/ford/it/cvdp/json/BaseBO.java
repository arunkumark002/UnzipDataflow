package com.ford.it.cvdp.json;

import com.ford.it.cvdp.util.SSPConstants;

public class BaseBO {
	
	private String sha_key;
	private String process_status = SSPConstants.FILE_STATUS_TRANSFORMATION_SUCCESSFUL;;
	private String process_status_details;
	private String process_status_date_time_utc;
	private String raw_payload;
	private String input_record_sha_key;
	
	public String getSha_key() {
		return sha_key;
	}

	public void setSha_key(String sha_key) {
		this.sha_key = sha_key;
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

	public String getRaw_payload() {
		return raw_payload;
	}

	public void setRaw_payload(String raw_payload) {
		this.raw_payload = raw_payload;
	}

	public String getInput_record_sha_key() {
		return input_record_sha_key;
	}

	public void setInput_record_sha_key(String input_record_sha_key) {
		this.input_record_sha_key = input_record_sha_key;
	}
}