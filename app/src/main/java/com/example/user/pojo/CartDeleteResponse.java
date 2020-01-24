package com.example.user.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CartDeleteResponse implements Serializable {

	@SerializedName("status")
	private boolean status;

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"CartDeleteResponse{" + 
			"status = '" + status + '\'' + 
			"}";
		}
}