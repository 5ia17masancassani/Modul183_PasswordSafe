package com.example.PasswordSafe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Controller
public class MainController {
	@Autowired
	private RecordService recordService;

	@Autowired
	private PasswordService passwordService;

	int counter = 0;

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
				model.addAttribute("record", new Record());
				model.addAttribute("records", recordService.getRecords());
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
	@PostMapping("/addRecord")
	public String view(Model model, @ModelAttribute Record record) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		record.setPassword(passwordService.encryptString(record.getNormalPassword()));
		record.setId(counter);
		recordService.addRecord(record);
		counter++;
		model.addAttribute("records", recordService.getRecords());
		return "userView";
	}

	@RequestMapping(value = "/delete_user", method = RequestMethod.GET)
	public String handleDeleteUser(@RequestParam(name="record")int id, Model model) {
		recordService.removeRecord(id);
		model.addAttribute("record", new Record());
		model.addAttribute("records", recordService.getRecords());
		return "userView";
	}


}
