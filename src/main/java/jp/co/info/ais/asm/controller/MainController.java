package jp.co.info.ais.asm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import jp.co.info.ais.asm.domain.Todo;
import jp.co.info.ais.asm.service.TodoService;

@Controller
public class MainController {

	private static final Logger logger = LogManager.getLogger(MainController.class);

	@Autowired
	private TodoService todoService;

    @RequestMapping(value = "/indexTest", method = RequestMethod.GET)
    public String indexTest(Model model,
    		@SessionAttribute(name = "MY_SESSION_MESSAGES", required = false) List<String> messages) {

		Todo newTodo = new Todo();
		newTodo.setTitle("飲み会");
		newTodo.setDetails("銀座 19:00");

		//todoService.insert(newTodo); // 新しいTodoをインサートする

    	Todo todo = todoService.selectTodo(1);
    	if(!(null == todo)) {
    		logger.debug("Hello from Todo title = {}", todo.getTitle());
    	}

    	logger.debug(messages.toString() + " indexTest");

		model.addAttribute("message", "Hello!! Springboot!!!!");
        return "indexTest";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		List<String> messages = (List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
		if (messages == null) {
			messages = new ArrayList<>();
			request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
		}
		messages.add("bobobobo");
		request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);

		logger.debug(messages.toString());

        return "index";
    }
}
