package com.example.drivingexam.service.impl;

import com.example.drivingexam.dto.request.UserRequest;
import com.example.drivingexam.model.User;
import com.example.drivingexam.repo.UserRepo;
import com.example.drivingexam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper mapper;
    @Override
    public String userSave(UserRequest dto) {
        User user = mapper.map(dto, User.class);
        user.setApproved(false);
        userRepo.save(user);
        return "saved";
    }
}
