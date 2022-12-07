package com.ford.datafactory.batch.bo;

public class TransactionPromotionCountersBO extends BaseBO{

	private	Long	tch_id	;
	private	String	tch_audit_cd	;
	private	Long	tch_audit_cu	;
	private	Long	tch_cav_id	;
	private	String	tch_change_date	;
	private	Double	tch_change_value	;
	private	Double	tch_prev_value	;
	private	Long	tch_src_trn_id	;
	private	Long	tch_trl_cnt_id	;
	private	Long	tch_trl_id	;
	private	Double	tch_value	;
	private	String	ten_domain	;
	private	String	ten_code	;
	private	Integer	ten_order	;
	private	String	ten_cyclic	;
	private	String	ten_condition_formula	;
	private	String	tenx_reversible	;
	private	String	ten_value_formula	;
	private	Long	ten_limit	;
	private	String	ten_description	;
	private	Integer	ten_accum_multi	;
	private	String	ten_process_level	;
	private	String	ten_trl_code	;
	private	String	ten_accum_unit	;
	private	Long	ten_trl_id	;
	private String  ctr_code_transaction;
	private String  ctr_code_home;
	private int     partition_year;
	private String  ten_reverse_type;
	
	
	public Long getTch_id() {
		return tch_id;
	}
	public void setTch_id(Long tch_id) {
		this.tch_id = tch_id;
	}
	public String getTch_audit_cd() {
		return tch_audit_cd;
	}
	public void setTch_audit_cd(String tch_audit_cd) {
		this.tch_audit_cd = tch_audit_cd;
	}
	public Long getTch_audit_cu() {
		return tch_audit_cu;
	}
	public void setTch_audit_cu(Long tch_audit_cu) {
		this.tch_audit_cu = tch_audit_cu;
	}
	public Long getTch_cav_id() {
		return tch_cav_id;
	}
	public void setTch_cav_id(Long tch_cav_id) {
		this.tch_cav_id = tch_cav_id;
	}
	public String getTch_change_date() {
		return tch_change_date;
	}
	public void setTch_change_date(String tch_change_date) {
		this.tch_change_date = tch_change_date;
	}
	public Double getTch_change_value() {
		return tch_change_value;
	}
	public void setTch_change_value(Double tch_change_value) {
		this.tch_change_value = tch_change_value;
	}
	public Double getTch_prev_value() {
		return tch_prev_value;
	}
	public void setTch_prev_value(Double tch_prev_value) {
		this.tch_prev_value = tch_prev_value;
	}
	public Long getTch_src_trn_id() {
		return tch_src_trn_id;
	}
	public void setTch_src_trn_id(Long tch_src_trn_id) {
		this.tch_src_trn_id = tch_src_trn_id;
	}
	public Long getTch_trl_cnt_id() {
		return tch_trl_cnt_id;
	}
	public void setTch_trl_cnt_id(Long tch_trl_cnt_id) {
		this.tch_trl_cnt_id = tch_trl_cnt_id;
	}
	public Long getTch_trl_id() {
		return tch_trl_id;
	}
	public void setTch_trl_id(Long tch_trl_id) {
		this.tch_trl_id = tch_trl_id;
	}
	public Double getTch_value() {
		return tch_value;
	}
	public void setTch_value(Double tch_value) {
		this.tch_value = tch_value;
	}
	public String getTen_domain() {
		return ten_domain;
	}
	public void setTen_domain(String ten_domain) {
		this.ten_domain = ten_domain;
	}
	public String getTen_code() {
		return ten_code;
	}
	public void setTen_code(String ten_code) {
		this.ten_code = ten_code;
	}
	public Integer getTen_order() {
		return ten_order;
	}
	public void setTen_order(Integer ten_order) {
		this.ten_order = ten_order;
	}
	public String getTen_cyclic() {
		return ten_cyclic;
	}
	public void setTen_cyclic(String ten_cyclic) {
		this.ten_cyclic = ten_cyclic;
	}
	public String getTen_condition_formula() {
		return ten_condition_formula;
	}
	public void setTen_condition_formula(String ten_condition_formula) {
		this.ten_condition_formula = ten_condition_formula;
	}
	public String getTenx_reversible() {
		return tenx_reversible;
	}
	public void setTenx_reversible(String tenx_reversible) {
		this.tenx_reversible = tenx_reversible;
	}
	public String getTen_value_formula() {
		return ten_value_formula;
	}
	public void setTen_value_formula(String ten_value_formula) {
		this.ten_value_formula = ten_value_formula;
	}
	public Long getTen_limit() {
		return ten_limit;
	}
	public void setTen_limit(Long ten_limit) {
		this.ten_limit = ten_limit;
	}
	public String getTen_description() {
		return ten_description;
	}
	public void setTen_description(String ten_description) {
		this.ten_description = ten_description;
	}
	public Integer getTen_accum_multi() {
		return ten_accum_multi;
	}
	public void setTen_accum_multi(Integer ten_accum_multi) {
		this.ten_accum_multi = ten_accum_multi;
	}
	public String getTen_process_level() {
		return ten_process_level;
	}
	public void setTen_process_level(String ten_process_level) {
		this.ten_process_level = ten_process_level;
	}
	public String getTen_trl_code() {
		return ten_trl_code;
	}
	public void setTen_trl_code(String ten_trl_code) {
		this.ten_trl_code = ten_trl_code;
	}
	public String getTen_accum_unit() {
		return ten_accum_unit;
	}
	public void setTen_accum_unit(String ten_accum_unit) {
		this.ten_accum_unit = ten_accum_unit;
	}
	public Long getTen_trl_id() {
		return ten_trl_id;
	}
	public void setTen_trl_id(Long ten_trl_id) {
		this.ten_trl_id = ten_trl_id;
	}
	public int getPartition_year() {
		return partition_year;
	}
	public void setPartition_year(int partition_year) {
		this.partition_year = partition_year;
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
	public String getTen_reverse_type() {
		return ten_reverse_type;
	}
	public void setTen_reverse_type(String ten_reverse_type) {
		this.ten_reverse_type = ten_reverse_type;
	}

    
	
}
