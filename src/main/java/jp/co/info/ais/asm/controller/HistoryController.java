package jp.co.info.ais.asm.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jp.co.info.ais.asm.common.Page;
import jp.co.info.ais.asm.domain.History;
import jp.co.info.ais.asm.modelAndView.HistoryXlsxView;
import jp.co.info.ais.asm.service.HistoryService;

@Controller
@RequestMapping("/history")
public class HistoryController {

	private static final Logger logger = LogManager.getLogger(HistoryController.class);

	@Autowired
	private HistoryService HistoryService;

	//状態コード値セッティング
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String History(Model model) {
		model.addAttribute("stateCode", HistoryService.selectStateCode());
    	return "history";
    }

    //エクセルファイル抽出
    @RequestMapping("/rentalHsitory.xlsx")
    public ModelAndView exportXlsx() {
    	List<History> history = HistoryService.exportXlsx();

        return new ModelAndView(new HistoryXlsxView(), "history", history);
    }

    //履歴情報削除
    @RequestMapping("/deleteHistory")
    @ResponseBody
    public int deleteHistory(@RequestBody ArrayList<String> deleteList ) {


    	int deletedNum = HistoryService.deleteHistory(deleteList);
    	return deletedNum;
    }

    //検索条件及び画面表示情報の作成
    @RequestMapping("/getHistorylist")
    @ResponseBody
    public Page<History> getHistorylist(@RequestBody Page<History> page) {
    	logger.debug(page.toString());

    	//検索条件生成
    	History condition = new History();
    	condition.setLength(page.getLength());
    	condition.setStart(page.getStart());

    	//検索条件設定-資産番号
    	String assetNumber = page.getColumns().get(0).getSearch().getValue();
    	condition.setAssetNumber(assetNumber);

    	//検索条件設定-貸出者
    	String applicantId = page.getColumns().get(1).getSearch().getValue();
    	condition.setApplicantId(applicantId);

    	//検索条件設定-状態コード
    	String statusCode = page.getColumns().get(2).getSearch().getValue();
    	condition.setStatusCode(statusCode);

    	//検索条件設定-貸与期間
    	String rentalPeriod = page.getColumns().get(3).getSearch().getValue();
    	if(null != rentalPeriod && !rentalPeriod.equals("")) {
    		rentalPeriod = rentalPeriod.replaceAll("[ /]", "");
    		String[] dateArr = rentalPeriod.split("-");
			condition.setRentalDayS(dateArr[0]);
			condition.setRentalDayE(dateArr[1]);
    	}

    	//検索条件設定-返却期間
    	String returnPeriod = page.getColumns().get(4).getSearch().getValue();
    	if(null != returnPeriod && !returnPeriod.equals("")) {
    		returnPeriod = returnPeriod.replaceAll("[ /]", "");
    		String[] dateArr = returnPeriod.split("-");
    		condition.setReturnDayS(dateArr[0]);
    		condition.setReturnDayE(dateArr[1]);
    	}

    	//検索及び画面情報取得
        List<History> list = HistoryService.selectHistory(condition);
        page.setData(list);
        int totalCount = HistoryService.selectCount(condition);
        page.setRecordsFiltered(totalCount);

        logger.debug("result ==="+page.toString());
        return page;
    }


}
