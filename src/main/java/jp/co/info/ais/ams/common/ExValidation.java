package jp.co.info.ais.ams.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@ConfigurationProperties("validation")
@Getter
@Setter
@ToString
public class ExValidation {

	private Pattern pattern;
	private Matcher matcher;
	private final static String EXTENSION_PATTERN = "/^[ぁ-んァ-ヶー一-龠]+$/u";

	public ExValidation() {
		pattern = Pattern.compile(EXTENSION_PATTERN);
	}

	public boolean validate(final String target) {
		matcher = pattern.matcher(target);
		return matcher.matches();
	}
}
