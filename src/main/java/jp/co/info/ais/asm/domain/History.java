package jp.co.info.ais.asm.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class History {

	String maker;
	String model;

	String assetNumber;
	int status = -1;
	String rentNumber;
	String purpose;
	String storageLocation;
	String applicant;
	String rentuser;
	String bpPartner;
	Date rentday;
	Date returnday;

	int length = 10;
	int start;
}