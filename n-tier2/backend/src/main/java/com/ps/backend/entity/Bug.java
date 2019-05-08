package com.ps.backend.entity;

import javax.persistence.*;

@Entity
public class Bug {


        @Id
        @GeneratedValue
        private Long id;
        private String name;
        private String description;
        private String status;


    @Column(name = "user_id", nullable = false)
    private Long userId;

//    @ManyToOne
//    @JoinColumn(name = "id", insertable = false, updatable = false)
//    private User user;

    @Column(name = "email_id", nullable = false)
    private Long emailId;


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }
}
