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

import jp.co.info.ais.asm.service.CodeService;

@Controller
@RequestMapping("/code")
public class CodeController {

	/**
	 *
	 */
	private static final Logger logger = LogManager.getLogger(CodeController.class);

	@Autowired
	private CodeService codeService;

	@Autowired
	HttpSession session;
	 @RequestMapping(value = "", method = RequestMethod.GET)
	    public String History(Model model) {
			model.addAttribute("stateCode", codeService.selectStateCode());
	    	return "code";
	    }
	 /**
	  *
	  * @return
	  */
	 @RequestMapping(value= "/codeprocess", method = RequestMethod.POST)
	 public String MasterCodeCheck(@RequestParam String codeMasterId, @RequestParam String codeMasterName, Model model) {
		logger.info("CODE MASTER ID 存在チェック =====");
		logger.info("code_master_id :" + codeMasterId);
		logger.info("code_master_name" + codeMasterName);


		try {
				int masterIdCheck =codeService.selectMasterId(codeMasterId);

				if(masterIdCheck == 1) {
					model.addAttribute("errorMessage","マスターコードが存在してます。");
					return "redirect:/";
				}


		}catch(Exception e) {
		logger.error("処理エラーが発生",e.toString());
		return "";
		}



		 return "";

	 }


}
