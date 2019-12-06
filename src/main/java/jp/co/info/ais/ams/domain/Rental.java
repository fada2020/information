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

	//貸与情報管理のPrimaryKey
	private int rentalSeq;
	//IT資産管理のPrimaryKey
	private int assetSeq;
	//貸与ナンバー
	private String rentalNo;
	//貸与日
	private String rentalDay;
	//使用者
	private String rentalUserId;
	//物を借りる目的
	private String purpose;
	//物を借りるとき特記事項
	private String speciality;
	//貸出者
	private String applicantId;
	//返却日
	private String returnDay;
	//返却者
	private String returnUserId;
	//返却予定日
	private String returnPeriod;
	//状態コード
	private String statusCode;
	//使う場所
	private String storageLocation;
	//BP社名
	private String bpName;
	//最初入力する人
	private String insertId;
	//最初入力する日付
	private Date insertDate;
	//アップデートする人
	private String updateId;
	//アップデートする日付
	private Date updateDate;
	//返却予定日の検索スタート
	private String rentalDayS;
	//返却予定日の検索終わり
	private String rentalDayE;


	//IT資産ナンバー
	private String assetNumber;
	//コード詳細ID
	private String codeDetailId;
	//コード詳細名
	private String codeDetailName;
	//IT資産の区分コード
	private String kubunCode;
	//IT資産の資産名
	private String item2;

	//ページに10個ずつ表す
	private int length = 10;
	//ページのスタート
	private int start;

	private ArrayList<Integer>list;
	//サービスで使う
	public Rental (int assetSeq,String statusCode,String updateId) {
		this.assetSeq=assetSeq;
		this.statusCode=statusCode;
		this.updateId=updateId;
	}
	//サービスで使う
	public Rental (String assetNumber,String statusCode,String updateId) {
		this.assetNumber=assetNumber;
		this.statusCode=statusCode;
		this.updateId=updateId;
	}
}
