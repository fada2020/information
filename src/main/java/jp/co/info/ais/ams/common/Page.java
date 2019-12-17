package jp.co.info.ais.ams.common;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
//ページをセッティング
public class Page<T> {
	//スタートする位置
	int start;
	//一ページに表示するデータ数
    int length;
    //一ページに表示する件数のマックス
    int recordsTotal;
   //ページに表示する件数
    int recordsFiltered;
    //検索条件を入れるドメイン
    @NotNull
    Search search;
    //テーブルのカラム
    @NotNull
    List<Column> columns;
    //ジェネリック型
	List<T> data;
}
