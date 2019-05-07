package com.ps.backend.rest;

import com.ps.common.dto.EmailIDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/email")
public interface EmailRestApi {
    @GetMapping("/{id}")
    EmailIDTO findById(@PathVariable("id") Long id);

    @GetMapping("/list")
    List<EmailIDTO> findAll();

    @PostMapping("/save")
    Long save(@RequestBody EmailIDTO emailIDTO);
}
