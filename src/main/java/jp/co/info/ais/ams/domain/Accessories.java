package jp.co.info.ais.ams.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Accessories {
	int accessoriesSeq;
	int assetSeq;
	String itemName;
	String itemSpec;
	int itemQuantity;
	String remarks;

	String insertId;
	String updateId;
}
