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


	int status = -1;
	String rentNo;
	String purpose;
	String storageLocation;
	String applicant;
	String rentUserId;
	String bpName;
	String rentalDay;
	String returnDay;

	int length = 10;
	int start;
}