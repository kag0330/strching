package com.stretching.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoodsController {
	
	@GetMapping("/sportingGoods")
	public String goods() {
		return "sportingGoods";
	}
	@GetMapping("/sportingGoods2")
	public String goods2() {
		return "sportingGoods2";
	}
	@GetMapping("/sportingGoods3")
	public String goods3() {
		return "sportingGoods3";
	}
	@GetMapping("/sportingGoods4")
	public String goods4() {
		return "sportingGoods4";
	}
	@GetMapping("/sportingGoods5")
	public String goods5() {
		return "sportingGoods5";
	}
	@GetMapping("/detail")
	public String detail() {
		return "detail";
	}
	@GetMapping("/s_Band")
	public String sband() {
		return "s_Band";
	}
	@GetMapping("/s_Band2")
	public String sband2() {
		return "s_Band2";
	}
	@GetMapping("/s_Band3")
	public String sband3() {
		return "s_Band3";
	}
	@GetMapping("/s_FoamRoller")
	public String sFoamRoller() {
		return "s_FoamRoller";
	}
	@GetMapping("/s_FoamRoller2")
	public String sFoamRoller2() {
		return "s_FoamRoller2";
	}
	@GetMapping("/s_FoamRoller3")
	public String sFoamRoller3() {
		return "s_FoamRoller3";
	}
	@GetMapping("/s_MassageBall")
	public String sMassageBall() {
		return "s_MassageBall";
	}
	@GetMapping("/s_MassageBall2")
	public String sMassageBall2() {
		return "s_MassageBall2";
	}
	
}
