package jp.co.info.ais.asm.controller;

import java.util.List;

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
@RequestMapping("/history")
public class HistoryController {

	@Autowired
	private ITAssetService ITAssetService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String ITAssetList(Model model) {
    	model.addAttribute("productCode", ITAssetService.selectProductCode());
    	model.addAttribute("stateCode", ITAssetService.selectStateCode());
        return "history";
    }

    @RequestMapping("/getITAssetlist")
    @ResponseBody
    public Page<ITAsset> getITAssetlist(@RequestBody Page<ITAsset> page) {
    	ITAsset condition = new ITAsset();
    	condition.setLength(page.getLength());
    	condition.setStart(page.getStart());

    	String assetNumber = page.getColumns().get(0).getSearch().getValue();
    	if(null != assetNumber && !assetNumber.equals("")){
    		condition.setAssetNumber(assetNumber);
    	}

    	int psNum = Integer.parseInt(page.getColumns().get(1).getSearch().getValue());
    	condition.setPsNum(psNum);

    	int sNumber = Integer.parseInt(page.getColumns().get(2).getSearch().getValue());
    	condition.setSNumber(sNumber);

    	String maker = page.getColumns().get(3).getSearch().getValue();
    	if(null != maker && !maker.equals("")){
    		condition.setMaker(maker);
    	}

    	String model = page.getColumns().get(4).getSearch().getValue();
    	if(null != model && !model.equals("")){
    		condition.setModel(model);
    	}

		/*    	String date = page.getColumns().get(5).getSearch().getValue();
		    	if(null != date && !date.equals("")){
		    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		    		try {
		    			condition.setPurchaseDate(sdf.parse(date));
					} catch (ParseException e) {
						e.printStackTrace();
					}
		    	}*/

        List<ITAsset> list = ITAssetService.selectList(condition);

        page.setData(list);

        int totalCount = ITAssetService.selectCount(condition);

        page.setRecordsFiltered(totalCount);

        return page;
    }
}
