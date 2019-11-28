package jp.co.info.ais.ams.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.info.ais.ams.common.AppConstant;
import jp.co.info.ais.ams.controller.RentalController;
import jp.co.info.ais.ams.domain.Asset;
import jp.co.info.ais.ams.domain.CodeDetail;
import jp.co.info.ais.ams.domain.Rental;
import jp.co.info.ais.ams.mapper.RentalMapper;

@Service
public class RentalService {

	@Autowired
	AppConstant appConstant;
	@Autowired
	HttpSession session;
	@Autowired
	RentalMapper rentalMapper;
	/*エラーを見せる為の宣言*/
	private static final Logger logger = LogManager.getLogger(RentalController.class);

	/**
	 * 全ての貸与情報を持ち出す
	 *
	 * @param Rental
	 * @return List<Rental>
	 */
	public List<Rental> selectAll(Rental rental) {

		return rentalMapper.selectAll(rental);
	}

	/**
	 * 全ての貸与情報が何個か数える
	 *
	 * @param Rental
	 * @return int
	 */
	public int selectCount(Rental rental) {

		return rentalMapper.selectCount(rental);
	}

	/**
	 * メイン画面で選択してもらったコードネームを通して該当するコード詳細の情報を持ち出す
	 *
	 * @param String
	 * @return  List<CodeDetail>
	 */
	public List<CodeDetail> getSelectCodeData(String codeDetailName) {

		return rentalMapper.getSelectCodeData(codeDetailName);
	}

	/**
	 * 単一の貸与情報を持ち出す
	 *
	 * @param int
	 * @return Rental
	 */
	public Rental researchRental(int assetSeq) {

		return rentalMapper.researchRental(assetSeq);
	}

	/**
	 *メインページに見せるコードの詳細情報を持ち出す
	 *

	 * @return List<CodeDetail>
	 */
	public List<CodeDetail> selectCodeDetail() {
		String codeMasterId = AppConstant.MASTER_DETAIL;
		return rentalMapper.selectCodeDetail(codeMasterId);
	}

	/**
	 *メインページに見せるステータスコードの詳細情報を持ち出す
	 *
	 * @param
	 * @return List<CodeDetail>
	 */
	public List<CodeDetail> selectStatusCode() {

		return rentalMapper.selectStatusCode();
	}

	/**
	 *メインページに見せる詳細情報を持ち出す
	 *
	 * @param
	 * @return List<CodeDetail>
	 */
	public List<CodeDetail> selectCode() {
		String codeMasterId = AppConstant.MASTER_CLASS;
		return rentalMapper.selectCode(codeMasterId);
	}

	/**
	 *最後に選択してもらった資産情報を持ち出す
	 *
	 * @param String
	 * @return Asset
	 */
	public Asset selectAsset(String number) {


		return rentalMapper.selectAsset(new Rental(number,AppConstant.STATE_STORAGE,(String)session.getAttribute("id")));
	}

	/**
	 *選択してもらったコードを基にして該当する資産の情報を持ち出す
	 *
	 * @param String
	 * @return List<Asset>
	 */
	public List<Asset> selectAssetList(String selectedItem) {
		Asset asset = new Asset();
		asset.setKubunCode(selectedItem);
		asset.setStatusCode(AppConstant.STATE_STORAGE);
		return rentalMapper.selectAssetList(asset);
	}

	/**
	 *リスト上にあるデータを持ち込んで貸与データテーブルに書き込む
	 *
	 * @param  List<Rental>
	 * @return int
	 */
	@Transactional
	public int addRental(List<Rental> rentalList) {

		for (Rental r : rentalList) {
			r.setStatusCode(AppConstant.STATE_RENTAL);

		}
		return rentalMapper.addRental(rentalList);

	}

	/**
	 *リスト上にあるデータを持ち込んで資産データテーブルのステータスを変える
	 *
	 * @param  List<Rental>
	 * @return int
	 */
	@Transactional
	public int changeStatus(List<Rental> rentalList) {

		return rentalMapper.changeStatus(rentalList);

	}

	/**
	 *返却する為の情報を持ち込んで貸与データテーブルと資産データテーブルの情報を変える
	 *
	 * @param Rental
	 * @param String
	 * @param int
	 */
	@Transactional
	public void returnAsset(String updateId, String returnUserId, int assetSeq) {
		/*
		 * 返却する際にレンタルのオブジェクトに返却者等をセッティングする
		 * */

		try {
			Rental rental = new Rental();
			rental.setAssetSeq(assetSeq);
			rental.setUpdateId(updateId);
			rental.setReturnUserId(returnUserId);
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);
			Date currentTime = new Date();
			String mTime = mSimpleDateFormat.format(currentTime);
			rental.setReturnDay(mTime);
			int num = 0;
			num = rentalMapper.returnAsset(rental);
			//ちゃんと返却が出来たら資産の状態も変更する
			if (num > 0) {
				rental.setStatusCode(AppConstant.STATE_STORAGE);
				rentalMapper.changeAStatus(rental);

			}
		} catch (Exception e) {

			logger.debug(e.getMessage());
		}

	}

	/**
	 *資産ナンバーを基にして資産のステータスを変える
	 *
	 * @param String 資産ナンバー
	 *  @return int 戻り値
	 */
	@Transactional
	public int changeAssetStatus(String assetNumber) {

		return rentalMapper.changeAssetStatus(new Rental(assetNumber,AppConstant.STATE_RENTAL,(String)session.getAttribute("id")));
	}

	/**
	 *該当する資産のステータスの情報を変える
	 * @param updateId
	 *
	 * @param int 資産シークエンス
	 *  @return int 戻り値
	 */
	@Transactional
	public void changeAStatus(int assetSeq, String updateId) {

		try {
			rentalMapper.changeAStatus(new Rental(assetSeq, AppConstant.STATE_STORAGE, updateId));
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
	}

	/**
	 *アップデートする為のメソッド
	 *
	 * @param Rental 貸与情報
	 * @param String アップデートアカウント
	 *  @return int 戻り値
	 */
	@Transactional
	public int updateRental(Rental rental, String updateId) {
		try {

			rental.setUpdateId(updateId);

		} catch (Exception e) {

			logger.debug(e.getMessage());
		}
		return rentalMapper.updateRental(rental);
	}

}
