package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import com.beans.Product;
import com.dao.ProductDao;
import com.dao.UserDao;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class ProductController {
	@Autowired
	ProductDao pdao;
	
    @GetMapping("/add-product")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute("product") Product product, @RequestParam("image") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // Dosyayı kaydet
                String uploadsDir = "/uploads/";
                String realPathtoUploads = Paths.get(System.getProperty("user.dir"), "src", "main", "webapp", uploadsDir).toString();
                if (!new File(realPathtoUploads).exists()) {
                    new File(realPathtoUploads).mkdirs();
                }

                String filePath = realPathtoUploads + File.separator + file.getOriginalFilename();
                File dest = new File(filePath);
                file.transferTo(dest);

                // Dosya yolunu ayarla
                product.setImageUrl(uploadsDir + file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        pdao.save(product);
        return "redirect:/profile";
    }
    @RequestMapping("/profile")    
    public String viewproduct(Model m){    
        List<Product> list=pdao.getProducts();    
        m.addAttribute("list",list);  
        return "profile";   
    }
    @RequestMapping("/shareproduct/{productId}")    
    public String edit(@PathVariable int productId, Model m){    
        Product product =pdao.getProductById(productId);    
        m.addAttribute("command",product);  
        return "homepage_foruser";    
    }
    

    
}
