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
    public String pay(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        List<Cart> cartItems = cartdao.getCartItemsByUserId(user.getId());
        double totalPrice = 0;

        for (Cart cartItem : cartItems) {
            Product product = pdao.getProductById(cartItem.getProductId());
            totalPrice += product.getPrice() * cartItem.getQuantity();
        }

        Payment payment = new Payment();
        payment.setUserId(user.getId());
        payment.setTotalPrice(totalPrice);

        paymentdao.savePayment(payment);

        return "redirect:/checkout?totalPrice=" + totalPrice;
    }
    
    
    
    @RequestMapping("/checkout")
    public String checkout(HttpSession session, @RequestParam("totalPrice") double totalPrice) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/register";
        }

        // Ödemeleri session'a ekleyin
        Payment payment = paymentdao.getPaymentByUserId(user.getId());
        
        
        session.setAttribute("totalPrice", totalPrice);
        session.setAttribute("payment", payment);

        return "checkout";
    }



}
