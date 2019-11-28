package jp.co.info.ais.ams.domain;

import java.util.ArrayList;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Rental {

	//貸与情報管理の基本
	private int rentalSeq;
	private int assetSeq;
	private String rentalNo;

	private String rentalDay;

	private String rentalUserId;
	private String purpose;
	private String speciality;
	private String applicantId;

	private String returnDay;

	private String returnUserId;

	private String returnPeriod;
	private String statusCode;

	private String storageLocation;

	private String bpName;

	//資産マスタから持ち込む
	private String assetNumber;
	private String codeDetailId;
	private String codeDetailName;
	private String kubunCode;
	private String item2;

	//ページングする為のもの
	private int length = 10;
	private int start;

	private String rentalDayS;
	private String rentalDayE;

	private String insertId;
	private Date insertDate;
	private String updateId;
	private Date updateDate;

	private ArrayList<String>list;
}
