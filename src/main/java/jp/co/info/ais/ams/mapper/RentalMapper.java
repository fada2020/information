package jp.co.info.ais.ams.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.info.ais.ams.domain.Asset;
import jp.co.info.ais.ams.domain.CodeDetail;
import jp.co.info.ais.ams.domain.Rental;

@Mapper
public interface RentalMapper {

	//一覧ページに表示
	public List<Rental> selectAll(Rental rental);
	//一覧ページに表示する全体のデータ数
	public int selectCount(Rental rental);
	//データコードをセレクト
	public List<CodeDetail> getSelectCodeData(String codeDetailName);
	//単一の貸出情報を取り出す
	public Rental researchRental(int assetSeq);
	//メインページに見せるコードの詳細情報を取り出す
	public List<CodeDetail> selectCodeDetail(CodeDetail codeDetail);
	//メインページに見せる資産コードの詳細情報を取り出す
	public List<CodeDetail> selectStatusCode(CodeDetail codeDetail);
	//メインページに見せるHW,SWなどの情報を取り出す
	public List<CodeDetail> selectCode(CodeDetail codeDetail);
	//最後に選択してもらった資産情報を取り出す
	public Asset selectAsset(Rental rental );
	//選択してもらったコードを基にして該当する資産の情報を取り出す
	public List<Asset> selectAssetList(Asset asset);
	//リスト上にあるデータを取り込んで貸出データテーブルに書き込む
	public int addRental(@Param ("itemList" ) List<Rental> itemList);
	//リスト上にあるデータを取り込んで資産データテーブルのステータスを変える
	public int changeStatus(@Param ("itemList" ) List<Rental> itemList);
	//返却する為の情報を取り込んで貸出データテーブルと資産データテーブルの情報を変える
	public int returnAsset(Rental rental);
	//返却が出来たら資産の状態も変更する
	public int changeAssetStatus(Rental rental);
	//該当する資産のステータスの情報を変える
	public void changeAStatus(Rental rental);
	//アップデートする為のメソッド
	public int updateRental(Rental rental);
}
