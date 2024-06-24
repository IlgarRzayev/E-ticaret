package com.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.beans.Cart;
import com.beans.Product;
import com.beans.User;
import com.dao.CartDao;
import com.dao.ProductDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
@Controller
public class CartController {
	 	@Autowired
	    CartDao cartdao;
	    @Autowired
	    ProductDao pdao;

	    @GetMapping(value = "/addToCart")
	    public String addToCart(@RequestParam("productId") String productId, HttpServletRequest req, HttpSession session) {
	        User user = (User) session.getAttribute("loggedInUser");
	        if (user == null) {
	            return "redirect:/register";
	        }

	        Product product = pdao.getProductById(Integer.valueOf(productId));
	        if (product == null) {
	            // Handle product not found scenario
	            return "redirect:/products"; // Örneğin, ürün bulunamazsa ürün listesine yönlendirin
	        }

	        Cart cartItem = new Cart();
	        cartItem.setUserId(user.getId());
	        cartItem.setProductId(product.getProductId());
	        cartItem.setQuantity(1); // Default quantity
	        cartItem.setPrice(product.getPrice()); // Ürünün fiyatını ekleyin

	        cartdao.addCartItem(cartItem);
	        return "redirect:/cart";
	    }


	    @RequestMapping("/cart")
	    public String viewCart(HttpSession session, HttpServletRequest req) {
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

	        req.getServletContext().setAttribute("cartItems", cartItems);
	        req.getServletContext().setAttribute("products", products);

	        return "cart";
	    }
	    
	    @RequestMapping(value = "/deletecart", method = RequestMethod.GET)
	    public String delete(@RequestParam("cartId") String cartId) {
	        cartdao.delete(Integer.valueOf(cartId));
	        return "redirect:/cart";
	    }
	    
	    
}

