package com.birumerah.kiostix.dto;

import java.util.List;

public class ResponseBookingDTO extends ResponseDTO{
	private List<DataBookingDTO> data;

	public List<DataBookingDTO> getData() {
		return data;
	}

	public void setData(List<DataBookingDTO> data) {
		this.data = data;
	}
	
}
