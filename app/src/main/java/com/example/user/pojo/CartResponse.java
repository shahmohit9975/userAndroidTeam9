package com.example.user.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CartResponse implements Serializable {

	@SerializedName("merchantAndProductId")
	private String merchantAndProductId;

	@SerializedName("sellingPrice")
	private double sellingPrice;

	@SerializedName("productId")
	private String productId;

	@SerializedName("url1")
	private String url1;

	@SerializedName("price")
	private double price;

	@SerializedName("cartId")
	private int cartId;

	@SerializedName("description")
	private String description;

	@SerializedName("productRating")
	private double productRating;

	@SerializedName("categoryName")
	private String categoryName;

	@SerializedName("productName")
	private String productName;

	@SerializedName("cartQuantity")
	private int cartQuantity;

	@SerializedName("merchantName")
	private String merchantName;

	public void setMerchantAndProductId(String merchantAndProductId){
		this.merchantAndProductId = merchantAndProductId;
	}

	public String getMerchantAndProductId(){
		return merchantAndProductId;
	}

	public void setSellingPrice(double sellingPrice){
		this.sellingPrice = sellingPrice;
	}

	public double getSellingPrice(){
		return sellingPrice;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setUrl1(String url1){
		this.url1 = url1;
	}

	public String getUrl1(){
		return url1;
	}

	public void setPrice(double price){
		this.price = price;
	}

	public double getPrice(){
		return price;
	}

	public void setCartId(int cartId){
		this.cartId = cartId;
	}

	public int getCartId(){
		return cartId;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setProductRating(double productRating){
		this.productRating = productRating;
	}

	public double getProductRating(){
		return productRating;
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

	public void setCartQuantity(int cartQuantity){
		this.cartQuantity = cartQuantity;
	}

	public int getCartQuantity(){
		return cartQuantity;
	}

	public void setMerchantName(String merchantName){
		this.merchantName = merchantName;
	}

	public String getMerchantName(){
		return merchantName;
	}

	@Override
 	public String toString(){
		return 
			"CartResponse{" + 
			"merchantAndProductId = '" + merchantAndProductId + '\'' + 
			",sellingPrice = '" + sellingPrice + '\'' + 
			",productId = '" + productId + '\'' + 
			",url1 = '" + url1 + '\'' + 
			",price = '" + price + '\'' + 
			",cartId = '" + cartId + '\'' + 
			",description = '" + description + '\'' + 
			",productRating = '" + productRating + '\'' + 
			",categoryName = '" + categoryName + '\'' + 
			",productName = '" + productName + '\'' + 
			",cartQuantity = '" + cartQuantity + '\'' + 
			",merchantName = '" + merchantName + '\'' + 
			"}";
		}
}