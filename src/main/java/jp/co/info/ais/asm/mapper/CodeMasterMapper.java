package jp.co.info.ais.asm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.info.ais.asm.domain.CodeMaster;

/**
 *
 * @author AIS191101
 *
 */
@Mapper
public interface CodeMasterMapper {

	void insertMasterId(CodeMaster codeMasterId);

	void insertMasterName(CodeMaster codeMasterName);

	void insertMasterIdName(CodeMaster codeMasterId, CodeMaster codeMasterName);

	List<CodeMaster> selectCodeMasterList(CodeMaster condition);

	int selectCount(CodeMaster condition);

	int CodeMasterListCheck(CodeMaster masterCode);

	void CodeMasterInsert(CodeMaster codeMasterId);


	int updateCodeMaster(CodeMaster codeMasterId);


	int deleteMasterCode(String codeMasterId);


}
