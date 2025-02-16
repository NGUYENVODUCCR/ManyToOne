package com.example.companycrudh2.service;

import com.example.companycrudh2.model.Company;
import com.example.companycrudh2.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(int id) {
        return companyRepository.findById(id).orElse(null);
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(int id, Company companyDetails) {
        Company company = companyRepository.findById(id).orElse(null);
        if (company != null) {
            company.setCompanyName(companyDetails.getCompanyName());
            company.setUsers(companyDetails.getUsers());
            return companyRepository.save(company);
        }
        return null;
    }

    public void deleteCompany(int id) {
        companyRepository.deleteById(id);
    }
}
