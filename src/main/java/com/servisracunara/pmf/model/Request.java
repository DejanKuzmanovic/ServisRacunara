package com.servisracunara.pmf.model;

import javax.persistence.*;

@Entity
@Table(name = "request")
public class Request {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String phone;

    private String description;

    private String answer;

    private Boolean approved;

    @ManyToOne()
    private User user;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPhone() {
        return phone;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }

    public String getAnswer() {
        return answer;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
