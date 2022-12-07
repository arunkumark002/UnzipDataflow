package com.ford.datafactory.batch.bo;

public class TransactionBO extends BaseBO {

	// Transaction details
	private Long trn_id;

	private String prt_code;

	private Long trn_acc_id;

	private String trn_channel;

	private String trn_comments;

	private Long trn_cus_id;

	private String trn_date;

	private Double trn_discount_value;

	private Long trn_loc_id;

	private String trn_process_date;

	private String trn_receipt_id;

	private Long trn_src_trl_id;

	private Long trn_src_trn_id;

	private String trn_status;

	private Double trn_total_value;

	private String trn_type;

	private String ttp_name;

	private String ttp_desc;

	private Long trn_usr_id;

	private String ctr_code_account;

	private String trnx_app_source_code;

	private String trnx_correlation_id;

	private String trnx_currency;

	// partner transaction attributes
	private String trnx_datt1;

	private String trnx_datt2;

	private String trnx_earn_code;

	private Double trnx_natt1;

	private Double trnx_natt2;

	private Double trnx_natt3;

	private Double trnx_natt4;

	private Double trnx_natt5;

	private String trnx_reason_code;

	private String trnx_receipt_id;

	private String trnx_satt1;

	private String trnx_satt2;

	private String trnx_satt3;

	private String trnx_satt4;

	private String trnx_satt5;

	private String trnx_satt6;

	private String trnx_satt7;

	private String trnx_satt8;

	private Double trnx_total_discount_basket;

	private Double trnx_total_quantity;

	private Double trnx_total_value_net;

	private String trnx_trn_source;

	private String trnx_zone_id;

	private String trnx_book_date;

	private String trnx_request_date;

	private String trnx_activity_code;

	private String trn_cashier_id;

	private String trnx_prt_invoice_no;

	private String trnx_pay_invoice_id;

	private String trnx_reward_program;

	private String trnx_ext_tx_group_no;

	private String prt_code_fpr;

	private Double trnx_total_points_value;

	private Integer trnx_proc_date_offset_utc;

	private Double trnx_misc_amount;

	// FPA_FPR (FordPass Rewards) enhancement
	private String trn_attr01;

	private String trn_attr02;

	private String trn_attr03;

	private String trnx_additional_attributes;

	// partition details
	private Integer partition_year;

	private String ctr_code;

	//FPA �  Enhancement CR# TA1990032 - US1068023 - Addition of prg_code
	private String prg_code;

	////FPA - FPR ( FordPass Rewards - Pre-Feature Analysis) enhancement CR# F206322
	private String trnx_satt9;
	private String trnx_satt10;
	private String trnx_satt11;
	private String trnx_satt12;
	private String trnx_satt13;
	private String trnx_satt14;
	private String trnx_satt15;
	private String trnx_satt16;
	private String trnx_satt17;
	private String trnx_satt18;
	private String trnx_satt19;
	private String trnx_satt20;
	private Double trnx_natt6;
	private Double trnx_natt7;
	private Double trnx_natt8;
	private Double trnx_natt9;
	private Double trnx_natt10;
	private String trnx_datt3;
	private String trnx_datt4;
	private String trnx_datt5;
	private String trnx_datt6;

	//
	private String trn_attr04;
	private String trn_attr05;


	public Long getTrn_id() {
		return trn_id;
	}

	public void setTrn_id(Long trn_id) {
		this.trn_id = trn_id;
	}

	public String getPrt_code() {
		return prt_code;
	}

	public void setPrt_code(String prt_code) {
		this.prt_code = prt_code;
	}

	public Long getTrn_acc_id() {
		return trn_acc_id;
	}

	public void setTrn_acc_id(Long trn_acc_id) {
		this.trn_acc_id = trn_acc_id;
	}

	public String getTrn_channel() {
		return trn_channel;
	}

	public void setTrn_channel(String trn_channel) {
		this.trn_channel = trn_channel;
	}

	public String getTrn_comments() {
		return trn_comments;
	}

	public void setTrn_comments(String trn_comments) {
		this.trn_comments = trn_comments;
	}

	public Long getTrn_cus_id() {
		return trn_cus_id;
	}

