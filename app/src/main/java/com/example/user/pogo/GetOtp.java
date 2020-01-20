package com.example.user.pogo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class GetOtp implements Serializable {

	@SerializedName("email")
	private String email;

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"GetOtp{" + 
			"email = '" + email + '\'' + 
			"}";
		}
}