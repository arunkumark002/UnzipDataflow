package com.ford.datafactory.batch.bo;



import java.util.ArrayList;

public class RewardBO extends BaseBO {

    private Long pcd_id;

    private String prt_code;

    private String prd_audit_cd;

    private Long prd_audit_cu;

    private String prd_audit_md;

    private Long prd_audit_mu;

    private String prd_audit_rd;

    private Long prd_audit_ru;

    private String prd_description;

    private Long prd_manufacturer_com_id;

    private String prd_name;

    private Double prd_unit_price;

    private String prdx_real_code;

    private String prc_description;

    private String prc_name;

    private String prc_short_description;

    private String prcx_real_code;
    //////

    private Long rwd_brand_com_id;

    private Long rwd_ctp_id;

    private String rwd_delivery_method;

    private String rwd_end_date;

    private String rwd_mobile_displayed;

    private String rwd_start_date;

    private String rwd_status;

    private Long rwd_supplier_com_id;

    private String rwd_type;

    private Long rwd_vat;

    /////
    private String pcd_audit_rd;

    private String pcd_channels;

    private String pcd_code;

    private Long pcd_points;

    private String ptp_code;

    private Long rwd_ltr_id;

    private Long rwdx_claim_period;

    private Long rwdx_claim_reminder;

    private Sgm_code sgm_code;

    private  Reward_custom_attributes reward_custom_attributes;

    private String prg_code;

    // F261022 Enhancement: Addition of 2 columns pwd_logistic_id and pcd_rgt_id.
    private Long rwd_logistic_id;

    private Long pcd_rgt_id;

    public static class Reward_custom_attributes{
        private ArrayList <Attributes> attributes = new ArrayList <Attributes>();
        public static class Attributes{
            private Long rxa_atr_id;
            private String atr_code;
            private String rxa_value;
            public Long getRxa_atr_id() {
                return rxa_atr_id;
            }
            public void setRxa_atr_id(Long rxa_atr_id) {
                this.rxa_atr_id = rxa_atr_id;
            }
            public String getAtr_code() {
                return atr_code;
            }
            public void setAtr_code(String atr_code) {
                this.atr_code = atr_code;
            }
            public String getRxa_value() {
                return rxa_value;
            }
            public void setRxa_value(String rxa_value) {
                this.rxa_value = rxa_value;
            }
        }
        public ArrayList <Attributes> getAttributes() {
            return attributes;
        }
        public void setAttributes(ArrayList <Attributes> attributes) {
            this.attributes = attributes;
        }
    }

    //Partition Columns
    private String ctr_code_points;

    private Integer partition_year;

    private String cntry_c;

    public Long getRwd_brand_com_id() {
        return rwd_brand_com_id;
    }

    public void setRwd_brand_com_id(Long rwd_brand_com_id) {
        this.rwd_brand_com_id = rwd_brand_com_id;
    }

    public Long getRwd_ctp_id() {
        return rwd_ctp_id;
    }

    public void setRwd_ctp_id(Long rwd_ctp_id) {
        this.rwd_ctp_id = rwd_ctp_id;
    }

    public String getRwd_delivery_method() {
        return rwd_delivery_method;
    }

    public void setRwd_delivery_method(String rwd_delivery_method) {
        this.rwd_delivery_method = rwd_delivery_method;
    }

    public String getRwd_end_date() {
        return rwd_end_date;
    }

    public void setRwd_end_date(String rwd_end_date) {
        this.rwd_end_date = rwd_end_date;
    }

    public String getRwd_mobile_displayed() {
        return rwd_mobile_displayed;
    }

    public void setRwd_mobile_displayed(String rwd_mobile_displayed) {
        this.rwd_mobile_displayed = rwd_mobile_displayed;
    }

    public String getRwd_start_date() {
        return rwd_start_date;
    }

    public void setRwd_start_date(String rwd_start_date) {
        this.rwd_start_date = rwd_start_date;
    }

    public String getRwd_status() {
        return rwd_status;
    }

    public void setRwd_status(String rwd_status) {
        this.rwd_status = rwd_status;
    }

    public Long getRwd_supplier_com_id() {
        return rwd_supplier_com_id;
    }

    public void setRwd_supplier_com_id(Long rwd_supplier_com_id) {
        this.rwd_supplier_com_id = rwd_supplier_com_id;
    }

    public String getRwd_type() {
        return rwd_type;
    }

    public void setRwd_type(String rwd_type) {
        this.rwd_type = rwd_type;
    }

    public Long getRwd_vat() {
        return rwd_vat;
    }

    public void setRwd_vat(Long rwd_vat) {
        this.rwd_vat = rwd_vat;
    }

    public String getCtr_code_points() {
        return ctr_code_points;
    }

