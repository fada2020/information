package jp.co.info.ais.ams.common;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Column {
	String data;
	@NotNull
	Search search;
}