package br.com.camiloporto.tenant.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/realestates")
@Controller
public class RealEstateController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String home() {
		return "realestate/index";
	}
	
	@RequestMapping(params={"id"}, method = RequestMethod.GET)
	public String detail(@RequestParam("id") Long idRealEstate) {
		return "realestate/detail";
	}

}
