package com.example.project.controllers;

import com.example.project.services.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String adminHomePage() {
        return "admin/admin";
    }

    @GetMapping("/admins")
    public String admins() {
        return "admin/admins";
    }

}
