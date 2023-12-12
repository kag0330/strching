package com.stretching.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.stretching.service.UserService;

@Controller
public class GoodsController {
	@Autowired
	private UserService userService;

	@GetMapping("/sportingGoods")
	public String goods(Authentication auth, Model model) {
		userService.logincheck(auth, model);
		return "sportingGoods";
	}

	@GetMapping("/sportingGoods2")
	public String goods2(Authentication auth, Model model) {
		userService.logincheck(auth, model);
		return "sportingGoods2";
	}

	@GetMapping("/sportingGoods3")
	public String goods3(Authentication auth, Model model) {
		userService.logincheck(auth, model);
		return "sportingGoods3";
	}

	@GetMapping("/sportingGoods4")
	public String goods4(Authentication auth, Model model) {
		userService.logincheck(auth, model);
		return "sportingGoods4";
	}

	@GetMapping("/sportingGoods5")
	public String goods5(Authentication auth, Model model) {
		userService.logincheck(auth, model);
		return "sportingGoods5";
	}

	@GetMapping("/detail")
	public String detail(Authentication auth, Model model) {
		userService.logincheck(auth, model);
		return "detail";
	}

	@GetMapping("/s_Band")
	public String sband(Authentication auth, Model model) {
		userService.logincheck(auth, model);
		return "s_Band";
	}

	@GetMapping("/s_Band2")
	public String sband2(Authentication auth, Model model) {
		userService.logincheck(auth, model);
		return "s_Band2";
	}

	@GetMapping("/s_Band3")
	public String sband3(Authentication auth, Model model) {
		userService.logincheck(auth, model);
		return "s_Band3";
	}

	@GetMapping("/s_FoamRoller")
	public String sFoamRoller(Authentication auth, Model model) {
		userService.logincheck(auth, model);
		return "s_FoamRoller";
	}

	@GetMapping("/s_FoamRoller2")
	public String sFoamRoller2(Authentication auth, Model model) {
		userService.logincheck(auth, model);
		return "s_FoamRoller2";
	}

	@GetMapping("/s_FoamRoller3")
	public String sFoamRoller3(Authentication auth, Model model) {
		userService.logincheck(auth, model);
		return "s_FoamRoller3";
	}

	@GetMapping("/s_MassageBall")
	public String sMassageBall(Authentication auth, Model model) {
		userService.logincheck(auth, model);
		return "s_MassageBall";
	}

	@GetMapping("/s_MassageBall2")
	public String sMassageBall2(Authentication auth, Model model) {
		userService.logincheck(auth, model);
		return "s_MassageBall2";
	}

}
