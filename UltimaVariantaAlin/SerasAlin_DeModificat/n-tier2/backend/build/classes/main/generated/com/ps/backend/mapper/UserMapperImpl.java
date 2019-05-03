package com.ps.backend.mapper;

import com.ps.backend.entity.User;
import com.ps.common.dto.NameIdDTO;
import com.ps.common.dto.UserDTO;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-03T22:52:09+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setUsername( userDTO.getUsername() );
        user.setPassword( userDTO.getPassword() );
        user.setRole( userDTO.getRole() );

        return user;
    }

    @Override
    public UserDTO toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setPassword( user.getPassword() );
        userDTO.setRole( user.getRole() );

        return userDTO;
    }

    @Override
    public NameIdDTO toNameIdDTO(User user) {
        if ( user == null ) {
            return null;
        }

        NameIdDTO nameIdDTO = new NameIdDTO();

        nameIdDTO.setName( user.getUsername() );
        nameIdDTO.setId( user.getId() );

        return nameIdDTO;
    }

    @Override
    public Set<NameIdDTO> toNameIdDTOs(Collection<User> users) {
        if ( users == null ) {
            return null;
        }

        Set<NameIdDTO> set = new HashSet<NameIdDTO>( Math.max( (int) ( users.size() / .75f ) + 1, 16 ) );
        for ( User user : users ) {
            set.add( toNameIdDTO( user ) );
        }

        return set;
    }
}
