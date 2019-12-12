package jp.co.info.ais.ams.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import jp.co.info.ais.ams.domain.Dashboard;


@Mapper
public interface DashboardMapper {

	public ArrayList<Dashboard> possession();	// 資産種別保有数量

	public int newItem();						// 今月購入した装置数量

	public ArrayList<Dashboard> rslist();		// 主要ハードウェアの貸与現況

	public int kosyouItem();					// 故障した装置数

}
