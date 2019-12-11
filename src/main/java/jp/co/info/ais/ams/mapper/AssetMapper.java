package jp.co.info.ais.ams.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.info.ais.ams.domain.Accessories;
import jp.co.info.ais.ams.domain.Asset;
import jp.co.info.ais.ams.domain.CodeDetail;
import jp.co.info.ais.ams.domain.MaintenanceHistory;

@Mapper
public interface AssetMapper {
	Asset selectAsset(int assetSeq);

	List<Asset> selectAssetList(Asset asset);

	int selectCount(Asset asset);

	List<CodeDetail> selectProductCode(CodeDetail codeDetail);

	List<CodeDetail> selectStateCode(CodeDetail codeDetail);

	int updateAsset(Asset asset);

	void insertAccessories(Accessories accessories);

	void insertMaintenanceHistory(MaintenanceHistory maintenanceHistory);

	int updateAccessories(Accessories accessories);

	int deleteAccessories(Accessories accessories);

	int updateMaintenanceHistory(MaintenanceHistory maintenanceHistory);

	void insertAsset(Asset asset);

	int updateAssetSeq(String kubunCode);

	String selectAssetNumber(String companyCode, String kubunCode);

	int deleteAsset(int assetSeq);

	int deleteMaintenanceHistory(MaintenanceHistory maintenanceHistory);

}
