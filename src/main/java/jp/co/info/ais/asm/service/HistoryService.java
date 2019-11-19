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

	public List<History> selectHistory(History condition){
		return HistoryMapper.selectHistory(condition);
	}

	public List<CodeDetail> selectStateCode() {
		return HistoryMapper.selectStateCode();
	}

	public int selectCount(History condition) {
		return HistoryMapper.selectCount(condition);
	}

	public List<History> exportXlsx(){
		return HistoryMapper.exportXlsx();
	}

	public int deleteHistory(ArrayList<String> deleteList) {
		return HistoryMapper.deleteHistory(deleteList);
	}
}