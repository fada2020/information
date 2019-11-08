package jp.co.info.ais.asm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.asm.domain.ProductKind;
import jp.co.info.ais.asm.domain.Rental;
import jp.co.info.ais.asm.mapper.RentalMapper;

@Service
public class RentalService {


	@Autowired
	RentalMapper rentalMapper;

	public  List<Rental> select(Rental rental)throws Exception {



		return rentalMapper.select(rental);
	}

	public int selectCount(Rental rental)throws Exception {

		return rentalMapper.selectCount(rental);
	}

	public List<Map<String, String>> getSelectData(String pcNum)throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return rentalMapper.getSelectData(pcNum);
	}

	public List<Map<String, String>> getSelectProduct(String psNum)throws Exception{
		// TODO 自動生成されたメソッド・スタブ
		return rentalMapper.getSelectProduct(psNum);
	}

	public Map<String, String> writeProduct(String realCode)throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return rentalMapper.writeProduct(realCode);
	}

	public List<ProductKind> searchCode()throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return rentalMapper.searchCode();
	}


}
