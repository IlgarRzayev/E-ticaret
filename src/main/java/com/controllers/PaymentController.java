package com.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beans.Cart;
import com.beans.Order;
import com.beans.Payment;
import com.beans.Product;
import com.beans.User;
import com.dao.CartDao;
import com.dao.PaymentDao;
import com.dao.ProductDao;

import jakarta.servlet.http.HttpSession;

public class PaymentController {
	@Autowired
    CartDao cartdao;
    @Autowired
    ProductDao pdao;
    @Autowired
	PaymentDao paymentdao;
	
	
	
	@RequestMapping(value = "/buy/{productId}", method = RequestMethod.GET)
    public String addToCart(@PathVariable int productId, HttpSession session, Model m) {
        User user = (User) session.getAttribute("loggedInUser");
        
        
        Payment payment = new Payment();
        payment.setUserId(user.getId());
        payment.setProductId(productId);
        paymentdao.addPayment(payment);

        return "redirect:/payment";
    }
    
    
    
	@RequestMapping("/checkout")
	public String checkout(HttpSession session, Model m) {
	    User user = (User) session.getAttribute("loggedInUser");

	    if (user == null) {
	        return "redirect:/register";
	    }

	    List<Cart> cartItems = cartdao.getCartItemsByUserId(user.getId());
	    List<Product> products = new ArrayList<>();
	    double totalPrice = 0;

	    for (Cart item : cartItems) {
	        Product product = pdao.getProductById(item.getProductId());
	        products.add(product);
	        totalPrice += product.getPrice() * item.getQuantity();
	    }

	    m.addAttribute("cartItems", cartItems);
	    m.addAttribute("products", products);
	    m.addAttribute("totalPrice", totalPrice);

	    return "checkout";
	}

}
