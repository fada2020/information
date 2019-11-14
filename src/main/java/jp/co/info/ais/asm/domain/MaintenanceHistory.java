package jp.co.info.ais.asm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MaintenanceHistory {
	int historySeq;
	int assetSeq;
	String implementationDate;
	String implementationDetail;
	String repId;
	String approverId;

	String insertId;
	String updateId;

}
