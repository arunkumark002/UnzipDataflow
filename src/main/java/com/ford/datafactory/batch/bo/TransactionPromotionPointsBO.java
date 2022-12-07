package com.ford.datafactory.batch.bo;

public class TransactionPromotionPointsBO extends BaseBO{
	
	private Long tei_trl_id;
	
	private String ptp_code;
	
	private Long trp_id;
	
	private Long trp_points;
	
	private Long trp_trn_id;
	
	private String tei_condition_formula;
	
	private String tei_points_formula;
	
	private String tei_process_level;
	
	private String ctr_code_transaction;
	
	private String ctr_code_home;
	
	private Integer partition_year;
	
	private String prt_code;

	

	public Long getTei_trl_id() {
		return tei_trl_id;
	}

	public void setTei_trl_id(Long tei_trl_id) {
		this.tei_trl_id = tei_trl_id;
	}

	public String getPtp_code() {
		return ptp_code;
	}

	public void setPtp_code(String ptp_code) {
		this.ptp_code = ptp_code;
	}

	public Long getTrp_id() {
		return trp_id;
	}

	public void setTrp_id(Long trp_id) {
		this.trp_id = trp_id;
	}

	public Long getTrp_points() {
		return trp_points;
	}

	public void setTrp_points(Long trp_points) {
		this.trp_points = trp_points;
	}

	public Long getTrp_trn_id() {
		return trp_trn_id;
	}

	public void setTrp_trn_id(Long trp_trn_id) {
		this.trp_trn_id = trp_trn_id;
	}

	public String getTei_condition_formula() {
		return tei_condition_formula;
	}

	public void setTei_condition_formula(String tei_condition_formula) {
		this.tei_condition_formula = tei_condition_formula;
	}

	public String getTei_points_formula() {
		return tei_points_formula;
	}

	public void setTei_points_formula(String tei_points_formula) {
		this.tei_points_formula = tei_points_formula;
	}

	public String getTei_process_level() {
		return tei_process_level;
	}

	public void setTei_process_level(String tei_process_level) {
		this.tei_process_level = tei_process_level;
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