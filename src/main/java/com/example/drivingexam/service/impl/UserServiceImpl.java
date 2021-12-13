package com.example.drivingexam.service.impl;

import com.example.drivingexam.config.EncryptPassword;
import com.example.drivingexam.dto.request.UserRequest;
import com.example.drivingexam.dto.response.UserResponse;
import com.example.drivingexam.model.Role;
import com.example.drivingexam.model.User;
import com.example.drivingexam.repo.UserRepo;
import com.example.drivingexam.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private static final String HASH_KEY = "USER";

    private final UserRepo userRepo;
    private final ModelMapper mapper;
    private RedisTemplate redisTemplate;

    @Override
    public String registerUser(UserRequest request) {

        User userByUsername = userRepo.findUserByUsername(request.getUsername());
        User userByEmail = userRepo.findUserByEmail(request.getEmail());
        if (userByUsername == null || userByUsername.getIsDelete()) {
            if (userByEmail == null || userByEmail.getIsDelete()) {
                User user = mapper.map(request, User.class);
                String encrypt = EncryptPassword.hashPassword(user.getPassword());
                user.setPassword(encrypt);
                user.setApproved(false);
                user.setIsDelete(false);
                User save = userRepo.save(user);
                redisTemplate.opsForHash().put(HASH_KEY, save.getId(), save);
                return "Please wait until admin approved";
            } else
                throw new RuntimeException("Email already exists");
        } else
            throw new RuntimeException("Username already exists");
    }

    // Admin Useri approved etmek ucun request atir
    @Override
    public String acceptApprov(String username, Boolean isboolean) {

        User user = userRepo.findUserByUsername(username);
        user.setApproved(isboolean);
        User save = userRepo.save(user);
        redisTemplate.opsForHash().put(HASH_KEY, save.getId(), save);

        if (isboolean) {
            return "User Activated";
        }
        return "User Deaktived";
    }

    @Override
    public String deleteUser(String username) {
        User user = userRepo.findUserByUsername(username);
        log.info("Delete User Method {} ", user);
        if (user == null) {
            throw new RuntimeException("DELETE USER ERROR");
        }
        user.setIsDelete(true);

        userRepo.save(user);
        redisTemplate.opsForHash().delete(HASH_KEY, user.getId());
        return "User deleted";
    }

    @Override
    public Boolean deleted(String username) {
        User userByUsername = userRepo.findUserByUsername(username);
        return userByUsername.getIsDelete();
    }

    @Override
    public String updateUser(UserRequest dto) {
        User user = userRepo.findUserByUsername(dto.getUsername());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setFullName(dto.getFullName());
        user.setPhone(dto.getPhone());

        User save = userRepo.save(user);
        redisTemplate.opsForHash().put(HASH_KEY, save.getId(), user);

        return "User Updated";
    }

    @Override
    public List<UserResponse> getAll() {
        List<UserResponse> response = new ArrayList<>();

        List<User> values = redisTemplate.opsForHash().values(HASH_KEY);
        List<User> all = userRepo.findAll();


        if (!values.isEmpty()) {
            log.info("GetALl From Redis");
            for (User item : values){
                response.add(mapper.map(item, UserResponse.class));
            }
        } else {
            log.info("GetALl From DataBase");
            for (User item : all) {
                response.add(mapper.map(item, UserResponse.class));
            }
        }
        return response;
    }

    @Override
    public String changePassword(String username, String oldPassword, String newPassword) {
        String encode = EncryptPassword.hashPassword(oldPassword);
        User cache = (User) redisTemplate.opsForHash().get(HASH_KEY, username);

        if (cache == null) {
            User user = userRepo.findUserByUsername(username);
            if (user.getPassword().equals(encode)) {
                user.setPassword(EncryptPassword.hashPassword(newPassword));

                User save = userRepo.save(user);
                redisTemplate.opsForHash().put(HASH_KEY, save.getId(), save);

                return "Password Changed";
            } else {
                throw new RuntimeException("Incorrect password");
            }
        } else {
            log.info("ChangePassword -> FindUserByUsername From Redis");
            if (cache.getPassword().equals(encode)) {
                cache.setPassword(EncryptPassword.hashPassword(newPassword));

                User save = userRepo.save(cache);
                redisTemplate.opsForHash().put(HASH_KEY, save.getId(), save);

                return "Password Changed";
            } else {
                throw new RuntimeException("Incorrect password");
            }
        }


    }

    @Override
    public String addRole(String username, String role) {
        log.info("ADD ROLE METHOD STARTED");
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username %s not found" + username));
        log.info("USERNAME NOT NULL");
        List<Role> roles = user.getRoles();
        for (Role item : roles) {
            if (item.getName().equals(role)) ;
            return "This Role (" + role + ") already exists for User";
        }
        roles.add(new Role(null, role));
        user.setRoles(roles);
        User save = userRepo.save(user);
        redisTemplate.opsForHash().put(HASH_KEY, save.getId(), save);
        log.info("USER SAVED");
        return "Role Added";
    }

    @Override
    public Boolean approved(String username) {
        User user = userRepo.findUserByUsername(username);
        return user.getApproved();
    }

    @Override
    public List<UserResponse> getUnApproved() {
        List<User> users = userRepo.getUsersByUnApproved(false);
        List<UserResponse> responses = new ArrayList<>();

        for (User user : users) {
            responses.add(mapper.map(user, UserResponse.class));
        }

        return responses;
    }
}