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

	//貸与情報管理の基本
	private int rentalseq;
	private int assetseq;
	@DateTimeFormat
	private Date rentalDay;
	private String rentalUserId;
	private String purpose;
	private String speciality;
	private String applicantId;
	@DateTimeFormat
	private Date renturnDay;
	private String renturnUserId;
	private String returnPeriod;
	private int statusCode;
	private String storageLocation;
	private String bpName;

	//資産マスタから持ち込む
	private String assetNumber;
	private int codeDetailId;
	private String codeDetailName;
	private int kubunNum;


	//ページングする為のもの
	private int length;
	private int start;

}
