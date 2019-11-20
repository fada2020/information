package jp.co.info.ais.asm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.asm.common.AppConstant;
import jp.co.info.ais.asm.domain.Accessories;
import jp.co.info.ais.asm.domain.Asset;
import jp.co.info.ais.asm.domain.CodeDetail;
import jp.co.info.ais.asm.domain.MaintenanceHistory;
import jp.co.info.ais.asm.mapper.AssetMapper;

@Service
public class AssetService {

	private static final Logger logger = LogManager.getLogger(AssetService.class);

	@Autowired
	AppConstant appConstant;

	@Autowired
	AssetMapper assetMapper;

	public Asset selectAsset(int assetSeq) {
		return assetMapper.selectAsset(assetSeq);
	}

	public List<Asset> selectList(Asset condition) {
		return assetMapper.selectAssetList(condition);
	}

	public int selectCount(Asset condition) {
		return assetMapper.selectCount(condition);
	}

	public List<CodeDetail> selectProductCode() {
		return assetMapper.selectProductCode();
	}

	public List<CodeDetail> selectStateCode() {
		return assetMapper.selectStateCode();
	}

	public void insertAccessories(Accessories accessories) {
		assetMapper.insertAccessories(accessories);
	}

	public void insertMaintenanceHistory(MaintenanceHistory maintenanceHistory) {
		assetMapper.insertMaintenanceHistory(maintenanceHistory);
	}

	public int updateAccessories(Accessories accessories) {
		return assetMapper.updateAccessories(accessories);
	}

	public int deleteAccessories(Accessories accessories) {
		return assetMapper.deleteAccessories(accessories);
	}

	public int updateMaintenanceHistory(MaintenanceHistory maintenanceHistory) {
		return assetMapper.updateMaintenanceHistory(maintenanceHistory);
	}

	public int updateAsset(Asset asset) {
		return assetMapper.updateAsset(asset);
	}

	public void insertAsset(Asset asset) {
		// 購入日'-'除去
		String date = asset.getPurchaseDate();
		if(null != date && !date.equals("")) {
			date = date.replaceAll("[-]", "");
			asset.setPurchaseDate(date);
		}
		// 製品コード照会
		List<CodeDetail> pdCodeList = assetMapper.selectProductCode();
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

	public int deleteAsset(int assetSeq) {
		return assetMapper.deleteAsset(assetSeq);
	}

	public int deleteMaintenanceHistory(MaintenanceHistory maintenanceHistory) {
		return assetMapper.deleteMaintenanceHistory(maintenanceHistory);
	}

}
