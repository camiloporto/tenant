package br.com.camiloporto.tenant.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/usuarios")
@Controller
public class UserController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String createUser() {
		return "redirect:/realestates";
	}
	
}
