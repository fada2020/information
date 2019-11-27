package jp.co.info.ais.asm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.asm.domain.CodeDetail;
import jp.co.info.ais.asm.domain.History;
import jp.co.info.ais.asm.mapper.HistoryMapper;

@Service
public class HistoryService{

	@Autowired
	HistoryMapper HistoryMapper;

	/**
	 * 検索及び画面表示情報の作成
	 *
	 * @param History　検索条件
	 * @return List<History>　検索結果
	 */
	public List<History> selectHistory(History condition){
		return HistoryMapper.selectHistory(condition);
	}

	/**
	 * 状態コード値セッティング
	 *
	 * @return List<CodeDetail>　状態コード情報リスト
	 */
	public List<CodeDetail> selectStateCode() {
		return HistoryMapper.selectStateCode();
	}

	/**
	 * 検索結果の数
	 *
	 * @param History　検索条件
	 * @return int　検索結果の数
	 */
	public int selectCount(History condition) {
		return HistoryMapper.selectCount(condition);
	}

	/**
	 * エクセルファイル抽出
	 *
	 * @return List<History>　全ての履歴データ
	 */
	public List<History> exportXlsx(){
		return HistoryMapper.exportXlsx();
	}

	/**
	 * 履歴情報削除
	 *
	 * @param ArrayList<String>　削除対象の貸与シークエンス
	 * @return int　削除されたデータの数
	 */
	public int deleteHistory(ArrayList<String> deleteList) {
		return HistoryMapper.deleteHistory(deleteList);
	}
}