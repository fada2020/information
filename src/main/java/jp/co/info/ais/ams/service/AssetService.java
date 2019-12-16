package jp.co.info.ais.ams.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.info.ais.ams.common.AppConstant;
import jp.co.info.ais.ams.domain.Accessories;
import jp.co.info.ais.ams.domain.Asset;
import jp.co.info.ais.ams.domain.CodeDetail;
import jp.co.info.ais.ams.domain.MaintenanceHistory;
import jp.co.info.ais.ams.domain.Rental;
import jp.co.info.ais.ams.mapper.AssetMapper;
import jp.co.info.ais.ams.mapper.RentalMapper;

@Service
public class AssetService {

	private static final Logger logger = LogManager.getLogger(AssetService.class);

	@Autowired
	AppConstant appConstant;

	@Autowired
	AssetMapper assetMapper;

	@Autowired
	RentalMapper rentalMapper;

	/**
	 * 資産詳細情報
	 *
	 * @param int 資産シーケンス
	 * @return Asset  資産Object
	 */
	public Asset selectAsset(int assetSeq) {
		Asset asset = assetMapper.selectAsset(assetSeq);
		// その他改行
		String other = asset.getOther();
		if(null != other && !other.equals("")) {
			other = other.replaceAll("<br>", "\n");
			asset.setOther(other);
		}
		return asset;
	}

	/**
	 * 検索条件に応じた資産リスト
	 *
	 * @param Asset 検索条件
	 * @return List<Asset> 資産リスト
	 */
	public List<Asset> selectList(Asset condition) {
		// 日付検索前処理
		String date = condition.getPurchaseDate();
		if(null != date && !date.equals("")){
			// 空白及び'/'除去
			date = date.replaceAll("[ /]", "");
			// '-'基準切り
			String[] dateArr = date.split("-");
			condition.setStartPurchaseDate(dateArr[0]);
			condition.setEndPurchaseDate(dateArr[1]);
		}
		return assetMapper.selectAssetList(condition);
	}

	/**
	 * 検索条件に応じた資産数
	 *
	 * @param Asset 検索条件
	 * @return int 資産数
	 */
	public int selectCount(Asset condition) {
		return assetMapper.selectCount(condition);
	}

	/**
	 * 区分コード情報
	 *
	 * @return List<CodeDetail> 区分コード情報リスト
	 */
	public List<CodeDetail> selectProductCode() {

		return assetMapper.selectProductCode(new CodeDetail(appConstant.MASTER_DETAIL,null,null, appConstant.USE_CODE));
	}

	/**
	 * 状態コード情報
	 *
	 * @return List<CodeDetail> 状態コード情報リスト
	 */
	public List<CodeDetail> selectStateCode() {
		return assetMapper.selectStateCode(new CodeDetail(appConstant.MASTER_STATE,null,null, appConstant.USE_CODE));
	}

	/**
	 * 付属品情報登録
	 *
	 * @param Accessories 付属品情報
	 */
	public void insertAccessories(Accessories accessories) {
		assetMapper.insertAccessories(accessories);
	}

	/**
	 * 故障・廃棄履歴情報登録
	 *
	 * @param MaintenanceHistory 故障・廃棄履歴情報
	 */
	public void insertMaintenanceHistory(MaintenanceHistory maintenanceHistory) {
		assetMapper.insertMaintenanceHistory(maintenanceHistory);
	}

	/**
	 * 付属品情報修正
	 *
	 * @param Accessories 付属品情報
	 * @return int 結果値
	 */
	public int updateAccessories(Accessories accessories) {
		return assetMapper.updateAccessories(accessories);
	}

	/**
	 * 付属品情報削除
	 *
	 * @param Accessories 付属品情報
	 * @return int 結果値
	 */
	public int deleteAccessories(Accessories accessories) {
		return assetMapper.deleteAccessories(accessories);
	}

