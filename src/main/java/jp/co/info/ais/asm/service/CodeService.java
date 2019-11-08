package jp.co.info.ais.asm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.asm.domain.Company;
import jp.co.info.ais.asm.mapper.CompanyMapper;

@Service
public class CodeService {

	@Autowired
	private CompanyMapper companyMapper;

	public Company selectCode(String cname) {
		return companyMapper.select(cname);
	}

	public void insert(Company company) {
		companyMapper.insert(company);
	}

}
