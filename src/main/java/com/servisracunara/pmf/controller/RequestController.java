package com.servisracunara.pmf.controller;

import com.servisracunara.pmf.dto.RequestDTO;
import com.servisracunara.pmf.service.RequestService;
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
public class RequestController {

    private static final String REDIRECT_INDEX_PAGE = "redirect:/page/index";

    private RequestService requestService;

    @Autowired
    public void setRequestService(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping(value = "/request/create")
    public String createRequest(@ModelAttribute("request") RequestDTO requestDTO, BindingResult results,
                                HttpServletResponse response) throws IOException {

        JSONObject messages =
                results.hasErrors() ? attachErrors(results) : new JSONObject();

        if (messages.length() != 0) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, messages.toString());
            return null;
        }

        requestService.createRequest(requestDTO);

        return REDIRECT_INDEX_PAGE;
    }

    @PostMapping(value = "/request/answer")
    public String setAdminAnswer(@ModelAttribute("requestId") String requestId, @ModelAttribute("answer") String answer, BindingResult results,
                                HttpServletResponse response) throws IOException {

        JSONObject messages =
                results.hasErrors() ? attachErrors(results) : new JSONObject();

        if (messages.length() != 0) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, messages.toString());
            return null;
        }

        requestService.setAdminAnswer(requestId, answer);

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
