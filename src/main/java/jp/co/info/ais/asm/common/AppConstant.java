package jp.co.info.ais.asm.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties("constant")
@Getter @Setter
public class AppConstant {
	String companyCode;
	// ダッシュボード貸与現況のリスト名称
	public final static String fixed_rent_list01 = "Desktop";
	public final static String fixed_rent_list02 = "Notebook";
	public final static String fixed_rent_list03 = "Tablet";
	public final static String fixed_rent_list04 = "Mobile";
	public final static String fixed_rent_list05 = "Monitor";
	public final static String fixed_rent_list06 = "Keyboard";
	public final static String fixed_rent_list07 = "Mouse";

}
