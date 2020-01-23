package com.example.user.pojo;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class PopularProducts implements Serializable {

	@SerializedName("productId")
	private String productId;

	@SerializedName("description")
	private String description;

	@SerializedName("categoryName")
	private String categoryName;

	@SerializedName("productName")
	private String productName;

	@SerializedName("merchantAndProductId")
	private String merchantAndProductId;

	@SerializedName("sellingPrice")
	private int sellingPrice;

	@SerializedName("url3")
	private String url3;

	@SerializedName("url1")
	private String url1;

	@SerializedName("merchantId")
	private String merchantId;

	@SerializedName("url2")
	private String url2;

	@SerializedName("price")
	private int price;

	@SerializedName("_id")
	private String id;

	@SerializedName("productRating")
	private double productRating;

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	public String getCategoryName(){
		return categoryName;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	public void setMerchantAndProductId(String merchantAndProductId){
		this.merchantAndProductId = merchantAndProductId;
	}

	public String getMerchantAndProductId(){
		return merchantAndProductId;
	}

	public void setSellingPrice(int sellingPrice){
		this.sellingPrice = sellingPrice;
	}

	public int getSellingPrice(){
		return sellingPrice;
	}

	public void setUrl3(String url3){
		this.url3 = url3;
	}

	public String getUrl3(){
		return url3;
	}

	public void setUrl1(String url1){
		this.url1 = url1;
	}

	public String getUrl1(){
		return url1;
	}

	public void setMerchantId(String merchantId){
		this.merchantId = merchantId;
	}

	public String getMerchantId(){
		return merchantId;
	}

	public void setUrl2(String url2){
		this.url2 = url2;
	}

	public String getUrl2(){
		return url2;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setProductRating(double productRating){
		this.productRating = productRating;
	}

	public double getProductRating(){
		return productRating;
	}

	@Override
 	public String toString(){
		return 
			"PopularProducts{" + 
			"productId = '" + productId + '\'' + 
			",description = '" + description + '\'' + 
			",categoryName = '" + categoryName + '\'' + 
			",productName = '" + productName + '\'' + 
			",merchantAndProductId = '" + merchantAndProductId + '\'' + 
			",sellingPrice = '" + sellingPrice + '\'' + 
			",url3 = '" + url3 + '\'' + 
			",url1 = '" + url1 + '\'' + 
			",merchantId = '" + merchantId + '\'' + 
			",url2 = '" + url2 + '\'' + 
			",price = '" + price + '\'' + 
			",_id = '" + id + '\'' + 
			",productRating = '" + productRating + '\'' + 
			"}";
		}
}