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
    UserDao udao; 
    @Autowired
    ProductDao pdao;
    @Autowired
    CartDao cartdao; 

    
     // Ana sayfayı gösterir.
     
    @GetMapping("/")
    public String index(HttpServletRequest req) {
        req.getServletContext().setAttribute("products", pdao.getProducts());
        return "homepage_foruser";
    }

   
     // Kullanıcı kayıt formunu gösterir.
     
    @RequestMapping("/register")
    public String showform(HttpServletRequest req) {
        req.getServletContext().setAttribute("user", new User()); // 'command' yerine 'user' olarak değiştir
        return "register";
    }

    
     // Yeni kullanıcıyı kaydeder.
     
     
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("user") User user, HttpSession session, HttpServletRequest req) {
        // Email adresinin veritabanında var olup olmadığını kontrol et
        User existingUser = udao.getUserByEmail(user.getEmail());
        if (existingUser != null) {
            // Eğer email zaten varsa hata mesajını modele ekle
            req.getServletContext().setAttribute("error", "Kullanılmış email adresi");
            return "register";
        }

        // Eğer email adresi yoksa kullanıcıyı kaydet
        udao.save(user);
        session.setAttribute("loggedInUser", user);
        return "homepage_foruser";
    }

    
     // Kullanıcı giriş işlemini gerçekleştirir.
     
    @RequestMapping(value = "/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password,
                        HttpSession session, HttpServletRequest req) {
        // Email ve şifreye göre kullanıcıyı getir
        User user = udao.getUserByEmailAndPassword(email, password);
        if (user != null) {
            session.setAttribute("loggedInUser", user);
            req.getServletContext().setAttribute("user", user);
            // Kullanıcının admin olup olmadığını kontrol et
            if ("admin@admin".equals(user.getEmail()) && "admin".equals(user.getPassword())) {
                session.setAttribute("ADMIN", user);
                return "admin-page"; // Admin kullanıcı için admin sayfasına yönlendir
            } else {
                return "homepage_foruser"; // Normal kullanıcı için ana sayfaya yönlendir
            }
        } else {
            req.getServletContext().setAttribute("error", "Geçersiz email veya şifre");
            return "register";
        }
    }

    
     // Kullanıcıyı oturumdan çıkarır.
     
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        // Oturumu sonlandır
        session.removeAttribute("loggedInUser");
        session.invalidate(); // Oturumu geçersiz kıl

        return "homepage_foruser"; // Ana sayfaya yönlendir
    }

    // Aşağıdaki işlemler admin için geçerlidir

    /**
     * Yeni bir kullanıcı ekler.
     
     */
    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public String add(@ModelAttribute("user") User user) {
        udao.save(user);
        return "admin-page"; // admin sayfasına yönlendir
    }

    
     //Tüm kullanıcıları görüntüler.
     
    @RequestMapping("/viewuser")
    public String viewuser(HttpServletRequest req) {
        // Tüm kullanıcıları getir ve Servlet context üzerine set et
        List<User> list = udao.getUsers();
        req.getServletContext().setAttribute("list", list);
        return "viewuser"; // Kullanıcıları görüntüleme sayfasına yönlendir
    }

    /**
     * Bir kullanıcının bilgilerini düzenlemek için formu gösterir.
     */
    @RequestMapping(value = "/edituser")
    public String edit(@RequestParam("id") String id, HttpServletRequest req) {
        // Kullanıcıyı ID'sine göre getir ve Servlet context üzerine set et
        User user = udao.getUserById(Integer.valueOf(id));
        req.getServletContext().setAttribute("command", user); // 'command' yerine 'user' olarak değiştir
        return "usereditform"; // Kullanıcı düzenleme formuna yönlendir
    }

    /**
     * Düzenlenmiş kullanıcı bilgilerini kaydeder.
     */
    @RequestMapping(value = "/usereditsave", method = RequestMethod.POST)
    public String editsave(@RequestParam("id") int id,
                           @RequestParam("name") String name,
                           @RequestParam("email") String email,
                           @RequestParam("surname") String surname) {
        // Veritabanından mevcut kullanıcıyı getir
        User user = udao.getUserById(id);
        if (user != null) {
            // Kullanıcı özelliklerini güncelle
            user.setName(name);
            user.setEmail(email);
            user.setSurname(surname);

            // Güncelleme işlemini yap
            udao.update(user);
        }
        return "redirect:/admin-page"; // Admin sayfasına yönlendir
    }

    /**
     * Bir kullanıcıyı siler.
     *
     */
    @RequestMapping(value = "/deleteuser", method = RequestMethod.GET)
    public String delete(@RequestParam("id") String id) {
        int userId = Integer.parseInt(id);

        // Önce kullanıcının sepet kayıtlarını sil
        cartdao.deleteByUserId(userId);

        // Sonra kullanıcıyı sil
        udao.delete(userId);
        return "redirect:/admin-page"; // Admin sayfasına yönlendir
    }
}
