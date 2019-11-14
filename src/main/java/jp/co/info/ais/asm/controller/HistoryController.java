package jp.co.info.ais.asm.controller;

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

import jp.co.info.ais.asm.common.Page;
import jp.co.info.ais.asm.domain.History;
import jp.co.info.ais.asm.service.HistoryService;

@Controller
@RequestMapping("/history")
public class HistoryController {
	private static final Logger logger = LogManager.getLogger(HistoryController.class);

	@Autowired
	private HistoryService HistoryService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String ITAssetList(Model model) {
		model.addAttribute("stateCode", HistoryService.selectStateCode());
    	return "history";
    }

    @RequestMapping("/getHistorylist")
    @ResponseBody
    public Page<History> getHistorylist(@RequestBody Page<History> page) {
    	History condition = new History();
    	condition.setLength(page.getLength());
    	condition.setStart(page.getStart());

    	String assetNumber = page.getColumns().get(0).getSearch().getValue();
    	if(null != assetNumber && !assetNumber.equals("")){
    		assetNumber = "%"+assetNumber+"%";
    		condition.setAssetNumber(assetNumber);
    	}

    	String applicant = page.getColumns().get(1).getSearch().getValue();
    	if(null != applicant && !applicant.equals("")){
    		applicant = "%"+applicant+"%";
    		condition.setApplicant(applicant);
    	}

    	String statusCode = page.getColumns().get(2).getSearch().getValue();
    	if(statusCode.equals("000")){
    		condition.setStatusCode(statusCode);
    	}

    	String rentalDayS = page.getColumns().get(3).getSearch().getValue();
    	if(null != rentalDayS && !rentalDayS.equals("")) {
    		condition.setRentalDayS(rentalDayS);
    	}

    	String rentalDayE = page.getColumns().get(4).getSearch().getValue();
    	if(null != rentalDayE && !rentalDayE.equals("")) {
    		condition.setRentalDayE(rentalDayE);
    	}

    	String returnDayS = page.getColumns().get(5).getSearch().getValue();
    	if(null != returnDayS && !returnDayS.equals("")) {
    		condition.setReturnDayS(returnDayS);
    	}

    	String returnDayE = page.getColumns().get(6).getSearch().getValue();
    	if(null != returnDayE && !returnDayE.equals("")) {
    		condition.setReturnDayE(returnDayE);
    	}

        List<History> list = HistoryService.selectHistory(condition);

        page.setData(list);

        int totalCount = HistoryService.selectHistory(condition).size();

        page.setRecordsFiltered(totalCount);

        return page;
    }
}
