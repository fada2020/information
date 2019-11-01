package com.example.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.domain.Todo;
import com.example.demo.service.TodoService;

@Controller
public class MainController {

	private static final Logger logger = LogManager.getLogger(MainController.class);

	@Autowired
	private TodoService todoService;

    @RequestMapping(value = "/indexTest", method = RequestMethod.GET)
    public String indexTest(Model model) {

		Todo newTodo = new Todo();
		newTodo.setTitle("飲み会");
		newTodo.setDetails("銀座 19:00");

		todoService.insert(newTodo); // 新しいTodoをインサートする

    	Todo todo = todoService.selectTodo(1);
    	if(!(null == todo)) {
    		logger.debug("Hello from Todo title = {}", todo.getTitle());
    	}

		model.addAttribute("message", "Hello!! Springboot!!!!");
        return "indexTest";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }
}
