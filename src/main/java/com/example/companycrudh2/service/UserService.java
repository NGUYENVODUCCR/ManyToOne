package com.example.companycrudh2.service;

import com.example.companycrudh2.model.Company;
import com.example.companycrudh2.model.UserDemo;
import com.example.companycrudh2.repository.UserDemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDemoRepository userDemoRepository;

    @Autowired
    private CompanyService companyService; // Để lấy Company khi cập nhật

    /**
     * Lấy danh sách tất cả UserDemo
     */
    public List<UserDemo> getAllUsers() {
        return userDemoRepository.findAll();
    }

    /**
     * Lấy danh sách UserDemo theo Company ID
     */
    public List<UserDemo> getUsersByCompanyId(int companyId) {
        return userDemoRepository.findByCompanyId(companyId);
    }

    /**
     * Lấy UserDemo theo ID
     */
    public UserDemo getUserById(int id) {
        return userDemoRepository.findById(id).orElse(null);
    }

    /**
     * Lưu UserDemo vào Database
     */
    public UserDemo saveUser(UserDemo user) {
        return userDemoRepository.save(user);
    }

    /**
     * Cập nhật thông tin UserDemo (bao gồm cả công ty mới)
     */
    public UserDemo updateUser(int id, UserDemo userDetails, int companyId) {
        UserDemo existingUser = userDemoRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setFirstName(userDetails.getFirstName());
            existingUser.setLastName(userDetails.getLastName());

            // Cập nhật Company nếu khác null
            Company newCompany = companyService.getCompanyById(companyId);
            if (newCompany != null && !newCompany.equals(existingUser.getCompany())) {
                existingUser.setCompany(newCompany);
            }

            return userDemoRepository.save(existingUser);
        }
        return null;
    }

    /**
     * Xóa UserDemo theo ID
     */
    public void deleteUser(int id) {
        userDemoRepository.deleteById(id);
    }
}
