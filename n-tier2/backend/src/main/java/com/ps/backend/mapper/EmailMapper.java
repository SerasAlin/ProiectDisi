package com.ps.backend.mapper;

import com.ps.backend.entity.Email;
import com.ps.common.dto.EmailIDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface EmailMapper {

    EmailIDTO toDTO(Email email);

}
