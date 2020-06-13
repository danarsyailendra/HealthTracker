package com.mercubuana.healthtracker.response;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin{

	@SerializedName("data")
	private Data data;

	@SerializedName("status")
	private boolean status;

	@SerializedName("message")
	private String message;

	public Data getData(){
		return data;
	}

	public boolean isStatus(){
		return status;
	}

	public String getMessage(){
		return message;
	}
}