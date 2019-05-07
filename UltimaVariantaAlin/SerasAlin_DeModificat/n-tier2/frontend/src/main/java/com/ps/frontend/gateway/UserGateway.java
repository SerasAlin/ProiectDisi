package com.ps.frontend.gateway;

import com.ps.common.dto.UserDTO;

import java.util.List;

public interface UserGateway {

    String test();

    UserDTO findById(Long id);

    List<UserDTO> findAll();

    Long save(UserDTO userDTO);

    UserDTO logIN(String username, String password);
}
