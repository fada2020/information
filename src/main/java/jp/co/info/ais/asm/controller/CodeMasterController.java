package jp.co.info.ais.asm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.info.ais.asm.common.Page;
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

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String masterIdNameInsert(Model model, CodeMaster codemaster) {
		try {
			String id = (String) session.getAttribute("codeMasterId");
			String name = (String) session.getAttribute("codeMasterName");

			if (id.equals(codemaster.getCodeMasterId()) && name.equals(codemaster.getCodeMasterName())) {
				logger.info("すでに存在しています。。");
				model.addAttribute("errorMessage", "すでに存在しています。");
				session.setAttribute("errorMessage", "すでに存在しています。");
				return "redirect/:";

			} else {

				codemaster.setCodeMasterId(id);
				codemaster.setCodeMasterName(name);

				codeMasterService.insertMasterId(codemaster);
				codeMasterService.insertMasterName(codemaster);
				session.setAttribute("codemaster", codemaster);

				return "";

			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return "code.html";
	}

	@RequestMapping(value = "/codeList", method = RequestMethod.POST)
	@ResponseBody
	public Page<CodeMaster> CodeList(@RequestBody Page<CodeMaster> page) {

		// 検索条件
		CodeMaster condition = new CodeMaster();

		// ページング処理
		condition.setLength(page.getLength());
		condition.setStart(page.getStart());

		String codeMasterId = page.getColumns().get(0).getSearch().getValue();
		if (null != codeMasterId && !codeMasterId.equals("")) {
			condition.setCodeMasterId(codeMasterId);
		}

		String codeMasterName = page.getColumns().get(1).getSearch().getValue();
		if (null != codeMasterName && !codeMasterName.equals("")) {
			condition.setCodeMasterName(codeMasterName);
		}

		// リスト照会
		List<CodeMaster> CodeList = codeMasterService.selectCodeMasterList(condition);

		// データをページオブジェクトにセット
		page.setData(CodeList);

		// 総数
		int totalCount = codeMasterService.selectCount(condition);

		page.setRecordsFiltered(totalCount);

		return page;
	}

	@RequestMapping(value = "/CodeListCheck", method = RequestMethod.POST)
	@ResponseBody
	public int CodeListCheck(@RequestBody CodeMaster masterCode) {
		int num = 0;
		try {

			num = codeMasterService.CodeMasterListCheck(masterCode);

		} catch (Exception e) {
			logger.debug(e.getMessage());
		}

		return num;
	}

<<<<<<< HEAD
	@RequestMapping(value = "/updateCodeMaster", method = RequestMethod.POST)
	@ResponseBody
	public int updateCodeMaster(@RequestBody CodeMaster masterCode) {
		int num = 0;
		try {

			codeMasterService.updateCodeMaster(masterCode);

		} catch (Exception e) {
			logger.debug(e.getMessage());
		}

		return num;
	}
=======
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public int assetDelete(@RequestBody String codeMasterId) {
    	int result = 0;
    	try {
	    	// 資産情報削除
	    	result = codeMasterService.deleteMasterCode(codeMasterId);
    	}catch (Exception e) {
    		logger.error(e.getMessage());
		}
        return result;
    }
>>>>>>> 3be52e8d4c16704a50ade481eb63dbaf52450ea4

}
