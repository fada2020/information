package jp.co.info.ais.asm.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.info.ais.asm.domain.Login;


@Controller
public class LoginController {

	private static final Logger logger = LogManager.getLogger(LoginController.class);

	@RequestMapping("/")
    public String index() {
        return "login.html";
    }


	//Login button id & pass check
	@RequestMapping(value= "/loginCheck", method = RequestMethod.POST)
	public String loginCheck(@ModelAttribute("login") Login login)  {
		//





		return "index.html";
	}


	//id & check ok >> Mainpage

	//間違い



}
