package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.beans.Product;
import com.dao.ProductDao;
import com.dao.UserDao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@Controller
public class ProductController {
	@Autowired
	ProductDao dao;
	
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
        dao.save(product);
        return "redirect:/profile";
    }

    
}
