package jp.co.info.ais.ams.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CodeDetail {
	//コードID
	private String codeMasterId;
	//詳細コードID
	private String codeDetailId;
	//詳細コードネーム
	private String codeDetailName;
	private String item1;
	private String item2;
	private String item3;
	private String item4;
	private String item5;

	private int orderBy;
	//使用未使用
	private String useFlag;

	//ページング
	private int length = 10;
	private int start;
	private int checkId;

	public CodeDetail() {}

	public CodeDetail(String codeMasterId, String codeDetailId, String item1, String useFlag) {
		super();
		this.codeMasterId = codeMasterId;
		this.codeDetailId = codeDetailId;
		this.item1 = item1;
		this.useFlag = useFlag;
	}

}
