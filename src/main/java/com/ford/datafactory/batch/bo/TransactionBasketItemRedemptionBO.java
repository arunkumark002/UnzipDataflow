package com.ford.datafactory.batch.bo;

public class TransactionBasketItemRedemptionBO extends BaseBO {

	private Long tpd_id;
	private String ptp_code;
	private String tpd_pcd_code;
	private Long tpd_pcd_id;
	private Long tpd_points;
	private Long tpd_prd_id;
	private Double tpd_quantity;
	private Long tpd_trn_id;
	private Double tpd_value;
	private String ctr_code_transaction;
	private String ctr_code_home;
	private int partition_year;

	public Long getTpd_id() {
		return tpd_id;
	}

	public void setTpd_id(Long tpd_id) {
		this.tpd_id = tpd_id;
	}

	public String getPtp_code() {
		return ptp_code;
	}

	public void setPtp_code(String ptp_code) {
		this.ptp_code = ptp_code;
	}

	public String getTpd_pcd_code() {
		return tpd_pcd_code;
	}

	public void setTpd_pcd_code(String tpd_pcd_code) {
		this.tpd_pcd_code = tpd_pcd_code;
	}

	public Long getTpd_pcd_id() {
		return tpd_pcd_id;
	}

	public void setTpd_pcd_id(Long tpd_pcd_id) {
		this.tpd_pcd_id = tpd_pcd_id;
	}

	public Long getTpd_points() {
		return tpd_points;
	}

	public void setTpd_points(Long tpd_points) {
		this.tpd_points = tpd_points;
	}

	public Long getTpd_prd_id() {
		return tpd_prd_id;
	}

	public void setTpd_prd_id(Long tpd_prd_id) {
		this.tpd_prd_id = tpd_prd_id;
	}

	public Double getTpd_quantity() {
		return tpd_quantity;
	}

	public void setTpd_quantity(Double tpd_quantity) {
		this.tpd_quantity = tpd_quantity;
	}

	public Long getTpd_trn_id() {
		return tpd_trn_id;
	}

	public void setTpd_trn_id(Long tpd_trn_id) {
		this.tpd_trn_id = tpd_trn_id;
	}

	public Double getTpd_value() {
		return tpd_value;
	}

	public void setTpd_value(Double tpd_value) {
		this.tpd_value = tpd_value;
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

	public int getPartition_year() {
		return partition_year;
	}

	public void setPartition_year(int partition_year) {
		this.partition_year = partition_year;
	}

}
