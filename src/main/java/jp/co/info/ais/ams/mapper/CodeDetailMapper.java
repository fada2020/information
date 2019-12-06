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


	// リスト照会
	List<CodeDetail> selectCodeDetailList(CodeDetail condition);

	//ページング
	int selectCount(CodeDetail condition);

	// Listを確認してINTタイプで送る
	int codeDetailListCheck(CodeDetail codeDetail);

	//詳細新規登録
	void codeDetailInsert(CodeDetail codeDetail);

	//変更Update
	int updateCodeDetail(CodeDetail detailCode);

	//詳細ーコード削除
	int deleteCodeDetail(String codeMasterId);

	// マスターコードIDで選択 (code_detailテーブルから)
	List<CodeDetail> selectMasterCodeId();

	//マスターコードIDで選択 (code_masterテーブルから)-
	List<CodeDetail> selectCode();

	//詳細ーコード削除
	int deleteDetailCode(String codeMDetail);



}
