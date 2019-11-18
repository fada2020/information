package jp.co.info.ais.asm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.asm.domain.Asset;
import jp.co.info.ais.asm.domain.CodeDetail;
import jp.co.info.ais.asm.domain.Rental;
import jp.co.info.ais.asm.domain.StatusCode;
import jp.co.info.ais.asm.mapper.RentalMapper;

@Service
public class RentalService {


	@Autowired
	RentalMapper rentalMapper;

	public  List<Rental> selectAll(Rental rental)throws Exception {



		return rentalMapper.selectAll(rental);
	}

	public int selectCount(Rental rental)throws Exception {

		return rentalMapper.selectCount(rental);
	}

	public List<CodeDetail> getSelectCodeData(String codeDetailName)throws Exception {

		return rentalMapper.getSelectCodeData(codeDetailName);
	}






	public Rental researchRental(String assetNumber)throws Exception  {

		return rentalMapper.researchRental(assetNumber);
	}
	public List<CodeDetail> selectCodeDetail()throws Exception  {

		return rentalMapper.selectCodeDetail();
	}

	public List<StatusCode> selectStatusCode()throws Exception  {

		return rentalMapper.selectStatusCode();
	}

	public List<CodeDetail> selectCode()throws Exception  {

		return rentalMapper.selectCode();
	}

	public Asset selectAsset(String number ) throws Exception  {

		return rentalMapper.selectAsset(number);
	}

	public List<Asset> selectAssetList(String selectedItem) throws Exception  {

		return rentalMapper.selectAssetList(selectedItem);
	}

	public int addRental(List<Rental> rentalList) throws Exception {

		return rentalMapper.addRental(rentalList);

	}

	public int changeStatus(List<Rental> rentalList) throws Exception {

		return rentalMapper.changeStatus(rentalList);

	}

	public int returnAsset(Rental rental) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return rentalMapper.returnAsset(rental);
	}

	public int changeAssetStatus(int assetSeq) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return rentalMapper.changeAssetStatus(assetSeq);
	}


}
