package jp.co.info.ais.asm.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.asm.domain.CodeMaster;
import jp.co.info.ais.asm.mapper.CodeMasterMapper;

@Service
public class CodeMasterService {
	private static final Logger logger = LogManager.getLogger(CodeMasterService.class);

	@Autowired
	private CodeMasterMapper codeMasterMapper;


	public void insertMasterIdName(CodeMaster codeMasterId, CodeMaster codeMasterName) {
		codeMasterMapper.insertMasterIdName(codeMasterId, codeMasterName);

	}

	public void insertMasterName(CodeMaster codeMasterName) {

		codeMasterMapper.insertMasterName(codeMasterName);
	}


	public void insertMasterId(CodeMaster codeMasterId) {

		codeMasterMapper.insertMasterName(codeMasterId);
	}


}