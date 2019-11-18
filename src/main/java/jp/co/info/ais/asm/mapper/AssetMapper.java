package jp.co.info.ais.asm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.info.ais.asm.domain.Accessories;
import jp.co.info.ais.asm.domain.Asset;
import jp.co.info.ais.asm.domain.CodeDetail;
import jp.co.info.ais.asm.domain.MaintenanceHistory;

@Mapper
public interface AssetMapper {
	Asset selectAsset(int assetSeq);

	List<Asset> selectAssetList(Asset asset);

	int selectCount(Asset asset);

	List<CodeDetail> selectProductCode();

	List<CodeDetail> selectStateCode();

	void insertAccessories(Accessories accessories);

	void insertMaintenanceHistory(MaintenanceHistory maintenanceHistory);

	int updateAccessories(Accessories accessories);

	int updateMaintenanceHistory(MaintenanceHistory maintenanceHistory);
}
