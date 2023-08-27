package ua.khai.controllers;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
import ua.khai.service.DonorService;
import ua.khai.util.WebUtil;

import java.io.IOException;
import java.util.Optional;


@Controller
@RequestMapping("/admin/donors")
public class AdminDonorController extends AbstractController{

    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("#", null, null),
            new HeaderName("donor name", "donorName", "name"),
            new HeaderName("phone", "phone", "phone"),
            new HeaderName("address", "address", "address"),
            new HeaderName("contact person", "contactPerson", "contact"),
            new HeaderName("delete", null, null),
            new HeaderName("add", null, null)
    };

    @Autowired
    private final DonorService donorService;

    public AdminDonorController(DonorService donorService) {
        this.donorService = donorService;
    }


    @GetMapping
    public String findAll(Model model, WebRequest webRequest) throws IOException {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(webRequest);
        DataTableResponse<Donor> response  = donorService.findAll(dataTableRequest);
       initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/donors/all");
        model.addAttribute("createNew", "/admin/donors/new");
        model.addAttribute("cardHeader", "All Donors");
        return "pages/admin/donor/donor_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "admin/donors");
    }

    @GetMapping("/new")
    public String redirectToNewDonorPage(Model model) {
        model.addAttribute("donor", new Donor());
        return "pages/admin/donor/donor_new";
    }

    @PostMapping("/create")
    public String createNewDonor(RedirectAttributes attributes, @ModelAttribute("donor") Donor dto) {
        donorService.create(dto);
        return "redirect:/admin/donors";
    }

    @GetMapping("/delete{id}")
    public String delete(@PathVariable @Min(value = 1, message = "id can not be zero") Long id) {
        donorService.delete(id);
        return "redirect:/admin/donors";
    }
}
