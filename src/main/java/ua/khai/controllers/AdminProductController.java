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

import ua.khai.entity.Product;

import ua.khai.service.ProductService;

import ua.khai.util.WebUtil;

import java.io.IOException;
import java.util.Optional;


@Controller
@RequestMapping("/admin/products")
public class AdminProductController extends AbstractController{

    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("#", null, null),
            new HeaderName("image", null, null),
            new HeaderName("product name", "productName", "name"),
            new HeaderName("size", "size", "size"),
            new HeaderName("weight", "weight", "weight"),
            new HeaderName("type", "type", "type"),
            new HeaderName("count", "count", "count"),
            new HeaderName("details", null, null),
            new HeaderName("delete", null, null),
            new HeaderName("add", null, null)
    };

    @Autowired
    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public String findAll(Model model, WebRequest webRequest) throws IOException {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(webRequest);
        DataTableResponse<Product> response  = productService.findAll(dataTableRequest);
       initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/products/all");
        model.addAttribute("createNew", "/admin/products/new");
        model.addAttribute("cardHeader", "All Products");
        return "pages/admin/product/product_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "admin/products");
    }

    @GetMapping("/new")
    public String redirectToNewBookPage(Model model) {
        model.addAttribute("product", new Product());
        return "pages/admin/product/product_new";
    }

    @PostMapping("/create")
    public String createNewProduct(RedirectAttributes attributes, @ModelAttribute("product") Product dto) {
        productService.create(dto);
        return "redirect:/admin/products";
    }

    @GetMapping("/delete{id}")
    public String delete(@PathVariable @Min(value = 1, message = "id can not be zero") Long id) {
        productService.delete(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/details{id}")
    public String details(@PathVariable @NotBlank(message = "id can not be empty") String id, Model model) {

            Long productId = Long.parseLong(id);
            Optional<Product> dto = productService.findById(productId);
            model.addAttribute("product", dto.get());
            return "pages/admin/product/product_details";

    }

}
