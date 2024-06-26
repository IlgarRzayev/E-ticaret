package com.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.beans.Cart;
import com.beans.Order;
import com.beans.Product;
import com.beans.User;
import com.dao.CartDao;
import com.dao.OrderDao;
import com.dao.PaymentDao;
import com.dao.ProductDao;

@Controller
public class OrderController {
	@Autowired
	OrderDao orderdao;
	@Autowired
	ProductDao pdao;
	@Autowired
	CartDao cartdao;
	@Autowired
	PaymentDao paymentdao;
	

	/**
	 * Sepeti kullanıcı siparişine dönüştürür.
	 * Kullanıcı giriş yapmamışsa giriş sayfasına yönlendirir.
	 
	 */
	@RequestMapping(value = "/buy", method = RequestMethod.POST)
    public String addToOrder(@RequestParam("paymentMethod") String paymentMethod, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        // Kullanıcının sepetindeki ürünleri al
        List<Cart> cartItems = cartdao.getCartItemsByUserId(user.getId());

        // Her ürün için bir sipariş oluştur
        for (Cart cartItem : cartItems) {
            Order order = new Order();
            order.setUserId(user.getId());
            order.setProductId(cartItem.getProductId());
            order.setPaymentMethod(paymentMethod);

            // Siparişi veritabanına ekle
            orderdao.addOrder(order);

            // Sepetten ürünü kaldır (İsteğe bağlı olarak)
            cartdao.deleteByProductId(user.getId(), cartItem.getProductId());
        }

        // Kullanıcının ödemesini sil (İsteğe bağlı olarak)
        paymentdao.deletePayment(user.getId());

        return "redirect:/orders"; // Siparişler sayfasına yönlendir
    }

	/**
	 * Kullanıcının siparişlerini görüntüler.
	 * Kullanıcı giriş yapmamışsa kayıt sayfasına yönlendirir.
	 * 
	 */
	@RequestMapping("/orders")
	public String viewOrders(HttpSession session, HttpServletRequest req) {
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			return "redirect:/register";
		}
		
		// Kullanıcının siparişlerini al
		List<Order> orders = (List<Order>) orderdao.getOrdersByUserId(user.getId());
		TreeMap<Integer, Product> products = new TreeMap<>();

		// Siparişlere ait ürünleri al ve TreeMap'e ekle
		for (Order order : orders) {
			Product product = pdao.getProductById(order.getProductId());
			products.put(product.getProductId(), product);
		}
		
		// Servlet context üzerine sipariş ve ürünleri set et
		req.getServletContext().setAttribute("orders", orders);
		req.getServletContext().setAttribute("products", products);

		return "orderpage"; // Sipariş sayfasına yönlendir
	}

	/**
	 * Tüm siparişleri görüntüler.
	 * 
	 */
	@RequestMapping("/vieworder")
	public String vieworder(HttpServletRequest req) {
		List<Order> list = orderdao.getOrders();
		req.getServletContext().setAttribute("list", list);
		return "vieworder"; // Sipariş görüntüleme sayfasına yönlendir
	}

	/**
	 * Belirli bir siparişi siler.
	
	 */
	@RequestMapping(value = "/deleteorder", method = RequestMethod.GET)
	public String delete(@RequestParam("orderId") String id) {
		int orderId = Integer.parseInt(id);

		// Siparişi sil
		orderdao.delete(orderId);
		return "redirect:/admin-page"; // Admin sayfasına yönlendir
	}

	/**
	 * Belirli bir siparişi düzenleme formunu gösterir.
	
	 */
	@RequestMapping(value = "/editorder")
	public String edit(@RequestParam("orderId") String id, HttpServletRequest req) {
		Order order = orderdao.getOrderById(Integer.valueOf(id));
		req.getServletContext().setAttribute("command", order); // 'command' yerine 'order' olarak değiştir
		return "ordereditform"; // Sipariş düzenleme formuna yönlendir
	}
	
	/**
	 * Bir siparişi günceller.
	 
	 */
	@RequestMapping(value = "/ordereditsave", method = RequestMethod.POST)
    public String updateOrder(
            @RequestParam("orderId") int orderId,
            @RequestParam("paymentMethod") String paymentMethod,
            @RequestParam("totalPrice") double totalPrice,
            HttpSession session) {

        // Yeni bilgilerle sipariş oluştur
        Order order = new Order();
        order.setOrderId(orderId);
        order.setPaymentMethod(paymentMethod);
        order.setTotalPrice(totalPrice);

        // Siparişi güncelle
        orderdao.updateOrder(order);

        return "redirect:/orders"; // Siparişler sayfasına yönlendir
    }

	/**
	 * Yeni bir sipariş ekler.
	
	 */
	@RequestMapping(value = "/add-order", method = RequestMethod.POST)
	public String add(@ModelAttribute("order") Order order) {
		orderdao.addOrder(order);
		return "admin-page"; // Admin sayfasına yönlendir
	}

}
