package com.example.companycrudh2.controller;

import com.example.companycrudh2.model.Company;
import com.example.companycrudh2.service.CompanyService;
import com.example.companycrudh2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    // Hiển thị danh sách UserDemo của một Company
    @GetMapping("/{id}/users")
    public String viewUsersByCompany(@PathVariable int id, Model model) {
        Company company = companyService.getCompanyById(id);
        if (company == null) {
            return "redirect:/companies"; // Nếu không tìm thấy công ty, quay về danh sách
        }
        model.addAttribute("company", company);
        model.addAttribute("users", userService.getUsersByCompanyId(id));
        return "list-users-by-company"; // Giao diện danh sách users theo Company
    }

    // Hiển thị danh sách công ty
    @GetMapping
    public String listCompanies(Model model) {
        model.addAttribute("companies", companyService.getAllCompanies());
        return "company-list";
    }

    // Form thêm công ty mới
    @GetMapping("/add")
    public String addCompanyForm(Model model) {
        model.addAttribute("company", new Company());
        return "company-add";
    }

    // Lưu công ty mới
    @PostMapping("/add")
    public String saveCompany(@ModelAttribute("company") Company company) {
        companyService.createCompany(company);
        return "redirect:/companies";
    }

    // Form chỉnh sửa công ty
    @GetMapping("/edit/{id}")
    public String editCompanyForm(@PathVariable int id, Model model) {
        Company company = companyService.getCompanyById(id);
        if (company == null) {
            return "redirect:/companies"; // Nếu không có công ty, tránh lỗi NullPointerException
        }
        model.addAttribute("company", company);
        return "company-edit";
    }

    // Cập nhật công ty
    @PostMapping("/edit/{id}")
    public String updateCompany(@PathVariable int id, @ModelAttribute("company") Company company) {
        companyService.updateCompany(id, company);
        return "redirect:/companies";
    }

    // Xóa công ty
    @GetMapping("/delete/{id}")
    public String deleteCompany(@PathVariable int id) {
        companyService.deleteCompany(id);
        return "redirect:/companies";
    }

}
