package com.example.companycrudh2.repository;

import com.example.companycrudh2.model.UserDemo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserDemoRepository extends JpaRepository<UserDemo, Integer> {
    List<UserDemo> findByCompanyId(int companyId); // Lấy UserDemo theo companyId
}
