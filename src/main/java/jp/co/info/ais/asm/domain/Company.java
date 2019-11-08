package jp.co.info.ais.asm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString(exclude = "finished")
public class Company {
    private String cname;
    private String ccode;
    private int cnum;
}
