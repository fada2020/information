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

		session.setAttribute("id", "ais111111");

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

		Asset asset = rentalService.selectAsset(number);
		model.addAttribute("asset", asset);
		return asset;
	}

	@RequestMapping(value = "/addRental", method = RequestMethod.POST)
	@ResponseBody
	public void addRental(Model model, @RequestBody List<Rental> rentalList) throws Exception {
		for (Rental r : rentalList) {
			logger.debug(r.toString());
		}
		int num2 = 0;
		int num = rentalService.addRental(rentalList);
		if (num == 1) {

			num2 = rentalService.changeStatus(rentalList);

		}
		if (num2 > 0) {
			model.addAttribute("result", "成功");
		} else {
			model.addAttribute("result", "失敗");
		}
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
			logger.debug(page.getColumns().get(0).getSearch().getValue());
			rental.setAssetNumber(assetNumber);
		}

		String date = page.getColumns().get(1).getSearch().getValue();
		if (null != date && !date.equals("")) {

			logger.debug(page.getColumns().get(1).getSearch().getValue());

			rental.setRentalDay(date);

		}

		List<Rental> list = rentalService.selectAll(rental);
		for (Rental r : list) {
			String rentalDay = r.getRentalDay();
			String returnPeriod = r.getReturnPeriod();
			rentalDay = rentalDay.substring(0, 4) + "/" + rentalDay.substring(4, 6) + "/"
					+ rentalDay.substring(6, rentalDay.length());
			returnPeriod = returnPeriod.substring(0, 4) + "/" + returnPeriod.substring(4, 6) + "/"
					+ returnPeriod.substring(6, returnPeriod.length());
			r.setRentalDay(rentalDay);
			r.setReturnPeriod(returnPeriod);
		}
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
				rentalService.changeAssetStatus(num1);

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
		return "";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	private int rentalUpdate(Model model) {

		int suc = 0;

		return suc;
	}
}
