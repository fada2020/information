package jp.co.info.ais.ams.controller;

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

import jp.co.info.ais.ams.common.Page;
import jp.co.info.ais.ams.domain.CodeDetail;
import jp.co.info.ais.ams.service.CodeDetailService;

@Controller
@RequestMapping("/codedetail")

public class CodeDetailController {
	private static final Logger logger = LogManager.getLogger(CodeMasterController.class);
	@Autowired
	private CodeDetailService codeDetailService;

	@Autowired
	HttpSession session;


	    @RequestMapping(value = "", method = RequestMethod.GET)
	    public String assetList(Model model) {
	    	try {
	    		// コード値セッティング
	    	model.addAttribute("selectmasterid", codeDetailService.selectMasterCodeId());
			/*	model.addAttribute("stateCode", codeDetailService.selectStateCode());
				model.addAttribute("kubunCode", codeDetailService.selectKubunCode());*/
	    	}catch (Exception e) {
	    		logger.error(e.getMessage());
			}
	        return "codedetail.html";
	    }


	@RequestMapping(value = "/codedetaileList", method = RequestMethod.POST)
	@ResponseBody
	public Page<CodeDetail> CodeDetailList(@RequestBody Page<CodeDetail> page){

		// 検索条件
		CodeDetail condition = new CodeDetail();

		// ページング処理
		condition.setLength(page.getLength());
		condition.setStart(page.getStart());

		String codeMasterId = page.getColumns().get(0).getSearch().getValue();
		if (null != codeMasterId && !codeMasterId.equals("")) {
			condition.setCodeMasterId(codeMasterId);
		}

		String codeDetailId = page.getColumns().get(1).getSearch().getValue();
		if (null != codeDetailId && !codeDetailId.equals("")) {
			condition.setCodeDetailId(codeDetailId);
		}

		String codeDetailName = page.getColumns().get(1).getSearch().getValue();
		if (null != codeDetailName && !codeDetailName.equals("")) {
			condition.setCodeDetailId(codeDetailName);
		}
		// リスト照会
		List <CodeDetail> CodeDetailList = codeDetailService.selectCodeDetailList(condition);

		page.setData(CodeDetailList);

		int totalCount = codeDetailService.selectCount(condition);

		page.setRecordsFiltered(totalCount);

		return page;
	}
}
