package jp.co.info.ais.ams.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.ams.domain.Login;
import jp.co.info.ais.ams.mapper.LoginMapper;


@Service
public class LoginService {


	private static final Logger logger = LogManager.getLogger(LoginService.class);

	/**
	 *
	 */
	@Autowired
	private LoginMapper loginMapper;
	/**
	 * ID存在チェック
	 * @param id
	 * @return int id
	 */
	public int selectLoginId(String id) {
		return loginMapper.selectId(id);

	}
	/**
	 * ID,パスワードの存在チェック
	 * @param id
	 * @param pass
	 * @return String id,pass
	 */
	public Login selectLogin(String id ,String pass) {
		return loginMapper.selectLogin(id, pass);

	}




}
