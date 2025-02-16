package com.example.companycrudh2.controller;

import com.example.companycrudh2.model.Company;
import com.example.companycrudh2.model.UserDemo;
import com.example.companycrudh2.service.CompanyService;
import com.example.companycrudh2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    // Hiển thị form thêm UserDemo
    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new UserDemo());
        model.addAttribute("companies", companyService.getAllCompanies());
        return "add-user";
    }

    // Xử lý thêm UserDemo
    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") UserDemo userDemo) {
        userService.saveUser(userDemo);
        return "redirect:/users/add";
    }

    // Hiển thị danh sách tất cả UserDemo
    @GetMapping("/list")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "list-users";
    }

    // Hiển thị form sửa UserDemo
    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable int id, Model model) {
        UserDemo user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("companies", companyService.getAllCompanies()); // Danh sách Company
            return "edit-user"; // Giao diện chỉnh sửa UserDemo
        }
        return "redirect:/users/list";
    }

    // Xử lý cập nhật UserDemo (bao gồm Company)
    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable int id,
                             @ModelAttribute UserDemo userDemo,
                             @RequestParam("companyId") int companyId) {
        userService.updateUser(id, userDemo, companyId);
        return "redirect:/users/list";
    }

    // Xóa công ty
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/users/list";
    }
}
