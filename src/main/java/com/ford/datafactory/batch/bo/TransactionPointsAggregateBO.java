package com.ford.datafactory.batch.bo;

public class TransactionPointsAggregateBO extends BaseBO{
	private Long tpt_points;
	private Long tpt_id;
	private String ptp_code;
	private Long tpt_acc_id;
	private String tpt_audit_cd;
	private String tpt_audit_md;
	private String tpt_book_date;
	private Long tpt_src_trn_id;
	private String tpt_status;
	private String ctr_code_transaction;
	private String ctr_code_home;
	private Integer partition_year;
	private Double tptx_points_value;
	private String tpt_expiration_date;
	
	public Long getTpt_points() {
		return tpt_points;
	}
	public void setTpt_points(Long tpt_points) {
		this.tpt_points = tpt_points;
	}
	public Long getTpt_id() {
		return tpt_id;
	}
	public void setTpt_id(Long tpt_id) {
		this.tpt_id = tpt_id;
	}
	public String getPtp_code() {
		return ptp_code;
	}
	public void setPtp_code(String ptp_code) {
		this.ptp_code = ptp_code;
	}
	public Long getTpt_acc_id() {
		return tpt_acc_id;
	}
	public void setTpt_acc_id(Long tpt_acc_id) {
		this.tpt_acc_id = tpt_acc_id;
	}
	public String getTpt_audit_cd() {
		return tpt_audit_cd;
	}
	public void setTpt_audit_cd(String tpt_audit_cd) {
		this.tpt_audit_cd = tpt_audit_cd;
	}
	public String getTpt_audit_md() {
		return tpt_audit_md;
	}
	public void setTpt_audit_md(String tpt_audit_md) {
		this.tpt_audit_md = tpt_audit_md;
	}
	public String getTpt_book_date() {
		return tpt_book_date;
	}
	public void setTpt_book_date(String tpt_book_date) {
		this.tpt_book_date = tpt_book_date;
	}
	public Long getTpt_src_trn_id() {
		return tpt_src_trn_id;
	}
	public void setTpt_src_trn_id(Long tpt_src_trn_id) {
		this.tpt_src_trn_id = tpt_src_trn_id;
	}
	public String getTpt_status() {
		return tpt_status;
	}
	public void setTpt_status(String tpt_status) {
		this.tpt_status = tpt_status;
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
	public Double getTptx_points_value() {
		return tptx_points_value;
	}
	public void setTptx_points_value(Double tptx_points_value) {
		this.tptx_points_value = tptx_points_value;
	}
	public String getTpt_expiration_date() {
		return tpt_expiration_date;
	}
	public void setTpt_expiration_date(String tpt_expiration_date) {
		this.tpt_expiration_date = tpt_expiration_date;
	}
}
