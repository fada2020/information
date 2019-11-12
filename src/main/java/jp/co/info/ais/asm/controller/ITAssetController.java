package jp.co.info.ais.asm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.info.ais.asm.common.Page;
import jp.co.info.ais.asm.domain.Accessories;
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
    public Page<ITAsset> getITAssetlist(@RequestBody Page<ITAsset> page) {
    	ITAsset condition = new ITAsset();
    	condition.setLength(page.getLength());
    	condition.setStart(page.getStart());

    	String assetNumber = page.getColumns().get(0).getSearch().getValue();
    	if(null != assetNumber && !assetNumber.equals("")){
    		logger.debug(page.getColumns().get(0).getSearch().getValue());
    		condition.setAssetNumber(assetNumber);
    	}

    	int psNum = Integer.parseInt(page.getColumns().get(1).getSearch().getValue());
    	condition.setPsNum(psNum);

    	int sNumber = Integer.parseInt(page.getColumns().get(2).getSearch().getValue());
    	condition.setSNumber(sNumber);

    	String maker = page.getColumns().get(3).getSearch().getValue();
    	if(null != maker && !maker.equals("")){
    		logger.debug(page.getColumns().get(3).getSearch().getValue());
    		condition.setMaker(maker);
    	}

    	String model = page.getColumns().get(4).getSearch().getValue();
    	if(null != model && !model.equals("")){
    		logger.debug(page.getColumns().get(4).getSearch().getValue());
    		condition.setModel(model);
    	}

    	String date = page.getColumns().get(5).getSearch().getValue();
    	if(null != date && !date.equals("")){
    		date = date.replaceAll(" ", "");
    		String[] dateArr = date.split("-");
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    		logger.debug(page.getColumns().get(5).getSearch().getValue());
    		try {
    			condition.setStartPurchaseDate(sdf.parse(dateArr[0]+dateArr[1]+dateArr[2]));
    			condition.setEndPurchaseDate(sdf.parse(dateArr[3]+dateArr[4]+dateArr[5]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
    	}

        List<ITAsset> list = ITAssetService.selectList(condition);

        page.setData(list);

        int totalCount = ITAssetService.selectCount(condition);

        page.setRecordsFiltered(totalCount);

        return page;
    }

    @RequestMapping(value = "/{assetNumber}", method = RequestMethod.GET)
    public String ITAssetInfo(@PathVariable("assetNumber") String assetNumber, Model model) {
    	model.addAttribute("asset", ITAssetService.select(assetNumber));
    	model.addAttribute("productCode", ITAssetService.selectProductCode());
    	model.addAttribute("stateCode", ITAssetService.selectStateCode());
        return "ITAssetUpdate";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String ITAssetUpdate(Model model, ITAsset asset) {
    	logger.debug(asset.toString());
    	model.addAttribute("productCode", ITAssetService.selectProductCode());
    	model.addAttribute("stateCode", ITAssetService.selectStateCode());
    	model.addAttribute("asset", ITAssetService.select(asset.getAssetNumber()));
        return "ITAssetUpdate";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Accessories add(Model model,@RequestBody  Accessories accessories) {
    	logger.debug(accessories.toString());
        return accessories;
    }
}
