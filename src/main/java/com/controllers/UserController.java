package com.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.beans.User;
import com.dao.UserDao;


@Controller
public class UserController {
	@Autowired
	UserDao udao;// will inject dao from XML file

	@RequestMapping("/")
	public String index(Model m) {
		return "redirect:index.jsp";
	}

	/*
	 * It displays a form to input data, here "command" is a reserved request
	 * attribute which is used to display object data into form
	 */
	
	@RequestMapping("/register")
	public String showform(Model m) {
		m.addAttribute("command", new User());
		return "register";
	}
	

	/*
	 * It saves object into database. The @ModelAttribute puts request data into
	 * model object. You need to mention RequestMethod.POST method because default
	 * request is GET
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("user") User user) {
		udao.save(user);
		return "homepage_foruser";// will redirect to viewemp request mapping
	}
	/* It provides list of employees in model object */    
    @RequestMapping("/viewemp")    
    public String viewemp(Model m){    
        List<User> list=udao.getUsers();    
        m.addAttribute("list",list);  
        return "viewemp";   
    }    
    /* It displays object data into form for the given id.   
     * The @PathVariable puts URL data into variable.*/    
    @RequestMapping(value="/editemp/{id}")    
    public String edit(@PathVariable int id, Model m){    
        User user=udao.getUserById(id);    
        m.addAttribute("command",user);  
        return "empeditform";    
    }    
    /* It updates model object. */    
    @RequestMapping(value="/editsave",method = RequestMethod.POST)    
    public String editsave(@ModelAttribute("user") User user){    
        udao.update(user);    
        return "redirect:/viewemp";    
    }    
    /* It deletes record for the given id in URL and redirects to /viewemp */    
    @RequestMapping(value="/deleteuser/{id}",method = RequestMethod.GET)    
    public String delete(@PathVariable int id){    
        udao.delete(id);    
        return "redirect:/viewemp";    
    }
	
}
