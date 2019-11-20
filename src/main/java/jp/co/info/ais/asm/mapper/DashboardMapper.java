package jp.co.info.ais.asm.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import jp.co.info.ais.asm.domain.Dashboard;


@Mapper
public interface DashboardMapper {

	public ArrayList<Dashboard> possession();

	public int newItem();

	public ArrayList<Dashboard> rslist();

}
