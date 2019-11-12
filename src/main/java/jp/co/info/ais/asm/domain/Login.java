package jp.co.info.ais.asm.domain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString

public class Login {
	String emp_id;
	String emp_pass;
	String emp_name;
	String emp_organization;
	String emp_position;

}
