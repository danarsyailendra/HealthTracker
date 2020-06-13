package com.mercubuana.healthtracker.response;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("user_email")
	private String userEmail;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("user_name")
	private String userName;

	@SerializedName("user_gender")
	private String userGender;

	@SerializedName("user_pass")
	private String userPass;

	public String getUserEmail(){
		return userEmail;
	}

	public String getUserId(){
		return userId;
	}

	public String getUserName(){
		return userName;
	}

	public String getUserGender(){
		return userGender;
	}

	public String getUserPass(){
		return userPass;
	}
}