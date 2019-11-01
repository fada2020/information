package jp.co.info.ais.asm.common;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Page<T> {
	int start;

    int length;

    int recordsTotal;

    int recordsFiltered;

    @NotNull
    Search search;

    @NotNull
    List<Column> columns;

	List<T> data;
}
