package com.beans;

public class Payment {
	
    private int userId;
    private double totalPrice;
    
    
    
    public int getUserId() {
    	return userId;
    }
    public void setUserId(int userId) {
    	this.userId = userId;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
}