	public void setTrn_cus_id(Long trn_cus_id) {
		this.trn_cus_id = trn_cus_id;
	}

	public String getTrn_date() {
		return trn_date;
	}

	public void setTrn_date(String trn_date) {
		this.trn_date = trn_date;
	}

	public Double getTrn_discount_value() {
		return trn_discount_value;
	}

	public void setTrn_discount_value(Double trn_discount_value) {
		this.trn_discount_value = trn_discount_value;
	}

	public Long getTrn_loc_id() {
		return trn_loc_id;
	}

	public void setTrn_loc_id(Long trn_loc_id) {
		this.trn_loc_id = trn_loc_id;
	}

	public String getTrn_process_date() {
		return trn_process_date;
	}

	public void setTrn_process_date(String trn_process_date) {
		this.trn_process_date = trn_process_date;
	}

	public String getTrn_receipt_id() {
		return trn_receipt_id;
	}

	public void setTrn_receipt_id(String trn_receipt_id) {
		this.trn_receipt_id = trn_receipt_id;
	}

	public Long getTrn_src_trl_id() {
		return trn_src_trl_id;
	}

	public void setTrn_src_trl_id(Long trn_src_trl_id) {
		this.trn_src_trl_id = trn_src_trl_id;
	}

	public Long getTrn_src_trn_id() {
		return trn_src_trn_id;
	}

	public void setTrn_src_trn_id(Long trn_src_trn_id) {
		this.trn_src_trn_id = trn_src_trn_id;
	}

	public String getTrn_status() {
		return trn_status;
	}

	public void setTrn_status(String trn_status) {
		this.trn_status = trn_status;
	}

	public Double getTrn_total_value() {
		return trn_total_value;
	}

	public void setTrn_total_value(Double trn_total_value) {
		this.trn_total_value = trn_total_value;
	}

	public String getTrn_type() {
		return trn_type;
	}

	public void setTrn_type(String trn_type) {
		this.trn_type = trn_type;
	}

	public String getTtp_name() {
		return ttp_name;
	}

	public void setTtp_name(String ttp_name) {
		this.ttp_name = ttp_name;
	}

	public String getTtp_desc() {
		return ttp_desc;
	}

	public void setTtp_desc(String ttp_desc) {
		this.ttp_desc = ttp_desc;
	}

	public Long getTrn_usr_id() {
		return trn_usr_id;
	}

	public void setTrn_usr_id(Long trn_usr_id) {
		this.trn_usr_id = trn_usr_id;
	}

	public String getCtr_code_account() {
		return ctr_code_account;
	}

	public void setCtr_code_account(String ctr_code_account) {
		this.ctr_code_account = ctr_code_account;
	}

	public String getTrnx_app_source_code() {
		return trnx_app_source_code;
	}

	public void setTrnx_app_source_code(String trnx_app_source_code) {
		this.trnx_app_source_code = trnx_app_source_code;
	}

	public String getTrnx_correlation_id() {
		return trnx_correlation_id;
	}

	public void setTrnx_correlation_id(String trnx_correlation_id) {
		this.trnx_correlation_id = trnx_correlation_id;
	}

	public String getTrnx_currency() {
		return trnx_currency;
	}

	public void setTrnx_currency(String trnx_currency) {
		this.trnx_currency = trnx_currency;
	}

	public String getTrnx_datt1() {
		return trnx_datt1;
	}

	public void setTrnx_datt1(String trnx_datt1) {
		this.trnx_datt1 = trnx_datt1;
	}

	public String getTrnx_datt2() {
		return trnx_datt2;
	}

	public void setTrnx_datt2(String trnx_datt2) {
		this.trnx_datt2 = trnx_datt2;
	}

	public String getTrnx_earn_code() {
		return trnx_earn_code;
	}

	public void setTrnx_earn_code(String trnx_earn_code) {
		this.trnx_earn_code = trnx_earn_code;
	}

	public Double getTrnx_natt1() {
		return trnx_natt1;
	}

	public void setTrnx_natt1(Double trnx_natt1) {
		this.trnx_natt1 = trnx_natt1;
	}

