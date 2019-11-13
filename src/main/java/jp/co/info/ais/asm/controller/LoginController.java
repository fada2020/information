package jp.co.info.ais.asm.controller;


import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.info.ais.asm.domain.Login;
import jp.co.info.ais.asm.service.LoginService;


@Controller
public class LoginController {

	private static final Logger logger = LogManager.getLogger(LoginController.class);

	@Autowired
	HttpSession session;

	@Autowired
	private LoginService loginservice;

	@RequestMapping("/")
    public String index() {
        return "login.html";
    }


	//Login button id & pass check
	@RequestMapping(value= "/loginCheck", method = RequestMethod.POST)
	public String loginCheck(@RequestParam String assetId, @RequestParam String assetPass, Model model)  {

		int userCheck = loginservice.selectLoginId(assetId);

		if (userCheck != 1) {
			model.addAttribute("errorMessage", "社員番号が存在しません。");
			return "login";
		}

		Login user = loginservice.selectLogin(assetId, assetPass);
		if (user == null) {
			model.addAttribute("errorMessage", "パスワードが一致しないです。");
			return "login";
		}

		session.setAttribute("organizationCode", user.getOrganizationCode());
		session.setAttribute("name", user.getEmpName());


		return "index";
	}

}
