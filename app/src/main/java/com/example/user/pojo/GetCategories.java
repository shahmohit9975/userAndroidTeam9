package com.example.user.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class GetCategories implements Serializable {

	@SerializedName("_id")
	private String id;

	@SerializedName("categoryName")
	private String categoryName;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	public String getCategoryName(){
		return categoryName;
	}

	@Override
 	public String toString(){
		return 
			"GetCategories{" + 
			"_id = '" + id + '\'' + 
			",categoryName = '" + categoryName + '\'' + 
			"}";
		}
}