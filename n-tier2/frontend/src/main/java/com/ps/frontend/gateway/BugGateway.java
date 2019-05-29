package com.ps.frontend.gateway;

import com.ps.common.dto.BugDTO;

import java.util.List;

public interface BugGateway {

    BugDTO findById(Long id);

    List<BugDTO> findAll();

    List<BugDTO> filterByStatus(String status);

    List<BugDTO> findByName(String name);

    Long save(BugDTO bugDTO);

    Long update(Long id, BugDTO bugDTO);

}
