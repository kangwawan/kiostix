package com.birumerah.kiostix.dto;

import java.util.List;

public class TransactionDetailDTO {
    private String order_no;
    private String status;
    private String event_name;
    private Double order_total; //22222,
    private VenueDetailDTO venue_details; 
    private CustomerDetailDTO customer_details; 
    private List<ItemDetailDTO> item_details; 
    private String expired_date;
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public Double getOrder_total() {
		return order_total;
	}
	public void setOrder_total(Double order_total) {
		this.order_total = order_total;
	}
	public VenueDetailDTO getVenue_details() {
		return venue_details;
	}
	public void setVenue_details(VenueDetailDTO venue_details) {
		this.venue_details = venue_details;
	}
	public CustomerDetailDTO getCustomer_details() {
		return customer_details;
	}
	public void setCustomer_details(CustomerDetailDTO customer_details) {
		this.customer_details = customer_details;
	}
	public List<ItemDetailDTO> getItem_details() {
		return item_details;
	}
	public void setItem_details(List<ItemDetailDTO> item_details) {
		this.item_details = item_details;
	}
	public String getExpired_date() {
		return expired_date;
	}
	public void setExpired_date(String expired_date) {
		this.expired_date = expired_date;
	}
}
