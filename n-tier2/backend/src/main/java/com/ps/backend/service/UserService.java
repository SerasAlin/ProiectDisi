package com.ps.backend.service;

import com.ps.common.dto.UserDTO;

import java.util.List;

public interface UserService {

    String testService();

    UserDTO findById(Long id);

    List<UserDTO> findAll();

    Long save(UserDTO userDTO);

    Long update(Long id, UserDTO userDTO);

    UserDTO logIN(String username, String password);
}
