package jp.co.info.ais.asm.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.asm.domain.Code;
import jp.co.info.ais.asm.mapper.CodeMapper;

@Service
public class CodeService {
	private static final Logger logger = LogManager.getLogger(CodeService.class);

	@Autowired
	private CodeMapper codeMapper;

	public List<Code> selectCodeList(Code condition){
		return codeMapper.selectCodeList(condition);

	}

	public List<Code> selectStateCode() {
		return codeMapper.selectStateCode();
	}




}
