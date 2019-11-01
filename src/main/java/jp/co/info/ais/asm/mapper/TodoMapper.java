package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Todo;

@Mapper
public interface TodoMapper {
	
	void insert(Todo todo);
	
	Todo select(int id);
	
}
