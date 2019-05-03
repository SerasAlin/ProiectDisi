package com.ps.backend.service;

import com.ps.common.dto.BugDTO;

import java.util.List;

public interface BugService {


    BugDTO findById(Long Id);

    List<BugDTO> findAll();

    Long save(BugDTO bugDTO);


}