	public Double getTrnx_natt2() {
		return trnx_natt2;
	}

	public void setTrnx_natt2(Double trnx_natt2) {
		this.trnx_natt2 = trnx_natt2;
	}

	public Double getTrnx_natt3() {
		return trnx_natt3;
	}

	public void setTrnx_natt3(Double trnx_natt3) {
		this.trnx_natt3 = trnx_natt3;
	}

	public Double getTrnx_natt4() {
		return trnx_natt4;
	}

	public void setTrnx_natt4(Double trnx_natt4) {
		this.trnx_natt4 = trnx_natt4;
	}

	public Double getTrnx_natt5() {
		return trnx_natt5;
	}

	public void setTrnx_natt5(Double trnx_natt5) {
		this.trnx_natt5 = trnx_natt5;
	}

	public String getTrnx_reason_code() {
		return trnx_reason_code;
	}

	public void setTrnx_reason_code(String trnx_reason_code) {
		this.trnx_reason_code = trnx_reason_code;
	}

	public String getTrnx_receipt_id() {
		return trnx_receipt_id;
	}

	public void setTrnx_receipt_id(String trnx_receipt_id) {
		this.trnx_receipt_id = trnx_receipt_id;
	}

	public String getTrnx_satt1() {
		return trnx_satt1;
	}

	public void setTrnx_satt1(String trnx_satt1) {
		this.trnx_satt1 = trnx_satt1;
	}

	public String getTrnx_satt2() {
		return trnx_satt2;
	}

	public void setTrnx_satt2(String trnx_satt2) {
		this.trnx_satt2 = trnx_satt2;
	}

	public String getTrnx_satt3() {
		return trnx_satt3;
	}

	public void setTrnx_satt3(String trnx_satt3) {
		this.trnx_satt3 = trnx_satt3;
	}

	public String getTrnx_satt4() {
		return trnx_satt4;
	}

	public void setTrnx_satt4(String trnx_satt4) {
		this.trnx_satt4 = trnx_satt4;
	}

	public String getTrnx_satt5() {
		return trnx_satt5;
	}

	public void setTrnx_satt5(String trnx_satt5) {
		this.trnx_satt5 = trnx_satt5;
	}

	public String getTrnx_satt6() {
		return trnx_satt6;
	}

	public void setTrnx_satt6(String trnx_satt6) {
		this.trnx_satt6 = trnx_satt6;
	}

	public String getTrnx_satt7() {
		return trnx_satt7;
	}

	public void setTrnx_satt7(String trnx_satt7) {
		this.trnx_satt7 = trnx_satt7;
	}

	public String getTrnx_satt8() {
		return trnx_satt8;
	}

	public void setTrnx_satt8(String trnx_satt8) {
		this.trnx_satt8 = trnx_satt8;
	}

	public Double getTrnx_total_discount_basket() {
		return trnx_total_discount_basket;
	}

	public void setTrnx_total_discount_basket(Double trnx_total_discount_basket) {
		this.trnx_total_discount_basket = trnx_total_discount_basket;
	}

	public Double getTrnx_total_quantity() {
		return trnx_total_quantity;
	}

	public void setTrnx_total_quantity(Double trnx_total_quantity) {
		this.trnx_total_quantity = trnx_total_quantity;
	}

	public Double getTrnx_total_value_net() {
		return trnx_total_value_net;
	}

	public void setTrnx_total_value_net(Double trnx_total_value_net) {
		this.trnx_total_value_net = trnx_total_value_net;
	}

	public String getTrnx_trn_source() {
		return trnx_trn_source;
	}

	public void setTrnx_trn_source(String trnx_trn_source) {
		this.trnx_trn_source = trnx_trn_source;
	}

	public String getTrnx_zone_id() {
		return trnx_zone_id;
	}

	public void setTrnx_zone_id(String trnx_zone_id) {
		this.trnx_zone_id = trnx_zone_id;
	}

	public String getTrnx_book_date() {
		return trnx_book_date;
	}

	public void setTrnx_book_date(String trnx_book_date) {
		this.trnx_book_date = trnx_book_date;
	}

	public String getTrnx_request_date() {
		return trnx_request_date;
	}

