package com.ps.backend.service;

import com.ps.common.dto.BugDTO;
import com.ps.common.dto.UserDTO;

import java.util.List;

public interface BugService {


    BugDTO findById(Long Id);

    List<BugDTO> findAll();

    Long save(BugDTO bugDTO);

    Long update(Long id, BugDTO bugDTO);
}
