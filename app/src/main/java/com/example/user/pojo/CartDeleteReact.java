package com.example.user.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CartDeleteReact implements Serializable {

	@SerializedName("cartId")
	private int cartId;

	public void setCartId(int cartId){
		this.cartId = cartId;
	}

	public int getCartId(){
		return cartId;
	}

	@Override
 	public String toString(){
		return 
			"CartDeleteReact{" + 
			"cartId = '" + cartId + '\'' + 
			"}";
		}
}