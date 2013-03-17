package br.com.camiloporto.tenant.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.camiloporto.tenant.model.Usuario;

@RequestMapping("/usuarios")
@Controller
public class UserController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String showNewUserForm() {
		return "usuarios/create";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String createUser(Usuario u) {
		return u.toString();
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Usuario u) {
		
		return "redirect:/realestates";
	}
	
}
