package jp.co.info.ais.ams.mapper;

import org.apache.ibatis.annotations.Mapper;

import jp.co.info.ais.ams.domain.Todo;

@Mapper
public interface TodoMapper {
	
	void insert(Todo todo);
	
	Todo select(int id);
	
}
