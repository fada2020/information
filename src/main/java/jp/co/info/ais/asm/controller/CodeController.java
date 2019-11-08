package jp.co.info.ais.asm.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import jp.co.info.ais.asm.service.CodeService;

@Controller
public class CodeController {

	private static final Logger logger = LogManager.getLogger(CodeController.class);

	@Autowired
	private CodeService codeService;

    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public String indexTest(Model model,
    		@SessionAttribute(name = "MY_SESSION_MESSAGES", required = false) List<String> messages) {

    	logger.debug(messages.toString() + " indexTest");

		model.addAttribute("message", "Hello!! Springboot!!!!");
        return "code";
    }
}
