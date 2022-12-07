package com.ford.datafactory.batch.bo;

public class TransactionRuleBO extends BaseBO{

	private Long trl_id;
	private String mcp_code;
	private String trl_audit_cd;
	private Long trl_audit_cu;
	private String trl_audit_md;
	private Long trl_audit_mu;
	private String trl_audit_rd;
	private Long trl_audit_ru;
	private String trl_code;
	private String trl_condition_formula;
	private String trl_description;
	private String trl_end_date;
	private String trl_name;
	private String trl_start_date;
	private String trl_status;
	private String trl_trn_channel;
	private String trl_trn_type;
	private String trg_code;
	private String trg_description;
	private Integer partition_year;
	private String cntry_c;
	private Sgm_code sgm_code;
	
	
	
	public Integer getPartition_year() {
		return partition_year;
	}
	public void setPartition_year(Integer partition_year) {
		this.partition_year = partition_year;
	}
	public Long getTrl_id() {
		return trl_id;
	}
	public void setTrl_id(Long trl_id) {
		this.trl_id = trl_id;
	}
	public String getMcp_code() {
		return mcp_code;
	}
	public void setMcp_code(String mcp_code) {
		this.mcp_code = mcp_code;
	}
	public String getTrl_audit_cd() {
		return trl_audit_cd;
	}
	public void setTrl_audit_cd(String trl_audit_cd) {
		this.trl_audit_cd = trl_audit_cd;
	}
	public Long getTrl_audit_cu() {
		return trl_audit_cu;
	}
	public void setTrl_audit_cu(Long trl_audit_cu) {
		this.trl_audit_cu = trl_audit_cu;
	}
	public String getTrl_audit_md() {
		return trl_audit_md;
	}
	public void setTrl_audit_md(String trl_audit_md) {
		this.trl_audit_md = trl_audit_md;
	}
	public Long getTrl_audit_mu() {
		return trl_audit_mu;
	}
	public void setTrl_audit_mu(Long trl_audit_mu) {
		this.trl_audit_mu = trl_audit_mu;
	}
	public String getTrl_audit_rd() {
		return trl_audit_rd;
	}
	public void setTrl_audit_rd(String trl_audit_rd) {
		this.trl_audit_rd = trl_audit_rd;
	}
	public Long getTrl_audit_ru() {
		return trl_audit_ru;
	}
	public void setTrl_audit_ru(Long trl_audit_ru) {
		this.trl_audit_ru = trl_audit_ru;
	}
	public String getTrl_code() {
		return trl_code;
	}
	public void setTrl_code(String trl_code) {
		this.trl_code = trl_code;
	}
	public String getTrl_condition_formula() {
		return trl_condition_formula;
	}
	public void setTrl_condition_formula(String trl_condition_formula) {
		this.trl_condition_formula = trl_condition_formula;
	}
	public String getTrl_description() {
		return trl_description;
	}
	public void setTrl_description(String trl_description) {
		this.trl_description = trl_description;
	}
	public String getTrl_end_date() {
		return trl_end_date;
	}
	public void setTrl_end_date(String trl_end_date) {
		this.trl_end_date = trl_end_date;
	}
	public String getTrl_name() {
		return trl_name;
	}
	public void setTrl_name(String trl_name) {
		this.trl_name = trl_name;
	}
	public String getTrl_start_date() {
		return trl_start_date;
	}
	public void setTrl_start_date(String trl_start_date) {
		this.trl_start_date = trl_start_date;
	}
	public String getTrl_status() {
		return trl_status;
	}
	public void setTrl_status(String trl_status) {
		this.trl_status = trl_status;
	}
	public String getTrl_trn_channel() {
		return trl_trn_channel;
	}
	public void setTrl_trn_channel(String trl_trn_channel) {
		this.trl_trn_channel = trl_trn_channel;
	}
	public String getTrl_trn_type() {
		return trl_trn_type;
	}
	public void setTrl_trn_type(String trl_trn_type) {
		this.trl_trn_type = trl_trn_type;
	}
	public String getTrg_code() {
		return trg_code;
	}
	public void setTrg_code(String trg_code) {
		this.trg_code = trg_code;
	}
	public String getTrg_description() {
		return trg_description;
	}
	public void setTrg_description(String trg_description) {
		this.trg_description = trg_description;
	}
	
	public String getCntry_c() {
		return cntry_c;
	}

	public void setCntry_c(String cntry_c) {
		this.cntry_c = cntry_c;
	}
	public Sgm_code getSgm_code() {
		return sgm_code;
	}
	public void setSgm_code(Sgm_code sgm_code) {
		this.sgm_code = sgm_code;
	}
	
}
