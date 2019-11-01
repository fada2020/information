package jp.co.info.ais.asm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.asm.domain.Todo;
import jp.co.info.ais.asm.mapper.TodoMapper;

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
