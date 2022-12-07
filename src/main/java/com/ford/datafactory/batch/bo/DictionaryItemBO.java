package com.ford.datafactory.batch.bo;

public class DictionaryItemBO extends BaseBO {

	private Long unique_id;

	private String dic_description;

	private String dic_domain;

	private String dit_code;

	private String dit_description;

	private String dit_name;

	private String cntry_c;

	//FPA �  enhancement CR# F177282 - US1068018 - Addition of prg_code attribute
	private String prg_code;

	public Long getUnique_id() {
		return unique_id;
	}

	public void setUnique_id(Long unique_id) {
		this.unique_id = unique_id;
	}

	public String getDic_description() {
		return dic_description;
	}

	public void setDic_description(String dic_description) {
		this.dic_description = dic_description;
	}

	public String getDic_domain() {
		return dic_domain;
	}

	public void setDic_domain(String dic_domain) {
		this.dic_domain = dic_domain;
	}

	public String getDit_code() {
		return dit_code;
	}

	public void setDit_code(String dit_code) {
		this.dit_code = dit_code;
	}

	public String getDit_description() {
		return dit_description;
	}

	public void setDit_description(String dit_description) {
		this.dit_description = dit_description;
	}

	public String getDit_name() {
		return dit_name;
	}

	public void setDit_name(String dit_name) {
		this.dit_name = dit_name;
	}

	public String getCntry_c() {
		return cntry_c;
	}

	public void setCntry_c(String cntry_c) {
		this.cntry_c = cntry_c;
	}

	public String getPrg_code() {
		return prg_code;
	}

	public void setPrg_code(String prg_code) {
		this.prg_code = prg_code;
	}


}