    public void setCtr_code_points(String ctr_code_points) {
        this.ctr_code_points = ctr_code_points;
    }
    public Integer getPartition_year() {
        return partition_year;
    }

    public void setPartition_year(Integer partition_year) {
        this.partition_year = partition_year;
    }

    public Long getPcd_id() {
        return pcd_id;
    }

    public void setPcd_id(Long pcd_id) {
        this.pcd_id = pcd_id;
    }

    public String getPrt_code() {
        return prt_code;
    }

    public void setPrt_code(String prt_code) {
        this.prt_code = prt_code;
    }

    public String getPrd_audit_cd() {
        return prd_audit_cd;
    }

    public void setPrd_audit_cd(String prd_audit_cd) {
        this.prd_audit_cd = prd_audit_cd;
    }

    public Long getPrd_audit_cu() {
        return prd_audit_cu;
    }

    public void setPrd_audit_cu(Long prd_audit_cu) {
        this.prd_audit_cu = prd_audit_cu;
    }

    public String getPrd_audit_md() {
        return prd_audit_md;
    }

    public void setPrd_audit_md(String prd_audit_md) {
        this.prd_audit_md = prd_audit_md;
    }

    public Long getPrd_audit_mu() {
        return prd_audit_mu;
    }

    public void setPrd_audit_mu(Long prd_audit_mu) {
        this.prd_audit_mu = prd_audit_mu;
    }

    public String getPrd_audit_rd() {
        return prd_audit_rd;
    }

    public void setPrd_audit_rd(String prd_audit_rd) {
        this.prd_audit_rd = prd_audit_rd;
    }

    public Long getPrd_audit_ru() {
        return prd_audit_ru;
    }

    public void setPrd_audit_ru(Long prd_audit_ru) {
        this.prd_audit_ru = prd_audit_ru;
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

	/*public Long getPrd_unit_price() {
		return prd_unit_price;
	}

	public void setPrd_unit_price(Long prd_unit_price) {
		this.prd_unit_price = prd_unit_price;
	}*/ // F211923- Change

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

    public String getPcd_audit_rd() {
        return pcd_audit_rd;
    }

    public void setPcd_audit_rd(String pcd_audit_rd) {
        this.pcd_audit_rd = pcd_audit_rd;
    }

    public String getPcd_channels() {
        return pcd_channels;
    }

    public void setPcd_channels(String pcd_channels) {
        this.pcd_channels = pcd_channels;
    }

    public String getPcd_code() {
        return pcd_code;
    }

    public void setPcd_code(String pcd_code) {
        this.pcd_code = pcd_code;
    }

    public Long getPcd_points() {
        return pcd_points;
    }

    public void setPcd_points(Long pcd_points) {
        this.pcd_points = pcd_points;
    }

    public String getPtp_code() {
        return ptp_code;
    }

    public void setPtp_code(String ptp_code) {
        this.ptp_code = ptp_code;
    }

    public String getCntry_c() {
        return cntry_c;
    }

    public void setCntry_c(String cntry_c) {
        this.cntry_c = cntry_c;
    }
    public Long getRwd_ltr_id() {
        return rwd_ltr_id;
    }
    public void setRwd_ltr_id(Long rwd_ltr_id) {
        this.rwd_ltr_id = rwd_ltr_id;
    }
    public Long getRwdx_claim_period() {
        return rwdx_claim_period;
    }
    public void setRwdx_claim_period(Long rwdx_claim_period) {
        this.rwdx_claim_period = rwdx_claim_period;
    }
    public Long getRwdx_claim_reminder() {
        return rwdx_claim_reminder;
    }
    public void setRwdx_claim_reminder(Long rwdx_claim_reminder) {
        this.rwdx_claim_reminder = rwdx_claim_reminder;
    }
    public Sgm_code getSgm_code() {
        return sgm_code;
    }
    public void setSgm_code(Sgm_code sgm_code) {
        this.sgm_code = sgm_code;
    }

    public Reward_custom_attributes getReward_custom_attributes() {
        return reward_custom_attributes;
    }

    public void setReward_custom_attributes(Reward_custom_attributes reward_custom_attributes) {
        this.reward_custom_attributes = reward_custom_attributes;
    }

    public String getPrg_code() {
        return prg_code;
    }

    public void setPrg_code(String prg_code) {
        this.prg_code = prg_code;
    }

    public Long getRwd_logistic_id() {
        return rwd_logistic_id;
    }

    public void setRwd_logistic_id(Long rwd_logistic_id) {
        this.rwd_logistic_id = rwd_logistic_id;
    }

    public Long getPcd_rgt_id() {
        return pcd_rgt_id;
    }

    public void setPcd_rgt_id(Long pcd_rgt_id) {
        this.pcd_rgt_id = pcd_rgt_id;
    }
}

