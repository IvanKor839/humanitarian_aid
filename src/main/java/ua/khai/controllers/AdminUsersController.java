package ua.khai.controllers;

import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.khai.datatable.DataTableRequest;
import ua.khai.datatable.DataTableResponse;
import ua.khai.entity.Product;
import ua.khai.entity.user.Admin;
import ua.khai.entity.user.Personal;
import ua.khai.entity.user.User;
import ua.khai.service.AdminService;
import ua.khai.service.PersonalCrudService;
import ua.khai.service.UserService;
import ua.khai.util.WebUtil;

import java.io.IOException;
import java.util.Optional;

import static ua.khai.type.RoleType.ROLE_ADMIN;


@Controller
@RequestMapping("/admin/users")
public class AdminUsersController extends AbstractController{

    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("#", null, null),
            new HeaderName("role", "role", "roleType"),
            new HeaderName("enabled", "enabled", "enabled"),
            new HeaderName("email", "email", "email"),
            new HeaderName("password", "password", "password"),
            new HeaderName("details", null, null),
            new HeaderName("ban", null, null)
    };

    @Autowired
    private final UserService userService;
    private final AdminService adminService;
    private final PersonalCrudService personalCrudService;

    public AdminUsersController(UserService userService, AdminService adminService, PersonalCrudService personalCrudService) {
        this.userService = userService;
        this.adminService = adminService;
        this.personalCrudService = personalCrudService;
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) throws IOException {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(webRequest);
        DataTableResponse<User> response  = userService.findAll(dataTableRequest);
       initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/users/all");
        model.addAttribute("createNew", "/admin/users/new");
        model.addAttribute("cardHeader", "All Users");
        return "pages/admin/user/user_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "admin/users");
    }

    @GetMapping("/update{id}")
    public String update(@PathVariable  Long id) {
        Optional<User> user = userService.findById(id);
        if (user.get().getEnabled()){
            user.get().setEnabled(false);
        }else {
            user.get().setEnabled(true);
        }
        userService.update(user.get());
        return "redirect:/admin/users";
    }
    @GetMapping("/details{id}")
    public String details(@PathVariable @NotBlank(message = "id can not be empty") String id, Model model) {

        Long userId = Long.parseLong(id);
        Optional<User> user = userService.findById(userId);
        if (user.get().getRoleType().equals(ROLE_ADMIN)){
            Admin admin = adminService.findById(userId).get();
            model.addAttribute("user", admin);
        }else {
            Personal personal = personalCrudService.findById(userId).get();
            model.addAttribute("user", personal);
        }

        return "pages/admin/user/user_details";
    }
}
