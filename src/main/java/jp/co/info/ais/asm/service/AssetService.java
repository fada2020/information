package jp.co.info.ais.asm.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.asm.domain.Accessories;
import jp.co.info.ais.asm.domain.Asset;
import jp.co.info.ais.asm.domain.CodeDetail;
import jp.co.info.ais.asm.mapper.AssetMapper;

@Service
public class AssetService {

	private static final Logger logger = LogManager.getLogger(AssetService.class);

	@Autowired
	AssetMapper assetMapper;

	public Asset select(int assetSeq) {
		Asset condition = new Asset();
		condition.setAssetSeq(assetSeq);
		List<Asset> result = assetMapper.select(condition);
		Asset asset = null;
		if(result != null)
			asset = result.get(0);
		return asset;
	}

	public List<Asset> selectList(Asset condition) {
		return assetMapper.select(condition);
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

}
