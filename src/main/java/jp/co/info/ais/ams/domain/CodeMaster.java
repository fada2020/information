package jp.co.info.ais.ams.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 *
 * @author AIS191101
 *
 */
@Getter @Setter
@ToString
public class CodeMaster {
		//コードID
		private String codeMasterId;
		//コードネーム
		private String codeMasterName;

		private String remarks;

		//使用未使用
		private String useFlag;

		//長さ
		int length;
		int start;
}