	/**
	 * 故障・廃棄履歴情報修正
	 *
	 * @param Accessories 故障・廃棄履歴情報
	 * @return int 結果値
	 */
	public int updateMaintenanceHistory(MaintenanceHistory maintenanceHistory) {
		return assetMapper.updateMaintenanceHistory(maintenanceHistory);
	}

	/**
	 * 故障・廃棄履歴情報削除
	 *
	 * @param Accessories 故障・廃棄履歴情報
	 * @return int 結果値
	 */
	public int deleteMaintenanceHistory(MaintenanceHistory maintenanceHistory) {
		return assetMapper.deleteMaintenanceHistory(maintenanceHistory);
	}

	/**
	 * 資産情報修正
	 *
	 * @param Asset 資産情報
	 * @return int 結果値
	 */
	@Transactional
	public int updateAsset(Asset asset) {
		if(!"".equals(asset.getPreStatusCode()) && !asset.getStatusCode().equals(asset.getPreStatusCode())) {
			Rental rental = new Rental();
			rental.setInsertId(asset.getInsertId());
			rental.setUpdateId(asset.getUpdateId());
			rental.setRentalUserId(asset.getInsertId());
			rental.setAssetSeq(asset.getAssetSeq());
			rental.setStatusCode(asset.getStatusCode());
			rental.setPurpose("");
			rental.setStorageLocation("");
			rental.setSpeciality("");
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);
			Date currentTime = new Date();
			String mTime = mSimpleDateFormat.format(currentTime);
			rental.setRentalDay(mTime);
			rental.setReturnDay(mTime);
			rental.setReturnPeriod(mTime);
			if(asset.getStatusCode().equals(AppConstant.STATE_RENTAL)) {
				List<Rental> itemList = new ArrayList<Rental>();
				itemList.add(rental);
				rentalMapper.addRental(itemList);
			}
			if(asset.getPreStatusCode().equals(AppConstant.STATE_RENTAL)) {
				rentalMapper.returnAsset(rental);
			}
		}
    	// 空白及び'-'除去
    	asset.setPurchaseDate(asset.getPurchaseDate().replaceAll("-", ""));
		// その他改行
		String other = asset.getOther();
		if(null != other && !other.equals("")) {
			other = other.replaceAll("\n", "<br>");
			asset.setOther(other);
		}
		return assetMapper.updateAsset(asset);
	}

	/**
	 * 資産情報登録
	 * 資産管理連番を作って資産情報を登録
	 * ex) AIS-SVR-001
	 *
	 * @param Asset 資産情報
	 */
	@Transactional
	public void insertAsset(Asset asset) {
		// 購入日'-'除去
		String date = asset.getPurchaseDate();
		if(null != date && !date.equals("")) {
			date = date.replaceAll("[-]", "");
			asset.setPurchaseDate(date);
		}
		// その他改行
		String other = asset.getOther();
		if(null != other && !other.equals("")) {
			other = other.replaceAll("\n", "<br>");
			asset.setOther(other);
		}
		// 製品コード照会
		List<CodeDetail> pdCodeList = assetMapper.selectProductCode(new CodeDetail(appConstant.MASTER_DETAIL,null,null, appConstant.USE_CODE));
		// マッピングのためのmap生成
		Map<String, String> pdCodeMap = new HashMap<String, String>();
		for(CodeDetail pdCode : pdCodeList) {
			pdCodeMap.put(pdCode.getCodeDetailId(), pdCode.getItem2());
		}

		// 資産Seq更新
		assetMapper.updateAssetSeq(asset.getKubunCode());
		// 資産管理番号生成
		String assetNumber = assetMapper.selectAssetNumber(appConstant.getCompanyCode(), asset.getKubunCode());
		asset.setAssetNumber(assetNumber);

		// 資産情報登録
		assetMapper.insertAsset(asset);
	}

	/**
	 * 資産情報削除
	 *
	 * @param int 資産シーケンス
	 * @return int 結果値
	 */
	public int deleteAsset(int assetSeq) {
		return assetMapper.deleteAsset(assetSeq);
	}

}
