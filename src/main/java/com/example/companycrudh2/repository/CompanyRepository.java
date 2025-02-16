package com.example.companycrudh2.repository;

import com.example.companycrudh2.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
