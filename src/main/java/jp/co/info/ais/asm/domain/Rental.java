package jp.co.info.ais.asm.domain;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

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
	private String rentalNo;
	@NotEmpty @Valid @NotBlank(message = "日付を入力してください。")
	private String rentalDay;
	@NotEmpty @NotBlank(message = "使用者を入力してください。")
	private String rentalUserId;
	private String purpose;
	private String speciality;
	private String applicantId;

	private String returnDay;
	@NotEmpty @NotBlank(message = "ログインしてください。")
	private String returnUserId;
	@NotEmpty  @Valid @Future @NotBlank(message = "返却期間を入力してください。")
	private String returnPeriod;
	private int statusCode;
	@NotEmpty @NotBlank(message = "場所を入力してください。")
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
}
