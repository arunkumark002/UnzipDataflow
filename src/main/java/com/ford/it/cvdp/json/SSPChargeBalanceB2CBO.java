package com.ford.it.cvdp.json;

public class SSPChargeBalanceB2CBO extends BaseBO {

    private String  create_timestamp;
    private String  customer_id;
    private Integer id;
    private String  update_timestamp;
    private String  vin;
    private String  subscription_status;
    private Float   total_balance;
    private String  expiration_date;
    private Float   current_balance;
    private String  country;
    private String  partition_date;

    public String getCreate_timestamp() {
        return create_timestamp;
    }

    public void setCreate_timestamp(String create_timestamp) {
        this.create_timestamp = create_timestamp;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUpdate_timestamp() {
        return update_timestamp;
    }

    public void setUpdate_timestamp(String update_timestamp) {
        this.update_timestamp = update_timestamp;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getSubscription_status() {
        return subscription_status;
    }

    public void setSubscription_status(String subscription_status) {
        this.subscription_status = subscription_status;
    }

    public Float getTotal_balance() {
        return total_balance;
    }

    public void setTotal_balance(Float total_balance) {
        this.total_balance = total_balance;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }

    public Float getCurrent_balance() {
        return current_balance;
    }

    public void setCurrent_balance(Float current_balance) {
        this.current_balance = current_balance;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPartition_date() {
        return partition_date;
    }

    public void setPartition_date(String partition_date) {
        this.partition_date = partition_date;
    }
}