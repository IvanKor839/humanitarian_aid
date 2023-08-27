package ua.khai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.khai.datatable.DataTableRequest;
import ua.khai.datatable.DataTableResponse;
import ua.khai.entity.Shipments;
import ua.khai.entity.user.User;
import ua.khai.repository.UserRepository;
import ua.khai.service.ShipmentsService;
import ua.khai.util.WebUtil;

import java.io.IOException;
import java.security.Principal;


@Controller
@RequestMapping("/personal/shipments")
public class PersonalShipmentsController extends AbstractController{

    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("#", null, null),
            new HeaderName("product", "product", "product"),
            new HeaderName("count", "count", "count"),
            new HeaderName("date", "date", "create")
    };

    @Autowired
    private final ShipmentsService shipmentsService;
    private final UserRepository userRepository;

    public PersonalShipmentsController(ShipmentsService shipmentsService, UserRepository userRepository) {
        this.shipmentsService = shipmentsService;
        this.userRepository = userRepository;
    }


    @GetMapping
    public String findAll(Model model, WebRequest webRequest, Principal user) throws IOException {
        User user1 = userRepository.findByEmail(user.getName());
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(webRequest);
        DataTableResponse<Shipments> response  = shipmentsService.findAll(dataTableRequest);
       initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/personal/shipments/all");
        model.addAttribute("createNew", "/personal/shipments/new");
        model.addAttribute("cardHeader", "All Shipments");
        return "pages/personal/shipments/shipments_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "personal/shipments");
    }
}
