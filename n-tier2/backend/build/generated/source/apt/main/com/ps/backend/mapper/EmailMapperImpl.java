package com.ps.backend.mapper;

import com.ps.backend.entity.Email;
import com.ps.common.dto.EmailIDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-07T18:58:22+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_201 (Oracle Corporation)"
)
@Component
public class EmailMapperImpl implements EmailMapper {

    @Override
    public EmailIDTO toDTO(Email email) {
        if ( email == null ) {
            return null;
        }

        EmailIDTO emailIDTO = new EmailIDTO();

        emailIDTO.setId( email.getId() );
        emailIDTO.setName( email.getName() );

        return emailIDTO;
    }
}
