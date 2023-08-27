package ua.khai.controllers;

import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.khai.datatable.DataTableRequest;
import ua.khai.datatable.DataTableResponse;
import ua.khai.entity.*;
import ua.khai.entity.user.Admin;
import ua.khai.entity.user.Personal;
import ua.khai.entity.user.User;
import ua.khai.repository.UserRepository;
import ua.khai.service.*;
import ua.khai.util.WebUtil;

import java.io.IOException;
import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/admin/requirements")
public class AdminRequirementsController extends AbstractController{

    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("#", null, null),
            new HeaderName("personal", "personal", "personal"),
            new HeaderName("product", "product", "product"),
            new HeaderName("count", "count", "count"),
            new HeaderName("status", "status", "status"),
            new HeaderName("create ship", "null", "null"),
            new HeaderName("delete", null, null),
            new HeaderName("add", null, null)
    };

    @Autowired
    private final RequirementsService requirementsService;
    private final ShipmentsService shipmentsService;
    private final PersonalCrudService personalCrudService;
    private final ProductService productService;
    private final UserRepository userRepository;

    public AdminRequirementsController(RequirementsService requirementsService, ShipmentsService shipmentsService, PersonalCrudService personalCrudService, ProductService productService, UserRepository userRepository) {
        this.requirementsService = requirementsService;
        this.shipmentsService = shipmentsService;
        this.personalCrudService = personalCrudService;
        this.productService = productService;
        this.userRepository = userRepository;
    }


    @GetMapping
    public String findAll(Model model, WebRequest webRequest) throws IOException {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(webRequest);
        DataTableResponse<Requirements> response  = requirementsService.findAll(dataTableRequest);
       initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/requirements/all");
        model.addAttribute("createNew", "/admin/requirements/new");
        model.addAttribute("cardHeader", "All Requirements");
        return "pages/admin/requirements/requirements_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "admin/requirements");
    }

    @GetMapping("/new")
    public String redirectToNewDonorPage(Model model) {
        List<Personal> personals = personalCrudService.findAll();
        List<Product> productList = productService.findAll();
        model.addAttribute("requirement", new Requirements());
        model.addAttribute("personals", personals);
        model.addAttribute("products", productList);
        return "pages/admin/requirements/requirements_new";
    }

    @PostMapping("/create")
    public String createNewDonor(RedirectAttributes attributes, @ModelAttribute("requirement") Requirements dto) {
        requirementsService.create(dto);
        return "redirect:/admin/requirements";
    }

    @GetMapping("/delete{id}")
    public String delete(@PathVariable @Min(value = 1, message = "id can not be zero") Long id) {
        requirementsService.delete(id);
        return "redirect:/admin/requirements";
    }

    @GetMapping("/ship{id}")
    public String createShip(@PathVariable @Min(value = 1, message = "id can not be zero") Long id, Principal userSpring) {
        Requirements requirements = requirementsService.findById(id).get();
        User user = userRepository.findByEmail(userSpring.getName());
        Shipments shipments = new Shipments();
        shipments.setAdmin((Admin) user);
        shipments.setCount(requirements.getCount());
        shipments.setProduct(requirements.getProduct());
        shipments.setPersonal(requirements.getPersonal());
        shipmentsService.create(shipments);
        requirementsService.delete(id);
        return "redirect:/admin/shipments";
    }
}
