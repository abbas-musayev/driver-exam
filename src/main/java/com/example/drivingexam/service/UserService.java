package com.example.drivingexam.service;

import com.example.drivingexam.dto.request.UserRequest;
import com.example.drivingexam.dto.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    String registerUser(UserRequest dto);
    String deleteUser(String username);
    Boolean deleted(String username);
    String updateUser(UserRequest dto);
    List<UserResponse> getAll();
    String changePassword(String username,String oldPassword,String newPassword);


    String acceptApprov(String username,Boolean isboolean);
    String addRole(String username,String role);
    Boolean approved(String username);
    List<UserResponse> getUnApproved();
}
