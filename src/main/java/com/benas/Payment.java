package com.beans;

public class Payment {
	private int cartId;
    private int userId;
    private int productId;
    private int paymentId;
    private int quantity;
    
    public int getCartId() {
    	return cartId;
    }
    public void setCartId(int cartId) {
    	this.cartId = cartId;
    }
    
    public int getUserId() {
    	return userId;
    }
    public void setUserId(int userId) {
    	this.userId = userId;
    }
    
    public int getProductId() {
    	return productId;
    }
    public void setProductId(int productId) {
    	this.productId = productId;
    }
    
    public int getPaymentId() {
    	return paymentId;
    }
    public void setPaymentId(int paymentId) {
    	this.paymentId = paymentId;
    }
    public int getQuantity() {
    	return quantity;
    }
    public void setQuantity(int quantity) {
    	this.quantity = quantity;
    }
}
