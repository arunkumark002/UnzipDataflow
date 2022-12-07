package com.ford.datafactory.batch.bo;

public class TransactionBasketItemEarnProductsBO extends BaseBO {

	private String prt_code;
	private String prd_description;	
	private Long prd_manufacturer_com_id;
	private String prd_name;	
	private Double prd_unit_price;
	private String prdx_real_code;
	private String prc_description;
	private String prc_name;
	private String prc_short_description;
	private String prcx_real_code;
	private Long tpd_id;
	private Double tpd_quantity;
	private Long tpd_trn_id;
	private Double tpd_value;
	private Double tpdx_discount;
	private Double tpdx_unit_price_net;
	private Double tpdx_value_net;
	private String ctr_code_transaction;
	private String ctr_code_home;
	private int partition_year;
	private String tpdx_udb_details;
	private String tpdx_ford_parts;
	
	public String getPrt_code() 
	{
		return prt_code;
	}
	public void setPrt_code(String prt_code) {
		this.prt_code = prt_code;
	}
	public String getPrd_description() {
		return prd_description;
	}
	public void setPrd_description(String prd_description) {
		this.prd_description = prd_description;
	}
	public Long getPrd_manufacturer_com_id() {
		return prd_manufacturer_com_id;
	}
	public void setPrd_manufacturer_com_id(Long prd_manufacturer_com_id) {
		this.prd_manufacturer_com_id = prd_manufacturer_com_id;
	}
	public String getPrd_name() {
		return prd_name;
	}
	public void setPrd_name(String prd_name) {
		this.prd_name = prd_name;
	}
	public Double getPrd_unit_price() {
		return prd_unit_price;
	}
	public void setPrd_unit_price(Double prd_unit_price) {
		this.prd_unit_price = prd_unit_price;
	}
	public String getPrdx_real_code() {
		return prdx_real_code;
	}
	public void setPrdx_real_code(String prdx_real_code) {
		this.prdx_real_code = prdx_real_code;
	}
	public String getPrc_description() {
		return prc_description;
	}
	public void setPrc_description(String prc_description) {
		this.prc_description = prc_description;
	}
	public String getPrc_name() {
		return prc_name;
	}
	public void setPrc_name(String prc_name) {
		this.prc_name = prc_name;
	}
	public String getPrc_short_description() {
		return prc_short_description;
	}
	public void setPrc_short_description(String prc_short_description) {
		this.prc_short_description = prc_short_description;
	}
	public String getPrcx_real_code() {
		return prcx_real_code;
	}
	public void setPrcx_real_code(String prcx_real_code) {
		this.prcx_real_code = prcx_real_code;
	}
	public Long getTpd_id() {
		return tpd_id;
	}
	public void setTpd_id(Long tpd_id) {
		this.tpd_id = tpd_id;
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
	public Double getTpdx_discount() {
		return tpdx_discount;
	}
	public void setTpdx_discount(Double tpdx_discount) {
		this.tpdx_discount = tpdx_discount;
	}
	public Double getTpdx_unit_price_net() {
		return tpdx_unit_price_net;
	}
	public void setTpdx_unit_price_net(Double tpdx_unit_price_net) {
		this.tpdx_unit_price_net = tpdx_unit_price_net;
	}
	public Double getTpdx_value_net() {
		return tpdx_value_net;
	}
	public void setTpdx_value_net(Double tpdx_value_net) {
		this.tpdx_value_net = tpdx_value_net;
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
	public String getTpdx_udb_details() {
		return tpdx_udb_details;
	}
	public void setTpdx_udb_details(String tpdx_udb_details) {
		this.tpdx_udb_details = tpdx_udb_details;
	}
	public String getTpdx_ford_parts() {
		return tpdx_ford_parts;
	}
	public void setTpdx_ford_parts(String tpdx_ford_parts) {
		this.tpdx_ford_parts = tpdx_ford_parts;
	}


}
