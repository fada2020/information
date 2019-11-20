package jp.co.info.ais.asm.domain;

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

		private String codeMasterId;
		private String codeMasterName;
		private String remarks;
		private int useFlag;

}
