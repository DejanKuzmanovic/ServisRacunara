package com.servisracunara.pmf.controller;

import com.servisracunara.pmf.dto.UserDTO;
import com.servisracunara.pmf.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("page")
public class UserController {

    private static final String REDIRECT_INDEX_PAGE = "redirect:/page/index";

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user/registration")
    public String registerUserAccount(@ModelAttribute("user1") UserDTO user, BindingResult results,
                                      HttpServletResponse response) throws IOException {

        JSONObject messages =
                results.hasErrors() ? attachErrors(results) : new JSONObject();

        if (messages.length() != 0) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, messages.toString());
            return null;
        }

        userService.registerUserAccount(user);

        return REDIRECT_INDEX_PAGE;
    }

    private JSONObject attachErrors(BindingResult results) {
        JSONObject messages = new JSONObject();
        JSONArray array = new JSONArray();

        messages.put("ERRORS", array);

        results.getAllErrors().forEach(x -> array.put(x.getDefaultMessage()));

        return messages;
    }

}
