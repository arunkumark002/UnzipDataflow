package com.ford.datafactory.batch.bo;

public class TransactionCouponRedemptionBO extends BaseBO  {
	
	
	private Long tcp_id;
	
	private String tcp_cpn_barcode;
	
	private Long trn_acc_id;
	
	private Long tcp_cpn_id;
	
	private String tcp_ctp_code;
	
	private Long tcp_trn_id;
	
	private String tcp_use_result;	
	
	private Long tcpx_cus_id;
	
	private String tcpx_invoice_no;
	
	private String ctr_code_transaction;
	
	
	
	// partition details
	private Integer partition_year;
	
	private String ctr_code_home;
	
	

	public Long getTcp_id() {
		return tcp_id;
	}

	public void setTcp_id(Long tcp_id) {
		this.tcp_id = tcp_id;
	}

	public String getTcp_cpn_barcode() {
		return tcp_cpn_barcode;
	}

	public void setTcp_cpn_barcode(String tcp_cpn_barcode) {
		this.tcp_cpn_barcode = tcp_cpn_barcode;
	}

	public Long getTrn_acc_id() {
		return trn_acc_id;
	}

	public void setTrn_acc_id(Long trn_acc_id) {
		this.trn_acc_id = trn_acc_id;
	}

	public Long getTcp_cpn_id() {
		return tcp_cpn_id;
	}

	public void setTcp_cpn_id(Long tcp_cpn_id) {
		this.tcp_cpn_id = tcp_cpn_id;
	}

	public String getTcp_ctp_code() {
		return tcp_ctp_code;
	}

	public void setTcp_ctp_code(String tcp_ctp_code) {
		this.tcp_ctp_code = tcp_ctp_code;
	}

	public Long getTcp_trn_id() {
		return tcp_trn_id;
	}

	public void setTcp_trn_id(Long tcp_trn_id) {
		this.tcp_trn_id = tcp_trn_id;
	}

	public String getTcp_use_result() {
		return tcp_use_result;
	}

	public void setTcp_use_result(String tcp_use_result) {
		this.tcp_use_result = tcp_use_result;
	}

	public Long getTcpx_cus_id() {
		return tcpx_cus_id;
	}

	public void setTcpx_cus_id(Long tcpx_cus_id) {
		this.tcpx_cus_id = tcpx_cus_id;
	}

	public String getTcpx_invoice_no() {
		return tcpx_invoice_no;
	}

	public void setTcpx_invoice_no(String tcpx_invoice_no) {
		this.tcpx_invoice_no = tcpx_invoice_no;
	}

	public String getCtr_code_transaction() {
		return ctr_code_transaction;
	}

	public void setCtr_code_transaction(String ctr_code_transaction) {
		this.ctr_code_transaction = ctr_code_transaction;
	}

	public Integer getPartition_year() {
		return partition_year;
	}

	public void setPartition_year(Integer partition_year) {
		this.partition_year = partition_year;
	}

	public String getCtr_code_home() {
		return ctr_code_home;
	}

	public void setCtr_code_home(String ctr_code_home) {
		this.ctr_code_home = ctr_code_home;
	}
	
	


	


	 
	
	
	
	
	
	
	
	
}
