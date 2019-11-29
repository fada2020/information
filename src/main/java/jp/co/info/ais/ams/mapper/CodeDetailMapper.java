package jp.co.info.ais.ams.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.info.ais.ams.domain.CodeDetail;

/**
 *
 * @author AIS191101
 *
 */
@Mapper
public interface CodeDetailMapper {


	List<CodeDetail> selectCodeDetailList(CodeDetail condition);

	int selectCount(CodeDetail condition);

	int CodeDetailListCheck(CodeDetail codeDetail);

	void CodeDetailInsert(CodeDetail codeMasterId);

	int updateCodeDetail(CodeDetail codeMasterId);

	int deleteCodeDetail(String codeMasterId);

	List<CodeDetail> selectProductCode();

	List<CodeDetail> selectStateCode();

	List<CodeDetail> selectKubunCode();

	List<CodeDetail> selectMasterCodeId();

	List<CodeDetail> selectCode();

	void CodeMasterInsert(CodeDetail codedetail);

	int deleteDetailCode(String codeMDetail);


}
