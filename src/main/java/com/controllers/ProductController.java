package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.beans.Product;
import com.beans.User;
import com.dao.OrderDao;
import com.dao.ProductDao;
import com.dao.UserDao;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductDao pdao;
    @Autowired
    OrderDao orderdao;
    @Autowired
    UserDao udao;

    /**
     * Yeni bir ürün ekler.
     
     */
    @PostMapping("/add-product")
    public String addProduct(@RequestParam("userId") String userId, @RequestParam("name") String name,
                             @RequestParam("category") String category, @RequestParam("quantity") int quantity,
                             @RequestParam("price") double price, HttpServletRequest req) {

        // Yeni ürün oluştur
        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setQuantity(quantity);
        product.setPrice(price);
        product.setUserId(Integer.valueOf(userId));

        // Ürünü veritabanına kaydet
        pdao.save(product);

        return "redirect:/admin-page"; // Admin sayfasına yönlendir
    }

    /**
     * Kullanıcının profiline bağlı olarak ürünleri görüntüler.
     *
     */
    @RequestMapping("/profile")
    public String viewproduct(HttpSession session, HttpServletRequest req) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/register"; // Kullanıcı giriş yapmamışsa kayıt sayfasına yönlendir
        }
        // Kullanıcının ürünlerini al ve Servlet context üzerine set et
        List<Product> list = pdao.getProductsByUserId(user.getId());
        req.getServletContext().setAttribute("list", list);
        return "profile"; // Profil sayfasına yönlendir
    }

    /**
     * Belirli bir ürünün detaylarını görüntüler.
     *
     */
    @RequestMapping("/viewdetail")
    public String viewProductDetails(@RequestParam("productId") String productId, HttpServletRequest req) {
        // Ürünü ID'sine göre al ve Servlet context üzerine set et
        Product product = pdao.getProductById(Integer.valueOf(productId));
        req.getServletContext().setAttribute("product", product);
        return "product-details"; // Ürün detayları sayfasına yönlendir
    }

    /**
     * Belirli bir ürünü paylaşım için hazırlar.
          */
    @RequestMapping("/shareproduct")
    public String share(@RequestParam("productId") String productId, HttpServletRequest req) {
        // Ürünü ID'sine göre al ve Servlet context üzerine set et
        Product product = pdao.getProductById(Integer.valueOf(productId));
        req.getServletContext().setAttribute("product", product);
        return "homepage_foruser"; // Anasayfa sayfasına yönlendir
    }

    /**
     * Belirli bir ürünü düzenleme formunu gösterir.
     *
     */
    @RequestMapping(value = "/editproduct")
    public String editProduct(@RequestParam("productId") String productId, HttpSession session,
                              HttpServletRequest req) {

        // Ürünü ID'sine göre al ve Servlet context üzerine set et
        Product product = pdao.getProductById(Integer.valueOf(productId));
        req.getServletContext().setAttribute("command", product); // 'command' yerine 'product' olarak değiştir
        return "producteditform"; // Ürün düzenleme formuna yönlendir
    }

    /**
     * Düzenlenmiş bir ürünü kaydeder.
 
     */
    @RequestMapping(value = "/producteditsave", method = RequestMethod.POST)
    public String saveEditedProduct(
            @RequestParam("productId") int productId,
            @RequestParam("userId") int userId,
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam("quantity") int quantity,
            @RequestParam("price") double price,
            HttpSession session) {

        // Yeni bilgilerle ürün oluştur
        Product product = new Product();
        product.setProductId(productId);
        product.setName(name);
        product.setCategory(category);
        product.setQuantity(quantity);
        product.setPrice(price);
        product.setUserId(userId);

        // Ürünü güncelle
        pdao.update(product);

        return "redirect:/viewproduct"; // Ürünler sayfasına yönlendir
    }

    /**
     * Belirli bir ürünü siler.
  
     */
    @RequestMapping(value = "/deleteproduct", method = RequestMethod.GET)
    public String deleteProduct(@RequestParam("productId") String id, HttpSession session) {
        // Ürünü ID'sine göre sil
        pdao.delete(Integer.valueOf(id));
        return "redirect:/viewproduct"; // Ürünler sayfasına yönlendir
    }

    /**
     * Tüm ürünleri görüntüler.

     */
    @RequestMapping("/viewproduct")
    public String viewproductsforadmin(HttpServletRequest req) {
        // Tüm ürünleri al ve Servlet context üzerine set et
        List<Product> list = pdao.getProducts();
        req.getServletContext().setAttribute("list", list);
        return "viewproduct"; // Ürünleri görüntüleme sayfasına yönlendir
    }
}
