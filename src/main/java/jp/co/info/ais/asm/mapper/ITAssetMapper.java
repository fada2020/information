package jp.co.info.ais.asm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.info.ais.asm.domain.ITAsset;
import jp.co.info.ais.asm.domain.ProductCode;
import jp.co.info.ais.asm.domain.StateCode;

@Mapper
public interface ITAssetMapper {
	List<ITAsset> select(ITAsset ITAsset);

	int selectCount(ITAsset ITAsset);

	List<ProductCode> selectProductCode();

	List<StateCode> selectStateCode();
}
