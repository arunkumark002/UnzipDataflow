package com.ford.datafactory.batch.bo;

public class UsersBO extends BaseBO {
	
	private Long usr_id;
	private String usr_block_date;
	private Long usr_com_id;
	private String usr_email;
	private String usr_email_permission;
	private String usr_ext_id;
	private String usr_first_name;
	private String usr_last_login_date;
	private String usr_last_name;
	private String usr_lng_code;
	private Long usr_loc_id;
	private String usr_login;
	private String usr_mobile;
	private String usr_module;
	private String usr_phone;
	private String usr_pwd_change_date;
	private Integer usr_pwd_failure_counter;
	private String usr_status;
	private String usr_task_notify;
	private String usr_audit_cd;
	private Long usr_audit_cu;
	private String usr_audit_md;
	private Long usr_audit_mu;
	private String usr_audit_rd;
	private Long usr_audit_ru;
	private Integer partition_year;
	private String cntry_c;
	private String prg_code; // FPA �  enhancement CR# F177282 - US1073590 - Addition of prg_code attribute
	
	public Long getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(Long usr_id) {
		this.usr_id = usr_id;
	}
	public String getUsr_audit_cd() {
		return usr_audit_cd;
	}
	public void setUsr_audit_cd(String usr_audit_cd) {
		this.usr_audit_cd = usr_audit_cd;
	}
	public Long getUsr_audit_cu() {
		return usr_audit_cu;
	}
	public void setUsr_audit_cu(Long usr_audit_cu) {
		this.usr_audit_cu = usr_audit_cu;
	}
	public String getUsr_audit_md() {
		return usr_audit_md;
	}
	public void setUsr_audit_md(String usr_audit_md) {
		this.usr_audit_md = usr_audit_md;
	}
	public Long getUsr_audit_mu() {
		return usr_audit_mu;
	}
	public void setUsr_audit_mu(Long usr_audit_mu) {
		this.usr_audit_mu = usr_audit_mu;
	}
	public String getUsr_audit_rd() {
		return usr_audit_rd;
	}
	public void setUsr_audit_rd(String usr_audit_rd) {
		this.usr_audit_rd = usr_audit_rd;
	}
	public Long getUsr_audit_ru() {
		return usr_audit_ru;
	}
	public void setUsr_audit_ru(Long usr_audit_ru) {
		this.usr_audit_ru = usr_audit_ru;
	}
	public String getUsr_block_date() {
		return usr_block_date;
	}
	public void setUsr_block_date(String usr_block_date) {
		this.usr_block_date = usr_block_date;
	}
	public Long getUsr_com_id() {
		return usr_com_id;
	}
	public void setUsr_com_id(Long usr_com_id) {
		this.usr_com_id = usr_com_id;
	}
	public String getUsr_email() {
		return usr_email;
	}
	public void setUsr_email(String usr_email) {
		this.usr_email = usr_email;
	}
	public String getUsr_email_permission() {
		return usr_email_permission;
	}
	public void setUsr_email_permission(String usr_email_permission) {
		this.usr_email_permission = usr_email_permission;
	}
	public String getUsr_ext_id() {
		return usr_ext_id;
	}
	public void setUsr_ext_id(String usr_ext_id) {
		this.usr_ext_id = usr_ext_id;
	}
	public String getUsr_first_name() {
		return usr_first_name;
	}
	public void setUsr_first_name(String usr_first_name) {
		this.usr_first_name = usr_first_name;
	}
	public String getUsr_last_login_date() {
		return usr_last_login_date;
	}
	public void setUsr_last_login_date(String usr_last_login_date) {
		this.usr_last_login_date = usr_last_login_date;
	}
	public String getUsr_last_name() {
		return usr_last_name;
	}
	public void setUsr_last_name(String usr_last_name) {
		this.usr_last_name = usr_last_name;
	}
	public String getUsr_lng_code() {
		return usr_lng_code;
	}
	public void setUsr_lng_code(String usr_lng_code) {
		this.usr_lng_code = usr_lng_code;
	}
	public Long getUsr_loc_id() {
		return usr_loc_id;
	}
	public void setUsr_loc_id(Long usr_loc_id) {
		this.usr_loc_id = usr_loc_id;
	}
	public String getUsr_login() {
		return usr_login;
	}
	public void setUsr_login(String usr_login) {
		this.usr_login = usr_login;
	}
	public String getUsr_mobile() {
		return usr_mobile;
	}
	public void setUsr_mobile(String usr_mobile) {
		this.usr_mobile = usr_mobile;
	}
	public String getUsr_module() {
		return usr_module;
	}
	public void setUsr_module(String usr_module) {
		this.usr_module = usr_module;
	}
	public String getUsr_phone() {
		return usr_phone;
	}
	public void setUsr_phone(String usr_phone) {
		this.usr_phone = usr_phone;
	}
	public String getUsr_pwd_change_date() {
		return usr_pwd_change_date;
	}
	public void setUsr_pwd_change_date(String usr_pwd_change_date) {
		this.usr_pwd_change_date = usr_pwd_change_date;
	}
	public Integer getUsr_pwd_failure_counter() {
		return usr_pwd_failure_counter;
	}
	public void setUsr_pwd_failure_counter(Integer usr_pwd_failure_counter) {
		this.usr_pwd_failure_counter = usr_pwd_failure_counter;
	}
	public String getUsr_status() {
		return usr_status;
	}
	public void setUsr_status(String usr_status) {
		this.usr_status = usr_status;
	}
	public String getUsr_task_notify() {
		return usr_task_notify;
	}
	public void setUsr_task_notify(String usr_task_notify) {
		this.usr_task_notify = usr_task_notify;
	}
	public Integer getPartition_year() {
		return partition_year;
	}
	public void setPartition_year(Integer partition_year) {
		this.partition_year = partition_year;
	}
	public String getCntry_c() {
		return cntry_c;
	}
	public void setCntry_c(String cntry_c) {
		this.cntry_c = cntry_c;
	}
	
	// FPA �  enhancement CR# F177282 - US1073590 - Addition of prg_code attribute
	public String getPrg_code() {
		return prg_code;
	}
	public void setPrg_code(String prg_code) {
		this.prg_code = prg_code;
	}	

}
