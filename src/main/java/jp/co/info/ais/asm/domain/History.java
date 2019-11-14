package jp.co.info.ais.asm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class History {

	String assetNumber;
	String maker;
	String model;


	String statusCode = "000";
	String rentNo;
	String purpose;
	String storageLocation;
	String applicant;
	String rentUserId;
	String bpName;
	String rentalDayS;
	String rentalDayE;
	String returnDayS;
	String returnDayE;

	int length = 10;
	int start;
}