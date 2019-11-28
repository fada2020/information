package jp.co.info.ais.ams.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import jp.co.info.ais.ams.common.Page;
import jp.co.info.ais.ams.domain.Asset;
import jp.co.info.ais.ams.domain.Rental;
import jp.co.info.ais.ams.service.RentalService;

@Controller
@RequestMapping("/rental")
public class RentalController {

	private static final Logger logger = LogManager.getLogger(RentalController.class);

	@Autowired
	HttpSession session;

	@Autowired
	private RentalService rentalService;

	/**
	 * 貸与管理ルトー
	 *
	 * @param Model
	 * @return 住所の"rentalIndex"
	 * */

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String ITAssetList(Model model) {
		try {
			//区分データ習得
			model.addAttribute("kubunCode", rentalService.selectCodeDetail());
			//ステータースデータ習得
			model.addAttribute("statusCode", rentalService.selectStatusCode());
			//区分コードの習得
			model.addAttribute("kubun", rentalService.selectCode());

			Date date = new Date();
			model.addAttribute("date", date);
		} catch (Exception e) {

			logger.debug(e.getMessage());
		}
		//戻り値 区分データ,ステータースデータ
		return "rentalIndex";
	}

	/**
	 * 貸与管理単一の資産の情報を持ち出す
	 * @param String assetNumber
	 * @return Asset 資産の情報
	 * */

	@RequestMapping(value = "/getAsset", method = RequestMethod.POST)
	@ResponseBody
	public Asset getAsset(Model model, @RequestBody String number) {
		Asset asset = new Asset();
		try {
			//戻り値はAssetで引数はasset_numberを使う
			asset = rentalService.selectAsset(number);
			//もし
			if (asset != null) {
				rentalService.changeAssetStatus(number);
				model.addAttribute("asset", asset);
			}
		} catch (Exception e) {

			logger.debug(e.getMessage());
		}

		return asset;
	}

	/**
	 * 貸与管理単一の資産をデータテーブルに入れ込む
	 * @param  List<Rental> 貸与情報のリスト
	 *
	 * */
	@RequestMapping(value = "/addRental", method = RequestMethod.POST)
	@ResponseBody
	public void addRental(Model model, @RequestBody List<Rental> rentalList) {

		try {
			rentalService.addRental(rentalList);

		} catch (Exception e) {

			logger.debug(e.getMessage());
		}
	}

	/**
	 * 任意の貸与リストの資産の状態を002から001に変える
	 * @param  int assetSeq 資産シークエンス
	 * @return int 戻り値
	 * */
	@RequestMapping(value = "/cancelAsset", method = RequestMethod.POST)
	@ResponseBody
	public int cancelAsset(@RequestBody int assetSeq) {
		int num=0;
		try {
			rentalService.changeAStatus(assetSeq);
		} catch (Exception e) {

			logger.debug(e.getMessage());
		}
return num;
	}

	/**
	 * 貸与メイン画面表示
	 *資産のデータの状態が001と引数に該当するデータを持ち出す
	 * @param   String 区分コード
	 * @return List<Asset> 資産情報のリスト
	 * */
	@RequestMapping(value = "/getAssetList", method = RequestMethod.POST)
	@ResponseBody
	public List<Asset> selectAssetList(@RequestBody String selectedItem) {
		List<Asset> assetList = new ArrayList<Asset>();
		try {
			selectedItem = selectedItem.replaceAll("[^0-9]", "");
			assetList = rentalService.selectAssetList(selectedItem);
		} catch (Exception e) {

			logger.debug(e.getMessage());
		}
		return assetList;
	}

	/**
	 * 貸与メイン画面表示
	 * 貸与リストのデータの状態が002に該当するデータを持ち出す
	 * ページングする為の処理もする
	 * 貸与データを十個ずつ表示する
	 * 検索条件のString assetNumberやString rentalPeriodも入っている
	 * @param  Page<Rental> page
	 * @returnPage<Rental> page
	 * */
	@RequestMapping("/getRentalList")
	@ResponseBody
	public Page<Rental> getuserlist(@RequestBody Page<Rental> page) {
		try {
			Rental rental = new Rental();
			rental.setLength(page.getLength());
			rental.setStart(page.getStart());
			//もし資産番号を検索したらrentalのオブジェクトに入れて該当するデータを持ち出す
			String assetNumber = page.getColumns().get(0).getSearch().getValue();
			if (null != assetNumber && !assetNumber.equals("")) {
				rental.setAssetNumber(assetNumber);
			}
			//もし貸与期間を検索したらrentalのオブジェクトに入れて該当するデータを持ち出す
			String rentalPeriod = page.getColumns().get(1).getSearch().getValue();
			if (null != rentalPeriod && !rentalPeriod.equals("")) {
				//rentalPeriodに数字以外の文字が入っていると除去する
				rentalPeriod = rentalPeriod.replaceAll("[ /]", "");
				String[] dateArr = rentalPeriod.split("-");
				rental.setRentalDayS(dateArr[0]);
				rental.setRentalDayE(dateArr[1]);
			}
			String rentalNo = page.getColumns().get(2).getSearch().getValue();
			if (null != rentalNo && !rentalNo.equals("")) {
				rental.setRentalNo(rentalNo);
			}
			List<Rental> list = rentalService.selectAll(rental);

			page.setData(list);

			int totalCount = rentalService.selectCount(rental);

			page.setRecordsFiltered(totalCount);
		} catch (Exception e) {

			logger.debug(e.getMessage());
		}

		return page;
	}

	/**
	 * 貸与した資産を返却する
	 * htmlから assetSeqを持ち込んで該当する貸与データの状態を002から001に変える
	 * @param  int 資産シークエンス
	 * @return 無し
	 * */
	@RequestMapping(value = "/returnAsset", method = RequestMethod.POST)
	public void deleteRental(Model model, @RequestBody int assetSeq) {

		try {
			rentalService.returnAsset((String) session.getAttribute("id"), (String) session.getAttribute("name"), assetSeq);
		} catch (Exception e) {

			logger.debug(e.getMessage());
		}

	}

	/**
	 * 貸与画面で変更したい貸与情報があったら変更する為探す
	 *貸与データから変更したい情報を資産情報を元にして状態が002になっている貸与情報を持ち出す
	 * @param  int 資産シークエンス
	 * @return Rental 貸与情報
	 * */
	@RequestMapping(value = "/researchRental", method = RequestMethod.POST)
	@ResponseBody
	private Rental researchRental(Model model, @RequestBody int assetSeq) {
		Rental rental = new Rental();
		try {

			rental = rentalService.researchRental(assetSeq);
		} catch (Exception e) {

			logger.debug(e.getMessage());
		}

		return rental;
	}

	/**
	 * 貸与画面で変更する情報を持ち込む
	 *htmlから変更する全ての情報を持ち込んで貸与データに入れ替える
	 * @param  int 資産シークエンス
	 * @return Rental 貸与情報
	 * */
	@RequestMapping(value = "/updateRental", method = RequestMethod.POST)
	@ResponseBody
	private int updateAsset(Model model, @RequestBody Rental rental) {
		int successNum = 0;
		try {

			successNum = rentalService.updateRental(rental, (String) session.getAttribute("id"));

		} catch (Exception e) {

			logger.debug(e.getMessage());
		}
		return successNum;
	}



}
