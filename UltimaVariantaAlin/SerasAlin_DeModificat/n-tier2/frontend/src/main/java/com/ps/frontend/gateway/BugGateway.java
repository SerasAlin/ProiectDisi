package com.ps.frontend.gateway;

import com.ps.common.dto.BugDTO;

import java.util.List;

public interface BugGateway {

    BugDTO findById(Long id);

    List<BugDTO> findAll();

    Long save(BugDTO bugDTO);

}
