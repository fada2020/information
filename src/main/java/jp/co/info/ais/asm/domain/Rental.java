package jp.co.info.ais.asm.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Rental {

	private int pcNum;
	private int psNum;
	private String pcName;
	private String pcCode;
	private int pCode;
	private String pName;

	private int sNumber;
	private String state;

	private String assetNumber;
	private String storageLocation;

	private String rentNumber;
	@DateTimeFormat
	private Date rentDay;
	@DateTimeFormat
	private Date renturnDay;
	private String rentUser;
	private String renturnUser;
	private String applicant;
	private String purpose;
	private String rentSpeciality;
	@DateTimeFormat
	private Date returnPeriod;
	private int length;
	private int start;

}
