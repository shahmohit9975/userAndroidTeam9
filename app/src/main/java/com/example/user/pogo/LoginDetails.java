package com.example.user.pogo;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginDetails implements Serializable {

	@SerializedName("userPasssword")
	private String userPasssword;

	@SerializedName("userEmail")
	private String userEmail;

	public void setUserPasssword(String userPasssword){
		this.userPasssword = userPasssword;
	}

	public String getUserPasssword(){
		return userPasssword;
	}

	public void setUserEmail(String userEmail){
		this.userEmail = userEmail;
	}

	public String getUserEmail(){
		return userEmail;
	}

	@Override
 	public String toString(){
		return 
			"LoginDetails{" + 
			"userPasssword = '" + userPasssword + '\'' +
			",userEmail = '" + userEmail + '\'' +
			"}";
		}
}