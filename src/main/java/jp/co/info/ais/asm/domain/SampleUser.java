package jp.co.info.ais.asm.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@AllArgsConstructor
@ToString
public class SampleUser {
    private String username;
    private String mailaddress;
}
