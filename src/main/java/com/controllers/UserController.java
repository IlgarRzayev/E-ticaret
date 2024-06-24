package com.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.beans.User;
import com.dao.CartDao;
import com.dao.ProductDao;
import com.dao.UserDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	UserDao udao; // will inject dao from XML file
	@Autowired
	ProductDao pdao;
	@Autowired
	CartDao cartdao; // will inject dao from XML file
	

	@GetMapping("/")
	public String index(HttpServletRequest req) {
		req.getServletContext().setAttribute("products", pdao.getProducts());
		return "homepage_foruser";
	}

	@RequestMapping("/register")
	public String showform(HttpServletRequest req) {
		req.getServletContext().setAttribute("user", new User()); // Change 'command' to 'user'
		return "register";	
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("user") User user, HttpSession session, HttpServletRequest req) {
		// Check if the email already exists in the database
		User existingUser = udao.getUserByEmail(user.getEmail());
		if (existingUser != null) {
			// If email already exists, add error message to model
			req.getServletContext().setAttribute("error", "Kullanılmış email adresi");
			return "register";
		}

		// Save the user if email does not exist
		udao.save(user);
		session.setAttribute("loggedInUser", user);
		return "homepage_foruser";
	}

	@RequestMapping(value = "/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session, HttpServletRequest req) {
		User user = udao.getUserByEmailAndPassword(email, password);

		if (user != null) {
			session.setAttribute("loggedInUser", user);
			req.getServletContext().setAttribute("user", user);

			// Kullanıcının rolünü kontrol et
			if ("admin@admin".equals(user.getEmail()) && "admin".equals(user.getPassword())) {
				session.setAttribute("ADMIN", user);
				return "admin-page"; // Admin kullanıcı için ana sayfa
			} else {
				return "homepage_foruser"; // Normal kullanıcı için ana sayfa
			}
		} else {
			req.getServletContext().setAttribute("error", "Invalid email or password");
			return "register";
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// Oturumu sonlandır
		session.removeAttribute("loggedInUser");
		session.invalidate(); // Oturumu geçersiz kıl

		return "homepage_foruser"; // Ana sayfaya yönlendir
	}

	// Asagidaki islemler admin icin
	@RequestMapping(value = "/add-user", method = RequestMethod.POST)
	public String add(@ModelAttribute("user") User user) {
		udao.save(user);
		return "admin-page"; // will redirect to viewemp request mapping
	}

	@RequestMapping("/viewuser")
	public String viewuser(HttpServletRequest req) {
		List<User> list = udao.getUsers();
		req.getServletContext().setAttribute("list", list);
		return "viewuser";
	}

	@RequestMapping(value = "/edituser")
	public String edit(@RequestParam("id") String id, HttpServletRequest req) {
		User user = udao.getUserById(Integer.valueOf(id));
		req.getServletContext().setAttribute("command", user); // Change 'command' to 'user'
		return "usereditform";
	}

	@RequestMapping(value = "/usereditsave", method = RequestMethod.POST)
	public String editsave(@RequestParam("id") int id,
	                       @RequestParam("name") String name, 
	                       @RequestParam("email") String email, 
	                       @RequestParam("surname") String surname) {
	    // Fetch the existing user from the database
	    User user = udao.getUserById(id);
	    if (user != null) {
	        // Update the user properties
	        user.setName(name);
	        user.setEmail(email);
	        user.setSurname(surname);
	        
	        // Perform the update
	        udao.update(user);
	    }
	    return "redirect:/admin-page";
	}


	@RequestMapping(value = "/deleteuser", method = RequestMethod.GET)
	public String delete(@RequestParam("id") String id) {
		int userId = Integer.parseInt(id);
        
        // Önce kullanıcının ürünlerini sil
		cartdao.deleteByUserId(userId);
		pdao.deleteByUserId(userId);
        
        // Sonra kullanıcının sepet kayıtlarını sil
        
        
        // Son olarak kullanıcıyı sil
        udao.delete(userId);
		return "redirect:/admin-page";
	}
}
