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
import org.springframework.web.bind.annotation.PathVariable;
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
import java.net.http.HttpRequest;
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
	/*
	 * @GetMapping("/add-product") public String showAddProductForm(Model model) {
	 * model.addAttribute("product", new Product()); return "add-product"; }
	 */

	@PostMapping("/add-product")
	public String addProduct(@ModelAttribute("product") Product product, @RequestParam("image") MultipartFile file,
			HttpSession session, HttpServletRequest req) {
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			return "redirect:/register";
		}

		if (!file.isEmpty()) {
			try {
				// Dosyayı kaydet
				String uploadsDir = "/uploads/";
				String realPathtoUploads = Paths
						.get(System.getProperty("user.dir"), "src", "main", "webapp", uploadsDir).toString();
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
		product.setUserId(user.getId());
		pdao.save(product);
		return "redirect:/profile";
	}

	@RequestMapping("/profile")
	public String viewproduct(HttpSession session, HttpServletRequest req) {
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			return "redirect:/register";
		}
		List<Product> list = pdao.getProductsByUserId(user.getId());
		req.getServletContext().setAttribute("list", list);
		return "profile";
	}

	@RequestMapping("/viewdetail")
	public String viewProductDetails(@RequestParam("productId") String productId, HttpServletRequest req) {
		Product product = pdao.getProductById(Integer.valueOf(productId));
		req.getServletContext().setAttribute("product", product);
		return "product-details";
	}

	@RequestMapping("/shareproduct")
	public String share(@RequestParam("productId") String productId, HttpServletRequest req) {
		Product product = pdao.getProductById(Integer.valueOf(productId));
		req.getServletContext().setAttribute("product", product);
		return "homepage_foruser";
	}

	@RequestMapping(value = "/editproduct")
	public String editProduct(@RequestParam("productId") String productId, HttpSession session,
			HttpServletRequest req) {
		
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		Product product = pdao.getProductById(Integer.valueOf(productId));
		
		req.getServletContext().setAttribute("command", product);
		return "producteditform";
		// Yetkilendirme kontrolü
		/*
		 * if (loggedInUser != null && ("admin@admin".equals(loggedInUser.getEmail())))
		 * { return "producteditform"; } else { return "redirect:/profile"; }
		 */
	}

	@RequestMapping(value = "/producteditsave", method = RequestMethod.POST)
	public String saveEditedProduct(@ModelAttribute("product") Product product, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		
	    if (product != null) {
	        // Update the user properties
	       product.setUserId(loggedInUser.getId());
	        
	        // Perform the update
	        pdao.update(product);
	    }
		pdao.update(product);
		// Yetkilendirme kontrolü
		if (loggedInUser != null && ("admin@admin".equals(loggedInUser.getEmail()))) {
			return "redirect:/viewproduct";
		} else {
			return "redirect:/profile";
		}
	}

	@RequestMapping(value = "/deleteproduct", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam("productId") int id, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		//int productId = Integer.valueOf(id);
		
		pdao.delete(id);
		// Yetkilendirme kontrolü
		if (loggedInUser != null && ("admin@admin".equals(loggedInUser.getEmail()))) {
			return "redirect:/viewproduct";
		} else {
			return "redirect:/profile";
		}
	}

	@RequestMapping("/viewproduct")
	public String viewproductsforadmin(HttpServletRequest req) {
		List<Product> list = pdao.getProducts();
		req.getServletContext().setAttribute("list", list);
		return "viewproduct";
	}

}

