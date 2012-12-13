package br.com.camiloporto.tenant.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/realestates")
@Controller
public class RealEstateController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String home() {
		return "realestate/index";
	}

}
