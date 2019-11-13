package jp.co.info.ais.asm.controller;

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
import jp.co.info.ais.asm.domain.Asset;
import jp.co.info.ais.asm.service.AssetService;

@Controller
@RequestMapping("/asset")
public class AssetController {
	private static final Logger logger = LogManager.getLogger(AssetController.class);

	@Autowired
	private AssetService assetService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String ITAssetList(Model model) {
    	model.addAttribute("productCode", assetService.selectProductCode());
    	model.addAttribute("stateCode", assetService.selectStateCode());
        return "asset";
    }

    @RequestMapping("/getAssetlist")
    @ResponseBody
    public Page<Asset> getITAssetlist(@RequestBody Page<Asset> page) {
    	Asset condition = new Asset();
    	condition.setLength(page.getLength());
    	condition.setStart(page.getStart());

    	String assetNumber = page.getColumns().get(0).getSearch().getValue();
    	if(null != assetNumber && !assetNumber.equals("")){
    		logger.debug(page.getColumns().get(0).getSearch().getValue());
    		condition.setAssetNumber(assetNumber);
    	}

    	String kubunCode = page.getColumns().get(1).getSearch().getValue();
    	condition.setKubunCode(kubunCode);

    	String statusCode = page.getColumns().get(2).getSearch().getValue();
    	condition.setStatusCode(statusCode);

    	String makerName = page.getColumns().get(3).getSearch().getValue();
    	if(null != makerName && !makerName.equals("")){
    		logger.debug(page.getColumns().get(3).getSearch().getValue());
    		condition.setMakerName(makerName);
    	}

    	String modelName = page.getColumns().get(4).getSearch().getValue();
    	if(null != modelName && !modelName.equals("")){
    		logger.debug(page.getColumns().get(4).getSearch().getValue());
    		condition.setModelName(modelName);
    	}

    	String date = page.getColumns().get(5).getSearch().getValue();
    	if(null != date && !date.equals("")){
    		date = date.replaceAll("[ /]", "");
    		String[] dateArr = date.split("-");
			condition.setStartPurchaseDate(dateArr[0]);
			condition.setEndPurchaseDate(dateArr[1]);
    	}

        List<Asset> list = assetService.selectList(condition);

        page.setData(list);

        int totalCount = assetService.selectCount(condition);

        page.setRecordsFiltered(totalCount);

        return page;
    }

    @RequestMapping(value = "/{assetNumber}", method = RequestMethod.GET)
    public String ITAssetInfo(@PathVariable("assetNumber") String assetNumber, Model model) {
    	model.addAttribute("asset", assetService.select(assetNumber));
    	model.addAttribute("productCode", assetService.selectProductCode());
    	model.addAttribute("stateCode", assetService.selectStateCode());
        return "assetUpdate";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String ITAssetUpdate(Model model, Asset asset) {
    	logger.debug(asset.toString());
    	model.addAttribute("productCode", assetService.selectProductCode());
    	model.addAttribute("stateCode", assetService.selectStateCode());
    	model.addAttribute("asset", assetService.select(asset.getAssetNumber()));
        return "assetUpdate";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Accessories add(Model model,@RequestBody  Accessories accessories) {
    	logger.debug(accessories.toString());
        return accessories;
    }
}
