package com.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beans.Cart;
import com.beans.Product;
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
	            return "redirect:/register";
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
	        List<Product> products = new ArrayList<>();

	        for (Cart item : cartItems) {
	            Product product = pdao.getProductById(item.getProductId());
	            products.add(product);
	        }

	        m.addAttribute("cartItems", cartItems);
	        m.addAttribute("products", products);

	        return "cart";
	    }
	    
	    @RequestMapping(value = "/deletecart/{cartId}", method = RequestMethod.GET)
	    public String delete(@PathVariable int cartId) {
	        cartdao.delete(cartId);
	        return "redirect:/cart";
	    }
	    
	    
}

