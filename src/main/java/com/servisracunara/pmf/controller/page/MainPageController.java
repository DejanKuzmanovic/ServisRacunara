package com.servisracunara.pmf.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("page")
public class MainPageController {

    private static final String MAIN_PAGE_VIEW_NAME = "main-page";

    @GetMapping(value = "/index")
    public ModelAndView loginMessage(){
        return new ModelAndView(MAIN_PAGE_VIEW_NAME);
    }

}