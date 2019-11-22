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
import jp.co.info.ais.asm.mapper.RentalMapper;

@Service
public class RentalService {

	@Autowired
	RentalMapper rentalMapper;

	public List<Rental> selectAll(Rental rental) {

		return rentalMapper.selectAll(rental);
	}

	public int selectCount(Rental rental) {

		return rentalMapper.selectCount(rental);
	}

	public List<CodeDetail> getSelectCodeData(String codeDetailName) {

		return rentalMapper.getSelectCodeData(codeDetailName);
	}

	public Rental researchRental(int assetSeq) {

		return rentalMapper.researchRental(assetSeq);
	}

	public List<CodeDetail> selectCodeDetail() {

		return rentalMapper.selectCodeDetail();
	}

	public List<CodeDetail> selectStatusCode() {

		return rentalMapper.selectStatusCode();
	}

	public List<CodeDetail> selectCode() {

		return rentalMapper.selectCode();
	}

	public Asset selectAsset(String number) {

		return rentalMapper.selectAsset(number);
	}

	public List<Asset> selectAssetList(String selectedItem) {

		return rentalMapper.selectAssetList(selectedItem);
	}

	@Transactional
	public int addRental(List<Rental> rentalList) {

		return rentalMapper.addRental(rentalList);

	}

	@Transactional
	public int changeStatus(List<Rental> rentalList) {

		return rentalMapper.changeStatus(rentalList);

	}

	@Transactional
	public void returnAsset(Rental rental, String applicantId, int assetSeq) {
		try {
			rental.setAssetSeq(assetSeq);
			rental.setApplicantId(applicantId);
			rental.setReturnUserId(applicantId);
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);
			Date currentTime = new Date();
			String mTime = mSimpleDateFormat.format(currentTime);
			rental.setReturnDay(mTime);
			int num = 0;
			num = rentalMapper.returnAsset(rental);
			if (num > 0) {
				rentalMapper.changeAStatus(rental.getAssetSeq());

			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("例外が発生しました。");
		}

	}

	@Transactional
	public int changeAssetStatus(String assetNumber) {

		return rentalMapper.changeAssetStatus(assetNumber);
	}

	@Transactional
	public void changeAStatus(int assetSeq) {

		rentalMapper.changeAStatus(assetSeq);
	}

	@Transactional
	public int updateRental(Rental rental, String updateId) {
		try {
			rental.setUpdateId(updateId);
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("例外が発生しました。");
		}
		return rentalMapper.updateRental(rental);
	}

}
