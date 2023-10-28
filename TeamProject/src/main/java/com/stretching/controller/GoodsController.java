package com.stretching.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoodsController {
	
	@GetMapping("/sportingGoods")
	public String goods() {
		return "sportingGoods";
	}
	@GetMapping("/detail")
	public String detail() {
		return "detail";
	}
	@GetMapping("/s_Band")
	public String sband() {
		return "s_Band";
	}
	@GetMapping("/s_FoamRoller")
	public String sFoamRoller() {
		return "s_FoamRoller";
	}
	@GetMapping("/s_MassageBall")
	public String sMassageBall() {
		return "s_MassageBall";
	}

}
