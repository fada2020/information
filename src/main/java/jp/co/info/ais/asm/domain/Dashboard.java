package jp.co.info.ais.asm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Dashboard {

	private int typeCnt;
	private int newItem;
	private String hardTop;
	private int hardPercent;
	private String softTop;
	private int softPercent;

	private String itemName;
	private String state;
	private int itemCnt;
}
