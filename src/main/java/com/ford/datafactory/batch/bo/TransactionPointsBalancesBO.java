package com.ford.datafactory.batch.bo;

public class TransactionPointsBalancesBO extends BaseBO {

	private Long tpb_id;
	
	private String ptp_code;
	
	private Long tpb_trn_id;
	
	private Long tpb_points;
	
	private String ctr_code_transaction;
		
	//partition columns
	private String ctr_code_home;
	
	private Integer partition_year;
	
	private String prt_code;

	

	public Long getTpb_id() {
		return tpb_id;
	}

	public void setTpb_id(Long tpb_id) {
		this.tpb_id = tpb_id;
	}

	public String getPtp_code() {
		return ptp_code;
	}

	public void setPtp_code(String ptp_code) {
		this.ptp_code = ptp_code;
	}

	public Long getTpb_trn_id() {
		return tpb_trn_id;
	}

	public void setTpb_trn_id(Long tpb_trn_id) {
		this.tpb_trn_id = tpb_trn_id;
	}

	public Long getTpb_points() {
		return tpb_points;
	}

	public void setTpb_points(Long tpb_points) {
		this.tpb_points = tpb_points;
	}

	public String getCtr_code_transaction() {
		return ctr_code_transaction;
	}

	public void setCtr_code_transaction(String ctr_code_transaction) {
		this.ctr_code_transaction = ctr_code_transaction;
	}

	public String getCtr_code_home() {
		return ctr_code_home;
	}

	public void setCtr_code_home(String ctr_code_home) {
		this.ctr_code_home = ctr_code_home;
	}

	public Integer getPartition_year() {
		return partition_year;
	}

	public void setPartition_year(Integer partition_year) {
		this.partition_year = partition_year;
	}
	public String getPrt_code() {
		return prt_code;
	}

	public void setPrt_code(String prt_code) {
		this.prt_code = prt_code;
	}
	
	
}

