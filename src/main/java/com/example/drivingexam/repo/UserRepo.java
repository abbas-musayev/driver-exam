package com.example.drivingexam.repo;

import com.example.drivingexam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}
