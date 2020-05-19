package com.servisracunara.pmf.controller.page;

import com.servisracunara.pmf.controller.RequestController;
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
public class RequestPageController {

    private static final String REQUEST_PAGE_VIEW_NAME = "user-requests";

    private RequestService requestService;

    @Autowired
    public void setRequestService(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping(value = "/requests")
    public ModelAndView loginMessage(@ModelAttribute("user") UserDTO user, @ModelAttribute("request") RequestDTO requestDTO) {
        ModelAndView model = new ModelAndView(REQUEST_PAGE_VIEW_NAME);
        model.addObject("requests", requestService.getAllRequests());
        return model;
    }

}
