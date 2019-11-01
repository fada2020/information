package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Todo;
import com.example.demo.mapper.TodoMapper;

@Service
public class TodoService {
	
	@Autowired
	private TodoMapper todoMapper;
	
	public Todo selectTodo(int id) {
		return todoMapper.select(id);
	}

	public void insert(Todo todo) {
		todoMapper.insert(todo);
	}
	
}
