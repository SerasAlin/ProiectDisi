package com.ps.backend.mapper;

import com.ps.backend.entity.Bug;
import com.ps.common.dto.BugDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-03T22:52:09+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class BugMapperImpl implements BugMapper {

    @Override
    public BugDTO toDTO(Bug bug) {
        if ( bug == null ) {
            return null;
        }

        BugDTO bugDTO = new BugDTO();

        bugDTO.setId( bug.getId() );
        bugDTO.setName( bug.getName() );
        bugDTO.setDescription( bug.getDescription() );
        bugDTO.setStatus( bug.getStatus() );

        return bugDTO;
    }
}
