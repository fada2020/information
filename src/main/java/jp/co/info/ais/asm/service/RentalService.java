package jp.co.info.ais.asm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.asm.domain.Asset;
import jp.co.info.ais.asm.domain.CodeDetail;
import jp.co.info.ais.asm.domain.ProductKind;
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

	public List<Rental> getSelectProduct(String psNum)throws Exception{

		return rentalMapper.getSelectProduct(psNum);
	}

	public Map<String, String> writeProduct(String realCode)throws Exception {

		return rentalMapper.writeProduct(realCode);
	}

	public List<ProductKind> searchCode()throws Exception {

		return rentalMapper.searchCode();
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

	public List<Asset> selectAsset() throws Exception  {
		// TODO 自動生成されたメソッド・スタブ
		return rentalMapper.selectAsset();
	}


}
