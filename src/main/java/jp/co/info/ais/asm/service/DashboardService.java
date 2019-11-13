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

	public ArrayList<Dashboard> rslist() {

		ArrayList<Dashboard> rslist = new ArrayList<Dashboard>();
		rslist = dashboardMapper.rslist();

		return rslist;
	}

	public ArrayList<Dashboard> possession() {
		ArrayList<Dashboard> possession = new ArrayList<Dashboard>();
		possession = dashboardMapper.possession();

		return possession;
	}

}
