package com.example.user.pogo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BooleanResponse implements Serializable {

	@SerializedName("flag")
	private String flag;

	public void setFlag(String flag){
		this.flag = flag;
	}

	public String getFlag(){
		return flag;
	}

	@Override
 	public String toString(){
		return 
			"BooleanResponse{" + 
			"flag = '" + flag + '\'' + 
			"}";
		}
}