package com.ford.datafactory.batch.bo;

public class TransactionRecognitionTiersBO extends BaseBO {

	private Long trc_id;
	private Long trn_id;
	private String trl_code;
	private String rgs_code;
	private String rgt_code;
	private String ter_condition_formula;
	private String ter_process_level;
	private String ctr_code;
	private String partition_cntry_c;
	private Integer partition_year;
	
	public Long getTrc_id() {
		return trc_id;
	}
	public void setTrc_id(Long trc_id) {
		this.trc_id = trc_id;
	}
	public Long getTrn_id() {
		return trn_id;
	}
	public void setTrn_id(Long trn_id) {
		this.trn_id = trn_id;
	}
	public String getTrl_code() {
		return trl_code;
	}
	public void setTrl_code(String trl_code) {
		this.trl_code = trl_code;
	}
	public String getRgs_code() {
		return rgs_code;
	}
	public void setRgs_code(String rgs_code) {
		this.rgs_code = rgs_code;
	}
	public String getRgt_code() {
		return rgt_code;
	}
	public void setRgt_code(String rgt_code) {
		this.rgt_code = rgt_code;
	}
	public String getTer_condition_formula() {
		return ter_condition_formula;
	}
	public void setTer_condition_formula(String ter_condition_formula) {
		this.ter_condition_formula = ter_condition_formula;
	}
	public String getTer_process_level() {
		return ter_process_level;
	}
	public void setTer_process_level(String ter_process_level) {
		this.ter_process_level = ter_process_level;
	}
	public String getCtr_code() {
		return ctr_code;
	}
	public void setCtr_code(String ctr_code) {
		this.ctr_code = ctr_code;
	}
	public String getPartition_cntry_c() {
		return partition_cntry_c;
	}
	public void setPartition_cntry_c(String partition_cntry_c) {
		this.partition_cntry_c = partition_cntry_c;
	}
	public Integer getPartition_year() {
		return partition_year;
	}
	public void setPartition_year(Integer partition_year) {
		this.partition_year = partition_year;
	}
}
