package com.example.teachermanagement.service;

import com.example.teachermanagement.dtos.UserDTO;
import com.example.teachermanagement.model.User;

public interface IUserService {
    User createUser(UserDTO userDTO) throws Exception;
    String login(String email, String password, Long roleId) throws Exception;
}
