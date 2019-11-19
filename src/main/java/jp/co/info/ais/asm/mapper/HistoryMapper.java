package jp.co.info.ais.asm.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.info.ais.asm.domain.CodeDetail;
import jp.co.info.ais.asm.domain.History;

@Mapper
public interface HistoryMapper{

	List<History> selectHistory(History History);

	int selectCount(History History);

	List<CodeDetail> selectStateCode();

	List<History> exportXlsx();

	int deleteHistory(ArrayList<String> deleteList);
}