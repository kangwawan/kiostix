package com.birumerah.kiostix.model;

import java.util.Iterator;
import java.util.Map;

public class Bookings {
	private Long id;
	private String token;
	private Map<String,String> item;
	private String itemNo;
	private Integer itemQty;
	private String time;
	private String name;
	private String email;
	private String gender;
	private String phone;
	private String dob;
	private Integer paymentType;
	private Double amount;
	private String photo1;
	private String photo2;
	private String orderNo;
	private String notes1;
	private String notes2;
	private String notes3;
	private boolean offline;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getItemNo() {
		return itemNo;
	}
//	public void setItemNo(String itemNo) {
//		this.itemNo = itemNo;
//	}
	public Integer getItemQty() {
		return itemQty;
	}
//	public void setItemQty(Integer itemQty) {
//		this.itemQty = itemQty;
//	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public Integer getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getPhoto1() {
		return photo1;
	}
	public void setPhoto1(String photo1) {
		this.photo1 = photo1;
	}
	public String getPhoto2() {
		return photo2;
	}
	public void setPhoto2(String photo2) {
		this.photo2 = photo2;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getNotes1() {
		return notes1;
	}
	public void setNotes1(String notes1) {
		this.notes1 = notes1;
	}
	public String getNotes2() {
		return notes2;
	}
	public void setNotes2(String notes2) {
		this.notes2 = notes2;
	}
	public String getNotes3() {
		return notes3;
	}
	public void setNotes3(String notes3) {
		this.notes3 = notes3;
	}
	public boolean isOffline() {
		return offline;
	}
	public void setOffline(boolean offline) {
		this.offline = offline;
	}
//	public Object getItem() {
//		return item;
//	}
	public void setItem(Map<String,String> item) {
		this.item = item;
		if(item!=null){
			Iterator iter = item.keySet().iterator();
		    while (iter.hasNext()) {
		    	String key = (String) iter.next();
				this.itemNo = key;
				this.itemQty = Integer.parseInt(item.get(key));
			}
		}
		
	}
	
}



