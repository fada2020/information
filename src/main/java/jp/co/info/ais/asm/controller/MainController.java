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

import jp.co.info.ais.asm.domain.Dashboard;
import jp.co.info.ais.asm.service.DashboardService;

@Controller
public class MainController {

	@Autowired
	DashboardService dashboardService;

	private static final Logger logger = LogManager.getLogger(MainController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request
    					, Model model) {
		@SuppressWarnings("unchecked")
		List<String> messages = (List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
		if (messages == null) {
			messages = new ArrayList<>();
			request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
		}
		messages.add("bobobobo");
		request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
		logger.debug(messages.toString());
//		保有現況グラフ
		ArrayList<Dashboard> possession = new ArrayList<Dashboard>();
		possession = dashboardService.possession();
		model.addAttribute("possession", possession);



//		貸与現況グラフ
		ArrayList<Dashboard> rslist = new ArrayList<Dashboard>();
		rslist = dashboardService.rslist();
		model.addAttribute("rslist", rslist);

		for(int i = 0 ; i<rslist.size(); i++) {
			logger.debug("itemName:{},state:{},itemCnt:{}",
				rslist.get(i).getItemName(),rslist.get(i).getState(),rslist.get(i).getItemCnt());
		}

        return "dashboard";
    }
}
