package com.example.drivingexam.controller;

import com.example.drivingexam.dto.request.UserRequest;
import com.example.drivingexam.dto.response.UserResponse;
import com.example.drivingexam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserRequest request){
        return userService.registerUser(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-unapproved")
    public List<UserResponse> getUnApproved(){
        return userService.getUnApproved();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam String username){
        return userService.deleteUser(username);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/add-role")
    public String addRoleUser(@RequestParam String username,@RequestParam String role){
        return userService.addRole(username,role);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/accept-approve")
    public String acceptApprov(@RequestParam String username,@RequestParam Boolean isboolean){
        return userService.acceptApprov(username,isboolean);
    }


    @PostAuthorize("hasAnyRole('USER')")
    @PostMapping("/update")
    public String updateUser(UserRequest dto){
        return userService.updateUser(dto);
    }

    @PostAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/all")
    public List<UserResponse> getAll(){
        return userService.getAll();
    }

    @PostAuthorize("hasAnyRole('USER')")
    @PostMapping("/change-password")
    public String changePassword(String username,String oldPassword,String newPassword){
        return userService.changePassword(username,oldPassword,newPassword);
    }


}