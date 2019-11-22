package jp.co.info.ais.asm.controller;

import javax.servlet.http.HttpSession;

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
	@Autowired
	private CodeMasterService codeMasterService;

	@Autowired
	HttpSession session;


	@RequestMapping(value ="/masteridnameinsert",method = RequestMethod.POST)
	public String masterIdNameInsert(Model model, CodeMaster codemaster) {
		String id = (String) session.getAttribute("codeMasterId");
		String name =(String) session.getAttribute("codeMasterName");
		codemaster.setCodeMasterId(id);
		codemaster.setCodeMasterName(name);

		codeMasterService.insertMasterId(codemaster);
		codeMasterService.insertMasterName(codemaster);



		return "codemaster";


	}
}
