package com.example.user.pogo;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class VerifyOtp implements Serializable {

	@SerializedName("otp")
	private String otp;

	public void setOtp(String otp){
		this.otp = otp;
	}

	public String getOtp(){
		return otp;
	}

	@Override
 	public String toString(){
		return 
			"VerifyOtp{" + 
			"otp = '" + otp + '\'' + 
			"}";
		}
}