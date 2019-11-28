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

	public Rental researchRental(int assetSeq);

	public List<CodeDetail> selectCodeDetail(String codeMasterId);

	public List<CodeDetail> selectStatusCode();

	public List<CodeDetail> selectCode(String codeMasterId);

	public Asset selectAsset(Rental rental );

	public List<Asset> selectAssetList(Asset asset);

	public int addRental(@Param ("itemList" ) List<Rental> itemList);

	public int changeStatus(@Param ("itemList" ) List<Rental> itemList);


	public int returnAsset(Rental rental);

	public int changeAssetStatus(Rental rental);

	public void changeAStatus(Rental rental);

	public int updateRental(Rental rental);




}
