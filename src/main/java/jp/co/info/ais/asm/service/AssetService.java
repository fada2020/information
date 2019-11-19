package jp.co.info.ais.asm.service;

import java.util.List;

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
		logger.debug(appConstant.getCompanyName());
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

	public int updateMaintenanceHistory(MaintenanceHistory maintenanceHistory) {
		return assetMapper.updateMaintenanceHistory(maintenanceHistory);
	}

	public int updateAsset(Asset asset) {
		return assetMapper.updateAsset(asset);
	}

}
