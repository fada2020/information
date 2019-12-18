package jp.co.info.ais.ams.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class History {

	String assetNumber;
	String makerName;
	String modelName;

	String statusCode = "00";
	String rentalNo;
	String purpose;
	String storageLocation;
	String applicantId;
	String rentalUserId;
	String bpName;
	String rentalDayS;
	String rentalDayE;
	String returnDayS;
	String returnDayE;
	String rentalSeq;
	String approver;
	int length = 10;
	int start;
}