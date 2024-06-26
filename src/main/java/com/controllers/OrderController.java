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
import com.dao.ProductDao;

@Controller
public class OrderController {
	@Autowired
	OrderDao orderdao;
	@Autowired
	ProductDao pdao;
	@Autowired
	CartDao cartdao;

	@RequestMapping(value = "/buy", method = RequestMethod.POST)
    public String addToOrder(@RequestParam("paymentMethod") String paymentMethod, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        
        List<Cart> cartItems = cartdao.getCartItemsByUserId(user.getId());

        
        Order order = new Order();
        order.setUserId(user.getId());
        order.setPaymentMethod(paymentMethod);

        
        for (Cart cartItem : cartItems) {
      
            order.setProductId(cartItem.getProductId()); 
           
            orderdao.addOrder(order);
     
            
            // Sepetten ürünü kaldır (İsteğe bağlı olarak)
             cartdao.deleteByProductId(user.getId(), cartItem.getProductId());
        }

       
       

        return "redirect:/orders"; 
    }

	@RequestMapping("/orders")
	public String viewOrders(HttpSession session, HttpServletRequest req) {
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			return "redirect:/register";
		}
		List<Order> orders = (List<Order>) orderdao.getOrdersByUserId(user.getId());
		TreeMap<Integer, Product> products = new TreeMap<>();

		for (Order order : orders) {
			Product product = pdao.getProductById(order.getProductId());
			products.put(product.getProductId(), product);
		}
		
		
		req.getServletContext().setAttribute("orders", orders);
		req.getServletContext().setAttribute("products", products);

		return "orderpage";
	}

	@RequestMapping("/vieworder")
	public String vieworder(HttpServletRequest req) {
		List<Order> list = orderdao.getOrders();
		req.getServletContext().setAttribute("list", list);
		return "vieworder";
	}

	@RequestMapping(value = "/deleteorder", method = RequestMethod.GET)
	public String delete(@RequestParam("orderId") String id) {
		int orderId = Integer.parseInt(id);

		// Son olarak kullanıcıyı sil
		orderdao.delete(orderId);
		return "redirect:/admin-page";
	}

	@RequestMapping(value = "/editorder")
	public String edit(@RequestParam("orderId") String id, HttpServletRequest req) {
		Order order = orderdao.getOrderById(Integer.valueOf(id));
		req.getServletContext().setAttribute("command", order); // Change 'command' to 'user'
		return "ordereditform";
	}
	
	@RequestMapping(value = "/ordereditsave", method = RequestMethod.POST)
    public String updateOrder(
            @RequestParam("orderId") int orderId,
            @RequestParam("paymentMethod") String paymentMethod,
            @RequestParam("totalPrice") double totalPrice,
            HttpSession session) {

        
        Order order = new Order();
        order.setOrderId(orderId);
        order.setPaymentMethod(paymentMethod);
        order.setTotalPrice(totalPrice);

        orderdao.updateOrder(order);

        return "redirect:/orders"; // Siparişler sayfasına yönlendir
    }

	

	@RequestMapping(value = "/add-order", method = RequestMethod.POST)
	public String add(@ModelAttribute("order") Order order) {
		orderdao.addOrder(order);
		return "admin-page"; // will redirect to viewemp request mapping
	}

}
