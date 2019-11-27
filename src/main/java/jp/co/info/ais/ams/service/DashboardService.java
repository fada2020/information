package jp.co.info.ais.ams.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.ams.domain.Dashboard;
import jp.co.info.ais.ams.mapper.DashboardMapper;

@Service
public class DashboardService {

	@Autowired
	private DashboardMapper dashboardMapper;
	/**
	 * 保有現況数値
	 * @return ArrayList<Dashboard>
	 * @throws Exception
	 */
	public ArrayList<Dashboard> possession() throws Exception{
		ArrayList<Dashboard> possession = new ArrayList<Dashboard>();
		possession = dashboardMapper.possession();
		return possession;
	}

	/**
	 * 今月購入した装置
	 * @return int
	 * @throws Exception
	 */
	public int newItem() throws Exception {
		int newItem = dashboardMapper.newItem();
		return newItem;
	}

	/**
	 * 貸与現況
	 * @return ArrayList<Dashboard>
	 * @throws Exception
	 */
	public ArrayList<Dashboard> rslist() throws Exception {
		ArrayList<Dashboard> rslist = new ArrayList<Dashboard>();
		rslist = dashboardMapper.rslist();
		return rslist;
	}
}
