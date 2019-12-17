package jp.co.info.ais.ams.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties("constant")
@Getter @Setter
public class AppConstant {
	String companyCode;
	// ダッシュボード貸与状況のリスト名称
	public final static String fixed_rent_list01 = "Desktop";
	public final static String fixed_rent_list02 = "Notebook";
	public final static String fixed_rent_list03 = "Tablet";
	public final static String fixed_rent_list04 = "Mobile";
	public final static String fixed_rent_list05 = "Monitor";
	public final static String fixed_rent_list06 = "Keyboard";
	public final static String fixed_rent_list07 = "Mouse";
	//保管状態
	public final static String STATE_STORAGE = "01";
	//貸与状態
	public final static String STATE_RENTAL = "02";
	//故障状態
	public final static String STATE_BREAKDOWN = "03";
	//廃棄状態
	public final static String STATE_DISUSE = "04";
	//STATUSCODE
	public final static String MASTER_STATE ="001";
	//KUBUNCODE
	public final static String MASTER_DETAIL ="002";
	//KUBUNKIND
	public final static String MASTER_CLASS= "003";
	//使用状態
	public final static String USE_CODE= "0";
	//未使用状態
	public final static String UNUSE_CODE= "1";

}
