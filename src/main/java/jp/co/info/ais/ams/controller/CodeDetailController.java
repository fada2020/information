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

import jp.co.info.ais.ams.common.AppConstant;
import jp.co.info.ais.ams.common.Page;
import jp.co.info.ais.ams.domain.CodeDetail;
import jp.co.info.ais.ams.domain.CodeMaster;
import jp.co.info.ais.ams.service.CodeDetailService;
import jp.co.info.ais.ams.service.CodeMasterService;

@Controller
@RequestMapping("/codedetail")
public class CodeDetailController {
	private static final Logger logger = LogManager.getLogger(CodeMasterController.class);
	@Autowired
	private CodeDetailService codeDetailService;
	@Autowired
	private CodeMasterService codeMasterService;

	@Autowired
	HttpSession session;
	@Autowired
	AppConstant appconstant;
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String assetList(Model model) {
		try {
			// コード値セッティング
			model.addAttribute("selectmasterid", codeDetailService.selectMasterCodeId());
			CodeMaster codemaster = new CodeMaster();
			codemaster.setUseFlag(appconstant.USE_CODE);
			CodeDetail codeDetail=	new CodeDetail();
			codeDetail.setUseFlag(appconstant.USE_CODE);
			model.addAttribute("masterid",codeDetailService.codeMasterList(codemaster));
			model.addAttribute("countId",codeDetailService.selectCount(codeDetail));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "codedetail";
	}

	@RequestMapping(value = "/codedetailList", method = RequestMethod.POST)
	@ResponseBody
	public Page<CodeDetail> CodeDetailList(@RequestBody Page<CodeDetail> page) {
		try {
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

						String codeDetailName = page.getColumns().get(2).getSearch().getValue();
						if (null != codeDetailName && !codeDetailName.equals("")) {
							condition.setCodeDetailName(codeDetailName);
						}
						condition.setUseFlag(appconstant.USE_CODE);
			// リスト照会
			List<CodeDetail> CodeDetailList = codeDetailService.selectCodeDetailList(condition);

			page.setData(CodeDetailList);

			int totalCount = codeDetailService.selectCount(condition);

			page.setRecordsFiltered(totalCount);


		} catch (Exception e) {
			logger.debug(e.getMessage());
		}

		return page;
	}

	@RequestMapping(value = "/detaillistcheck", method = RequestMethod.POST)
	@ResponseBody
	public int CodeListCheck(@RequestBody CodeDetail codedetail) {
		int num = 0;
		logger.debug(codedetail);
		try {
			///結果が正しい場合チェックメソッド実行してnumに含める
			num = codeDetailService.CodeDetailListCheck(codedetail);

		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		return num;
	}

	/**

	 * マスターコード修正
	 * @param masterCode マスターコード
	 * @return int num
	 */
	@RequestMapping(value = "/updateCodeDetail", method = RequestMethod.POST)
	@ResponseBody
	public int updateCodeDetail(@RequestBody CodeDetail detailCode) {
		int num = 0;
		try {
			//結果が正しい場合修正メソッド実行してnumに含める
			num=codeDetailService.updateCodeDetail(detailCode);

		} catch (Exception e) {
			logger.debug(e.getMessage());
		}

		return num;
	}

	 /** 詳細ーコード削除
	 * @param
	 * @return
	 */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public int detailtDelete(@RequestBody String codeMDetail) {
    	int result = 0;
    	logger.debug("code:{}",codeMDetail);
    	try {
    		String[] array=codeMDetail.split(",");
    		CodeDetail codeDetail = new CodeDetail();
    		codeDetail.setCodeMasterId(array[0]);
    		codeDetail.setCodeDetailId(array[1]);
    		logger.debug(array[0]+","+array[1]);
    		//結果が正しい場合削除メソッド実行してresultに含める
	    	result = codeDetailService.deleteDetailCode(codeDetail);

    	}catch (Exception e) {
    		logger.error(e.getMessage());
		}
        return result;
    }
	@RequestMapping(value = "/codeListCheck", method = RequestMethod.POST)
	@ResponseBody
	public int codeListCheck(@RequestBody CodeDetail codeMDetail) {
		int num = 0;

		try {
			///結果が正しい場合チェックメソッド実行してnumに含める
			num = codeDetailService.codeDetailListCheck(codeMDetail);

		} catch (Exception e) {
			logger.debug(e.getMessage());
		}

		return num;
	}

	@RequestMapping(value = "/checkNumber", method = RequestMethod.POST)
	@ResponseBody
	public int checkNumber(@RequestBody String codeMDetail) {
		int num =0;

		CodeDetail codeDetail = new CodeDetail();
		codeDetail.setCodeMasterId(codeMDetail);
		try {
			///結果が正しい場合チェックメソッド実行してnumに含める
			num = codeDetailService.selectCount(codeDetail);
			logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++"+(num/10));

		} catch (Exception e) {
			logger.debug(e.getMessage());
		}

		return num;
	}



}
