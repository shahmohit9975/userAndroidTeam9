package com.example.user.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class UserDetails implements Serializable {

	@SerializedName("userPassword")
	private String userPassword;

	@SerializedName("userAddress")
	private String userAddress;

	@SerializedName("userName")
	private String userName;

	@SerializedName("userEmail")
	private String userEmail;

	@SerializedName("imageurl")
	private String image;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setUserPassword(String userPassword){
		this.userPassword = userPassword;
	}

	public String getUserPassword(){
		return userPassword;
	}

	public void setUserAddress(String userAddress){
		this.userAddress = userAddress;
	}

	public String getUserAddress(){
		return userAddress;
	}

	public void setUserName(String name){
		this.userName = name;
	}

	public String getUserName(){
		return userName;
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
			"UserDetails{" + 
			"userPassword = '" + userPassword + '\'' +
			",userAddress = '" + userAddress + '\'' +
			",name = '" + userName + '\'' +
			",userEmail = '" + userEmail + '\'' +
			"}";
		}
}