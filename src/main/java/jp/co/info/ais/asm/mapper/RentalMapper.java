package jp.co.info.ais.asm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.info.ais.asm.domain.Asset;
import jp.co.info.ais.asm.domain.CodeDetail;
import jp.co.info.ais.asm.domain.Rental;
import jp.co.info.ais.asm.domain.StatusCode;

@Mapper
public interface RentalMapper {

	//一覧ページに表示
	public List<Rental> selectAll(Rental rental) throws Exception;

	//一覧ページに表示する全体のデータ数
	public int selectCount(Rental rental) throws Exception;

	//データコードをセレクト
	public List<CodeDetail> getSelectCodeData(String codeDetailName) throws Exception;

	public Rental researchRental(int assetSeq) throws Exception;

	public List<CodeDetail> selectCodeDetail() throws Exception;

	public List<StatusCode> selectStatusCode() throws Exception;

	public List<CodeDetail> selectCode() throws Exception;

	public Asset selectAsset(String number ) throws Exception;

	public List<Asset> selectAssetList(String selectedItem)throws Exception;

	public int addRental(@Param ("itemList" ) List<Rental> itemList)throws Exception;

	public int changeStatus(@Param ("itemList" ) List<Rental> itemList)throws Exception;


	public int returnAsset(Rental rental)throws Exception;

	public int changeAssetStatus(String assetNumber)throws Exception;

	public int changeAStatus(int num1)throws Exception;

	public int updateRental(Rental rental);

}
