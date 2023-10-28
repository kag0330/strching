package com.stretching.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stretching.dto.UserDto;
import com.stretching.service.UserService;

@Controller
public class LoginController {

	private final UserService userService;
	
	public LoginController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/signin")
	public String login() {
		return "signin";
	}
	@GetMapping("/signup")
	public String signup(Model model, UserDto userDto) {
		model.addAttribute("userDto", userDto);
		return "signup";
	}
	
	@GetMapping("/find_id")
	public String findid() {
		return "find_id";
	}
	@GetMapping("/find_pass")
	public String findpass() {
		return "find_pass";
}
	@GetMapping("/myPage")
	public String mypage() {
		return "myPage";
	}
	/**=====================*/
	
	@PostMapping("signup/ok")
	public String signupOk(@ModelAttribute("userDto") UserDto userDto,
							Model model) {
		if(userService.UserSave(userDto) == true) {
			return "redirect:/signin";
		}else {
			model.addAttribute("useId", userDto.getId());
			return "redirect:/signup";
		}
	}
	
	@PostMapping("/idCheck")
	@ResponseBody
	public boolean idCheck(@RequestParam("id")String id) {
		return userService.UserIdCheck(id);
	}
}
