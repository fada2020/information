package jp.co.info.ais.asm.mapper;

import org.apache.ibatis.annotations.Mapper;

import jp.co.info.ais.asm.domain.Login;
@Mapper
public interface LoginMapper {

	Login select(String id, String pass);

}
