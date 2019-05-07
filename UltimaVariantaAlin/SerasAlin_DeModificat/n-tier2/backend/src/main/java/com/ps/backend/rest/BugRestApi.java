package com.ps.backend.rest;

import com.ps.common.dto.BugDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/bug")
public interface BugRestApi {

    @GetMapping("/{id}")
    BugDTO findById(@PathVariable("id") Long id);

    @GetMapping("/list")
    List<BugDTO> findAll();

    @PostMapping("/save")
    Long save(@RequestBody BugDTO bugDTO);


}
