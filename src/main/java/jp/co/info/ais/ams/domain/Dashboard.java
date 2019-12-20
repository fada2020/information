package jp.co.info.ais.ams.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Dashboard {
//	資産状況パラメータ
	private int typeCnt;
	private String typeName;
	private int newItem;
	private String kubunName;

//	資産貸出パラメータ
	private String kubunCode;
	private String statusCode;
}
