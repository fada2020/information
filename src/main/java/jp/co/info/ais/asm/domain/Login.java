package jp.co.info.ais.asm.domain;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Login {
	private String empId;
	private String empName;
	private String passwd;
	private String organizationCode;
	private String useFlag;
	private String createUser;
	private Date createDate;
	private String updateUser;
	private Date updateDate;


}
