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

		private String codeMasterId;//コードID
		private String codeMasterName;//コードネーム
		private String remarks;//
		private int useFlag;//使用未使用

		int length;//長さ
		int start;
}
