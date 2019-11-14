package jp.co.info.ais.asm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.info.ais.asm.domain.Accessories;
import jp.co.info.ais.asm.domain.Asset;
import jp.co.info.ais.asm.domain.CodeDetail;

@Mapper
public interface AssetMapper {
	List<Asset> select(Asset asset);

	int selectCount(Asset asset);

	List<CodeDetail> selectProductCode();

	List<CodeDetail> selectStateCode();

	void insertAccessories(Accessories accessories);
}
