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
import com.beans.Order;
import com.beans.Product;
import com.dao.ProductDao;

@Controller
public class OrderController {
    @Autowired
    ProductDao pdao;

    @RequestMapping("/order")
    public String showform(Model m) {
        m.addAttribute("command", new Order());
        return "orderpage";
    }

    
}
