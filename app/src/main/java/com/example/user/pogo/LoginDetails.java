package com.example.user.pogo;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginDetails implements Serializable {

	@SerializedName("userPassword")
	private String userPassword;

	@SerializedName("userEmail")
	private String userEmail;

	public void setUserPasssword(String userPasssword){
		this.userPassword = userPasssword;
	}

	public String getUserPasssword(){
		return userPassword;
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
			"userPasssword = '" + userPassword + '\'' +
			",userEmail = '" + userEmail + '\'' +
			"}";
		}
}