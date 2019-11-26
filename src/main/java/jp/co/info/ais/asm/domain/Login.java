package jp.co.info.ais.asm.domain;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Login {
	private String empId;//社員番号
	private String empName;//氏名
	private String passwd;//パスワード
	private String organizationCode;//所属コード
	private String useFlag;//使用と未使用チェック
	private String createUser;//ユーザー生成
	private Date createDate;//生成日数
	private String updateUser;//修正ユーザー
	private Date updateDate;//修正日数



}
