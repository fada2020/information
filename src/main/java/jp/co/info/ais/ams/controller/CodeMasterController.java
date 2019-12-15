package jp.co.info.ais.ams.controller;

import java.util.ArrayList;
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
@RequestMapping("/codemaster")

public class CodeMasterController {
	private static final Logger logger = LogManager.getLogger(CodeMasterController.class);
	@Autowired
	private CodeMasterService codeMasterService;
	@Autowired
	private CodeDetailService codeDetailService;
	@Autowired
	HttpSession session;
	@Autowired
	AppConstant appConstant;
	/**
	 *IDと名前存在チェック
	 *コード値セッティング
	 * @param model
	 * @param codemaster
	 * @return　String code.html
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String masterIdNameInsert(Model model) {
		try {
			String result="";
			int num=codeMasterService.selectCount(new CodeMaster())+1;
			if(num<10) {
				result="00"+num;
			}else if(num<100) {
				result="0"+num;
			}else {
				result=""+num;
			}
			model.addAttribute("countId",result);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return "code";
	}

		/**
		 * list出力
		 * コード値セッティング
		 * @param page
		 * @return　page　CodeList
		 */
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
			condition.setUseFlag(appConstant.USE_CODE);
			// リスト照会
			List<CodeMaster> CodeList = codeMasterService.selectCodeMasterList(condition);
			for(CodeMaster code :CodeList ) {
				code.setCheckId(codeMasterService.deleteCodeBefore(new CodeDetail(code.getCodeMasterId(), null, null,appConstant.USE_CODE)));
			}
			// データをページオブジェクトにセット
			page.setData(CodeList);

			// 総数
			int totalCount = codeMasterService.selectCount(condition);
			try {
			page.setRecordsFiltered(totalCount);
			}catch(Exception e) { logger.debug(e.getMessage());}
			return page;
		}

		/**
		 * Listの中に値の存在チェック
		 * @param masterCode マスターコード
		 * @return int num
		 */
		@RequestMapping(value = "/CodeListCheck", method = RequestMethod.POST)
		@ResponseBody
		public int CodeListCheck(@RequestBody CodeMaster masterCode) {
			int num = 0;



			logger.debug(masterCode);

			try {
				///結果が正しい場合チェックメソッド実行してnumに含める
				num = codeMasterService.CodeMasterListCheck(masterCode);

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
		@RequestMapping(value = "/updateCodeMaster", method = RequestMethod.POST)
		@ResponseBody
		public int updateCodeMaster(@RequestBody CodeMaster masterCode) {
			int num = 0;
			try {
				//結果が正しい場合修正メソッド実行してnumに含める
				 num = codeMasterService.updateCodeMaster(masterCode);
			} catch (Exception e) {
				logger.debug(e.getMessage());

			}

			return num;
		}

		/**
		 * マスターコード削除
		 * @param codeMasterId マスターコード
		 * @return result　codeMasterId
		 */
	    @RequestMapping(value = "/delete", method = RequestMethod.POST)
	    @ResponseBody
	    public int codeMasterDelete(@RequestBody String codeMasterId) {
	    	int result = 0;
	    	try {
	    		logger.debug(codeMasterId);
	    		//結果が正しい場合削除メソッド実行してresultに含める

		    	result = codeMasterService.deleteMasterCode(new CodeDetail(codeMasterId, null, null,appConstant.USE_CODE) );
	    	}catch (Exception e) {
	    		logger.error(e.getMessage());
			}
	        return result;
	    }
		/**
		 * もダル化
		 * @param codeMasterId マスターコード
		 * @return CodeDetail
		 */
	    @RequestMapping(value = "/infoAjax", method = RequestMethod.POST)
	    @ResponseBody
	    public List<CodeDetail> infoAjax(@RequestBody String codeMasterId) {
	    	List<CodeDetail>array = new ArrayList<CodeDetail>();
	    	try {
	    		logger.debug("++++++++++++++++++++++++++++++++++++"+codeMasterId);


	    		array = codeDetailService.selectCodeDetailList(new CodeDetail(codeMasterId, null, null,appConstant.USE_CODE) );
	    		logger.debug("++++++++++++++++++++++++++++++++++++"+array);
	    	}catch (Exception e) {
	    		logger.error(e.getMessage());
			}
	        return array;
	    }

}
