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

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
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

// 保有現況数値
		ArrayList<Dashboard> dash = new ArrayList<Dashboard>();
		dash = dashboardService.possession();
	// HW, SW, Total Cnt
		int hwCnt = 0;
		int swCnt = 0;
		for(int i = 0 ; i < dash.size() ; i++) {
			if(dash.get(i).getTypeName().equals("HW")) {
				hwCnt += dash.get(i).getTypeCnt();
			} else {
				swCnt += dash.get(i).getTypeCnt();
			}
		}
		model.addAttribute("hwCnt", hwCnt);
		model.addAttribute("swCnt", swCnt);
		model.addAttribute("totalCnt", hwCnt + swCnt);
	// newItem Cnt
		int newItem = dashboardService.newItem();
		model.addAttribute("newItem", newItem);
//  保有現況グラフ
		model.addAttribute("marulist", dash);
		// (1)hardTop, softTop
		ArrayList<String> hardTop = new ArrayList<String>();
		ArrayList<String> softTop = new ArrayList<String>();
		for(int i = 0 ; i<dash.size(); i++) {
			if(dash.get(i).getTypeName().equals("HW")) {
				hardTop.add(dash.get(i).getKubunName());
			} else {
				softTop.add(dash.get(i).getKubunName());
			}
		}
		model.addAttribute("hardTop", hardTop);
		model.addAttribute("softTop", softTop);
		// (2)hardPercent, softPercent
		ArrayList<Integer> hardPercent = new ArrayList<Integer>();
		ArrayList<Integer> softPercent = new ArrayList<Integer>();
		for(int i = 0 ; i<dash.size(); i++) {
			if(dash.get(i).getTypeName().equals("HW")) {
				hardPercent.add(dash.get(i).getTypeCnt());
			} else {
				softPercent.add(dash.get(i).getTypeCnt());
			}
		}
		model.addAttribute("hardPercent", hardPercent);
		model.addAttribute("softPercent", softPercent);

//  貸与現況グラフ
		ArrayList<Dashboard> rslist = new ArrayList<Dashboard>();
		rslist = dashboardService.rslist();
		ArrayList<String> rentItem = new ArrayList<String>();
	// rentItem
		for(int i = 0 ; i<rslist.size(); i++) {
			if(i+1 < rslist.size()) {
				if(!rslist.get(i).getKubunName().equals(rslist.get(i+1).getKubunName())  ){
					rentItem.add(rslist.get(i).getKubunName());
//					logger.debug("rslist;{}", rslist.get(i).getKubunName()+i);
				}
			} else {
				if(!rslist.get(i).getKubunName().equals(rslist.get(i-1).getKubunName())  ){
					rentItem.add(rslist.get(i).getKubunName());
//					logger.debug("rslist;{}", rslist.get(i).getKubunName()+i);
				}
			}
		}
		model.addAttribute("rentItem", rentItem);
		for(int i = 0 ; i<rslist.size() ; i++) {
			if(rslist.get(i).getKubunCode().equals("002")) {
				if(rslist.get(i).getStatusCode().equals("001")) {
					model.addAttribute("DT001", rslist.get(i).getTypeCnt());
				}
				if(rslist.get(i).getStatusCode().equals("002")) {
					model.addAttribute("DT002", rslist.get(i).getTypeCnt());
				} else {
					model.addAttribute("DT002", 0);
				}
			}
			if(rslist.get(i).getKubunCode().equals("003")) {
				if(rslist.get(i).getStatusCode().equals("001")) {
					model.addAttribute("NB001", rslist.get(i).getTypeCnt());
				}
				if(rslist.get(i).getStatusCode().equals("002")) {
					model.addAttribute("NB002", rslist.get(i).getTypeCnt());
				} else {

					model.addAttribute("NB002", 0);
				}
			}
		}
		logger.debug("{}",rslist);





		/*for(int j = 1 ; j <3 ; j++) {
			int statusCode = Integer.parseInt(rslist.get(j).getStatusCode());
			for(int i = 1 ; i<rslist.size() ; i++) {
				int kubunCode = Integer.parseInt(rslist.get(i).getKubunCode());
				if(kubunCode == i+1 && statusCode == j ) {
					test.get(i).setTypeCnt(rslist.get(i).getTypeCnt());
				} else {
					test.get(i).setTypeCnt(rslist.get(i).getTypeCnt());
				}
				logger.debug("s : {}",test.get(i).getTypeCnt());
			}
		}
*/


	// rentCnt



/*
		ArrayList<Integer> rentCnt = new ArrayList<Integer>();
		ArrayList<Integer> storageCnt = new ArrayList<Integer>();
		for(int j = 1 ; j <3 ; j++) {
			int statusCode = Integer.parseInt(rslist.get(j).getStatusCode());
			for(int i = 1 ; i<rslist.size() ; i++) {
				int kubunCode = Integer.parseInt(rslist.get(i).getKubunCode());
				if(kubunCode == i+1 && statusCode == j ) {
					storageCnt.add(rslist.get(i).getTypeCnt());
				} else {
					rentCnt.add(rslist.get(i).getTypeCnt());
				}
				logger.debug("s : {},r : {}",storageCnt , rentCnt);
			}
		}

*/


        return "dashboard";
    }
}
