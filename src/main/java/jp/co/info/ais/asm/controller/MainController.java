package jp.co.info.ais.asm.controller;

import java.util.ArrayList;

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

	/**
	 * 資産管理システムのダッシュボードを出力
	 * @param model
	 * @return dashboard.html
	 */
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String index(Model model) {

		try {
			// 保有現況装置
			ArrayList<Dashboard> dash = dashboardService.possession();// HW, SW, Total 装置数量
			int newItem = dashboardService.newItem();// 今月購入した装置数量
			int hwCnt = 0;
			int swCnt = 0;
			for (Dashboard item : dash) {
				if (item.getTypeName().equals("HW")) {
					hwCnt += item.getTypeCnt();
				} else {
					swCnt += item.getTypeCnt();
				}
			}
			model.addAttribute("hwCnt", hwCnt);		// HW 装置数量
			model.addAttribute("swCnt", swCnt);		// SW 装置数量
			model.addAttribute("totalCnt", hwCnt + swCnt);// Total 装置数量
			model.addAttribute("newItem", newItem);// 今月購入した装置数量
			// 保有現況グラフ
			ArrayList<String> hardTop = new ArrayList<String>();
			ArrayList<String> softTop = new ArrayList<String>();
			ArrayList<Integer> hardCnt = new ArrayList<Integer>();
			ArrayList<Integer> softCnt = new ArrayList<Integer>();

			for (Dashboard item : dash) {
				if (item.getTypeName().equals("HW")) {
					hardTop.add(item.getKubunName());
					hardCnt.add(item.getTypeCnt());
				} else {
					softTop.add(item.getKubunName());
					softCnt.add(item.getTypeCnt());
				}
			}
			model.addAttribute("toplist", dash);		// Top5 list
			model.addAttribute("hardTop", hardTop);		// HW Top5
			model.addAttribute("softTop", softTop);		// SW Top5
			model.addAttribute("hardCnt", hardCnt);		// HW Top5 Cnt
			model.addAttribute("softCnt", softCnt);		// SW Top5 Cnt

			// 貸与現況グラフ
			ArrayList<Dashboard> rslist = new ArrayList<Dashboard>();
			rslist = dashboardService.rslist();
			ArrayList<String> rentItem = new ArrayList<String>();
			// 【目録】貸与現況グラフ
			for (int i = 0; i < rslist.size(); i++) {
				if (i + 1 < rslist.size()) {
					if (!rslist.get(i).getKubunName().equals(rslist.get(i + 1).getKubunName())) {
						rentItem.add(rslist.get(i).getKubunName());
					}
				} else {
					if (!rslist.get(i).getKubunName().equals(rslist.get(i - 1).getKubunName())) {
						rentItem.add(rslist.get(i).getKubunName());
					}
				}
			}
			model.addAttribute("rentItem", rentItem);
			// 【数量】貸与現況グラフ：保有、貸与
			model.addAttribute("DT001", 0);	model.addAttribute("DT002", 0);	//初期化
			model.addAttribute("NB001", 0); model.addAttribute("NB002", 0);
			model.addAttribute("TBL001", 0); model.addAttribute("TBL002", 0);
			model.addAttribute("MD001", 0); model.addAttribute("MD002", 0);
			model.addAttribute("DP001", 0); model.addAttribute("DP002", 0);
			model.addAttribute("KBD001", 0); model.addAttribute("KBD002", 0);
			model.addAttribute("MS001", 0); model.addAttribute("MS002", 0);

			for (Dashboard item : rslist) {
				if (item.getKubunCode().equals("002")) {
					// Desktop保有
					if(item.getStatusCode().equals("001")) {
						model.addAttribute("DT001", item.getTypeCnt());
					}
					// Desktop貸与
					if(item.getStatusCode().equals("002")) {
						model.addAttribute("DT002", item.getTypeCnt());
					} else {
						model.addAttribute("DT002", 0);
						model.addAttribute("NB001", 0); model.addAttribute("NB002", 0);
						model.addAttribute("TBL001", 0); model.addAttribute("TBL002", 0);
						model.addAttribute("MD001", 0); model.addAttribute("MD002", 0);
						model.addAttribute("DP001", 0); model.addAttribute("DP002", 0);
						model.addAttribute("KBD001", 0); model.addAttribute("KBD002", 0);
						model.addAttribute("MS001", 0); model.addAttribute("MS002", 0);
						continue;
					}
				}
				if (item.getKubunCode().equals("003")) {
					// Notebook保有
					if(item.getStatusCode().equals("001")) {
						model.addAttribute("NB001", item.getTypeCnt());
					}
					// Notebook貸与
					if(item.getStatusCode().equals("002")) {
						model.addAttribute("NB002", item.getTypeCnt());
					} else {
						model.addAttribute("NB002", 0);
						model.addAttribute("TBL001", 0); model.addAttribute("TBL002", 0);
						model.addAttribute("MD001", 0); model.addAttribute("MD002", 0);
						model.addAttribute("DP001", 0); model.addAttribute("DP002", 0);
						model.addAttribute("KBD001", 0); model.addAttribute("KBD002", 0);
						model.addAttribute("MS001", 0); model.addAttribute("MS002", 0);
						continue;
					}
				}
				if (item.getKubunCode().equals("004")) {
					// Tablet保有
					if(item.getStatusCode().equals("001")) {
						model.addAttribute("TBL001", item.getTypeCnt());
					}
					// Tablet貸与
					if(item.getStatusCode().equals("002")) {
						model.addAttribute("TBL002", item.getTypeCnt());
					} else {
						model.addAttribute("TBL002", 0);
						model.addAttribute("MD001", 0); model.addAttribute("MD002", 0);
						model.addAttribute("DP001", 0); model.addAttribute("DP002", 0);
						model.addAttribute("KBD001", 0); model.addAttribute("KBD002", 0);
						model.addAttribute("MS001", 0); model.addAttribute("MS002", 0);
						continue;
					}
				}
				if (item.getKubunCode().equals("005")) {
					// Mobile保有
					if(item.getStatusCode().equals("001")) {
						model.addAttribute("MD001", item.getTypeCnt());
					}
					// Mobile貸与
					if(item.getStatusCode().equals("002")) {
						model.addAttribute("MD002", item.getTypeCnt());
					} else {
						model.addAttribute("MD002", 0);
						model.addAttribute("DP001", 0); model.addAttribute("DP002", 0);
						model.addAttribute("KBD001", 0); model.addAttribute("KBD002", 0);
						model.addAttribute("MS001", 0); model.addAttribute("MS002", 0);
						continue;
					}
				}
				if (item.getKubunCode().equals("006")) {
					// Monitor保有
					if(item.getStatusCode().equals("001")) {
						model.addAttribute("DP001", item.getTypeCnt());
					}
					// Monitor貸与
					if(item.getStatusCode().equals("002")) {
						model.addAttribute("DP002", item.getTypeCnt());
					} else {
						model.addAttribute("DP002", 0);
						model.addAttribute("KBD001", 0); model.addAttribute("KBD002", 0);
						model.addAttribute("MS001", 0); model.addAttribute("MS002", 0);
						continue;
					}
				}
				if (item.getKubunCode().equals("007")) {
					// Keyboard保有
					if(item.getStatusCode().equals("001")) {
						model.addAttribute("KBD001", item.getTypeCnt());
					}
					// Keyboard貸与
					if(item.getStatusCode().equals("002")) {
						model.addAttribute("KBD002", item.getTypeCnt());
					} else {
						model.addAttribute("KBD002", 0);
						model.addAttribute("MS001", 0); model.addAttribute("MS002", 0);
						continue;
					}
				}
				if (item.getKubunCode().equals("008")) {
					// Mouse保有
					if(item.getStatusCode().equals("001")) {
						model.addAttribute("MS001", item.getTypeCnt());
					}
					// Keyboard貸与
					if(item.getStatusCode().equals("002")) {
						model.addAttribute("MS002", item.getTypeCnt());
					} else {
						model.addAttribute("MS002", 0);
						continue;
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		} //try...catch文

		return "dashboard";
	}


}
