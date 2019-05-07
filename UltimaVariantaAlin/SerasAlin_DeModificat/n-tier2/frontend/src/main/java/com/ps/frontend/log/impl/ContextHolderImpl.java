package com.ps.frontend.log.impl;

import com.ps.common.dto.UserDTO;
import com.ps.frontend.log.ContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ContextHolderImpl implements ContextHolder {

    private static UserDTO userDTO;

    @Override
    public void setLoggedIn(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public UserDTO getLoggedIn() {
        return userDTO;
    }
}
