package jp.co.info.ais.ams.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.ams.common.AppConstant;
import jp.co.info.ais.ams.domain.Asset;
import jp.co.info.ais.ams.domain.CodeDetail;
import jp.co.info.ais.ams.domain.CodeMaster;
import jp.co.info.ais.ams.mapper.AssetMapper;
import jp.co.info.ais.ams.mapper.CodeDetailMapper;
import jp.co.info.ais.ams.mapper.CodeMasterMapper;

@Service
public class CodeDetailService {
	private static final Logger logger = LogManager.getLogger(CodeMasterService.class);

	@Autowired
	private CodeDetailMapper codeDetailMapper;
	@Autowired
	private AppConstant appConstant;
	@Autowired
	private CodeMasterMapper codeMasterMapper;
	@Autowired
	private AssetMapper assetMapper;
	/**
	 *　Listで転送
	 * @param condition
	 * @return　List<CodeMaster> マスターコードリスト
	 */
	public List<CodeDetail> selectCodeDetailList(CodeDetail condition) {
		return codeDetailMapper.selectCodeDetailList(condition);
	}

	/**
	 *
	 * @param condition
	 * @return int condition
	 */
	public int selectCount(CodeDetail condition) {

		return codeDetailMapper.selectCount(condition);
	}

	/**
	 *Listに値の存在チェック
	 * @param masterCode
	 * @return int num
	 */
	public int CodeDetailListCheck(CodeDetail codedetail) {
		int num = 0;
		num = codeDetailMapper.codeDetailListCheck(codedetail);
		if(num==0) {
			codeDetailMapper.codeDetailInsert(codedetail);
		}
		return num;

	}


	/**
	 * 区分コード情報
	 *
	 * @return List<CodeDetail> 区分コード情報リスト
	 */
	public List<CodeDetail> selectMasterCodeId() {
		return codeDetailMapper.selectMasterCodeId();
	}



	public List<CodeDetail> selectCode() {

		return codeDetailMapper.selectCode(appConstant.USE_CODE);
	}
	/**
	 *修正Update
	 * @param masterCode
	 * @return int masterCode
	 */
	/*public int updateCodeDetail(CodeMaster masterCode) {

		 return codeDetailMapper.updateCodeDetail(masterCode);

	}
	*//**
		*マスターコード削除
		* @param codeMasterId
		* @return int codeMasterId
		*//*
			public int deleteCodeDetail(String codeMasterId) {
			return codeDetailMapper.deleteDetailCode(codeMasterId);

			}
			*/

	public List<CodeMaster> masterid() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public int updateCodeDetail(CodeDetail detailCode) {

		int num=0;
		try {

		num =codeDetailMapper.updateCodeDetail(detailCode);
		}catch(Exception e) {
			logger.debug(e.getMessage());
		}
		 return num;
	}

	public int deleteBefore(CodeDetail codeDetail) {
		int num=0;
		try {
			Asset asset =new Asset();
			if(codeDetail.getCodeMasterId().equals(appConstant.MASTER_STATE)) {
				asset.setStatusCode(codeDetail.getCodeDetailId());
				num=assetMapper.selectCount(asset);
			}else if(codeDetail.getCodeMasterId().equals(appConstant.MASTER_DETAIL)){
				asset.setKubunCode(codeDetail.getCodeDetailId());
				num=assetMapper.selectCount(asset);
			}else if(codeDetail.getCodeMasterId().equals(appConstant.MASTER_CLASS)) {
				num=codeDetailMapper.selectCount(new CodeDetail(appConstant.MASTER_DETAIL,null,codeDetail.getCodeDetailId(),appConstant.USE_CODE));
			}else {
				num=0;
			}
		}catch(Exception e) {
			logger.debug(e.getMessage());
		}
		return num;
	}
	public int deleteDetailCode(CodeDetail codeDetail) {
	int num=0;
		try {
			num=deleteBefore(codeDetail);
		if(num==0) {
			codeDetail.setUseFlag(appConstant.UNUSE_CODE);
			codeDetailMapper.deleteDetailCode(codeDetail);
		}
		}catch(Exception e) {
			logger.debug(e.getMessage());
		}
		return num;
	}

	public int codeDetailListCheck(CodeDetail codeMDetail) {
		int num = 0;
		try {
		num = codeDetailMapper.codeDetailListCheck(codeMDetail);
		if (num == 0) {
			if(codeMDetail.getCodeMasterId().equals(appConstant.MASTER_DETAIL)&&codeMDetail.getItem1()==null) {
						codeMDetail.setItem1(appConstant.STATE_STORAGE);

					}
			codeDetailMapper.codeDetailInsert(codeMDetail);
		}
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}

		return num;

	}

	public List<CodeMaster> codeMasterList(CodeMaster codemaster) {


		return codeMasterMapper.codeMasterList(codemaster);
	}
}
