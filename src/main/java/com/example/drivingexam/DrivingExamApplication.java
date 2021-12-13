package com.example.drivingexam;

import com.example.drivingexam.model.Role;
import com.example.drivingexam.model.User;
import com.example.drivingexam.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class DrivingExamApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DrivingExamApplication.class, args);
    }

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {

        if (userRepo.findAll().isEmpty()){
            String encode = encoder.encode("123456");

            User user = new User();
            user.setFullName("Abbas Musayev");
            user.setUsername("Abbas");
            user.setPassword(encode);
            user.setRoles(Arrays.asList(new Role(null,"ADMIN"),new Role(null,"USER")));
            user.setIsDelete(false);
            user.setEmail("abbas99musayev@gmail.com");
            userRepo.save(user);
        }

    }
}
