package jp.co.info.ais.asm.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	public Rental researchRental(int assetSeq)throws Exception  {

		return rentalMapper.researchRental(assetSeq);
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
	@Transactional
	public int addRental(List<Rental> rentalList) throws Exception {

		return rentalMapper.addRental(rentalList);

	}
	@Transactional
	public int changeStatus(List<Rental> rentalList) throws Exception {

		return rentalMapper.changeStatus(rentalList);

	}
	@Transactional
	public void returnAsset(Rental rental) {

		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);
		Date currentTime = new Date();
		String mTime = mSimpleDateFormat.format(currentTime);
		rental.setReturnDay(mTime);
		int num = 0;
		num=rentalMapper.returnAsset(rental);
		if(num>0) {
			rentalMapper.changeAStatus(rental.getAssetSeq());

		}

	}
	@Transactional
	public int changeAssetStatus(String assetNumber) throws Exception {

		return rentalMapper.changeAssetStatus(assetNumber);
	}
	@Transactional
	public void changeAStatus(int assetSeq) throws Exception {

	rentalMapper.changeAStatus(assetSeq);
	}
	@Transactional
	public int updateRental(Rental rental) {

		return rentalMapper.updateRental(rental);
	}


}
