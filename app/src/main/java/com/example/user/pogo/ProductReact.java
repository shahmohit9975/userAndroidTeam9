package com.example.user.pogo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductReact implements Serializable {

	@SerializedName("merchantAndProductId")
	private String merchantAndProductId;

	@SerializedName("productId")
	private String productId;

	@SerializedName("categoryName")
	private String categoryName;

	public void setMerchantAndProductId(String merchantAndProductId){
		this.merchantAndProductId = merchantAndProductId;
	}

	public String getMerchantAndProductId(){
		return merchantAndProductId;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
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
			"ProductReact{" + 
			"merchantAndProductId = '" + merchantAndProductId + '\'' + 
			",productId = '" + productId + '\'' + 
			",categoryName = '" + categoryName + '\'' + 
			"}";
		}
}