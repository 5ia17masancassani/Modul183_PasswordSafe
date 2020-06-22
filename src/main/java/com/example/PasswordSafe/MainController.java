package com.example.PasswordSafe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

	@Autowired
	private PasswordService passwordService;

	@GetMapping("/")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@PostMapping("/login")
	public String view(Model model, @ModelAttribute User user) {
		if(user.getUsername().equals("Admin")){
			String temp = user.getPassword();
			user.setPassword(passwordService.encryptPassword(user.getPassword()));
			if(user.getPassword().equals("dKjJUq85r3iDkOoxFlG2bg==")){
				passwordService.decryptPassword(user.getPassword());
				model.addAttribute("user", user);
				return "userView";
			} else{
				user.setPassword(temp);
				model.addAttribute("error", "Password incorrect!");
				return "login";
			}

		} else{
			model.addAttribute("error", "Username incorrect!");
			return "login";
		}

	}

}