	public void setTrnx_request_date(String trnx_request_date) {
		this.trnx_request_date = trnx_request_date;
	}

	public String getTrnx_activity_code() {
		return trnx_activity_code;
	}

	public void setTrnx_activity_code(String trnx_activity_code) {
		this.trnx_activity_code = trnx_activity_code;
	}

	public Integer getPartition_year() {
		return partition_year;
	}

	public void setPartition_year(Integer partition_year) {
		this.partition_year = partition_year;
	}

	public String getCtr_code() {
		return ctr_code;
	}

	public void setCtr_code(String ctr_code) {
		this.ctr_code = ctr_code;
	}

	public String getTrn_cashier_id() {
		return trn_cashier_id;
	}

	public void setTrn_cashier_id(String trn_cashier_id) {
		this.trn_cashier_id = trn_cashier_id;
	}

	public String getTrnx_prt_invoice_no() {
		return trnx_prt_invoice_no;
	}

	public void setTrnx_prt_invoice_no(String trnx_prt_invoice_no) {
		this.trnx_prt_invoice_no = trnx_prt_invoice_no;
	}

	public String getTrnx_pay_invoice_id() {
		return trnx_pay_invoice_id;
	}

	public void setTrnx_pay_invoice_id(String trnx_pay_invoice_id) {
		this.trnx_pay_invoice_id = trnx_pay_invoice_id;
	}

	public String getTrnx_reward_program() {
		return trnx_reward_program;
	}

	public void setTrnx_reward_program(String trnx_reward_program) {
		this.trnx_reward_program = trnx_reward_program;
	}

	public String getTrnx_ext_tx_group_no() {
		return trnx_ext_tx_group_no;
	}

	public void setTrnx_ext_tx_group_no(String trnx_ext_tx_group_no) {
		this.trnx_ext_tx_group_no = trnx_ext_tx_group_no;
	}

	public String getPrt_code_fpr() {
		return prt_code_fpr;
	}

	public void setPrt_code_fpr(String prt_code_fpr) {
		this.prt_code_fpr = prt_code_fpr;
	}

	public Double getTrnx_total_points_value() {
		return trnx_total_points_value;
	}

	public void setTrnx_total_points_value(Double trnx_total_points_value) {
		this.trnx_total_points_value = trnx_total_points_value;
	}

	public Integer getTrnx_proc_date_offset_utc() {
		return trnx_proc_date_offset_utc;
	}

	public void setTrnx_proc_date_offset_utc(Integer trnx_proc_date_offset_utc) {
		this.trnx_proc_date_offset_utc = trnx_proc_date_offset_utc;
	}

	public Double getTrnx_misc_amount() {
		return trnx_misc_amount;
	}

	public void setTrnx_misc_amount(Double trnx_misc_amount) {
		this.trnx_misc_amount = trnx_misc_amount;
	}

	public String getTrnx_additional_attributes() {
		return trnx_additional_attributes;
	}

	public void setTrnx_additional_attributes(String trnx_additional_attributes) {
		this.trnx_additional_attributes = trnx_additional_attributes;
	}

	public String getTrn_attr01() {
		return trn_attr01;
	}

	public void setTrn_attr01(String trn_attr01) {
		this.trn_attr01 = trn_attr01;
	}

	public String getTrn_attr02() {
		return trn_attr02;
	}

	public void setTrn_attr02(String trn_attr02) {
		this.trn_attr02 = trn_attr02;
	}

	public String getTrn_attr03() {
		return trn_attr03;
	}

	public void setTrn_attr03(String trn_attr03) {
		this.trn_attr03 = trn_attr03;
	}

	public String getPrg_code() {
		return prg_code;
	}

	public void setPrg_code(String prg_code) {
		this.prg_code = prg_code;
	}
	public String getTrnx_satt9() {
		return trnx_satt9;
	}

	public void setTrnx_satt9(String trnx_satt9) {
		this.trnx_satt9 = trnx_satt9;
	}
	public String getTrnx_satt10() {
		return trnx_satt10;
	}

