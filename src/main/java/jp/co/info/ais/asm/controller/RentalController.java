package jp.co.info.ais.asm.controller;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.info.ais.asm.common.Page;
import jp.co.info.ais.asm.domain.Rental;
import jp.co.info.ais.asm.service.AssetService;
import jp.co.info.ais.asm.service.RentalService;

@Controller
@RequestMapping("/rental")
public class RentalController {

	private static final Logger logger = LogManager.getLogger(RentalController.class);

	//資産管理
	@Autowired
	private AssetService ITAssetService;

	@Autowired
	private RentalService rentalService;
	//1必須チェック
	//2.ヌルチェック
	//3.習得チェック
	//4.リターンチェック

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String ITAssetList(Model model) throws Exception {
		//区分データ習得
		model.addAttribute("kubunCode", rentalService.selectCodeDetail());
		//ステータースデータ習得
		model.addAttribute("statusCode", rentalService.selectStatusCode());

		//戻り値 区分データ,ステータースデータ
		return "rentalIndex";
	}

	@RequestMapping("/getRentalList")
	@ResponseBody
	public Page<Rental> getuserlist(@RequestBody Page<Rental> page) throws Exception {
		Rental rental = new Rental();
		rental.setLength(page.getLength());
		rental.setStart(page.getStart());

		String assetNumber = page.getColumns().get(0).getSearch().getValue();
		if (null != assetNumber && !assetNumber.equals("")) {
			logger.debug(page.getColumns().get(0).getSearch().getValue());
			rental.setAssetNumber(assetNumber);
		}

		String date = page.getColumns().get(1).getSearch().getValue();
		if (null != date && !date.equals("")) {

			logger.debug(page.getColumns().get(1).getSearch().getValue());

				rental.setRentalDay(date);



		}

		List<Rental> list = rentalService.selectAll(rental);
		for(Rental r : list) {
			String rentalDay=r.getRentalDay();
			String returnPeriod=r.getReturnPeriod();
			rentalDay = rentalDay.substring(0, 4) + "/" +rentalDay.substring(4, 6)+"/"+ rentalDay.substring(6, rentalDay.length());
			returnPeriod = returnPeriod.substring(0, 4) + "/" +returnPeriod.substring(4, 6)+"/"+ returnPeriod.substring(6, returnPeriod.length());
			r.setRentalDay(rentalDay);
			r.setReturnPeriod(returnPeriod);
		}
		page.setData(list);

		int totalCount = rentalService.selectCount(rental);

		page.setRecordsFiltered(totalCount);

		return page;
	}

	@RequestMapping("/rentalBoardInsert") //게시글 작성폼 호출
	private String rentalBoardInsertForm(Model model) throws Exception {


		return "rentalInsert";
	}

	@RequestMapping(value = "/selectFirst", method = RequestMethod.GET)
	public @ResponseBody List<Rental> getSelectData(
			@RequestParam(value = "pcNum", required = true) int pcNum) throws Exception {

		List<Rental> selectedMap = rentalService.getSelectData(pcNum);

		return selectedMap;
	}

	@RequestMapping(value = "/selectSecond", method = RequestMethod.GET)
	public @ResponseBody List<Rental> getSelectProduct(
			@RequestParam(value = "psNum", required = true) int psNum) throws Exception {
		String p = Integer.toString(psNum);
		System.out.println(p);
		System.out.println(psNum);
		List<Rental> selectedMap = rentalService.getSelectProduct(p);

		return selectedMap;
	}

	@RequestMapping(value = "/selectWrite", method = RequestMethod.GET)
	public @ResponseBody Map<String, String> writeProduct(
			@RequestParam(value = "assetNumber", required = true) String realCode) throws Exception {

		Map<String, String> selectedMap = rentalService.writeProduct(realCode);

		return selectedMap;
	}

	@RequestMapping(value = "/{assetNumber}", method = RequestMethod.GET)
	public String ITAssetInfo(@PathVariable("assetNumber") String assetNumber, Model model) throws Exception {
		model.addAttribute("asset", rentalService.researchRental(assetNumber));
		model.addAttribute("kubunCode", rentalService.selectCodeDetail());
		model.addAttribute("stateCode", rentalService.selectStatusCode());
		return "rentalIndex";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	private int rentalUpdate(Model model) {

		int suc = 0;

		return suc;
	}
}
