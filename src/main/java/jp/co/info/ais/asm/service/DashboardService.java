package jp.co.info.ais.asm.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.asm.domain.Dashboard;
import jp.co.info.ais.asm.mapper.DashboardMapper;

@Service
public class DashboardService {

	@Autowired
	private DashboardMapper dashboardMapper;

//	保有現況数値
	public ArrayList<Dashboard> possession() {
		ArrayList<Dashboard> possession = new ArrayList<Dashboard>();
		possession = dashboardMapper.possession();
		return possession;
	}
//	新しいアイテム
	public int newItem() {
		int newItem = dashboardMapper.newItem();
		return newItem;
	}
//	円グラフ
	public ArrayList<Dashboard> maruGraph() {
		ArrayList<Dashboard> marulist = new ArrayList<Dashboard>();
		marulist = dashboardMapper.maruGraph();
		return marulist;
	}

//	貸与現況
	public ArrayList<Dashboard> rslist() {

		ArrayList<Dashboard> rslist = new ArrayList<Dashboard>();
		rslist = dashboardMapper.rslist();

		return rslist;
	}


}
