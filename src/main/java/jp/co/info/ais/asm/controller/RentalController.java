package jp.co.info.ais.asm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.info.ais.asm.common.Page;
import jp.co.info.ais.asm.domain.ProductKind;
import jp.co.info.ais.asm.domain.Rental;
import jp.co.info.ais.asm.service.ITAssetService;
import jp.co.info.ais.asm.service.RentalService;


@Controller

public class RentalController {


	private static final Logger logger = LogManager.getLogger(RentalController.class);

	@Autowired
	private ITAssetService ITAssetService;
	@Autowired
	private RentalService rentalService;


	 @RequestMapping(value = "/rental", method = RequestMethod.GET)
	    public String ITAssetList(Model model) {
	    	model.addAttribute("productCode", ITAssetService.selectProductCode());
	    	model.addAttribute("stateCode", ITAssetService.selectStateCode());
	        return "rentalIndex";
	    }

	    @RequestMapping("/getRentalList")
	    @ResponseBody
	    public Page<Rental> getuserlist(@RequestBody Page<Rental> page)throws Exception {
	    	Rental rental = new Rental();
	    	rental.setLength(page.getLength());
	    	rental.setStart(page.getStart());

	    	String assetNumber = page.getColumns().get(0).getSearch().getValue();
	    	if(null != assetNumber && !assetNumber.equals("")){
	    		logger.debug(page.getColumns().get(0).getSearch().getValue());
	    		rental.setAssetNumber(assetNumber);
	    	}

	    	int psNum = Integer.parseInt(page.getColumns().get(1).getSearch().getValue());
	    	rental.setPsNum(psNum);

	    	int sNumber = Integer.parseInt(page.getColumns().get(2).getSearch().getValue());
	    	rental.setSNumber(sNumber);

	    	String date = page.getColumns().get(3).getSearch().getValue();
	    	if(null != date && !date.equals("")){
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	    		logger.debug(page.getColumns().get(3).getSearch().getValue());
	    		try {
	    			rental.setRentDay(sdf.parse(date));
				} catch (ParseException e) {
					e.printStackTrace();
				}
	    	}

	        List<Rental> list = rentalService.select(rental);

	        page.setData(list);

	        int totalCount = rentalService.selectCount(rental);

	        page.setRecordsFiltered(totalCount);

	        return page;
	    }


	    @RequestMapping("/rentalBoardInsert") //게시글 작성폼 호출
		private String rentalBoardInsertForm(Model model) throws Exception {
			model.addAttribute("productKindCode", rentalService.searchCode());
		List<ProductKind> list = rentalService.searchCode();

			return "rentalInsert";
		}



	    @RequestMapping(value = "/select", method = RequestMethod.GET)
		public @ResponseBody List<Map<String, String>> getSelectData(
				@RequestParam(value = "pcNum", required = true) String pcNum) throws Exception {

			List<Map<String, String>> selectedMap = rentalService.getSelectData(pcNum);

			return selectedMap;
		}

		@RequestMapping(value = "/selectMini", method = RequestMethod.GET)
		public @ResponseBody List<Map<String, String>> getSelectProduct(
				@RequestParam(value = "psNum", required = true) String psNum) throws Exception {

			List<Map<String, String>> selectedMap = rentalService.getSelectProduct(psNum);

			return selectedMap;
		}

		@RequestMapping(value = "/selectWrite", method = RequestMethod.GET)
		public @ResponseBody Map<String, String> writeProduct(
				@RequestParam(value = "productCode", required = true) String realCode) throws Exception {

			Map<String, String> selectedMap = rentalService.writeProduct(realCode);

			return selectedMap;
		}


}
