package com.ps.backend.rest.impl;

import com.ps.backend.rest.EmailRestApi;
import com.ps.backend.service.EmailService;
import com.ps.common.dto.EmailIDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmailRestController implements EmailRestApi {

    private final EmailService emailService;

    @Autowired
    public EmailRestController(EmailService emailService){
        this.emailService = emailService;
    }

    @Override
    public EmailIDTO findById(@PathVariable("id") Long id) {
        return emailService.findById(id);
    }

    @Override
    public List<EmailIDTO> findAll() {
        return emailService.findAll();
    }

    @Override
    public Long save(@RequestBody EmailIDTO emailIDTO) {
        return emailService.save(emailIDTO);
    }
}
