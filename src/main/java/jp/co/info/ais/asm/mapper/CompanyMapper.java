package jp.co.info.ais.asm.mapper;

import org.apache.ibatis.annotations.Mapper;

import jp.co.info.ais.asm.domain.Company;

@Mapper
public interface CompanyMapper {

	void insert(Company company);

	Company select(String cname);

}
