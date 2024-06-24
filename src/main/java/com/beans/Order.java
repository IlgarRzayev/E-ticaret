package com.beans;

import java.util.List;

public class Order {
    private int orderId;
    private int userId;
    private double totalPrice;
    private String paymentMethod;
    private int productId; // Her bir sipariş için ürünleri tutacak liste

    // Getter ve Setter metotları
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getProductId() {
    	return productId;
    }
    public void setProductId(int productId) {
    	this.productId = productId;
    }
}
