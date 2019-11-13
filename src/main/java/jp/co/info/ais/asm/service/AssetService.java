package jp.co.info.ais.asm.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.asm.domain.Asset;
import jp.co.info.ais.asm.domain.CodeDetail;
import jp.co.info.ais.asm.mapper.AssetMapper;

@Service
public class AssetService {

	private static final Logger logger = LogManager.getLogger(AssetService.class);

	@Autowired
	AssetMapper ITAssetMapper;

	public Asset select(String assetNumber) {
		Asset condition = new Asset();
		condition.setAssetNumber(assetNumber);
		List<Asset> result = ITAssetMapper.select(condition);
		Asset asset = null;
		if(result != null)
			asset = result.get(0);
		return asset;
	}

	public List<Asset> selectList(Asset condition) {
		return ITAssetMapper.select(condition);
	}

	public int selectCount(Asset condition) {
		return ITAssetMapper.selectCount(condition);
	}

	public List<CodeDetail> selectProductCode() {
		return ITAssetMapper.selectProductCode();
	}

	public List<CodeDetail> selectStateCode() {
		return ITAssetMapper.selectStateCode();
	}

}
