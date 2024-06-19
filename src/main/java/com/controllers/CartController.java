package com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beans.Cart;
import com.beans.User;
import com.dao.CartDao;
import com.dao.ProductDao;

import jakarta.servlet.http.HttpSession;

public class CartController {
	 	@Autowired
	    CartDao cartdao;
	    @Autowired
	    ProductDao pdao;

	    @RequestMapping(value = "/addToCart/{productId}", method = RequestMethod.GET)
	    public String addToCart(@PathVariable int productId, HttpSession session, Model m) {
	        User user = (User) session.getAttribute("loggedInUser");
	        if (user == null) {
	            return "redirect:/login";
	        }

	        Cart cartItem = new Cart();
	        cartItem.setUserId(user.getId());
	        cartItem.setProductId(productId);
	        cartItem.setQuantity(1); // Default quantity
	        cartdao.addCartItem(cartItem);

	        return "redirect:/cart";
	    }

	    @RequestMapping("/cart")
	    public String viewCart(HttpSession session, Model m) {
	        User user = (User) session.getAttribute("loggedInUser");
	        if (user == null) {
	            return "redirect:/register";
	        }

	        List<Cart> cartItems = cartdao.getCartItemsByUserId(user.getId());
	        m.addAttribute("cartItems", cartItems);
	        return "cart";
	    }

	    @RequestMapping("/login")
	    public String login(HttpSession session, Model m) {
	        // Login işlemleri burada yapılır.
	        // Giriş başarılı olduğunda:
	        User user = (User) session.getAttribute("loggedInUser");
	        if (user != null) {
	            List<Cart> cartItems = cartdao.getCartItemsByUserId(user.getId());
	            session.setAttribute("cartItems", cartItems);
	        }
	        return "redirect:/homepage_foruser";
	    }
}
