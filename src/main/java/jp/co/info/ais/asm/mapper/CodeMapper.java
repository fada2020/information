package jp.co.info.ais.asm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.info.ais.asm.domain.Code;
import jp.co.info.ais.asm.domain.CodeMaster;

@Mapper
public interface CodeMapper {

	List<Code> selectCodeList(Code code);

	//001
	List<Code> selectStateCode();
	//002
	List<Code> selectProductCode();
	//003
	List<Code> selectCategoryCode();
	//004
	List<Code> selectKindCode();

	int selectMasterId(String masterId);

	CodeMaster MasterIdNameCheck(String masterId, String masterName);




}
