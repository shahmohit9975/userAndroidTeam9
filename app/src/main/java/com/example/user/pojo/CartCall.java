package com.example.user.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CartCall implements Serializable {

	@SerializedName("userEmail")
	private String userEmail;

	public void setUserEmail(String userEmail){
		this.userEmail = userEmail;
	}

	public String getUserEmail(){
		return userEmail;
	}

	@Override
 	public String toString(){
		return 
			"CartCall{" + 
			"userEmail = '" + userEmail + '\'' + 
			"}";
		}
}