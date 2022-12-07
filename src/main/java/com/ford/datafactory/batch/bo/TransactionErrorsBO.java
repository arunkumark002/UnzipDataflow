package com.ford.datafactory.batch.bo;

public class TransactionErrorsBO extends BaseBO {
	private Long ter_id;
	private Long trn_id;
	private Long trl_id;
	private String tre_error_code;
	private String tre_condition_formula;
	private String tre_process_level;
	private String tre_trn_status;
	private String tre_block_account;
	private String tre_block_customer;
	private String tre_block_user;
	private String ter_message;

	public Long getTer_id() {
		return ter_id;
	}

	public void setTer_id(Long ter_id) {
		this.ter_id = ter_id;
	}

	public Long getTrn_id() {
		return trn_id;
	}

	public void setTrn_id(Long trn_id) {
		this.trn_id = trn_id;
	}

	public Long getTrl_id() {
		return trl_id;
	}

	public void setTrl_id(Long trl_id) {
		this.trl_id = trl_id;
	}

	public String getTre_error_code() {
		return tre_error_code;
	}

	public void setTre_error_code(String tre_error_code) {
		this.tre_error_code = tre_error_code;
	}

	public String getTre_condition_formula() {
		return tre_condition_formula;
	}

	public void setTre_condition_formula(String tre_condition_formula) {
		this.tre_condition_formula = tre_condition_formula;
	}

	public String getTre_process_level() {
		return tre_process_level;
	}

	public void setTre_process_level(String tre_process_level) {
		this.tre_process_level = tre_process_level;
	}

	public String getTre_trn_status() {
		return tre_trn_status;
	}

	public void setTre_trn_status(String tre_trn_status) {
		this.tre_trn_status = tre_trn_status;
	}

	public String getTre_block_account() {
		return tre_block_account;
	}

	public void setTre_block_account(String tre_block_account) {
		this.tre_block_account = tre_block_account;
	}

	public String getTre_block_customer() {
		return tre_block_customer;
	}

	public void setTre_block_customer(String tre_block_customer) {
		this.tre_block_customer = tre_block_customer;
	}

	public String getTre_block_user() {
		return tre_block_user;
	}

	public void setTre_block_user(String tre_block_user) {
		this.tre_block_user = tre_block_user;
	}

	public String getTer_message() {
		return ter_message;
	}

	public void setTer_message(String ter_message) {
		this.ter_message = ter_message;
	}

}
