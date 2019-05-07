package com.ps.backend.service.impl;

import com.ps.backend.entity.Bug;
import com.ps.backend.mapper.BugMapper;
import com.ps.backend.repository.BugRepository;
import com.ps.backend.service.BugService;
import com.ps.backend.service.EmailService;
import com.ps.common.dto.BugDTO;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BugServiceImpl implements BugService {

    private final BugRepository bugRepository;
    private final BugMapper bugMapper;
    private final EmailService emailService;

    public BugServiceImpl(BugRepository bugRepository, BugMapper bugMapper, EmailService emailService) {
        this.bugRepository = bugRepository;
        this.bugMapper = bugMapper;
        this.emailService = emailService;
    }

    @Override
    public BugDTO findById(Long Id) {
        Bug bug= bugRepository.findById(Id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find order with ID: " + Id));
        return bugMapper.toDTO(bug);
    }

    @Override
    public List<BugDTO> findAll() {
        return bugRepository.findAll()
                .stream()
                .map(bugMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Long save(BugDTO bugDTO) {
        Bug bug= bugDTO.getId() != null ?
                bugRepository.findById(bugDTO.getId()).orElseThrow(EntityNotFoundException::new) : new Bug();

        //  order.setTotal(orderDTO.getTotal());


        bug.setName(bugDTO.getName());
        bug.setDescription(bugDTO.getDescription());
        bug.setStatus(bugDTO.getStatus());

        bug.setUserId(bugDTO.getUser().getId());

        bug.setEmailId(bugDTO.getEmail().getId());

        try {
            emailService.sendNotification(bug);
            System.out.println("Success");
        } catch (MailException e) {
            e.printStackTrace();
            System.out.println("fail");
        }

        return bugRepository.save(bug).getId();
    }
}
