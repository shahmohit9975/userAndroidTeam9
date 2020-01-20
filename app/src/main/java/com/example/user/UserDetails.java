package com.example.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class UserDetails implements Serializable {

	@SerializedName("password")
	private String password;

	@SerializedName("address")
	private String address;

	@SerializedName("name")
	private String name;

	@SerializedName("email")
	private String email;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
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
			"UserDetails{" + 
			"password = '" + password + '\'' + 
			",address = '" + address + '\'' + 
			",name = '" + name + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}