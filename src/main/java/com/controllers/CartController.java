package com.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

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

import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
	@Autowired
	CartDao cartdao;
	@Autowired
	ProductDao pdao;

	/**
	 * Sepete ürün ekler.
	 * Kullanıcı giriş yapmamışsa kayıt sayfasına yönlendirir.
	 * Ürün bulunamazsa ürün listesi sayfasına yönlendirir.
	
	 */
	@GetMapping(value = "/addToCart")
	public String addToCart(@RequestParam("productId") String productId,
			@RequestParam("quantity") @Nullable String quantity, HttpServletRequest req, HttpSession session) {
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			return "redirect:/register";
		}

		Product product = pdao.getProductById(Integer.valueOf(productId));
		if (product == null) {
			// Ürün bulunamazsa senaryosunu yönetin
			return "redirect:/products"; // Ürün listesine yönlendirir
		}

		Cart cartItem = new Cart();
		cartItem.setUserId(user.getId());
		cartItem.setProductId(product.getProductId());
		if (quantity == null) {
			quantity = "1";
		}
		cartItem.setQuantity(Integer.valueOf(quantity)); // Varsayılan miktar
		cartItem.setPrice(product.getPrice()); // Ürün fiyatını ayarlar
		cartdao.addCartItem(cartItem);
		return "redirect:/cart";
	}

	/**
	 * Sepeti görüntüler.
	 * Kullanıcı giriş yapmamışsa kayıt sayfasına yönlendirir 
	
	 */
	@RequestMapping("/cart")
	public String viewCart(HttpSession session, HttpServletRequest req) {
		User user = (User) session.getAttribute("loggedInUser");

		if (user == null) {
			return "redirect:/register";
		}

		List<Cart> cartItems = cartdao.getCartItemsByUserId(user.getId());
		TreeMap<Integer, Product> products = new TreeMap<>();

		for (Cart item : cartItems) {
			Product product = pdao.getProductById(item.getProductId());
			products.put(product.getProductId(), product);
		}

		req.getServletContext().setAttribute("cartItems", cartItems);
		req.getServletContext().setAttribute("products", products);
		return "cart";
	}

	/**
	 * Belirli bir öğeyi sepetten siler.
	
	 */
	@RequestMapping(value = "/deletecart", method = RequestMethod.GET)
	public String delete(@RequestParam("cartId") String cartId) {
		cartdao.delete(Integer.valueOf(cartId));
		return "redirect:/cart";
	}

}
