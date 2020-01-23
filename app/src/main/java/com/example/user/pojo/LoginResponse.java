package com.example.user.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class LoginResponse implements Serializable {

	@SerializedName("userAddress")
	private String userAddress;

	@SerializedName("userImgUrl")
	private String userImgUrl;

	@SerializedName("userName")
	private String userName;

	public void setUserAddress(String userAddress){
		this.userAddress = userAddress;
	}

	public String getUserAddress(){
		return userAddress;
	}

	public void setUserImgUrl(String userImgUrl){
		this.userImgUrl = userImgUrl;
	}

	public String getUserImgUrl(){
		return userImgUrl;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	@Override
 	public String toString(){
		return 
			"LoginResponse{" + 
			"userAddress = '" + userAddress + '\'' + 
			",userImgUrl = '" + userImgUrl + '\'' + 
			",userName = '" + userName + '\'' + 
			"}";
		}
}