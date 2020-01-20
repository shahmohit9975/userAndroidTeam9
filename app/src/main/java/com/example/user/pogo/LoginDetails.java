package com.example.user.pogo;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginDetails implements Serializable {

	@SerializedName("passsword")
	private String passsword;

	@SerializedName("email")
	private String email;

	public void setPasssword(String passsword){
		this.passsword = passsword;
	}

	public String getPasssword(){
		return passsword;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"LoginDetails{" + 
			"passsword = '" + passsword + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}