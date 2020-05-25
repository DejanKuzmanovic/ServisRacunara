package com.servisracunara.pmf.controller.page;

import com.servisracunara.pmf.dto.RequestDTO;
import com.servisracunara.pmf.dto.UserDTO;
import com.servisracunara.pmf.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("page")
public class AdminPageController {

    private static final String ADMIN_PAGE_VIEW_NAME = "admin-console";

    private RequestService requestService;

    @Autowired
    public void setRequestService(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping(value = "/admin-console")
    public ModelAndView adminPage(@ModelAttribute("user") UserDTO user, @ModelAttribute("requestDTO") RequestDTO requestDTO) {
        ModelAndView model = new ModelAndView(ADMIN_PAGE_VIEW_NAME);
        model.addObject("requests", requestService.getAllUnansweredRequests());
        return model;
    }

}
