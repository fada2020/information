package jp.co.info.ais.asm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.info.ais.asm.domain.CodeMaster;
import jp.co.info.ais.asm.service.CodeMasterService;

@Controller
@RequestMapping("/codemaster")

public class CodeMasterController {
	private static final Logger logger = LogManager.getLogger(CodeMasterController.class);
	@Autowired
	private CodeMasterService codeMasterService;

	@Autowired
	HttpSession session;


	@RequestMapping(value ="",method = RequestMethod.POST)
	public String masterIdNameInsert(Model model, CodeMaster codemaster) {
		try {
		String id = (String) session.getAttribute("codeMasterId");
		String name =(String) session.getAttribute("codeMasterName");

		List<CodeMaster> codeMasterList = codeMasterService.selectCodeMasterList();

		if(id.equals(codemaster.getCodeMasterId())&&name.equals(codemaster.getCodeMasterName())){
			logger.info("すでに存在しています。。");
			model.addAttribute("errorMessage", "すでに存在しています。");
			session.setAttribute("errorMessage", "すでに存在しています。");
			return "redirect/:";

		}else {

			codemaster.setCodeMasterId(id);
			codemaster.setCodeMasterName(name);


			codeMasterService.insertMasterId(codemaster);
			codeMasterService.insertMasterName(codemaster);
			session.setAttribute("codemaster",codemaster);

			return "";

		}
		}catch (Exception e) {
    		logger.error(e.getMessage());
		}

		return "code.html";
	}
}
