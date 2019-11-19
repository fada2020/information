package jp.co.info.ais.asm.controller;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.info.ais.asm.service.CodeService;

@Controller
@RequestMapping("/code")
public class CodeController {

	private static final Logger logger = LogManager.getLogger(CodeController.class);

	@Autowired
	private CodeService CodeService;

	@Autowired
	HttpSession session;
	 @RequestMapping(value = "", method = RequestMethod.GET)
	    public String History(Model model) {
			model.addAttribute("stateCode", CodeService.selectStateCode());
	    	return "code";
	    }


}
