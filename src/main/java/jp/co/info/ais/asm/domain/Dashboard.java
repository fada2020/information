package jp.co.info.ais.asm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Dashboard {
//	資産現況パラメータ
	private int typeCnt;
	private String typeName;
	private int newItem;
	private String kubunName;

//	資産貸与パラメータ
	private String kubunCode;
	private String statusCode;
}
