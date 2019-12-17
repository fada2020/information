package jp.co.info.ais.ams.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter
@ToString
//ページにサーチを使ってコントローラとページの間にやり取りが出来る
public class Search {
	String value;
}
