package com.ps.backend.service;

import com.ps.backend.entity.Bug;
import com.ps.common.dto.EmailIDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmailService {
    void sendNotification(Bug bug);

    EmailIDTO findById(Long Id);

    List<EmailIDTO> findAll();

    Long save(EmailIDTO emailIDTO);
}
