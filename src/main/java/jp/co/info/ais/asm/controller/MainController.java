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

			// 保有現況数値
			ArrayList<Dashboard> dash = new ArrayList<Dashboard>();
			dash = dashboardService.possession();
			// HW, SW, Total Cnt
			int hwCnt = 0;
			int swCnt = 0;
			for (Dashboard item : dash) {
				if (item.getTypeName().equals("HW")) {
					hwCnt += item.getTypeCnt();
				} else {
					swCnt += item.getTypeCnt();
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
			ArrayList<Integer> hardPercent = new ArrayList<Integer>();
			ArrayList<Integer> softPercent = new ArrayList<Integer>();

			for (Dashboard item : dash) {
				if (item.getTypeName().equals("HW")) {
					hardTop.add(item.getKubunName());
					hardPercent.add(item.getTypeCnt());
				} else {
					softTop.add(item.getKubunName());
					softPercent.add(item.getTypeCnt());
				}
			}
			model.addAttribute("hardTop", hardTop);
			model.addAttribute("softTop", softTop);
			model.addAttribute("hardPercent", hardPercent);
			model.addAttribute("softPercent", softPercent);

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
			model.addAttribute("DT001", 0);	model.addAttribute("DT002", 0);
			model.addAttribute("NB001", 0); model.addAttribute("NB002", 0);
			model.addAttribute("TBL001", 0); model.addAttribute("TBL002", 0);
			model.addAttribute("MD001", 0); model.addAttribute("MD002", 0);
			model.addAttribute("DP001", 0); model.addAttribute("DP002", 0);
			model.addAttribute("KBD001", 0); model.addAttribute("KBD002", 0);
			model.addAttribute("MS001", 0); model.addAttribute("MS002", 0);

			for (Dashboard item : rslist) {
				if (item.getKubunCode().equals("002")) {
					if(item.getStatusCode().equals("001")) {
						model.addAttribute("DT001", item.getTypeCnt());
					}
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
					if(item.getStatusCode().equals("001")) {
						model.addAttribute("NB001", item.getTypeCnt());
					}
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
					if(item.getStatusCode().equals("001")) {
						model.addAttribute("TBL001", item.getTypeCnt());
					}
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
					if(item.getStatusCode().equals("001")) {
						model.addAttribute("MD001", item.getTypeCnt());
					}
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
					if(item.getStatusCode().equals("001")) {
						model.addAttribute("DP001", item.getTypeCnt());
					}
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
					if(item.getStatusCode().equals("001")) {
						model.addAttribute("KBD001", item.getTypeCnt());
					}
					if(item.getStatusCode().equals("002")) {
						model.addAttribute("KBD002", item.getTypeCnt());
					} else {
						model.addAttribute("KBD002", 0);
						model.addAttribute("MS001", 0); model.addAttribute("MS002", 0);
						continue;
					}
				}
				if (item.getKubunCode().equals("008")) {
					if(item.getStatusCode().equals("001")) {
						model.addAttribute("MS001", item.getTypeCnt());
					}
					if(item.getStatusCode().equals("002")) {
						model.addAttribute("MS002", item.getTypeCnt());
					} else {
						model.addAttribute("MS002", 0);
						continue;
					}
				}
			}
/*
			for (int i = 0; i < rslist.size(); i++) {

				if (rslist.get(i).getKubunCode().equals("004")) {
					if (rslist.get(i).getStatusCode().equals("001")) {
						model.addAttribute("TBL001", rslist.get(i).getTypeCnt());
					}
					if (!rslist.get(i).getStatusCode().equals("001")) {
						model.addAttribute("TBL001", 0);
					}
					if (rslist.get(i).getStatusCode().equals("002")) {
						model.addAttribute("TBL002", rslist.get(i).getTypeCnt());
					} else {
						model.addAttribute("TBL002", 0);
					}
				}
				if (rslist.get(i).getKubunCode().equals("005")) {
					if (rslist.get(i).getStatusCode().equals("001")) {
						model.addAttribute("MD001", rslist.get(i).getTypeCnt());
					}
					if (!rslist.get(i).getStatusCode().equals("001")) {
						model.addAttribute("MD001", 0);
					}
					if (rslist.get(i).getStatusCode().equals("002")) {
						model.addAttribute("MD002", rslist.get(i).getTypeCnt());
					} else {
						model.addAttribute("MD002", 0);
					}
				}

			}*/

		} catch (Exception e) {
			System.out.println(e);
		} //try...catch文

		return "dashboard";
	}


}
