package com.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.beans.Cart;
import com.beans.Order;
import com.beans.Payment;
import com.beans.Product;
import com.beans.User;
import com.dao.CartDao;
import com.dao.PaymentDao;
import com.dao.ProductDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
@Controller
public class PaymentController {
	@Autowired
    CartDao cartdao;
    @Autowired
    ProductDao pdao;
    @Autowired
	PaymentDao paymentdao;
	
	
	
	@RequestMapping(value = "/pay", method = RequestMethod.GET)
    public String addToCart(@RequestParam("productId") String productId, HttpSession session, Model m) {
        User user = (User) session.getAttribute("loggedInUser");
        Product product = pdao.getProductById(Integer.valueOf(productId));
        
        Payment payment = new Payment();
        payment.setUserId(user.getId());
        payment.setTotalPrice(product.getPrice());
        paymentdao.addPayment(payment);

        return "redirect:/checkout";
    }
    
    
    
	@RequestMapping("/checkout")
	public String checkout(HttpSession session, HttpServletRequest req) {
	    User user = (User) session.getAttribute("loggedInUser");

	    if (user == null) {
	        return "redirect:/register";
	    }

	    List<Payment> payments = paymentdao.getPaymentsByUserId(user.getId());
	    

	    

	    session.setAttribute("payments", payments);

	    return "checkout";
	}

}

