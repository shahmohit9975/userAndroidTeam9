package com.example.user.pogo;

import com.google.gson.annotations.SerializedName;


public class GetCategories{

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