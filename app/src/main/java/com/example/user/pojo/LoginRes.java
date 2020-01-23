package com.example.user.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginRes implements Serializable {

	@SerializedName("userImgUrl")
	private String userImgUrl;

	@SerializedName("userName")
	private String userName;

	@SerializedName("loginStatus")
	private boolean loginStatus;

	@SerializedName("uuid")
	private String uuid;

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

	public void setLoginStatus(boolean loginStatus){
		this.loginStatus = loginStatus;
	}

	public boolean isLoginStatus(){
		return loginStatus;
	}

	public void setUuid(String uuid){
		this.uuid = uuid;
	}

	public String getUuid(){
		return uuid;
	}

	@Override
 	public String toString(){
		return 
			"LoginRes{" + 
			"userImgUrl = '" + userImgUrl + '\'' + 
			",userName = '" + userName + '\'' + 
			",loginStatus = '" + loginStatus + '\'' + 
			",uuid = '" + uuid + '\'' + 
			"}";
		}
}