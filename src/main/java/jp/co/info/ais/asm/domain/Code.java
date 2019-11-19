package jp.co.info.ais.asm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Code {
	//code detail
	private String codeMasterId;
	private String codeDetailId;
	private String codeDetailName;
	private String item1;
	private String item2;
	private String item3;
	private String item4;
	private String item5;
	private int orderBy;
	private String deleteFlag;

	//code master
	//private String codeMasterId;
	private String codeMasterName;
	private String remarks;
	private int useFlag;


}
