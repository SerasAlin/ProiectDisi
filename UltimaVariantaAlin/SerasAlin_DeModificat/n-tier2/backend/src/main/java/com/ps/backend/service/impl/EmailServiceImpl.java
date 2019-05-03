package com.ps.backend.service.impl;

import com.ps.backend.entity.Bug;
import com.ps.backend.entity.Email;
import com.ps.backend.mapper.EmailMapper;
import com.ps.backend.repository.EmailRepository;
import com.ps.backend.service.EmailService;
import com.ps.common.dto.EmailIDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final EmailRepository emailRepository;
    private final EmailMapper emailMapper;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender, EmailRepository emailRepository, EmailMapper emailMapper){
        this.emailSender = emailSender;
        this.emailRepository = emailRepository;
        this.emailMapper = emailMapper;
    }

    @Override
    public void sendNotification(Bug bug) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo("serasalin96@gmail.com");
        mail.setSubject("Bug name: " + bug.getName() + " " + "Bug status: " + bug.getStatus() );
        mail.setText("text");

        emailSender.send(mail);
    }

    @Override
    public EmailIDTO findById(Long id) {
        Email entity = emailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find email with ID: " + id));
        EmailIDTO email = new EmailIDTO();
        email.setId(entity.getId());
        email.setName(entity.getName());

        return email;
    }

    @Override
    public List<EmailIDTO> findAll() {
        return emailRepository.findAll()
                .stream()
                .map(emailMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Long save(EmailIDTO addressDTO) {
        Optional<Email> optionalAddress;

        if (addressDTO.getId() == null) {
            optionalAddress = Optional.empty();
        } else {
            optionalAddress = emailRepository.findById(addressDTO.getId());
        }

        Email address = optionalAddress.isPresent() ? optionalAddress.get() : new Email();
        address.setName(addressDTO.getName());

        return emailRepository.save(address).getId();

    }


}
