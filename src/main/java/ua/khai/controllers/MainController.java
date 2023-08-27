package ua.khai.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.khai.type.RoleType;
import ua.khai.util.SecurityUtil;

@Controller
public class MainController {

    @GetMapping("/")
    private String main(Model model) {
        if (SecurityUtil.hasRole(RoleType.ROLE_ADMIN.name())) {
            return "redirect:/admin/dashboard";
        }
        if (SecurityUtil.hasRole(RoleType.ROLE_PERSONAL.name())) {
            System.out.println(SecurityUtil.getUsername());
            return "redirect:/personal/dashboard";
        }
        return "redirect:/login";
    }
}
