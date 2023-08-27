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
import ua.khai.entity.Product;
import ua.khai.entity.Requirements;
import ua.khai.entity.Shipments;
import ua.khai.entity.user.Personal;
import ua.khai.service.PersonalCrudService;
import ua.khai.service.ProductService;
import ua.khai.service.RequirementsService;
import ua.khai.service.ShipmentsService;
import ua.khai.util.WebUtil;

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/admin/shipments")
public class AdminShipmentsController extends AbstractController{

    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("#", null, null),
            new HeaderName("admin", "admin", "admin"),
            new HeaderName("personal", "personal", "personal"),
            new HeaderName("product", "product", "product"),
            new HeaderName("count", "count", "count"),
            new HeaderName("date", "date", "create"),
            new HeaderName("delete", null, null)
    };

    @Autowired
    private final ShipmentsService shipmentsService;
    private final PersonalCrudService personalCrudService;
    private final ProductService productService;

    public AdminShipmentsController(ShipmentsService shipmentsService, PersonalCrudService personalCrudService, ProductService productService) {
        this.shipmentsService = shipmentsService;
        this.personalCrudService = personalCrudService;
        this.productService = productService;
    }


    @GetMapping
    public String findAll(Model model, WebRequest webRequest) throws IOException {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(webRequest);
        DataTableResponse<Shipments> response  = shipmentsService.findAll(dataTableRequest);
       initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/shipments/all");
        model.addAttribute("createNew", "/admin/shipments/new");
        model.addAttribute("cardHeader", "All Shipments");
        return "pages/admin/shipments/shipments_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "admin/shipments");
    }

    @GetMapping("/new")
    public String redirectToNewDonorPage(Model model) {
        List<Personal> personals = personalCrudService.findAll();
        List<Product> productList = productService.findAll();
        model.addAttribute("shipment", new Shipments());
        model.addAttribute("personals", personals);
        model.addAttribute("products", productList);
        return "pages/admin/requirements/requirements_new";
    }

    @PostMapping("/create")
    public String createNewDonor(RedirectAttributes attributes, @ModelAttribute("shipment") Shipments dto) {
        shipmentsService.create(dto);
        return "redirect:/admin/shipments";
    }

    @GetMapping("/delete{id}")
    public String delete(@PathVariable @Min(value = 1, message = "id can not be zero") Long id) {
        shipmentsService.delete(id);
        return "redirect:/admin/shipments";
    }
}
