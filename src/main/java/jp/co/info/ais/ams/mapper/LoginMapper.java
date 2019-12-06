package jp.co.info.ais.ams.mapper;

import org.apache.ibatis.annotations.Mapper;

import jp.co.info.ais.ams.domain.Login;
@Mapper
public interface LoginMapper {

	//データベースからIDを持って来る
	int selectId(String id);

	Login selectLogin(String id, String pass);

}
