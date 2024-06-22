package com.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import jakarta.servlet.http.*;

import com.beans.Cart;
import com.beans.Order;
import com.beans.Product;
import com.beans.User;
import com.dao.OrderDao;
import com.dao.ProductDao;

@Controller
public class OrderController {
    @Autowired
    OrderDao orderdao;
    @Autowired
    ProductDao pdao;

    @RequestMapping(value = "/pay/{productId}", method = RequestMethod.GET)
    public String addToOrder(@PathVariable int productId, HttpSession session, Model m) {
        User user = (User) session.getAttribute("loggedInUser");
        
        
        Order order = new Order();
        order.setUserId(user.getId());
        order.setProductId(productId);
        orderdao.addOrder(order);

        return "redirect:/orderpage";
    }
    
    
    
    @RequestMapping("/order")
        public String viewOrder(HttpSession session, Model m) {
    	User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/register";
        }

        List<Order> orders = (List<Order>) orderdao.getOrdersByUserId(user.getId());
        List<Product> products = new ArrayList<>();

        for (Order order : orders) {
            Product product = pdao.getProductById(order.getProductId());
            products.add(product);
        }

        m.addAttribute("orders", orders);
        m.addAttribute("products", products);

        return "orderpage";
        
    }
    
    

    
}

