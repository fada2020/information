package jp.co.info.ais.asm.controller;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.info.ais.asm.common.AppConstant;
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
			model.addAttribute("totalCnt", hwCnt + swCnt);	// Total 装置数量
			model.addAttribute("newItem", newItem);	// 今月購入した装置数量
			// 保有現況グラフ
			int i = 0, j = 0, hw = 0, sw = 0;	// i, j, hw, sw 値を初期化
			String[] hardTop = new String[20];
			String[] softTop = new String[5];
			String[] colorTop = {"#3498DB", "#9B59B6", "#E74C3C", "#26B99A", "#BDC3C7"};
			int hardCnt[] = new int[20];
			int softCnt[] = new int[5];
			// hardTop, hardCnt, softTop, softCntに値を代入
			for (Dashboard item : dash) {
				if (item.getTypeName().equals("HW")) {
					hardTop[j] = item.getKubunName();
					hardCnt[j] = item.getTypeCnt();
					j += 1;
					hw += 1;
				} else {
					softTop[i] = item.getKubunName();
					softCnt[i] = item.getTypeCnt();
					i += 1;
					sw += 1;
				}
			}
			model.addAttribute("toplist", dash);		// Top5 table list
			model.addAttribute("hardTop", hardTop);		// HW Top5 Graph list
			model.addAttribute("softTop", softTop);		// SW Top5 Graph list
			model.addAttribute("hardCnt", hardCnt);		// HW Top5 Graph Cnt
			model.addAttribute("softCnt", softCnt);		// SW Top5 Graph Cnt
			model.addAttribute("colorTop", colorTop);	// color list
			model.addAttribute("hw", hw);		// 登録したHW種数
			model.addAttribute("sw", sw);		// 登録したSW種数

			// 貸与現況グラフ
			ArrayList<Dashboard> rslist = new ArrayList<Dashboard>();
			rslist = dashboardService.rslist();
			// 【目録】貸与現況グラフ
			AppConstant rentlist = new AppConstant();
			model.addAttribute("rentlist", rentlist);
			// 【数量】貸与現況グラフ：保有、貸与
			model.addAttribute("DT001", 0);	model.addAttribute("DT002", 0);	//初期化
			model.addAttribute("NB001", 0); model.addAttribute("NB002", 0);
			model.addAttribute("TBL001", 0); model.addAttribute("TBL002", 0);
			model.addAttribute("MD001", 0); model.addAttribute("MD002", 0);
			model.addAttribute("DP001", 0); model.addAttribute("DP002", 0);
			model.addAttribute("KBD001", 0); model.addAttribute("KBD002", 0);
			model.addAttribute("MS001", 0); model.addAttribute("MS002", 0);

			for (Dashboard item : rslist) {
				if (item.getKubunCode().equals("02")) {
					// Desktop保有
					if(item.getStatusCode().equals("01")) {
						model.addAttribute("DT001", item.getTypeCnt());
					}
					// Desktop貸与
					if(item.getStatusCode().equals("02")) {
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
				if (item.getKubunCode().equals("03")) {
					// Notebook保有
					if(item.getStatusCode().equals("01")) {
						model.addAttribute("NB001", item.getTypeCnt());
					}
					// Notebook貸与
					if(item.getStatusCode().equals("02")) {
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
				if (item.getKubunCode().equals("04")) {
					// Tablet保有
					if(item.getStatusCode().equals("01")) {
						model.addAttribute("TBL001", item.getTypeCnt());
					}
					// Tablet貸与
					if(item.getStatusCode().equals("02")) {
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
				if (item.getKubunCode().equals("05")) {
					// Mobile保有
					if(item.getStatusCode().equals("01")) {
						model.addAttribute("MD001", item.getTypeCnt());
					}
					// Mobile貸与
					if(item.getStatusCode().equals("02")) {
						model.addAttribute("MD002", item.getTypeCnt());
					} else {
						model.addAttribute("MD002", 0);
						model.addAttribute("DP001", 0); model.addAttribute("DP002", 0);
						model.addAttribute("KBD001", 0); model.addAttribute("KBD002", 0);
						model.addAttribute("MS001", 0); model.addAttribute("MS002", 0);
						continue;
					}
				}
				if (item.getKubunCode().equals("06")) {
					// Monitor保有
					if(item.getStatusCode().equals("01")) {
						model.addAttribute("DP001", item.getTypeCnt());
					}
					// Monitor貸与
					if(item.getStatusCode().equals("02")) {
						model.addAttribute("DP002", item.getTypeCnt());
					} else {
						model.addAttribute("DP002", 0);
						model.addAttribute("KBD001", 0); model.addAttribute("KBD002", 0);
						model.addAttribute("MS001", 0); model.addAttribute("MS002", 0);
						continue;
					}
				}
				if (item.getKubunCode().equals("07")) {
					// Keyboard保有
					if(item.getStatusCode().equals("01")) {
						model.addAttribute("KBD001", item.getTypeCnt());
					}
					// Keyboard貸与
					if(item.getStatusCode().equals("02")) {
						model.addAttribute("KBD002", item.getTypeCnt());
					} else {
						model.addAttribute("KBD002", 0);
						model.addAttribute("MS001", 0); model.addAttribute("MS002", 0);
						continue;
					}
				}
				if (item.getKubunCode().equals("08")) {
					// Mouse保有
					if(item.getStatusCode().equals("01")) {
						model.addAttribute("MS001", item.getTypeCnt());
					}
					// Keyboard貸与
					if(item.getStatusCode().equals("02")) {
						model.addAttribute("MS002", item.getTypeCnt());
					} else {
						model.addAttribute("MS002", 0);
						continue;
					}
				}
			}

		} catch (Exception e) {
			logger.debug("error'{}",e);
		} //try...catch文

		return "dashboard";
	}


}
