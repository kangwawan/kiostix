package com.birumerah.kiostix.dto;

public class ResponseLoginDTO extends ResponseDTO{

	private DataLoginDTO data;
	
	public DataLoginDTO getData() {
		return data;
	}

	public void setData(DataLoginDTO data) {
		this.data = data;
	}
	
}
