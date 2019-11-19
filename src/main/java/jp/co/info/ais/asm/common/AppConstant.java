package jp.co.info.ais.asm.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties("constant")
@Getter @Setter
public class AppConstant {
	String companyCode;
}
