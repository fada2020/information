package jp.co.info.ais.asm.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.asm.domain.ITAsset;
import jp.co.info.ais.asm.domain.ProductCode;
import jp.co.info.ais.asm.domain.StateCode;
import jp.co.info.ais.asm.mapper.ITAssetMapper;

@Service
public class ITAssetService {

	private static final Logger logger = LogManager.getLogger(ITAssetService.class);

	@Autowired
	ITAssetMapper ITAssetMapper;

	public List<ITAsset> select(ITAsset ITAsset) {
		return ITAssetMapper.select(ITAsset);
	}

	public int selectCount(ITAsset ITAsset) {
		return ITAssetMapper.selectCount(ITAsset);
	}

	public List<ProductCode> selectProductCode() {
		return ITAssetMapper.selectProductCode();
	}

	public List<StateCode> selectStateCode() {
		return ITAssetMapper.selectStateCode();
	}

}
