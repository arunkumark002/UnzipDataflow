package com.ford.datafactory.batch.bo;

public class TransactionBadgesBO extends BaseBO {
	 
	private Long trb_id;
	private String trl_code;
	private Long tba_order;
	private String tba_process_level;
	private Long trn_id;
	private Long bdg_id;
	private String ctr_code;
	
	public Long getTrb_id() {
		return trb_id;
	}
	public void setTrb_id(Long trb_id) {
		this.trb_id = trb_id;
	}
	public String getTrl_code() {
		return trl_code;
	}
	public void setTrl_code(String trl_code) {
		this.trl_code = trl_code;
	}
	public Long getTba_order() {
		return tba_order;
	}
	public void setTba_order(Long tba_order) {
		this.tba_order = tba_order;
	}
	public String getTba_process_level() {
		return tba_process_level;
	}
	public void setTba_process_level(String tba_process_level) {
		this.tba_process_level = tba_process_level;
	}
	public Long getTrn_id() {
		return trn_id;
	}
	public void setTrn_id(Long trn_id) {
		this.trn_id = trn_id;
	}
	public Long getBdg_id() {
		return bdg_id;
	}
	public void setBdg_id(Long bdg_id) {
		this.bdg_id = bdg_id;
	}
	public String getCtr_code() {
		return ctr_code;
	}
	public void setCtr_code(String ctr_code) {
		this.ctr_code = ctr_code;
	}

}
