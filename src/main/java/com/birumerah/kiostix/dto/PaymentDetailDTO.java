package com.birumerah.kiostix.dto;

public class PaymentDetailDTO {
    private String payment_id;
    private String payment_name;
    private String payment_image;
    private String payment_description;
    private String fee_info;
    private Double fee_value;
	public String getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}
	public String getPayment_name() {
		return payment_name;
	}
	public void setPayment_name(String payment_name) {
		this.payment_name = payment_name;
	}
	public String getPayment_image() {
		return payment_image;
	}
	public void setPayment_image(String payment_image) {
		this.payment_image = payment_image;
	}
	public String getPayment_description() {
		return payment_description;
	}
	public void setPayment_description(String payment_description) {
		this.payment_description = payment_description;
	}
	public String getFee_info() {
		return fee_info;
	}
	public void setFee_info(String fee_info) {
		this.fee_info = fee_info;
	}
	public Double getFee_value() {
		return fee_value;
	}
	public void setFee_value(Double fee_value) {
		this.fee_value = fee_value;
	}
}
