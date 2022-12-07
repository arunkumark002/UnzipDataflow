package com.ford.datafactory.batch.bo;

public class TransactionAttributesBO extends BaseBO {

	private Long tra_id;
	private Long tra_trn_id;
	private String tra_value;
	private String tea_attr_domain;
	private String tea_condition_formula;
	private String tea_process_level;
	private Long tea_trl_id;
	private String tea_value_formula;
	private String atr_code;
	private String atr_name;
	private String atr_description;
	private String atr_default_value;
	private String ctr_code_transaction;
	private String ctr_code_home;
	private Integer partition_year;
	
	public Long getTra_id() {
		return tra_id;
	}
	public void setTra_id(Long tra_id) {
		this.tra_id = tra_id;
	}
	public Long getTra_trn_id() {
		return tra_trn_id;
	}
	public void setTra_trn_id(Long tra_trn_id) {
		this.tra_trn_id = tra_trn_id;
	}
	public String getTra_value() {
		return tra_value;
	}
	public void setTra_value(String tra_value) {
		this.tra_value = tra_value;
	}
	public String getTea_attr_domain() {
		return tea_attr_domain;
	}
	public void setTea_attr_domain(String tea_attr_domain) {
		this.tea_attr_domain = tea_attr_domain;
	}
	public String getTea_condition_formula() {
		return tea_condition_formula;
	}
	public void setTea_condition_formula(String tea_condition_formula) {
		this.tea_condition_formula = tea_condition_formula;
	}
	public String getTea_process_level() {
		return tea_process_level;
	}
	public void setTea_process_level(String tea_process_level) {
		this.tea_process_level = tea_process_level;
	}
	public Long getTea_trl_id() {
		return tea_trl_id;
	}
	public void setTea_trl_id(Long tea_trl_id) {
		this.tea_trl_id = tea_trl_id;
	}
	public String getTea_value_formula() {
		return tea_value_formula;
	}
	public void setTea_value_formula(String tea_value_formula) {
		this.tea_value_formula = tea_value_formula;
	}
	public String getAtr_code() {
		return atr_code;
	}
	public void setAtr_code(String atr_code) {
		this.atr_code = atr_code;
	}
	public String getAtr_name() {
		return atr_name;
	}
	public void setAtr_name(String atr_name) {
		this.atr_name = atr_name;
	}
	public String getAtr_description() {
		return atr_description;
	}
	public void setAtr_description(String atr_description) {
		this.atr_description = atr_description;
	}
	public String getAtr_default_value() {
		return atr_default_value;
	}
	public void setAtr_default_value(String atr_default_value) {
		this.atr_default_value = atr_default_value;
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

}
