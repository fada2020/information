package jp.co.info.ais.asm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Rental {

	//貸与情報管理の基本
	private int rentalSeq;
	private int assetSeq;
	private String rental_no;
	private String rentalDay;
	private String rentalUserId;
	private String purpose;
	private String speciality;
	private String applicantId;

	private String renturnDay;
	private String renturnUserId;
	private String returnPeriod;
	private int statusCode;
	private String storageLocation;
	private String bpName;
	private String rentalNo;

	//資産マスタから持ち込む
	private String assetNumber;
	private String codeDetailId;
	private String codeDetailName;
	private String kubunCode;

	//ページングする為のもの
	private int length = 10;
	private int start;



}
