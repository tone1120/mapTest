package net.kdigital.map.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping({"","/"})
	public String map() {
		return "map_test2";
	}
	


}
