package com.ps.backend.service.impl;

import com.ps.backend.entity.User;
import com.ps.backend.mapper.UserMapper;
import com.ps.backend.repository.UserRepository;
import com.ps.backend.service.UserService;
import com.ps.common.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
   // private final CarRepository carRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
       // this.carRepository = carRepository;
        this.userMapper = userMapper;
    }

    @Override
    public String testService() {
        return "service working";
    }

    @Override
    public UserDTO findById(Long id) {
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find user with ID: " + id));
        UserDTO user = new UserDTO();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setPassword(entity.getPassword());
        user.setRole(entity.getRole());

        return user;
    }

    @Override
    public List<UserDTO> findAll() {
        //List<Car> all = carRepository.findAll();

        return userRepository.findAll().stream().map(entity -> {
            UserDTO user = new UserDTO();
            user.setId(entity.getId());
            user.setUsername(entity.getUsername());
            user.setPassword(entity.getPassword());
            user.setRole(entity.getRole());
            return user;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long save(UserDTO userDTO) {
        Optional<User> optionalUser;

        if (userDTO.getId() == null) {
            optionalUser = Optional.empty();
        } else {
            optionalUser = userRepository.findById(userDTO.getId());
        }

        User user = optionalUser.isPresent() ? optionalUser.get() : new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());

        return userRepository.save(user).getId();
    }

    @Override
    public UserDTO logIN(String username, String password) {
        return userRepository.findAll().stream()
                .filter(x->((x.getUsername().equals(username)) && (x.getPassword().equals(password))))
                .map(userMapper::toDto)
                .findAny()
                .orElse(null);
    }


}
