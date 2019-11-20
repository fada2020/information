package jp.co.info.ais.asm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

import jp.co.info.ais.asm.common.Page;
import jp.co.info.ais.asm.domain.Asset;
import jp.co.info.ais.asm.domain.Rental;
import jp.co.info.ais.asm.service.RentalService;

@Controller
@RequestMapping("/rental")
public class RentalController {

	private static final Logger logger = LogManager.getLogger(RentalController.class);
	@Autowired
	HttpSession session;
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
		//区分コードの習得
		model.addAttribute("kubun", rentalService.selectCode());

		Date date = new Date();
		model.addAttribute("date", date);
		//戻り値 区分データ,ステータースデータ
		return "rentalIndex";
	}

	@RequestMapping(value = "/getAsset", method = RequestMethod.POST)
	@ResponseBody
	public Asset getAsset(Model model, @RequestBody String number) throws Exception {

		//戻り値はAssetで引数はasset_numberを使う
		Asset asset = rentalService.selectAsset(number);
		//もし
		if (asset != null) {
			rentalService.changeAssetStatus(number);
			model.addAttribute("asset", asset);
		}

		return asset;
	}

	@RequestMapping(value = "/addRental", method = RequestMethod.POST)
	@ResponseBody
	public void addRental(Model model, @RequestBody List<Rental> rentalList) {

		try {
			rentalService.addRental(rentalList);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/cancelAsset", method = RequestMethod.POST)
	@ResponseBody
	public void cancelAsset(@RequestBody int assetSeq) throws Exception {
		rentalService.changeAStatus(assetSeq);

	}

	@RequestMapping(value = "/getAssetList", method = RequestMethod.POST)
	@ResponseBody
	public List<Asset> selectAssetList(@RequestBody String selectedItem) throws Exception {
		selectedItem = selectedItem.replaceAll("[^0-9]", "");
		List<Asset> assetList = rentalService.selectAssetList(selectedItem);
		logger.debug(selectedItem);

		logger.debug(rentalService.selectAssetList(selectedItem).toString());

		return assetList;
	}

	@RequestMapping("/getRentalList")
	@ResponseBody
	public Page<Rental> getuserlist(@RequestBody Page<Rental> page) throws Exception {
		Rental rental = new Rental();
		rental.setLength(page.getLength());
		rental.setStart(page.getStart());

		String assetNumber = page.getColumns().get(0).getSearch().getValue();
		if (null != assetNumber && !assetNumber.equals("")) {
			assetNumber = "%" + assetNumber + "%";
			rental.setAssetNumber(assetNumber);
		}
		String rentalPeriod = page.getColumns().get(1).getSearch().getValue();
		if (null != rentalPeriod && !rentalPeriod.equals("")) {
			rentalPeriod = rentalPeriod.replaceAll("[ /]", "");
			String[] dateArr = rentalPeriod.split("-");
			rental.setRentalDayS(dateArr[0]);
			rental.setRentalDayE(dateArr[1]);
		}

		List<Rental> list = rentalService.selectAll(rental);

		page.setData(list);

		int totalCount = rentalService.selectCount(rental);

		page.setRecordsFiltered(totalCount);

		return page;
	}

	@RequestMapping(value = "/returnAsset", method = RequestMethod.POST)
	public String deleteRental(Model model, @RequestBody String seq) {
		Rental rental = new Rental();
		logger.debug(seq);
		int num1 = Integer.parseInt(seq);
		rental.setAssetSeq(num1);
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);
		Date currentTime = new Date();
		String mTime = mSimpleDateFormat.format(currentTime);
		rental.setReturnDay(mTime);

		String applicantId = (String) session.getAttribute("id");
		rental.setApplicantId(applicantId);
		int num = 0;
		try {
			num = rentalService.returnAsset(rental);
			if (num > 0) {
				rentalService.changeAStatus(num1);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//区分データ習得
				model.addAttribute("kubunCode", rentalService.selectCodeDetail());
				//ステータースデータ習得
				model.addAttribute("statusCode", rentalService.selectStatusCode());
				//区分コードの習得
				model.addAttribute("kubun", rentalService.selectCode());

				Date date = new Date();

				model.addAttribute("date", date);
				//戻り値 区分データ,ステータースデータ
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "rentalIndex";
	}

	@RequestMapping(value = "/researchRental", method = RequestMethod.POST)
	@ResponseBody
	private Rental researchRental(Model model, @RequestBody int assetSeq) {
		Rental rental = new Rental();
		try {
			logger.debug(assetSeq);
			rental = rentalService.researchRental(assetSeq);
		} catch (Exception e) {
			e.printStackTrace();

		}

		return rental;
	}

	@RequestMapping(value = "/updateRental", method = RequestMethod.POST)
	@ResponseBody
	private String updateAsset(Model model, @RequestBody Rental rental) {

		try {
			logger.debug(rental.toString());
			rental.setUpdateId((String) session.getAttribute("id"));

			int num = rentalService.updateRental(rental);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "rentalIndex";
	}
}
