package com.example.user.pogo;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CategoryResponse implements Serializable {

	@SerializedName("categoryName")
	private String categoryName;

	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	public String getCategoryName(){
		return categoryName;
	}

	@Override
 	public String toString(){
		return 
			"CategoryResponse{" + 
			"categoryName = '" + categoryName + '\'' + 
			"}";
		}
}