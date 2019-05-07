package com.ps.backend.mapper;


import com.ps.backend.entity.Bug;
import com.ps.backend.entity.Email;
import com.ps.backend.entity.User;
import com.ps.common.dto.BugDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {User.class, Email.class })
public interface BugMapper {

    BugDTO toDTO(Bug bug);

}