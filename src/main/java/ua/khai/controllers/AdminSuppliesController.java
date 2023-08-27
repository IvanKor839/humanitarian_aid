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
import ua.khai.entity.Donor;
import ua.khai.entity.Product;
import ua.khai.entity.Supplies;
import ua.khai.service.DonorService;
import ua.khai.service.ProductService;
import ua.khai.service.SuppliesService;
import ua.khai.util.WebUtil;

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/admin/supplies")
public class AdminSuppliesController extends AbstractController{

    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("#", null, null),
            new HeaderName("donor", "donor", "donor"),
            new HeaderName("product", "product", "product"),
            new HeaderName("count", "count", "count"),
            new HeaderName("delete", null, null),
            new HeaderName("add", null, null)
    };

    @Autowired
    private final SuppliesService suppliesService;
    private final DonorService donorService;
    private final ProductService productService;

    public AdminSuppliesController(SuppliesService suppliesService, DonorService donorService, ProductService productService) {
        this.suppliesService = suppliesService;
        this.donorService = donorService;
        this.productService = productService;
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) throws IOException {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(webRequest);
        DataTableResponse<Supplies> response  = suppliesService.findAll(dataTableRequest);
       initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/supplies/all");
        model.addAttribute("createNew", "/admin/supplies/new");
        model.addAttribute("cardHeader", "All Supplies");
        return "pages/admin/supplies/supply_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "admin/supplies");
    }

    @GetMapping("/new")
    public String redirectToNewDonorPage(Model model) {
        List<Donor> donorList = donorService.findAll();
        List<Product> productList = productService.findAll();
        model.addAttribute("supply", new Supplies());
        model.addAttribute("donors", donorList);
        model.addAttribute("products", productList);
        return "pages/admin/supplies/supply_new";
    }

    @PostMapping("/create")
    public String createNewDonor(RedirectAttributes attributes, @ModelAttribute("supply") Supplies dto) {
        suppliesService.create(dto);
        return "redirect:/admin/supplies";
    }

    @GetMapping("/delete{id}")
    public String delete(@PathVariable @Min(value = 1, message = "id can not be zero") Long id) {
        suppliesService.delete(id);
        return "redirect:/admin/supplies";
    }
}
