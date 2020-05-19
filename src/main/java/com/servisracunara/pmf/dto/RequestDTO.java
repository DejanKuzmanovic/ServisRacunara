package com.servisracunara.pmf.dto;

import javax.validation.constraints.NotNull;

public class RequestDTO {

    @NotNull
    private String title;

    @NotNull
    private String phone;

    @NotNull
    private String description;

    private String answer;

    private Boolean approved;

    public String getTitle() {
        return title;
    }

    public String getPhone() {
        return phone;
    }

    public String getDescription() {
        return description;
    }

    public String getAnswer() {
        return answer;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
