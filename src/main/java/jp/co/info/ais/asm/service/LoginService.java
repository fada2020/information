package jp.co.info.ais.asm.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.asm.domain.Login;
import jp.co.info.ais.asm.mapper.LoginMapper;




@Service
public class LoginService {


	private static final Logger logger = LogManager.getLogger(ITAssetService.class);


	@Autowired
	private LoginMapper loginMapper;

	public Login selectLogin(String id, String pass) {
		return loginMapper.select(id, pass);

	}
	/*
	public  loginCheckResult() {
		 //checkResult 転送？
		return null;
	}*/

}
