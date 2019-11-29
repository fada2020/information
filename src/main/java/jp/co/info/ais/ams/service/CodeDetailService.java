package jp.co.info.ais.ams.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.ams.domain.CodeDetail;
import jp.co.info.ais.ams.domain.CodeMaster;
import jp.co.info.ais.ams.mapper.CodeDetailMapper;

@Service
public class CodeDetailService {
	private static final Logger logger = LogManager.getLogger(CodeMasterService.class);

	@Autowired
	private CodeDetailMapper codeDetailMapper;

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

	/**
	 * 状態コード情報
	 *
	 * @return List<CodeDetail> 状態コード情報リスト
	 */
	public List<CodeDetail> selectStateCode() {
		return codeDetailMapper.selectStateCode();
	}


	public List<CodeDetail> selectCode() {

		return codeDetailMapper.selectCode();
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

	public int deleteDetailCode(String codeMDetail) {
		return codeDetailMapper.deleteDetailCode(codeMDetail);
	}
	public int codeDetailListCheck(CodeDetail codeMDetail) {
		int num = 0;
		try {
		num = codeDetailMapper.codeDetailListCheck(codeMDetail);
		if (num == 0) {
			codeDetailMapper.codeDetailInsert(codeMDetail);
		}
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}

		return num;

	}
}
