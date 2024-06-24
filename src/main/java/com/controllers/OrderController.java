package com.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String addToOrder(@RequestParam("productId") String productId,@RequestParam("paymentMethod") String paymentMethod,
                             HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/register";
        }

        // Retrieve the product information using productId
        int prodId = Integer.parseInt(productId);
        Product product = pdao.getProductById(prodId);

        if (product == null) {
            // Handle case where product is not found
            return "redirect:/cart";
        }

        // Create a new Order for the single product
        Order order = new Order();
        order.setUserId(user.getId());
        order.setProductId(prodId);
        order.setPaymentMethod(paymentMethod);

        order.setTotalPrice(product.getPrice()); // Assuming price is per unit quantity

        orderdao.addOrder(order);

        // Remove the item from cart (if necessary)
        cartdao.deleteByProductId(user.getId(),prodId);

        return "redirect:/orders";
    }


    @RequestMapping("/orders")
    public String viewOrders(HttpSession session, HttpServletRequest req) {
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/register";
        }


	    List<Order> orders = (List<Order>) orderdao.getOrdersByUserId(user.getId());
	    List<Product> products = new ArrayList<>();
	    
	    for (Order item : orders) {
            Product product = pdao.getProductById(item.getProductId());
            products.add(product);
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
	public String edit(@RequestParam("id") String id, HttpServletRequest req) {
		Order order = orderdao.getOrderById(Integer.valueOf(id));
		req.getServletContext().setAttribute("command", order); // Change 'command' to 'user'
		return "ordereditform";
	}
    
    
}

    
    

    
}

