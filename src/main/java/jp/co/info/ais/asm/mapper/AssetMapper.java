package jp.co.info.ais.asm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.info.ais.asm.domain.Asset;
import jp.co.info.ais.asm.domain.CodeDetail;

@Mapper
public interface AssetMapper {
	List<Asset> select(Asset ITAsset);

	int selectCount(Asset ITAsset);

	List<CodeDetail> selectProductCode();

	List<CodeDetail> selectStateCode();
}
