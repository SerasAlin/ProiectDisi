package com.ps.common.dto;

public class BugDTO {

    private Long id;
    // private int total;

    private String name;
    private String description;
    private String status;

    private NameIdDTO user;
    private EmailIDTO email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NameIdDTO getUser() {
        return user;
    }

    public void setUser(NameIdDTO user) {
        this.user = user;
    }

    public EmailIDTO getEmail() {
        return email;
    }

    public void setEmail(EmailIDTO email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
