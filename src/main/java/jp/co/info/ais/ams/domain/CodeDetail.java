package jp.co.info.ais.ams.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class CodeDetail {

	private String codeMasterId;
	private String codeDetailId;
	private String codeDetailName;
	private String item1;
	private String item2;
	private String item3;
	private String item4;
}