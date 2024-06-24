package com.beans;

public class Product {
    private String name;
    private String category;
    private int quantity;
    private double price;
    private String imageUrl;
    private int productId; 
    private int userId;
    
    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
   
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public int getProductId() {    
	    return productId;    
	}    
	public void setProductId(int productId) {    
	    this.productId = productId;    
	    
	}
	public int getUserId() {    
	    return userId;    
	}    
	public void setUserId(int userId) {    
	    this.userId = userId;    
	    
	}
}
