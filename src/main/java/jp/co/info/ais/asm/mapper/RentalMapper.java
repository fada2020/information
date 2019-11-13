package jp.co.info.ais.asm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import jp.co.info.ais.asm.domain.CodeDetail;
import jp.co.info.ais.asm.domain.ProductKind;
import jp.co.info.ais.asm.domain.Rental;
import jp.co.info.ais.asm.domain.StatusCode;

@Mapper
public interface RentalMapper {

	public List<Rental> selectAll(Rental rental)throws Exception;

	public int selectCount(Rental rental) throws Exception;

	public List<Rental> getSelectData(int pcNum)throws Exception;

	public List<Rental> getSelectProduct(String psNum)throws Exception;

	public Map<String, String> writeProduct(String realCode)throws Exception;

	public List<ProductKind> searchCode()throws Exception;

	public Rental researchRental(String assetNumber)throws Exception;

	public List<CodeDetail> selectCodeDetail()throws Exception;

	public List<StatusCode> selectStatusCode()throws Exception;


}
