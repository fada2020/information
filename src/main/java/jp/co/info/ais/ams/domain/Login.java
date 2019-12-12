package jp.co.info.ais.ams.domain;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Login {
	//社員番号
	private String empId;
	//氏名
	private String empName;
	//パスワード
	private String passwd;
	//所属コード
	private String organizationCode;
	//使用と未使用チェック
	private String useFlag;
	//ユーザー生成
	private String createUser;
	//生成日数
	private Date createDate;
	//修正ユーザー
	private String updateUser;
	//修正日数
	private Date updateDate;



}
