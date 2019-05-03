package com.ps.frontend.gateway;
import com.ps.common.dto.*;
import java.util.List;


public interface EmailGateway {

    EmailIDTO findById(Long id);

    List<EmailIDTO> findAll();

    Long save(EmailIDTO addressDTO);
}
