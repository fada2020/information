package jp.co.info.ais.ams.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.ams.common.AppConstant;
import jp.co.info.ais.ams.domain.CodeDetail;
import jp.co.info.ais.ams.domain.CodeMaster;
import jp.co.info.ais.ams.mapper.CodeDetailMapper;
import jp.co.info.ais.ams.mapper.CodeMasterMapper;

@Service
public class CodeMasterService {
	private static final Logger logger = LogManager.getLogger(CodeMasterService.class);

	@Autowired
	private CodeMasterMapper codeMasterMapper;

	@Autowired
	private CodeDetailMapper codeDetailMapper;
	/**
	 *マスターコードと名前をInsert
	 * @param codeMasterId
	 * @param codeMasterName
	 */
	public void insertMasterIdName(CodeMaster codeMasterId, CodeMaster codeMasterName) {
		codeMasterMapper.insertMasterIdName(codeMasterId, codeMasterName);

	}
	/**
	 *名前をInsert
	 * @param codeMasterName
	 */
	public void insertMasterName(CodeMaster codeMasterName) {

		codeMasterMapper.insertMasterName(codeMasterName);
	}
	/**
	 *マスターコードInsert
	 * @param codeMasterId
	 */
	public void insertMasterId(CodeMaster codeMasterId) {

		codeMasterMapper.insertMasterName(codeMasterId);
	}
	/**
	 *　Listで転送
	 * @param condition
	 * @return　List<CodeMaster> マスターコードリスト
	 */
	public List<CodeMaster> selectCodeMasterList(CodeMaster condition) {
		return codeMasterMapper.selectCodeMasterList(condition);
	}
	/**
	 *
	 * @param condition
	 * @return int condition
	 */
	public int selectCount(CodeMaster condition) {
		return codeMasterMapper.selectCount(condition);
	}
	/**
	 *Listに値の存在チェック
	 * @param masterCode
	 * @return int num
	 */
	public int CodeMasterListCheck(CodeMaster masterCode) {
		int num = 0;
		num = codeMasterMapper.CodeMasterListCheck(masterCode);
		if (num == 0) {
			codeMasterMapper.CodeMasterInsert(masterCode);
		}

		return num;

	}
	/**
	 *修正Update
	 * @param masterCode
	 * @return int masterCode
	 */
	public int updateCodeMaster(CodeMaster masterCode) {

		 return codeMasterMapper.updateCodeMaster(masterCode);

	}

	public int deleteCodeBefore(CodeDetail codeDetail) {
		int result=0;

		try {

			result=codeDetailMapper.selectCount(codeDetail);

		}catch(Exception e) {
			logger.debug(e.getMessage());
		}
		return result;
	}
	/**
	 *マスターコード削除
	 * @param codeMasterId
	 * @return int codeMasterId
	 */
	public int deleteMasterCode(CodeDetail codeDetail) {
		int result=0;

		try {

			result=deleteCodeBefore(codeDetail);

			if(result==0) {
				CodeMaster codeMaster = new CodeMaster();
				codeMaster.setCodeMasterId(codeDetail.getCodeMasterId());
				codeMaster.setUseFlag(AppConstant.UNUSE_CODE);
			codeMasterMapper.deleteMasterCode(codeMaster);
		}

		}catch(Exception e) {
			logger.debug(e.getMessage());
		}
		return result;

	}



}
