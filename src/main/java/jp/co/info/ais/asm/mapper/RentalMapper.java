package jp.co.info.ais.asm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.info.ais.asm.domain.Asset;
import jp.co.info.ais.asm.domain.CodeDetail;
import jp.co.info.ais.asm.domain.Rental;

@Mapper
public interface RentalMapper {

	//一覧ページに表示
	public List<Rental> selectAll(Rental rental);

	//一覧ページに表示する全体のデータ数
	public int selectCount(Rental rental);

	//データコードをセレクト
	public List<CodeDetail> getSelectCodeData(String codeDetailName);

	public Rental researchRental(int assetSeq);

	public List<CodeDetail> selectCodeDetail();

	public List<CodeDetail> selectStatusCode();

	public List<CodeDetail> selectCode();

	public Asset selectAsset(String number );

	public List<Asset> selectAssetList(String selectedItem);

	public int addRental(@Param ("itemList" ) List<Rental> itemList);

	public int changeStatus(@Param ("itemList" ) List<Rental> itemList);


	public int returnAsset(Rental rental);

	public int changeAssetStatus(String assetNumber);

	public void changeAStatus(int assetSeq);

	public int updateRental(Rental rental);



}
