package com.ps.frontend.log;

import com.ps.common.dto.UserDTO;

public interface ContextHolder {

    void setLoggedIn(UserDTO userDTO);

    UserDTO getLoggedIn();
}
