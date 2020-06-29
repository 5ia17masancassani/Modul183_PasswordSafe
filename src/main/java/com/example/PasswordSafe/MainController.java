package com.example.PasswordSafe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

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
	public String view(Model model, @ModelAttribute User user) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		if(user.getUsername().equals("Admin")){
			String temp = user.getPassword();
			user.setPassword(passwordService.encryptString(user.getPassword()));
			if(user.getPassword().equals("dnX3MkSqVapcPTPS+KDFrA==")){
				passwordService.decryptString(user.getPassword());
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
