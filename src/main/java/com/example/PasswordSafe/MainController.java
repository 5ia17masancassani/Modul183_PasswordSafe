package com.example.PasswordSafe;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@PostMapping("/login")
	public String view(Model model, @ModelAttribute User user) {
		if(user.getUsername().equals("Admin")){
			if(user.getPassword().equals("123")){
				model.addAttribute("user", user);
				return "userView";
			} else{
				model.addAttribute("error", "Password incorrect!");
				return "login";
			}

		} else{
			model.addAttribute("error", "Username incorrect!");
			return "login";
		}

	}

}
