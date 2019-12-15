package jp.co.info.ais.ams.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import jp.co.info.ais.ams.domain.Dashboard;


@Mapper
public interface DashboardMapper {

	public ArrayList<Dashboard> possession();	// 資産種別保有数量

	public int newItem();						// 今月購入した資産数量

	public ArrayList<Dashboard> rslist();		// 主要ハードウェアの貸与状況

	public int kosyouItem();					// 故障した資産数

}
