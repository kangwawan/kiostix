package com.birumerah.kiostix.dto;

import java.util.List;

public class DataBookingDTO {

	private TransactionDetailDTO transaction_detail;
	private List<PaymentDetailDTO> payment_detail;
	public TransactionDetailDTO getTransaction_detail() {
		return transaction_detail;
	}
	public void setTransaction_detail(TransactionDetailDTO transaction_detail) {
		this.transaction_detail = transaction_detail;
	}
	public List<PaymentDetailDTO> getPayment_detail() {
		return payment_detail;
	}
	public void setPayment_detail(List<PaymentDetailDTO> payment_detail) {
		this.payment_detail = payment_detail;
	}
	
}