	public void setTrnx_satt10(String trnx_satt10) {
		this.trnx_satt10 = trnx_satt10;
	}
	public String getTrnx_satt11() {
		return trnx_satt11;
	}

	public void setTrnx_satt11(String trnx_satt11) {
		this.trnx_satt11 = trnx_satt11;
	}

	public String getTrnx_satt12() {
		return trnx_satt12;
	}

	public void setTrnx_satt12(String trnx_satt12) {
		this.trnx_satt12 = trnx_satt12;
	}
	public String getTrnx_satt13() {
		return trnx_satt13;
	}

	public void setTrnx_satt13(String trnx_satt13) {
		this.trnx_satt13 = trnx_satt13;
	}
	public String getTrnx_satt14() {
		return trnx_satt14;
	}

	public void setTrnx_satt14(String trnx_satt14) {
		this.trnx_satt14 = trnx_satt14;
	}

	public String getTrnx_satt15() {
		return trnx_satt15;
	}

	public void setTrnx_satt15(String trnx_satt15) {
		this.trnx_satt15 = trnx_satt15;
	}
	public String getTrnx_satt16() {
		return trnx_satt16;
	}

	public void setTrnx_satt16(String trnx_satt16) {
		this.trnx_satt16 = trnx_satt16;
	}
	public String getTrnx_satt17() {
		return trnx_satt17;
	}

	public void setTrnx_satt17(String trnx_satt17) {
		this.trnx_satt17 = trnx_satt17;
	}
	public String getTrnx_satt18() {
		return trnx_satt18;
	}

	public void setTrnx_satt18(String trnx_satt18) {
		this.trnx_satt18 = trnx_satt18;
	}
	public String getTrnx_satt19() {
		return trnx_satt19;
	}

	public void setTrnx_satt19(String trnx_satt19) {
		this.trnx_satt19 = trnx_satt19;
	}
	public String getTrnx_satt20() {
		return trnx_satt20;
	}

	public void setTrnx_satt20(String trnx_satt20) {
		this.trnx_satt20 = trnx_satt20;
	}
	public Double  getTrnx_natt6() {
		return trnx_natt6;
	}

	public void setTrnx_natt6(Double  trnx_natt6) {
		this.trnx_natt6 = trnx_natt6;
	}
	public Double  getTrnx_natt7() {
		return trnx_natt7;
	}

	public void setTrnx_natt7(Double  trnx_natt7) {
		this.trnx_natt7 = trnx_natt7;
	}
	public Double  getTrnx_natt8() {
		return trnx_natt8;
	}

	public void setTrnx_natt8(Double  trnx_natt8) {
		this.trnx_natt8 = trnx_natt8;
	}
	public Double  getTrnx_natt9() {
		return trnx_natt9;
	}

	public void setTrnx_natt9(Double  trnx_natt9) {
		this.trnx_natt9 = trnx_natt9;
	}
	public Double  getTrnx_natt10() {
		return trnx_natt10;
	}

	public void setTrnx_natt10(Double  trnx_natt10) {
		this.trnx_natt10 = trnx_natt10;
	}

	public String getTrnx_datt3() {
		return trnx_datt3;
	}

	public void setTrnx_datt3(String trnx_datt3) {
		this.trnx_datt3 = trnx_datt3;
	}
	public String getTrnx_datt4() {
		return trnx_datt4;
	}

	public void setTrnx_datt4(String trnx_datt4) {
		this.trnx_datt4 = trnx_datt4;
	}
	public String getTrnx_datt5() {
		return trnx_datt5;
	}

	public void setTrnx_datt5(String trnx_datt5) {
		this.trnx_datt5 = trnx_datt5;
	}
	public String getTrnx_datt6() {
		return trnx_datt6;
	}

	public void setTrnx_datt6(String trnx_datt6) {
		this.trnx_datt6 = trnx_datt6;
	}

	public String getTrn_attr04() {
		return trn_attr04;
	}

	public void setTrn_attr04(String trn_attr04) {
		this.trn_attr04 = trn_attr04;
	}

	public String getTrn_attr05() {
		return trn_attr05;
	}

	public void setTrn_attr05(String trn_attr05) {
		this.trn_attr05 = trn_attr05;
	}
}