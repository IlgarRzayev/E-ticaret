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
    public String addToCart(@RequestParam("cartId") String cartId, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        Product product = pdao.getProductByCartId(Integer.valueOf(cartId));
        Payment payment = new Payment();
        payment.setUserId(user.getId());
        payment.setTotalPrice(product.getPrice());
        payment.setProductId(Integer.valueOf(product.getProductId()));
        return "redirect:/checkout?cartId="+Integer.valueOf(cartId);
    }
    
    
    
	@RequestMapping("/checkout")
	public String checkout(@RequestParam("cartId") String cartId,HttpSession session, HttpServletRequest req) {
	    User user = (User) session.getAttribute("loggedInUser");
	    if (user == null) {
	        return "redirect:/register";
	    }

	    List<Payment> payments = paymentdao.getPaymentsByUserId(user.getId());
	    double totalPrice = 0.0;

	    // Toplam fiyatı hesaplayın
	    for (Payment payment : payments) {
	        totalPrice += payment.getTotalPrice(); // Ödeme miktarının doğru alan adını kullanın
	    }

	    session.setAttribute("payments", payments);
	    session.setAttribute("totalPrice", totalPrice);

	    return "checkout";
	}


}
