package com.ps.backend.rest.impl;


import com.ps.backend.rest.BugRestApi;
import com.ps.backend.service.BugService;
import com.ps.common.dto.BugDTO;
import com.ps.common.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BugRestController implements BugRestApi {

    private final BugService bugService;

    @Autowired
    public BugRestController(BugService bugService) {
        this.bugService = bugService;
    }


    @Override
    public BugDTO findById(@PathVariable("id") Long id) {
        return bugService.findById(id);
    }

    @Override
    public List<BugDTO> findAll() {
        return bugService.findAll();
    }

    @Override
    public Long save(@RequestBody BugDTO bugDTO) {
        return bugService.save(bugDTO);
    }

    @Override
    public Long update(@PathVariable("id") Long id,@RequestBody BugDTO bugDTO) {
        return bugService.update(id,bugDTO);
    }

}
