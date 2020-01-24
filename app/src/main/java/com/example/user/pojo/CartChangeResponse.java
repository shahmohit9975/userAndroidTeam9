package com.example.user.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CartChangeResponse implements Serializable {

	@SerializedName("merchantAndProductId")
	private String merchantAndProductId;

	@SerializedName("cartId")
	private int cartId;

	@SerializedName("userEmail")
	private String userEmail;

	@SerializedName("cartQuantity")
	private int cartQuantity;

	public void setMerchantAndProductId(String merchantAndProductId){
		this.merchantAndProductId = merchantAndProductId;
	}

	public String getMerchantAndProductId(){
		return merchantAndProductId;
	}

	public void setCartId(int cartId){
		this.cartId = cartId;
	}

	public int getCartId(){
		return cartId;
	}

	public void setUserEmail(String userEmail){
		this.userEmail = userEmail;
	}

	public String getUserEmail(){
		return userEmail;
	}

	public void setCartQuantity(int cartQuantity){
		this.cartQuantity = cartQuantity;
	}

	public int getCartQuantity(){
		return cartQuantity;
	}

	@Override
 	public String toString(){
		return 
			"CartChangeResponse{" + 
			"merchantAndProductId = '" + merchantAndProductId + '\'' + 
			",cartId = '" + cartId + '\'' + 
			",userEmail = '" + userEmail + '\'' + 
			",cartQuantity = '" + cartQuantity + '\'' + 
			"}";
		}
}