package jp.co.info.ais.asm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import jp.co.info.ais.asm.domain.ITAsset;
import jp.co.info.ais.asm.service.ITAssetService;

@Controller
@RequestMapping("/ITAsset")
public class ITAssetController {
	private static final Logger logger = LogManager.getLogger(ITAssetController.class);

	@Autowired
	private ITAssetService ITAssetService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String ITAssetList(Model model) {
    	model.addAttribute("productCode", ITAssetService.selectProductCode());
    	model.addAttribute("stateCode", ITAssetService.selectStateCode());
        return "ITAsset";
    }

    @RequestMapping("/getITAssetlist")
    @ResponseBody
    public Page<ITAsset> getuserlist(@RequestBody Page<ITAsset> page) {
    	ITAsset ITAsset = new ITAsset();
    	ITAsset.setLength(page.getLength());
    	ITAsset.setStart(page.getStart());

    	String assetNumber = page.getColumns().get(0).getSearch().getValue();
    	if(null != assetNumber && !assetNumber.equals("")){
    		logger.debug(page.getColumns().get(0).getSearch().getValue());
    		ITAsset.setAssetNumber(assetNumber);
    	}

    	int psNum = Integer.parseInt(page.getColumns().get(1).getSearch().getValue());
    	ITAsset.setPsNum(psNum);

    	int sNumber = Integer.parseInt(page.getColumns().get(2).getSearch().getValue());
    	ITAsset.setSNumber(sNumber);

    	String maker = page.getColumns().get(3).getSearch().getValue();
    	if(null != maker && !maker.equals("")){
    		logger.debug(page.getColumns().get(3).getSearch().getValue());
    		ITAsset.setMaker(maker);
    	}

    	String model = page.getColumns().get(4).getSearch().getValue();
    	if(null != model && !model.equals("")){
    		logger.debug(page.getColumns().get(4).getSearch().getValue());
    		ITAsset.setModel(model);
    	}

    	String date = page.getColumns().get(5).getSearch().getValue();
    	if(null != date && !date.equals("")){
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    		logger.debug(page.getColumns().get(5).getSearch().getValue());
    		try {
				ITAsset.setPurchaseDate(sdf.parse(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
    	}

        List<ITAsset> list = ITAssetService.select(ITAsset);

        page.setData(list);

        int totalCount = ITAssetService.selectCount(ITAsset);

        page.setRecordsFiltered(totalCount);

        return page;
    }
}
